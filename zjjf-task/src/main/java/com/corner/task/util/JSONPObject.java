package com.corner.task.util;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;

import java.io.IOException;
import java.io.Serializable;

public class JSONPObject implements JsonSerializable, Serializable {
	/**
	 * JSONP function name to use for serialization
	 */
	public String function;

	/**
	 * Value to be serialized as JSONP padded; can be null.
	 */
	public Object value;

	public JavaType serializationType;

	public JSONPObject(String function, Object value) {
		this(function, value, (JavaType) null);
	}

	public JSONPObject(String function, Object value, JavaType asType) {
		this.function = function;
		this.value = value;
		this.serializationType = asType;
	}

	@Override
	public void serializeWithType(JsonGenerator jgen, SerializerProvider provider, TypeSerializer typeSer) throws IOException {
		// First, wrapping:
		jgen.writeRaw(function);
		jgen.writeRaw('(');
		if (value == null) {
			provider.getDefaultNullValueSerializer().serialize(value, jgen, provider);
		} else if (typeSer != null) {
			provider.findValueSerializer(typeSer.getClass()).serialize(value, jgen, provider);
		} else {
			Class<?> cls = value.getClass();
			provider.findValueSerializer(cls).serialize(value, jgen, provider);
		}
		jgen.writeRaw(')');
	}

	public void serialize(JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
		// First, wrapping:
		jgen.writeRaw(function);
		jgen.writeRaw('(');
		if (value == null) {
			provider.getDefaultNullValueSerializer().serialize(value, jgen, provider);
		} else if (serializationType != null) {
			provider.findValueSerializer(serializationType.getClass()).serialize(value, jgen, provider);
		} else {
			Class<?> cls = value.getClass();
			provider.findValueSerializer(cls).serialize(value, jgen, provider);
		}
		jgen.writeRaw(')');
	}

	@JsonIgnore
	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	@JsonIgnore
	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	@JsonIgnore
	public JavaType getSerializationType() {
		return serializationType;
	}

	public void setSerializationType(JavaType serializationType) {
		this.serializationType = serializationType;
	}
}

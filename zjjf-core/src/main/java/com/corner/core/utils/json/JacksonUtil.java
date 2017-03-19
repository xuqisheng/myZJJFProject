package com.corner.core.utils.json;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * 
 * @ClassName: JSONUtil
 * @Description: TODO(输入一个对象，返回json string)
 * @author luke
 * @email luke@mibodoctor.com
 * @date 2015年2月4日 下午12:31:46
 * 
 */
public class JacksonUtil {

	private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	private static final ObjectMapper objectMapper;

	static {
		SimpleDateFormat dateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
		objectMapper = new ObjectMapper();
		objectMapper.setDateFormat(dateFormat);
	}
	
	public static ObjectMapper getObjectMapper(){
		return objectMapper;
	}

	/*
	 * JSON to Object
	 */
	public static <T> T JSONToObject(String json, Class<T> clazz) {
		try {
			return objectMapper.readValue(json, clazz);
		} catch (IOException e) {
			throw new RuntimeException("将对象转换为json字符时失败!");
		}
	}

	/*
	 * Object to JSON
	 */
	public static String objectToJSONString(Object obj) {
		String result = "";
		try {
			result = objectMapper.writeValueAsString(obj);
		} catch (Exception e) {
			result = "";
		}
		return result;
	}

	/*
	 * readJsonFromFile file="user.json"
	 */
	// public static<T> T readJsonFromFile(String file,Class<T> clazz) {
	// return objectMapper.readValue(new File(file),clazz);
	// mapper.writeValue(new File("user-modified.json"), user);
	// }

	/*
	 * read "Raw" Data Json to file
	 */
	public static <T> void readJsonFromRawData(String file, Class<T> clazz) {
		Map<String, Object> userData = new HashMap<String, Object>();
		Map<String, String> nameStruct = new HashMap<String, String>();
		nameStruct.put("first", "Joe");
		nameStruct.put("last", "Sixpack");
		userData.put("name", nameStruct);
		userData.put("gender", "MALE");
		userData.put("verified", Boolean.FALSE);
		userData.put("userImage", "Rm9vYmFyIQ==");
		try {
			objectMapper.writeValue(new File("user.json"), userData);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * read Json to "Raw" Data
	 */
	// public static<T> void readJsonToFile(String file,Class<T> clazz) {
	// Map<String,Object> userData = new HashMap<String,Object>();
	// Map<String,String> nameStruct = new HashMap<String,String>();
	// nameStruct.put("first", "Joe");
	// nameStruct.put("last", "Sixpack");
	// userData.put("name", nameStruct);
	// userData.put("gender", "MALE");
	// userData.put("verified", Boolean.FALSE);
	// userData.put("userImage", "Rm9vYmFyIQ==");
	// try {
	// Map<String,User> result = mapper.readValue(src, new
	// TypeReference<Map<String,User>>() { });
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	
	/*
	 * Tree Model Example
	 * can read from 
	 */
	public static JsonNode readJsonStringToModel( String jsonString ) {
		JsonNode rootNode = null;
		try {
			rootNode = objectMapper.readTree(jsonString);
			JsonNode nameNode = rootNode.path("name");
			String lastName = nameNode.path("last").textValue();
			if ("xmler".equalsIgnoreCase(lastName)) {
				((ObjectNode)nameNode).put("last", "Jsoner");
			}
			// and write it out:
			//objectMapper.writeValue(new File("user-modified.json"), rootNode);
			//TreeMapper treeMapper = new TreeMapper();
			//ObjectNode userOb = treeMapper.objectNode();
			//Object nameOb = userRoot.putObject("name");
			//nameOb.put("first", "Joe");
			//nameOb.put("last", "Sixpack");
			//userOb.put("gender", User.Gender.MALE.toString());
			//userOb.put("verified", false);
			//byte[] imageData = getImageData(); // or wherever it comes from
			//userOb.put("userImage", imageData);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return rootNode;
	}

	/*
	 * Streaming API Example
	 */
	public static <T> void readJsonToModel(String file, Class<T> clazz) {
		JsonFactory f = objectMapper.getFactory();
		JsonGenerator g;
		try {
			g = f.createGenerator(new File("user.json"), JsonEncoding.UTF8);
			g.writeStartObject();
			g.writeObjectFieldStart("name");
			g.writeStringField("first", "Joe");
			g.writeStringField("last", "Sixpack");
			g.writeEndObject(); // for field 'name'
			g.writeStringField("gender", "male");
			g.writeBooleanField("verified", false);
			g.writeFieldName("userImage"); // no 'writeBinaryField' (yet?)
			byte[] binaryData = "132".getBytes();
			g.writeBinary(binaryData);
			g.writeEndObject();
			g.close(); // important: will force flushing of output, close underlying output stream
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * read Json to Model and modify value
	 */
	public static <T> void parserJsonToModel(String file, Class<T> clazz) {
		try {
			JsonFactory f = objectMapper.getFactory();
			JsonParser jp =  f.createParser(new File("user.json"));
			//User user = new User();
			jp.nextToken(); // will return JsonToken.START_OBJECT (verify?)
			while (jp.nextToken() != JsonToken.END_OBJECT) {
				String fieldname = jp.getCurrentName();
				jp.nextToken(); // move to value, or START_OBJECT/START_ARRAY
				if ("name".equals(fieldname)) { // contains an object
					//Name name = new Name();
					while (jp.nextToken() != JsonToken.END_OBJECT) {
						String namefield = jp.getCurrentName();
						jp.nextToken(); // move to value
						if ("first".equals(namefield)) {
							//name.setFirst(jp.getText());
						} else if ("last".equals(namefield)) {
							//name.setLast(jp.getText());
						} else {
							throw new IllegalStateException("Unrecognized field '" + fieldname + "'!");
						}
					}
					//user.setName(name);
				} else if ("gender".equals(fieldname)) {
					//user.setGender(User.Gender.valueOf(jp.getText()));
				} else if ("verified".equals(fieldname)) {
					//user.setVerified(jp.getCurrentToken() == JsonToken.VALUE_TRUE);
				} else if ("userImage".equals(fieldname)) {
					//user.setUserImage(jp.getBinaryValue());
				} else {
					throw new IllegalStateException("Unrecognized field '" + fieldname + "'!");
				}
			}
			jp.close(); // ensure resources get cleaned up timely and properly
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

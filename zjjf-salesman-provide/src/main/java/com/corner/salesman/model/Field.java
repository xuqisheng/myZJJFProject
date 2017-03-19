package com.corner.salesman.model;


/**
 * 该字段提供报告组装列名称及对应值所用
 * @author Administrator
 *
 */
public class Field{

    public Field() {}
    
    public Field(String key, String value) {
		super();
		this.key = key;
		this.value = value;
	}

	private String key;
    
    private String value;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
    
}
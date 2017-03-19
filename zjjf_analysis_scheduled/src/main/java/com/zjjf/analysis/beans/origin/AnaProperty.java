package com.zjjf.analysis.beans.origin;

public class AnaProperty {
    private Integer id;

    private String ana_key;

    private String ana_value;

    private String desc_r;

    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAna_key() {
        return ana_key;
    }

    public void setAna_key(String ana_key) {
        this.ana_key = ana_key;
    }

    public String getAna_value() {
        return ana_value;
    }

    public void setAna_value(String ana_value) {
        this.ana_value = ana_value == null ? null : ana_value.trim();
    }

    public String getDesc_r() {
		return desc_r;
	}

	public void setDesc_r(String desc_r) {
		this.desc_r = desc_r;
	}

	public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}
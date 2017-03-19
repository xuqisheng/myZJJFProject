package com.corner.core.beans;

public class UserLoginInfo {
    private String uli_id;

    private Byte uli_device_type;

    private String uli_device_UUID;

    private Integer uli_push_code;

    public String getUli_id() {
        return uli_id;
    }

    public void setUli_id(String uli_id) {
        this.uli_id = uli_id == null ? null : uli_id.trim();
    }

    public Byte getUli_device_type() {
        return uli_device_type;
    }

    public void setUli_device_type(Byte uli_device_type) {
        this.uli_device_type = uli_device_type;
    }

    public String getUli_device_UUID() {
        return uli_device_UUID;
    }

    public void setUli_device_UUID(String uli_device_UUID) {
        this.uli_device_UUID = uli_device_UUID == null ? null : uli_device_UUID.trim();
    }

    public Integer getUli_push_code() {
        return uli_push_code;
    }

    public void setUli_push_code(Integer uli_push_code) {
        this.uli_push_code = uli_push_code;
    }
}
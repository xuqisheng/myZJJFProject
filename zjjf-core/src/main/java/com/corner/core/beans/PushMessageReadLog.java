package com.corner.core.beans;

import java.util.Date;

public class PushMessageReadLog {
    private Long msg_lg_id;

    private String msg_lg_msgid;

    private String msg_lg_uid;

    private Boolean msg_lg_flag;

    private Date msg_lg_rdtime;

    public Long getMsg_lg_id() {
        return msg_lg_id;
    }

    public void setMsg_lg_id(Long msg_lg_id) {
        this.msg_lg_id = msg_lg_id;
    }

    public String getMsg_lg_msgid() {
        return msg_lg_msgid;
    }

    public void setMsg_lg_msgid(String msg_lg_msgid) {
        this.msg_lg_msgid = msg_lg_msgid == null ? null : msg_lg_msgid.trim();
    }

    public String getMsg_lg_uid() {
        return msg_lg_uid;
    }

    public void setMsg_lg_uid(String msg_lg_uid) {
        this.msg_lg_uid = msg_lg_uid == null ? null : msg_lg_uid.trim();
    }

    public Boolean getMsg_lg_flag() {
        return msg_lg_flag;
    }

    public void setMsg_lg_flag(Boolean msg_lg_flag) {
        this.msg_lg_flag = msg_lg_flag;
    }

    public Date getMsg_lg_rdtime() {
        return msg_lg_rdtime;
    }

    public void setMsg_lg_rdtime(Date msg_lg_rdtime) {
        this.msg_lg_rdtime = msg_lg_rdtime;
    }
}
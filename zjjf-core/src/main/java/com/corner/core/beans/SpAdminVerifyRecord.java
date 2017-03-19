package com.corner.core.beans;

import java.util.Date;

public class SpAdminVerifyRecord {
    private Integer id;

    private String verifyadminId;

    private String verifyAdminNm;

    private Integer verifyObjIntId;

    private String verifyObjectId;

    private String verifyObjectNm;

    private String actionNm;

    private Date actionTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVerifyadminId() {
        return verifyadminId;
    }

    public void setVerifyadminId(String verifyadminId) {
        this.verifyadminId = verifyadminId == null ? null : verifyadminId.trim();
    }

    public String getVerifyAdminNm() {
        return verifyAdminNm;
    }

    public void setVerifyAdminNm(String verifyAdminNm) {
        this.verifyAdminNm = verifyAdminNm == null ? null : verifyAdminNm.trim();
    }

    public Integer getVerifyObjIntId() {
        return verifyObjIntId;
    }

    public void setVerifyObjIntId(Integer verifyObjIntId) {
        this.verifyObjIntId = verifyObjIntId;
    }

    public String getVerifyObjectId() {
        return verifyObjectId;
    }

    public void setVerifyObjectId(String verifyObjectId) {
        this.verifyObjectId = verifyObjectId == null ? null : verifyObjectId.trim();
    }

    public String getVerifyObjectNm() {
        return verifyObjectNm;
    }

    public void setVerifyObjectNm(String verifyObjectNm) {
        this.verifyObjectNm = verifyObjectNm == null ? null : verifyObjectNm.trim();
    }

    public String getActionNm() {
        return actionNm;
    }

    public void setActionNm(String actionNm) {
        this.actionNm = actionNm == null ? null : actionNm.trim();
    }

    public Date getActionTime() {
        return actionTime;
    }

    public void setActionTime(Date actionTime) {
        this.actionTime = actionTime;
    }
}
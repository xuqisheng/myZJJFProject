package com.corner.core.beans;

public class StoreInfo {
    private Integer id;

    private String tuijianRenTel;

    private String yewuRenTel;

    private String idCardUpPic;

    private String idCardDownPic;

    private String licensePic;

    private String licenseNum;

    private String tobaccoPic;

    private String backCardPic;

    private String sendRegion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTuijianRenTel() {
        return tuijianRenTel;
    }

    public void setTuijianRenTel(String tuijianRenTel) {
        this.tuijianRenTel = tuijianRenTel == null ? null : tuijianRenTel.trim();
    }

    public String getYewuRenTel() {
        return yewuRenTel;
    }

    public void setYewuRenTel(String yewuRenTel) {
        this.yewuRenTel = yewuRenTel == null ? null : yewuRenTel.trim();
    }

    public String getIdCardUpPic() {
        return idCardUpPic;
    }

    public void setIdCardUpPic(String idCardUpPic) {
        this.idCardUpPic = idCardUpPic == null ? null : idCardUpPic.trim();
    }

    public String getIdCardDownPic() {
        return idCardDownPic;
    }

    public void setIdCardDownPic(String idCardDownPic) {
        this.idCardDownPic = idCardDownPic == null ? null : idCardDownPic.trim();
    }

    public String getLicensePic() {
        return licensePic;
    }

    public void setLicensePic(String licensePic) {
        this.licensePic = licensePic == null ? null : licensePic.trim();
    }

    public String getLicenseNum() {
        return licenseNum;
    }

    public void setLicenseNum(String licenseNum) {
        this.licenseNum = licenseNum == null ? null : licenseNum.trim();
    }

    public String getTobaccoPic() {
        return tobaccoPic;
    }

    public void setTobaccoPic(String tobaccoPic) {
        this.tobaccoPic = tobaccoPic == null ? null : tobaccoPic.trim();
    }

    public String getBackCardPic() {
        return backCardPic;
    }

    public void setBackCardPic(String backCardPic) {
        this.backCardPic = backCardPic == null ? null : backCardPic.trim();
    }

    public String getSendRegion() {
        return sendRegion;
    }

    public void setSendRegion(String sendRegion) {
        this.sendRegion = sendRegion == null ? null : sendRegion.trim();
    }
}
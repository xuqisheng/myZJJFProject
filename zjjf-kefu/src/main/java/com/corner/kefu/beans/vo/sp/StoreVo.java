package com.corner.kefu.beans.vo.sp;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import com.corner.core.beans.SpGroup;
import com.corner.core.beans.vo.ABaseVo;
import com.corner.core.utils.code.MatrixUtil;

/**
 * 商铺信息加省市区
 * 
 * @author Howe at 2015年2月9日下午5:03:22
 * @Email itzihao@sina.com
 * @Desc
 */
public class StoreVo extends ABaseVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String imgPre = MatrixUtil.fastfdspreurl;
	private String id;// 商铺ID
	private String ordId;// 商铺排序权重
	private String name;// 商铺名称
	private String suppliercode;// 商铺编码
	private String lng;// 经度
	private String lat;// 纬度
	private String img;// 商铺照片
	private String sales;// 商铺总销售量
	private String sendTimeBegin;// 配送起始时间
	private String sendTimeEnd;// 配送结束时间
	private String sendess;// 起送费用
	private String totalComment;// 商铺总评论数
	private String avgComment;// 商铺评价平均分数
	private String type;// 状态（打烊、加班、放假）
	private String amount;// 总金额
	private String explain;// 商铺服务说明
	private String address;// 详细地址
	private String provincename;// 省
	private String cityname;// 市
	private String areaname;// 区
	private Integer provinceid;// 省编号
	private Integer cityid;// 市编号
	private Integer areaid;// 区编号
	private double range;// 距离
	private String qrcodeurl ;
	private String userid;//用户id
	private String userName;//用户名
	private String mobile;//用户手机号
	private String useraddress;//用户上次使用地址编号
	private String isFirstsinfle;//是否标记为首单
	private Integer year;//当前年
	private Integer month;//当前月
	private Integer date;//当前日
	private Integer hour;//当前时
	private Integer minute;//当前分
	private String contact;//店主
	private Integer storetype;//店铺状态，0不是首单，地址存在，1不是首单，地址不存在，旗舰店，2不是首单，地址不存在，不是旗舰店，3首单旗舰店，4首单不是旗舰店
	private Byte status;//店铺状态
	private String spGropName;//定格名称
	private Byte isSelected;//是否被选中
	private String voucherMoney;//优惠劵面额
    private Date dateTime;
    private String acGroupId;//用户组id
    private String spVoucherId;
    private String orderId;//订单编号
    private String areaStr;//店铺所属区域字符串
    private String licenseNum;//营业执照号码
    private String idCardUpPic;//身份证正面图片地址
    private String idCardDownPic;//身份证背面图片地址
    private String licensePic;//营业执照图片地址
    
    private List<SpGroup> spGroupList;//定格集合
    
    
    //用于优惠劵
    @Override
    public boolean equals(Object obj) {
    	Boolean b = false;
    	if(obj!=null){
    		String id = (String) obj;
    		if(this.id.equals(id)){
    			b=true;
    		}
    	}
    	return b;
    }
	
	public Date getDateTime() {
		return dateTime;
	}
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	public String getVoucherMoney() {
		return voucherMoney;
	}
	public void setVoucherMoney(String voucherMoney) {
		this.voucherMoney = voucherMoney;
	}
	public Byte getIsSelected() {
		return isSelected;
	}
	public void setIsSelected(Byte isSelected) {
		this.isSelected = isSelected;
	}
	public Byte getStatus() {
		return status;
	}
	public void setStatus(Byte status) {
		this.status = status;
	}

	public String getQrcodeurl() {
		return qrcodeurl;
	}

	public void setQrcodeurl(String qrcodeurl) {
		this.qrcodeurl = qrcodeurl;
	}

	

	public String getSuppliercode() {
		return suppliercode;
	}

	public void setSuppliercode(String suppliercode) {
		this.suppliercode = suppliercode;
	}

	public double getRange() {
		return range;
	}

	public void setRange(double range) {
		this.range = range;
	}

	public String getProvincename() {
		return provincename;
	}

	public void setProvincename(String provincename) {
		this.provincename = provincename;
	}

	public String getCityname() {
		return cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}

	public String getAreaname() {
		return areaname;
	}

	public void setAreaname(String areaname) {
		this.areaname = areaname;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrdId() {
		return ordId;
	}

	public void setOrdId(String ordId) {
		this.ordId = ordId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = imgPre + img;
	}

	public String getSales() {
		return sales;
	}

	public void setSales(String sales) {
		this.sales = sales;
	}

	public String getSendTimeBegin() {
		return sendTimeBegin;
	}

	public void setSendTimeBegin(String sendTimeBegin) {
		this.sendTimeBegin = sendTimeBegin;
	}

	public String getSendTimeEnd() {
		return sendTimeEnd;
	}

	public void setSendTimeEnd(String sendTimeEnd) {
		this.sendTimeEnd = sendTimeEnd;
	}

	public String getSendess() {
		return sendess;
	}

	public void setSendess(String sendess) {
		this.sendess = sendess;
	}

	public String getTotalComment() {
		return totalComment;
	}

	public void setTotalComment(String totalComment) {
		this.totalComment = totalComment;
	}

	public String getAvgComment() {
		return avgComment;
	}

	public void setAvgComment(String avgComment) {
		this.avgComment = avgComment;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getProvinceid() {
		return provinceid;
	}

	public void setProvinceid(Integer provinceid) {
		this.provinceid = provinceid;
	}

	public Integer getCityid() {
		return cityid;
	}

	public void setCityid(Integer cityid) {
		this.cityid = cityid;
	}

	public Integer getAreaid() {
		return areaid;
	}

	public void setAreaid(Integer areaid) {
		this.areaid = areaid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	
	public String getUseraddress() {
		return useraddress;
	}

	public void setUseraddress(String useraddress) {
		this.useraddress = useraddress;
	}

	public String getIsFirstsinfle() {
		return isFirstsinfle;
	}

	public void setIsFirstsinfle(String isFirstsinfle) {
		this.isFirstsinfle = isFirstsinfle;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getDate() {
		return date;
	}

	public void setDate(Integer date) {
		this.date = date;
	}

	public Integer getHour() {
		return hour;
	}

	public void setHour(Integer hour) {
		this.hour = hour;
	}

	public Integer getMinute() {
		return minute;
	}

	public void setMinute(Integer minute) {
		this.minute = minute;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public Integer getStoretype() {
		return storetype;
	}

	public void setStoretype(Integer storetype) {
		this.storetype = storetype;
	}
	public String getSpGropName() {
		return spGropName;
	}
	public void setSpGropName(String spGropName) {
		this.spGropName = spGropName;
	}

	public String getAcGroupId() {
		return acGroupId;
	}

	public void setAcGroupId(String acGroupId) {
		this.acGroupId = acGroupId;
	}

	public String getSpVoucherId() {
		return spVoucherId;
	}

	public void setSpVoucherId(String spVoucherId) {
		this.spVoucherId = spVoucherId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getAreaStr() {
		return areaStr;
	}

	public void setAreaStr(String areaStr) {
		this.areaStr = areaStr;
	}

	public String getLicenseNum() {
		return licenseNum;
	}

	public void setLicenseNum(String licenseNum) {
		this.licenseNum = licenseNum;
	}

	public String getIdCardUpPic() {
		return idCardUpPic;
	}

	public void setIdCardUpPic(String idCardUpPic) {
		this.idCardUpPic = idCardUpPic;
	}

	public String getIdCardDownPic() {
		return idCardDownPic;
	}

	public void setIdCardDownPic(String idCardDownPic) {
		this.idCardDownPic = idCardDownPic;
	}

	public String getLicensePic() {
		return licensePic;
	}

	public void setLicensePic(String licensePic) {
		this.licensePic = licensePic;
	}

	public List<SpGroup> getSpGroupList() {
		return spGroupList;
	}

	public void setSpGroupList(List<SpGroup> spGroupList) {
		this.spGroupList = spGroupList;
	}
}

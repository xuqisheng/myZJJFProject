package com.corner.scms.beans.ro;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.corner.core.beans.ScmsOrderDetail;
import com.corner.core.beans.ro.AmazeUIListRo;
import com.corner.scms.config.CommonPageConfig;

public class ScmsOrderInfoMgCondition extends AmazeUIListRo{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1284697009164468103L;
	
	
	/**工作台**/
	private String staging;
	/**订单号，客户名称，手机号**/
	private String orderId;
	private String jsType;
	private String supportStatus;
	/** 订单开始时间 */
    private String getOrderTimeStart;
    /** 订单结束时间     */
    private String getOrderTimeEnd;
    /**页数     */
    private int pageIndex = CommonPageConfig.commonPageIndex;
    /**页码     */
	private int pageSize = CommonPageConfig.commonPageSize;
    /**是否删除     */
    private String isDelete;
 	// token
 	private String token;
 	private String sortOrder;
 	private String sortName;
 	private String id;

    private String pId;

    private String tradeNo;

    private Date addTime;

    private BigDecimal goodsPrice;

    private BigDecimal orderPrice;

    private BigDecimal rebate;

    private BigDecimal coupon;

    private String couponId;

    private String userId;

    private String userName;
    private String name;

    private String userRemark;

    private String storeId;

    private String storeName;

    private String consignee;

    private String mobile;

    private String userTel;

    private String address;

    private String status;

    private Byte evaluation;

    private Byte supportmetho;

    private Date supportTime;

    private Byte ordertype;

    private String closemsg;

    private Date gaveTime;

    private String supplierId;

    private String supplierTel;

    private String supplierNam;

    private Byte spStatus;

    private String spRemark;

    private Date spCheckTime;

    private Date printTime;

    private Date deliveryTime;

    private Date getOrderTime;

    private Date ackTime;

    private Short ackCode;

    private Byte isStar;

    private Date sendDate;

    private Date fistTime;

    private Date endTime;

    private String qrcodeurl;

    private Byte isRemind;

    private BigDecimal zmaoli;

    private BigDecimal zfee;

    private String acId;

    private String acStatus;

    private String acRemark;

    private Date acCheckTime;

    private Date acSettleTime;

    private Date acPayTime;

    private Byte level;

    private BigDecimal freight;

    private String col1;

    private String col2;

    private String col3;
 	

    private String contact;
    
    /**商品信息**/
    private String[] itemBaseId;
    private String[] itemBaseName;
    private String[] quantity;//购买数量
    private String[] barCode;//条形码
    private String[] spec;//条形码
    private String[] price; //单价
    private String storeType;//客户类型
    
    private String baseId; // 商品ID
    private String editStoreType;	//编辑的客户类型
    private String editJsType;	//编辑的客户类型
    private String editSupportStatus;	//编辑的客户类型
    private BigDecimal salePrice;	//出货价格
    
    
    
    public String getStaging() {
		return staging;
	}

	public void setStaging(String staging) {
		this.staging = staging;
	}

	public BigDecimal getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}

	public String getBaseId() {
		return baseId;
	}

	public void setBaseId(String baseId) {
		this.baseId = baseId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEditStoreType() {
		return editStoreType;
	}

	public void setEditStoreType(String editStoreType) {
		this.editStoreType = editStoreType;
	}

	public String getEditJsType() {
		return editJsType;
	}

	public void setEditJsType(String editJsType) {
		this.editJsType = editJsType;
	}

	public String getEditSupportStatus() {
		return editSupportStatus;
	}

	public void setEditSupportStatus(String editSupportStatus) {
		this.editSupportStatus = editSupportStatus;
	}

	/**子订单信息**/
    private List<ScmsOrderDetail> scmsOrderDetails;
    
    
 	public List<ScmsOrderDetail> getScmsOrderDetails() {
		return scmsOrderDetails;
	}

	public void setScmsOrderDetails(List<ScmsOrderDetail> scmsOrderDetails) {
		this.scmsOrderDetails = scmsOrderDetails;
	}

	
	public String[] getPrice() {
		return price;
	}

	public void setPrice(String[] price) {
		this.price = price;
	}

	public String getStoreType() {
		return storeType;
	}

	public void setStoreType(String storeType) {
		this.storeType = storeType;
	}

	public String[] getItemBaseId() {
		return itemBaseId;
	}

	public void setItemBaseId(String[] itemBaseId) {
		this.itemBaseId = itemBaseId;
	}

	public String[] getItemBaseName() {
		return itemBaseName;
	}

	public void setItemBaseName(String[] itemBaseName) {
		this.itemBaseName = itemBaseName;
	}

	public String[] getQuantity() {
		return quantity;
	}

	public void setQuantity(String[] quantity) {
		this.quantity = quantity;
	}

	public String[] getBarCode() {
		return barCode;
	}

	public void setBarCode(String[] barCode) {
		this.barCode = barCode;
	}

	public String[] getSpec() {
		return spec;
	}

	public void setSpec(String[] spec) {
		this.spec = spec;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public int getPageIndex() {
		return pageIndex*pageSize;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getSortName() {
		return sortName;
	}

	public void setSortName(String sortName) {
		this.sortName = sortName;
	}

	public String getGetOrderTimeStart() {
		return getOrderTimeStart;
	}

	public void setGetOrderTimeStart(String getOrderTimeStart) {
		this.getOrderTimeStart = getOrderTimeStart;
	}

	public String getGetOrderTimeEnd() {
		return getOrderTimeEnd;
	}

	public void setGetOrderTimeEnd(String getOrderTimeEnd) {
		this.getOrderTimeEnd = getOrderTimeEnd;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId == null ? null : orderId.trim();
	}

	public String getJsType() {
		return jsType;
	}

	public void setJsType(String jsType) {
		this.jsType = jsType;
	}

	public String getSupportStatus() {
		return supportStatus;
	}

	public void setSupportStatus(String supportStatus) {
		this.supportStatus = supportStatus;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public BigDecimal getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(BigDecimal goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public BigDecimal getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(BigDecimal orderPrice) {
		this.orderPrice = orderPrice;
	}

	public BigDecimal getRebate() {
		return rebate;
	}

	public void setRebate(BigDecimal rebate) {
		this.rebate = rebate;
	}

	public BigDecimal getCoupon() {
		return coupon;
	}

	public void setCoupon(BigDecimal coupon) {
		this.coupon = coupon;
	}

	public String getCouponId() {
		return couponId;
	}

	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserRemark() {
		return userRemark;
	}

	public void setUserRemark(String userRemark) {
		this.userRemark = userRemark;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getUserTel() {
		return userTel;
	}

	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Byte getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(Byte evaluation) {
		this.evaluation = evaluation;
	}

	public Byte getSupportmetho() {
		return supportmetho;
	}

	public void setSupportmetho(Byte supportmetho) {
		this.supportmetho = supportmetho;
	}

	public Date getSupportTime() {
		return supportTime;
	}

	public void setSupportTime(Date supportTime) {
		this.supportTime = supportTime;
	}

	public Byte getOrdertype() {
		return ordertype;
	}

	public void setOrdertype(Byte ordertype) {
		this.ordertype = ordertype;
	}

	public String getClosemsg() {
		return closemsg;
	}

	public void setClosemsg(String closemsg) {
		this.closemsg = closemsg;
	}

	public Date getGaveTime() {
		return gaveTime;
	}

	public void setGaveTime(Date gaveTime) {
		this.gaveTime = gaveTime;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierTel() {
		return supplierTel;
	}

	public void setSupplierTel(String supplierTel) {
		this.supplierTel = supplierTel;
	}

	public String getSupplierNam() {
		return supplierNam;
	}

	public void setSupplierNam(String supplierNam) {
		this.supplierNam = supplierNam;
	}

	public Byte getSpStatus() {
		return spStatus;
	}

	public void setSpStatus(Byte spStatus) {
		this.spStatus = spStatus;
	}

	public String getSpRemark() {
		return spRemark;
	}

	public void setSpRemark(String spRemark) {
		this.spRemark = spRemark;
	}

	public Date getSpCheckTime() {
		return spCheckTime;
	}

	public void setSpCheckTime(Date spCheckTime) {
		this.spCheckTime = spCheckTime;
	}

	public Date getPrintTime() {
		return printTime;
	}

	public void setPrintTime(Date printTime) {
		this.printTime = printTime;
	}

	public Date getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public Date getGetOrderTime() {
		return getOrderTime;
	}

	public void setGetOrderTime(Date getOrderTime) {
		this.getOrderTime = getOrderTime;
	}

	public Date getAckTime() {
		return ackTime;
	}

	public void setAckTime(Date ackTime) {
		this.ackTime = ackTime;
	}

	public Short getAckCode() {
		return ackCode;
	}

	public void setAckCode(Short ackCode) {
		this.ackCode = ackCode;
	}

	public Byte getIsStar() {
		return isStar;
	}

	public void setIsStar(Byte isStar) {
		this.isStar = isStar;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public Date getFistTime() {
		return fistTime;
	}

	public void setFistTime(Date fistTime) {
		this.fistTime = fistTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getQrcodeurl() {
		return qrcodeurl;
	}

	public void setQrcodeurl(String qrcodeurl) {
		this.qrcodeurl = qrcodeurl;
	}

	public Byte getIsRemind() {
		return isRemind;
	}

	public void setIsRemind(Byte isRemind) {
		this.isRemind = isRemind;
	}

	public BigDecimal getZmaoli() {
		return zmaoli;
	}

	public void setZmaoli(BigDecimal zmaoli) {
		this.zmaoli = zmaoli;
	}

	public BigDecimal getZfee() {
		return zfee;
	}

	public void setZfee(BigDecimal zfee) {
		this.zfee = zfee;
	}

	public String getAcId() {
		return acId;
	}

	public void setAcId(String acId) {
		this.acId = acId;
	}

	public String getAcStatus() {
		return acStatus;
	}

	public void setAcStatus(String acStatus) {
		this.acStatus = acStatus;
	}

	public String getAcRemark() {
		return acRemark;
	}

	public void setAcRemark(String acRemark) {
		this.acRemark = acRemark;
	}

	public Date getAcCheckTime() {
		return acCheckTime;
	}

	public void setAcCheckTime(Date acCheckTime) {
		this.acCheckTime = acCheckTime;
	}

	public Date getAcSettleTime() {
		return acSettleTime;
	}

	public void setAcSettleTime(Date acSettleTime) {
		this.acSettleTime = acSettleTime;
	}

	public Date getAcPayTime() {
		return acPayTime;
	}

	public void setAcPayTime(Date acPayTime) {
		this.acPayTime = acPayTime;
	}

	public Byte getLevel() {
		return level;
	}

	public void setLevel(Byte level) {
		this.level = level;
	}

	public BigDecimal getFreight() {
		return freight;
	}

	public void setFreight(BigDecimal freight) {
		this.freight = freight;
	}

	public String getCol1() {
		return col1;
	}

	public void setCol1(String col1) {
		this.col1 = col1;
	}

	public String getCol2() {
		return col2;
	}

	public void setCol2(String col2) {
		this.col2 = col2;
	}

	public String getCol3() {
		return col3;
	}

	public void setCol3(String col3) {
		this.col3 = col3;
	}
	
}
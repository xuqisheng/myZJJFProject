package com.corner.kefu.beans.ro;

import java.math.BigDecimal;
import java.util.Date;

import com.corner.core.beans.ro.AmazeUIListRo;

public class ItemBaseRo extends AmazeUIListRo{
	//************************************************
	private static final long serialVersionUID = 1L;
	
	private Integer id;

	private Integer cateid;

	private String mdseId;

	private Integer ordid;

	private String name;

	private String brand;

	private String spec;

	private String imgs;

	private String imgb;

	private BigDecimal price;

	private Integer sales;

	private Boolean news;

	private Boolean tuijian;

	private Date addtime;

	private Byte checkStatus;
	
	private String checkUser;
	
	private Date checkTime;
	
	private Date updatetime;
	private Byte useType;
	private Byte status;
	private Boolean isDelete;
	private String shortNm;
	private String quanNm;
	private String pkg;
	private Integer pkgNum;
	private String taste;
	private String measure;
	private Integer upId;
	private Integer brandId;
    private String color;
    private String tgId;
    private Integer upper;
    private Integer lower;
	
	//************************************************
	
	private String noAndName;
	private Integer cateId;
	private Integer yiJiId;
	private Integer erJiId;
	private String brandName;
	
	private String itemBaseName;

	
	public Integer getCateId() {
		return cateId;
	}

	public void setCateId(Integer cateId) {
		this.cateId = cateId;
	}

	public Integer getYiJiId() {
		return yiJiId;
	}

	public void setYiJiId(Integer yiJiId) {
		this.yiJiId = yiJiId;
	}

	public Integer getErJiId() {
		return erJiId;
	}

	public void setErJiId(Integer erJiId) {
		this.erJiId = erJiId;
	}

	public String getNoAndName() {
		return noAndName;
	}

	public void setNoAndName(String noAndName) {
		this.noAndName = noAndName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCateid() {
		return cateid;
	}

	public void setCateid(Integer cateid) {
		this.cateid = cateid;
	}

	public String getMdseId() {
		return mdseId;
	}

	public void setMdseId(String mdseId) {
		this.mdseId = mdseId;
	}

	public Integer getOrdid() {
		return ordid;
	}

	public void setOrdid(Integer ordid) {
		this.ordid = ordid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public String getImgs() {
		return imgs;
	}

	public void setImgs(String imgs) {
		this.imgs = imgs;
	}

	public String getImgb() {
		return imgb;
	}

	public void setImgb(String imgb) {
		this.imgb = imgb;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getSales() {
		return sales;
	}

	public void setSales(Integer sales) {
		this.sales = sales;
	}

	public Boolean getNews() {
		return news;
	}

	public void setNews(Boolean news) {
		this.news = news;
	}

	public Boolean getTuijian() {
		return tuijian;
	}

	public void setTuijian(Boolean tuijian) {
		this.tuijian = tuijian;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public Byte getUseType() {
		return useType;
	}

	public void setUseType(Byte useType) {
		this.useType = useType;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public String getShortNm() {
		return shortNm;
	}

	public void setShortNm(String shortNm) {
		this.shortNm = shortNm;
	}

	public String getPkg() {
		return pkg;
	}

	public void setPkg(String pkg) {
		this.pkg = pkg;
	}

	public Integer getPkgNum() {
		return pkgNum;
	}

	public void setPkgNum(Integer pkgNum) {
		this.pkgNum = pkgNum;
	}

	public String getTaste() {
		return taste;
	}

	public void setTaste(String taste) {
		this.taste = taste;
	}

	public String getMeasure() {
		return measure;
	}

	public void setMeasure(String measure) {
		this.measure = measure;
	}

	public Integer getUpId() {
		return upId;
	}

	public void setUpId(Integer upId) {
		this.upId = upId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getTgId() {
		return tgId;
	}

	public void setTgId(String tgId) {
		this.tgId = tgId;
	}

	public String getQuanNm() {
		return quanNm;
	}

	public void setQuanNm(String quanNm) {
		this.quanNm = quanNm;
	}

	public Integer getUpper() {
		return upper;
	}

	public void setUpper(Integer upper) {
		this.upper = upper;
	}

	public Integer getLower() {
		return lower;
	}

	public void setLower(Integer lower) {
		this.lower = lower;
	}

	public Byte getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(Byte checkStatus) {
		this.checkStatus = checkStatus;
	}

	public String getCheckUser() {
		return checkUser;
	}

	public void setCheckUser(String checkUser) {
		this.checkUser = checkUser;
	}

	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public Boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}

	public String getItemBaseName() {
		return itemBaseName;
	}

	public void setItemBaseName(String itemBaseName) {
		this.itemBaseName = itemBaseName;
	}



}

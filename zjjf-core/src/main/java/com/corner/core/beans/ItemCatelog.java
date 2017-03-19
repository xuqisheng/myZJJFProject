package com.corner.core.beans;

import java.io.Serializable;
import java.util.List;

public class ItemCatelog  implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String name;

	private Integer pid;

	private String spid;

	private String img;

	private Byte ordId;

	private Boolean status;

	private Byte catelogType;

	private Boolean isDelete;

	private String col1;

	private String col2;

	private String col3;

	private List<StoreItem> storeItemList;

	//private List<StoreItemVo> storeItemVoList;

	private List<ItemCatelog> secondMenu;

	private Integer itemCount;

	//private List<DgPlantItemVo> plItemVoList;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getSpid() {
		return spid;
	}

	public void setSpid(String spid) {
		this.spid = spid == null ? null : spid.trim();
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img == null ? null : img.trim();
	}

	public Byte getOrdId() {
		return ordId;
	}

	public void setOrdId(Byte ordId) {
		this.ordId = ordId;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Byte getCatelogType() {
		return catelogType;
	}

	public void setCatelogType(Byte catelogType) {
		this.catelogType = catelogType;
	}

	public Boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}

	public String getCol1() {
		return col1;
	}

	public void setCol1(String col1) {
		this.col1 = col1 == null ? null : col1.trim();
	}

	public String getCol2() {
		return col2;
	}

	public void setCol2(String col2) {
		this.col2 = col2 == null ? null : col2.trim();
	}

	public String getCol3() {
		return col3;
	}

	public void setCol3(String col3) {
		this.col3 = col3 == null ? null : col3.trim();
	}

	public List<StoreItem> getStoreItemList() {
		return storeItemList;
	}

	public void setStoreItemList(List<StoreItem> storeItemList) {
		this.storeItemList = storeItemList;
	}

	public List<ItemCatelog> getSecondMenu() {
		return secondMenu;
	}

	public void setSecondMenu(List<ItemCatelog> secondMenu) {
		this.secondMenu = secondMenu;
	}

	public Integer getItemCount() {
		return itemCount;
	}

	public void setItemCount(Integer itemCount) {
		this.itemCount = itemCount;
	}
}
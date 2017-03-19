package com.corner.scms.beans.vo;

import java.util.List;

import com.corner.core.beans.Store;
import com.corner.core.beans.StoreGroup;

public class StoreGroupVo extends StoreGroup{

private List<Store> stores;
    
    private List<Store> noGroup;
    
    private String ids[];

	public List<Store> getStores() {
		return stores;
	}

	public void setStores(List<Store> stores) {
		this.stores = stores;
	}

	public List<Store> getNoGroup() {
		return noGroup;
	}

	public void setNoGroup(List<Store> noGroup) {
		this.noGroup = noGroup;
	}

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}
    
    
}

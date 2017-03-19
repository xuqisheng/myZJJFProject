package com.corner.kefu.dao;

import java.util.List;

import com.corner.core.beans.ScmsMinimum;



public interface ScmsMinimumMgMapper {
	List<ScmsMinimum> getScmsMinimumByManagerId(String managerId);
	
	ScmsMinimum getScmsMinimumByBrandIdAndManagerId(ScmsMinimum minimum);
}
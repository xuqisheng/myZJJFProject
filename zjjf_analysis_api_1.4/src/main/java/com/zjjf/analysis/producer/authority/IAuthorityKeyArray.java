package com.zjjf.analysis.producer.authority;

import java.util.HashMap;

public interface IAuthorityKeyArray {

	public Object[][] getAuthorityKeyArray(String userName, Integer menuId);

	public void getAuthorityCityOrArea(HashMap<String, Object> paramMap, String userName, Integer menuId);
}

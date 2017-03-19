package com.corner.salesman.service;

import java.util.List;

import com.corner.salesman.model.TmplDetailInfo;
import com.corner.salesman.model.TmplInfo;

public interface TmplInfoService {
	
    /**
     * 获取模板列表信息
     * @return
     */
    List<TmplInfo> findTmplInfoList() throws Exception;
    
    /**
     * 根据模板ID获取对应模板字段列表
     * @param tmplId
     * @return
     */
	public List<TmplDetailInfo> findTmplAttrList(String tmplId) throws Exception;
}

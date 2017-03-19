package com.corner.salesman.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.corner.salesman.model.TmplDetailInfo;

/**
 * 模板信息业务层接口
 * @author Administrator
 *
 */
public interface TmplDetailInfoService {
	
	
	/**
	 * 添加模板明细信息方法（同时添加多条）
	 * @param tmplVo
	 * @return
	 * @throws Exception
	 */
	public void addTmplDetailInfo(String tmplId,String[] tmplTrVals, String userId) throws Exception;
	
	/**
	 * 添加模板明细信息方法（仅添加一条）
	 * @param tmplVo
	 * @return
	 * @throws Exception
	 */
	public void addSingleTmplDetailInfo(String tmplId,String[] tmplTrVals, String userId) throws Exception;
	
    /**
     * 根据模板id查询模板相关属性
     * @param tmplId
     * @return
     */
	public List<TmplDetailInfo> findTmplDetailByTmplId(String tmplId) throws Exception;
	
	/**
	 * 根据ID删除模板明细信息方法
	 * @param tmplId
	 * @return
	 * @throws Exception
	 */
	public void delTmplDetailInfo(String tmplId) throws Exception;
	
}

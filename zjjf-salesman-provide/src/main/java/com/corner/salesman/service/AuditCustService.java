package com.corner.salesman.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 客户审核业务层接口
 * @author Administrator
 *
 */
public interface AuditCustService {

	
	/**
	 * 根据查询条件获取用户审核信息
	 * @param paramMap
	 * @return
	 */
	public List<HashMap<String,Object>> getAuditCustList(Map<String,Object> paramMap) throws Exception;
	
	/**
	 * 修改客户审核状态
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public int updateStoreStatus(Map<String,Object> paramMap) throws Exception;
	
	/**
	 * 获取审核客户明细信息
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> getAuditCustDetail(Map<String,Object> paramMap) throws Exception;
	
}

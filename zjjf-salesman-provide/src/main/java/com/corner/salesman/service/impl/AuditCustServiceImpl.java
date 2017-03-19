package com.corner.salesman.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.corner.rpc.salesman.api.service.UserService;
import com.corner.rpc.shop.api.service.StoreService;
import com.corner.salesman.service.AuditCustService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 客户审核业务层接口
 * @author Administrator
 *
 */
@Service("auditCustService")
@Transactional(readOnly = true)
public class AuditCustServiceImpl implements AuditCustService {

	//private static final Logger logger = LoggerFactory.getLogger(AuditCustServiceImpl.class);
	@Autowired
	private UserService userService;
	@Autowired
	private StoreService storeService;
	
	/**
	 * 客户审核业务层接口
	 * @author Administrator
	 *
	 */
	@Override
	public List<HashMap<String,Object>> getAuditCustList(Map<String, Object> paramMap)throws Exception {
		List<String> mobileList = null;
		//String userId = paramMap.get("userId");
		Object deptId = paramMap.get("deptId");
		Object userType = paramMap.get("userType");
		Object mobile = paramMap.get("mobile");
		Object status = paramMap.get("status");
		
		List<String> saleManMobiles = Lists.newArrayList();
		//如果是普通用户则直接使用当前的mobile做条件查询
		if("0".equals(userType)){
			saleManMobiles.add(mobile.toString());
		}
		//如果是管理者，则使用部门ID获取部门下所有用户注册手机号做查询条件
		else if("1".equals(userType)){
			if(null != deptId){
				mobileList = userService.getUserMobileList(deptId.toString());
			}
			//将获取部门用户注册手机号添加查询集合
			if(null != mobileList && !mobileList.isEmpty()){
				saleManMobiles.addAll(mobileList);
			}
		}
		
		//根据审核状态和业务员手机号获取待审核列表信息
		Map<String, Object> paramMap2 = Maps.newHashMap();
		paramMap2.put("status", status);
		paramMap2.put("saleManMobiles", saleManMobiles);
		List<HashMap<String, Object>> relustList = storeService.getStoreList(paramMap2);
		
		return relustList;
	}
	
	/**
	 * 修改客户审核状态
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@Override
	public int updateStoreStatus(Map<String,Object> paramMap) throws Exception{
		return storeService.updateStoreStatus(paramMap);
	}
	
	/**
	 * 获取审核客户明细信息
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> getAuditCustDetail(Map<String,Object> paramMap) throws Exception{
		return storeService.getStoreDetailInfo(paramMap);
	}

}

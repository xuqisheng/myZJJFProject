/**   
* @Title: ERPManagerContractServiceImpl.java 
* @Package com.corner.kefu.service.erp.impl 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年9月3日 下午3:17:13 
* @version V1.0   
*/

package com.corner.kefu.service.erp.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.beans.ERPManagerContract;
import com.corner.core.beans.vo.Pager;
import com.corner.core.dao.ERPManagerContractMapper;
import com.corner.core.utils.StringUtil;
import com.corner.kefu.beans.ro.erp.ERPManagerContractRo;
import com.corner.kefu.beans.vo.erp.ERPManagerContractVo;
import com.corner.kefu.dao.RegionMgMapper;
import com.corner.kefu.dao.erp.ERPManagerContractMgMapper;
import com.corner.kefu.service.erp.ERPManagerContractService;

/** 
* @ClassName: ERPManagerContractServiceImpl 
* @Description:
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年9月3日 下午3:17:13 
*  
*/
@Service
public class ERPManagerContractServiceImpl implements ERPManagerContractService {

	@Autowired
	ERPManagerContractMapper eRPManagerContractMapper;
	@Autowired
	RegionMgMapper regionMgMapper;
	@Autowired
	ERPManagerContractMgMapper eRPManagerContractMgMapper;

	@Override
	public void save(ERPManagerContract managerContract) {
		if(StringUtils.isNotEmpty(managerContract.getId())){
			managerContract.setUpdateTime(new Date());
			eRPManagerContractMapper.updateByPrimaryKeySelective(managerContract);
		}else {
			managerContract.setId(StringUtil.getUUID());
			Date date = new Date();
			managerContract.setAddTime(date);
			managerContract.setUpdateTime(date);
			eRPManagerContractMapper.insertSelective(managerContract);
		}
	}

	@Override
	public Pager<ERPManagerContractVo> getContractList(ERPManagerContractRo managerContractRo) {
		if(managerContractRo.getRegionLevel()!=null&&managerContractRo.getRegionLevel()==0){
			managerContractRo.setRegionLevel(null);
			managerContractRo.setRegionId(null);
		}
	    List<ERPManagerContractVo> contractList = eRPManagerContractMgMapper.getContractList(managerContractRo);
	    Integer count = 0 ;
	    if(contractList!=null&&contractList.size()!=0){
	    	count = eRPManagerContractMgMapper.getCountContractList(managerContractRo);
	    }
		return new Pager<ERPManagerContractVo>(count, contractList);
	}

	@Override
	public void delContract(ERPManagerContractRo managerContractRo) {
        eRPManagerContractMgMapper.delContract(managerContractRo);		
	}

	@Override
	public ERPManagerContract selectByPrimaryKey(String id) {
		return eRPManagerContractMapper.selectByPrimaryKey(id);
	}
}

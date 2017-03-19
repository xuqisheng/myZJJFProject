/**   
* @Title: ScmsSpSignResultMgServiceImpl.java 
* @Package com.corner.scms.service.sp.impl 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年2月23日 下午2:27:05 
* @version V1.0   
*/

package com.corner.scms.service.sp.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.beans.SignResult;
import com.corner.core.beans.Supplier;
import com.corner.core.dao.SignResultMapper;
import com.corner.scms.dao.ScmsSpSignResultMgMapper;
import com.corner.scms.service.impl.BaseServiceImpl;
import com.corner.scms.service.sp.ScmsSpSignResultMgService;

/** 
* @ClassName: ScmsSpSignResultMgServiceImpl 
* @Description:
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年2月23日 下午2:27:05 
*  
*/
@Service
public class ScmsSpSignResultMgServiceImpl extends BaseServiceImpl implements ScmsSpSignResultMgService {
	
	@Autowired
	ScmsSpSignResultMgMapper scmsSpSignResultMgMapper;
	@Autowired
	SignResultMapper signResultMapper;

	/**
	 * 
	* Title: selectSignResult 
	* Description:查询签署协议 
	* @param map
	* @return
	* @throws Exception 
	* @see com.corner.scms.service.sp.ScmsSpSignResultMgService#selectSignResult(java.util.Map)
	 */
	@Override
	public List<SignResult> selectSignResult(Map<String, Object> map) throws Exception {
		return scmsSpSignResultMgMapper.selectSignResult(map);
	}

	
	/**
	 * 
	* Title: addSignResult 
	* Description:签署协议 
	* @param map
	* @throws Exception 
	* @see com.corner.scms.service.sp.ScmsSpSignResultMgService#addSignResult(java.util.Map)
	 */
	@Override
	public void addSignResult(Map<String, Object> map) throws Exception {
		SignResult signResult = new SignResult();
		signResult.setProtocolId(Integer.parseInt((String)map.get("id")));
		Supplier supplier = (Supplier) map.get("supplier");
		signResult.setUserId(supplier.getId());
		signResult.setSignResult(new Byte("1"));
		signResult.setSignTime(new Date());
		signResult.setUserNm(supplier.getSupplierName());
		signResult.setUserPwd(supplier.getPassword());
        signResultMapper.insertSelective(signResult);		
	}

}

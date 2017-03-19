/**   
* @Title: SpFinWalletServiceImpl.java 
* @Package com.corner.kefu.service.sp.impl 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年7月18日 下午6:00:16 
* @version V1.0   
*/

package com.corner.kefu.service.sp.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.corner.core.beans.FinWallet;
import com.corner.core.beans.RechargeGrade;
import com.corner.core.beans.Supplier;
import com.corner.core.beans.vo.Pager;
import com.corner.core.dao.FinWalletMapper;
import com.corner.core.dao.RechargeGradeMapper;
import com.corner.core.dao.StoreMapper;
import com.corner.core.dao.SupplierMapper;
import com.corner.core.utils.StringUtil;
import com.corner.kefu.beans.ro.sp.FinWalletMgRo;
import com.corner.kefu.beans.ro.sp.FinWalletRechargeInfoRo;
import com.corner.kefu.beans.ro.sp.FinWalletRo;
import com.corner.kefu.beans.vo.sp.FinWalletLogVo;
import com.corner.kefu.beans.vo.sp.FinWalletRechargeInfoVo;
import com.corner.kefu.beans.vo.sp.FinWalletVo;
import com.corner.kefu.beans.vo.sp.RechargeGradeVo;
import com.corner.kefu.dao.UserMgMapper;
import com.corner.kefu.dao.sp.FinWalletLogMgMapper;
import com.corner.kefu.dao.sp.FinWalletRechargeInfoMgMapper;
import com.corner.kefu.dao.sp.SpFinWalletMgMapper;
import com.corner.kefu.dao.sp.SpRechargeGradeMgMapper;
import com.corner.kefu.service.sp.SpFinWalletService;

/** 
* @ClassName: SpFinWalletServiceImpl 
* @Description:
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年7月18日 下午6:00:16 
*  
*/
@Service
public class SpFinWalletServiceImpl implements SpFinWalletService {
 
	@Autowired
	private SpRechargeGradeMgMapper spRechargeGradeMgMapper;
	
	@Autowired
	private SpFinWalletMgMapper spFinWalletMgMapper;
	
	@Autowired
	private FinWalletMapper finWalletMapper;
	
	@Autowired
	private FinWalletRechargeInfoMgMapper finWalletRechargeInfoMgMapper;
	
	@Autowired
	RechargeGradeMapper rechargeGradeMapper;
	
	@Autowired
	FinWalletLogMgMapper finWalletLogMgMapper;
	
	@Autowired
	StoreMapper storeMapper;
	
	@Autowired
	SupplierMapper supplierMapper;
	
	@Autowired
	UserMgMapper userMgMapper;
	
	/**
	 * 
	* Title: getRechargeGradeList 
	* Description:获取充值梯度列表 
	* @return 
	* @see com.corner.kefu.service.sp.SpFinWalletService#getRechargeGradeList()
	 */
	@Override
	public List<RechargeGradeVo> getRechargeGradeList() {
		return spRechargeGradeMgMapper.getRechargeGradeList();
	}

	
	/**
	 * 
	* Title: getStoreWalletList 
	* Description:获取钱包明细列表 
	* @param finWalletRo
	* @return 
	* @see com.corner.kefu.service.sp.SpFinWalletService#getStoreWalletList(com.corner.kefu.beans.ro.sp.FinWalletRo)
	 */
	@Override
	public Pager<FinWalletVo> getStoreWalletList(FinWalletRo finWalletRo) {
		List<FinWalletVo> list = spFinWalletMgMapper.getStoreWalletList(finWalletRo);
		Integer totalSize = spFinWalletMgMapper.getCountStoreWalletList(finWalletRo);
		return new Pager<FinWalletVo>(totalSize,list);
	}


	/**
	 * 
	* Title: updateStoreFinWallet 
	* Description:修改钱包status状态 
	* @param finWalletRo 
	* @see com.corner.kefu.service.sp.SpFinWalletService#updateStoreFinWallet(com.corner.kefu.beans.ro.sp.FinWalletRo)
	 */
	@Override
	public void updateStoreFinWallet(FinWalletRo finWalletRo) {
		finWalletMapper.updateByPrimaryKeySelective(finWalletRo);
	}


	@Override
	public Pager<FinWalletRechargeInfoVo> getAllWalletRechargeInfo(FinWalletRechargeInfoRo rechargeInfo) {
		if(rechargeInfo.getMobile() != null && !"".equals(rechargeInfo.getMobile())){
			rechargeInfo.setMobile(rechargeInfo.getMobile().trim());
		}
		if(rechargeInfo.getEndTime() != null){
			Date endTime = DateUtils.addDays(rechargeInfo.getEndTime(), 1);
			rechargeInfo.setEndTime(endTime);
		}
		
		List<FinWalletRechargeInfoVo> list = finWalletRechargeInfoMgMapper.getAllWalletRechargeInfo(rechargeInfo);
		for (FinWalletRechargeInfoVo finWalletRechargeInfoVo : list) {
			if(!StringUtils.isEmpty(finWalletRechargeInfoVo.getSupplierId())&&!StringUtils.isEmpty(finWalletRechargeInfoVo.getStoreId())){
				finWalletRechargeInfoVo.setUserTypeStr("小店/批发商");
			}else if (!StringUtils.isEmpty(finWalletRechargeInfoVo.getSupplierId())) {
				finWalletRechargeInfoVo.setUserTypeStr("批发商");
			}else if (!StringUtils.isEmpty(finWalletRechargeInfoVo.getStoreId())) {
				finWalletRechargeInfoVo.setUserTypeStr("小店");
			}else {
				finWalletRechargeInfoVo.setUserTypeStr("");
			}
		}
		int num = finWalletRechargeInfoMgMapper.getAllWalletRechargeInfoCount(rechargeInfo);
		return new Pager<FinWalletRechargeInfoVo>(num, list);
	}
	
	/**
	 * 
	* Title: addRechargeGrade 
	* Description:增加充值梯度 
	* @param rechargeGrade
	* @return 
	* @see com.corner.kefu.service.sp.SpFinWalletService#addRechargeGrade(com.corner.core.beans.RechargeGrade)
	 */
	@Override
	public void addRechargeGrade(RechargeGrade rechargeGrade) {
		spFinWalletMgMapper.addRechargeGrade(rechargeGrade);
	}


	/**
	 * 
	* Title: addSupplierWallet 
	* Description:创造批发商钱包 
	* @param supplier 
	* @see com.corner.kefu.service.sp.SpFinWalletService#addSupplierWallet(com.corner.core.beans.Supplier)
	 */
	@Override
	public FinWallet addSupplierWallet(Supplier supplier) {
		FinWallet finWallet = new FinWallet();
		finWallet.setId(StringUtil.newUUID());
		Date date = new Date();
		finWallet.setAddTime(date);
		finWallet.setLastOpTime(date);
		finWalletMapper.insertSelective(finWallet);
		return finWallet;
	}

    /**
     * 
    * Title: deleteRechargeGrade 
    * Description: 删除充值送劵梯度
    * @param rechargeGrade 
    * @see com.corner.kefu.service.sp.SpFinWalletService#deleteRechargeGrade(com.corner.core.beans.RechargeGrade)
     */
	@Override
	public void deleteRechargeGrade(RechargeGrade rechargeGrade) {
		rechargeGradeMapper.deleteByPrimaryKey(rechargeGrade.getId());
	}


	/**
	 * 
	* Title: getWalletLog 
	* Description:获取钱包交易明细 
	* @param finWalletRo
	* @return 
	* @see com.corner.kefu.service.sp.SpFinWalletService#getWalletLog(com.corner.kefu.beans.ro.sp.FinWalletRo)
	 */
	@Override
	public Pager<FinWalletLogVo> getWalletLog(FinWalletMgRo finWalletMgRo) {
		Integer totalSize = 0;
		List<FinWalletLogVo> list = finWalletLogMgMapper.getWalletLog(finWalletMgRo);
		if(list!=null&&list.size()!=0){
			totalSize = finWalletLogMgMapper.getCountWalletLog(finWalletMgRo);
			//for (FinWalletLogVo finWalletLogVo : list) {
				//finWalletLogVo.setBusinessTypeStr(BusinessType.getName(finWalletLogVo.getBusinessType()));
				//finWalletLogVo.setPurposeStr(Purpose.getName(finWalletLogVo.getPurpose().intValue()));
			//}
		}
		
		return new Pager<FinWalletLogVo>(totalSize, list);
	}

}

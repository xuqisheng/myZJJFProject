/**   
* @Title: SpWalletMgServiceImpl.java 
* @Package com.corner.scms.service.sp.impl 
* @Description: 
* @author 杨开泰  yangkaitai@izjjf.cn   
* @date 2015年12月4日 上午11:58:00 
* @version V1.0   
*/
package com.corner.scms.service.sp.impl;

import com.corner.core.beans.FinWallet;
import com.corner.core.beans.FinWalletLog;
import com.corner.core.beans.SpWithDraw;
import com.corner.core.beans.vo.Pager;
import com.corner.core.dao.FinWalletMapper;
import com.corner.scms.beans.CheckoutAndLogParam;
import com.corner.scms.beans.ro.SpWalletLogRo;
import com.corner.scms.beans.ro.SpWithDrawRo;
import com.corner.scms.beans.vo.SpWalletLogVo;
import com.corner.scms.beans.vo.SpWithDrawVo;
import com.corner.scms.dao.FinWalletMgMapper;
import com.corner.scms.dao.ScmsSpWalletMgMapper;
import com.corner.scms.service.impl.BaseServiceImpl;
import com.corner.scms.service.sp.ScmsSpWalletMgService;
import com.corner.scms.utils.enums.BusinessType;
import com.corner.scms.utils.enums.Purpose;
import com.corner.scms.utils.enums.SystemCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/** 
 * @ClassName: SpWalletMgServiceImpl 
 * @Description: 供应商钱包业务实现类
 * @author 杨开泰  yangkaitai@izjjf.cn 
 * @date 2015年12月4日 上午11:58:00  
 */
@Service
public class ScmsSpWalletMgServiceImpl extends BaseServiceImpl implements ScmsSpWalletMgService {
	
	@Autowired  
	ScmsSpWalletMgMapper scmsSpWalletMgMapper;
	@Autowired
	FinWalletMgMapper finWalletMgMapper;

	@Override
	public Pager<SpWithDrawVo> selectSpWithDrawPageList(SpWithDrawRo spWithDrawRo) {
		List<SpWithDrawVo> list = scmsSpWalletMgMapper.selectSpWithDrawPageList(spWithDrawRo);
		int totalSize = scmsSpWalletMgMapper.selectCountSpWithDraw(spWithDrawRo);
		return new Pager<SpWithDrawVo>(totalSize, list);
	}

	@Override
	public Pager<SpWalletLogVo> selectSpWalletLogPageList(SpWalletLogRo spWalletLogRo) {
		List<SpWalletLogVo> list = scmsSpWalletMgMapper.selectSpWalletLogPageList(spWalletLogRo);
		int totalSize = scmsSpWalletMgMapper.selectCountSpWalletLog(spWalletLogRo);
		return new Pager<SpWalletLogVo>(totalSize, list);
	}
	@Override
	public SpWithDraw selectByPrimaryKey(Long withDrawId) {
		return scmsSpWalletMgMapper.selectByPrimaryKey(withDrawId);
	}

	@Override
	@Deprecated
	public Long alertSpWallet(Map<String, Object> objMap) {
		return scmsSpWalletMgMapper.alertSpWallet(objMap);
	}
	@Override
	public void checkoutAndLog(SystemCode systemCode , BusinessType businessType , String voucherMain , Purpose purpose , BigDecimal amount){
		CheckoutAndLogParam param = new CheckoutAndLogParam();
		param.setBusinessType(businessType);
		param.setPurpose(purpose);
		param.setSystemCode(systemCode);
		param.setVoucherMain(voucherMain);
		param.setAmount(amount);

		String result= scmsSpWalletMgMapper.checkoutAndLog(param);
		if(!"1".equals(result)){
			throw new RuntimeException(result);
		}
	}

	@Override
	public void deleteFinWalletLogBySub(String voucherMain) {
		finWalletMgMapper.updateByVoucherMain(voucherMain);
	}
}

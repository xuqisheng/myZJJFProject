/**   
* @Title: SpWalletMgService.java 
* @Package com.corner.scms.service.sp 
* @Description:
* @author 杨开泰  yangkaitai@izjjf.cn   
* @date 2015年12月4日 上午11:55:49 
* @version V1.0   
*/
package com.corner.scms.service.sp;

import java.math.BigDecimal;
import java.util.Map;

import com.corner.core.beans.SpWithDraw;
import com.corner.core.beans.vo.Pager;
import com.corner.scms.beans.ro.SpWalletLogRo;
import com.corner.scms.beans.ro.SpWithDrawRo;
import com.corner.scms.beans.vo.SpWalletLogVo;
import com.corner.scms.beans.vo.SpWithDrawVo;
import com.corner.scms.service.BaseService;
import com.corner.scms.utils.enums.BusinessType;
import com.corner.scms.utils.enums.Purpose;
import com.corner.scms.utils.enums.SystemCode;

import static com.corner.core.pay.alipay.config.AlipayConfigForWAP.v;

/** 
 * @ClassName: SpWalletMgService 
 * @Description: 供应商钱包业务层
 * @author 杨开泰  yangkaitai@izjjf.cn 
 * @date 2015年12月4日 上午11:55:49  
 */
public interface ScmsSpWalletMgService extends BaseService {

	Pager<SpWithDrawVo> selectSpWithDrawPageList(SpWithDrawRo spWithDrawRo);

	Pager<SpWalletLogVo> selectSpWalletLogPageList(SpWalletLogRo spWalletLogRo);

	SpWithDraw selectByPrimaryKey(Long withDrawId);

	@Deprecated
	Long alertSpWallet(Map<String, Object> objMap);

	void checkoutAndLog(SystemCode systemCode , BusinessType businessType , String voucherMain , Purpose purpose , BigDecimal amount);

	void deleteFinWalletLogBySub(String voucherMain);
}

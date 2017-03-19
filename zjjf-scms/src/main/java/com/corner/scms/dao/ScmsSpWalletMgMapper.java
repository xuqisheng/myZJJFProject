package com.corner.scms.dao;

import java.util.List;
import java.util.Map;

import com.corner.core.beans.SpWithDraw;
import com.corner.scms.beans.CheckoutAndLogParam;
import com.corner.scms.beans.ro.SpWalletLogRo;
import com.corner.scms.beans.ro.SpWithDrawRo;
import com.corner.scms.beans.vo.SpWalletLogVo;
import com.corner.scms.beans.vo.SpWithDrawVo;


public interface ScmsSpWalletMgMapper {

	List<SpWithDrawVo> selectSpWithDrawPageList(SpWithDrawRo spWithDrawRo);

	int selectCountSpWithDraw(SpWithDrawRo spWithDrawRo);

	List<SpWalletLogVo> selectSpWalletLogPageList(SpWalletLogRo spWalletLogRo);

	int selectCountSpWalletLog(SpWalletLogRo spWalletLogRo);

	SpWithDraw selectByPrimaryKey(Long withDrawId);

	@Deprecated
	Long alertSpWallet(Map<String, Object> objMap);

	String checkoutAndLog(CheckoutAndLogParam param);
}

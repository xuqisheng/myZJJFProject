package com.corner.kefu.dao.sp;

import java.util.List;

import com.corner.kefu.beans.ro.sp.FinWalletRechargeInfoRo;
import com.corner.kefu.beans.vo.sp.FinWalletRechargeInfoVo;

public interface FinWalletRechargeInfoMgMapper {

	public List<FinWalletRechargeInfoVo> getAllWalletRechargeInfo(FinWalletRechargeInfoRo rechargeInfo);

	public int getAllWalletRechargeInfoCount(FinWalletRechargeInfoRo rechargeInfo);

}

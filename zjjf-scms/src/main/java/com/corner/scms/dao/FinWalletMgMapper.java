package com.corner.scms.dao;

import com.corner.scms.beans.ro.FinWalletLogRo;
import com.corner.scms.beans.vo.FinWalletLogVo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface FinWalletMgMapper {
	List<FinWalletLogVo> selectFinWalletLogPageList(FinWalletLogRo finWalletLogRo);
	int selectCountFinWalletLog(FinWalletLogRo finWalletLogRo);

	BigDecimal selectAllIncomeAndOutlay(Map map);

	Integer updateByVoucherMain(String voucherMain);
}

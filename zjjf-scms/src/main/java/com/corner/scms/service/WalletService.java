package com.corner.scms.service;

import com.corner.core.beans.FinWallet;
import com.corner.core.beans.vo.Pager;
import com.corner.scms.beans.ro.FinWalletLogRo;
import com.corner.scms.beans.vo.FinWalletLogVo;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Created by 孟星魂 on 2016/7/28.
 */
public interface WalletService {
    FinWallet selectOne(String userId);

    Pager<FinWalletLogVo> selectFinWalletLogPageList(FinWalletLogRo spWalletLogRo);

    Long doWithDraw(String walletId , BigDecimal bigDecimal , String spId);

    Map<String , Object> selectAllIncomeAndOutlay(String walletId);
}

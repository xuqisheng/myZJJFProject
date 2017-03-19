package com.corner.kefu.dao.sp;

import com.corner.core.beans.SpVoucherActive;
import com.corner.core.beans.SpVoucherActiveStoreLog;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: SpVoucherActiveStoreLogMgMapper
 * @Description:
 * @author: 杨开泰
 * @date: 2016年 10月25 15:02
 */
public interface SpVoucherActiveStoreLogMgMapper {
    List<SpVoucherActiveStoreLog> getAccLog(SpVoucherActive active);

    void batchSave(List<SpVoucherActiveStoreLog> logList);

    BigDecimal getTotalSend(Map<String, Object> paramMap);
}

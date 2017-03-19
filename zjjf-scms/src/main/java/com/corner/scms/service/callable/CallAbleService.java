package com.corner.scms.service.callable;

import com.corner.core.enums.OrderPrefix;
import com.corner.core.enums.SocktOperateType;

/**
 * Created by mxh on 2016/8/24.
 */
public interface CallAbleService {
    void socktOperateLog(SocktOperateType socktOperateType , String voucherMain) throws Exception;

    String checkItemIsHave(String warehoseId , Integer itemId , Integer typeMg);

    String getOrderId(String tableName) throws Exception;

    public String getStockOrderId(OrderPrefix orderPrefix) throws Exception;
}

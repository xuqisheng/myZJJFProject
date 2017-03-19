package com.corner.kefu.service.callable.impl;

import com.corner.core.enums.OrderPrefix;
import com.corner.core.enums.SocktOperateType;
import com.corner.core.utils.StringUtil;
import com.corner.core.utils.callable.CheckItemIsHave;
import com.corner.core.utils.callable.SocktOperateLog;
import com.corner.kefu.dao.CallableMgMapper;
import com.corner.kefu.service.callable.CallAbleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by mxh on 2016/8/24.
 */
@Service
public class CallAbleServiceImpl implements CallAbleService{
    @Autowired
    CallableMgMapper callableMgMapper;
    @Override
    public void socktOperateLog(SocktOperateType socktOperateType, String voucherMain) throws Exception{
        SocktOperateLog socktOperateLog = new SocktOperateLog();
        socktOperateLog.setBusinessType(socktOperateType.getIndex());
        socktOperateLog.setVoucherMain(voucherMain);
        String result = callableMgMapper.socktOperateLog(socktOperateLog);
        if (!"1".equals(result))
            throw new Exception(result);
    }

    @Override
    public String checkItemIsHave(String warehoseId, Integer itemId, Integer typeMg) {
    	CheckItemIsHave have = new CheckItemIsHave(warehoseId,itemId,typeMg);
    	return callableMgMapper.checkItemIsHave(have);
    }

    @Override
    public String getOrderId(String tableName) throws Exception{
        if(StringUtil.stringIsNullOrEmpty(tableName))
            throw new Exception("缺少正确的参数");
        String orderId = callableMgMapper.getDateTypeUUID(tableName);
        return orderId;
    }
    @Override
    public String getStockOrderId(OrderPrefix orderPrefix) throws Exception{
        if(orderPrefix == null)
            throw new Exception("缺少正确的参数");
        String orderId = callableMgMapper.getDateTypeUUID(orderPrefix.getTableName());
        orderId = orderPrefix.getPrefix() + orderId;    //加前缀如果未匹配上的什么都不加
        return orderId;
    }
}

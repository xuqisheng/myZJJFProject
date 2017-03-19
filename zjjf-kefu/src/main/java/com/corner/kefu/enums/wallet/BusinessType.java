package com.corner.kefu.enums.wallet;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * 业务类型（表示这笔流水在业务系统码对应的系统中的业务类型，比如店宝中的订单业务，
 * 快销宝中的批发商提现业务，财务中的实收实付业务。
 * 1001-订单业务，1002-充值业务，1003-提现业务，1004-实收业务，1005-实付业务）
 */
public enum BusinessType {
    ORDER("订单", 1001),
    RECHARGE("充值", 1002),
    WITHDRAW("提现", 1003),
    RECEIVED("实收", 1004),
    PAID("实付", 1005);
    private String name;
    private Integer index;
    BusinessType(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public static String getName(int index) {
        for (BusinessType ds : BusinessType.values()) {
            if (ds.getIndex() == index) {
                return ds.name;
            }
        }
        return null;
    }
    private final static Map<Integer, BusinessType> map = new HashMap();
    public static Map<Integer, BusinessType> map(){
        if(map.size()==0) {
            map.put(ORDER.getIndex(), ORDER);
            map.put(RECHARGE.getIndex(), RECHARGE);
            map.put(WITHDRAW.getIndex(), WITHDRAW);
            map.put(RECEIVED.getIndex(), RECEIVED);
            map.put(PAID.getIndex(), PAID);
        }
        return map;
    }
    public final static EnumSet<BusinessType> set = EnumSet.allOf(BusinessType.class);
    public String getName() {
        return name;
    }

    public Integer getIndex() {
        return index;
    }
}

package com.corner.scms.utils.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * 业务系统码（表示触发这笔流水的业务系统的代码，
 * 1001-店宝，1002-快销宝，1003-财务系统，大于等于1004-其它业务系统）
 */
public enum SystemCode {
    STORE("店宝", 1001),SUPPLIER("批发商", 1002),FINANCIAL("财务", 1003);
    private String name;
    private Integer index;
    SystemCode(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public static String getName(int index) {
        for (SystemCode ds : SystemCode.values()) {
            if (ds.getIndex() == index) {
                return ds.name;
            }
        }
        return null;
    }
    private final static Map<Integer, SystemCode> map = new HashMap();
    public static Map<Integer, SystemCode> map(){
        if(map.size()==0) {
            map.put(STORE.getIndex(), STORE);
            map.put(SUPPLIER.getIndex(), SUPPLIER);
            map.put(FINANCIAL.getIndex(), FINANCIAL);
        }
        return map;
    }
    public final static EnumSet<SystemCode> set = EnumSet.allOf(SystemCode.class);

    public String getName() {
        return name;
    }

    public Integer getIndex() {
        return index;
    }

}

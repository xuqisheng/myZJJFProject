package com.corner.scms.utils.enums;

import org.apache.commons.collections.map.HashedMap;

import java.util.Map;

/**
 * 业务系统码
 */
public enum WithDrawStatus {
    //（0预留、1：申请中、2：审核通过、3审核不通过、4生成结算单（打款中）、5已打款、6已退款）
    DEFALUE("预留", 0),
    START("申请发起", 1),
    AUDITING("审核中", 2),
    AUDITYES("审核通过", 3),
    AUDITNO("审核不通过", 4),
    PAYYES("已打款", 5),
    PAYNO("已退款", 6);
//    DEFALUE("预留", 0),
//    AUDITING("申请中", 1),
//    AUDITYES("审核通过", 2),
//    AUDITNO("审核不通过", 3),
//    PAYING("生成结算单（打款中）", 4),
//    PAYYES("已打款", 5),
//    PAYNO("已退款", 6);
    private String name;
    private final Byte index;
    WithDrawStatus(String name, int index) {
        this.name = name;
        this.index = (byte)index;
    }

    public static String getName(int index) {
        for (WithDrawStatus ds : WithDrawStatus.values()) {
            if (ds.getIndex() == index) {
                return ds.name;
            }
        }
        return null;
    }
    private final static Map<Byte , WithDrawStatus> map = new HashedMap();
    public static Map<Byte , WithDrawStatus> map(){
        if(map.size()==0) {
            map.put(DEFALUE.getIndex(), DEFALUE);
            map.put(START.getIndex(), START);
            map.put(AUDITING.getIndex(), AUDITING);
            map.put(AUDITYES.getIndex(), AUDITYES);
            map.put(AUDITNO.getIndex(), AUDITNO);
            map.put(PAYYES.getIndex(), PAYYES);
            map.put(PAYNO.getIndex(), PAYNO);
        }
        return map;
    }
    public String getName() {
        return name;
    }

    public Byte getIndex() {
        return index;
    }
}

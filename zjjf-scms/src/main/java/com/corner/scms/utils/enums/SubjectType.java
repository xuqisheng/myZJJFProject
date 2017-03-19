package com.corner.scms.utils.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * 费用科目（即费用类型）（1001-订单，1002-手续费，1003-保证金，1004--罚款，1005-平台服务费，
 * 2001-货款，2002-运费，2003-推广费，2004-返现，2005-满减，2006-优惠券，3001-充值，
 * 3002-提现） - - 以1开头的表示流入平台钱包的科目类型，
 * 以2开头的表示流出平台钱包的科目类型，以3开头的表示导致整个金服资金变动的科目类型
 */
public enum SubjectType {
    ORDER(1001,"订单"),
    FACTORAGE(1002,"手续费"),
    DEPOSIT(1003,"保证金"),
    PENALTY(1004,"罚款"),
    SERVICE_FEE(1005,"平台服务费"),
    GOODS(2001,"货款"),
    FREIGHT(2002,"运费"),
    PROMOTION_FEE(2003,"推广费"),
    RETURN_MONEY(2004,"返现"),
    DISCOUNT(2005,"满减"),
    COUPON(2006,"优惠券"),
    RECHARGE(3001,"充值"),
    WITHDRAW(3002,"提现"),
    RECEIVABLE(3003,"应收"),
    RECEIVED(3004,"实收"),
    PAYABLE(3005,"应付"),
    PAID(3006,"实付");

    private String name;
    private Integer index;
    SubjectType(int index,String name) {
        this.name = name;
        this.index = index;
    }

    public static String getName(int index) {
        for (SubjectType ds : SubjectType.values()) {
            if (ds.getIndex() == index) {
                return ds.name;
            }
        }
        return null;
    }
    private final static Map<Integer, SubjectType> map = new HashMap();
    public static Map<Integer, SubjectType> map(){
        if(map.size()==0) {
            map.put(ORDER.getIndex(), ORDER);
            map.put(GOODS.getIndex(), GOODS);
            map.put(FACTORAGE.getIndex(), FACTORAGE);
            map.put(COUPON.getIndex(), COUPON);
            map.put(FREIGHT.getIndex(), FREIGHT);
            map.put(DEPOSIT.getIndex(), DEPOSIT);
            map.put(PENALTY.getIndex(), PENALTY);
            map.put(SERVICE_FEE.getIndex(), SERVICE_FEE);
            map.put(PROMOTION_FEE.getIndex(), PROMOTION_FEE);
            map.put(RETURN_MONEY.getIndex(), RETURN_MONEY);
            map.put(WITHDRAW.getIndex(), WITHDRAW);
            map.put(RECHARGE.getIndex(), RECHARGE);
            map.put(DISCOUNT.getIndex(), DISCOUNT);

            map.put(RECEIVABLE.getIndex(), RECEIVABLE);
            map.put(RECEIVED.getIndex(), RECEIVED);
            map.put(PAYABLE.getIndex(), PAYABLE);
            map.put(PAID.getIndex(), PAID);
        }
        return map;
    }
    public final static EnumSet<SubjectType> set = EnumSet.allOf(SubjectType.class);
    public String getName() {
        return name;
    }

    public Integer getIndex() {
        return index;
    }
}

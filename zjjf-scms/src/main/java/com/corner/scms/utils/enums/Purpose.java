package com.corner.scms.utils.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * 用途（0-订单，1-货款分账，2-手续费，3-优惠券，4-运费，5-保证金，6-罚款，7-平台服务费，8-推广费，9-返现，10-提现，11-充值,12-满减），
 * 例如：下单支付￥500，要先充值￥500到小店钱包，这￥500的用途就是用于“支付订单”,在用户的钱包明细中只显示订单支出，不会显示这种用途的充值收入
 */
public enum Purpose {
    ORDER(0,"订单"),
    GOODS(1,"货款分账"),
    FACTORAGE(2,"手续费"),
    COUPON(3,"优惠券"),
    FREIGHT(4,"运费"),
    DEPOSIT(5,"保证金"),
    PENALTY(6,"罚款"),
    SERVICE_FEE(7,"平台服务费"),
    PROMOTION_FEE(8,"推广费"),
    RETURN_MONEY(9,"返现"),
    WITHDRAW(10,"提现"),
    RECHARGE(11,"充值"),
    DISCOUNT(12,"满减");
    private String name;
    private Byte index;
    Purpose(int index, String name) {
        this.name = name;
        this.index = (byte)index;
    }

    public static String getName(int index) {
        for (Purpose ds : Purpose.values()) {
            if (ds.getIndex() == index) {
                return ds.name;
            }
        }
        return null;
    }
    private final static Map<Byte, Purpose> map = new HashMap();
    public static Map<Byte, Purpose> map(){
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
        }
        return map;
    }
    public final static EnumSet<Purpose> set = EnumSet.allOf(Purpose.class);
    public String getName() {
        return name;
    }

    public Byte getIndex() {
        return index;
    }
}

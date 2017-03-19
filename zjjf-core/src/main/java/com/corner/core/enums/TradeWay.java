package com.corner.core.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * 支付方式1、快钱支付2、货到付款 3、支付宝支付 4、微信支付 5、转角钱包支付，大于5-其它支付方式
 */
public enum TradeWay {
    KUAIQIAN("快钱支付", 1),
    CASH_ON_DELIVERY("货到付款", 2),
    ALIPAY("支付宝支付",3),
    WEIXIN("微信支付",4),
    ZJWALLET("转角钱包",5);
    private String name;
    private Integer index;
    TradeWay(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public static String getName(Integer index) {
        for (TradeWay ds : TradeWay.values()) {
            if (ds.getIndex() == index) {
                return ds.name;
            }
        }
        return null;
    }
    private final static Map<Integer, TradeWay> map = new HashMap();
    public static Map<Integer, TradeWay> map(){
        if(map.size()==0) {
            map.put(KUAIQIAN.getIndex(), KUAIQIAN);
            map.put(CASH_ON_DELIVERY.getIndex(), CASH_ON_DELIVERY);
            map.put(ALIPAY.getIndex(), ALIPAY);
            map.put(WEIXIN.getIndex(), WEIXIN);
            map.put(ZJWALLET.getIndex(), ZJWALLET);
        }
        return map;
    }
    public final static EnumSet<TradeWay> set = EnumSet.allOf(TradeWay.class);
    public String getName() {
        return name;
    }

    public Integer getIndex() {
        return index;
    }
}

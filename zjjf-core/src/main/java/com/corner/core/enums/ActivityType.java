package com.corner.core.enums;

/**
 * Created by MXH on 2016/10/25.
 * 活动类型
 */
public enum ActivityType {
    LOGIN_COUPONS("注册送优惠劵", 1),
    MEET_COUPONS("满送优惠劵" , 2),
    MEET_SUBTRACT("满减" , 3),
    MEET_GOODS_COUPONS("满送优惠劵+商品" , 9),
    MEET_SUBTRACT_GOODS("满减金额+商品" , 10),
    MEET_GOODS("满送商品" , 11),
    MEETGOODS_GOODS("满购商品送商品" , 12),
    AFTER_ORDER_COUPONS("提前下单送优惠券" , 13),
    TOTAL_MEET_COUPONS("累计送券" , 14);
    private String name;
    private Byte index;

    ActivityType(String name, int index) {
        this.name = name;
        this.index = (byte) index;
    }

    public static String getName(Byte index) {
        for (ActivityType activityType : ActivityType.values()) {
            if (activityType.getIndex().equals(index)) {
                return activityType.name;
            }
        }
        return "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getIndex() {
        return index;
    }

    public void setIndex(Byte index) {
        this.index = index;
    }
}

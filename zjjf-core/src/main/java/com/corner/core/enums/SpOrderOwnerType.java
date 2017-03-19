package com.corner.core.enums;

/**
 * Created by mxh on 2016/9/4.
 */
public enum SpOrderOwnerType {

    ITEM("兑换商品", 1),
    MONEY("扣减金额" , 2);
    private String name;
    private Byte index;

    SpOrderOwnerType(String name, int index) {
        this.name = name;
        this.index = (byte) index;
    }

    public static String getName(Byte index) {
        for (SpOrderOwnerType spOrderOwnerType : SpOrderOwnerType.values()) {
            if (spOrderOwnerType.getIndex().intValue() == index.intValue()) {
                return spOrderOwnerType.name;
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

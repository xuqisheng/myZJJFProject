package com.corner.core.enums;

/**
 * Created by mxh on 2016/9/4.
 */
public enum SpOrderOwnerCash {
    P_G("瓶盖", 1),
    P_S("瓶身" , 2),
    Z_X("纸箱" , 3);
    private String name;
    private Byte index;

    SpOrderOwnerCash(String name, int index) {
        this.name = name;
        this.index = (byte) index;
    }

    public static String getName(Byte index) {
        for (SpOrderOwnerCash spOrderOwnerCash : SpOrderOwnerCash.values()) {
            if (spOrderOwnerCash.getIndex().intValue() == index.intValue()) {
                return spOrderOwnerCash.name;
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

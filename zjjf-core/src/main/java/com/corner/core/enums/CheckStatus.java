package com.corner.core.enums;

/**
 * Created by mxh on 2016/9/4.
 */
public enum CheckStatus {
    NO("未审核", 1),
    YES("已审核" , 2);
    private String name;
    private Byte index;

    CheckStatus(String name, int index) {
        this.name = name;
        this.index = (byte) index;
    }

    public static String getName(Byte index) {
        for (CheckStatus checkStatus : CheckStatus.values()) {
            if (checkStatus.getIndex().intValue() == index.intValue()) {
                return checkStatus.name;
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

package com.corner.core.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * 损益单
 */
public enum ProfitType {
    OUT("报损单", 1),
    IN("报溢单" , 2);
    private String name;
    private Byte index;

    ProfitType(String name, int index) {
        this.name = name;
        this.index = (byte) index;
    }

    public static String getName(Byte index) {
        for (ProfitType profitType : ProfitType.values()) {
            if (profitType.getIndex().intValue() == index.intValue()) {
                return profitType.name;
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

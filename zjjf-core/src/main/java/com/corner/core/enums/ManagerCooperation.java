package com.corner.core.enums;

/**
 * @ClassName: ManagerCooperation
 * @Description:
 * @author 孟星魂  mengxinghun@izjjf.cn
 * @date 2016年8月4日 下午1:51:08
 *
 */

public enum ManagerCooperation {
    ONE("购销", 0), BATCHYES("平台入驻",1);
    private String name;
    private Byte index;

    ManagerCooperation(String name, int index) {
        this.name = name;
        this.index = (byte) index;
    }

    public static String getName(Byte index) {
        for (ManagerCooperation ManagerCooperation : com.corner.core.enums.ManagerCooperation.values()) {
            if (ManagerCooperation.getIndex().intValue() == index.intValue()) {
                return ManagerCooperation.name;
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

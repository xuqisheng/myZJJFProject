/**
 * @Title: PayMethod.java
 * @Package com.corner.core.enums
 * @Description:
 * @author 杨开泰 yangkaitai@izjjf.cn
 * @date 2016年8月4日 下午1:51:08
 * @version V1.0
 */

package com.corner.core.enums;

import java.util.EnumMap;
import java.util.EnumSet;

/**
 * @ClassName: ManagerStatus
 * @Description:
 * @author 孟星魂  mengxinghun@izjjf.cn
 * @date 2016年8月4日 下午1:51:08
 *
 */

public enum ManagerStatus {
    IMPORTING("引进中",0),
    JOINING("合作中", 1),
    JOINEND("合作结束" , 2);
    private String name;
    private Byte index;

    @SuppressWarnings("unused")
    private final static EnumSet<ManagerStatus> enumSet = EnumSet.allOf(ManagerStatus.class);

    private final static EnumMap<ManagerStatus, String> enumMap = new EnumMap<ManagerStatus, String>(ManagerStatus.class);

    static {
        if(enumMap.size()==0){
            enumMap.put(ManagerStatus.IMPORTING, "引进中");
            enumMap.put(ManagerStatus.JOINING, "合作中");
            enumMap.put(ManagerStatus.JOINEND, "合作结束");
        }
    }

    ManagerStatus(String name, int index) {
        this.name = name;
        this.index = (byte) index;
    }

    public static String getName(Byte index) {
        for (ManagerStatus managerStatus : ManagerStatus.values()) {
            if (managerStatus.getIndex().intValue() == index.intValue()) {
                return managerStatus.name;
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

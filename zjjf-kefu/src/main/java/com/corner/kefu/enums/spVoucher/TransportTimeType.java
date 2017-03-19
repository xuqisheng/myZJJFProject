package com.corner.kefu.enums.spVoucher;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: TransportTimeType
 * @Description: 优惠劵送达时间枚举
 * @author: 杨开泰
 * @date: 2016年 10月03 16:48
 */
public enum TransportTimeType {
    NOLIMIT("不限制",-1),
    TWENTY_FOUR("24小时",0),
    FORTY_EIGHT("48小时",1),
    SEVENTY_TWO("72小时",2);

    private String name;
    private Byte index;

    TransportTimeType(String name, int index) {
        this.name = name;
        this.index = (byte)index;
    }

    public static  final EnumSet<TransportTimeType> enumSet = EnumSet.allOf(TransportTimeType.class);

    public static  final Map<Byte,TransportTimeType> map = new HashMap<>();

    static {
        if(map.size()==0){
            map.put(NOLIMIT.getIndex(), NOLIMIT);
            map.put(TWENTY_FOUR.getIndex(), TWENTY_FOUR);
            map.put(FORTY_EIGHT.getIndex(), FORTY_EIGHT);
            map.put(SEVENTY_TWO.getIndex(), SEVENTY_TWO);
        }
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

    public static Map<Byte, TransportTimeType> getMap() {
        return map;
    }
}

package com.corner.scms.utils.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * 收款或付款方类型
 * (10-平台; 20-微信 ,21-支付宝,22快钱;30-用户,31-小店,32-批发商,33-经销商,34-仓库,35-物流,36-银行)
 */
public enum SideType {
    PLATFORM(10,"平台"),
    WEIXIN(20, "微信"),
    ALIPAY(21,"支付宝"),
    KUAIQIAN(22, "快钱"),
    USER(30, "用户"),
    STORE(31, "小店"),
    SUPPLIER(32, "批发商"),
    RESELLER(33, "经销商"),
    WAREHOUSE(34, "仓库"),
    LOGISTICS(35, "物流"),
    BANK(36,"银行");
    private String name;
    private Byte index;
    SideType(int index, String name) {
        this.index = (byte)index;
        this.name = name;
    }

    public static String getName(int index) {
        for (SideType ds : SideType.values()) {
            if (ds.getIndex() == index) {
                return ds.name;
            }
        }
        return null;
    }
    private final static Map<Byte, SideType> map = new HashMap();
    public static Map<Byte, SideType> map(){
        if(map.size()==0) {
            map.put(PLATFORM.getIndex(), PLATFORM);
            map.put(WEIXIN.getIndex(), WEIXIN);
            map.put(ALIPAY.getIndex(), ALIPAY);
            map.put(KUAIQIAN.getIndex(), KUAIQIAN);
            map.put(USER.getIndex(), USER);
            map.put(STORE.getIndex(), STORE);
            map.put(SUPPLIER.getIndex(), SUPPLIER);
            map.put(RESELLER.getIndex(), RESELLER);
            map.put(WAREHOUSE.getIndex(), WAREHOUSE);
            map.put(LOGISTICS.getIndex(), LOGISTICS);
        }
        return map;
    }
    public final static EnumSet<SideType> set = EnumSet.allOf(SideType.class);
    public String getName() {
        return name;
    }

    public Byte getIndex() {
        return index;
    }
}

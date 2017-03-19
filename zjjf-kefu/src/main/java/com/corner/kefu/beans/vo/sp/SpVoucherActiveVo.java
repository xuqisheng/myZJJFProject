/**
 * @Title: SpVoucherActiveVo.java
 * @Package com.corner.kefu.beans.vo.sp
 * @Description:
 * @author 杨开泰 yangkaitai@izjjf.cn
 * @date 2016年4月5日 下午6:05:44
 * @version V1.0
 */

package com.corner.kefu.beans.vo.sp;

import com.corner.core.beans.SpVoucherActive;
import com.corner.core.beans.SpVoucherTemp;
import com.corner.kefu.enums.spVoucher.VoucherActiveType;

/**
 * @ClassName: SpVoucherActiveVo
 * @Description:活动视图类
 * @author 杨开泰  yangkaitai@izjjf.cn
 * @date 2016年4月5日 下午6:05:44
 *
 */

public class SpVoucherActiveVo extends SpVoucherActive {

    private String ruleTypeStr;// 类型字符串

    private String statusStr;// 是否启用字符串

    private SpVoucherTemp spVoucherTemp;// 活动赠送的优惠劵

    private String plantHalveStr;// 平台承担比例
    private String spHalveStr;// 批发商承担比例

    private String ruleStartPriceStr;// 单笔订单金额字符串

    private Integer totalSupplier;// 批发商参与活动数

    private String ruleStartStr;// 活动开始时间字符串

    private String ruleEndStr;// 活动结束时间字符串

    private String useItemFlagStr;//

    public String getUseItemFlagStr() {
        if (this.getUseItemFlag().intValue() == 0) {
            return "不限制";
        }
        if (this.getUseItemFlag().intValue() == 1) {
            return "指定商品";
        }
        if (this.getUseItemFlag().intValue() == 2) {
            return "排除商品";
        }
        return useItemFlagStr;
    }

    public void setUseItemFlagStr(String useItemFlagStr) {
        this.useItemFlagStr = useItemFlagStr;
    }

    public String getRuleTypeStr() {
        return VoucherActiveType.map.get(getRuleType()).getName();
    }

    public void setRuleTypeStr(String ruleTypeStr) {
        this.ruleTypeStr = ruleTypeStr;
    }

    public String getStatusStr() {
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    public SpVoucherTemp getSpVoucherTemp() {
        return spVoucherTemp;
    }

    public void setSpVoucherTemp(SpVoucherTemp spVoucherTemp) {
        this.spVoucherTemp = spVoucherTemp;
    }

    public String getPlantHalveStr() {
        return plantHalveStr;
    }

    public void setPlantHalveStr(String plantHalveStr) {
        this.plantHalveStr = plantHalveStr;
    }

    public String getSpHalveStr() {
        return spHalveStr;
    }

    public void setSpHalveStr(String spHalveStr) {
        this.spHalveStr = spHalveStr;
    }

    public String getRuleStartPriceStr() {
        return ruleStartPriceStr;
    }

    public void setRuleStartPriceStr(String ruleStartPriceStr) {
        this.ruleStartPriceStr = ruleStartPriceStr;
    }

    public Integer getTotalSupplier() {
        return totalSupplier;
    }

    public void setTotalSupplier(Integer totalSupplier) {
        this.totalSupplier = totalSupplier;
    }

    public String getRuleStartStr() {
        return ruleStartStr;
    }

    public void setRuleStartStr(String ruleStartStr) {
        this.ruleStartStr = ruleStartStr;
    }

    public String getRuleEndStr() {
        return ruleEndStr;
    }

    public void setRuleEndStr(String ruleEndStr) {
        this.ruleEndStr = ruleEndStr;
    }
}

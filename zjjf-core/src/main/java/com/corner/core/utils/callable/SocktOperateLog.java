package com.corner.core.utils.callable;

/**
 * 库存操作存储过程、
 *
 * 调用实体
 */
public class SocktOperateLog {
    /**库存操作业务类型（表示在合种业务触发对应库存操作
        1001-订单业务:10011-下单，10012-提单，10013-打单，10014-派单，10015-派单中，10016-送达 ，10018-取消订单；
        1008-采购业务:10086-采购入库 ；
        2001-初始化业务:20011-仓库初始化业务）
     **/
    private Integer businessType;
    /**
     *#业务凭证（1001-订单业务：放入订单ID；1008-采购业务：放入库单orderId）
     */
    private String voucherMain;
    /**
     * 返回结果 如果是1 表示成功   其他表示失败
     */
    private String result;

    public Integer getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }

    public String getVoucherMain() {
        return voucherMain;
    }

    public void setVoucherMain(String voucherMain) {
        this.voucherMain = voucherMain;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}

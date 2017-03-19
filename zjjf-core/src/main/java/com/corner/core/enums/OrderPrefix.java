package com.corner.core.enums;


/**
 * 获取业务单前缀
 */
public enum OrderPrefix {
    //采购订单
    ManagerOrderInfo("ERPManagerOrderInfo" , "PO"),
    //销售出库单
    MarketStockOut("ERPMarketStockInfoOut" , "SR"),
    //销售退货单
    MarketStockIN("ERPMarketStockInfoIn" , "SD"),
    //采购入库单
    PurchaseStockIn("ERPPurchaseStockInfoIn" , "PA"),
    //采购退货单
    PurchaseStockOut("ERPPurchaseStockInfoOut" , "PR"),
    //损益单-报益
    ProfitIn("ERPProfitInfoIn" , "BY"),
    //损益单-报损
    ProfitOut("ERPProfitInfoOut" , "BS");
    private String prefix;
    private String tableName;
    OrderPrefix(String tableName , String prefix ){
        this.prefix = prefix;
        this.tableName = tableName;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public static String getTableName(String tableName) {
        for (OrderPrefix orderPrefix : OrderPrefix.values()) {
            if (orderPrefix.tableName.equals(tableName)) {
                return orderPrefix.prefix;
            }
        }
        return "";
    }
}

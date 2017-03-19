package com.corner.scms.beans;

import com.corner.scms.utils.enums.BusinessType;
import com.corner.scms.utils.enums.Purpose;
import com.corner.scms.utils.enums.SystemCode;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created by Huaml on 2016/7/11.
 * 结账并记录流水的参数对象
 */
public class CheckoutAndLogParam {

    /**
     * 业务系统码
     */
    @NotNull(message = "{systemCodeNotNull}")
    private Integer systemCode;

    /**
     * 业务代码
     */
    @NotNull(message = "{businessTypeNotNull}")
    private Integer businessType;

    /**
     * 主业务凭证号
     */
    @NotNull(message = "{voucherMainNotNull}")
    private String voucherMain;

    private Byte purpose;

    private BigDecimal amount;

    private String result;

    public Integer getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(SystemCode systemCode) {
        this.systemCode = systemCode.getIndex();
    }

    public Integer getBusinessType() {
        return businessType;
    }

    public void setBusinessType(BusinessType businessType) {
        this.businessType = businessType.getIndex();
    }

    public String getVoucherMain() {
        return voucherMain;
    }

    public void setVoucherMain(String voucherMain) {
        this.voucherMain = voucherMain;
    }

    public Byte getPurpose() {
        return purpose;
    }

    public void setPurpose(Purpose purpose) {
        this.purpose = purpose.getIndex();
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}

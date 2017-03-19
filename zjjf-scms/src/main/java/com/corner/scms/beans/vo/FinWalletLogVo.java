/**   
* @Title: SpWalletLogVo.java 
* @Package com.corner.scms.beans.vo 
* @Description: TODO(用一句话描述该文件做什么) 
* @author 杨开泰  yangkaitai@izjjf.cn   
* @date 2015年12月7日 上午10:13:45 
* @version V1.0   
*/
package com.corner.scms.beans.vo;

import com.corner.core.beans.FinWalletLog;
import com.corner.core.enums.TradeWay;

import java.math.BigDecimal;

/** 
 * @ClassName: SpWalletLogVo 
 * @Description: 交易明细视图类
 * @author 杨开泰  yangkaitai@izjjf.cn 
 * @date 2015年12月7日 上午10:13:45  
 */
public class FinWalletLogVo extends FinWalletLog {
    private String shouru;
    private String zhichu;
    private BigDecimal balance;
    private String subjectTypeStr;

    public String getTradeWayStr(){
        return TradeWay.getName(getTradeWay());
    }

    public String getShouru() {
        return shouru;
    }

    public void setShouru(String shouru) {
        this.shouru = shouru;
    }

    public String getZhichu() {
        return zhichu;
    }

    public void setZhichu(String zhichu) {
        this.zhichu = zhichu;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getSubjectTypeStr() {
        return subjectTypeStr;
    }

    public void setSubjectTypeStr(String subjectTypeStr) {
        this.subjectTypeStr = subjectTypeStr;
    }
}

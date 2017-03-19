package com.corner.account.service;

import com.corner.account.beans.ro.BillPayCondition;
import com.corner.account.beans.vo.BillPayVo;
import com.corner.core.beans.vo.Pager;

public interface BillPayService {

	Pager<BillPayVo> getBillPayOverViewList(BillPayCondition command);

	Pager<BillPayVo> getBillPayStatusList(BillPayCondition command);

}

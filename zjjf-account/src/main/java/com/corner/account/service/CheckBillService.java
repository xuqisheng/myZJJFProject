package com.corner.account.service;

import com.corner.account.beans.ro.CheckBillCondition;
import com.corner.account.beans.vo.CheckBillVo;
import com.corner.core.beans.vo.Pager;

public interface CheckBillService extends BaseService {

	Pager<CheckBillVo> getCheckBillList(CheckBillCondition command);

	Pager<CheckBillVo> getCheckBillDetailList(CheckBillCondition command);

}

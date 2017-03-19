package com.corner.kefu.service;

import com.corner.core.beans.vo.Pager;
import com.corner.kefu.beans.ro.CheckBillCondition;
import com.corner.kefu.beans.vo.CheckBillVo;

public interface CheckBillService extends BaseService {

	Pager<CheckBillVo> getCheckBillList(CheckBillCondition command);

	Pager<CheckBillVo> getCheckBillDetailList(CheckBillCondition command);

}

package com.corner.kefu.service;

import com.corner.core.beans.vo.Pager;
import com.corner.kefu.beans.ro.BillSettleCondition;
import com.corner.kefu.beans.ro.CheckBillCondition;
import com.corner.kefu.beans.vo.BillSettleVo;

public interface BillSettleService extends BaseService{

	Pager<BillSettleVo> getBillSettleList(BillSettleCondition command);

	Pager<BillSettleVo> getBillSettleOrderList(CheckBillCondition command);

}

package com.corner.account.service;

import com.corner.account.beans.ro.BillSettleCondition;
import com.corner.account.beans.vo.BillSettleVo;
import com.corner.core.beans.vo.Pager;

public interface BillSettleService extends BaseService{

	Pager<BillSettleVo> getBillSettleList(BillSettleCondition command);

	Pager<BillSettleVo> getBillSettleOrderList(BillSettleCondition command);

}

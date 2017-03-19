package com.corner.account.dao;

import java.util.List;

import com.corner.account.beans.ro.BillSettleCondition;
import com.corner.account.beans.vo.BillSettleVo;

public interface BillSettleMapper {

	List<BillSettleVo> getPageList(BillSettleCondition command);

	int getPageListSize(BillSettleCondition command);

	List<BillSettleVo> getDetailPageList(BillSettleCondition command);

	int getDetailPageListSize(BillSettleCondition command);

}

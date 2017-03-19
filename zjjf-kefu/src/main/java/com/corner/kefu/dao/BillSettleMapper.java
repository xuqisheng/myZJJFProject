package com.corner.kefu.dao;

import java.util.List;

import com.corner.kefu.beans.ro.BillSettleCondition;
import com.corner.kefu.beans.ro.CheckBillCondition;
import com.corner.kefu.beans.vo.BillSettleVo;

public interface BillSettleMapper {

	List<BillSettleVo> getPageList(BillSettleCondition command);

	int getPageListSize(BillSettleCondition command);

	List<BillSettleVo> getDetailPageList(CheckBillCondition command);

	int getDetailPageListSize(CheckBillCondition command);

}

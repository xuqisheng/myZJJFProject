package com.corner.account.dao;

import java.util.List;

import com.corner.account.beans.ro.BillPayCondition;
import com.corner.account.beans.vo.BillPayVo;

public interface BillPayMapper {

	List<BillPayVo> getPageList(BillPayCondition command);

	int getPageListSize(BillPayCondition command);

	List<BillPayVo> getDetailPageList(BillPayCondition command);

	int getDetailPageListSize(BillPayCondition command);

}

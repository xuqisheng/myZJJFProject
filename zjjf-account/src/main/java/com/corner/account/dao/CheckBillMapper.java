package com.corner.account.dao;

import com.corner.account.beans.ro.CheckBillCondition;
import com.corner.account.beans.vo.CheckBillVo;

import java.util.List;

public interface CheckBillMapper {

	List<CheckBillVo> getPageList(CheckBillCondition command);
	
	int getPageListSize(CheckBillCondition command);

	List<CheckBillVo> getDetailPageList(CheckBillCondition command);

	int getDetailPageListSize(CheckBillCondition command);

}

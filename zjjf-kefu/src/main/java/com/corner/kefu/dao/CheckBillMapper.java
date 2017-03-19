package com.corner.kefu.dao;

import java.util.List;

import com.corner.kefu.beans.ro.CheckBillCondition;
import com.corner.kefu.beans.vo.CheckBillVo;

public interface CheckBillMapper {

	List<CheckBillVo> getPageList(CheckBillCondition command);
	
	int getPageListSize(CheckBillCondition command);

	List<CheckBillVo> getDetailPageList(CheckBillCondition command);

	int getDetailPageListSize(CheckBillCondition command);

}

package com.corner.account.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.corner.account.beans.ro.BillSheetCondition;
import com.corner.core.beans.AcSheet;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;

public interface AcSheetService {
	
	Pager<AcSheet> getAcSheetList(BillSheetCondition command);

	ModelMsg addAcSheet(BillSheetCondition command, HttpServletRequest request, HttpServletResponse response) throws Exception;

	ModelMsg updateAcsheetByCondition(BillSheetCondition command);

}

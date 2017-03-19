package com.corner.account.service;

import com.corner.account.beans.ro.SpWalletCheckMgCondition;
import com.corner.account.beans.ro.SpWithDrawMgCondition;
import com.corner.account.beans.vo.SpWalletCheckVo;
import com.corner.account.beans.vo.SpWithDrawVo;
import com.corner.core.beans.SpWDSheet;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface SpWithDrawService {

	Pager<SpWithDrawVo> getSpWithDrawList(SpWithDrawMgCondition command);
	
	Pager<SpWithDrawVo> getSpWithDrawDetailList(SpWithDrawMgCondition command);

	ModelMsg updateWithDrawStatus(SpWithDrawMgCondition command);

	ModelMsg addDWSheet(SpWithDrawMgCondition command, HttpServletRequest request, HttpServletResponse response) throws Exception;

	Pager<SpWDSheet> getSpWDSheetList(SpWithDrawMgCondition command);
	
	ModelMsg updateWDsheetByCondition(SpWithDrawMgCondition command);

	Pager<SpWalletCheckVo> getSpOneDayCheckList(SpWalletCheckMgCondition command);

}

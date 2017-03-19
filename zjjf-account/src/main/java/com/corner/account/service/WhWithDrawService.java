package com.corner.account.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.corner.account.beans.ro.WhWalletCheckMgCondition;
import com.corner.account.beans.ro.WhWithDrawMgCondition;
import com.corner.account.beans.vo.ScmsWarehouseWithDrawVo;
import com.corner.account.beans.vo.WhWalletCheckVo;
import com.corner.account.beans.vo.WhWithDrawVo;
import com.corner.core.beans.WhWDSheet;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;

public interface WhWithDrawService {

	Pager<ScmsWarehouseWithDrawVo> getWhWithDrawList(WhWithDrawMgCondition command);
	
	Pager<WhWithDrawVo> getWhWithDrawDetailList(WhWithDrawMgCondition command);

	ModelMsg updateWithDrawStatus(WhWithDrawMgCondition command);

	ModelMsg addDWSheet(WhWithDrawMgCondition command, HttpServletRequest request, HttpServletResponse response) throws Exception;

	Pager<WhWDSheet> getWhWDSheetList(WhWithDrawMgCondition command);
	
	ModelMsg updateWDsheetByCondition(WhWithDrawMgCondition command);

	Pager<WhWalletCheckVo> getWhOneDayCheckList(WhWalletCheckMgCondition command);

}

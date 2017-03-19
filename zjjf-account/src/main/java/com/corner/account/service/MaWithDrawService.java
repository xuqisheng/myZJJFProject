package com.corner.account.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.corner.account.beans.ro.MaWalletCheckMgCondition;
import com.corner.account.beans.ro.MaWithDrawMgCondition;
import com.corner.account.beans.vo.MaWalletCheckVo;
import com.corner.account.beans.vo.MaWithDrawVo;
import com.corner.account.beans.vo.ManagerWithDrawVo;
import com.corner.core.beans.MaWDSheet;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;

public interface MaWithDrawService {

	Pager<ManagerWithDrawVo> getMaWithDrawList(MaWithDrawMgCondition command);
	
	Pager<MaWithDrawVo> getMaWithDrawDetailList(MaWithDrawMgCondition command);

	ModelMsg updateWithDrawStatus(MaWithDrawMgCondition command);

	ModelMsg addDWSheet(MaWithDrawMgCondition command, HttpServletRequest request, HttpServletResponse response) throws Exception;

	Pager<MaWDSheet> getMaWDSheetList(MaWithDrawMgCondition command);
	
	ModelMsg updateWDsheetByCondition(MaWithDrawMgCondition command);

	Pager<MaWalletCheckVo> getMaOneDayCheckList(MaWalletCheckMgCondition command);

}

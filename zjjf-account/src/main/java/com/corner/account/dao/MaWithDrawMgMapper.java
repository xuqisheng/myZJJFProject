package com.corner.account.dao;

import java.util.List;

import com.corner.account.beans.ro.MaWalletCheckMgCondition;
import com.corner.account.beans.ro.MaWithDrawMgCondition;
import com.corner.account.beans.vo.MaWalletCheckVo;
import com.corner.account.beans.vo.MaWithDrawVo;
import com.corner.account.beans.vo.ManagerWithDrawVo;

public interface MaWithDrawMgMapper {

	List<ManagerWithDrawVo> getMaWithDrawList(MaWithDrawMgCondition command);

	int getMaWithDrawListCount(MaWithDrawMgCondition command);
	
	List<MaWithDrawVo> getMaWithDrawDetailList(MaWithDrawMgCondition command);

	int getMaWithDrawDetailListCount(MaWithDrawMgCondition command);

	int updateMaWithDrawBatch(MaWithDrawMgCondition command);

	List<MaWalletCheckVo> getMaWithDrawCheckList(MaWalletCheckMgCondition command);


}

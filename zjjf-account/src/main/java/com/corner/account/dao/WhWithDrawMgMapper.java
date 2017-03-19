package com.corner.account.dao;

import java.util.List;

import com.corner.account.beans.ro.WhWalletCheckMgCondition;
import com.corner.account.beans.ro.WhWithDrawMgCondition;
import com.corner.account.beans.vo.ScmsWarehouseWithDrawVo;
import com.corner.account.beans.vo.WhWalletCheckVo;
import com.corner.account.beans.vo.WhWithDrawVo;

public interface WhWithDrawMgMapper {

	List<ScmsWarehouseWithDrawVo> getWhWithDrawList(WhWithDrawMgCondition command);

	int getWhWithDrawListCount(WhWithDrawMgCondition command);
	
	List<WhWithDrawVo> getWhWithDrawDetailList(WhWithDrawMgCondition command);

	int getWhWithDrawDetailListCount(WhWithDrawMgCondition command);

	int updateWhWithDrawBatch(WhWithDrawMgCondition command);

	List<WhWalletCheckVo> getWhWithDrawCheckList(WhWalletCheckMgCondition command);

}

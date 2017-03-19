package com.corner.account.dao;

import java.util.List;

import com.corner.account.beans.ro.SpWalletCheckMgCondition;
import com.corner.account.beans.ro.SpWithDrawMgCondition;
import com.corner.account.beans.vo.SpWalletCheckVo;
import com.corner.account.beans.vo.SpWithDrawVo;

public interface SpWithDrawMgMapper {

	List<SpWithDrawVo> getSpWithDrawList(SpWithDrawMgCondition command);

	int getSpWithDrawListCount(SpWithDrawMgCondition command);
	
	List<SpWithDrawVo> getSpWithDrawDetailList(SpWithDrawMgCondition command);

	int getSpWithDrawDetailListCount(SpWithDrawMgCondition command);

	int updateSpWithDrawBatch(SpWithDrawMgCondition command);

	List<SpWalletCheckVo> getSpWithDrawCheckList(SpWalletCheckMgCondition command);


}

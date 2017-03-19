package com.corner.account.dao;

import java.util.List;

import com.corner.account.beans.ro.MaWithDrawMgCondition;
import com.corner.core.beans.MaWDSheet;
import com.corner.core.beans.MaWDSheetMapKey;

public interface MaWDSheetMgMapper {
	List<MaWDSheet> getPageList(MaWithDrawMgCondition command);

	int getPageListSize(MaWithDrawMgCondition command);

	List<MaWDSheetMapKey> checkSheetExit(MaWithDrawMgCondition command);

	int addWDSheetMapBatch(MaWithDrawMgCondition command);

}

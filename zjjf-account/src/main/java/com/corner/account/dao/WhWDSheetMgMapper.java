package com.corner.account.dao;

import java.util.List;

import com.corner.account.beans.ro.WhWithDrawMgCondition;
import com.corner.core.beans.WhWDSheet;
import com.corner.core.beans.WhWDSheetMapKey;

public interface WhWDSheetMgMapper {
	List<WhWDSheet> getPageList(WhWithDrawMgCondition command);

	int getPageListSize(WhWithDrawMgCondition command);

	List<WhWDSheetMapKey> checkSheetExit(WhWithDrawMgCondition command);

	int addWDSheetMapBatch(WhWithDrawMgCondition command);

}

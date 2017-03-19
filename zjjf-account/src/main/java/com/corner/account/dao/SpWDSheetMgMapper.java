package com.corner.account.dao;

import java.util.List;

import com.corner.account.beans.ro.SpWithDrawMgCondition;
import com.corner.core.beans.SpWDSheet;
import com.corner.core.beans.SpWDSheetMapKey;

public interface SpWDSheetMgMapper {
	List<SpWDSheet> getPageList(SpWithDrawMgCondition command);

	int getPageListSize(SpWithDrawMgCondition command);

	List<SpWDSheetMapKey> checkSheetExit(SpWithDrawMgCondition command);

	int addWDSheetMapBatch(SpWithDrawMgCondition command);

}

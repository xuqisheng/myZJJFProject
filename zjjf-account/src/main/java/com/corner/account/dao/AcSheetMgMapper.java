package com.corner.account.dao;

import com.corner.account.beans.ro.BillSheetCondition;
import com.corner.core.beans.AcSheet;
import com.corner.core.beans.AcSheetOrderMapKey;

import java.util.List;

public interface AcSheetMgMapper {

	List<AcSheet> getPageList(BillSheetCondition command);

	int getPageListSize(BillSheetCondition command);

	List<AcSheetOrderMapKey> checkSheetExit(BillSheetCondition command);

	int addOrderSheetMapBatch(BillSheetCondition command);

}

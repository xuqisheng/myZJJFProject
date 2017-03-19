package com.corner.scms.dao;

import com.corner.core.beans.MaOrderInfo;
import com.corner.core.beans.ScOrderInfo;

public interface MaOrderInfoMgMapper {

	MaOrderInfo selectMaOrderInfo(ScOrderInfo scOrderInfoLevel2) throws Exception;

}

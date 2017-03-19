package com.corner.account.dao;

import java.util.List;

import com.corner.account.beans.ro.SystemConfigCondition;
import com.corner.core.beans.SettlementConf;

public interface SettlementConfMgMapper {

	List<SettlementConf> getPageList(SystemConfigCondition command);

	int getPageListSize(SystemConfigCondition command);

}

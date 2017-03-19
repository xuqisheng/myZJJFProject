package com.corner.account.service;

import java.util.List;

import com.corner.account.beans.ro.SystemConfigCondition;
import com.corner.core.beans.SettlementConf;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;

public interface SystemConfigMgService extends BaseService{

	ModelMsg updateByPrimaryKeySelective(SettlementConf settlementConf);

	Pager<SettlementConf> getSettlementConfPageList(SystemConfigCondition command);

	ModelMsg addObject(SettlementConf settlementConf);
}

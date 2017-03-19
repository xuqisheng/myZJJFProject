package com.corner.account.service;

import java.util.Map;

import com.corner.account.beans.ro.SpWithDrawMgCondition;
import com.corner.core.beans.vo.Pager;


public interface SpWalletService {
	Pager<Map<String, Object>> getSpWalletPage(SpWithDrawMgCondition command);
	Pager<Map<String, Object>> getSpWalletLogPage(SpWithDrawMgCondition command);
}

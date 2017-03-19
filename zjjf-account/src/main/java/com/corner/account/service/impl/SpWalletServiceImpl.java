package com.corner.account.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.account.beans.ro.SpWithDrawMgCondition;
import com.corner.account.dao.SpWalletLogMgMapper;
import com.corner.account.service.SpWalletService;
import com.corner.core.beans.vo.Pager;

@Service
public class SpWalletServiceImpl extends BaseServiceImpl implements SpWalletService {

	private static Logger logger = LoggerFactory.getLogger(SpWalletServiceImpl.class);
	@Autowired
	SpWalletLogMgMapper spWalletLogMgMapper;
	@Override
	public Pager<Map<String, Object>> getSpWalletPage(SpWithDrawMgCondition command) {
		List<Map<String, Object>> list = spWalletLogMgMapper.getSpWalletPage(command);
		int count = spWalletLogMgMapper.getSpWalletPageCount(command);
		return new Pager<Map<String, Object>>(count, list);
	}
	@Override
	public Pager<Map<String, Object>> getSpWalletLogPage(SpWithDrawMgCondition command) {
		List<Map<String, Object>> list = spWalletLogMgMapper.getSpWalletLogPage(command);
		int count = spWalletLogMgMapper.getSpWalletLogPageCount(command);
		return new Pager<Map<String, Object>>(count, list);
	}
}

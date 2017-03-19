package com.corner.kefu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.beans.ShareDisseminateLog;
import com.corner.kefu.dao.ShareDisseminateLogMgMapper;
import com.corner.kefu.service.ShareDisseminateLogService;
@Service
public class ShareDisseminateLogServiceImpl implements ShareDisseminateLogService {
	@Autowired
	ShareDisseminateLogMgMapper shareDisseminateLogMgMapper;

	@Override
	public void updateLogByStoreId(ShareDisseminateLog log) {
		// TODO Auto-generated method stub
		shareDisseminateLogMgMapper.updateLogByStoreId(log);
	}

}

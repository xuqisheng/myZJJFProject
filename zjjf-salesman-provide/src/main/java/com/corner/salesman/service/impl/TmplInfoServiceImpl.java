package com.corner.salesman.service.impl;

import com.corner.salesman.dao.TmplDetailInfoMapper;
import com.corner.salesman.dao.TmplInfoMapper;
import com.corner.salesman.model.TmplDetailInfo;
import com.corner.salesman.model.TmplInfo;
import com.corner.salesman.service.TmplInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 模板业务层
 * @author Administrator
 *
 */
@Service
public class TmplInfoServiceImpl implements TmplInfoService {

	@Autowired
	private TmplInfoMapper tmplInfoMapper;
	@Autowired
	private TmplDetailInfoMapper tmplDetailMapper;
	
	@Override
	public List<TmplInfo> findTmplInfoList() throws Exception {
		return tmplInfoMapper.findTmplInfoList();
	}

    /**
     * 根据模板ID获取对应模板字段列表
     * @param tmplId
     * @return
     */
	@Override
	public List<TmplDetailInfo> findTmplAttrList(String tmplId) throws Exception {
		return tmplDetailMapper.findTmplDetailByTmplId(tmplId);
	}
}

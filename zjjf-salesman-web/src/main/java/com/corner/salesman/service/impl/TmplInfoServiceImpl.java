package com.corner.salesman.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.beans.vo.Pager;
import com.corner.salesman.dao.TmplDetailInfoMapper;
import com.corner.salesman.dao.TmplInfoMapper;
import com.corner.salesman.model.TmplInfo;
import com.corner.salesman.service.TmplInfoService;
@Service
public class TmplInfoServiceImpl implements TmplInfoService{
	
	@Autowired
	private TmplInfoMapper tmplInfoMapper;
	@Autowired
	private TmplDetailInfoMapper detailInfoMapper;

	@Override
	public Pager<TmplInfo> getTmplInfoPageList(TmplInfo tmplVo) throws Exception {
		List<TmplInfo> list = tmplInfoMapper.getTmplInfoPageList(tmplVo);
		int size = tmplInfoMapper.getTmplInfoPageSize(tmplVo);
		return new Pager<TmplInfo>(size, list);
	}

	@Override
	public int addTmplInfo(TmplInfo tmplVo) throws Exception {
		return tmplInfoMapper.insertSelective(tmplVo);
	}

	@Override
	public int updateTmplInfo(TmplInfo tmplVo) throws Exception {
		return tmplInfoMapper.updateByPrimaryKeySelective(tmplVo);
	}

	@Override
	public int deleteTmplInfo(String id) throws Exception {
		detailInfoMapper.delTmplDetailByTmplId(id);
		return tmplInfoMapper.deleteByPrimaryKey(id);
	}

	@Override
	public TmplInfo findTmplInfoById(String id) throws Exception {
		return tmplInfoMapper.selectByPrimaryKey(id);
	}


}

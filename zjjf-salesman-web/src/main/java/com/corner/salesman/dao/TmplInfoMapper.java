package com.corner.salesman.dao;

import java.util.List;

import com.corner.salesman.model.TmplInfo;

public interface TmplInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(TmplInfo record);

    int insertSelective(TmplInfo record);

    TmplInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TmplInfo record);

    int updateByPrimaryKey(TmplInfo record);
    
	/**
	 * 查询模板信息分页列表
	 * @param record
	 * @return
	 */
    public List<TmplInfo> getTmplInfoPageList(TmplInfo record);
	/**
	 * 查询模板信息分页总数
	 * @param record
	 * @return
	 */
	public int getTmplInfoPageSize(TmplInfo record);
	
}
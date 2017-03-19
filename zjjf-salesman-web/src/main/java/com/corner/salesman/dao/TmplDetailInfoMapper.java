package com.corner.salesman.dao;

import java.util.List;

import com.corner.salesman.model.TmplDetailInfo;

public interface TmplDetailInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TmplDetailInfo record);

    int insertSelective(TmplDetailInfo record);

    TmplDetailInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TmplDetailInfo record);

    int updateByPrimaryKey(TmplDetailInfo record);
    
    /**
     * 根据模板id查询模板相关属性
     * @param tmplId
     * @return
     */
    List<TmplDetailInfo> findTmplDetailByTmplId(String tmplId);
    
    /**
     * 根据模板id删除模板相关属性
     * @param tmplId
     * @return
     */
    int delTmplDetailByTmplId(String tmplId);
}
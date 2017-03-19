package com.corner.salesman.dao;

import java.util.List;
import com.corner.salesman.model.Dictionary;

public interface DictionaryMapper {
    int deleteByPrimaryKey(String id);

    int insert(Dictionary record);

    int insertSelective(Dictionary record);

    Dictionary selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Dictionary record);

    int updateByPrimaryKey(Dictionary record);
    
	/**
	 * 查询字典信息列表（分页）
	 * @param record
	 * @return
	 */
    public List<Dictionary> getDictPageList(Dictionary record);
	/**
	 * 查询字典信息总数
	 * @param record
	 * @return
	 */
	public int getDictPageSize(Dictionary record);
	
	/**
	 * 根据类型查询字典中的配置数据
	 * @param record
	 * @return
	 */
	public List<Dictionary> findDictListByType(String type);
	
	/**
	 * 根据type和value获取记录
	 * @param record
	 * @return
	 */
	public List<Dictionary> findDictListByTypeAndValue(Dictionary record);
}
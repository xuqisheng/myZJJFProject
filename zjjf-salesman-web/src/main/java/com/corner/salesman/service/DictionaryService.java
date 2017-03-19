package com.corner.salesman.service;

import java.util.List;

import com.corner.core.beans.vo.Pager;
import com.corner.salesman.model.Dictionary;

public interface DictionaryService {
	
    public List<Dictionary> findDictList() throws Exception;
    
	/**
	 * 查询部门分页方法
	 * @param dictiVO
	 * @return
	 * @throws Exception
	 */
	public Pager<Dictionary> getDictPageList(Dictionary dictiVO) throws Exception;
	
	/**
	 * 添加部门信息方法
	 * @param dictiVO
	 * @return
	 * @throws Exception
	 */
	public int addDictionary(Dictionary dictiVO) throws Exception;
	
	/**
	 * 修改部门信息方法
	 * @param dictiVO
	 * @return
	 * @throws Exception
	 */
	public int updateDictionary(Dictionary dictiVO) throws Exception;
	
	/**
	 * 删除部门信息方法
	 * @param dictiVO
	 * @return
	 * @throws Exception
	 */
	public int deleteDictionary(Dictionary dictiVO) throws Exception;
	
	/**
	 * 根据ID查询部门信息
	 * @param dictiVO
	 * @return
	 * @throws Exception
	 */
	public Dictionary findDictionaryById(String id) throws Exception;
	
	/**
	 * 根据类型查询字典中的配置数据
	 * @param record
	 * @return
	 */
	public List<Dictionary> findDictListByType(String type) throws Exception;
	
	/**
	 * 根据type和value组合获取已经存在的记录
	 * @param dictVo
	 * @return
	 * @throws Exception
	 */
	public boolean isExit_type_value_recode(Dictionary dictVo) throws Exception;
}

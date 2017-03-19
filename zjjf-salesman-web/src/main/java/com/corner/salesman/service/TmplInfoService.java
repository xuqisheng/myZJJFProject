package com.corner.salesman.service;

import com.corner.core.beans.vo.Pager;
import com.corner.salesman.model.TmplInfo;

/**
 * 模板信息业务层接口
 * @author Administrator
 *
 */
public interface TmplInfoService {
	
	/**
	 * 查询部门分页方法
	 * @param tmplVo
	 * @return
	 * @throws Exception
	 */
	public Pager<TmplInfo> getTmplInfoPageList(TmplInfo tmplVo) throws Exception;
	
	/**
	 * 添加部门信息方法
	 * @param tmplVo
	 * @return
	 * @throws Exception
	 */
	public int addTmplInfo(TmplInfo tmplVo) throws Exception;
	
	/**
	 * 修改部门信息方法
	 * @param tmplVo
	 * @return
	 * @throws Exception
	 */
	public int updateTmplInfo(TmplInfo tmplVo) throws Exception;
	
	/**
	 * 删除部门信息方法
	 * @param tmplVo
	 * @return
	 * @throws Exception
	 */
	public int deleteTmplInfo(String id) throws Exception;
	
	/**
	 * 根据ID查询部门信息
	 * @param tmplVo
	 * @return
	 * @throws Exception
	 */
	public TmplInfo findTmplInfoById(String id) throws Exception;
	
}

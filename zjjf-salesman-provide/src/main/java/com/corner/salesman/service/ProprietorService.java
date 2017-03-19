package com.corner.salesman.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.corner.salesman.common.utils.Json;
import com.corner.salesman.model.Proprietor;
import com.corner.salesman.model.User;

/**  
 * @desc  敬业者业务层接口
 * 创建时间：2015-1-27 下午5:15:03  
 * @author 元宝  
 * @version 2.2  
 */

public interface ProprietorService {
	
	/**
	 * 根据ID查询条件查询经验者信息
	 * @param proprietor
	 * @return
	 * @throws Exception
	 */
	public Proprietor findProprietorById(String id) throws Exception;
	
	/**
	 * 根据查询条件查询经验者信息
	 * @param proprietor
	 * @return
	 * @throws Exception
	 */
	public List<Proprietor> findProprietorList(Proprietor proprietor) throws Exception;
	
    /**
     * 添加经营者信息方法
     * @param proprietor
     * @return
     */
    public int addProprietorInfo(Proprietor proprietor) throws Exception;
    
	
    /**
     * 修改经营者信息方法
     * @param proprietor
     * @return
     */
    public int updateProprietorInfo(Proprietor proprietor) throws Exception;
}

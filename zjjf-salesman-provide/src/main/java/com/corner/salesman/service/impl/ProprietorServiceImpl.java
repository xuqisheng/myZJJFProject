package com.corner.salesman.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.corner.salesman.common.utils.DateUtils;
import com.corner.salesman.common.utils.IdGen;
import com.corner.salesman.common.utils.StringUtils;
import com.corner.salesman.dao.ProprietorMapper;
import com.corner.salesman.model.Proprietor;
import com.corner.salesman.service.ProprietorService;

/**
 * 创建时间：2015-1-27 下午5:22:59
 * 
 * @author andy
 * @version 2.2
 */
@Service("proprietorService")
@Transactional(readOnly = true)
public class ProprietorServiceImpl implements ProprietorService {

	@Autowired
	private ProprietorMapper proprietorMapper;
	
	/**
	 * 根据ID查询条件查询经验者信息
	 * @param proprietor
	 * @return
	 * @throws Exception
	 */
	public Proprietor findProprietorById(String id) throws Exception{
		return proprietorMapper.selectByPrimaryKey(id);
	}
	/**
	 * 根据查询条件查询经验者信息
	 * @param proprietor
	 * @return
	 * @throws Exception
	 */
	public List<Proprietor> findProprietorList(Proprietor proprietor) throws Exception{
		return null;
	}
	
    /**
     * 添加经营者信息方法
     * @param proprietor
     * @return
     */
	@Transactional(readOnly = false)
    public int addProprietorInfo(Proprietor proprietor) throws Exception{
		proprietor.setId(IdGen.uuid());
		proprietor.setCreateBy(proprietor.getUserId());
		proprietor.setCreateTime(DateUtils.dateToString(new Date(), DateUtils.DATETIME_FORMAT));
    	return proprietorMapper.insertSelective(proprietor);
    }
	
    /**
     * 修改经营者信息方法
     * @param proprietor
     * @return
     */
	@Transactional(readOnly = false)
    public int updateProprietorInfo(Proprietor proprietor) throws Exception{
		String id = proprietor.getId();
		//如果经营者ID不为空则修改相关信息，反之则新增经营者信息
		if(StringUtils.isNotBlank(id)){
			proprietor.setUpdateBy(proprietor.getUserId());
			proprietor.setUpdateTime(DateUtils.dateToString(new Date(), DateUtils.DATETIME_FORMAT));
	    	return proprietorMapper.updateByPrimaryKeySelective(proprietor);
		}else{
			proprietor.setId(IdGen.uuid());
			proprietor.setCreateBy(proprietor.getUserId());
			proprietor.setCreateTime(DateUtils.dateToString(new Date(), DateUtils.DATETIME_FORMAT));
	    	return proprietorMapper.insertSelective(proprietor);
		}
    }

}

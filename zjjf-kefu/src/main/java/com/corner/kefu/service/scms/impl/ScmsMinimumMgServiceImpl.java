package com.corner.kefu.service.scms.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.beans.Brand;
import com.corner.core.beans.CustomerService;
import com.corner.core.beans.ItemBase;
import com.corner.core.beans.ScmsItem;
import com.corner.core.beans.ScmsMinimum;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.dao.BrandMapper;
import com.corner.core.dao.CustomerServiceMapper;
import com.corner.core.dao.ItemBaseMapper;
import com.corner.core.dao.ScmsItemMapper;
import com.corner.core.dao.ScmsMinimumMapper;
import com.corner.kefu.beans.ro.scms.ScmsMinimumMgRo;
import com.corner.kefu.dao.ScmsMinimumMgMapper;
import com.corner.kefu.service.scms.ScmsMinimumMgService;
/**
 * 
 * @ClassName: ScmsOrderInfoMgServiceImpl 
 * @Description: 品牌起购量信息处理
 * @author 孟星魂	mengxinghun@izjjf.cn
 * @date 2015年12月28日 上午11:45:29 
 *
 */

@Service
public class ScmsMinimumMgServiceImpl implements ScmsMinimumMgService{
	private static Logger logger = LoggerFactory.getLogger(ScmsMinimumMgServiceImpl.class);
	@Autowired
	ScmsMinimumMgMapper scmsMinimumMgMapper;
	@Autowired
	ScmsMinimumMapper scmsMinimumMapper;
	@Autowired
	CustomerServiceMapper customerServiceMapper;
	@Autowired
	ItemBaseMapper itemBaseMapper;
	@Autowired
	BrandMapper brandMapper;
	@Autowired
	ScmsItemMapper scmsItemMapper;
	
	
	/*	获取起购量信息集合
	* <p>Title: getScmsMinimumByManagerId</p> 
	* <p>Description: </p> 
	* @param managerId
	* @return 
	* @see com.corner.kefu.service.scms.ScmsMinimumMgService#getScmsMinimumByManagerId(java.lang.String) 
	*/ 
	@Override
	public List<ScmsMinimum> getScmsMinimumByManagerId(String managerId) {
		return scmsMinimumMgMapper.getScmsMinimumByManagerId(managerId);
	}
	@Override
	public ModelMsg updateScmsMinimum(ScmsMinimumMgRo command) {
		ScmsMinimum minimum = new ScmsMinimum();
		minimum.setManagerId(command.getManagerId());
		minimum.setUpdateTime(new Date());
		CustomerService customerService = customerServiceMapper.selectByPrimaryKey(command.getKefuId());
		minimum.setKfId(customerService.getId());
		minimum.setKfName(customerService.getNickName());
		for (int i = 0; i < command.getIds().length; i++) {
			minimum.setId(command.getIds()[i]);
			minimum.setMinimum(Short.valueOf(command.getMinimums()[i]));
			int result = scmsMinimumMapper.updateByPrimaryKeySelective(minimum);
			if(result == 0)
				return new ModelMsg(false, "起购量信息修改失败");
		}
		return new ModelMsg(true, "起购量信息修改成功");
	}
	/*	TODO(用一句话描述这个变量表示什么) 
	* <p>Title: insertScmsMinimum</p> 
	* <p>Description: </p> 
	* @param command
	* @return 
	* @see com.corner.kefu.service.scms.ScmsMinimumMgService#insertScmsMinimum(com.corner.core.beans.ScmsItem) 
	*/ 
	@Override
	public ModelMsg insertScmsMinimum(ScmsItem command) {
		ScmsMinimum minimum = new ScmsMinimum();
		ItemBase itemBase= itemBaseMapper.selectByPrimaryKey(command.getItemBaseId());
		if(itemBase.getBrandId() == null || "".equals(itemBase.getBrandId())){
			return new ModelMsg(false, "该商品无品牌信息，请到基础商品库完善");
		}
		Brand brand = brandMapper.selectByPrimaryKey(itemBase.getBrandId());
		if(brand == null){
			return new ModelMsg(false, "该商品品牌信息不存在，请到基础商品库修正");
		}
		minimum.setBrandId(brand.getId());
		minimum.setManagerId(command.getManagerId());
		ScmsMinimum minimum2 = scmsMinimumMgMapper.getScmsMinimumByBrandIdAndManagerId(minimum);
		if(minimum2 != null){
			logger.info("已有该品牌的起购量");
			command.setMiniumId(minimum2.getId());
			return new ModelMsg(true, "已有该品牌的起购量" , minimum2);
		}
		minimum.setBrandName(brand.getName());
		minimum.setAddTime(new Date());
		//数据默认值100
		//minimum.setMinimum(Short.valueOf("100"));
		int result = scmsMinimumMapper.insertSelective(minimum);
		if(result == 0 ){
			logger.info("起购量信息添加失败");
			return new ModelMsg(false, "起购量信息添加失败");
		}
		logger.info("起购量信息添加成功");
		command.setMiniumId(minimum.getId());
		return new ModelMsg(true, "起购量信息添加成功" , minimum);
	}
}

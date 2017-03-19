package com.corner.kefu.service.scms.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.beans.ScmsFreightTpl;
import com.corner.core.beans.ScmsFreightTplMap;
import com.corner.core.beans.ScmsWarehouse;
import com.corner.core.beans.vo.Pager;
import com.corner.core.dao.ScmsFreightTplMapMapper;
import com.corner.core.dao.ScmsFreightTplMapper;
import com.corner.kefu.beans.ro.ScmsFreightTplRo;
import com.corner.kefu.dao.ScmsFreightTplMgMapper;
import com.corner.kefu.dao.ScmsWarehouseMgMapper;
import com.corner.kefu.service.scms.TempletService;

@Service
public class TempletServiceImpl implements TempletService{

	@Autowired
	private ScmsFreightTplMapper scmsFreightTplMapper;
	
	@Autowired
	private ScmsFreightTplMgMapper scmsFreightTplMgMapper;

	@Autowired
	private ScmsFreightTplMapMapper scmsFreightTplMapMapper;
	
	@Autowired
	private ScmsWarehouseMgMapper scmsWarehouseMgMapper;

	@Override
	public Pager<ScmsFreightTpl> findAllTemplet(ScmsFreightTpl condition) {
		List<ScmsFreightTpl> list=this.scmsFreightTplMgMapper.getPageList(condition);
		int size=this.scmsFreightTplMgMapper.getPageListSize(condition);
		return new Pager<ScmsFreightTpl>(size,list);
	}


	@Override
	public Integer deleteObject(String id) {
		List<ScmsWarehouse> list=this.scmsWarehouseMgMapper.findwarehouseBytplId(id);
		if(list.size()>0){
			return 3;
		}
		return this.scmsFreightTplMgMapper.updateObject(id);
	}

//此代码 和后面的 edit方法类似    待 重构
	@Override
	public int addObject(ScmsFreightTplRo condition) {
		ScmsFreightTpl tpl=new ScmsFreightTpl();
		tpl.setAddTime(new Date());
		tpl.setName(condition.getName());
		tpl.setTplMark(condition.getTplMark());
		tpl.setPaidMethods(condition.getPaidMethods());
		int result=this.scmsFreightTplMapper.insertSelective(tpl);
		if(condition.getPaidMethods()==0){
			for(int i=0;i<condition.getOrderPrice().length;i++){
				ScmsFreightTplMap map=new ScmsFreightTplMap();
				map.setOrderPrice(condition.getOrderPrice()[i]);
				map.setFreight(condition.getFreight()[i]);
				map.setTplId(tpl.getId());
				this.scmsFreightTplMapMapper.insertSelective(map);
			}
		}else if(condition.getPaidMethods()==1){
			for(int i=0;i<condition.getFreightPrice().length;i++){
				ScmsFreightTplMap map=new ScmsFreightTplMap();
				map.setFreightPrice(condition.getFreightPrice()[i]);
				map.setOrderNum(condition.getOrderNum()[i]);
				map.setTplId(tpl.getId());
				this.scmsFreightTplMapMapper.insertSelective(map);
			}
		}else if(condition.getPaidMethods()==2){
			ScmsFreightTplMap map=new ScmsFreightTplMap();
			map.setFreightPrice(condition.getOneFreightPrice());
			map.setTplId(tpl.getId());
			this.scmsFreightTplMapMapper.insertSelective(map);
		}else{
			result = 0;
		}
		return result;
	}


	@Override
	public ScmsFreightTpl findTelById(String id) {
		return this.scmsFreightTplMapper.selectByPrimaryKey(id);
	}


	@Override
	public List<ScmsFreightTplMap> findAllTplMapByUid(String id) {
		return this.scmsFreightTplMgMapper.findAllTplMapByUid(id);
	}

//此方法和前面 add方法类似  待重构
	@Override
	public int edit(ScmsFreightTplRo condition) {
		this.scmsFreightTplMgMapper.deleteobject(condition.getId());
		ScmsFreightTpl tpl=new ScmsFreightTpl();
		tpl.setId(condition.getId());
		tpl.setName(condition.getName());
		tpl.setTplMark(condition.getTplMark());
		tpl.setPaidMethods(condition.getPaidMethods());
		int result=this.scmsFreightTplMapper.updateByPrimaryKeySelective(tpl);
		if(condition.getPaidMethods()==0){
			for(int i=0;i<condition.getOrderPrice().length;i++){
				ScmsFreightTplMap map=new ScmsFreightTplMap();
				map.setOrderPrice(condition.getOrderPrice()[i]);
				map.setFreight(condition.getFreight()[i]);
				map.setTplId(tpl.getId());
				this.scmsFreightTplMapMapper.insertSelective(map);
			}
		}else if(condition.getPaidMethods() == 1){
			for(int i=0;i<condition.getFreightPrice().length;i++){
				ScmsFreightTplMap map=new ScmsFreightTplMap();
				map.setFreightPrice(condition.getFreightPrice()[i]);
				map.setOrderNum(condition.getOrderNum()[i]);
				map.setTplId(tpl.getId());
				this.scmsFreightTplMapMapper.insertSelective(map);
			}
		}else if(condition.getPaidMethods() == 2){
			ScmsFreightTplMap map=new ScmsFreightTplMap();
			map.setFreightPrice(condition.getOneFreightPrice());
			map.setTplId(tpl.getId());
			this.scmsFreightTplMapMapper.insertSelective(map);
		}else{
			result = 0;
		}
		return result;
	}
	
	
}

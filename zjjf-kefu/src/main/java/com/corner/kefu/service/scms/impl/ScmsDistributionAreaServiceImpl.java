package com.corner.kefu.service.scms.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.beans.ScmsGroup;
import com.corner.core.beans.ScmsItem;
import com.corner.core.beans.Supplier;
import com.corner.core.beans.vo.Pager;
import com.corner.core.dao.ScmsGroupMapper;
import com.corner.kefu.beans.ro.scms.ScmsDistributionAreaRo;
import com.corner.kefu.beans.vo.ScmsGroupVo;
import com.corner.kefu.dao.ScmsGroupMgMapper;
import com.corner.kefu.service.scms.ScmsDistributionAreaService;

@Service
public class ScmsDistributionAreaServiceImpl implements ScmsDistributionAreaService{

	
	@Autowired
	ScmsGroupMapper scmsGroupMapper;
	
	
	@Autowired
	ScmsGroupMgMapper scmsGroupMgMapper;
	
	
	@Override
	public List<ScmsGroup> findAllArea() {
		return this.scmsGroupMgMapper.findScmsGroupByUpId("0");
	}
	
	@Override
	public Pager<ScmsGroupVo> findAllScmsDistributionArea(ScmsDistributionAreaRo condition) {
		if(condition.getPageIndex()<0){
			condition.setPageIndex(0);
		}
		if(condition.getPageSize()<=0){
			condition.setPageSize(10);
		}
		
		List<ScmsGroupVo> list=this.scmsGroupMgMapper.getPageList(condition);
		int size=this.scmsGroupMgMapper.getPageListSize(condition);
		
		return new Pager<ScmsGroupVo>(size,list);
	}
	
	@Override
	public int addScmsGroup(ScmsGroup scmsGroup){
		return this.scmsGroupMapper.insertSelective(scmsGroup);
	}
	@Override
	public int deleteArea(Integer id) {
		ScmsGroup group=new ScmsGroup();
		group.setId(id);
		group.setIsDelete(true);
		return this.scmsGroupMapper.updateByPrimaryKeySelective(group);
	}
	@Override
	public ScmsGroup findById(String id) {
		return this.scmsGroupMapper.selectByPrimaryKey(Integer.valueOf(id));
	}
	@Override
	public int update(ScmsGroup group) {
		return this.scmsGroupMapper.updateByPrimaryKeySelective(group);
	}
	
	/**
	 * 
	* @Title: findScmsGroupByUpId 
	* @Description: 根据UpId 获取子分类信息
	* @param @param upId
	* @param @return    设定文件 
	* @return List<ScmsGroup>    返回类型 
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @throws
	 */
	@Override
	public List<ScmsGroup> findScmsGroupByUpId(String upId) {
		return this.scmsGroupMgMapper.findScmsGroupByUpId(upId);
	}
	@Override
	public List<ScmsGroup> findScmsGroupBy0(){
		return this.scmsGroupMgMapper.findScmsGroupBy0();
	}
	@Override
	public List<ScmsGroup> getScmsGroupname(String name) {
		
		return scmsGroupMgMapper.getScmsGroupname(name);
	}

	@Override
	public int deletecategory(Integer id) {
		int result=this.scmsGroupMgMapper.deletecategory(id);
		this.scmsGroupMgMapper.updateAreaByUid(id);
		return result;
	}

	@Override
	public int updateCategory(ScmsGroup group) {
		return this.scmsGroupMapper.updateByPrimaryKeySelective(group);
	}

	@Override
	public List<ScmsItem> findItemByAreaid(Integer id) {
		return this.scmsGroupMgMapper.findItemByAreaid(id);
	}

	@Override
	public List<Supplier> findSupplierByAreaId(Integer id) {
		return this.scmsGroupMgMapper.findSupplierByAreaId(id);
	}

	@Override
	public int findOrders(String warehouseId) {
		return this.scmsGroupMgMapper.findOrders(warehouseId);
	}

}

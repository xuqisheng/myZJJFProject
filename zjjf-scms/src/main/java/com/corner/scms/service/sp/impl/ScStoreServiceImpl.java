package com.corner.scms.service.sp.impl;

import java.awt.Menu;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.beans.ScmsStore;
import com.corner.core.beans.StoreGroup;
import com.corner.core.beans.StoreGroupMember;
import com.corner.core.beans.vo.Pager;
import com.corner.core.dao.ScmsStoreMapper;
import com.corner.core.dao.StoreGroupMemberMapper;
import com.corner.core.dao.StoreMapper;
import com.corner.scms.beans.ro.ScmsStoreCondition;
import com.corner.scms.beans.vo.ScmsStoreVo;
import com.corner.scms.beans.vo.StoreVo;
import com.corner.scms.dao.ScmsStoreMgMapper;
import com.corner.scms.dao.StoreGroupMapper;
import com.corner.scms.service.impl.BaseServiceImpl;
import com.corner.scms.service.sp.ScStoreService;


/**
 * ClassName: ScStoreServiceImpl
 * @Description: 客户管理  service实现
 * @author 海灵子
 * @date 2015年12月6日
 */

@Service
public class ScStoreServiceImpl extends BaseServiceImpl implements ScStoreService{
	
	@Autowired
	private ScmsStoreMapper scmsStoreMapper;
	
	@Autowired
	private ScmsStoreMgMapper scmsStoreMgMapper;
	
	@Autowired
	private StoreGroupMapper storeGroupMapper;
	
	private StoreGroupMemberMapper storeGroupMemberMapper;
	
	
	@Override
	public Pager<ScmsStoreVo> getAllStoreByCondition(ScmsStoreCondition condition) {
		if(condition.getNameOrTelphone()!=null)condition.setNameOrTelphone(condition.getNameOrTelphone().trim());
		if(condition.getPageIndex()<0){
			condition.setPageIndex(0);
		}
		if(condition.getPageSize()<=0){
			condition.setPageSize(10);
		}
		List<ScmsStoreVo> list=this.scmsStoreMgMapper.getPageList(condition);
		int size =this.scmsStoreMgMapper.getPageListSize(condition);
		return new Pager<ScmsStoreVo>(size, list);
	}

	@Override
	public int addScmsStore(ScmsStore scmsStore) {
		scmsStore.setAddTime(new Date());
		scmsStore.setIsDelete(false);
		scmsStore.setStatus((byte) 1);
		this.scmsStoreMapper.insertSelective(scmsStore);//插入线下便利店
		StoreGroup g=new StoreGroup();
		g.setSupplierId(scmsStore.getSpId());
		StoreGroup group=storeGroupMapper.findXianXiaGroup(g);
		group.setNumber(group.getNumber()+1);
		/*//维护关系
		StoreGroupMember mem=new StoreGroupMember();
		mem.setStoreGroupID(scmsStore.getId());*/
		//跟新组表的客户数
		
		return storeGroupMapper.updateByPrimaryKeySelective(group);
	}

	@Override
	public ScmsStoreVo findById(String id) {
		
		return this.scmsStoreMgMapper.findById(Integer.parseInt(id));
	}

	@Override
	public int updateScmsStore(ScmsStore scmsStore) {
		
		return this.scmsStoreMapper.updateByPrimaryKeySelective(scmsStore);
	}

	@Override
	public int deleteById(Integer id,String supplierId) {
		StoreGroup g=new StoreGroup();
		g.setSupplierId(supplierId);
		StoreGroup group=storeGroupMapper.findXianXiaGroup(g);
		group.setNumber(group.getNumber()-1);
		storeGroupMapper.updateByPrimaryKeySelective(group);
		return this.scmsStoreMgMapper.deleteById(id);
	}

	@Override
	public Pager<StoreVo> getAllDownStoreByCondition(ScmsStoreCondition condition) {
		if(condition.getNameOrTelphone()!=null)condition.setNameOrTelphone(condition.getNameOrTelphone().trim());
		if(condition.getPageIndex()<0){
			condition.setPageIndex(0);
		}
		if(condition.getPageSize()<=0){
			condition.setPageSize(10);
		}
		List<StoreVo> list=this.scmsStoreMgMapper.getPageListDown(condition);
		int size =this.scmsStoreMgMapper.getPageListSizeDown(condition);
		return new Pager<StoreVo>(size, list);
	}
	
	

}

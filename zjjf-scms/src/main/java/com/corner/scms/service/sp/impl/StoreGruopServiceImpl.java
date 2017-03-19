package com.corner.scms.service.sp.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.beans.ScmsItem;
import com.corner.core.beans.ScmsStore;
import com.corner.core.beans.SpVoucherActiveMiddle;
import com.corner.core.beans.Store;
import com.corner.core.beans.StoreGroup;
import com.corner.core.beans.StoreGroupMember;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.core.dao.ScmsItemMapper;
import com.corner.core.dao.StoreGroupMemberMapper;
import com.corner.core.dao.StoreMapper;
import com.corner.core.utils.StringUtil;
import com.corner.scms.service.impl.BaseServiceImpl;
import com.corner.scms.beans.ro.PromotionRo;
import com.corner.scms.beans.vo.ScmsStoreVo;
import com.corner.scms.beans.vo.StoreGroupVo;
import com.corner.scms.dao.ScmsItemMgMapper;
import com.corner.scms.dao.StoreGroupMapper;
import com.corner.scms.dao.StoreMgMapper;
import com.corner.scms.service.sc.ScmsItemMgService;
import com.corner.scms.service.sp.StoreGruopService;

/**
 * 
* @ClassName: ScmsManagerMgServiceImpl 
* @Description: 商品信息处理 
* @author 孟星魂	mengxinghun@izjjf.cn
* @date 2015年12月28日 上午11:45:29 
*
*/
@Service
public class StoreGruopServiceImpl extends BaseServiceImpl implements StoreGruopService {

	private static Logger logger = LoggerFactory.getLogger(StoreGruopServiceImpl.class);

	@Autowired
	private StoreGroupMapper storeGroupMapper;
	
	@Autowired
	private StoreGroupMemberMapper storeGroupMemberMapper;
	
	@Autowired
	private StoreMgMapper storeMapper;
	
	@Override
	public Pager<StoreGroup> updateAndgetAllStoreGroupByCondition(StoreGroupVo condition) {
		this.updateXianXiagroup(condition);
		List<StoreGroup> list=this.storeGroupMapper.getAllStoreGroupByCondition(condition);
		int size=this.storeGroupMapper.getAllStoreGroupByConditionCount(condition);
		return new Pager<StoreGroup>(size,list);
	}
	
	@Override
	public void updateXianXiagroup(StoreGroupVo condition){
		StoreGroup xianxia=this.storeGroupMapper.findXianXiaGroup(condition);
				if(xianxia==null){
					List<ScmsStore>  store=this.storeGroupMapper.findNumber(condition);
					StoreGroup group=new StoreGroup();
					group.setName("线下客户组");
					group.setRemark("所有的线下便利店客户");
					group.setDate(new Date());
					group.setNumber(store.size());
					group.setSupplierId(condition.getSupplierId());
					this.storeGroupMapper.insertSelective(group);
					
				}
	}

	@Override
	public List<StoreGroup> findAllGroups(StoreGroupVo condition) {
		return this.storeGroupMapper.findAllGroups(condition);
	}

	@Override
	public void updateNumber(String storeGroupID) {
		
		this.storeGroupMapper.updateNumber(storeGroupID);
	}

	@Override
	public void updateNumberMis(String storeGroupID) {
		
		this.storeGroupMapper.updateNumberMis(storeGroupID);
	}

	@Override
	public void deleteObject(StoreGroupVo condition) {
		this.storeGroupMapper.deleteByPrimaryKey(condition.getId());
	}

	@Override
	public StoreGroup findById(StoreGroupVo condition) {
		StoreGroupVo group=this.storeGroupMapper.selectByPrimaryKey(condition.getId());
		List<Store> stores=this.storeMapper.findStores(condition.getId());
		List<Store> noGroup=this.storeMapper.findAllNoGroupStore(condition);
		group.setNoGroup(noGroup);
		group.setStores(stores);
		return group;
	}

	@Override
	public void addGroup(StoreGroupVo condition) {
		if(condition.getId().equals("")){
			condition.setId(StringUtil.getUUID());
			this.storeGroupMapper.insertSelective(condition);
		}else{
			this.storeGroupMapper.updateByPrimaryKeySelective(condition);
			this.storeMapper.deleteByGroupId(condition.getId());
		}
		if(condition.getIds()!=null){
			for(String id:condition.getIds()){
				StoreGroupMember mem=new StoreGroupMember();
				mem.setStoreGroupID(condition.getId());
				mem.setStoreId(Integer.parseInt(id));
				mem.setSupplierId(condition.getSupplierId());
				mem.setType(2);
				this.storeGroupMemberMapper.insertSelective(mem);
			}
		}
		
	}

	@Override
	public void addCustomer(StoreGroupVo condition) {
			StoreGroup g=this.storeGroupMapper.selectByPrimaryKey(condition.getId());
			g.setNumber(g.getNumber()+condition.getIds().length);
			this.storeGroupMapper.updateByPrimaryKeySelective(g);
			for(String id:condition.getIds()){
				StoreGroupMember mem=new StoreGroupMember();
				mem.setStoreGroupID(condition.getId());
				mem.setStoreId(Integer.parseInt(id));
				mem.setSupplierId(condition.getSupplierId());
				mem.setType(2);
				this.storeGroupMemberMapper.insertSelective(mem);
			}
	}

	@Override
	public int findCountNumer(String id) {
		return this.storeMapper.selectcountx(id);
	}

	@Override
	public List<StoreGroup> findGroupsByconditionalread(PromotionRo condition) {
		
		return this.storeGroupMapper.findGroupsByconditionalread(condition);
	}

	
	@Override
	public List<StoreGroup> findGroupsByconditionother(PromotionRo condition) {
		// TODO Auto-generated method stub
		return this.storeGroupMapper.findGroupsByconditionother(condition);
	}

	@Override
	public List<StoreGroup> findGroupsByconditionalready(PromotionRo condition) {
		// TODO Auto-generated method stub
		
		return this.storeGroupMapper.findGroupsByconditionalready(condition);
	}

	@Override
	public List<StoreGroup> findGroupsByconditionalreadyno(PromotionRo v) {
		// TODO Auto-generated method stub
		return this.storeGroupMapper.findGroupsByconditionalreadyno(v);
	}

	@Override
	public SpVoucherActiveMiddle findSpVoucherActiveMiddle(StoreGroupVo condition) {
		// TODO Auto-generated method stub
		return storeGroupMapper.findSpVoucherActiveMiddle(condition);
	}

	@Override
	public String findUrl() {
		// TODO Auto-generated method stub
		return this.storeGroupMapper.findUrl();
	}

	



	
}

package com.corner.kefu.service.sp.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.corner.kefu.service.callable.CallAbleService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.beans.FinWallet;
import com.corner.core.beans.SpGroup;
import com.corner.core.beans.Supplier;
import com.corner.core.beans.User;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.core.dao.SupplierMapper;
import com.corner.core.dao.UserMapper;
import com.corner.core.enums.SocktOperateType;
import com.corner.core.utils.CreateSequence;
import com.corner.core.utils.StringUtil;
import com.corner.core.utils.callable.SocktOperateLog;
import com.corner.core.utils.safe.MD5;
import com.corner.kefu.beans.ro.erp.ERPManagerRo;
import com.corner.kefu.beans.ro.sp.SupplierMgRo;
import com.corner.kefu.beans.ro.sp.SupplierRo;
import com.corner.kefu.beans.vo.RegionVo;
import com.corner.kefu.beans.vo.sp.PlantItemVo;
import com.corner.kefu.beans.vo.sp.SupplierVo;
import com.corner.kefu.dao.sp.SpGroupMgMapper;
import com.corner.kefu.dao.sp.SpPlantItemMgMapper;
import com.corner.kefu.dao.sp.SpSupplierMgMapper;
import com.corner.kefu.dao.sp.SpUserMgMapper;
import com.corner.kefu.service.sp.SpFinWalletService;
import com.corner.kefu.service.sp.SpRegionService;
import com.corner.kefu.service.sp.SpSupplierService;

@Service
public class SpSupplierServiceImpl implements SpSupplierService{
	@Autowired
	SpSupplierMgMapper spSupplierMgMapper;
	@Autowired
	SupplierMapper supplierMapper;
	
	@Autowired
	SpGroupMgMapper spGroupMgMapper;
	
	@Autowired
	SpPlantItemMgMapper spPlantItemMgMapper;
	
	@Autowired
	SpRegionService spRegionService; 
	
	@Autowired
	SpFinWalletService spFinWalletService;
	
	@Autowired
	UserMapper userMapper;
	
	@Autowired
	SpUserMgMapper spUserMgMapper;

	@Autowired
	CallAbleService callAbleService;
	
	/**
	 * 跟新供应商信息
	 * 
	 * @author Dick 2015年6月15日
	 * @Email 823882651@qq.com
	 * @Desc
	 * @param supplier
	 * @return
	 */
	@Override
	public Boolean update(Supplier supplier) {
		Supplier newSupplier = supplierMapper.selectByPrimaryKey(supplier.getId());
		if((supplier.getSupplierCode()==null || "".equals(supplier.getSupplierCode()) || supplier.getAreaId().intValue() != newSupplier.getAreaId().intValue()) && supplier.getProvince()!=null && supplier.getCity() !=null && supplier.getAreaId() != null){
			Map<String, Object> map = getSupplierCode(supplier);
			String supplierCode = (String)map.get("supplierCode");
			supplier.setSupplierCode(supplierCode);
			String sequenceNum = (String)map.get("sequenceNum");
			if(sequenceNum != null && !"".equals(sequenceNum)){
				supplier.setSequenceNum(Integer.parseInt(sequenceNum));
			}else{
				supplier.setSequenceNum(null);
			}
		}
		
		Integer res = supplierMapper.updateByPrimaryKeySelective(supplier);
		return res == 1 ? true : false;
	}

	/**
	 * 添加供应商
	 * 
	 * @author Dick 2015年6月15日
	 * @Email 823882651@qq.com
	 * @Desc
	 * @param supplier
	 * @return
	 */
	@Override
	public ModelMsg addSupplier(Supplier supplier) {
		ModelMsg msg = new ModelMsg();
		msg.setSuccess(false);
		supplier.setId(StringUtil.newUUID());
		supplier.setStatus((byte) 2);
		supplier.setIsDelete(false);
		supplier.setRegTime(new Date());
		supplier.setUpdateTime(new Date());
		if((supplier.getSupplierCode()==null || "".equals(supplier.getSupplierCode())) && supplier.getProvince()!=null && supplier.getCity() !=null && supplier.getAreaId() != null){
			Map<String, Object> map = getSupplierCode(supplier);
			String supplierCode = (String)map.get("supplierCode");
			supplier.setSupplierCode(supplierCode);
			String sequenceNum = (String)map.get("sequenceNum");
			if(sequenceNum != null && !"".equals(sequenceNum)){
				supplier.setSequenceNum(Integer.parseInt(sequenceNum));
			}else{
				supplier.setSequenceNum(null);
			}
		}
		supplier.setPassword(MD5.StringToMd5(supplier.getPassword()));
		//创建批发商钱包
		//先判断有没有该号码的User
		List<User> userList = spUserMgMapper.getUserByMobile(supplier.getMobile());
		if(userList!=null){
			if(userList.size()!=0){
				if(userList.size()!=1){
					msg.setMessage("该手机号存在多个User数据,添加批发商失败!");
					return msg;	
				}else {//userList.size()==1
					if(!StringUtils.isEmpty(userList.get(0).getSupplierId())){
						msg.setMessage("该手机号对应的User已经存在批发商数据,添加批发商失败!");
						return msg;			
					}else {//创建Supplier
						User userRo = userList.get(0);
						supplierMapper.insertSelective(supplier);
						userRo.setSupplierId(supplier.getId());
						if(StringUtils.isEmpty(userList.get(0).getWalletId())){
							//创建钱包
							FinWallet finWallet = spFinWalletService.addSupplierWallet(supplier);
							userRo.setWalletId(finWallet.getId());
						}
						userRo.setLastTime(new Date());
						userMapper.updateByPrimaryKey(userRo);
					}
				}
			}else {
				//没有User数据,则创建User
				createUserAndSupplier(supplier);
			}
		}else {
			createUserAndSupplier(supplier);
		}
		msg.setSuccess(true);
		return msg;
	}

	/**
	 * 
	* @Title: createUserAndSupplier 
	* @Description:创建User 和 批发商
	* @param @param supplier
	* @return void    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	private void createUserAndSupplier(Supplier supplier) {
		//插入批发商
		supplierMapper.insertSelective(supplier);
		//初始化批发商仓库信息
		try {
			callAbleService.socktOperateLog(SocktOperateType.Operate_20012 , supplier.getId());
		}catch (Exception e){
			System.out.println("仓库信息初始化失败");
		}

		
		//创建钱包
		FinWallet finWallet = spFinWalletService.addSupplierWallet(supplier);
		
		Date date = new Date();
		
		//新建User
		User user = new User();
		user.setId(StringUtil.newUUID());
        user.setPassword(supplier.getPassword());		
        user.setMobile(supplier.getMobile()); 
        if(supplier.getProvince()!=null){
        	user.setProvince(supplier.getProvince());
        }
        if(supplier.getCity()!=null){
        	user.setCity(supplier.getCity());
        }
        user.setRegTime(date);
        user.setUpdateTime(date);
        user.setSupplierId(supplier.getId());
        user.setWalletId(finWallet.getId());
        
        userMapper.insertSelective(user);
	}

	//返回批发商编号
	private Map<String, Object> getSupplierCode(Supplier supplier) {
		Map<String, Object> map = new HashMap<>();
		String regionStr = spRegionService.getRegionStr(supplier.getProvince(),supplier.getCity(), supplier.getAreaId());
		Integer num = spSupplierMgMapper.getMaxSequenceNum(supplier);
		if(num == null){
			num=0;
		}
		String sequenceNum = "";
		do {
			sequenceNum = CreateSequence.getSequence(num, 4);
			if(sequenceNum.contains("4")){
				num++;
				//不包含4的
				continue;
			}else{
				break;
			}
		} while (true);
		//拼接的店铺编号
		String supplierCode = (regionStr+sequenceNum).toString();
		map.put("supplierCode", supplierCode);
		map.put("sequenceNum", sequenceNum);
		return map;
	}

	/**
	 * 根据条件获取供应商
	 * 
	 * @author Dick 2015年6月15日
	 * @Email 823882651@qq.com
	 * @Desc
	 * @param supplierRo
	 * @return
	 */
	@Override
	public Pager<SupplierVo> getSuppliers(SupplierRo supplierRo) {
		List<SupplierVo> list = spSupplierMgMapper.selectSupplierSelective(supplierRo);
		int count = spSupplierMgMapper.selectSupplierSelectiveCount(supplierRo);
		return new Pager<>(count,list);
	}

	@Override
	public Supplier selectSupplierBySupplierCodee(String supplierCode) {
		return spSupplierMgMapper.selectSupplierBySupplierCode(supplierCode);
	}

	/**
	 * 通过id 查询supplier
	 * 
	 * @param supplier
	 * @return
	 */
	@Override
	public Supplier getSupplierById(String id) {
		return supplierMapper.selectByPrimaryKey(id);
	}
	
	/**
	 * 
	* Title: getSupplierProductList 
	* Description:查询批发商在所属定格下的商品列表 
	* @return
	* @throws Exception 
	* @see com.corner.kefu.service.sp.SpSupplierService#getSupplierProductList()
	 */
	@Override
	public Map<String, Object> getSupplierProductList(SupplierMgRo supplierMgRo) throws Exception {
		Map<String, Object>map = new HashMap<>();
		//查询批发商
		Supplier supplier = supplierMapper.selectByPrimaryKey(supplierMgRo.getId());
		map.put("supplier", supplier);
		
		List<SpGroup> spList = spGroupMgMapper.getSpGroupListBySupplierId(supplierMgRo);
		map.put("spList", spList);
		
		List<PlantItemVo> plList = spPlantItemMgMapper.getPlangItemBySupplier(supplierMgRo);
		map.put("plList", plList);
		
		Integer count = 0 ;
		if(plList!=null&&plList.size()!=0){
			count = spPlantItemMgMapper.getCountPlangItemBySupplier(supplierMgRo);
		}
		map.put("count", count);
		return map;
	}

	@Override
	public List<Supplier> getSupplier() throws Exception {
		return spSupplierMgMapper.getSupplier();
	}

	@Override
	public SupplierVo getSupplierVoById(Supplier supplier) throws Exception {
		return spSupplierMgMapper.getSupplierVoById(supplier);
	}

	@Override
	public List<SupplierVo> selectSupplierList(SupplierVo supplierVo) {
		return spSupplierMgMapper.selectSupplierList(supplierVo);
	}

	@Override
	public List<SupplierVo> getSupplierBySpGroupId(Integer[] spGroupIds) {
		// TODO Auto-generated method stub
		return spSupplierMgMapper.getSupplierBySpGroupId(spGroupIds);
	}
	
	@Override
	public List<SupplierVo> getSupplierBySupplierVoList(List<SupplierVo> list) {
		if(null != list && list.size() > 0)
		{
			Map<String,Object> param = new HashMap<>();
			List<String> spIds = new ArrayList<>();
			List<Integer> spGroupIds = new ArrayList<>();
			for(SupplierVo one:list){
				boolean eflag = false;
				for(String spId:spIds){
					if(one.getId().equals(spId)){
						eflag = true;
					}
				}
				if(!eflag){
					spIds.add(one.getId());
				}
				
				eflag = false;
				for(Integer spGroupId:spGroupIds){
					if(one.getSpGroupId().equals(spGroupId)){
						eflag = true;
					}
				}
				if(!eflag){
					spGroupIds.add(one.getSpGroupId());
				}
			}
			
			param.put("spIds", spIds);
			param.put("spGroupIds", spGroupIds);
			
			return spSupplierMgMapper.getSupplierByMap(param);
		}
		else
		{
			return null;
		}
	}

	
	@Override
	public void updateUserAndSupplier(User user, Supplier supplier) {
	     userMapper.updateByPrimaryKeySelective(user);
	     supplierMapper.updateByPrimaryKeySelective(supplier);
	}

	@Override
	public Supplier getSupplierByMobile(String mobile) {
		return spSupplierMgMapper.getSupplierByMobile(mobile);
	}

	/**
	 * 
	* @Title: getTreeSupplierList 
	* @Description:获取Supplier ztree批发商列表
	* @param @return
	* @return List<RegionVo>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@Override
	public List<RegionVo> getTreeSupplierList() {
		List<RegionVo> list = spSupplierMgMapper.getTreeSupplierList();
		if(list!=null&&list.size()!=0){
			for (RegionVo shen : list) {//省
				List<RegionVo> shiList = shen.getRegionList();
				if(shiList!=null&&shiList.size()!=0){
					for (RegionVo shi : shiList) {//市
						List<RegionVo> quList = shi.getRegionList();
						if(quList!=null&&quList.size()!=0){
							for (RegionVo qu : quList) {//区
								List<RegionVo> spList = qu.getRegionList();
								if(spList!=null&&spList.size()!=0){
									for (RegionVo sp : spList) {//批发部
										String areaStr = shen.getName()+"/" + shi.getName()+"/" +qu.getName();
										sp.setSupplierAreStr(areaStr);
									}
								}
							}
						}
					}
				}
			}
		}
		return list;
	}

	@Override
	public List<SupplierVo> getMnagerSupplierList(ERPManagerRo manager) {
		return spSupplierMgMapper.getMnagerSupplierList(manager);
	}
}

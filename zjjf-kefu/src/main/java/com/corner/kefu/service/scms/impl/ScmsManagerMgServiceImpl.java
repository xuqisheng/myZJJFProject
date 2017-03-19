package com.corner.kefu.service.scms.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.beans.MaWallet;
import com.corner.core.beans.ScmsGroup;
import com.corner.core.beans.ScmsGroupMapKey;
import com.corner.core.beans.ScmsManager;
import com.corner.core.beans.ScmsWarehouse;
import com.corner.core.beans.Supplier;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.core.dao.MaWalletMapper;
import com.corner.core.dao.ScmsGroupMapMapper;
import com.corner.core.dao.ScmsManagerMapper;
import com.corner.core.dao.ScmsShoppingCartMapper;
import com.corner.core.dao.SupplierMapper;
import com.corner.core.utils.JSONUtil;
import com.corner.core.utils.StringUtil;
import com.corner.core.utils.safe.MD5;
import com.corner.kefu.beans.ro.scms.ScmsItemMgRo;
import com.corner.kefu.beans.ro.scms.ScmsManagerMgRo;
import com.corner.kefu.dao.ScmsGroupMapMgMapper;
import com.corner.kefu.dao.ScmsGroupMgMapper;
import com.corner.kefu.dao.ScmsItemMgMapper;
import com.corner.kefu.dao.ScmsManagerMgMapper;
import com.corner.kefu.dao.ScmsSupplierMgMapper;
import com.corner.kefu.service.scms.ScmsManagerMgService;
import com.corner.kefu.utils.BeanUtil;
/**
 * 
* @ClassName: ScmsManagerMgServiceImpl 
* @Description: 经销商信息处理 
* @author 孟星魂	mengxinghun@izjjf.cn
* @date 2015年12月28日 上午11:45:29 
*
 */
@Service
public class ScmsManagerMgServiceImpl implements ScmsManagerMgService {

	private static Logger logger = LoggerFactory.getLogger(ScmsManagerMgServiceImpl.class);

	@Autowired
	ScmsManagerMgMapper scmsManagerMgMapper;
	@Autowired
	ScmsShoppingCartMapper scmsShoppingCartMapper;
	@Autowired
	ScmsManagerMapper scmsManagerMapper;
	
	@Autowired
	ScmsGroupMapMapper scmsGroupMapMapper;
	@Autowired
	MaWalletMapper maWalletMapper;
	@Autowired
	ScmsGroupMapMgMapper scmsGroupMapMgMapper;
	@Autowired
	ScmsGroupMgMapper scmsGroupMgMapper;
	@Autowired
	SupplierMapper supplierMapper;
	@Autowired
	ScmsSupplierMgMapper scmsSupplierMgMapper;
	@Autowired
	ScmsItemMgMapper scmsItemMgMapper;
	
	@Override
	public Pager<ScmsManager> getScmsManagerPageList(ScmsManagerMgRo command) {
		logger.info("经销商信息查询开始:"+JSONUtil.objectToJSONString(command));
		
		/**排列顺序按照添加日期进行排序**/
		command.setSortName("updateTime");
		command.setSortOrder("desc");
		
		command.setIsDelete(false);
		command.setStatus(Byte.parseByte("2"));
		
		List<ScmsManager> list = scmsManagerMgMapper.getPageList(command);
		int size = scmsManagerMgMapper.getPageListSize(command);
		return new Pager<ScmsManager>(size,list);
	}

	@Override
	public ModelMsg addScmsManager(ScmsManagerMgRo command) {
		logger.info("添加经销商信息开始：" + JSONUtil.objectToJSONString(command));
		
		ScmsManager scmsManager = new ScmsManager();
		if(StringUtil.stringIsNullOrEmpty(scmsManager.getId()))
			scmsManager.setId(StringUtil.getUUID());
		if(command instanceof ScmsManager)
			scmsManager = (ScmsManager)command;
		scmsManager.setManagerName(command.getManagerName());
		scmsManager.setManagerCode(command.getManagerCode());
		scmsManager.setRegTime(new Date());
		scmsManager.setUpdateTime(new Date());
		scmsManager.setStatus(Byte.parseByte("2"));	//默认审核通过
		scmsManager.setMinimum(command.getMinimum());
		scmsManager.setIsDelete(false);
		scmsManager.setMobile(command.getMobile());
		scmsManager.setCity(command.getCity());
		scmsManager.setEmail(command.getEmail());
		scmsManager.setAreaId(command.getAreaId());
		scmsManager.setBankCardurl(command.getBankCardurl());
		scmsManager.setBankName(command.getBankName());
		scmsManager.setBankNum(command.getBankNum());
		scmsManager.setBranderName(command.getBranderName());
		scmsManager.setBranderTel(command.getBranderTel());
		scmsManager.setBsCircleId(command.getBsCircleId());
		scmsManager.setCallNum(command.getCallNum());
		scmsManager.setManagerAddress(command.getManagerAddress());
		scmsManager.setManagerMark(command.getManagerMark());
		
		/**密码MD5加密**/
		scmsManager.setPassword(MD5.StringToMd5(command.getPassword()));
		/**经销区域信息**/
		int result = scmsManagerMapper.insertSelective(scmsManager);
		if(result == 0)
			return new ModelMsg(false, "登记经销商失败");
		MaWallet maWallet = new MaWallet();
		maWallet.setId(scmsManager.getId());
		maWallet.setIsDelete(false);
		maWallet.setStatus(Byte.valueOf("1"));
		maWallet.setWallet(new BigDecimal("0"));
		maWallet.setWalletAddTime(new Date());
		maWallet.setWalletUpdateTime(new Date());
		maWalletMapper.insert(maWallet);
		if(result == 0)
			return new ModelMsg(false, "登记经销商失败");
		ScmsGroupMapKey groupMapKey = new ScmsGroupMapKey();
		for (int i = 0; i < command.getGroupIds().length; i++) {
			if(StringUtil.stringIsNullOrEmpty(command.getGroupIds()[i]))
				continue;
			groupMapKey.setGroupId(Integer.parseInt(command.getGroupIds()[i]));
			groupMapKey.setManagerId(scmsManager.getId());
			result = scmsGroupMapMapper.insertSelective(groupMapKey);
			if(result == 0)
				return new ModelMsg(false, "增加区域失败");
		}
		return new ModelMsg(true , "登记经销商成功");
	}

	@Override
	public ModelMsg updateScmsManager(ScmsManagerMgRo command) {
		ScmsManager scmsManager = (ScmsManager)command;
		scmsManager.setUpdateTime(new Date());
		if(!StringUtil.stringIsNullOrEmpty(command.getPassword()))
			scmsManager.setPassword(MD5.StringToMd5(command.getPassword()));
		int result = scmsManagerMapper.updateByPrimaryKeySelective(scmsManager);
		
		if(result == 0)
			return new ModelMsg(false, "更新经销商失败");
		ScmsItemMgRo itemMgRo = new ScmsItemMgRo();
		itemMgRo.setManagerId(command.getId());
		/**获取数据库区域关联信息列表**/
		List<ScmsGroupMapKey> groupMapKeys = scmsGroupMapMgMapper.findByManagerId(command.getId());
		for (ScmsGroupMapKey scmsGroupMapKey : groupMapKeys) {
			boolean isFind = false;
			for (int i = 0; i < command.getGroupIds().length; i++) {
				if(scmsGroupMapKey.getGroupId().equals(Integer.parseInt(command.getGroupIds()[i]))){
					isFind = true;
					break;
				}
			}
			if(!isFind){
				itemMgRo.setGroupId(scmsGroupMapKey.getGroupId().toString());
				if(scmsItemMgMapper.getPageListSize(itemMgRo) !=0)
					return new ModelMsg(false, "经销商信息修改成功,区域信息修改失败，对应区域下有商品信息");
			}
		}
		result = scmsGroupMapMgMapper.deleteByManagerId(command.getId());
		if(result == 0)
			return new ModelMsg(false, "更新经销商失败");
		ScmsGroupMapKey groupMapKey = new ScmsGroupMapKey();
		for (int i = 0; i < command.getGroupIds().length; i++) {
			groupMapKey.setGroupId(Integer.parseInt(command.getGroupIds()[i]));
			groupMapKey.setManagerId(scmsManager.getId());
			result = scmsGroupMapMapper.insertSelective(groupMapKey);
			if(result == 0)
				logger.info("添加区域失败！");
		}
		
			return new ModelMsg(true , "更新经销商成功");
	}

	@Override
	public ScmsManagerMgRo findById(String id) {
		ScmsManager scmsManager = scmsManagerMapper.selectByPrimaryKey(id);
		ScmsManagerMgRo command = BeanUtil.toObject(ScmsManagerMgRo.class, scmsManager);
		//经销区域信息获取
		List<ScmsGroup> groups = scmsGroupMgMapper.findScmsGroupByManagerId(id);
		if(groups == null || groups.size() == 0){
			logger.error("未获取到经销区域信息");
			return command;
		}
		command.setScmsGroups(groups);
		return command;
	}

	@Override
	public ModelMsg checkManagerCode(ScmsManagerMgRo command) {
		/**防止有其他参数参杂，新建一个只放经销商编号**/
		ScmsManagerMgRo managerMgRo = new ScmsManagerMgRo();
		managerMgRo.setManagerCode(command.getManagerCode());
		managerMgRo.setMobile(command.getMobile());
		managerMgRo.setUserName(command.getUserName());
		int size = scmsManagerMgMapper.getPageListSize(managerMgRo);
		if(size == 0)
			return new ModelMsg(true, "无经销商编号信息");
		else{
			return new ModelMsg(false , "信息录入重复");
		}
	}

	
	@Override
	public ModelMsg deleteManager(String id) {
		/**物理删除**/
//		int result = scmsManagerMapper.deleteByPrimaryKey(command.getId());
		ScmsItemMgRo command = new ScmsItemMgRo();
		command.setManagerId(id);
		int size = scmsItemMgMapper.getPageListSize(command);
		if(size == 0){
			/**修改为删除状态**/
			ScmsManager scmsManager = new ScmsManager();
			scmsManager.setId(id);
			scmsManager.setIsDelete(true);
			int result = scmsManagerMapper.updateByPrimaryKeySelective(scmsManager);
			if(result == 0)
				return new ModelMsg(false, "删除失败");
			else
				return new ModelMsg(true , "删除成功");
		}
		return new ModelMsg(false, "该经销商下还在出售商品，不能删除");
	}

	@Override
	public ModelMsg updateSupplier(Supplier command) {
		command.setUpdateTime(new Date());
		if(0 == command.getBsCircleId()){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("spId", command.getId());
			scmsItemMgMapper.deleteScmsShoppingCareBySpId(map);
		}
		if (supplierMapper.updateByPrimaryKeySelective(command) == 0)
			return  new ModelMsg(false , "删除失败");
		return new ModelMsg(true , "修改成功");
	}

	@Override
	public Pager<Supplier> getSupplierPageList(ScmsManagerMgRo command) {
		/**排列顺序按照修改日期进行排序**/
		command.setSortName("updateTime");
		command.setSortOrder("desc");
		List<Supplier> list = scmsSupplierMgMapper.getPageList(command);
		int size = scmsSupplierMgMapper.getPageListSize(command);
		return new Pager<Supplier>(size,list);
	}

	/*	获取所有的经销商信息
	* <p>Title: getAllScmsManager</p> 
	* <p>Description: </p> 
	* @return 
	* @see com.corner.kefu.service.scms.ScmsManagerMgService#getAllScmsManager() 
	*/ 
	@Override
	public List<ScmsManager> getAllScmsManager(ScmsManagerMgRo command) {
		return scmsManagerMgMapper.getAllScmsManager(command);
	}
}

package com.corner.rpc.salesman.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.rpc.salesman.api.service.ShopService;
import com.corner.rpc.salesman.dao.DictMapper;
import com.corner.rpc.salesman.dao.LinePlansMapper;
import com.corner.rpc.salesman.dao.ShopMapper;
import com.corner.rpc.salesman.dao.SpGroupLineMapper;
import com.corner.rpc.salesman.dao.UserMapper;
import com.corner.rpc.salesman.model.Shop;
import com.corner.rpc.salesman.model.SpGroupLine;
import com.corner.rpc.salesman.utils.GeneratorSeqUtils;
import com.corner.salesman.commons.persistence.Page;
import com.corner.salesman.commons.utils.Constants;
import com.corner.salesman.commons.utils.DateUtils;
import com.corner.salesman.commons.utils.JedisUtils;
import com.corner.salesman.commons.utils.ObjectUtils;
import com.corner.salesman.commons.utils.UuidUtil;
import com.google.common.collect.Lists;

@Service("shopService")
public class ShopServiceImpl implements ShopService {
	
	@Autowired
	private ShopMapper shopMapper;
	@Autowired
	private DictMapper dictMapper;
	@Autowired
	private SpGroupLineMapper spgLineMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private LinePlansMapper linePlansMapper;
	
	/**
	 * 根据ID查询条件查询经验者信息
	 * @param shop
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Shop findShopById(Map<String,Object> paramMap) throws Exception{
		
		Shop shop = shopMapper.selectByPrimaryKey(paramMap);
		if(null != shop){
			
			/*//回填店铺所属定格路线信息
			Map<String,String> map = shopMapper.getSpGroupAndLineName(shopId);
			if(null != map){
				shop.setSpGroupName(map.get("spGroupName"));
				shop.setLineName(map.get("lineName"));
			}else{
				shop.setSpGroupName("");
				shop.setLineName("");
			}*/
			
			//将图片切割放于list中
			List picList = new ArrayList();
			String picUrl = shop.getPicUrl();
			if(StringUtils.isNotBlank(picUrl)){
				String[] picItem = picUrl.split(",");
				for (int i = 0; i < picItem.length; i++) {
					//如果图片url不为空则拼接完整的url地址
					picList.add(picItem[i]);
				}
			}
			shop.setPicList(picList);
			//将picUrl清空
			shop.setPicUrl(null);
		}
		
		return shop;
	}
	
	/**
	 * 根据查询条件查询经验者信息
	 * @param shop
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Shop> findShopList(Shop shop) throws Exception{
		return null;
	}
	
    /**
     * 添加经营者信息方法
     * @param shop
     * @return
     */
	@Override
    public int addShopInfo(Shop shop) throws Exception{
		//0、生成这个门店的门店ID
		String shopId = UuidUtil.get32UUID();
		String createTime = DateUtils.dateToString(new Date(), DateUtils.DATETIME_FORMAT);
		shop.setShopId(shopId);
		shop.setCreateBy(shop.getUserId());
		shop.setCreateTime(createTime);
		shop.setUpdateBy(shop.getUserId());
		shop.setUpdateTime(createTime);
		//设置生成店铺编码（阿街系统自定义的编码，同店宝不一样）
		String shopNo = GeneratorSeqUtils.getShopNoSeq();
		//TODO 需要添加存在校验
		shop.setShopNo(shopNo);
		
    	//当修改的时候如果路线ID不为空时候，需要删除原来定格路线关系，再insert当前最新映射关系表
		String lineId = shop.getLineId();
    	this.bindShopLine(lineId, shopId);
    	
		return shopMapper.insertSelective(shop);
	}
	
    /**
     * 修改经营者信息方法
     * @param shop
     * @return
     */
	@Override
    public int updateShopInfo(Shop shop) throws Exception{
    	String createTime = DateUtils.dateToString(new Date(), DateUtils.DATETIME_FORMAT);
    	shop.setUpdateBy(shop.getUserId());
    	shop.setUpdateTime(createTime);
    	String shopId = shop.getShopId();
    	String lineId = shop.getLineId();
    	//当修改的时候如果路线ID不为空时候，需要删除原来定格路线关系，再insert当前最新映射关系表
    	this.bindShopLine(lineId, shopId);
		return shopMapper.updateByPrimaryKeySelective(shop);
	}
	
	/**
	 * 绑定店铺与路线的关系
	 * @param lineId
	 * @param shopId
	 */
	public void bindShopLine(String lineId,String shopId){
		
		if(StringUtils.isNotBlank(lineId) && StringUtils.isNotBlank(shopId)){
        	SpGroupLine spgLine = new SpGroupLine();
        	spgLine.setShopId(shopId);
        	spgLine.setLineId(lineId);
        	//删除原来关系
        	spgLineMapper.deleteLineShopMapperByShopId(shopId);
        	//插入新的映射关系
        	spgLineMapper.insertLineShopMapper(spgLine);
    	}
	}
    
    /**
     * 根据业务员ID 查询该业务员的相关门店
     * @param shop
     * @return
     */
	@Override
    public List<Shop> findShopListByUserId(Shop shop) throws Exception{
		return shopMapper.findShopListByUserId(shop);
	}
    
    /**
     * 根据业务员ID 查询该业务员的相关门店(分页方法)
     * @param shop
     * @return
     */
	@Override
	public Page<Shop> findShopListByUserId(Page<Shop> page, Shop shop) throws Exception{
		return null;
	}
    
    /**
     * 查询店宝新增的客户信息（供同步店宝数据使用）
     * @param shopInfo
     * @return
     */
	@Override
    public List<Shop> queryAddToShopList(Map<String,Object> paramMap) throws Exception{
    	return shopMapper.queryAddToShopList(paramMap);
    }
    
	/**
	 * 检查对应的定格是否已经存在
	 */
	@Override
	public int checkShopIsExist(String shopNo) throws Exception {
		return shopMapper.checkShopIsExist(shopNo);
	}
	
	/**
	 * 根据店铺ID获取对应管理定格和线路名称
	 * @param shopId
	 * @return
	 * @throws Exception
	 */
    public Map<String,String> getSpGroupAndLineName(String shopId) throws Exception{
    	return shopMapper.getSpGroupAndLineName(shopId);
    }
    
    /**
     * 
     * @param userId
     * @param list
     * @throws Exception
     */
    @Override
    public void batchSaveShop(String userId,List<Shop> list) throws Exception{
		
    	String createTime = DateUtils.dateToString(new Date(), DateUtils.DATETIME_FORMAT);
    	//循环保存list客户信息
    	if(null != list && !list.isEmpty()){
    		for(Shop shop : list){
    			String shopId = UuidUtil.get32UUID();
    			String shopType = shop.getShopType();
    			
    			//如果shopType类型为非数字字符串，则需要映射取值填充
    			if(!ObjectUtils.isInteger(shopType)){
    				Map<String,String> map = this.getShopTypeMap();
    				String typeVal = map.get(shopType);
    				shop.setShopType(typeVal);
    			}
    			
    			//5、生成这个门店的门店ID
    			shop.setShopId(shopId);
    			shop.setCreateBy(userId);
    			shop.setCreateTime(createTime);
    			shop.setUpdateBy(userId);
    			shop.setUpdateTime(createTime);
    			shopMapper.insertSelective(shop);
    		}
    	}    	
    }
    
	/**
	 * 为excel解析准备转换信息
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Map<String,String> getShopTypeMap()throws Exception{
		Map<String, String> shopTypeMap = (Map<String, String>)JedisUtils.getObject(Constants.SHOP_TYPE_CONVERT_MAP);
		if(null == shopTypeMap){
			shopTypeMap = new HashMap<String,String>(); 
			
			List<Map<String, String>> shopTypeList = dictMapper.getDictListByType("shop_type");
			for(Map<String, String> map: shopTypeList){
				String key = map.get("label");
				String val = map.get("value");
				shopTypeMap.put(key, val);
			}
			
			JedisUtils.setObject(Constants.SHOP_TYPE_CONVERT_MAP,shopTypeMap,0);
		}
		return shopTypeMap;
	}
	
	/**
	 * 根据部门 或 用户ID 获取对应部门或个人归属的路线信息
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@Override
    public List<HashMap<String,Object>> getMyShopLineList(Shop shop) throws Exception{
		Map<String,Object> paraMap = new HashMap<String,Object>();
		
		String userId = shop.getUserId();
		String deptId = shop.getDeptId();
		
		//合适当前用户是否为管理员，如果是则按照部门纬度查询，反之则查询用户纬度数据
		int num = userMapper.checkIsLeader(userId);
		if(num>0){
			paraMap.put("deptId", deptId);
		}else{
			paraMap.put("userId", userId);
		}
		
		List<HashMap<String,Object>> list = userMapper.getUserListHashMap(paraMap);
		if(null != list && !list.isEmpty()){
			for(Map<String,Object> retMap : list){
				if(null == retMap){
					continue;
				}
				String uId = retMap.get("userId")+"";
				
				if("ALL".equals(uId)){
					List<HashMap<String, String>> lineList = Lists.newArrayList();
					HashMap<String, String> lineMap = new HashMap<String, String>();
					lineMap.put("lineId", "ALL");
					lineMap.put("lineName", "全部线路");
					lineList.add(lineMap);
					retMap.put("lineList", lineList);
				}else{
					List<HashMap<String, Object>> lineList = shopMapper.getMyShopLineList(uId);
					retMap.put("lineList", lineList);
				}
			}
		}
		
		return list;
	}

    /**
     * 查询我的客户列表
     * @param shopInfo
     * @return
     */
	@Override
    public Page<Shop> getMyShopList(Page<Shop> page, Shop shop) throws Exception{
		// 设置分页参数
    	shop.setPage(page);
		// 执行分页查询
    	shop.setWeek(DateUtils.getChineseWeekday());
		List<Shop> list = shopMapper.getMyShopList(shop);
		if(null != list && !list.isEmpty()){
			page.setList(list);
		}
		return page;
	}
	
    /**
     * 批量将指定业务员绑定店铺
     * @param shop
     * @throws Exception
     */
	@Override
    public void bacthBindShop(Shop shop) throws Exception{
    	
		StringBuffer sbShop = new StringBuffer();
		String shopIds = shop.getShopIds();
		//店铺集合的ID不为空，则按照规则拼接成in的查询条件带到sql中
		if(StringUtils.isNotBlank(shopIds)){
			String[] shopIdSet = shopIds.split(",");
			for (int i = 0; i < shopIdSet.length; i++) {
				if(i<shopIdSet.length-1){
					sbShop.append("'").append(shopIdSet[i]).append("'").append(",");
				}else{
					sbShop.append("'").append(shopIdSet[i]).append("'");
				}
			}
			
			shop.setShopIds(sbShop.toString());
		}
		
		//删除原来与店铺有关系的相关业务表数据
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("shopNos", sbShop.toString());
		linePlansMapper.deleteShopLineMapperByShopNos(paramMap);
		//批量将之前拼接的店铺分配给指定业务员
    	shopMapper.bacthBindShop(shop);
    }
	
    /**
     * 根据查询条件获取店铺坐标信息
     * @param shop
     * @return
     */
	@Override
    public List<HashMap<String,Object>> getShopSiteList(Shop shop) throws Exception{
    	return shopMapper.getShopSiteList(shop);
    }
    
    
    /**
     * 如果客户经纬度为空，则更新客户坐标信息
     * @param paraMap
     * @return
     */
	@Override
    public void updateShopCoordinate(Map<String,Object> paraMap) throws Exception{
    	shopMapper.updateShopCoordinate(paraMap);
    }
    
    /**
     * 根据用户ID查询对应业务员负责的店铺
     * @param userId
     * @return
     */
	@Override
    public List<String> getShopNoList(String userId) throws Exception{
    	return shopMapper.getShopNoList(userId);
    }
	
	/**
     * 根据部门ID查询对应部门业务员负责全部店铺编码
     * @param deptId
     * @return
     */
	@Override
    public List<String> getShopNoListByDeptId(String deptId) throws Exception{
		return shopMapper.getShopNoListByDeptId(deptId);
    }
	
    /**
     * 获取tbl_shop_t表中全部有效的店铺编码列表
     * @param 
     * @return
     */
	@Override
    public List<String> getAllShopNoList() throws Exception{
		return shopMapper.getAllShopNoList();
    }
	
    /**
     * 查询已经绑定的业务员的客户关系列表
     * @return
     */
	@Override
    public Page<Shop> getStoreRelationList(Page<Shop> page, Shop shop) throws Exception{
		// 设置分页参数
    	shop.setPage(page);
		// 执行分页查询
		List<Shop> list = shopMapper.getStoreRelationList(shop);
		if(null != list && !list.isEmpty()){
			page.setList(list);
		}
		return page;
    }
}

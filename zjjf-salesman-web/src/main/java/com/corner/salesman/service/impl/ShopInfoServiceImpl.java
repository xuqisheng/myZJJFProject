package com.corner.salesman.service.impl;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.druid.pool.DruidPooledConnection;
import com.corner.core.beans.vo.Pager;
import com.corner.rpc.salesman.api.service.DictService;
import com.corner.rpc.salesman.api.service.ShopService;
import com.corner.rpc.salesman.model.Shop;
import com.corner.rpc.shop.api.service.StoreService;
import com.corner.rpc.shop.model.Store;
import com.corner.salesman.commons.utils.UuidUtil;
import com.corner.salesman.dao.ShopInfoMapper;
import com.corner.salesman.model.ShopInfo;
import com.corner.salesman.service.ShopInfoService;
import com.google.common.collect.Maps;
/**
 * 定格与路线数据业务层
 * @author Administrator
 *
 */
@Service
public class ShopInfoServiceImpl implements ShopInfoService {
	
	private static Logger logger = LoggerFactory.getLogger(ShopInfoServiceImpl.class);
	@Autowired
	private ShopInfoMapper shopInfoMapper;
	@Autowired
	private DictService dictService;
	@Autowired
	private StoreService storeService;
	@Autowired
	private ShopService shopService;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
    private static DruidPooledConnection con = null;

	@Override
	public Pager<ShopInfo> getShopInfoPageList(ShopInfo shopVO) throws Exception {
		List<ShopInfo> list = shopInfoMapper.getShopPageList(shopVO);
		int size = shopInfoMapper.getShopPageSize(shopVO);
		return new Pager<ShopInfo>(size, list);
	}
	
	@Override
	public Pager<ShopInfo> getChoicePageList(ShopInfo shopVO) throws Exception {
		
		StringBuffer sbShop = new StringBuffer();
		String shopIds = shopVO.getShopIds();
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
			
			shopVO.setShopIds(sbShop.toString());
		}
		
		
		List<ShopInfo> list = shopInfoMapper.getChoicePageList(shopVO);
		int size = shopInfoMapper.getChoicePageSize(shopVO);
		return new Pager<ShopInfo>(size, list);
	}

	@Override
	public int addShopInfo(ShopInfo shopVO) throws Exception {
		return shopInfoMapper.insertSelective(shopVO);
	}

	@Override
	public int updateShopInfo(ShopInfo shopVO) throws Exception {
		return shopInfoMapper.updateByPrimaryKeySelective(shopVO);
	}

	@Override
	public int deleteShopInfo(ShopInfo shopVO) throws Exception {
		return shopInfoMapper.deleteShopById(shopVO.getShopId());
	}

	@Override
	public ShopInfo findShopInfoById(String shopId) throws Exception {
		return shopInfoMapper.selectByPrimaryKey(shopId);
	}

	/**
	 * 检查对应的定格是否已经存在
	 */
	@Override
	public int checkShopIsExist(String shopNo) throws Exception {
		return shopInfoMapper.checkShopIsExist(shopNo);
	}
	
	@Override
	public int updateBindSpgroupShop(ShopInfo record) throws Exception{
		return shopInfoMapper.updateBindSpgroupShop(record);
	}
	
	/**
	 * 批量同步店宝店铺信息到阿街系统
	 * @param shopVO
	 * @throws Exception
	 */
	@Override
	public void batchSyncShop(ShopInfo shopVO) throws Exception{
		//1、查询店宝客户信息
		Store store= new Store();
		String updateBy = shopVO.getUpdateBy();
		String queryType = shopVO.getQueryType();
		store.setQueryType(queryType);
		List<Store> shopList = storeService.querySyncStoreList(store);
		
		//2、检查如果没有在阿街库，则insert到表中反之跳过
		if(null !=shopList && !shopList.isEmpty()){
			/*for (Store storeVO : shopList) {
				//方案一、逐一检查是否满足该insert条件
				String shopNo = storeVO.getSupplierCode();
				int num = shopService.checkShopIsExist(shopNo);
				if(num == 0){
					Shop shop = new Shop();
					
					shopService.addShopInfo(shop);
				}
			}*/
			
			//方案二、批量insert到临时表，在批量检查插入
			insertBatch(shopList,updateBy);
		}
	}
	
	 /** 
     * 批处理执行预处理SQL测试 
     * 
     * @param m 批次 
     * @param n 每批数量 
     * @throws Exception 异常时抛出 
     */ 
    @SuppressWarnings("resource")
	public void insertBatch(List<Store> shopList,String updateBy) throws Exception { 
            Long start = System.currentTimeMillis(); 
            
            DruidPooledConnection conn = null;
            PreparedStatement pstmt = null;
            List<Shop> list = null;
             
            try{
            	int i = 0;
                //从池中获取连接 
        		conn = this.getConnection(); 
                conn.setAutoCommit(false); 
                
                //删除原来表数据
                pstmt = conn.prepareStatement("DELETE FROM tbl_store_tmp_t");
                pstmt.executeUpdate();
                conn.commit(); 
                
                //插入到表中
                String sql = "INSERT INTO tbl_store_tmp_t (supplierCode,NAME,contact,tel,mobile,provinceId,cityId,areaId,province,city,area,address,spGroupId,licenseNum,id)VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
                pstmt = conn.prepareStatement(sql); 
                for (Store store : shopList) { 
                		i++;
                		
                		Integer provinceId = store.getProvinceId();
                		Integer cityId = store.getCityId();
                		Integer areaId = store.getAreaId();
                		Integer spGroupId = store.getSpGroupId();
                		Integer storeId = store.getId();
                		
                        pstmt.setString(1, store.getSupplierCode()); 
                        pstmt.setString(2, store.getName()); 
                        pstmt.setString(3, store.getContact()); 
                        pstmt.setString(4, store.getTel()); 
                        pstmt.setString(5, store.getMobile()); 
                        pstmt.setInt(6, provinceId == null?0:provinceId); 
                        pstmt.setInt(7, cityId == null?0:cityId); 
                        pstmt.setInt(8, areaId == null?0:areaId); 
                        pstmt.setString(9, store.getProvince()); 
                        pstmt.setString(10, store.getCity()); 
                        pstmt.setString(11, store.getArea()); 
                        pstmt.setString(12, store.getAddress()); 
                        pstmt.setInt(13, spGroupId == null?0:spGroupId);
                        pstmt.setString(14, store.getLicenseNum()); 
                        pstmt.setInt(15, storeId == null?0:storeId);
                        //加入批处理 
                        pstmt.addBatch(); 
                        
                        if(i%500==0){
                        	conn.commit(); 
                        }
                } 
                pstmt.executeBatch();    //执行批处理 
                conn.commit(); 
                pstmt.clearBatch();        //清理批处理 
                Long end1 = System.currentTimeMillis(); 
                //System.out.println("============批量111111执行"+shopList.size()+"条Insert操作，共耗时：" + (end1 - start) / 1000f + "秒！"); 
                logger.info("============批量111111执行"+shopList.size()+"条Insert操作，共耗时：" + (end1 - start) / 1000f + "秒！");
                
                //查询所有客户表的客户编码，拼接客户编码做查询条件
                StringBuffer sbShop = new StringBuffer();
                Map<String,Object> paramMap = Maps.newHashMap();
                
                List<String> shopNoList = shopService.getAllShopNoList();
        		if(null != shopNoList && !shopNoList.isEmpty()){
        			for (int j = 0; j < shopNoList.size(); j++) {
        				if(j<shopNoList.size()-1){
        					sbShop.append("'").append(shopNoList.get(j)).append("'").append(",");
        				}else{
        					sbShop.append("'").append(shopNoList.get(j)).append("'");
        				}
        			}
        			paramMap.put("shopNos",sbShop.toString());
        		}
                
                //比对店宝新增数据是否在阿街客户信息表中，如果不包含其中，则追加到客户信息表中
                list = shopService.queryAddToShopList(paramMap);
                //System.out.println("================共有："+list.size()+"数据为店宝新增数据");
                logger.info("================共有："+(list==null?0:list.size())+"数据为店宝新增数据");
                if(null != list && !list.isEmpty()){
                	//插入到表中
                    StringBuffer shopSql = new StringBuffer();
                    shopSql.append("INSERT INTO tbl_shop_t (provinceId,cityId,areaId,province,city,area,shopNo,");
                    shopSql.append("shopName,bossName,telephone,bossTel,shopAddress,spGroupId,isRegister,");
                    shopSql.append("shopId,registerTel,shopLicense,storeId,createBy,updateBy,createTime,updateTime)");
                    shopSql.append("VALUES (?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?,?,now(),now())");
                    pstmt = conn.prepareStatement(shopSql.toString()); 
                    
                	for(int j=0;j<list.size();j++){
                		 Shop shop = list.get(j);
                         pstmt.setInt(1, shop.getProvinceId()); 
                         pstmt.setInt(2, shop.getCityId()); 
                         pstmt.setInt(3, shop.getAreaId()); 
                         pstmt.setString(4, shop.getProvince()); 
                         pstmt.setString(5, shop.getCity()); 
                         pstmt.setString(6, shop.getArea()); 
                		 pstmt.setString(7, shop.getShopNo()); 
                         pstmt.setString(8, shop.getShopName()); 
                         pstmt.setString(9, shop.getBossName()); 
                         pstmt.setString(10, shop.getTelephone()); 
                         pstmt.setString(11, shop.getBossTel()); 
                         pstmt.setString(12, shop.getShopAddress()); 
                         pstmt.setString(13, shop.getSpGroupId()); 
                         pstmt.setString(14, shop.getIsRegister()); 
                         pstmt.setString(15, UuidUtil.get32UUID());
                         pstmt.setString(16, shop.getRegisterTel());
                         pstmt.setString(17, shop.getShopLicense());
                         pstmt.setInt(18, shop.getStoreId()); 
                         pstmt.setString(19, updateBy);
                         pstmt.setString(20, updateBy);
                         
                         //加入批处理 
                         pstmt.addBatch(); 
                         
                         if(i%1000==0){
                         	conn.commit(); 
                         }
                	}
                    pstmt.executeBatch();    //执行批处理 
                    conn.commit(); 
                    pstmt.clearBatch();        //清理批处理 
                }
                
            }catch(Exception e){
            	e.printStackTrace();
            }finally{
            	if (null != pstmt){
            		pstmt.close();
            	}
            	if (null != con){
                    con.close();
            	}
            }
            Long end = System.currentTimeMillis(); 
            //System.out.println("============批量222222222执行"+list.size()+"条Insert操作，共耗时：" + (end - start) / 1000f + "秒！"); 
            logger.info("============批量222222222执行"+(list==null?0:list.size())+"条Insert操作，共耗时：" + (end - start) / 1000f + "秒！");
    } 
	
   public DruidPooledConnection getConnection() throws SQLException, ClassNotFoundException, IOException {
        if (null == con || con.isClosed()) {
        	con = (DruidPooledConnection)jdbcTemplate.getDataSource().getConnection();
        }
        return con;
    }
   
}

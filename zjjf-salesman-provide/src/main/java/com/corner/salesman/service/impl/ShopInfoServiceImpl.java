package com.corner.salesman.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.corner.salesman.common.utils.DateUtils;
import com.corner.salesman.common.utils.IdGen;
import com.corner.salesman.common.utils.StringUtils;
import com.corner.salesman.commons.persistence.Page;
import com.corner.salesman.dao.ProprietorMapper;
import com.corner.salesman.dao.SequenceMapper;
import com.corner.salesman.dao.ShopInfoMapper;
import com.corner.salesman.dao.UserMapper;
import com.corner.salesman.model.ShopInfo;
import com.corner.salesman.model.User;
import com.corner.salesman.service.ShopInfoService;

/**
 * 创建时间：2015-1-27 下午5:22:59
 * 
 * @author andy
 * @version 2.2
 */
@Service("shopInfoService")
@Transactional(readOnly = true)
public class ShopInfoServiceImpl implements ShopInfoService {
	
	//private static final Logger logger = LoggerFactory.getLogger(ShopInfoServiceImpl.class);

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private ProprietorMapper proprietorMapper;
	@Autowired
	private ShopInfoMapper shopInfoMapper;
	@Autowired
	private SequenceMapper sequenceMapper;
	
	
	
	/**
	 * 根据ID查询条件查询经验者信息
	 * @param shopInfo
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ShopInfo findShopInfoById(String shopId) throws Exception{
		
		ShopInfo shopInfo = shopInfoMapper.selectByPrimaryKey(shopId);
		//将图片切割放于list中
		List picList = new ArrayList();
		String picUrl = shopInfo.getPicUrl();
		if(StringUtils.isNotBlank(picUrl)){
			String[] picItem = picUrl.split(",");
			for (int i = 0; i < picItem.length; i++) {
				//如果图片url不为空则拼接完整的url地址
				picList.add(picItem[i]);
			}
		}
		
		if(null != shopInfo){
			shopInfo.setPicList(picList);
		}
		//将picUrl清空
		shopInfo.setPicUrl(null);
		return shopInfo;
	}
	/**
	 * 根据查询条件查询经验者信息
	 * @param shopInfo
	 * @return
	 * @throws Exception
	 */
	public List<ShopInfo> findShopInfoList(ShopInfo shopInfo) throws Exception{
		return null;
	}
	
    /**
     * 添加经营者信息方法
     * @param shopInfo
     * @return
     */
	@Transactional(readOnly = false)
    public int addShopInfoInfo(ShopInfo shopInfo) throws Exception{
		
		//0、生成这个门店的门店ID
		String shopId = IdGen.uuid();
		String createTime = DateUtils.dateToString(new Date(), DateUtils.DATETIME_FORMAT);
		
		/*String shopNoSeq = sequenceMapper.getNextValSeqByName("shopNo");
		String shopNo = getNumber(shopNoSeq);
		//组装客户shopNo字段信息为区域首字母+shopNo组成
		String area = shopInfo.getArea();
		if(StringUtils.isNotBlank(area)){
			String pyArea = GetPinyin.getPinYinHeadChar(area).toUpperCase();
			shopInfo.setShopNo(pyArea+shopNo);
		}else{
			shopInfo.setShopNo(shopNo);
		}*/
		
		shopInfo.setShopId(shopId);
		shopInfo.setCreateBy(shopInfo.getUserId());
		shopInfo.setCreateTime(createTime);
		shopInfo.setUpdateBy(shopInfo.getUserId());
		shopInfo.setUpdateTime(createTime);
    	return shopInfoMapper.insertSelective(shopInfo);
    }
	
	//组装客户shopNo字段信息为区域首字母+shopNo组成
    public static String getNumber(String num){
        if(num.length() == 1){
            num = "0000" + num;
        }else if(num.length() == 2){
            num = "000" + num;
        }else if(num.length() == 3){
            num = "00" + num;
        }else if(num.length() == 4){
            num = "0" + num;
        }else if(num.length() > 5){
            System.out.println("num is out of range");
        }
        return num;
    }
	
    /**
     * 修改经营者信息方法
     * @param shopInfo
     * @return
     */
	@Transactional(readOnly = false)
    public int updateShopInfoInfo(ShopInfo shopInfo) throws Exception{
    	String createTime = DateUtils.dateToString(new Date(), DateUtils.DATETIME_FORMAT);
		shopInfo.setUpdateBy(shopInfo.getUserId());
		shopInfo.setUpdateTime(createTime);
    	return shopInfoMapper.updateByPrimaryKeySelective(shopInfo);
    }
	
    /**
     * 根据业务员ID 查询该业务员的相关门店
     * @param shopInfo
     * @return
     */
    public List<ShopInfo> findShopListByUserId(ShopInfo shopInfo) throws Exception{
    	return shopInfoMapper.findShopListByUserId(shopInfo);
    }
    
    /**
     * 根据业务员ID 查询该业务员的相关门店（分页查询）
     * @param shopInfo
     * @return
     */
	@Override
	public Page<ShopInfo> findShopListByUserId(Page<ShopInfo> page, ShopInfo shopInfo) throws Exception {
		// 设置分页参数
		shopInfo.setPage(page);
		//根据当前用户id查询是否是部门管理人员如果是则返回用户信息，将部门和用户类型作为条件查询同一个部门的其他业务员的客户信息
		String userId = shopInfo.getUserId();
		 User user = userMapper.findLeaderInfoById(userId);
		 if(null != user){
			String deptId = user.getDeptId();
			String userType = user.getUserType();
			shopInfo.setDeptId(deptId);
			shopInfo.setUserType(userType);
		 }
		
		// 执行分页查询
		List<ShopInfo> list = shopInfoMapper.findShopListByUserId(shopInfo);
		
		page.setList(list);
		return page;
	}
	
    /**
     * 根据查询条件查询店铺信息列表(分页方法)
     * @param page
     * @param shopInfo
     * @return
     * @throws Exception
     */
	@Override
    public Page<ShopInfo> queryShopList(Page<ShopInfo> page, ShopInfo shopInfo) throws Exception{
		// 设置分页参数
		shopInfo.setPage(page);
		// 执行分页查询
		List<ShopInfo> list = shopInfoMapper.queryShopList(shopInfo);
		page.setList(list);
		return page;
	}
	
    /**
     * 根据业务员ID 查询相关门店坐标点
     * @param shopInfo
     * @return
     */
	@Override
    public List<ShopInfo> findShopCoordinateList(ShopInfo shopInfo) throws Exception{
    	return shopInfoMapper.findShopCoordinateList(shopInfo);
    }

    /**
     * 根据用户ID查询对应业务员负责的店铺
     * @param userId
     * @return
     */
	/*@Override
    public List<String> getShopNoList(String userId) throws Exception{
		return shopInfoMapper.getShopNoList(userId);
    }*/
}

package com.corner.salesman.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.beans.vo.Pager;
import com.corner.core.utils.StringUtil;
import com.corner.rpc.shop.api.service.SpGroupService;
import com.corner.rpc.shop.model.SpGroup;
import com.corner.salesman.dao.DepartmentMapper;
import com.corner.salesman.dao.SpGroupDataMapper;
import com.corner.salesman.model.Department;
import com.corner.salesman.model.Shop;
import com.corner.salesman.model.SpGroupData;
import com.corner.salesman.service.SpGroupDataService;
/**
 * 定格与路线数据业务层
 * @author Administrator
 *
 */
@Service
public class SpGroupDataServiceImpl implements SpGroupDataService {
	
	@Autowired
	private SpGroupDataMapper spGroupDataMapper;
	@Autowired
	private SpGroupService spGroupService;
	@Autowired
	private DepartmentMapper deptMapper;
	

	@Override
	public Pager<SpGroupData> getSpGroupDataPageList(SpGroupData spGroupData) throws Exception {
		List<SpGroupData> list = spGroupDataMapper.getSpGroupDataPageList(spGroupData);
		int size = spGroupDataMapper.getSpGroupDataPageSize(spGroupData);
		//根据定格ID查询对应的业务代表
		for (SpGroupData spgVo : list) {
			String spGroupId = spgVo.getSpGroupId();
			if(StringUtils.isNotBlank(spGroupId)){
				String dbUsers = spGroupDataMapper.getSpGroupDbUser(spGroupId);
				spgVo.setDbUsers(dbUsers);
			}
		}
		
		return new Pager<SpGroupData>(size, list);
	}

	@Override
	public int addSpGroupData(SpGroupData spgVo) throws Exception {
		String opType = spgVo.getOpType();
		String deptId = spgVo.getDeptId();
		String spGroupId = spgVo.getSpGroupId();
		if(StringUtils.isNotBlank(opType)){
			//删除原来的老数据，insert最新数据
			spGroupDataMapper.deleteSpgBusAgent(spGroupId);
			spGroupDataMapper.deleteSpgLineMapper(spGroupId);
			spGroupDataMapper.deleteSpgLine(spGroupId);
			
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("spGroupId", spGroupId);
			paramMap.put("deptId", deptId);
			spGroupDataMapper.deleteSpGroupByDeptAndSpgId(paramMap);
		}
		
		//1、insert 业务代表（tbl_bus_agent_t）
		SpGroupData busAgent = new SpGroupData();
		busAgent.setSpGroupId(spgVo.getSpGroupId());
		String userIdSet = spgVo.getUserIdSet();
		String[] userIds = userIdSet.split(",");
		for(int i=0;i<userIds.length;i++){
			busAgent.setUserId(userIds[i]);
			spGroupDataMapper.insertBusAgent(busAgent);
		}
		
		//线路集合存储字段lineSet（格式： 线路AA##客户ID1，客户ID2，客户ID3@@线路BB##客户ID4，客户ID5，客户ID6@@）
		StringBuffer sbLine = new StringBuffer();
		String lineSet = spgVo.getLineSet();
		
		if(StringUtils.isNotBlank(lineSet)){
			String[] lineData = lineSet.split("@@");
			for(int i=0;i<lineData.length;i++){
				String[] lineInfo = lineData[i].split("##");
				String lineStr = lineInfo[0];//当前路线
				String shopIds = lineInfo[1];//当前路线对应的客户集合（格式：客户ID1，客户ID2，客户ID3，客户ID4）
				
				String line = null,lineId = null;
				if(StringUtils.isNotBlank(lineStr)){
					String[] lineArr = lineStr.split(",");
					if(lineArr.length==2){
						line = lineArr[0];
						lineId = lineArr[1];
					}else{
						line = lineArr[0];
						lineId = StringUtil.getUUID();
					}
				}
				
				//拼接路线集合供tbl_sp_group_t表中的路线集合使用
				sbLine.append(line);
				if(i<lineData.length-1){
					sbLine.append(",");
				}
				
				//2、insert 定格路线(tbl_sp_group_line_t)
				SpGroupData spgLVO = new SpGroupData();
				spgLVO.setLine(line);
				spgLVO.setSpGroupId(spGroupId);
				spgLVO.setLineId(lineId);
				spGroupDataMapper.insertSpGroupLine(spgLVO);
				
				if(StringUtils.isNotBlank(shopIds)){
					String[] shopIdSet = shopIds.split(",");
					//3、insert 定格与店铺关系（tbl_line_shop_mapper_t）
					for(int j=0;j<shopIdSet.length;j++){
						SpGroupData record = new SpGroupData();
						record.setLineId(lineId);
						record.setShopId(shopIdSet[j]);
						spGroupDataMapper.deleteShopLineByShopId(shopIdSet[j]);
						spGroupDataMapper.insertLineShopMapper(record);
					}

				}
			}
		}
		
		//4、insert 定格数据信息（tbl_sp_group_t）
		spgVo.setLine(sbLine.toString());
		return spGroupDataMapper.insertSelective(spgVo);
	}
	
    /**
     * 保存同步的定格数据
     * @throws Exception
     */
	@Override
    public void saveSyncSpGroupData(SpGroupData spGroupData) throws Exception{
		
		Integer provinceId = spGroupData.getProvinceId();
		Integer cityId = spGroupData.getCityId();
		Integer areaId = spGroupData.getAreaId();
		
		Integer hisProvinceId = spGroupData.getHisProvinceId();
		Integer hisCityId = spGroupData.getHisCityId();
		Integer hisAreaId = spGroupData.getHisAreaId();
		String deptId =  spGroupData.getDeptId();
		String updateBy = spGroupData.getUpdateBy();
		Date updateTime = spGroupData.getUpdateTime();
		//如果当前区域信息同原来信息不一致的时候删除原来老的数据
		if(!provinceId.equals(hisProvinceId)|| !cityId.equals(hisCityId)|| !areaId.equals(hisAreaId)){
			
			Department deptVo =  new Department();
			deptVo.setDeptId(deptId);
			deptVo.setProvinceId(provinceId);
			deptVo.setCityId(cityId);
			deptVo.setAreaId(areaId);
			deptVo.setUpdateBy(updateBy);
			deptVo.setUpdateTime(updateTime);
			//将当前部门所绑定的区域同步更新
			deptMapper.updateDeptmentByDeptId(deptVo);
			spGroupDataMapper.delSpGroupBindDbUser(spGroupData);
			spGroupDataMapper.delSpGroupData(spGroupData);
		}
		
		//首先在插入数据前清除一些垃圾数据
		//spGroupDataMapper.deleteJunkLine();
		//spGroupDataMapper.deleteJunkShop();
		
		//1、通过dubbo接口查询店宝当前部门绑定省市区下面对应的定格数据；
		SpGroup spObj = new SpGroup();
		spObj.setProvinceId(provinceId);
		spObj.setCityId(cityId);
		spObj.setAreaId(areaId);
		List<SpGroup> list = spGroupService.querySpGroupList(spObj);
		
		//2、将查询的定格数据insert到阿街系统库中保存
		if(null != list && !list.isEmpty()){
			for(SpGroup spVO : list){
				Integer id = spVO.getId();
				String name = spVO.getName();
				spGroupData.setSpGroupId(id+"");
				
				int spgNum = this.checkSpGroupIsExist(spGroupData);
				if(spgNum==0){
					spGroupData.setSpGroupName(name);
					spGroupDataMapper.insertSelective(spGroupData);
				}
			}
		}
    }

	@Override
	public int updateSpGroupData(SpGroupData spGroupData) throws Exception {
		return spGroupDataMapper.updateByPrimaryKeySelective(spGroupData);
	}

	@Override
	public int deleteSpGroupData(SpGroupData spGroupData) throws Exception {
		return spGroupDataMapper.deleteByPrimaryKey(spGroupData.getSpGroupId());
	}

	@Override
	public SpGroupData findSpGroupDataById(SpGroupData spGroupData) throws Exception {
		String spGroupId = spGroupData.getSpGroupId();
		//1、查询定格路线头部信息
		SpGroupData spgVO = spGroupDataMapper.findSpGroupDataById(spGroupData);
		if(null != spgVO){
			//2、回填对应的用户信息（小红，小麦等方式拼接返回）
			HashMap<String, String> map = spGroupDataMapper.getMergeUserInfo(spGroupId);
			if(null != map){
				String userIds = map.get("userIds");
				String userNames = map.get("userNames");
				spgVO.setUserIdSet(userIds);
				spgVO.setDbUsers(userNames);
			}
		}
		
		return spgVO;
	}

	/**
	 * 检查对应的定格是否已经存在
	 */
	@Override
	public int checkSpGroupDataIsExist(String spGroupId) throws Exception {
		return spGroupDataMapper.checkSpGroupDataIsExist(spGroupId);
	}
	
	@Override
	public int checkSpGroupIsExist(SpGroupData record) throws Exception{
		return spGroupDataMapper.checkSpGroupIsExist(record);
	}
	
	/**
	 * 检查对应的定格是否已经存在
	 */
	@Override
	public List<SpGroupData> getSpgLineData(String spGroupId) throws Exception {
		
		List<SpGroupData> list = spGroupDataMapper.findSpgLineListById(spGroupId);
		for(SpGroupData lineVO : list){
			List<Shop> shop = spGroupDataMapper.findSpgLineCustList(lineVO.getLineId());
			lineVO.setShop(shop);
		}
		
		return list;
	}

    /**
     * 根据部门查询定格路线列表信息
     * @param record
     * @return
     */
    public List<SpGroupData> getSpGroupBindDeptList(SpGroupData record) throws Exception{
    	List<SpGroupData> list = spGroupDataMapper.getSpGroupBindDeptList(record);
    	if(null != list && !list.isEmpty()){
        	for(SpGroupData spgVO : list){
        		//2、回填对应的用户信息（小红，小麦等方式拼接返回）
    			HashMap<String, String> map = spGroupDataMapper.getMergeUserInfo(spgVO.getSpGroupId());
    			if(null != map){
    				String userIds = map.get("userIds");
    				String userNames = map.get("userNames");
    				spgVO.setUserIdSet(userIds);
    				spgVO.setDbUsers(userNames);
    			}
    			
    			//3、如果定格线路为空，则重新查询一下表中是否存在线路（解决两个部门共用同一区域定格问题）
    			String line = spgVO.getLine();
    			if(StringUtils.isBlank(line)){
    				String lineName = spGroupDataMapper.getSpGroupLineNameBySgpId(spgVO.getSpGroupId());
    				spgVO.setLine(lineName);
    			}
        	}
    	}

    	return list;
    }
}

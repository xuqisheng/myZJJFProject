package com.corner.rpc.salesman.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.rpc.salesman.api.service.LinePlansService;
import com.corner.rpc.salesman.dao.LinePlansMapper;
import com.corner.rpc.salesman.dao.ShopMapper;
import com.corner.rpc.salesman.model.LinePlans;
import com.corner.salesman.commons.push.UMengPushTools;
import com.corner.salesman.commons.utils.Constants;
import com.corner.salesman.commons.utils.DateUtils;
import com.corner.salesman.commons.utils.JedisUtils;
import com.corner.salesman.commons.utils.UuidUtil;
import com.google.common.collect.Maps;

/**
 * 线路规划业务实现层
 * @author Administrator
 *
 */
@Service("linePlansService")
public class LinePlansServiceImpl implements LinePlansService {

	@Autowired
	private LinePlansMapper linePlansMapper;
	@Autowired
	private ShopMapper shopMapper;
	
	/**
	 * 查询线路计划数据
	 * @param linePlans
	 * @throws Exception
	 */
	@Override
	public List<LinePlans> queryLinePlansList(LinePlans linePlans) throws Exception {
		
		String salesmanId =  linePlans.getSalesmanId();
		List<LinePlans> lineList = linePlansMapper.queryLinePlansList(linePlans);
		if(null != lineList && !lineList.isEmpty()){
			for(LinePlans lineVo : lineList){
				String lineId = lineVo.getLineId();
				Map<String,Object> paramMap = Maps.newHashMap();
				paramMap.put("lineId", lineId);
				paramMap.put("salesmanId", salesmanId);
				//下面获取线路客户查询必须加上业务员ID,目的是过滤客户表重复的客户编码
				List<HashMap<String, Object>> shopList = shopMapper.getShopByLineId(paramMap);
				lineVo.setShopList(shopList);
			}
		}
		
		return lineList;
	}

	/**
	 * 保存线路计划
	 * @param linePlans
	 * @throws Exception
	 */
	@Override
	public void saveLinePlans(LinePlans linePlans) throws Exception {
		
		//线路集合存储字段lineSet（格式： 线路AA##客户ID1，客户ID2，客户ID3@@线路BB##客户ID4，客户ID5，客户ID6@@）
		String lineStr = linePlans.getLineStr();
		String deptId =  linePlans.getDeptId();
		String userId =  linePlans.getUserId();
		
		if(StringUtils.isNotBlank(lineStr)){
			String[] lineData = lineStr.split("@@");
			int lineSize = lineData.length;
			//防空数字时将原来数据删除（如果大于零表示真正有数据）
			if(lineSize>0){
				Map<String,Object> paraMap = new HashMap<String,Object>();
				paraMap.put("userId", userId);
				paraMap.put("deptId", deptId);
				//删除当前用户原来的线路数据(先删关系再删线路)
				linePlansMapper.deleteShopLineByLineId(paraMap);
				linePlansMapper.deleteLinePlans(paraMap);
			}
			
			for(int i=0;i<lineSize;i++){
				String[] lineInfo = lineData[i].split("##");
				String lineNames = lineInfo[0];//当前路线(路线名称 和 路线ID)
				String shopNos = lineInfo[1];//当前路线对应的客户集合（格式：客户ID1，客户ID2，客户ID3，客户ID4）
				
				String lineName = null,lineId = null;
				if(StringUtils.isNotBlank(lineNames)){
					String[] lineArr = lineNames.split(",");
					if(lineArr.length==2){
						lineName = lineArr[0];
						lineId = lineArr[1];
					}else{
						lineName = lineArr[0];
						lineId = UuidUtil.get32UUID();
					}
				}
				

				Integer shopTotal = null;
				if(StringUtils.isNotBlank(shopNos)){
					String[] shopIdSet = shopNos.split(",");
					
					shopTotal = shopIdSet.length;
					//2、insert 定格与店铺关系（tbl_line_shop_mapper_t）
					for(int j=0;j<shopTotal;j++){
						LinePlans record = new LinePlans();
						record.setLineId(lineId);
						record.setShopNo(shopIdSet[j]);
						//linePlansMapper.deleteShopLineByShopNo(shopIdSet[j]);
						linePlansMapper.insertLineShopMapper(record);
					}

				}
				
				//3、insert 路线规划(tbl_line_splans_t)
				LinePlans lineVO = new LinePlans();
				lineVO.setLineName(lineName);
				lineVO.setLineId(lineId);
				lineVO.setDeptId(deptId);
				lineVO.setSalesmanId(userId);
				lineVO.setShopTotal(shopTotal);//店铺数量需要切割统计
				//linePlansMapper.deleteByPrimaryKey(lineId);
				linePlansMapper.insertSelective(lineVO);
			}
		}
	}
	
	
	/**
	 * 修改拜访计划
	 * @param linePlans
	 * @throws Exception
	 */
	@Override
	public void updateVisitPlans(LinePlans linePlans) throws Exception{
		String salesmanId = linePlans.getSalesmanId();
		String lineStr = linePlans.getLineStr();//数据格式： lineId,week@@lineId,week
		if(StringUtils.isNotBlank(lineStr)){
			String[] lineSet = lineStr.split("@@");
			//将APP端拼接过来的数据按照格式切割，再根据线路ID修改对应表的week字段信息
			for(int j=0;j<lineSet.length;j++){
				String[] lineArr = lineSet[j].split(",");
				if(lineArr.length==2){
					LinePlans record = new LinePlans();
					record.setLineId(StringUtils.isBlank(lineArr[0])?null:lineArr[0]);
					record.setWeek(lineArr[1]);
					record.setSalesmanId(salesmanId);
					linePlansMapper.deleteWeekPlansMapperById(record);
					
					if(StringUtils.isNotBlank(lineArr[0])){
						linePlansMapper.insertWeekPlansMapper(record);
					}
				}
			}

			//记录个人未查阅的警告消息数
			long warnTotal = JedisUtils.incr(Constants.AJ_PLANS_CHANGE_TOTAL_KEY+salesmanId);
				
			Map<String,Object> warnObj = new HashMap<String,Object>();
			warnObj.put("subject", "您的拜访计划有变动！");
			warnObj.put("type", "6");
			warnObj.put("total", warnTotal+"");
			warnObj.put("typeName", "拜访计划");
			warnObj.put("createTime", DateUtils.dateToString(new Date(), DateUtils.DATETIME_FORMAT_7));
			
			//保存个人最新消息提醒信息(覆盖原来的消息)
			JedisUtils.setObject(Constants.AJ_PLANS_CHANGE_KEY+salesmanId, warnObj, 0);
			
			//拜访计划变动消息推送公共的方法
			UMengPushTools.getInstance().sendPlansCustomizedcast(salesmanId);
		}
	}
	
	/**
	 * 查询用户拜访客户路线
	 * @param userId
	 * @throws Exception
	 */
	@Override
	public List<HashMap<String,Object>> getVisitLineByUserId(String userId) throws Exception{
		return linePlansMapper.getVisitLineByUserId(userId);
	}

	
	/**
	 * 根据用户ID查询用户拜访计划列表
	 * @param userId
	 * @return
	 */
	@Override
	public List<HashMap<String,Object>> getMyVisitPlansList(LinePlans lineVo) throws Exception{
		
		String userId = lineVo.getUserId();
		String salesmanId = lineVo.getSalesmanId();
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("salesmanId", salesmanId);
		
		List<HashMap<String,Object>> list = linePlansMapper.getMyVisitPlansList(paraMap);
		if(null != list && !list.isEmpty()){
			for(HashMap<String,Object> map :list){
				Object week = map.get("week");
				Object lineId = map.get("lineId");
				Object lineName = map.get("lineName");
				Object shopTotal = map.get("shopTotal");
				
				map.put("week", week==null?"":week);
				map.put("lineId", lineId==null?"":lineId);
				map.put("lineName", lineName==null?"":lineName);
				map.put("shopTotal", shopTotal==null?0:shopTotal);
			}
		}

		//如果业务员ID为空，则表示当前用户为管理者，故使用管理者的id来拼接key
		salesmanId = StringUtils.isNotBlank(salesmanId)?salesmanId:userId;
		//将拜访计划消息提醒记录数归零处理
		JedisUtils.set(Constants.AJ_PLANS_CHANGE_TOTAL_KEY+salesmanId,"0",0);
		return list;
	}
	
	/**
	 * 查询业务员当天的拜访计划
	 * @param paramMap
	 * @throws Exception
	 */
	public List<HashMap<String, Object>> getMyTodayVisitPansList(Map<String, Object> paraMap)throws Exception{
		/*Map<String,String> paraMap = new HashMap<String,String>();
		paraMap.put("salesmanId", userId);
		paraMap.put("week", DateUtils.getChineseWeekday());*/
		return linePlansMapper.getMyVisitPlansList(paraMap);
	}

	/**
	 * 查询客户下的路线
	 * @param linePlans
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<HashMap<String,Object>> getMyVisitLineList(LinePlans record) throws Exception {
		//声明一个用户Id
		String userId=record.getUserId();
		//第一步：根据线路取数据
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("salesmanId", userId);
		paraMap.put("week", DateUtils.getChineseWeekday());
		
		List<HashMap<String,Object>> lineList = linePlansMapper.getMyVisitPlansList(paraMap);
		//场景一：如果按照week查询当天路线计划如果有安排，则返回当天对应计划列表数据（一条路线 和 路线下的客户）
		if(null != lineList && !lineList.isEmpty()){
			for(Map<String,Object> retMap : lineList){
				String lineId = retMap.get("lineId")+"";
				paraMap.put("lineId", lineId);
				List<HashMap<String, Object>> shopList=linePlansMapper.getMyVisitShopList(paraMap);
				retMap.put("shopList", shopList);
			}
		}
		//场景二：如果按照week查询，没有当天的计划，把用户名下有的路线都查询并返回
		else if(null == lineList || lineList.isEmpty()){
			lineList = linePlansMapper.getVisitLineByUserId(userId);
			if(null != lineList && !lineList.isEmpty()){
				for(Map<String,Object> retMap : lineList){
					String lineId = retMap.get("lineId")+"";
					paraMap.put("lineId", lineId);
					
					List<HashMap<String, Object>> shopList=linePlansMapper.getMyVisitShopList(paraMap);
					retMap.put("shopList", shopList);
				}
			} else {
				//场景三：如果一条线路没有数据时，查询业务员名下为划分的所有客户，并设置一条虚拟路线来承载客户列表
				List virtualLineList = new ArrayList();
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("lineId", "default");
				map.put("lineName", "默认线路");
				
				List<HashMap<String, Object>> shopList=linePlansMapper.getShopBySalesmanIdList(userId);
				map.put("shopList", shopList);
				virtualLineList.add(map);
				return virtualLineList;
			}
		}
		
		return lineList;
	}

	
	/**
	 * 根据线路ID删除客户和线路数据
	 * @param lineId
	 * @throws Exception
	 */
	public void deleteShopLineByLineId(String lineId) throws Exception{
		linePlansMapper.deleteShopLineMapperByLineId(lineId);
		linePlansMapper.deleteByPrimaryKey(lineId);
	}
	
	/**
	 * 切换用户部门时候，修改原来个人路线数据中的deptId
	 * @param paramMap
	 * @throws Exception
	 */
	public void updateDeptIdByUserId(Map<String,Object> paramMap) throws Exception{
		linePlansMapper.updateDeptIdByUserId(paramMap);
	}
}

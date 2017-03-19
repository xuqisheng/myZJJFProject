package com.corner.rpc.salesman.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.rpc.salesman.api.service.VisitHisRecordService;
import com.corner.rpc.salesman.dao.VisitHisRecordMapper;
import com.corner.rpc.salesman.model.VisitHisRecord;
import com.corner.salesman.commons.persistence.Page;
import com.corner.salesman.commons.utils.DateUtils;


@Service("visitHisRecordService")
public class VisitHisRecordServiceImpl implements VisitHisRecordService {

	@Autowired
	private VisitHisRecordMapper visitHisRecordMapper;
	
	@Override
	public void insertVisitHisRecord(String lineId) throws Exception {
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("lineId", lineId);
		paraMap.put("week", DateUtils.getChineseWeekday());
		visitHisRecordMapper.insertVisitHisRecord(paraMap);
	}
    
    /**
     * 根据店铺shopNo检查是否存在
     * @param shopNo
     * @return
     */
	@Override
	public int checkTodayShopNoIsExist(String shopNo) throws Exception{
		return visitHisRecordMapper.checkShopNoIsExist(shopNo);
	}
	
    /**
     * 检查当天线路ID是否存在
     * @param lineId
     * @return
     */
	@Override
	public int checkTodayLineIsExist(String lineId) throws Exception{
		return visitHisRecordMapper.checkTodayLineIsExist(lineId);
	}
	
    /**
     * 根据店铺shopNo检查对应店铺是否签过离店到（大于0表示已经签过离店）
     * @param shopNo
     * @return
     */
	@Override
    public int checkShopVisitStatus(String shopNo) throws Exception{
		return visitHisRecordMapper.checkShopVisitStatus(shopNo);
	}
    
    /**
     * 根据线路ID修改已拜访总数记录
     * @param lineId
     * @return
     */
	@Override
	public void updateVisitHisRecord(String lineId) throws Exception{
		visitHisRecordMapper.updateVisitHisRecord(lineId);
	}
	
    /**
     * 根据查询日期获取每一天的拜访结果列表(分页方法)
     * @param page
     * @param shop
     * @return
     * @throws Exception
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
    public Page<VisitHisRecord> getTodayVisitPlansList(Page<VisitHisRecord> page, VisitHisRecord record) throws Exception{
		// 设置分页参数
		record.setPage(page);
		String queryTime = record.getCreateTime();
		String currDate = DateUtils.dateToString(new Date(), DateUtils.NORMAL_DATE_FORMAT);
		
		List<HashMap<String, Object>> list = null;
		/*Map<String,Object> paraRecord =  new HashMap<String,Object>();
		paraRecord.put("deptId", record.getDeptId());
		paraRecord.put("visitDate", record.getCreateTime());*/
		
		if(queryTime.equals(currDate)){
			//如果查询日期等于当天的时间，则获取计划表中且星期对应是当天的星期的数据
			//paraRecord.put("week", DateUtils.getChineseWeekday());
			//record.setMap(paraRecord);
			record.setWeek(DateUtils.getChineseWeekday());
			
			list = visitHisRecordMapper.getTodayVisitPlansList(record);
		}else{
			//如果查询时间不等于当天的，则查询历史记录表中的历史记录
			list = visitHisRecordMapper.getHistoryVisitPlansList(record);
		}
		
		if(null != list && !list.isEmpty()){
			
			for(HashMap<String,Object> map :list){
				
				Object week = map.get("week");
				Object status = map.get("status");
				Object lineId = map.get("lineId");
				Object lineName = map.get("lineName");
				Object shopTotal = map.get("shopTotal");
				Object visitTotal = map.get("visitTotal");
				Object salesmanName = map.get("salesmanName");
				Object createTime = map.get("createTime");
				
				map.put("week", week==null?"":week);
				map.put("status", status==null?"":status);
				map.put("lineId", lineId==null?"":lineId);
				map.put("lineName", lineName==null?"":lineName);
				map.put("shopTotal", shopTotal==null?0:shopTotal);
				map.put("visitTotal", visitTotal==null?0:visitTotal);
				map.put("salesmanName", salesmanName==null?"":salesmanName);
				map.put("createTime", createTime==null?"":createTime);
			}
			
			//page中的list同返回的list类型不一致，故作转换处理
			List newList = new ArrayList();
			newList.addAll(list);
			//将list回填page对象
			page.setList(newList);
		}
		
		return page;
    }
	
    /**
     * 获取当天的拜访结果列表
     * @param record
     * @return
     * @throws Exception
     */
	@Override
    public List<HashMap<String, Object>> getTodayVisitPlansList(VisitHisRecord record) throws Exception{
		record.setWeek(DateUtils.getChineseWeekday());
		return visitHisRecordMapper.getTodayVisitPlansList(record);
    }
}

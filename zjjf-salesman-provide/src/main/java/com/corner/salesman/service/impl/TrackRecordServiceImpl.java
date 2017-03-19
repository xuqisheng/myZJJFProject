package com.corner.salesman.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.corner.salesman.common.utils.BaiDuUtil;
import com.corner.salesman.common.utils.DateUtils;
import com.corner.salesman.common.utils.Encodes;
import com.corner.salesman.common.utils.IdGen;
import com.corner.salesman.common.utils.MapDistance;
import com.corner.salesman.commons.persistence.Page;
import com.corner.salesman.commons.utils.Constants;
import com.corner.salesman.dao.TrackRecordMapper;
import com.corner.salesman.dao.UserDeptMapper;
import com.corner.salesman.model.TrackRecord;
import com.corner.salesman.service.TrackRecordService;

/**
 * 创建时间：2015-1-27 下午5:22:59
 * 
 * @author 元宝
 * @version v0.1
 */
@Service("trackRecordService")
@Transactional(readOnly = true)
public class TrackRecordServiceImpl implements TrackRecordService {
	
	private static final Logger logger = LoggerFactory.getLogger(TrackRecordServiceImpl.class);
	@Autowired
	private UserDeptMapper userDeptMapper;
	@Autowired
	private TrackRecordMapper trackRecordMapper;
	
	/**
	 * 根据用户ID查询该用户轨迹信息列表
	 * @param trackRecord
	 * @return
	 */
	@Override
    public List<TrackRecord> findTrackRecordByUserId(TrackRecord trackRecord) throws Exception{
    	return trackRecordMapper.findTrackRecordByUserId(trackRecord);
    }
	
	/**
	 * 根据用户ID+时间查询该用户轨迹坐标信息列表
	 * @param trackRecord
	 * @return
	 */
	/*@Override
    public List<TrackRecord> findMyAllDateTrackList(TrackRecord trackRecord) throws Exception{
		return trackRecordMapper.findMyAllDateTrackList(trackRecord);
	}*/
	@SuppressWarnings("unused")
	@Override
    public List<TrackRecord> findMyAllDateTrackList(TrackRecord trackRecord) throws Exception{
		
		List<TrackRecord> newList = null;
		List<TrackRecord> list = trackRecordMapper.findMyAllDateTrackList(trackRecord);
		
		if(null != list && list.size()>3){
			newList = new ArrayList<TrackRecord>();
			
			//声明一个比对的中间变量
			double lng1 = 0d;
	  	    double lat1 = 0d;
	  	    String startTime = null;
	  	    
	  	    double invalidLng = 0d, invalidLat = 0d;//连续无效的中间变量
	  	    double validLng = 0d,validLat = 0d;//连续有效的中间变量
			
			for(int i=0;i<list.size();i++){
				TrackRecord track1 = list.get(i);
				Integer type = track1.getType();
				//类型为3表示是外勤签到，该点永远不能被过滤掉
				if(null != type && type==3){
					//如果类型等于3，则直接加入有效队列中，忽略后面的计算
					newList.add(track1);
					continue;
				}
				
				double lng2 = track1.getLongitude();
		  	    double lat2 = track1.getLatitude();
		  	    String endTime = track1.getCreateTime();
		  	    //System.err.println("=============lng2:"+lng2+" ,lat2:"+lat2+",creatTime:"+endTime);
			  	    
				if(i==0){
					lng1 = track1.getLongitude();
			  	    lat1 = track1.getLatitude();
			  	    startTime = track1.getCreateTime();
				}else{
			  	    //如果当前坐标等于无效，则直接跳过即可
			  	    if(invalidLng==lng2 && invalidLat==lat2){
			  	    	continue;
			  	    }else if(validLng==lng2 && validLat==lat2){
			  	    	startTime = endTime;//减小正常坐标时间与多个偏移的坐标之间的时间差，所以直接用对应比对时间替换正确坐标的时间
			  	    	continue;
			  	    }
				}
				
		  	    //计算两点时间差（单位：秒）
		  	    long time = DateUtils.dateDiff(startTime, endTime, DateUtils.DATETIME_FORMAT);
		  	    //计算两个坐标距离
		  	    double distance = MapDistance.LantitudeLongitudeDist(lng1, lat1, lng2, lat2);
		  	    //距离除以时间等于速度
		  	    double speed = distance/time;
		  	    boolean bool = Double.isNaN(speed);
		  	    //小于10每秒的速度
				if(bool || speed<15){
					//将满足指定阈值的对象加到新的list中返回app
					newList.add(track1);
					//如果成立则将比对的第二个点做下一个比对的点
					lng1 = lng2;
			  	    lat1 = lat2;
			  	    startTime = endTime;
			  	    
			  	    validLng = lng2;
			  	    validLat = lat2;
				}else{
					invalidLng = lng2;
					invalidLat = lat2;
					//startTime = endTime;//减小正常坐标时间与多个偏移的坐标之间的时间差，所以直接用对应比对时间替换正确坐标的时间
				}
				
			}
			
			if(null == newList){
				newList = list;
			}
			//将数据倒序排列
			Collections.reverse(newList);
		}else{
			newList = list;
		}
		//System.err.println("===================原始list:"+list.size()+"条, 新的list:"+newList.size()+"条");
		return newList;
	}
	
	/**
	 * 根据用户ID查询该用户轨迹明细列表
	 * @param trackRecord
	 * @return
	 */
    public Page<TrackRecord> findMyTrackDetailList(Page<TrackRecord> page, TrackRecord trackRecord) throws Exception{
    	// 设置分页参数
		trackRecord.setPage(page);
		// 执行分页查询
		List<TrackRecord> list = trackRecordMapper.findMyTrackDetailList(trackRecord);
		page.setList(list);
		return page;
    }
	
	@Override
	public Page<TrackRecord> findTrackRecordPage(Page<TrackRecord> page, TrackRecord trackRecord) throws Exception {
		// 设置分页参数
		trackRecord.setPage(page);
		// 执行分页查询
		List<TrackRecord> list = trackRecordMapper.findTrackRecordByUserId(trackRecord);
		page.setList(list);
		return page;
	}
    
    /**
     * 根据用户组ID查询该组业务员最新规矩列表
     * @param trackRecord
     * @return
     */
	@Override
    public List<TrackRecord> findGroupNewTrackByTime(TrackRecord trackRecord) throws Exception{
		//1、根据用户ID查询所在用户组
		String deptId = trackRecord.getDeptId();//userDeptMapper.findDeptIdByUserId(trackRecord.getUserId());
    	if(StringUtils.isNotBlank(deptId)){
    		trackRecord.setGroupId(deptId);
    	}else{
    		deptId = userDeptMapper.findDeptIdByUserId(trackRecord.getUserId());
    		trackRecord.setGroupId(deptId);
    	}
		return trackRecordMapper.findGroupNewTrackByTime(trackRecord);
	}
	
	/**
	 * 根据用户组ID查询该用户轨迹信息列表(分页方法)
	 * @param trackRecord
	 * @return
	 */
	public Page<TrackRecord> findGroupNewTrackByTime(Page<TrackRecord> page, TrackRecord trackRecord) throws Exception{

    	String deptId = trackRecord.getDeptId();
    	if(StringUtils.isNotBlank(deptId)){
    		trackRecord.setGroupId(deptId);
    	}else{
    		deptId = userDeptMapper.findDeptIdByUserId(trackRecord.getUserId());
    		trackRecord.setGroupId(deptId);
    	}
    	
    	//如果查询时间为空，则默认查询表中最大的创建时间
    	String createTime = trackRecord.getCreateTime();
    	if(StringUtils.isBlank(createTime)){
    		String dbMaxDate = trackRecordMapper.getTrackMaxDate();
    		trackRecord.setCreateTime(dbMaxDate);
    	}
		
		// 设置分页参数
		trackRecord.setPage(page);
		// 执行分页查询
		List<TrackRecord> list = trackRecordMapper.findGroupNewTrackByTime(trackRecord);
		page.setList(list);
		return page;
	}
    
    /**
     * 添加轨迹信息
     * @param trackRecord
     * @return
     */
	@Override
	@Transactional(readOnly = false)
    public int addTrackRecord(TrackRecord trackRecord) throws Exception{
		
		//==================该部分代码v1.4.0版本后可以去除 start=========
		String deptId = trackRecord.getDeptId();
		//1、根据用户ID查询所在用户组
    	if(StringUtils.isBlank(deptId)){
    		deptId = userDeptMapper.findDeptIdByUserId(trackRecord.getUserId());
    		trackRecord.setDeptId(deptId);
    	}
    	//==================该部分代码v1.4.0版本后可以去除 end =========
		
    	Date date = new Date();
		trackRecord.setTimePoint(DateUtils.dateToString(date, DateUtils.HOUR_TIME_FORMAT));
		trackRecord.setId(IdGen.uuid());
		trackRecord.setCreateBy(trackRecord.getUserId());
		trackRecord.setCreateTime(DateUtils.dateToString(date, DateUtils.DATETIME_FORMAT));
		trackRecord.setWeek(DateUtils.getChineseWeekday());
		trackRecord.setGroupId(trackRecord.getDeptId());
		//3、根据用户ID删除最新个人轨迹表中的数据
		trackRecordMapper.deleteTrackRecordNewByUserId(trackRecord);
		//4、分别往最新轨迹表和轨迹表中存储
		trackRecordMapper.insertTrackRecordNew(trackRecord);
		return trackRecordMapper.insertTrackRecord(trackRecord);
	}
	
    /**
     * 批量添加轨迹信息
     * @param trackRecord
     * @return
     */
	@Override
	@Transactional(readOnly = false)
    public void addBatchTrackRecord(TrackRecord trackRecord) throws Exception{
    	
    	TrackRecord newTrack = null;
    	String batchTrackStr = trackRecord.getBatchTrackStr();
    	if(StringUtils.isNotBlank(batchTrackStr)){
    	   	//1、根据用户ID查询所在用户组
        	String deptId = trackRecord.getDeptId();//userDeptMapper.findDeptIdByUserId(trackRecord.getUserId());
        	String userId = trackRecord.getUserId();
        	Integer type = trackRecord.getType();
        	Integer deviceType = trackRecord.getDeviceType();
        	//指定时间点用于判断是否在指定时间内进行反地理编码
        	String pointInTime = null;
			if(null != deviceType && deviceType == 2){
				pointInTime = Constants.POINT_IN_TIME_IOS;
			}else{
				pointInTime = Constants.POINT_IN_TIME_ANDROID;
			}
        	
        	
    		String[] locationSet = batchTrackStr.split("]");
    		//如果批量上传的数据不为空，则构造下方对象insert到表中
    		for(String locationStr:locationSet){
    			Double longitude = null;//经度
    			Double latitude = null;//纬度
    			newTrack = new TrackRecord();
    			newTrack.setUserId(userId);
    			newTrack.setType(type);
    			newTrack.setDeviceType(deviceType);
    			
    			String[] pointSet = locationStr.split(",");
    			 String longitudeStr = pointSet[0];//经度
    			 String latitudeStr = pointSet[1];//纬度
    			 if("(null)".equals(longitudeStr) || "(null)".equals(latitudeStr)){
    				 continue;
    			 }
    			 
    			 if(StringUtils.isNotBlank(longitudeStr)){
    				 longitude = Double.valueOf(longitudeStr);
    				 newTrack.setLongitude(longitude);
    			 }
    			 
    			 if(StringUtils.isNotBlank(latitudeStr)){
    				 latitude = Double.valueOf(latitudeStr);
    				 newTrack.setLatitude(latitude);//纬度
    			 }
 				if(pointSet.length>=3){
 					String localtimes = pointSet[2];
 					
 					//=============解决IOS平板时间戳时间少于13为的问题 start==============
 					if(deviceType == 2){
 						String createTime = null;
 	 					if(pointSet.length==5){
 	 						createTime = pointSet[4];
 	 					}
 	 					
 	 					if(localtimes.length()<13 && null != createTime){
 	 						localtimes = DateUtils.getStringToDate(createTime, DateUtils.DATETIME_FORMAT)+"";
 	 						newTrack.setTimePoint(DateUtils.timestampToDate(localtimes, DateUtils.HOUR_TIME_FORMAT));
 	 	 					newTrack.setLocaltimes(localtimes);
 	 	 					newTrack.setCreateTime(createTime);
 	 					}else{
 	 						newTrack.setTimePoint(DateUtils.timestampToDate(localtimes, DateUtils.HOUR_TIME_FORMAT));
 	 	 					newTrack.setLocaltimes(localtimes);
 	 	 					newTrack.setCreateTime(DateUtils.timestampToDate(localtimes));
 	 					}
 					}
 					//=============解决IOS平板时间戳时间少于13为的问题 end==============
 					else{
 						newTrack.setTimePoint(DateUtils.timestampToDate(localtimes, DateUtils.HOUR_TIME_FORMAT));
 	 					newTrack.setLocaltimes(localtimes);
 	 					newTrack.setCreateTime(DateUtils.timestampToDate(localtimes));
 					}
				}
 				if(pointSet.length==4){
 					String positionName = pointSet[3];
 					positionName = Encodes.urlDecode(positionName);
 					if(StringUtils.isNotBlank(positionName)){
 	 					newTrack.setPositionName(positionName);
 					}else{
 						//设置满足条件的反地理编码处理
 						setPositionName(longitude, latitude,pointInTime,newTrack);
 					}
				}else{
					//设置满足条件的反地理编码处理
					setPositionName(longitude, latitude,pointInTime,newTrack);
				}
 				
 	    		//设置所在部门ID
 	        	if(StringUtils.isNotBlank(deptId)){
 	        		newTrack.setGroupId(deptId);
 	        	}else{
 	        		deptId = userDeptMapper.findDeptIdByUserId(trackRecord.getUserId());
 	        		newTrack.setGroupId(deptId);
 	        	}
 	        	newTrack.setId(IdGen.uuid());
 	        	newTrack.setCreateBy(trackRecord.getUserId());
 	        	newTrack.setWeek(DateUtils.getChineseWeekday());
 	    		//4、往历史轨迹表中存储
 	    		trackRecordMapper.insertTrackRecord(newTrack);
    		}
    	}
    }
	
	//设置满足条件的反地理编码处理
	public void setPositionName(Double longitude,Double latitude,String pointInTime,TrackRecord newTrack){
		
		String time = newTrack.getCreateTime();
		if(StringUtils.isNotBlank(time)){
			String[] timeSet = time.split(":");//切割时间，如：2016-04-28 13:59:40
			if(StringUtils.isNotBlank(time)&&timeSet.length==3){
				String minute = timeSet[1].trim();//取得时间的分钟值
				
				//如果截取的分钟包含在指定常量内的就进行反地理编码
				if(pointInTime.contains(minute)){
					if(null != longitude && null != latitude){
						String positionName = null;
						try{
							positionName = BaiDuUtil.getLocationName(latitude.toString(), longitude.toString());
						}catch (Exception e) {
							logger.error("批量上传坐标反地理编码失败！");
						}
						//positionName = positionName==null?"":positionName;
						if(StringUtils.isNotBlank(positionName)){
							newTrack.setPositionName(positionName);
						}
					}
				}
			}
		}
	}
	
	/**
     * 查询业务员个人最新轨迹列表
     * @param trackRecord
     * @return
     */
	public Page<TrackRecord> findUserEverydayTrackById(Page<TrackRecord> page, TrackRecord trackRecord) throws Exception{
    	// 设置分页参数
		trackRecord.setPage(page);
		// 执行分页查询
		List<TrackRecord> list = trackRecordMapper.findUserEverydayTrackById(trackRecord);
		page.setList(list);
		return page;
	}
}
	

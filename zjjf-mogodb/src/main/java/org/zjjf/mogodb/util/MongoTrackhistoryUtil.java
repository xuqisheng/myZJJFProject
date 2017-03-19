package org.zjjf.mogodb.util;

import com.alibaba.fastjson.JSON;
import com.mongodb.BasicDBObject;
import com.mongodb.QueryOperators;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.FindOneAndDeleteOptions;
import com.mongodb.client.result.UpdateResult;
import org.apache.commons.lang.StringUtils;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zjjf.mogodb.common.IM4Collection4SalesmanTrack;
import org.zjjf.mogodb.common.IMongoDB;
import org.zjjf.mogodb.model.TrackRecordDo;

import java.util.*;
import java.util.regex.Pattern;

/**
 * 提供对外调用的方法
 * @author 小武
 *
 */
public class MongoTrackhistoryUtil implements IMongoDB,IM4Collection4SalesmanTrack{
	
	private static final Logger _logger = LoggerFactory.getLogger(MongoTrackhistoryUtil.class);
	
	/**
	 * 添加一条文档数据
	 * @param one
	 * @return
	 */
	public static boolean addTrackInfo(TrackRecordDo one){
		Document doc = switchTrackRecordDo2Document(one);
		return MongodbUtil.getInstance().insertDocument(MongodbUtil.getInstance().getTrackDBName(), m_c_tbl_track_record_history_t, doc);
	}
	
	
	/**
	 * （物理删除）删除用户一条数据
	 * @param id
	 * @return
	 */
	public static boolean delUserOneTrackById(String id){
		Document doc= delDocument4trackByColName(c_id, id);
		if(null != doc) 
			return true;
		else 
			return false;
	}
	
	
	/**
	 * 根据id修改，文档中某个字段的值。
	 * @param idval id字段的值
	 * @param colName 修改的列的名称
	 * @param colVal 修改的列的值
	 * @return
	 */
	public static boolean updateDocument4TrackById(String idval,String colName,String colVal){
		MongoCollection<Document> col = MongodbUtil.getInstance().getTrackCollection();
		UpdateResult  ur = col.updateOne(Filters.eq(c_id, idval), new Document("$set", new Document(colName, colVal)));
		_logger.debug(JSON.toJSONString(ur));
		return ur.isModifiedCountAvailable();
	}
	
	
	/**
	 * 分页查询用户最近一周的轨迹数据
	 * @param userId 用户id
	 * @param curPageNo 当前页面
	 * @param pageSize 每页大小
	 * @return
	 */
	public static MPageResult findUserOneWeekTrackhistoryByPage(String userId, Integer curPageNo,Integer pageSize) {
		Date nowDate = new Date();
		Calendar ca = new GregorianCalendar();
		ca.setTime(nowDate);
		ca.add(ca.DATE, -7);
		Date startDate = ca.getTime();
		return findPageDocument4trackhistoryBy_userId(userId, curPageNo, pageSize, c_createtime, startDate, nowDate,c_createtime,null,null);
	}
	
	/**
	 * 分页查询用户最近一个月的轨迹数据
	 * @param userId 用户id
	 * @param curPageNo 当前页面
	 * @param pageSize 每页大小
	 * @return
	 */
	public static MPageResult findUserOneMonthTrackhistoryByPage(String userId, Integer curPageNo,Integer pageSize) {
		Date nowDate = new Date();
		Calendar ca = new GregorianCalendar();
		ca.setTime(nowDate);
		ca.add(ca.DATE, -30);
		Date startDate = ca.getTime();
		return findPageDocument4trackhistoryBy_userId(userId, curPageNo, pageSize, c_createtime, startDate, nowDate,c_createtime,null,null);
	}
	
	
	/**
	 * 分页查询用户最近一个月的轨迹数据
	 * @param userId 用户id
	 * @param curPageNo 当前页面
	 * @param pageSize 每页大小
	 * @return
	 */
	public static MPageResult findUserAllTrackhistoryByPageLikeSome(String userId, Integer curPageNo,Integer pageSize) {
		Date nowDate = new Date();
		Calendar ca = new GregorianCalendar();
		ca.setTime(nowDate);
		ca.add(ca.DATE, -700);
		Date startDate = ca.getTime();
		
		String[] likeColNames = new String[]{c_positionname,c_deviceid};
		String likeVal = "东东";
		
		return findPageDocument4trackhistoryBy_userId(userId, curPageNo, pageSize, c_createtime, startDate, nowDate,c_createtime,likeColNames,likeVal);
	}
	
	
	/**
	 * 根据列名模糊查询数据
	 * 
	 * @param colName
	 *            mongodb的列名
	 * @param val
	 *            列里面的值
	 * @return
	 */
	public static List<Document> findDocument4trackByColName(String colName, String val) {
		List<Document> list = new ArrayList<Document>();
		Document doc = null;
		MongoCollection<Document> col = MongodbUtil.getInstance().getTrackCollection();
		FindIterable<Document> iter = col.find(new BasicDBObject(colName, val));
		MongoCursor<Document> cursor = iter.iterator();
		while (cursor.hasNext()) {
			doc = cursor.next();
			_logger.debug(JSON.toJSONString(doc));
			list.add(doc);
		}
		return list;
	}

	/**
	 * 根据时间字段查询时间范围内的文档
	 * 
	 * @param colName
	 *            列名
	 * @param startDate
	 *            开始时间
	 * @param endDate
	 *            结束时间
	 * @return
	 */
	public static List<Document> findDocument4trackhistoryByTime(String colName, Date startDate, Date endDate) {
		List<Document> list = new ArrayList<Document>();
		Document doc = null;
		MongoCollection<Document> col = MongodbUtil.getInstance().getTrackCollection();
		BasicDBObject filter = new BasicDBObject();
		filter.put(colName, new BasicDBObject(QueryOperators.GTE, startDate).append(QueryOperators.LTE, endDate));
		FindIterable<Document> iter = col.find(filter);
		MongoCursor<Document> cursor = iter.iterator();
		while (cursor.hasNext()) {
			doc = cursor.next();
			_logger.debug(JSON.toJSONString(doc));
			list.add(doc);
		}
		return list;
	}
	
	/**
	 * 分页查询用户的轨迹数据
	 * @param userId 用户id
	 * @param curPageNo 当前页面
	 * @param pageSize 每页大小
	 * @param dataColName 按时间查询时候，时间列名
	 * @param startDate 开始时间
	 * @param endDate 结束时间
	 * @param sortColname 排序字段名称
	 * @return
	 */
	public static MPageResult findPageDocument4trackhistoryBy_userId(
			String userId, Integer curPageNo,Integer pageSize,
			String dataColName,Date startDate, Date endDate ,
			String sortColname,String[] likeColNames,String likeVal) {
		if(StringUtils.isEmpty(userId)){
			return new MPageResult(curPageNo, pageSize, 0, null);
		}
		
		String _logParam = " userId:" + userId + "; curPageNo:" + curPageNo + "; pageSize:" + pageSize + "; "
				+ "dataColName"+ dataColName + "; startDate:"+ startDate + "; endDate:"+ endDate + "; sortColname:" + sortColname + "; ";
		
		_logger.debug("start query:" + _logParam);
		List<Document> list = new ArrayList<Document>();
		Document doc = null;
		MongoCollection<Document> col = MongodbUtil.getInstance().getTrackCollection();
//		BasicDBObject queryparam = new BasicDBObject(new BasicDBObject(c_userid, userId));
		
//		BasicDBObject queryparam = new BasicDBObject(QueryOperators.AND,new BasicDBObject[] {
//				new BasicDBObject(c_userid, userId),
//				new BasicDBObject(dataColName,new BasicDBObject(QueryOperators.GTE, startDate).append(QueryOperators.LTE, endDate))
//				
//		});
		
		BasicDBObject queryparam = null;
		
		if(null != likeColNames && likeColNames.length > 0 && StringUtils.isNotBlank(likeVal)){
			Pattern pattern = Pattern.compile("^.*" + likeVal+ ".*$", Pattern.CASE_INSENSITIVE); 
			BasicDBObject[] likes = new BasicDBObject[likeColNames.length];
			BasicDBObject likeOne=new BasicDBObject();
			for(int idx = 0;idx<likeColNames.length;idx++){
				likeOne.put(likeColNames[idx], pattern);
			}
			
			queryparam = new BasicDBObject(QueryOperators.AND,new BasicDBObject[] {
					new BasicDBObject(c_userid, userId),
					new BasicDBObject(dataColName,new BasicDBObject(QueryOperators.GTE, startDate).append(QueryOperators.LTE, endDate)) ,
					new BasicDBObject(QueryOperators.OR,likes)
			});
		}else{
			queryparam = new BasicDBObject(QueryOperators.AND,new BasicDBObject[] {
					new BasicDBObject(c_userid, userId),
					new BasicDBObject(dataColName,new BasicDBObject(QueryOperators.GTE, startDate).append(QueryOperators.LTE, endDate))
			});
		}
		
		BasicDBObject sort = new BasicDBObject(c_createtime,m_query_sort_desc);
		if(StringUtils.isNotEmpty(sortColname)){
			sort = new BasicDBObject(sortColname,m_query_sort_desc);
		}
		
		long totalSize = col.count(queryparam);
		FindIterable<Document> iter = col.find(queryparam).sort(sort).skip((curPageNo-1) * pageSize).limit(pageSize);
		MongoCursor<Document> cursor = iter.iterator();
		int idx = 0 ;
		while (cursor.hasNext()) {
				doc = cursor.next();
				_logger.debug("query data["+idx+"]: "+JSON.toJSONString(doc));
				list.add(doc);
				idx++;
		}
		MPageResult mr = new MPageResult(curPageNo, pageSize, totalSize, list);
		_logger.debug("end query:" + _logParam);
		return mr;
	}
	
	/**
	 * 分页查询用户的轨迹数据
	 * @param userId 用户id
	 * @param curPageNo 当前页面
	 * @param pageSize 每页大小
	 * @return
	 */
	public static MPageResult findPageDocument4trackhistoryBy_userId(String userId, Integer curPageNo,Integer pageSize) {
		return findPageDocument4trackhistoryBy_userId(userId, curPageNo, pageSize, null, null, null, c_createtime,null,null);
	}
	
	/**
	 * 根据列的名称和值，删除数据。
	 * @param colName 列名称
	 * @param val 列的值
	 * @return
	 */
	public static Document delDocument4trackByColName(String colName, String val) {
		List<Document> list = new ArrayList<Document>();
		Document doc = null;
		MongoCollection<Document> col = MongodbUtil.getInstance().getTrackCollection();
		FindOneAndDeleteOptions options = new FindOneAndDeleteOptions();
		BasicDBObject filter = new BasicDBObject(colName, val);
		doc = col.findOneAndDelete(filter, options);
		_logger.debug(JSON.toJSONString(doc));
		return doc;
	}

	
	
	/**
	 * 根据Mongo里面的objectid获取文档数据
	 * @param _id
	 * @return
	 */
	public static List<Document> findDocument4trackBy_id(String _id) {
		List<Document> list = new ArrayList<Document>();
		Document doc = null;
		MongoCollection<Document> col = MongodbUtil.getInstance().getTrackCollection();
		FindIterable<Document> iter = col.find(new BasicDBObject(c__id, new ObjectId(_id)));
		MongoCursor<Document> cursor = iter.iterator();
		while (cursor.hasNext()) {
			doc = cursor.next();
			_logger.debug(JSON.toJSONString(doc));
			list.add(doc);
		}
		return list;
	}
	
	/**
	 * (用户轨迹对象转换)JavaBean对象转Mongodb对象
	 * @param lists
	 * @return
	 */
	public static List<Document> switchListTrackRecordDo2Document(List<TrackRecordDo> lists){
		List<Document> listDo = new ArrayList<Document>(lists.size());
		for(TrackRecordDo one:lists){
			listDo.add(switchTrackRecordDo2Document(one));
		}
		return listDo;
	}
	
	/**
	 * (用户轨迹对象转换)Mongodb对象转JavaBean对象
	 * @param docs
	 * @return
	 */
	public static List<TrackRecordDo> switchListDocument2TrackRecordDo(List<Document> docs){
		List<TrackRecordDo> listDo = new ArrayList<TrackRecordDo>(docs.size());
		for(Document doc:docs){
			listDo.add(switchDocument(doc));
		}
		return listDo;
	}
	
	/**
	 * (用户轨迹对象转换)JavaBean对象转Mongodb对象
	 * @param one 轨迹对象
	 * @return
	 */
	public static Document switchTrackRecordDo2Document(TrackRecordDo one){
		Document doc = new Document();
		doc.append(c_deviceid, one.getDeviceId());
		doc.append(c_devicetype, one.getDeviceType());
		doc.append(c_groupid, one.getGroupId());
		doc.append(c_id, one.getId());
		doc.append(c_latitude, one.getLatitude());
		doc.append(c_localtimes, one.getLocaltimes());
		doc.append(c_longitude, one.getLongitude());
		doc.append(c_positionname, one.getPositionName());
		doc.append(c_timepoint, one.getTimePoint());
		doc.append(c_type, one.getType());
		doc.append(c_week, one.getWeek());
		return doc;
	}
	/**
	 * (用户轨迹对象转换)Mongodb对象转JavaBean对象
	 * @param one
	 * @return
	 */
	public static TrackRecordDo switchDocument(Document one){
		TrackRecordDo track = new TrackRecordDo();
		track.setDeviceId(one.getString(c_deviceid));
		track.setDeviceType(one.getInteger(c_devicetype));
		track.setGroupId(one.getString(c_groupid));
		track.setId(one.getString(c_id));
		track.setLatitude(one.getDouble(c_latitude));
		track.setLocaltimes(one.getString(c_localtimes));
		track.setLongitude(one.getDouble(c_longitude));
		track.setPositionName(one.getString(c_positionname));
		track.setTimePoint(one.getString(c_timepoint));
		track.setType(one.getInteger(c_type));
		track.setWeek(one.getString(c_week));
		return track;
	}
}

package com.corner.salesman.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.corner.salesman.common.utils.DateUtils;
import com.corner.salesman.common.utils.IdGen;
import com.corner.salesman.common.utils.JedisUtils;
import com.corner.salesman.commons.persistence.Page;
import com.corner.salesman.commons.push.UMengPushTools;
import com.corner.salesman.commons.utils.Constants;
import com.corner.salesman.dao.NoticeMapper;
import com.corner.salesman.dao.UserDeptMapper;
import com.corner.salesman.dao.UserMapper;
import com.corner.salesman.model.Notice;
import com.corner.salesman.model.User;
import com.corner.salesman.model.UserNotice;
import com.corner.salesman.service.NoticeService;

/**
 * 公告信息Service
 * @author 元宝
 * @version 2016-01-26
 */
@Service("noticeService")
@Transactional(readOnly = true)
public class NoticeServiceImpl implements NoticeService {
	
	private static final Logger logger = LoggerFactory.getLogger(NoticeServiceImpl.class);
	@Autowired
	private UserDeptMapper userDeptMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private NoticeMapper noticeMapper;
	
	@Transactional(readOnly = false)
	public void addNotice(Notice notice) {
		
		//1、根据创建人找到组ID
		String deptId = null;
		String userId = notice.getUserId();
		deptId = userDeptMapper.findDeptIdByUserId(userId);
		if(StringUtils.isNotBlank(deptId)){
			//2、将查询到的组ID设置到新增任务中
			notice.setGroupId(deptId);
		}
		
		//3、生成一个新的UUID做为该公告的ID
		String noticeId = IdGen.uuid();
		Date date = new Date();
		//4.添加公告信息到表中
		notice.setId(noticeId);
		notice.setCreateTime(DateUtils.dateToString(date, DateUtils.DATETIME_FORMAT));
		notice.setCreateBy(userId);
		noticeMapper.insert(notice);
		
		//5.获取app选择的用户，根据assigns按逗号分割，然后组装对象保存关系表
		String assigns = notice.getAssigns();
		if(!assigns.contains(userId)){
			assigns = userId+","+assigns;
		}
		
		String[] userSet = assigns.split(",");
		for (int i = 0; i < userSet.length; i++) {
			UserNotice userNotice = new UserNotice();
			userNotice.setUserId(userSet[i]);
			userNotice.setNoticeId(noticeId);
			userNotice.setGroupId(deptId);
			noticeMapper.insertUserNotice(userNotice);
			//给发送公告的用户添加公告新消息数量
			if(!userSet[i].equals(userId)){
				String noticeKey = Constants.NEW_NOTICE_TOTAL_KEY+userSet[i];
				//提供首页监控获取最新的方法使用
				JedisUtils.incr(noticeKey);
			}
		}
	}

	@Override
	public List<Notice> findList(Notice notice) throws Exception {
		return noticeMapper.findList(notice);
	}

	@Override
	public Page<Notice> findPage(Page<Notice> page, Notice notice) throws Exception {
		// 设置分页参数
		notice.setPage(page);
		// 执行分页查询
		List<Notice> list = null;
		//ALL: 查询全部；PRI: 查询个人公告；
		if("PRI".equals(notice.getQueryType())){
			list = noticeMapper.findMyCreateNoticList(notice);
		}else{
			list = noticeMapper.findNoticListByUserId(notice);
		}
		
		//将查询的公告信息根据ID查询参与人回填到对象中
		/*for(Notice result : list){
			String noticeId = result.getId();
			String participant = noticeMapper.findParticipantByNoticeId(noticeId);
			result.setParticipant(participant);
		}*/
		
		//清空个人日报更新redis记录数
		JedisUtils.set(Constants.NEW_NOTICE_TOTAL_KEY+notice.getUserId(),"0",0);
		
		page.setList(list);
		return page;
	}
	
	/**
	 * 查询同自己相关的最新公告消息
	 * @param notice
	 * @return
	 */
	@Override
	public Notice findNewNotic2One(Notice notice) throws Exception{
		//该属性标识是否清空消息队列【0：正常刷新（清除）;1:发布刷新（不清除）】
		String queryType = notice.getQueryType();
		
		List<Notice> list = noticeMapper.findNoticListByUserId(notice);
		Notice newNotice = null;
		if(!list.isEmpty()){
			newNotice = new Notice();
			newNotice = list.get(0);
			newNotice.setTotal(list.size());
		}else{
			//如果用户一条公告都没有，则显示默认公告例子
			newNotice = (Notice)JedisUtils.getObject(Constants.NOTICE_EXAMPLE_KEY);
			if(null == newNotice){
				newNotice = new Notice();
				newNotice.setNoticeId("000000000");
				newNotice.setSubject("公告标题（样例）");
				newNotice.setContent("1、公告标题长度要求在18个字符内；[:rn]2、公告内容长度要求在200字符内；[:rn]3、首页仅显示最新的一条公告；[:rn]4、如需查看更多公告，请进入公告列表查看。");
				newNotice.setCreateTime("05月01日");
				newNotice.setUserName("管理员");
				JedisUtils.setObject(Constants.NOTICE_EXAMPLE_KEY,newNotice,0);
			}
			
		}
		
		//如果app端请求查询类型为0，则需要清空redis对应记录key,为1则只查询不清空
		if(!"1".equals(queryType)){
			//清空个人日报更新redis记录数
			JedisUtils.set(Constants.NEW_NOTICE_TOTAL_KEY+notice.getUserId(),"0",0);
		}
		
		return newNotice;
	}

	/**
	 * 添加公告信息方法（v1.3.0版本）
	 * @param notice
	 * @throws Exception
	 */
	@Override
	@Transactional(readOnly = false)
	public void addNoticeV130(Notice notice) throws Exception {
		
		//生成一个新的UUID做为该公告的ID
		String userId = notice.getUserId();
		String deptId = notice.getDeptId();
		String noticeId = IdGen.uuid();
		Date date = new Date();
		//添加公告信息到表中
		notice.setId(noticeId);
		notice.setCreateTime(DateUtils.dateToString(date, DateUtils.DATETIME_FORMAT));
		notice.setCreateBy(userId);
		noticeMapper.insert(notice);
		
		//发送公告用户ID列表
		List<String> userIdList = null;
		
		//发送类型(1：所有人；2：我的部门；3：所有管理者，4：运营中心所有主管；)
		String sendType = notice.getSendType();
		//查询公司全体有效用户发送公告
		if("1".equals(sendType)){
			userIdList = userMapper.getEffectiveUserIdList();
			
		}else if("2".equals(sendType)){
			//查询我所在部门所有成员
			User user = new User();
			user.setUserId(userId);
			user.setDeptId(deptId);
			userIdList = userMapper.getDeptUserIdList(user);
			user = null;//手动释放
		}else if("3".equals(sendType)){
			//所有管理者
			userIdList = userMapper.getLeaderIdList();
		}else if("4".equals(sendType)){
			//运营中心所有成员
			User user = new User();
			user.setDeptId("YY001");
			userIdList = userMapper.getDeptUserIdList(user);
			user = null;//手动释放
		}else{
			logger.error("===============公告发送群体类型错误！===============");
		}
		
		
		StringBuffer sbAlias =  new StringBuffer();
		if(null != userIdList && !userIdList.isEmpty()){
			//如果发公告人自己不在集合中则需要添加到list中，否则收不到自己发的跨部门的邮件
			int count = Collections.frequency(userIdList,userId);
			if(count==0){
				userIdList.add(userId);
			}
			
			for(String uId : userIdList){
				UserNotice userNotice = new UserNotice();
				userNotice.setUserId(uId);
				userNotice.setNoticeId(noticeId);
				noticeMapper.insertUserNotice(userNotice);
				
				//给发送公告的用户添加公告新消息数量
				if(!uId.equals(userId)){
					//收集推送人员的用户ID作为推送别名对象
					sbAlias.append(uId).append(",");
					
					String noticeKey = Constants.NEW_NOTICE_TOTAL_KEY+uId;
					//提供首页监控获取最新的方法使用
					JedisUtils.incr(noticeKey);
				}
			}
		}else{
			//如果查询发送群体一个人都没有就只发给自己
			UserNotice userNotice = new UserNotice();
			userNotice.setUserId(userId);
			userNotice.setNoticeId(noticeId);
			noticeMapper.insertUserNotice(userNotice);
		}
		
		//调用推送提醒消息
		String alias = sbAlias.toString();
		if(StringUtils.isNotBlank(alias)){
			UMengPushTools.getInstance().sendNoticeCustomizedcast(alias);
		}
	}
	
	/**
	 * 根据公告ID查询对应公告详情
	 * @param noticeId
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Notice findNoticDetailById(String noticeId) throws Exception{
		Notice notice =  noticeMapper.findNoticDetailById(noticeId);
		
		if(null != notice){
			List picList = null;
			String picUrl = notice.getPicUrl();
			//将图片切割放于list中
			if(StringUtils.isNotBlank(picUrl)){
				picList = new ArrayList();
				String[] picItem = picUrl.split(",");
				for (int i = 0; i < picItem.length; i++) {
					//如果图片url不为空则拼接完整的url地址
					picList.add(picItem[i]);
				}
				//将图片ID置空
				notice.setPicUrl(null);
			}
			
			notice.setPicList(picList);
		}
		
    	return notice;
	}
	
	/**
	 * 根据公告关系映射表进行删除公告
	 * @param noticeId
	 * @return
	 */
	@Override
	@Transactional(readOnly = false)
	public void delNoticeById(String id)throws Exception{	
		
		String userIds = noticeMapper.getNoticeUserById(id);
		noticeMapper.delNoticeById(id);
		noticeMapper.delUserNoticeById(id);
		
		//调用通知撤销推送提醒消息
		if(StringUtils.isNotBlank(userIds)){
			UMengPushTools.getInstance().sendUndoNoticeCustomizedcast(userIds);
		}
	}

	
}
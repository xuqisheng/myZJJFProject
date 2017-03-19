package com.corner.salesman.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.corner.salesman.common.persistence.CrudDao;
import com.corner.salesman.common.persistence.annotation.MyBatisDao;
import com.corner.salesman.model.Notice;
import com.corner.salesman.model.UserNotice;

/**
 * 公告信息DAO接口
 * @author 元宝
 * @version 2016-01-26
 */
@MyBatisDao
public interface NoticeMapper extends CrudDao<Notice> {
	
	/**
	 * 保存用户与公告的关系
	 * @param userNotice
	 * @return
	 */
	public int insertUserNotice(UserNotice userNotice);
	
	/**
	 * 根据用户登陆
	 * @param notice
	 * @return
	 */
	public List<Notice> findNoticListByUserId(Notice notice);
	
	
	/**
	 * 查询我创建的公告
	 * @param notice
	 * @return
	 */
	public List<Notice> findMyCreateNoticList(Notice notice);
	
	/**
	 * 根据通知ID查询会议参与人
	 * @param notice
	 * @return
	 */
	public String findParticipantByNoticeId(String noticeId);
	
	/**
	 * 查询同自己相关的最新公告消息(v1.5.0版本之前)
	 * @param notice
	 * @return
	 */
	public Notice findNewNotic2One(Notice notice);
	
	/**
	 * 查询同自己相关的最新公告消息(v1.6.0版本启用)
	 * @param notice
	 * @return
	 */
	public HashMap<String,Object> getNewNotic2One(Map<String,Object> paramMap);
	
	/**
	 * 根据公告ID查询对应公告详情
	 * @param noticeId
	 * @return
	 */
	public Notice findNoticDetailById(String noticeId);
	
	/**
	 * 根据公告编号删除
	 * @param id
	 * @return
	 */
	public void delNoticeById(String id);
	
	/**
	 * 根据公告关系映射表对应的公告id删除
	 * @param id
	 * @return
	 */
	public void delUserNoticeById(String id);
	
	/**
	 * 根据公告ID获取所有涉及的知会人ID拼接字符串（eg: userId1,userId2,userId3...）
	 * @param noticeId
	 * @return
	 */
	public String getNoticeUserById(String noticeId);
}
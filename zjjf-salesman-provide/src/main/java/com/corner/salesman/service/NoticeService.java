package com.corner.salesman.service;

import java.util.List;

import com.corner.salesman.commons.persistence.Page;
import com.corner.salesman.model.Notice;

/**
 * @描述： 通知业务成
 * @author Longx
 * @创建时间  2016-01-26
 */
public interface NoticeService {

	public List<Notice> findList(Notice notice) throws Exception;
	
	public Page<Notice> findPage(Page<Notice> page, Notice notice) throws Exception;
	
	public void addNotice(Notice notice) throws Exception;
	
	public void delNoticeById(String id) throws Exception;
	
	
	/**
	 * 添加公告信息方法（v1.3.0版本）
	 * @param notice
	 * @throws Exception
	 */
	public void addNoticeV130(Notice notice) throws Exception;
	
	/**
	 * 查询同自己相关的最新公告消息
	 * @param notice
	 * @return
	 */
	public Notice findNewNotic2One(Notice notice) throws Exception;
	
	/**
	 * 根据公告ID查询对应公告详情
	 * @param noticeId
	 * @return
	 */
	public Notice findNoticDetailById(String noticeId) throws Exception;
	
	
}

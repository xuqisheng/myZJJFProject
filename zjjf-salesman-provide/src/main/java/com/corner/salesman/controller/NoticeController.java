package com.corner.salesman.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.corner.salesman.common.persistence.AppObject;
import com.corner.salesman.common.persistence.AppPage;
import com.corner.salesman.common.utils.EmojiFilter;
import com.corner.salesman.common.utils.Encodes;
import com.corner.salesman.common.utils.Json;
import com.corner.salesman.common.utils.StringUtils;
import com.corner.salesman.commons.persistence.Page;
import com.corner.salesman.model.Notice;
import com.corner.salesman.service.NoticeService;
import com.corner.salesman.service.UserService;

/**
 * 公告信息Controller
 * @author 元宝
 * @version 2016-01-26
 */
@Controller
@RequestMapping("/mobile/notice")
public class NoticeController {
	
	private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);

	@Autowired
	private NoticeService noticeService;
	@Autowired
	private UserService userService;
	
	/**
	 * 添加公告信息(v1.2.1版本以前的接口)
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"addNotice"})
	public Object addNoticeInfo(Notice notice, HttpServletRequest request, HttpServletResponse response, Model model){
		Json json = new Json();
		try {
			logger.info("提交新增公告信息为：{}", JSON.toJSON(notice));
			
			String subject = notice.getSubject();
			String content = notice.getContent();
			String assigns = notice.getAssigns();
			String userId = notice.getUserId();
			
			if(StringUtils.isBlank(userId)){
				json.setMsg("发布人ID不能为空！");
				json.setSuccess(false);
				json.setCode("500");
				return json;
			}
			if(StringUtils.isBlank(subject)){
				json.setMsg("公告主题不能为空！");
				json.setSuccess(false);
				json.setCode("500");
				return json;
			}
			if(StringUtils.isBlank(content)){
				json.setMsg("公告内容不能为空！");
				json.setSuccess(false);
				json.setCode("500");
				return json;
			}
			if(StringUtils.isBlank(assigns)){
				json.setMsg("公告知会人不能为空！");
				json.setSuccess(false);
				json.setCode("500");
				return json;
			}
			
			//对app端转码的中文进行解码处理
			subject = Encodes.urlDecode(subject);
			content = Encodes.urlDecode(content);
			logger.info("添加公告主题和内容分别为subject:{},content:{}",subject,content);
			if(EmojiFilter.containsEmoji(subject)){
				json.setMsg("公告主题不能包含特殊字符！");
				json.setSuccess(false);
				json.setCode("500");
				return json;
			}
			if(EmojiFilter.containsEmoji(content)){
				json.setMsg("公告内容不能包含特殊字符！");
				json.setSuccess(false);
				json.setCode("500");
				return json;
			}
			
			notice.setSubject(subject);
			notice.setContent(content);
			
			noticeService.addNotice(notice);
			 json.setMsg("添加公告信息成功！");
			 json.setSuccess(true);
		} catch (Exception e) {
			logger.info("添加公告信息异常：{}",e.getMessage());
			json.setMsg("添加公告信息异常！");
			json.setSuccess(false);
			json.setCode("500");
		}
		return json;
	}
	
	
	/**
	 * 添加公告信息(v1.2.1版本以后的接口)
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"addNoticeV130"})
	public Object addNoticeV130(Notice notice, HttpServletRequest request, HttpServletResponse response, Model model){
		Json json = new Json();
		try {
			//logger.info("提交新增公告信息为：{}", JSON.toJSON(notice));
			
			String subject = notice.getSubject();
			String content = notice.getContent();
			String sendType = notice.getSendType();
			String userId = notice.getUserId();
			String deptId = notice.getDeptId();
			
			if(StringUtils.isBlank(userId)){
				json.setMsg("发布人ID不能为空！");
				json.setSuccess(false);
				json.setCode("500");
				return json;
			}
			if(StringUtils.isBlank(deptId)){
				json.setMsg("部门ID不能为空！");
				json.setSuccess(false);
				json.setCode("500");
				return json;
			}
			if(StringUtils.isBlank(subject)){
				json.setMsg("公告主题不能为空！");
				json.setSuccess(false);
				json.setCode("500");
				return json;
			}
			if(StringUtils.isBlank(content)){
				json.setMsg("公告内容不能为空！");
				json.setSuccess(false);
				json.setCode("500");
				return json;
			}
			if(StringUtils.isBlank(sendType)){
				json.setMsg("公告知会群体类型不能为空！");
				json.setSuccess(false);
				json.setCode("500");
				return json;
			}
			
			int num = userService.checkIsLeader(userId);
			if(num<=0){
				json.setMsg("警告：您没有管理员权限，不能发布公告！");
				json.setSuccess(false);
				json.setCode("500");
				return json;
			}
			
			//对app端转码的中文进行解码处理
			subject = Encodes.urlDecode(subject);
			content = Encodes.urlDecode(content);
//			logger.info("添加公告主题和内容分别为subject:{},content:{}",subject,content);
			if(EmojiFilter.containsEmoji(subject)){
				json.setMsg("公告主题不能包含特殊字符！");
				json.setSuccess(false);
				json.setCode("500");
				return json;
			}
			if(EmojiFilter.containsEmoji(content)){
				json.setMsg("公告内容不能包含特殊字符！");
				json.setSuccess(false);
				json.setCode("500");
				return json;
			}
			
			notice.setSubject(subject);
			notice.setContent(content);
			notice.setGroupId(deptId);
			
			noticeService.addNoticeV130(notice);
			 json.setMsg("添加公告信息成功！");
			 json.setSuccess(true);
		} catch (Exception e) {
			logger.info("添加公告信息异常：{}",e.getMessage());
			json.setMsg("添加公告信息异常！");
			json.setSuccess(false);
			json.setCode("500");
		}
		return json;
	}
	
	/**
	 * 获取公告列表信息
	 * @param notice
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"getNoticeList"})
	public Object getNoticeList(Notice notice, HttpServletRequest request, HttpServletResponse response, Model model){
		Json json = new Json();
		try {
			 List<Notice> noticeList = noticeService.findList(notice);
			 //将list转换成统一格式返回app(需要加一层对象包装)
			 AppObject<Notice> resultObj = new AppObject<Notice>();
			 resultObj.setList(noticeList);
			 json.setData(resultObj);
			 json.setMsg("获取公告列表信息成功！");
			 json.setSuccess(true);
			//logger.info("获取公告列表信息为：{}", JSONArray.toJSONString(json));
		} catch (Exception e) {
			logger.info("获取公告列表信息异常：{}",e.getMessage());
			json.setMsg("获取公告列表信息异常！");
			json.setSuccess(false);
			json.setCode("500");
		}
		return json;
	}
	
	/**
	 * 获取公告列表信息
	 * @param notice
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"getNoticePageList"})
	public Object getNoticePageList(Notice notice, HttpServletRequest request, HttpServletResponse response, Model model){
		Json json = new Json();
		try {
			 Page<Notice> page = noticeService.findPage(new Page<Notice>(request, response), notice);
			 AppPage<Notice> target = new AppPage<Notice>();
			 BeanUtils.copyProperties(page, target);
			 json.setData(target);
			 json.setMsg("获取公告列表信息成功！");
			 json.setSuccess(true);
			//logger.info("获取公告列表信息为：{}", JSONArray.toJSONString(json));
		} catch (Exception e) {
			logger.info("获取公告列表信息异常：{}",e.getMessage());
			json.setMsg("获取公告列表信息异常！");
			json.setSuccess(false);
			json.setCode("500");
		}
		return json;
	}
	
	/**
	 * 获取最新的公告信息
	 * @param notice
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"getNewNotice"})
	public Object getNewNotice(Notice notice, HttpServletRequest request, HttpServletResponse response, Model model){
		Json json = new Json();
		try {
			 notice = noticeService.findNewNotic2One(notice);
			 AppObject<Notice> resultObj = new AppObject<Notice>();
			 resultObj.setResultObj(notice);
			 json.setData(resultObj);
			 json.setMsg("获取最新的公告信息成功！");
			 json.setSuccess(true);
		} catch (Exception e) {
			logger.info("获取最新的公告信息异常：{}",e.getMessage());
			json.setMsg("获取最新的公告信息异常！");
			json.setSuccess(false);
			json.setCode("500");
		}
		return json;
	}
	
	/**
	 * 获取最新的公告信息
	 * @param notice
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"getNoticeDetail"})
	public Object getNoticeDetail(Notice notice, HttpServletRequest request, HttpServletResponse response, Model model){
		Json json = new Json();
		try {
			 String noticeId = notice.getNoticeId();
			
			 if(StringUtils.isBlank(noticeId)){
				json.setMsg("公告ID不能为空！");
				json.setSuccess(false);
				json.setCode("500");
				return json;
			 }
			 
			 if("000000000".equals(noticeId)){
				json.setMsg("公告信息不存在！");
				json.setSuccess(false);
				json.setCode("500");
				return json;
			 }
			
			 notice = noticeService.findNoticDetailById(noticeId);
			 AppObject<Notice> resultObj = new AppObject<Notice>();
			 resultObj.setResultObj(notice);
			 json.setData(resultObj);
			 json.setMsg("获取最新的公告信息成功！");
			 json.setSuccess(true);
		} catch (Exception e) {
			logger.info("获取最新的公告信息异常：{}",e.getMessage());
			json.setMsg("获取最新的公告信息异常！");
			json.setSuccess(false);
			json.setCode("500");
		}
		return json;
	}
	/**
	 * 删除公告信息
	 * @param request
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"delNoticeById"})
	public Object delNoticeById(Notice notice,HttpServletRequest request,Model model){
		Json json = new Json();
		try {
			
			 String noticeId = notice.getNoticeId();
			 if(StringUtils.isBlank(noticeId)){
				json.setMsg("公告ID不能为空！");
				json.setSuccess(false);
				json.setCode("500");
				return json;
			 }
			 noticeService.delNoticeById(noticeId);
			 json.setMsg("删除公告信息成功！");
			 json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg("删除公告信息异常！");
			json.setSuccess(false);
			json.setCode("500");
		}
		
		return json;
		
	}
}

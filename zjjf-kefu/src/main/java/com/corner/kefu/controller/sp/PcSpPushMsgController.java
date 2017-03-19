/**
 * 
 */
package com.corner.kefu.controller.sp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.corner.core.beans.CustomerService;
import com.corner.core.beans.SpGroup;
import com.corner.core.beans.SpPushMsg;
import com.corner.core.beans.SpPushMsgMap;
import com.corner.core.beans.SpPushMsgType;
import com.corner.core.beans.vo.Pager;
import com.corner.core.utils.ResponseUtils;
import com.corner.core.utils.StringUtil;
import com.corner.core.utils.message.tools.UMengMessageVo;
import com.corner.kefu.beans.vo.sp.SpGroupVo;
import com.corner.kefu.beans.vo.sp.StoreVo;
import com.corner.kefu.config.SystemKeys;
import com.corner.kefu.controller.KefuBaseWebController;
import com.corner.kefu.enums.UMengPushType;
import com.corner.kefu.service.sp.SpGroupService;
import com.corner.kefu.service.sp.SpSpMsgService;
import com.corner.kefu.service.sp.SpStoreService;
import com.corner.kefu.uMeng.tools.PcSpPushUMengPushTools;

/**
 * 
 * @ClassName: PcSpPushMsgController
 * 
 * @Description: 消息中心控制表
 * 
 * @author: 杨开泰
 * 
 * @date: 2015年11月26日 下午4:22:08
 */
@RequestMapping("/customer/messageManage")
@Controller
public class PcSpPushMsgController extends KefuBaseWebController {

	private static final Logger logger = LoggerFactory.getLogger(PcSpPushMsgController.class);

	@Autowired
	SpSpMsgService spMsgService;

	@Autowired
	SpStoreService storeService;

	@Autowired
	SpGroupService spGroupService;
	
	
	/**
	 * 查询被选中的店铺集合
	 * @param request
	 * @return
	 */
	@RequestMapping("/getSelectedStFromSpPush.do")
	@ResponseBody
	public Object getSelectedStoreListFromSpPushMsgMap(HttpServletRequest request) {
		try {
			String id = request.getParameter("id");
			if (StringUtil.stringIsNullOrEmpty(id)) {
				return ResponseUtils.sendMsg(false, "参数有误!");
			}
			List<StoreVo> list = storeService.getSelectedStoreListFromSpPushMsgMap(id);
			return ResponseUtils.sendMsg(true, list);
		} catch (Exception e) {
			logger.error("PcSpPushMsgController============>getSelectedStFromSpPush.do",e);
			return ResponseUtils.sendMsg(false, "程序出错!");
		}
	}
	
	
	/**
	 * 查询被选中的定格集合
	 * @param request
	 * @return
	 */
	@RequestMapping("/getSelectedSpGFromSpPush.do")
	@ResponseBody
	public Object getSelectedSpGroupListFromSpPushMsgMap(HttpServletRequest request) {
		try {
			String id = request.getParameter("id");
			if (StringUtil.stringIsNullOrEmpty(id)) {
				return ResponseUtils.sendMsg(false, "参数有误!");
			}
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("id", id);
			List<SpGroupVo> selectedSpGroupListFromSpPushMsgMapList = spGroupService.getSelectedSpGroupListFromSpPushMsgMap(map);
			return ResponseUtils.sendMsg(true, selectedSpGroupListFromSpPushMsgMapList);
		} catch (Exception e) {
			logger.error("PcSpPushMsgController============>getSelectedSpGFromSpPush.do",e);
			return ResponseUtils.sendMsg(false, "程序出错!");
		}
	}
	
	
	/**
	 * 编辑入口进入查询,被选中的定格放在列表前面
	 * @param request
	 * @return
	 */
	@RequestMapping("/getSelectedSpGroupList.do")
	@ResponseBody
	public Object getSelectedSpGroupList(HttpServletRequest request,Model model) {
		try {
			String id = request.getParameter("id");
			Integer pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
			Integer pageSize = Integer.parseInt(request.getParameter("pageSize"));
			if(StringUtil.stringIsNullOrEmpty(id)||StringUtil.stringIsNullOrEmpty(pageIndex+"")||StringUtil.stringIsNullOrEmpty(pageSize+"")){
				return ResponseUtils.sendMsg(false, "参数有误!");
			}
			Map<String, Object> map  = new HashMap<String,Object>();
			map.put("pageIndex", pageIndex*pageSize);
			map.put("pageSize",pageSize);
			map.put("id",id);
			String keyStr = request.getParameter("keyStr");
			if(!StringUtil.stringIsNullOrEmpty(keyStr)){
				map.put("keyStr", keyStr);
				model.addAttribute("keyStr", keyStr);
			}
			List<SpGroupVo> selectedSpGroupList = spGroupService.getSelectedSpGroupList(map);
			Integer count = 0;
			if(selectedSpGroupList!=null&&selectedSpGroupList.size()>0){
				count = spGroupService.getMatchConditionSpGroupCount(map);
			}
			return new Pager<>(count, selectedSpGroupList);
		} catch (Exception e) {
			logger.error(e.toString(),e);
			return ResponseUtils.sendMsg(false, "程序出错!");
		}
	}
	
	
	
	
	/**
	 * 编辑入口进入,查询被选中的店铺(选中的放在最前面)
	 * @return
	 */
	@RequestMapping("/getSelectedStoreList.do")
	@ResponseBody
	public Object getSelectedStoreList(HttpServletRequest request) {
		try {
			String id = request.getParameter("id");
			Integer pageIndex =Integer.parseInt(request.getParameter("pageIndex"));
			Integer pageSize =Integer.parseInt(request.getParameter("pageSize"));
			if(StringUtil.stringIsNullOrEmpty("id")||StringUtil.stringIsNullOrEmpty(pageIndex+"")||StringUtil.stringIsNullOrEmpty(pageSize+"")){
				return ResponseUtils.sendMsg(false, "参数有误!");
			}
			String keyStr = request.getParameter("keyStr");
			pageSize = SystemKeys.commonPageSize;
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("pageIndex", pageIndex*pageSize);
			map.put("pageSize", pageSize);
			map.put("id", id);
			if(!StringUtil.stringIsNullOrEmpty(keyStr)){
				map.put("keyStr", keyStr);
			}
			List<StoreVo> selectedStoreList=storeService.getSelectedStoreList(map);
			Integer count = 0;
			if(selectedStoreList!=null && selectedStoreList.size()>0){
				//count 查询Store表总量
				count = storeService.getSpPushMsgCount(map);
			}
			return new Pager<StoreVo>(count, selectedStoreList);
		} catch (Exception e) {
			logger.error("PcSpPushMsgController============>getSelectedStoreList.do",e);
			return ResponseUtils.sendMsg(false, "程序出错!");
		}
	}
	
	
	/**
	 * 查询所有店铺
	 * @param request
	 * @return
	 */
	@RequestMapping("/getAllStore.do")
	@ResponseBody
	public Object getStoreList(HttpServletRequest request) {
		try {
			Integer pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
			Integer pageSize = Integer.parseInt(request.getParameter("pageSize"));
			//pageSize = SystemKeys.commonPageSize;
            if(StringUtil.stringIsNullOrEmpty(pageIndex+"") || StringUtil.stringIsNullOrEmpty(pageSize+"")){
            	return ResponseUtils.sendMsg(false,"程序出错");
            }
            Map<String,Object> map = new HashMap<String,Object>();
			map.put("pageIndex", pageIndex*pageSize);
			map.put("pageSize", pageSize);
			String keyStr = request.getParameter("keyStr");
			if(!StringUtil.stringIsNullOrEmpty(keyStr)){
				map.put("keyStr", keyStr);
			}
			List<StoreVo> list = storeService.getStoreListWithSpGroup(map);
			Integer total = 0;
			if (list!=null&&list.size()>0) {
				map.put("pageSize", 0);
				total = storeService.getStoreListWithSpGroup(map).size();
			}
			return new Pager<>(total, list);
		} catch (Exception e) {
			logger.error("PcSpPushMsgController============>getAllStore.do",e);
			return ResponseUtils.sendMsg(false, "");
		}
		
	}
	
	
	
	
	/**
	 * 查询所有定格
	 * @param request
	 * @return
	 */
	@RequestMapping("/getSpGroupList.do")
    @ResponseBody	
	public Object getSpGroupList(HttpServletRequest request) {
		try {
			Integer pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
			Integer pageSize = Integer.parseInt(request.getParameter("pageSize"));
			pageSize = SystemKeys.commonPageSize;
            if(StringUtil.stringIsNullOrEmpty(pageIndex+"") || StringUtil.stringIsNullOrEmpty(pageSize+"")){
            	return ResponseUtils.sendMsg(false,"程序出错");
            }
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("pageIndex", pageIndex*pageSize);
			map.put("pageSize", pageSize);
			String keyStr = request.getParameter("keyStr");
			if(!StringUtil.stringIsNullOrEmpty(keyStr)){
				map.put("keyStr", keyStr);
			}
		    List<SpGroup> list = spGroupService.getAllSpGroup(map);
		    Integer total = 0;
		    if(list!=null&&list.size()>0){
		    	map.put("pageSize",0);
		    	total = spGroupService.getAllSpGroup(map).size();
		    }
		    return new Pager<SpGroup>(total, list);
		} catch (Exception e) {
			logger.error("PcSpPushMsgController============>getSpGroupList.do",e);
			return ResponseUtils.sendMsg(false, "");
		}
	}
	
	
	/**
	 * 跳转到消息列表
	 * 
	 * @return
	 */
	@RequestMapping("/toIndex.do")
	public String toIndex(HttpServletRequest request, SpPushMsg spPushMsg, Model model) {
		try {
			int pageIndex = SystemKeys.commonPageIndex;
			int pageSize = SystemKeys.commonPageSize;
			String pageIndexStr = request.getParameter("pageIndex");
			if (!StringUtil.stringIsNullOrEmpty(pageIndexStr)) {
				pageIndex = Integer.parseInt(pageIndexStr);
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("pageIndex", (pageIndex - 1) * pageSize);
			map.put("pageSize", pageSize);
			map.put("keyStr", spPushMsg.getMsgTitle());
			List<SpPushMsg> list = spMsgService.getSpPushMsgList(map);
			Integer count = 0;
			if (list != null && list.size() > 0) {
				map.put("pageSize", 0);
				count = spMsgService.getSpPushMsgList(map).size();
			}
			model.addAttribute("list", list);
			model.addAttribute("keyStr", spPushMsg.getMsgTitle());
			this.pageUtil((int)map.get("pageIndex"), count, pageSize, request, model);
			return "/message/message";
		} catch (Exception e) {
			logger.error("PcSpPushMsgController============>toIndex.do", e);
			return error("程序出错!", model, request);
		}
	}

	/**
	 * 跳转到消息编辑页面/新增页面
	 * 
	 * @return
	 */
	@RequestMapping("/toEdit.do")
	public String toEdit(HttpServletRequest request, Model model) {
		try {
			String id = request.getParameter("id");
			if(!StringUtil.stringIsNullOrEmpty(id)){
				SpPushMsg spPushMsg = spMsgService.getSpPushMsgById(id);
				model.addAttribute("spPushMsg", spPushMsg);
			}
			String pageIndex = request.getParameter("pageIndex");
			if(!StringUtil.stringIsNullOrEmpty(pageIndex)){
				model.addAttribute("pageIndex", pageIndex);
			}
			List<SpPushMsgType> spPushMsgTypeList = spMsgService.getSpPushMSGList();
			model.addAttribute("typeList", spPushMsgTypeList);
			return "/message/message-edit";
		} catch (Exception e) {
			logger.error("PcSpPushMsgController============>toEdit.do", e);
			return error("程序出错!", model, request);
		}
	}
	
	
	
	/**
	 * 发送消息
	 * (发送加保存)
	 * @return
	 */
	@RequestMapping("/sendMsg.do")
	@ResponseBody
	public Object sendMsg(SpPushMsg spPushMsg, HttpServletRequest request) {
		try {
			// 参数校验
			if (StringUtil.stringIsNullOrEmpty(spPushMsg.getMsgTitle()) || StringUtil.stringIsNullOrEmpty(spPushMsg.getContent()) || StringUtil.stringIsNullOrEmpty(spPushMsg.getMsgType() + "") || StringUtil.stringIsNullOrEmpty(spPushMsg.getUmPushType())) {
				return ResponseUtils.sendMsg(false, "参数有误!");
			}
			spPushMsg.setTicker(spPushMsg.getMsgTitle());
			//如果是编辑进入的,先要维护下SpPushMsgMap表(不管有没有重新分配用户)
			if(!StringUtil.stringIsNullOrEmpty(request.getParameter("isEdit"))){
				spMsgService.removeMsgBySpPushMsgId(spPushMsg.getId());
				spPushMsg.setStatus((byte)1);
				commonSaveMessage(request,spPushMsg,true);
			}else {
				commonSaveMessage(request,spPushMsg,false);
			}
			//保存消息
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("spPushMsg", spPushMsg);
			List<StoreVo> storeList = null;
			List<String> spGroupList = null;
			if (spPushMsg.getUmPushType().equals(UMengPushType.ALL_PUSH.toString())) {
				umsendMsg(map,spPushMsg,request);
			} else if (spPushMsg.getUmPushType().equals(UMengPushType.SPGROUP_PUSH.toString())) {
				//spGroupList = spMsgService.getSelectedSpGroupList(spPushMsg.getId());
				String[] spGroupIdArr = request.getParameterValues("spGroupIdArr");
				spGroupList=Arrays.asList(spGroupIdArr);				
				map.put("spGroupList", spGroupList);
				umsendMsg(map,spPushMsg,request);
			} else if (spPushMsg.getUmPushType().equals(UMengPushType.SHOP_PUSH.toString())) {
				Map<String, Object> conditionMap = new HashMap<String, Object>();
				String[] storeIdArr = request.getParameterValues("storeIdArr");
				conditionMap.put("storeIdArr", storeIdArr);
				storeList = storeService.getAllStoreList(conditionMap);
				map.put("storeList", storeList);
				umsendMsg(map,spPushMsg,request);
			}
			return ResponseUtils.sendMsg(true, "发送消息成功!");
		}catch(BadSqlGrammarException e){
			return ResponseUtils.sendMsg(false, "指定对象没有店铺");
		}
		catch (Exception e) {
			logger.error("PcSpPushMsgController============>sendMsg.do", e);
			return ResponseUtils.sendMsg(false, "程序出错!");
		}
	}

	/**
	 * 发送消息,调用友盟api
	 * 
	 * @param map
	 * @param spPushMsg 
	 * @throws Exception
	 */
	private void umsendMsg(Map<String, Object> map, SpPushMsg spPushMsg,HttpServletRequest request) throws Exception {
		UMengMessageVo uMengMessageVo = new UMengMessageVo();
		//设置 uMengMessageVo
		uMengMessageVo.setAlert(spPushMsg.getMsgTitle());
		uMengMessageVo.setTicker(spPushMsg.getTicker());
		uMengMessageVo.setTitle(spPushMsg.getMsgTitle());
		uMengMessageVo.setText(spPushMsg.getContent());
		PcSpPushUMengPushTools uMengPushTools = PcSpPushUMengPushTools.getInstance();
		//UMengPushTools uMengPushTools = UMengPushTools.getInstance();
		if (map.get("storeList") == null && map.get("spGroupList") == null) {
			// 广播
			uMengPushTools.sendAndroidBroadcast(uMengMessageVo);// android 广播
			uMengPushTools.sendIOSBroadcast(uMengMessageVo);// ios
			uMengPushTools.sendIOSBroadcast2(uMengMessageVo);// ios
		} else if (map.get("storeList") != null) {
			// 自定义播,按店铺id发送
			List<StoreVo> storeList = (List<StoreVo>) map.get("storeList");
			for (Iterator<StoreVo> iterator = storeList.iterator(); iterator.hasNext();) {
				StoreVo store = iterator.next();
                uMengMessageVo.setAlias(store.getMobile());
                uMengMessageVo.setAlias_type("android");
				uMengPushTools.sendAndroidCustomizedcast(uMengMessageVo);// android
				uMengMessageVo.setAlias_type("iOS");
				uMengPushTools.sendIOSCustomizedcast(uMengMessageVo);// ios
				uMengPushTools.sendIOSCustomizedcast2(uMengMessageVo);//appStore版ios
			}
		} else {
			// 按定格发送
			List<String> list = (List<String>) map.get("spGroupList");
			for (String spGroupId : list) {
				uMengMessageVo.setAlias("spGroupId_"+spGroupId);
                uMengMessageVo.setAlias_type("spGroupId");
				uMengPushTools.sendAndroidCustomizedcast(uMengMessageVo);//android
				uMengPushTools.sendIOSCustomizedcast(uMengMessageVo);//ios
				uMengPushTools.sendIOSCustomizedcast2(uMengMessageVo);//appStore版ios
			}
		}
	}
	

	/**
	 * 保存消息
	 * 
	 * @return
	 */
	@RequestMapping("/saveMessage.do")
	@ResponseBody
	public Object saveMessage(HttpServletRequest request, SpPushMsg spPushMsg) {
		try {
			// 参数校验
			if (StringUtil.stringIsNullOrEmpty(spPushMsg.getMsgTitle()) || StringUtil.stringIsNullOrEmpty(spPushMsg.getContent()) 
			  || StringUtil.stringIsNullOrEmpty(spPushMsg.getMsgType() + "") || StringUtil.stringIsNullOrEmpty(spPushMsg.getUmPushType())) {
				return ResponseUtils.sendMsg(false, "参数有误!");
			}
			CustomerService service = (CustomerService) request.getSession().getAttribute("service");
			//TODO 如果service为空,返回
			spPushMsg.setPublishId(service.getId()+"");
			spPushMsg.setPublishName(service.getUserName());
			Boolean flag = false;
			//如果是编辑进入的,先要维护下SpPushMsgMap表(不管有没有重新分配用户)
			if(!StringUtil.stringIsNullOrEmpty(request.getParameter("isEdit"))){
				spMsgService.removeMsgBySpPushMsgId(spPushMsg.getId());
				commonSaveMessage(request,spPushMsg,true);
			}else {
				commonSaveMessage(request,spPushMsg,false);
			}
			return ResponseUtils.sendMsg(true, "保存成功!");
		}catch(BadSqlGrammarException e){
			return ResponseUtils.sendMsg(false, "指定发送对象没有店铺");
		}
		catch (Exception e) {
			logger.error(e.toString(), e);
			return ResponseUtils.sendMsg(false, "程序出错!");
		}
	}

    /**
     * 保存消息
     * @param request
     * @param spPushMsg
     * @param flag 表示是否编辑进入
     * @throws Exception
     */
	private void commonSaveMessage(HttpServletRequest request, SpPushMsg spPushMsg,Boolean flag) throws Exception {
			// 根据分配给谁,查询Store集合
			List<StoreVo> storeList = null;
			Map<String, Object> conditionMap = new HashMap<String, Object>();
			if (spPushMsg.getUmPushType().equals(UMengPushType.ALL_PUSH.toString())) {
				// 查询所有审核通过的店铺
				storeList = storeService.getAllStoreList(conditionMap);
			} else if (spPushMsg.getUmPushType().equals(UMengPushType.SPGROUP_PUSH.toString())) {
				// 取定格id数组,查询对应店铺集合
				String[] spGroupIdArr = request.getParameterValues("spGroupIdArr");// 去定格id数组
				if(spGroupIdArr==null||spGroupIdArr.length==0){
					throw new BadSqlGrammarException("", "", new SQLException());
				}
				conditionMap.put("spGroupIdArr", spGroupIdArr);
				storeList = storeService.getAllStoreList(conditionMap);
			} else if (spPushMsg.getUmPushType().equals(UMengPushType.SHOP_PUSH.toString())) {
				// 取店铺id数组,形成店铺集合
				String[] storeIdArr = request.getParameterValues("storeIdArr");
				if(storeIdArr==null||storeIdArr.length==0){
					throw new BadSqlGrammarException("", "", new SQLException());
				}
				conditionMap.put("storeIdArr", storeIdArr);
				storeList = storeService.getAllStoreList(conditionMap);
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("spPushMsg", spPushMsg);
			map.put("storeList", storeList);
			map.put("flag", flag);//表示是否从编辑入口进入
			List<SpPushMsgMap> msgMapList = new ArrayList<SpPushMsgMap>();
			for (Iterator<StoreVo> iterator = storeList.iterator(); iterator.hasNext();) {
				StoreVo storeVo = iterator.next();
				SpPushMsgMap spPushMsgMap = new SpPushMsgMap();
				spPushMsgMap.setMsgId(spPushMsg.getId());
				spPushMsgMap.setStoreId(Integer.parseInt(storeVo.getId()));
				spPushMsgMap.setUserId(storeVo.getUserid());
				msgMapList.add(spPushMsgMap);
			}
			map.put("msgMapList", msgMapList);
			spPushMsg.setPublishTime(new Date());
			spMsgService.addSpPushMsg(map);	
	}

}

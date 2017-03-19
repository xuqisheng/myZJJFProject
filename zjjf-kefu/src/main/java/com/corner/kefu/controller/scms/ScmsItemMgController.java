package com.corner.kefu.controller.scms;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.core.dao.ScmsManagerMapper;
import com.corner.core.utils.JSONUtil;
import com.corner.core.utils.ResponseUtils;
import com.corner.core.utils.StringUtil;
import com.corner.kefu.beans.ro.scms.ScmsItemMgRo;
import com.corner.kefu.config.SCMSConstants;
import com.corner.kefu.controller.KefuBaseWebController;
import com.corner.kefu.dao.ScmsGroupMgMapper;
import com.corner.kefu.dao.ScmsManagerMgMapper;
import com.corner.kefu.service.scms.ScmsItemMgService;
import com.corner.kefu.service.scms.ScmsManagerMgService;
import com.corner.kefu.service.scms.ScmsMinimumMgService;

@Controller
@RequestMapping(value="/scms/item")
public class ScmsItemMgController extends KefuBaseWebController{

	@Autowired
	ScmsItemMgService scmsItemMgService;
	@Autowired
	ScmsManagerMapper scmsManagerMapper;
	@Autowired
	ScmsGroupMgMapper scmsGroupMgMapper;
	@Autowired
	ScmsManagerMgMapper scmsManagerMgMapper;
	@Autowired
	ScmsMinimumMgService scmsMinimumMgService;
	@Autowired
	ScmsManagerMgService scmsManagerMgService;
	
	private static Logger logger = LoggerFactory.getLogger(ScmsItemMgController.class);
	/**
	 * 
	* @Title: addOrUpdate 
	* @Description: 添加编辑商品
	* @param @param request
	* @param @param command
	* @param @return    设定文件
	* @return Object    返回类型
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @throws
	 */
	@RequestMapping(value = "/addOrUpdate.do")
	public String addOrUpdate(HttpServletRequest request, ScmsItemMgRo command) {
		logger.info("查询商品"+JSONUtil.objectToJSONString(command));
		if(StringUtil.stringIsNullOrEmpty(command.getManagerId())){
			logger.info("缺少经销商ID");
			return "";
		}
		request.setAttribute("scmsManager", scmsManagerMapper.selectByPrimaryKey(command.getManagerId()));
		request.setAttribute("scmsGroups" , scmsGroupMgMapper.findScmsGroupByManagerId(command.getManagerId()));
		if("add".equals(command.getIsUpdate())){
			logger.info("跳转添加商品界面");
			return SCMSConstants.DEALER_GOODS_GOODS_ADD;
		}else if("minnum".equals(command.getIsUpdate())){
			logger.info("跳转设置启购量界面");
			request.setAttribute("scmsMinimums" , scmsMinimumMgService.getScmsMinimumByManagerId(command.getManagerId()));
			return SCMSConstants.DEALER_GOODS_DELIVERY_MINNUM;
		}else{
			logger.info("跳转编辑界面");
			if(StringUtil.stringIsNullOrEmpty(command.getId())){
				logger.info("缺少商品ID");
				return "";
			}
			return SCMSConstants.DEALER_GOODS_GOODS_EDIT;
		}
	}
	/**
	 * 
	* @Title: listPage 
	* @Description: 分页查询商品信息 
	* @param @param request
	* @param @param command
	* @param @return    设定文件
	* @return Object    返回类型
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @throws
	 */
	@RequestMapping(value = "/listPage.do")
	public String listPage(HttpServletRequest request, ScmsItemMgRo command , Model model) {
		logger.info("列表查询开始："+JSONUtil.objectToJSONString(command));
		Pager<ScmsItemMgRo> pager = scmsItemMgService.getScmsItemPageList(command);
		request.setAttribute("scmsManager" ,scmsManagerMgService.findById(command.getManagerId()));
		request.setAttribute("scmsItems", pager.getList());
		request.setAttribute("scmsItemMgRo", command);
		request.setAttribute("size", pager.getTotalSize());
		this.pageUtil(command.getPageIndex(), pager.getTotalSize(), command.getPageSize(), request, model);
		return SCMSConstants.DEALER_GOODS_GOODS;
		
	}
	
	/**
	 * 
	* @Title: addScmsItem 
	* @Description: 添加商品信息 
	* @param @param request
	* @param @param command
	* @param @return    设定文件
	* @return Object    返回类型
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @throws
	 */
	@RequestMapping(value = "/addScmsItem.do")
	@ResponseBody
	public Object addScmsItem(HttpServletRequest request, ScmsItemMgRo command) {
		try {
			logger.info("添加商品"+JSONUtil.objectToJSONString(command));
			if(StringUtil.stringIsNullOrEmpty(command.getItemBaseId()+"" , command.getGoodName()))
				return ResponseUtils.sendMsg(false, SCMSConstants.IS_NOT_NULL);
			/**校验是否有空数据录入**/
			try {
				if(command.getGroupIds() == null || command.getGroupIds().length == 0){
					/**根据传入的参数判断是单条还是多条添加**/
					if(command.getAreaName() == null || command.getGroupId() == null || command.getAreaPrice() == null || command.getZjjfPrice() == null || command.getMarketPrice()==null)
						return ResponseUtils.sendMsg(false,  SCMSConstants.IS_NOT_NULL);
				}
				else if(command.getAreaNames() == null || command.getGroupIds() == null || command.getAreaPrices() == null || command.getZjjfPrices() == null || command.getMarketPrices()==null){
					return ResponseUtils.sendMsg(false,  SCMSConstants.IS_NOT_NULL);
				}else{
					for (int i = 0; i < command.getAreaNames().length; i++) {
						if(StringUtil.stringIsNullOrEmpty(command.getAreaNames()[i] , command.getGroupIds()[i],command.getAreaPrices()[i],command.getZjjfPrices()[i],command.getMarketPrices()[i])){
							return ResponseUtils.sendMsg(false, SCMSConstants.IS_NOT_NULL); 
						}
					}
				}
			} catch (Exception e) {
				return ResponseUtils.sendMsg(false, "录入信息错误"); 
			}
			
			ModelMsg modelMsg = scmsItemMgService.addScmsItem(command);
			if(modelMsg.isSuccess())
				return ResponseUtils.sendMsg(true, "添加成功");
			else
				return ResponseUtils.sendMsg(false, modelMsg.getMessage());
		} catch (Exception e) {
			return ResponseUtils.sendMsg(false, "交易异常"+e);
		}
	}

	/**
	 * 
	 * @Title: findByMdseIdOrName 
	 * @Description: 查询商品单条
	 * @param @param request
	 * @param @param command
	 * @param @return    设定文件
	 * @return Object    返回类型
	 * @author 孟星魂	mengxinghun@izjjf.cn
	 * @throws
	 */
	@RequestMapping(value = "/findByMdseIdOrName.do")
	@ResponseBody
	public Object findByMdseIdOrName(HttpServletRequest request, ScmsItemMgRo command) {
		try {
			logger.info("查询商品"+JSONUtil.objectToJSONString(command));
			if(StringUtil.stringIsNullOrEmpty(command.getMdseId()) && StringUtil.stringIsNullOrEmpty(command.getName()))
				return ResponseUtils.sendMsg(false, SCMSConstants.IS_NOT_NULL);
			ScmsItemMgRo itemMgRo =  scmsItemMgService.findByMdseIdOrName(command); 
			if(itemMgRo != null)
				return ResponseUtils.sendMsg(true, itemMgRo);
			else
				return ResponseUtils.sendMsg(false, SCMSConstants.NOT_FOUND);
		} catch (Exception e) {
			return ResponseUtils.sendMsg(false, "交易异常"+e);
		}
	}
	/**
	 * 
	 * @Title: findById 
	 * @Description: 查询商品单条
	 * @param @param request
	 * @param @param command
	 * @param @return    设定文件
	 * @return Object    返回类型
	 * @author 孟星魂	mengxinghun@izjjf.cn
	 * @throws
	 */
	@RequestMapping(value = "/findById.do")
	@ResponseBody
	public Object findById(HttpServletRequest request, ScmsItemMgRo command) {
		try {
			logger.info("查询商品"+JSONUtil.objectToJSONString(command));
			if(StringUtil.stringIsNullOrEmpty(command.getId()))
				return ResponseUtils.sendMsg(false, SCMSConstants.IS_NOT_NULL);
			ScmsItemMgRo itemMgRo =  scmsItemMgService.findById(command); 
			if(itemMgRo != null)
				return ResponseUtils.sendMsg(true, itemMgRo);
			else
				return ResponseUtils.sendMsg(false, SCMSConstants.NOT_FOUND);
		} catch (Exception e) {
			return ResponseUtils.sendMsg(false, "交易异常"+e);
		}
	}
	/**
	 * 
	* @Title: findByItemId 
	* @Description: 查询商品 
	* @param @param request
	* @param @param command
	* @param @return    设定文件
	* @return Object    返回类型
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @throws
	 */
	@RequestMapping(value = "/findByItemId.do")
	@ResponseBody
	public Object findByItemId(HttpServletRequest request, ScmsItemMgRo command) {
		try {
			logger.info("查询商品:"+command.getId());
			if(StringUtil.stringIsNullOrEmpty(command.getId() + ""))
				return ResponseUtils.sendMsg(false, SCMSConstants.IS_NOT_NULL);
			ScmsItemMgRo scmsItemMgRo = scmsItemMgService.findById(command);
			if(scmsItemMgRo == null){
				return ResponseUtils.sendMsg(false, SCMSConstants.IS_NOT_NULL);
			}
			List<ScmsItemMgRo> itemMgRos =  scmsItemMgService.findByItemIdAndMonthAndYear(scmsItemMgRo); 
			if(itemMgRos != null && itemMgRos.size() != 0)
				return ResponseUtils.sendMsg(true, itemMgRos);
			else
				return ResponseUtils.sendMsg(false, SCMSConstants.NOT_FOUND);
		} catch (Exception e) {
			return ResponseUtils.sendMsg(false, "交易异常"+e);
		}
	}
	
	/**
	 * 
	* @Title: updateScmsItem 
	* @Description: 修改商品信息 多条
	* @param @param request
	* @param @param command
	* @param @return    设定文件
	* @return Object    返回类型
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @throws
	 */
	@RequestMapping(value = "/updateScmsItem.do")
	@ResponseBody
	public Object updateScmsItem(HttpServletRequest request, ScmsItemMgRo command) {
		try {
			logger.info("修改商品"+JSONUtil.objectToJSONString(command));
			if(StringUtil.stringIsNullOrEmpty(command.getItemBaseId()+"" , command.getGoodName()))
				return ResponseUtils.sendMsg(false, SCMSConstants.IS_NOT_NULL);
			/**校验是否有空数据录入**/
			try {
				if(command.getGroupIds() == null || command.getGroupIds().length == 0){
					/**根据传入的参数判断是单条还是多条添加**/
					if(command.getId() == null || command.getAreaName() == null || command.getGroupId() == null || command.getAreaPrice() == null || command.getZjjfPrice() == null || command.getMarketPrice()==null)
						return ResponseUtils.sendMsg(false, SCMSConstants.IS_NOT_NULL);
				}
				else if(command.getIds() == null || command.getAreaNames() == null || command.getGroupIds() == null || command.getAreaPrices() == null || command.getZjjfPrices() == null || command.getMarketPrices()==null){
					return ResponseUtils.sendMsg(false, SCMSConstants.IS_NOT_NULL);
				}else{
					for (int i = 0; i < command.getAreaNames().length; i++) {
						if(StringUtil.stringIsNullOrEmpty(command.getAreaNames()[i] , command.getGroupIds()[i],command.getAreaPrices()[i],command.getZjjfPrices()[i],command.getMarketPrices()[i])){
							return ResponseUtils.sendMsg(false, SCMSConstants.IS_NOT_NULL); 
						}
					}
				}
			} catch (Exception e) {
				return ResponseUtils.sendMsg(false, SCMSConstants.IS_NOT_NULL); 
			}
			if(StringUtil.stringIsNullOrEmpty(command.getItemBaseId()+""))
				return ResponseUtils.sendMsg(false, SCMSConstants.IS_NOT_NULL);
			ModelMsg modelMsg =  scmsItemMgService.updateScmsItem(command); 
			return ResponseUtils.sendMsg(modelMsg.isSuccess(), modelMsg.getMessage());
		} catch (Exception e) {
			return ResponseUtils.sendMsg(false, "交易异常"+e);
		}
	}
	/**
	 * 
	* @Title: updateByItemId 
	* @Description: 修改上下架信息多条 
	* @param @param request
	* @param @param command
	* @param @return    设定文件
	* @return Object    返回类型
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @throws
	 */
	@RequestMapping(value = "/updateByItemId.do")
	@ResponseBody
	public Object updateByItemId(HttpServletRequest request, ScmsItemMgRo command) {
		try {
			logger.info("查询商品"+JSONUtil.objectToJSONString(command));
			if(StringUtil.stringIsNullOrEmpty(command.getItemBaseId() + ""))
				return ResponseUtils.sendMsg(false, SCMSConstants.IS_NOT_NULL);
			command.setId("");
			ModelMsg modelMsg =  scmsItemMgService.updateScmsItem(command); 
			if(modelMsg.isSuccess())
				return ResponseUtils.sendMsg(true, "交易成功");
			else
				return ResponseUtils.sendMsg(false, SCMSConstants.NOT_FOUND);
		} catch (Exception e) {
			return ResponseUtils.sendMsg(false, "交易异常"+e);
		}
	}
	/**
	 * 
	* @Title: updateById 
	* @Description: 修改商品信息单条
	* @param @param request
	* @param @param command
	* @param @return    设定文件
	* @return Object    返回类型
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @throws
	 */
	@RequestMapping(value = "/updateById.do")
	@ResponseBody
	public Object updateById(HttpServletRequest request, ScmsItemMgRo command) {
		try {
			logger.info("查询商品"+JSONUtil.objectToJSONString(command));
			if(StringUtil.stringIsNullOrEmpty(command.getId() + ""))
				return ResponseUtils.sendMsg(false, SCMSConstants.IS_NOT_NULL);
			ModelMsg modelMsg =  scmsItemMgService.updateScmsItemById(command); 
			if(modelMsg.isSuccess())
				return ResponseUtils.sendMsg(true, "交易成功");
			else
				return ResponseUtils.sendMsg(false, SCMSConstants.NOT_FOUND);
		} catch (Exception e) {
			return ResponseUtils.sendMsg(false, "交易异常"+e);
		}
	}
	/**
	 * 
	* @Title: deleteByItemId 
	* @Description: 删除商品信息多条
	* @param request
	* @param command
	* @param @return    设定文件
	* @return Object    返回类型
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @throws
	 */
	@RequestMapping(value = "/updateScmsItemStatusOrIsDelete.do")
	@ResponseBody
	public Object updateScmsItemStatusOrIsDelete(HttpServletRequest request, ScmsItemMgRo command) {
		try {
			logger.info("查询商品"+JSONUtil.objectToJSONString(command));
			if(StringUtil.stringIsNullOrEmpty(command.getItemBaseId() + "" , command.getMonth() +"" , command.getYear()+""))
				return ResponseUtils.sendMsg(false, SCMSConstants.IS_NOT_NULL);
			if("delete".equals(command.getIsUpdate())){
				command.setIsDelete(true);
			}else{
				command.setIsDelete(false);
			}
			ModelMsg modelMsg =  scmsItemMgService.updateScmsItemStatusOrIsDelete(command); 
			if(modelMsg.isSuccess())
				return ResponseUtils.sendMsg(true, "交易成功");
			else
				return ResponseUtils.sendMsg(false, SCMSConstants.NOT_FOUND);
		} catch (Exception e) {
			return ResponseUtils.sendMsg(false, "交易异常"+e);
		}
	}
	/**
	 * 
	* @Title: deleteById 
	* @Description: 删除商品信息一条
	* @param request
	* @param command
	* @param @return    设定文件
	* @return Object    返回类型
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @throws
	 */
	@RequestMapping(value = "/deleteById.do")
	@ResponseBody
	public Object deleteById(HttpServletRequest request, ScmsItemMgRo command) {
		try {
			logger.info("查询商品"+JSONUtil.objectToJSONString(command));
			if(StringUtil.stringIsNullOrEmpty(command.getId()))
				return ResponseUtils.sendMsg(false, SCMSConstants.IS_NOT_NULL);
			ModelMsg modelMsg =  scmsItemMgService.deleteById(command); 
			if(modelMsg.isSuccess())
				return ResponseUtils.sendMsg(true, "交易成功");
			else
				return ResponseUtils.sendMsg(false, SCMSConstants.NOT_FOUND);
		} catch (Exception e) {
			return ResponseUtils.sendMsg(false, "交易异常"+e);
		}
	}
	
	/**
	 * 
	 * @Title: getItemBaseList
	 * @Description: 查询基础商品信息
	 * @param @param request
	 * @param @param command
	 * @param @return    设定文件
	 * @return Object    返回类型
	 * @author 孟星魂	mengxinghun@izjjf.cn
	 * @throws
	 */
	@RequestMapping(value = "/getItemBaseList.do")
	@ResponseBody
	public Object getItemBaseList(HttpServletRequest request, ScmsItemMgRo command) {
		try {
			ModelMsg msg =  scmsItemMgService.getItemBaseList(command); 
			if(msg.isSuccess())
				return ResponseUtils.sendMsg(true, msg.getData());
			else
				return ResponseUtils.sendMsg(false, msg.getMessage());
		} catch (Exception e) {
			return ResponseUtils.sendMsg(false, "交易异常"+e);
		}
	}
	/**
	 * 
	 * @Title: findByMdseId 
	 * @Description: 通过基础商品条形码查询此商品所以信息
	 * @param @param request
	 * @param @param command
	 * @param @return    设定文件
	 * @return Object    返回类型
	 * @author 孟星魂	mengxinghun@izjjf.cn
	 * @throws
	 */
	@RequestMapping(value = "/findByMdseId.do")
	@ResponseBody
	public Object findByMdseId(HttpServletRequest request, ScmsItemMgRo command) {
		try {
			logger.info("查询商品"+JSONUtil.objectToJSONString(command));
			if(StringUtil.stringIsNullOrEmpty(command.getMdseId()))
				return ResponseUtils.sendMsg(false, SCMSConstants.IS_NOT_NULL);
			ModelMsg msg =  scmsItemMgService.findByMdseId(command); 
			if(msg.isSuccess())
				return ResponseUtils.sendMsg(true, msg.getData());
			else
				return ResponseUtils.sendMsg(false, msg.getMessage());
		} catch (Exception e) {
			return ResponseUtils.sendMsg(false, "交易异常"+e);
		}
	}
	@RequestMapping(value = "/findItemByName.do")
	@ResponseBody
	public Object findItemByName(HttpServletRequest request, ScmsItemMgRo command , String query) {
		try {
			command.setName(query);
			logger.info("查询商品-----区域编号："+command.getGroupId() +",名称："+command.getName());
			if(StringUtil.stringIsNullOrEmpty(command.getGroupId()))
				return ResponseUtils.sendMsg(false, SCMSConstants.IS_NOT_NULL);
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("groupId", command.getGroupId());
			map.put("name", query.trim());
			ModelMsg msg =  scmsItemMgService.findItemByName(map); 
			if(msg.isSuccess())
				return ResponseUtils.sendMsg(true, msg.getData());
			else
				return ResponseUtils.sendMsg(false, msg.getMessage());
		} catch (Exception e) {
			return ResponseUtils.sendMsg(false, "交易异常"+e);
		}
	}
	
}


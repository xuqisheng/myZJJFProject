package com.corner.kefu.controller.sp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.corner.core.beans.ItemCatelog;
import com.corner.core.utils.ResponseUtils;
import com.corner.kefu.beans.ro.ItemCatelogRo;
import com.corner.kefu.beans.vo.sp.ItemCatelogVo;
import com.corner.kefu.config.BusinessCodeConts;
import com.corner.kefu.controller.KefuBaseWebController;
import com.corner.kefu.service.ItemCatelogService;
import com.corner.kefu.service.sp.SpItemCatelogService;
import com.corner.kefu.service.sp.SpSupplierService;

@RequestMapping("/customer/itemCatelog")
@Controller
public class PcItemCatelogController extends KefuBaseWebController implements BusinessCodeConts {
	
	@Autowired
    SpItemCatelogService itemCatelogService1;	
	
	@Autowired
	SpSupplierService spSupplierService;
	
	@Autowired
	ItemCatelogService itemCatelogService;

	
	
	
    /**
     * 	
    * @Title: getTreeItemCatelog 
    * @Description:获取一二级分类，包括分类下有哪些商品的一个json数据
    * @param @return
    * @return Object    返回类型
    *@author 杨开泰 yangkaitai@izjjf.cn
    * @throws
     */
	@RequestMapping("/getTreeItemCatelog.do")
	@ResponseBody
	public Object getTreeItemCatelog() {
		try {
			List<ItemCatelogVo> list = itemCatelogService.getTreeItemCateLogAndItemBase();
			return ResponseUtils.sendMsg(true, list);
		} catch (Exception e) {
			logger.error("",e);
			return ResponseUtils.sendMsg(false, e);
		}
	}
	
	
	
	
	
	
	
	
	
	/**
	 * 根据pid获取所有分类
	* @Title
	* @Description: TODO 
	* @param @param yiJiId
	* @param @return
	* @2016年1月5日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	@ResponseBody
	@RequestMapping("getAllItemCateByPid.do")
	public Object getAllItemCateByPid(Integer pid){
		Map<String, Object> map = new HashMap<String, Object>();
		 try {
			map.put("pid", pid);
			List<ItemCatelog> itemCatelogErJiList = itemCatelogService1.getAllItemCateByPid(map);
			return ResponseUtils.sendMsg(true, itemCatelogErJiList);
		} catch (Exception e) {
			return ResponseUtils.sendMsg(false);
		}
	}
	
	/**
	 * 
	 * @Title: queryItemCateByPid
	 * @date 2016年8月24日  下午5:31:06
	 * @author 小武
	 * @version  七彩虹
	 * @param pid
	 * @return
	 */
	@ResponseBody
	@RequestMapping("queryItemCateByPid.do")
	public Object queryItemCateByPid(Integer pid){
		Map<String, Object> map = new HashMap<String, Object>();
		 try {
			map.put("pid", pid);
			List data = itemCatelogService1.getAllItemCateByPid(map);
			return ResponseUtils.sendMsg(true, "查询分类数据成功","www.izjjf.com",allbusiness_ok,data);
		} catch (Exception e) {
			return ResponseUtils.sendMsg(false);
		}
	}
	
	
	/**
	 * 查询顶级分类的数据
	 * @Title: queryTopItemCate
	 * @date 2016年8月24日  下午5:32:18
	 * @author 小武
	 * @version  七彩虹
	 * @return
	 */
	@ResponseBody
	@RequestMapping("queryTopItemCate.do")
	public Object queryTopItemCate(){
		Map<String, Object> map = new HashMap<String, Object>();
		 try {
			map.put("pid", "0");
			List data = itemCatelogService1.getAllItemCateByPid(map);
			return ResponseUtils.sendMsg(true, "查询分类数据成功","www.izjjf.com",allbusiness_ok,data);
		} catch (Exception e) {
			return ResponseUtils.sendMsg(false);
		}
	}
	
	
    /**
     * 
    * @Title: loadItemCateLogList 
    * @Description:获取商品分类
    * @param @return
    * @return Object    返回类型
    *@author 杨开泰 yangkaitai@izjjf.cn
    * @throws
     */
	@RequestMapping("/loadItemCateLogList.do")
	@ResponseBody
	public Object loadItemCateLogList() {
		try {
			List<ItemCatelogVo> list = itemCatelogService.getItemCateLogList();
			return ResponseUtils.sendMsg(true, list);
		} catch (Exception e) {
			logger.error("",e);
			return ResponseUtils.sendMsg(false, e.toString());
		}
	}
	
	
	
	
	
	
	
	
	/**
	 * 获取商品分类
	 * 
	 * @author Dick 2015年5月19日
	 * @Email 823882651@qq.com
	 * @Desc
	 * @return
	 */
	@RequestMapping(value = "/getGoodlistInfo.do")
	@ResponseBody
	public Object getGoodlistInfo(ItemCatelogRo item, HttpServletRequest request) throws Exception{
		List<ItemCatelog> list = itemCatelogService.getAllItemCate(item);
		if (list == null) {
			return ResponseUtils.sendMsg(false, "对象不存在", request);
		}
		List<ItemCatelog> list_item = new ArrayList<ItemCatelog>();// 二级分类
		List<ItemCatelog> list_logs = new ArrayList<ItemCatelog>();// 一级分类
		List<ItemCatelog> list_items = new ArrayList<ItemCatelog>();// 一、二级分类

		Map<String, Object> map1 = new HashMap<String, Object>();
		Map<Integer, String> map3 = new HashMap<Integer, String>();
		if ("-1".equals(item.getSpid())) {
			for (ItemCatelog itemCatelog : list) {
				if ("0".equals(itemCatelog.getSpid())) {
					list_logs.add(itemCatelog);// 一级分类
				}
			}
			for (ItemCatelog itemCate : list_logs) {
				for (ItemCatelog itemCatelog : list) {
					if (itemCatelog.getSpid().contains(itemCate.getId().toString())) {
						list_item.add(itemCatelog);// 二级分类
					}
				}
				itemCate.setSecondMenu(list_item);
				list_items.add(itemCate);
			}
			for (Iterator<ItemCatelog> iterator = list_logs.iterator(); iterator.hasNext();) {
				ItemCatelog itemCatelog = (ItemCatelog) iterator.next();
				Map<Integer, String> map2 = new HashMap<Integer, String>();
				for (Iterator<ItemCatelog> iterator2 = list_item.iterator(); iterator2.hasNext();) {
					ItemCatelog itemCatelog2 = (ItemCatelog) iterator2.next();
					if (itemCatelog2.getPid().equals(itemCatelog.getId())) {
						map2.put(itemCatelog2.getId(), itemCatelog2.getName());
					}
				}
				map1.put(itemCatelog.getName(), map2);// 将一级分类和二级分类封装成json各式
			}
			for (Iterator<ItemCatelog> iterator = list_logs.iterator(); iterator.hasNext();) {
				ItemCatelog itemCatelog = (ItemCatelog) iterator.next();
				map3.put(itemCatelog.getId(), itemCatelog.getName());
			}
		}
		//List<Supplier> res = spSupplierService.getSupplier();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("listItem", list_item);
		map.put("listLogs", list_logs);
		return map;
	}
}

/**   
* @Title: PcStoreController.java 
* @Package com.corner.pc.web.controller 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年3月14日 下午5:06:02 
* @version V1.0   
*/

package com.corner.kefu.controller.sp;

import java.util.Date;
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

import com.corner.core.beans.SpAcGroup;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.core.utils.ResponseUtils;
import com.corner.core.utils.StringUtil;
import com.corner.kefu.beans.ro.sp.SpAcGroupRo;
import com.corner.kefu.beans.vo.sp.SpAcGroupVo;
import com.corner.kefu.beans.vo.sp.StoreVo;
import com.corner.kefu.controller.KefuBaseWebController;
import com.corner.kefu.service.sp.SpPcStoreService;

/** 
* @ClassName: PcStoreController 
* @Description:pc端店铺管理
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年3月14日 下午5:06:02 
*  
*/
@Controller
@RequestMapping(value = "/Customer/store")
public class PcStoreController extends KefuBaseWebController {

	private static final Logger logger = LoggerFactory.getLogger(PcStoreController.class);

	private static final String SHOPGROUP = "shop-group/";

	@Autowired
	SpPcStoreService pcStoreService;
	
	/**
	 * 
	* @Title: getStoreGroupList 
	* @Description:获取用户组表
	* @param @return
	* @return String    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/getStoreGroupList.do")
	public String getStoreGroupList(HttpServletRequest request, SpAcGroupRo spAcGroupRo, Model model) {
		String keyStr = request.getParameter("keyStr");
		if(!StringUtil.stringIsNullOrEmpty(keyStr)){
			spAcGroupRo.setKeyStr(keyStr);
			model.addAttribute("keyStr", keyStr);
		}
		Pager<SpAcGroupVo> pager = pcStoreService.getPagerList(spAcGroupRo);
		List<SpAcGroupVo> list = pager.getList();
		Integer count = pager.getTotalSize();
		model.addAttribute("list", list);
		this.pageUtil(spAcGroupRo.getPageIndex(), count, spAcGroupRo.getPageSize(), request, model);
		return SHOPGROUP + "index";
	}
	/**
	 * 
	* @Title: getSpAcGroupWithStoreList 
	* @Description:
	* @param @return
	* @return String    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/getSpAcGroupWithStoreList.do")
	public String getSpAcGroupWithStoreList(HttpServletRequest request, SpAcGroupRo spAcGroupRo, Model model) {
		String keyStr = request.getParameter("keyStr");
		if(!StringUtil.stringIsNullOrEmpty(keyStr)){
			spAcGroupRo.setKeyStr(keyStr);
			model.addAttribute("keyStr", keyStr);
		}
		Pager<StoreVo> pager = pcStoreService.getSpAcGroupWithStoreList(spAcGroupRo);
		List<StoreVo> list = pager.getList();
		Integer count = pager.getTotalSize();
		model.addAttribute("list", list);
		model.addAttribute("id", spAcGroupRo.getId());
		this.pageUtil(spAcGroupRo.getPageIndex(), count, spAcGroupRo.getPageSize(), request, model);
		return SHOPGROUP + "manage-user";
	}
	
	/**
	 * 
	* @Title: addStoreIntoSpACGroup 
	* @Description:
	* @param @param request
	* @param @return
	* @return Object    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/addStoreIntoSpACGroup.do")
	@ResponseBody
	public Object addStoreIntoSpACGroup(HttpServletRequest request)  throws Exception{
		String spAcGroupId = request.getParameter("spAcGroupId");
		String storeIdStr = request.getParameter("storeIdStr");
		String[] storeIdArr = storeIdStr.split(",");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("spAcGroupId", spAcGroupId);
		map.put("storeIdArr", storeIdArr);
		ModelMsg modelMsg = pcStoreService.addStoreIntoSpACGroup(map);
		return ResponseUtils.sendMsg(modelMsg.isSuccess() , modelMsg.getMessage());
	}
	@RequestMapping("selectByPrimaryKey.do")
	@ResponseBody
	public Object selectByPrimaryKey(Model model,HttpServletRequest request,String id){
		try {
			SpAcGroup spAcGroup = pcStoreService.selectByPrimaryKey(id);
			if(spAcGroup != null){
				return ResponseUtils.sendMsg(true, spAcGroup);
			}else{
				return ResponseUtils.sendMsg(false);
			}
		} catch (Exception e) {
			logger.error("",e);
			return error("出错了！", model, request);
		}
	}
	
	/**
	 * 
	* @Title: deleteStoreFromSpACGroup 
	* @Description:
	* @param @return
	* @return Object    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/deleteStoreFromSpACGroup.do")
	@ResponseBody
	public Object deleteStoreFromSpACGroup(HttpServletRequest request,SpAcGroupRo spAcGroupRo)  throws Exception{
		String storeIdStr = request.getParameter("storeIdStr");
		String storeIdArr[] = storeIdStr.split(",");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("spAcGroupId", spAcGroupRo.getId());
		map.put("storeIdArr", storeIdArr);
		ModelMsg modelMsg = pcStoreService.deleteStoreFromSpACGroup(map);
		return ResponseUtils.sendMsg(modelMsg.isSuccess() , modelMsg.getMessage());
	}
	/**
	 * 
	* @Title: deleteSpAcGroup 
	* @Description:
	* @param @return
	* @return Object    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/deleteSpAcGroup.do")
	@ResponseBody
	public Object deleteSpAcGroup(HttpServletRequest request) throws Exception{
		String id = request.getParameter("id");
		ModelMsg modelMsg = pcStoreService.deleteSpAcGroup(id); 
		return ResponseUtils.sendMsg(modelMsg.isSuccess() , modelMsg.getMessage());
	}
	/**
	 * 
	* @Title: getStoreList 
	* @Description:获取所有不在该用户组的店铺集合
	* @param @param request
	* @param @param model
	* @param @param spAcGroupRo
	* @param @return
	* @return String    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/getStoreList.do")
	public String getStoreList(HttpServletRequest request, Model model, SpAcGroupRo spAcGroupRo) {
		String keyStr = request.getParameter("keyStr");
		if(!StringUtil.stringIsNullOrEmpty(keyStr)){
			spAcGroupRo.setKeyStr(keyStr);
			model.addAttribute("keyStr", keyStr);
		}
		Pager<StoreVo> pager = pcStoreService.getAllNoShopGroupStoreList(spAcGroupRo);
		List<StoreVo> list = pager.getList();
		Integer count = pager.getTotalSize();
		this.pageUtil(spAcGroupRo.getPageIndex(), count, spAcGroupRo.getPageSize(), request, model);
		model.addAttribute("list", list);
		model.addAttribute("id", spAcGroupRo.getId());
		return SHOPGROUP + "manage-user-add";
	}
	
	/**
	 * 
	* @Title: add 
	* @Description:新增店铺用户组
	* @param @param request
	* @param @return
	* @return Object    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/addStoreGroup.do")
	@ResponseBody
	public Object add(HttpServletRequest request)  throws Exception{
		
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String code = request.getParameter("code");
		String areaId = request.getParameter("areaId");
		String cityId = request.getParameter("cityId");
		String provinceId = request.getParameter("provinceId");
		if(areaId.equals("-1") || cityId.equals("-1") || provinceId.equals("-1")){
			return ResponseUtils.sendMsg(false,"请选择区域！");
		}
		SpAcGroup spAcGroup = new SpAcGroup();
		spAcGroup.setAreaId(Integer.parseInt(areaId));
		spAcGroup.setCityId(Integer.parseInt(cityId));
		spAcGroup.setProvinceId(Integer.parseInt(provinceId));
		ModelMsg modelMsg = new ModelMsg(true, "");
		if (StringUtil.stringIsNullOrEmpty(id)) {
			spAcGroup.setName(name);
			spAcGroup.setCode(code);
			spAcGroup.setAddTime(new Date());
			modelMsg = pcStoreService.insertSelective(spAcGroup);
		} else {
			spAcGroup.setId(id);
			spAcGroup.setName(name);
			spAcGroup.setCode(code);
			modelMsg = pcStoreService.updateByPrimaryKeySelective(spAcGroup);
		}
		return ResponseUtils.sendMsg(modelMsg.isSuccess() , modelMsg.getMessage());
	}
	
	/**
	 * 
	* @Title: getAllStoreGroupList 
	* @Description:获取所有有效的用户组  不分页
	* @param @return
	* @return Object    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/getAllStoreGroupList.do")
	@ResponseBody
	public Object getAllStoreGroupList(HttpServletRequest request,SpAcGroupRo spAcGroupRo) {
		String keyStr = request.getParameter("keyStr");
		if(!StringUtil.stringIsNullOrEmpty(keyStr)){
			spAcGroupRo.setKeyStr(keyStr);
		}
		List<SpAcGroup> list = pcStoreService.getAllStoreGroupList(spAcGroupRo);
		return ResponseUtils.sendMsg(true, list);
	}
	
	/**
	 * 
	* @Title: readExcel 
	* @Description:
	* @param @return
	* @return String    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	/*@RequestMapping("/readExcel.do")
	public String readExcel(@RequestParam(value = "spAcGroupId", required = true) String spAcGroupId,
			@RequestParam(value = "excel", required = false) MultipartFile file, Model model, HttpServletRequest request) throws Exception {

		try {
			if (null != file && !file.isEmpty()) {
				String filePath = PathUtil.getClasspath() + UploadKeys.FILEPATHFILE; // 文件上传路径
				String fileName = FileUpload.fileUp(file, filePath, "storeExcel");
				List<Map> listPd = ObjectExcelRead.readExcel(filePath, fileName, 0, 0, 0, null, 0); // 执行读EXCEL操作,读出的数据导入List
				Map<String, Object> map = new HashMap<String,Object>();
				map.put("spAcGroupId", spAcGroupId);
				List<String>list = new ArrayList<String>();
				for (int i = 0; i < listPd.size(); i++) {
					String temp = ((String) listPd.get(i).get("var" + i)).trim();
					if(!StringUtil.stringIsNullOrEmpty(temp)){
						list.add(temp);
					}
				}
				map.put("list", list);
				if(list.size()!=0){
					pcStoreService.batchAddStore(map);
				}
			}
		} catch (Exception e) {
			return error(e.toString(), model, request);
		}
		return "redirect:/Customer/store/getSpAcGroupWithStoreList.do?id="+spAcGroupId;
	}*/
}

/**   
* @Title: SignProtocolController.java 
* @Package com.corner.scms.controller 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年2月24日 上午10:58:06 
* @version V1.0   
*/

package com.corner.scms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.corner.core.beans.PlantProtocol;
import com.corner.core.beans.SignResult;
import com.corner.core.beans.Supplier;
import com.corner.core.utils.ResponseUtils;
import com.corner.core.utils.StringUtil;
import com.corner.scms.service.sp.ScmsPlantProtocoMgService;
import com.corner.scms.service.sp.ScmsSpSignResultMgService;
import com.corner.scms.service.sp.ScmsSupplierMgService;

/**
 * @ClassName: SignProtocolController
 * @Description:签署协议
 * @author 杨开泰 yangkaitai@izjjf.cn
 * @date 2016年2月24日 上午10:58:06
 * 
 */
@Controller
@RequestMapping(value = "/scms/sign")
public class SignProtocolController extends ScmsBaseWebController {

	@Autowired
	ScmsSpSignResultMgService scmsSpSignResultMgService;

	@Autowired
	ScmsPlantProtocoMgService scmsPlantProtocoMgService;
	
	@Autowired
	ScmsSupplierMgService scmsSupplierMgService;

	/**
	 * 
	* @Title: signResult 
	* @Description:签署协议
	* @param @param request
	* @param @return
	* @param @throws Exception
	* @return Object    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/signResult.do")
	@ResponseBody
	public Object signResult(HttpServletRequest request) throws Exception {
		String id = request.getParameter("pr");// 协议id
		if (StringUtil.stringIsNullOrEmpty(id)) {
			return ResponseUtils.sendMsg(false, "缺少必要参数!");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		Supplier supplier = this.getCurrentUser(Supplier.class, request);
		supplier = scmsSupplierMgService.selectByPrimaryKey(supplier.getId());
		map.put("supplier", supplier);
		// 获取批发商需要签署的最新版协议
		PlantProtocol plantProtocol = scmsPlantProtocoMgService.getLastPlantProtocol();
		if (plantProtocol != null)
			map.put("plantProtocol", plantProtocol);
		List<SignResult> list = scmsSpSignResultMgService.selectSignResult(map);
		if (list == null||list.size()==0) {
			scmsSpSignResultMgService.addSignResult(map);
		}
		return ResponseUtils.sendMsg(true);
	}
}

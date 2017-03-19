/**   
* @Title: ERPManagerContractController.java 
* @Package com.corner.kefu.controller.erp 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年9月2日 下午2:00:07 
* @version V1.0   
*/

package com.corner.kefu.controller.erp;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.corner.core.beans.ERPManager;
import com.corner.core.beans.ERPManagerContract;
import com.corner.core.beans.vo.Pager;
import com.corner.core.utils.ResponseUtils;
import com.corner.kefu.beans.ro.erp.ERPManagerContractRo;
import com.corner.kefu.beans.vo.erp.ERPManagerContractVo;
import com.corner.kefu.controller.KefuBaseWebController;
import com.corner.kefu.service.erp.ERPManagerContractService;
import com.corner.kefu.service.erp.ERPManagerService;

/** 
* @ClassName: ERPManagerContractController 
* @Description:供应商合同管理
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年9月2日 下午2:00:07 
*  
*/
@Controller
@RequestMapping("/maContract")
public class ERPManagerContractController extends KefuBaseWebController {
	private static final Logger logger = LoggerFactory.getLogger(ERPManagerContractController.class);
	
	private final static String PATH = "erp/erpManager/";
	
	@Autowired
	ERPManagerContractService eRPManagerContractService;
	
	@Autowired
	ERPManagerService eRPManagerService;
	
	
	
	/**
	 * 
	* @Title: delContract 
	* @Description:删除合同
	* @param @param managerContractRo
	* @param @return
	* @return Object    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/delContract.do")
	@ResponseBody
	public Object delContract(ERPManagerContractRo managerContractRo) {
		if(StringUtils.isEmpty(managerContractRo.getId())){
			return ResponseUtils.sendMsg(false, "缺少合同id!");
		}
		eRPManagerContractService.delContract(managerContractRo);
		return ResponseUtils.sendMsg(true);
	}
	
	
	
	/**
	 * 
	* @Title: getContractList 
	* @Description:获取合同列表
	* @param @return
	* @return Object    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/getContractList.do")
	@ResponseBody
	public Object getContractList(ERPManagerContractRo managerContractRo) {
		Pager<ERPManagerContractVo> pager = eRPManagerContractService.getContractList(managerContractRo);
		pager.setFlag(true);
		return pager;
	}
	
	
	
	@RequestMapping("/save.do")
	@ResponseBody
	public Object save(ERPManagerContractRo contractRo) {
		if(StringUtils.isEmpty(contractRo.getErpManagerId())){
		  return ResponseUtils.sendMsg(false, "缺少供应商id!");	
		}
		ERPManagerContract managerContract = new ERPManagerContract();
		BeanUtils.copyProperties(contractRo, managerContract);
		eRPManagerContractService.save(managerContract);
		return ResponseUtils.sendMsg(true);
	}
	
	
	
	/**
	 * 
	* @Title: toAddContract 
	* @Description:跳转到新建供应商合同页面
	* @param @return
	* @return String    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/toAddContract.do")
    public String toAddContract(ERPManagerContractRo contractRo,Model model,HttpServletRequest request){
		if(StringUtils.isNotEmpty(contractRo.getId())){
			ERPManagerContract contract = eRPManagerContractService.selectByPrimaryKey(contractRo.getId());
			if(contract==null){
				return error("该合同不存在!", model, request);
			}
			if(contract.getIsDelete()){
				return error("该合同已被删除!", model, request);
			}
			ERPManager manager = eRPManagerService.selectByPrimaryKey(contract.getErpManagerId());
			model.addAttribute("contract", contract);
			model.addAttribute("manager", manager);
		}
    	return PATH+"add-supplier-agreement";
    }
	
	
	/**
	 * 
	* @Title: toContractIndex 
	* @Description:跳转到供应商合同管理首页
	* @param @return
	* @return String    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/toContractIndex.do")
	public String toContractIndex() {
		return PATH+"supplier-agreement";
	}
}

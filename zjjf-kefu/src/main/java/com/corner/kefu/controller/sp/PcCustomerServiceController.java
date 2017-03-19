package com.corner.kefu.controller.sp;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.corner.core.beans.CustomerService;
import com.corner.core.beans.vo.Pager;
import com.corner.core.utils.ResponseUtils;
import com.corner.core.utils.StringUtil;
import com.corner.core.utils.safe.MD5;
import com.corner.kefu.beans.ro.sp.CustomerServiceRo;
import com.corner.kefu.beans.vo.sp.CustomerServiceVo;
import com.corner.kefu.controller.KefuBaseWebController;
import com.corner.kefu.service.sp.SpCustomerServiceService;

@Controller
@RequestMapping("/userManager/customerService")
public class PcCustomerServiceController extends KefuBaseWebController {
	private static final Logger logger = LoggerFactory.getLogger(PcCustomerServiceController.class);

	@Autowired
	private SpCustomerServiceService customerServiceService;
//	@Autowired
//	private SpRoleService roleService;
//	@Autowired
//	private SpUserRoleMapService userRoleMapService;
//	@Autowired
//	private SpDepartmentService departmentService;
//	
//	@Autowired
//	private SpPositionService positionService;
//	
//	@RequestMapping("/returnAddAndEditPage.do")
//	public String returnAddAndEditPage(Model model,HttpServletRequest request){
//		//查出所有的角色
//		List<Role> roleList = roleService.getAllRoleIdAndName();
//		//查出所有部门
//		List<Department> departmentList = departmentService.getAllDept();
//		//查出所有的职位
//		List<Position> positionList = positionService.getPostByDept(null);
//		String addEditStr = request.getParameter("addEditStr");
//		if(addEditStr == null||"".equals(addEditStr) ){
//			return this.error("出错了！",model,request);
//		}else if(addEditStr.equals("add")){
//			model.addAttribute("str", "添加用户");
//		}else if(addEditStr.equals("edit")){
//			String customerServiceId = request.getParameter("id");
//			String status = request.getParameter("status");
//			CustomerServiceVo customerServiceVo = customerServiceService.getCustomerServiceById(customerServiceId);
//			customerServiceVo.setPassword(customerServiceVo.getPassword());
//			model.addAttribute("str", "编辑用户");
//			model.addAttribute("customerServiceId", customerServiceId);
//			model.addAttribute("status", status);
//			model.addAttribute("customerServiceVo", customerServiceVo);
//		}
//		model.addAttribute("positionList", positionList);
//		model.addAttribute("departmentList", departmentList);
//		model.addAttribute("addEditStr", addEditStr);
//		model.addAttribute("roleList", roleList);
//		return "PcKdianbao/System/user_edit";
//	}
	
	
//	/**
//	 * 添加用户(客服)
//	* @Title: addCustomerService 
//	* @Description: 
//	* @param @param customerService   
//	* @return void  
//	* @author 龙五 at 2015年12月24日下午2:12:44
//	* @email longwu@izjjf.cn
//	 */
//	@ResponseBody
//	@RequestMapping("addCustomerService.do")
//	public Object addCustomerService(CustomerService customerService,@RequestParam(required=false,value="roleId")String[] roleId,HttpServletRequest request){
//		CustomerService service = (CustomerService)request.getSession().getAttribute("service");
//		if(customerService.getUsername() == null||"".equals(customerService.getUsername())
//			||customerService.getNickname()==null||"".equals(customerService.getNickname())
//			||customerService.getGender()==null||customerService.getStatus()==null
//			||customerService.getDeptid()==null||customerService.getDeptid()==0
//			||customerService.getPostid()==null||customerService.getPostid()==0
//			||customerService.getPassword()==null||"".equals(customerService.getPassword())){
//			return ResponseUtils.sendMsg(false, "参数有误！");
//		}
//		try {
//			customerService.setUpdatetime(new Date());
//			customerService.setCreatetime(new Date());
//			customerService.setCreaterid(service.getId());//登陆用户的id
//			customerService.setPassword(MD5.StringToMd5(customerService.getPassword()));
//			customerServiceService.addCustomerService(customerService, roleId);
//			return ResponseUtils.sendMsg(true, "添加成功！");
//		} catch (Exception e) {
//			logger.error("",e);
//			return ResponseUtils.sendMsg(false, "添加失败！");
//		}
//	}
//	
//	/**
//	 * 修改用户（客服）状态和isDelete
//	* @Title: updateCustomerServiceStatusAndIsDelete 
//	* @Description: 
//	* @param @param updateStatusAndIsDeleteParam   
//	* @return void  
//	* @author 龙五 at 2015年12月24日下午2:49:22
//	* @email longwu@izjjf.cn
//	 */
//	@RequestMapping("updateCustomerServiceStatusAndIsDelete.do")
//	@ResponseBody
//	public Object updateCustomerServiceStatusAndIsDelete(@RequestParam("customerServiceId")String customerServiceId,
//														@RequestParam(required=false,value="status")Byte status,
//														@RequestParam(required=false,value="isDelete")Byte isDelete){
//		Map<String, Object> updateStatusAndIsDeleteParam = new HashMap<String, Object>();
//		updateStatusAndIsDeleteParam.put("customerServiceId", customerServiceId);
//		if(status!=null){
//			updateStatusAndIsDeleteParam.put("status", status);
//		}
//		if(isDelete!=null){
//			updateStatusAndIsDeleteParam.put("isDelete", isDelete);
//		}
//		updateStatusAndIsDeleteParam.put("updateTime", new Date());
//		
//		try {
//			customerServiceService.updateCustomerServiceStatusAndIsDelete(updateStatusAndIsDeleteParam);
//			return ResponseUtils.sendMsg(true);
//		} catch (Exception e) {
//			logger.error("",e);
//			return ResponseUtils.sendMsg(false);
//		}
//	}
//	
//	
//	/**
//	 * 根据用户（客服）id查出当前用户
//	* @Title: getCustomerServiceById 
//	* @Description: 
//	* @param @param CustomerServiceId
//	* @param @return   
//	* @return CustomerServiceVo  
//	* @author 龙五 at 2015年12月25日下午1:42:25
//	* @email longwu@izjjf.cn
//	 */
//	@RequestMapping("getCustomerServiceById.do")
//	@ResponseBody
//	public Object getCustomerServiceById(@RequestParam("customerServiceId")String customerServiceId){
//		if(customerServiceId !=null && !"".equals(customerServiceId)){
//			try {
//				CustomerServiceVo customerServiceVo = customerServiceService.getCustomerServiceById(customerServiceId);
//				if(customerServiceVo!=null){
//					return ResponseUtils.sendMsg(true, customerServiceVo);
//				}else{
//					return ResponseUtils.sendMsg(false, "数据有误！");
//				}
//				
//			} catch (Exception e) {
//				logger.error("",e);
//				return ResponseUtils.sendMsg(false, "数据有误！");
//			}
//		}
//		return ResponseUtils.sendMsg(false, "数据有误！");
//	}
//	
//	/**
//	 * 编辑用户（客服）信息
//	* @Title: updateCustomerService 
//	* @Description: 
//	* @param @param customerService   
//	* @return void  
//	* @author 龙五 at 2015年12月25日下午3:30:35
//	* @email longwu@izjjf.cn
//	 */
//	@RequestMapping("updateCustomerService.do")
//	@ResponseBody
//	public Object updateCustomerService(CustomerService customerService,@RequestParam(required=false,value="roleId")String[] roleId,String customerServiceId){
//		if(customerService.getUsername()==null||"".equals(customerService.getUsername())
//		||customerService.getNickname()==null||"".equals(customerService.getNickname())
//		||customerService.getDeptid()==null||customerService.getPostid()==null){
//			return ResponseUtils.sendMsg(false, "账号,姓名,密码,部门;参数有误！");
//		}
//		if(customerServiceId !=null && !"".equals(customerServiceId)){
//			customerService.setId(customerServiceId);
//		}else{
//			return ResponseUtils.sendMsg(false, "出错了！");
//		}
//		if("".equals(customerService.getPassword())||customerService.getPassword()==null){
//			customerService.setPassword(null);
//		}else{
//			customerService.setPassword(MD5.StringToMd5(customerService.getPassword()));
//		}
//		
//		try {
//			customerService.setUpdatetime(new Date());
//			customerServiceService.updateCustomerService(customerService,roleId);
//			return ResponseUtils.sendMsg(true, "编辑成功！");
//		} catch (Exception e) {
//			logger.error("",e);
//			return ResponseUtils.sendMsg(false, "编辑失败！");
//		}
//		
//	}
	
	/**
	 * 根据条件查询用户列表
	* @Title: getAllCustomerServiceByParam 
	* @Description: 
	* @param @param customerServiceRo
	* @param @return   
	* @return List<CustomerServiceVo>  6
	* @author 龙五 at 2015年12月25日下午5:19:35
	* @email longwu@izjjf.cn
	 */
	@RequestMapping("getAllCustomerServiceByParam.do")
	public String getAllCustomerServiceByParam(HttpServletRequest request, Model model,CustomerServiceRo customerServiceRo){
		String initStatus = request.getParameter("initStatus");
		Integer pageIndex = 1;
		String pageIndexStr = request.getParameter("pageIndex");
		if(!StringUtil.stringIsNullOrEmpty(pageIndexStr)){
			pageIndex = Integer.parseInt(pageIndexStr);
		}
		if(initStatus!=null&&!"".equals(initStatus)){
			customerServiceRo.setStatus(Byte.parseByte(initStatus));
		}
		if(customerServiceRo.getAllName()==null || "".equals(customerServiceRo.getAllName())){
			customerServiceRo.setAllName("");
		}
		try {
			Pager<CustomerServiceVo> CustomerServiceList = customerServiceService.getAllCustomerServiceByParam(customerServiceRo);
			if(CustomerServiceList!=null){
				model.addAttribute("customerServiceRo", customerServiceRo);
				model.addAttribute("CustomerServices", CustomerServiceList.getList());
				pageUtil(pageIndex, CustomerServiceList.getTotalSize(), customerServiceRo.getPageSize(), request, model);
				return "/system/user";
			}else{
				return "/system/user";
			}
		} catch (Exception e) {
			logger.error("",e);
			return this.error("出错了！", model, request);
		}
	}
	
//	/**
//	 * 手机号和账号唯一校验
//	* @Title
//	* @Description: TODO 
//	* @param @param userName
//	* @param @return
//	* @2016年1月7日     
//	* @author 龙五  longwu@izjjf.cn
//	* @return
//	* @throws
//	 */
//	@ResponseBody
//	@RequestMapping("chickUserName.do")
//	  public Object chickUserName(String userName){
//		  try {
//			int num = customerServiceService.chickUserName(userName);
//			if(num>0){
//				return ResponseUtils.sendMsg(true, "账号已存在");
//			}else{
//				return ResponseUtils.sendMsg(false);
//			}
//		} catch (Exception e) {
//			logger.error("验证账号时出错",e);
//			return ResponseUtils.sendMsg(false);
//		}
//	  }
//	@ResponseBody
//	@RequestMapping("chickMobile.do")
//	  public Object chickMobile(String mobile){
//		  try {
//			int num = customerServiceService.chickMobile(mobile);
//			if(num>0){
//				return ResponseUtils.sendMsg(true, "手机号已存在,请更换");
//			}else{
//				return ResponseUtils.sendMsg(false);
//			}
//		} catch (Exception e) {
//			logger.error("验证手机号时出错",e);
//			return ResponseUtils.sendMsg(false);
//		}
//	  }
//	
//	
	
	/**
	 * 查出所有的客服人员
	* @Title
	* @Description: TODO 
	* @param @return
	* @2016年3月31日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	@RequestMapping("getAllCustomerService.do")
	public Object getAllCustomerService(){
		try {
			List<CustomerService> CustomerServiceList = customerServiceService.getAllCustomerService();
			if(CustomerServiceList != null && CustomerServiceList.size() > 0){
				return ResponseUtils.sendMsg(true, CustomerServiceList);
			}else{
				return ResponseUtils.sendMsg(false);
			}
		} catch (Exception e) {
			logger.error("",e);
			return ResponseUtils.sendMsg(false);
		}
		
	}
	
	//跳到修改客服登陆密码页面
	@RequestMapping("returnUpdateLoginPassword.do")
	public String returnUpdateLoginPassword(){
		return "/system/modify-pwd";
	}
	/**
	 * 修改客服登陆密码
	* @Title
	* @Description: TODO 
	* @param @param request
	* @param @param usedPassword
	* @param @param newPassword
	* @param @return
	* @2016年1月12日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	@RequestMapping("/updateLoginPassword.do")
	@ResponseBody
	public Object updateLoginPassword(HttpServletRequest request,String usedPassword,String newPassword){
		//得到客服对象
		CustomerService service = getCurrentUser(CustomerService.class, request);
		String password = customerServiceService.getPasswordById(service.getId());
		try {
			if(MD5.StringToMd5(usedPassword).equals(password)){
				customerServiceService.updateLoginPassword(service.getId(), MD5.StringToMd5(newPassword));
				return ResponseUtils.sendMsg(true, "修改成功,请牢记密码,在下次登录时使用。");
			}else{
				return ResponseUtils.sendMsg(false, "原密码输入有误！");
			}
		} catch (Exception e) {
			logger.error("修改供应商密码时出错了",e);
			return ResponseUtils.sendMsg(false, "修改失败！");
		}
	}
	
}

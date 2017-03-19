package com.corner.kefu.controller.sp;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.corner.core.beans.Region;
import com.corner.core.beans.Salesman;
import com.corner.core.utils.DateStringUtil;
import com.corner.core.utils.ResponseUtils;
import com.corner.core.utils.StringUtil;
import com.corner.kefu.beans.ro.sp.SalesmanRo;
import com.corner.kefu.beans.vo.sp.SalesmanVo;
import com.corner.kefu.controller.KefuBaseWebController;
import com.corner.kefu.service.RegionService;
import com.corner.kefu.service.SystemInfoService;
import com.corner.kefu.service.sp.SpSalesmanService;

/**
 * 业务员控制器
 * @author aimee at 2015年6月9日上午11:45:15
 * @email 1297579898@qq.com
 */
@Controller
@RequestMapping(value="/customer/salesman")
public class PcSalesmanController extends KefuBaseWebController{
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(PcSalesmanController.class);
	@Autowired
	SpSalesmanService salesmanService;
	@Autowired
	RegionService regionService;
	@Autowired
	SystemInfoService systemInfoService;
	/**
	 * 添加业务员
	 * @author aimee at 2015年6月9日下午2:15:34
	 * @email 1297579898@qq.com
	 * @param man
	 * @return
	 */
	@RequestMapping(value="/saveSalesman.do")
	@ResponseBody
	public Object saveSalesman(Salesman man,HttpServletRequest request){
		try {
			//标志是添加还是更新，1是添加，2是更新
			String action = request.getParameter("action");
			if(StringUtil.stringIsNullOrEmpty(man.getMobile(),man.getUserName())){
				return ResponseUtils.sendMsg(false, "手机号或姓名不能为空");
			}
			if(man.getProvince()==-1 || man.getCity()==-1 || man.getArea()==-1){
				return ResponseUtils.sendMsg(false, "请选择区域");
			}
			if(!StringUtil.stringIsNullOrEmpty(man.getIdentitycard())){
				 //定义判别用户身份证号的正则表达式（要么是15位，要么是18位，最后一位可以为字母）  
	            Pattern idNumPattern = Pattern.compile("(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])");  
	            //通过Pattern获得Matcher  
	            Matcher idNumMatcher = idNumPattern.matcher(man.getIdentitycard());  
	            //判断用户输入是否为身份证号  
	            if(idNumMatcher.matches()){
	                //如果是，定义正则表达式提取出身份证中的出生日期  
	                Pattern birthDatePattern= Pattern.compile("\\d{6}(\\d{4})(\\d{2})(\\d{2}).*");//身份证上的前6位以及出生年月日  
	                //通过Pattern获得Matcher  
	                Matcher birthDateMather= birthDatePattern.matcher(man.getIdentitycard());  
	                //通过Matcher获得用户的出生年月日  
	                if(birthDateMather.find()){  
	                    String year = birthDateMather.group(1);  
	                    String month = birthDateMather.group(2);  
	                    String date = birthDateMather.group(3);  
	                    //输出用户的出生年月日  
	                    String birthdate = year+"-"+month+"-"+date;
	                    logger.info("您的出生年月日是：{}",year+"年"+month+"月"+date+"日");
	                    man.setBirthday(DateStringUtil.stringToDate(birthdate));
	                }     
	            }else{  
	                //如果不是，输出信息提示用户  
	            	logger.error("您输入的并不是身份证号");
	            	return ResponseUtils.sendMsg(false, "您输入的并不是身份证号");
	            }  
			}
			//解码用户名
			man.setUserName(decode(man.getUserName()));
			//解码邮箱
			if(!StringUtil.stringIsNullOrEmpty(man.getEmail())){
				man.setEmail(decode(man.getEmail()));
			}
			//解码地址
			if(!StringUtil.stringIsNullOrEmpty(man.getAddress())){
				man.setAddress(decode(man.getAddress()));
			}
//			//根据区域编号获取省编号和市编号
//			RegionVo region = regionService.getPID(man.getArea());
//			if(region==null){
//				return ResponseUtils.sendMsg(false,"所选择的区域对应的省市编号不存在");
//			}
//			man.setProvince(region.getProvinceId());
//			man.setCity(region.getCityId());
			boolean flag = false;
			if(action.equals("1")){//新增
				flag = salesmanService.save(man)>0?true:false;
			}else if(action.equals("2")){//修改
				flag = salesmanService.updateSalesman(man)>0?true:false;
			}
			if(flag){
				return ResponseUtils.sendMsg(true, "操作成功");
			}
			return ResponseUtils.sendMsg(false, "操作失败");
		} catch (Exception e) {
			logger.error("PCSalesmanController's method saveSalesman has an error:{}",e);
		}
		return ResponseUtils.sendMsg(false, "出错了");
	}
	/**
	 * 判断某个手机号是否已经录入
	 * @author aimee at 2015年6月9日下午4:12:13
	 * @email 1297579898@qq.com
	 * @param mobile
	 * @return
	 */
	@RequestMapping(value="/isExitMobile.do")
	@ResponseBody
	public Object isExitMobile(String mobile){
		try {
			if(StringUtil.stringIsNullOrEmpty(mobile)){
				return ResponseUtils.sendMsg(false, "手机号不能为空");
			}
			//判断手机号是否存在
			boolean flag = salesmanService.getCountWithMObile(mobile)>0?true:false;
			if(flag){
				return ResponseUtils.sendMsg(false, "该帐号已经存在");
			}
			return ResponseUtils.sendMsg(true, "该帐号不存在");
		} catch (Exception e) {
			logger.error("PCSalesmanController's method isExitMobile has an error:{}",e);
		}
		return ResponseUtils.sendMsg(false, "出错了");
	}
	
	/**
	 * 录入业务员页面
	 * @author aimee at 2015年6月9日下午2:18:01
	 * @email 1297579898@qq.com
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/SalesManView.do")
	public String goIntoSalesManView(Model model,HttpServletRequest request){
		try {
			model.addAttribute("action", 1);
			//通用省得集合
			Map<String, Object>map =  new HashMap<String, Object>();
			map.put("pId", 1);
			List<Region> shengList = regionService.getRegionByPidOrRegionLevel(map);
			model.addAttribute("shengList", shengList);
			return "system/salesman-edit";
		} catch (Exception e) {
			logger.error("PCSalesmanController's method goIntoSalesManView has an error:{}",e);
		}
		return error("出错了", model, request);
	}
	
	/**
	 * 业务员列表
	 * @author aimee at 2015年6月9日下午2:37:40
	 * @email 1297579898@qq.com
	 * @param request
	 * @param model
	 * @param man
	 * @return
	 */
	@RequestMapping(value="/SalesListView.do")
	public String getSalesmanList(HttpServletRequest request, Model model, SalesmanRo man){
		try {
			if(!StringUtil.stringIsNullOrEmpty(man.getKeywords())){
				model.addAttribute("keywords", man.getKeywords());
			}
			if(!StringUtil.stringIsNullOrEmpty(man.getKeywords())){
				man.setKeywords(man.getKeywords().trim());
			}
			List<SalesmanVo> list = new ArrayList<SalesmanVo>();
			//业务员集合
			list = salesmanService.getSalesmanList(man);
			for (SalesmanVo salesmanVo : list) {
				if(!StringUtil.stringIsNullOrEmpty(salesmanVo.getAreaname())){
					//判断区域名称是否包含"区",有去掉
					if(salesmanVo.getAreaname().contains("区")){
						salesmanVo.setAreaname(salesmanVo.getAreaname().substring(0,salesmanVo.getAreaname().indexOf("区")));
					}
				}
			}
			//业务员数量
			Integer count = salesmanService.getSalesmanListCount(man);
			//组合分页
			this.pageUtil(man.getPageIndex(), count, man.getPageSize(), request, model);
			model.addAttribute("list", list);
			return "system/salesman";
		} catch (Exception e) {
			logger.error("PCSalesmanController's method getSalesmanList has an error:{}",e);
		}
		return error("出错了", model, request);
	}
	/**
	 * 删除业务员
	 * @author aimee at 2015年6月9日下午5:12:30
	 * @email 1297579898@qq.com
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/deleteSalesman.do")
	public String deleteSalesman(String id,Model model,HttpServletRequest request){
		try {
			if(StringUtil.stringIsNullOrEmpty(id)){
				return error("请求的参数有误", model, request);
			}
			//删除业务员
			boolean flag = salesmanService.deleteSalesman(id)>0?true:false;
			if(flag){
				return "redirect:SalesListView.do";
			}
		} catch (Exception e) {
			logger.error("PCSalesmanController's method deleteSalesman has an error:{}",e);
		}
		return error("出错了", model, request);
	}
	/**
	 * 修改业务员信息页面
	 * @author aimee at 2015年6月9日下午5:24:50
	 * @email 1297579898@qq.com
	 * @param id
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/updateSalesmanView.do")
	public String updateSalesmanView(String id,Model model,HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if(StringUtil.stringIsNullOrEmpty(id)){
				return error("请求的参数有误", model, request);
			}
			//标记为修改，1添加，2修改
			model.addAttribute("action", 2);
			//根据id查询业务员
			Salesman man = salesmanService.getSalesman(id);
			model.addAttribute("salesman", man);
			//通用省得集合
			map =  new HashMap<String, Object>();
			map.put("pId", 1);
			List<Region> shengList = regionService.getRegionByPidOrRegionLevel(map);
			model.addAttribute("shengList", shengList);
			if(man.getProvince()!=null && man.getCity()!=null && man.getArea()!=null){
				//市的集合
				map =  new HashMap<String, Object>();
				map.put("pId", man.getProvince());
				List<Region> shiList = regionService.getRegionByPidOrRegionLevel(map);
				model.addAttribute("shiList", shiList);
				//区的集合
				map =  new HashMap<String, Object>();
				map.put("pId", man.getCity());
				List<Region> areaList = regionService.getRegionByPidOrRegionLevel(map);
				model.addAttribute("areaList", areaList);
			}
			return "system/salesman-edit";
		} catch (Exception e) {
			logger.error("PCSalesmanController's method updateSalesmanView has an error:{}",e);
		}
		return error("出错了", model, request);
	}
	
}

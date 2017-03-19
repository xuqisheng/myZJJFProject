/**   
* @Title: SpRegionController.java 
* @Package com.corner.scms.controller.sp 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年6月27日 下午2:41:49 
* @version V1.0   
*/

package com.corner.scms.controller.sp;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.corner.scms.beans.vo.RegionRegistVo;
import com.corner.scms.service.sp.SpRegionService;

/** 
* @ClassName: SpRegionController 
* @Description:区域控制器
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年6月27日 下午2:41:49 
*  
*/
@RequestMapping("/region")
@Controller
public class SpRegionController {
   
	private static Logger logger = LoggerFactory.getLogger(SpRegionController.class);
	
	@Autowired
	SpRegionService spRegionService;

	
	/**
	 * 
	* @Title: getAllEnableRegionList 
	* @Description:获取所有可用的区域列表
	* @param @return
	* @return Object    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/getAllEnableRegionList.do")
	@ResponseBody
	public Object getAllEnableRegionList() {
		try {
			List<RegionRegistVo> list = spRegionService.getAllEnableRegionList();
			return list;
		} catch (Exception e) {
			logger.error("",e);
			return null;
		}
	}
}

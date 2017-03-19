/**   
* @Title: PcRepeatProductProcessController.java 
* @Package com.corner.kefu.controller.sp 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年8月16日 上午10:45:11 
* @version V1.0   
*/

package com.corner.kefu.controller.sp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.corner.kefu.controller.KefuBaseWebController;

/** 
* @ClassName: PcRepeatProductProcessController 
* @Description:重复商品处理控制器
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年8月16日 上午10:45:11 
*  
*/
@RequestMapping("/repeatProduct")
@Controller
public class PcRepeatProductProcessController extends KefuBaseWebController {

	private static final String PATH = "repeatProduct/";
	
	
	
	/**
	 * 
	* @Title: toRepeatProduct 
	* @Description:
	* @param @return
	* @return String    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/repeatIndex.do")
	public String toRepeatProduct() {
		return PATH + "repeatProduct";
	}

}

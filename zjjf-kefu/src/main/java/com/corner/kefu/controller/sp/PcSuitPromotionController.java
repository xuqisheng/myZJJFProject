/**   
* @Title: PcSuitPromotionController.java 
* @Package com.corner.kefu.controller.sp 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年8月6日 上午11:02:08 
* @version V1.0   
*/

package com.corner.kefu.controller.sp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.corner.core.beans.AppItemLabel;
import com.corner.kefu.service.AppItemLabelService;

/** 
* @ClassName: PcSuitPromotionController 
* @Description:
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年8月6日 上午11:02:08 
*  
*/
@Controller
@RequestMapping("/suitPromotion")
public class PcSuitPromotionController {

	private final static String PATH = "suit-promotion/";
	
	@Autowired
	private AppItemLabelService appTagService;
	
	/**
	 * 
	* @Title: toSuitPromotion 
	* @Description:跳转到套装促销页面
	* @param @return
	* @return String    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/toSuitPromotion.do")
	public String toSuitPromotion() {
		return PATH+"suit-sale";
	}
	
	/**
	 * 
	* @Title: toAddSuitPromotion 
	* @Description:跳转到新增套装促销
	* @param @return
	* @return String    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@RequestMapping("/toAddSuitPromotion.do")
	public String toAddSuitPromotion(Model model) {
		//查询标签
		List<AppItemLabel> list = appTagService.getAllTag();
		model.addAttribute("tagList", list);
		return PATH+"add-suit-sale";
	}
}

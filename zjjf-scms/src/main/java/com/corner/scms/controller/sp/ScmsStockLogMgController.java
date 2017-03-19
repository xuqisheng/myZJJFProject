package com.corner.scms.controller.sp;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.corner.core.beans.Supplier;
import com.corner.core.beans.vo.Pager;
import com.corner.scms.beans.ro.StockManagerParamRo;
import com.corner.scms.beans.vo.ScmsStockLogVo;
import com.corner.scms.controller.ScmsBaseWebController;
import com.corner.scms.service.sp.ScmsPlantItemMgService;
import com.corner.scms.service.sp.ScmsStockLogMgService;
@Controller
@RequestMapping("/scms/stockLog")
public class ScmsStockLogMgController extends ScmsBaseWebController {
	private static final Logger logger = LoggerFactory.getLogger(ScmsStockLogMgController.class);
	@Autowired
	private ScmsStockLogMgService scmsStockLogMgService;
	@Autowired
	private ScmsPlantItemMgService plantItemMgService;

	//stock-detail页面
	@RequestMapping(value = "/getStockLogPage.do")
	public String getStockLogPage(HttpServletRequest request, Model model){
		//点击查看明细吧商品编号传过来
		String mdseId = request.getParameter("mdseId");
		model.addAttribute("mdseId", mdseId);
		return "product/stock-detail";
	}

	/**
	 * 获取所有记录数
	 * @author longwu at  2015年12月4日下午1:52:45
	 * @email tiezhongtang@izjjf.cn
	 * @param int
	 * @return
	 */

	@RequestMapping(value = "/getAllStockLog.do")
	@ResponseBody
	public Object getAllStockLog(HttpServletRequest request, Model model,StockManagerParamRo stockManagerParam,Integer pageIndex){

		//得到供应商对象
		Supplier supplier = this.getCurrentUser(Supplier.class, request);
		if(supplier == null)
			return "/login/index";
		try {
			stockManagerParam.setSpId(supplier.getId());
			stockManagerParam.setPageIndex(pageIndex+1);
			stockManagerParam.setCommodityIdAndName(stockManagerParam.getCommodityIdAndName().trim());
			if(stockManagerParam.getEndTime() != null){
				//查询时间加一天
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
				Calendar cd = Calendar.getInstance();   
				cd.setTime(stockManagerParam.getEndTime());   
				cd.add(Calendar.DATE, 1);
				Date datetime = sdf.parse(sdf.format(cd.getTime()));
				stockManagerParam.setEndTime(datetime);
			}

			Pager<ScmsStockLogVo> ScmsStockLogList = scmsStockLogMgService.getAllStockLog(stockManagerParam);
			if(ScmsStockLogList != null){
				return new Pager<ScmsStockLogVo>(ScmsStockLogList.getTotalSize(), ScmsStockLogList.getList());
			}else{
				return new Pager<ScmsStockLogVo>(ScmsStockLogList.getTotalSize(), ScmsStockLogList.getList());
			}
		} catch (Exception e) {
			logger.error("",e);
		}
		return null;
	}

}

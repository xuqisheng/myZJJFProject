package com.corner.data.analysis.service.bus;

import java.util.List;

import com.corner.core.beans.vo.Pager;
import com.corner.data.analysis.beans.vo.AnalysisShopSaleVo;
import com.corner.data.analysis.beans.vo.AnalysisShopVo;
/**
 * ClassName: AnalysisShopService
 * 
 * @Description: 商铺数据分析业务接口
 * @author 元宝
 * @date 2016年01月8日
 */
public interface AnalysisShopService {
	
	/**
	 * @Description: 商铺数据分析列表查询方法
	 * @param  shopVo
	 * @return pager
	 * @author 元宝	yuanbao@izjjf.cn
	 */
	public List<AnalysisShopVo> findShopSummaryList(AnalysisShopVo shopVo) throws Exception;
	
	/**
	 * @Description: 商铺销售明细列表查询方法
	 * @param  shopVo
	 * @return pager
	 * @author 元宝	yuanbao@izjjf.cn
	 */
	public Pager<AnalysisShopSaleVo> findShopSaleList(AnalysisShopSaleVo shopSaleVo) throws Exception;
	
	/**
	 * @Description: 商铺销售详情基础查询
	 * @param shopSaleVo
	 * @return list
	 * @author 元宝	yuanbao@izjjf.cn
	 */
	public List<AnalysisShopSaleVo> findShopSaleBaseById(AnalysisShopSaleVo shopSaleVo) throws Exception;
	
	/**
	 * @Description: 商铺销售明细详情列表查询
	 * @param shopSaleVo
	 * @return list
	 * @author 元宝	yuanbao@izjjf.cn
	 */
	public List<AnalysisShopSaleVo> findShopSaleDetailList(AnalysisShopSaleVo shopSaleVo) throws Exception;
}

package com.corner.data.analysis.dao;

import java.util.List;

import com.corner.data.analysis.beans.vo.AnalysisShopSaleVo;
import com.corner.data.analysis.beans.vo.AnalysisShopVo;
/**
 * ClassName: AnalysisShopMapper
 * 
 * @Description: 商铺汇总 模型层接口
 * @author 元宝
 * @date 2016年01月8日
 */
public interface AnalysisShopMapper {
	/**
	 * @Description: 商铺汇总列表信息查询
	 * @param shopVo
	 * @return list
	 * @author 元宝	yuanbao@izjjf.cn
	 */
	public List<AnalysisShopVo> findShopSummaryList(AnalysisShopVo shopVo);
	
	
	/**
	 * @Description: 商铺销售明细列表查询
	 * @param shopSaleVo
	 * @return list
	 * @author 元宝	yuanbao@izjjf.cn
	 */
	public List<AnalysisShopSaleVo> findShopSaleList(AnalysisShopSaleVo shopSaleVo);
	
	/**
	 * @Description: 商铺销售明细列表总记录数
	 * @param shopSaleVo
	 * @return int
	 * @author 元宝	yuanbao@izjjf.cn
	 */
	public int getShopSaleListSize(AnalysisShopSaleVo shopSaleVo);
	
	/**
	 * @Description: 商铺销售详情基础查询
	 * @param shopSaleVo
	 * @return list
	 * @author 元宝	yuanbao@izjjf.cn
	 */
	public List<AnalysisShopSaleVo> findShopSaleBaseById(AnalysisShopSaleVo shopSaleVo);
	
	/**
	 * @Description: 商铺销售明细详情列表查询
	 * @param shopSaleVo
	 * @return list
	 * @author 元宝	yuanbao@izjjf.cn
	 */
	public List<AnalysisShopSaleVo> findShopSaleDetailList(AnalysisShopSaleVo shopSaleVo);
}

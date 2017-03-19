package com.corner.data.analysis.service.bus.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.beans.vo.Pager;
import com.corner.data.analysis.beans.vo.AnalysisShopSaleVo;
import com.corner.data.analysis.beans.vo.AnalysisShopVo;
import com.corner.data.analysis.dao.AnalysisShopMapper;
import com.corner.data.analysis.service.bus.AnalysisShopService;
/**
 * ClassName: AnalysisShopServiceImpl
 * 
 * @Description: 商铺数据分析业务实现
 * @author 元宝
 * @date 2016年01月8日
 */
@Service
public class AnalysisShopServiceImpl implements AnalysisShopService {

	@Autowired
	private AnalysisShopMapper shopMapper;
	
	/**
	 * @Description: 商铺数据分析列表查询方法
	 * @param  shopVo
	 * @return pager
	 * @author 元宝	yuanbao@izjjf.cn
	 */
	@Override
	public List<AnalysisShopVo> findShopSummaryList(AnalysisShopVo shopVo) throws Exception{
		return shopMapper.findShopSummaryList(shopVo);
	}
	
	/**
	 * @Description: 商铺销售明细列表查询方法
	 * @param  shopVo
	 * @return pager
	 * @author 元宝	yuanbao@izjjf.cn
	 */
	@Override
	public Pager<AnalysisShopSaleVo> findShopSaleList(AnalysisShopSaleVo shopSaleVo) throws Exception{
		List<AnalysisShopSaleVo> shopSaleList = shopMapper.findShopSaleList(shopSaleVo);
		int count = shopMapper.getShopSaleListSize(shopSaleVo);
		return new Pager<AnalysisShopSaleVo>(count,shopSaleList);
	}
	
	/**
	 * @Description: 商铺销售详情基础查询
	 * @param shopSaleVo
	 * @return list
	 * @author 元宝	yuanbao@izjjf.cn
	 */
	public List<AnalysisShopSaleVo> findShopSaleBaseById(AnalysisShopSaleVo shopSaleVo) throws Exception{
		return shopMapper.findShopSaleBaseById(shopSaleVo);
	}
	
	/**
	 * @Description: 商铺销售明细详情列表查询
	 * @param shopSaleVo
	 * @return list
	 * @author 元宝	yuanbao@izjjf.cn
	 */
	public List<AnalysisShopSaleVo> findShopSaleDetailList(AnalysisShopSaleVo shopSaleVo) throws Exception{
		return shopMapper.findShopSaleDetailList(shopSaleVo);
	}
}

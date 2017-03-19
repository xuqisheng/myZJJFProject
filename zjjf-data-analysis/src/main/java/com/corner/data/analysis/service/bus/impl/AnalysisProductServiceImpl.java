package com.corner.data.analysis.service.bus.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.beans.vo.Pager;
import com.corner.data.analysis.beans.vo.AnalysisProductVo;
import com.corner.data.analysis.dao.AnalysisProductMapper;
import com.corner.data.analysis.service.bus.AnalysisProductService;

/**
 * ClassName: AnalysisProductServiceImpl
 * 
 * @Description: 商品数据分析业务实现层
 * @author 元宝
 * @date 2016年01月8日
 */
@Service
public class AnalysisProductServiceImpl implements AnalysisProductService {

	@Autowired
	private AnalysisProductMapper productMapper;
	/**
	 * @Description: 商品数据分析列表方法
	 * @param  productVo
	 * @return list
	 * @author 元宝	yuanbao@izjjf.cn
	 */
	@Override
	public Pager<AnalysisProductVo> findPagerList(AnalysisProductVo productVo) throws Exception{
		List<AnalysisProductVo> list = productMapper.findPagerList(productVo);
		int size = productMapper.getPagerListSize(productVo);
		return new Pager<AnalysisProductVo>(size,list);
	}
	
	/**
	 * @Title: getProductDetailById 
	 * @Description: 根据商品ID查询对应明细
	 * @param  productVo
	 * @return productVo   返回类型
	 * @author 元宝	yuanbao@izjjf.cn
     */
	@Override
	public List<AnalysisProductVo> getProductDetailById(AnalysisProductVo productVo) throws Exception {
		return productMapper.getProductDetailById(productVo);
	}

}

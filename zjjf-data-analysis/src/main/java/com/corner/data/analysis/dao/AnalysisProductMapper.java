package com.corner.data.analysis.dao;

import java.util.List;

import com.corner.data.analysis.beans.vo.AnalysisProductVo;

/**
 * ClassName: AnalysisProductMapper
 * 
 * @Description: 商品数据分析 模型层接口
 * @author 元宝
 * @date 2016年01月8日
 */
public interface AnalysisProductMapper {
	/**
	 * @Description: 商品数据分析列表方法
	 * @param  productVo
	 * @return list
	 * @author 元宝	yuanbao@izjjf.cn
	 */
	public List<AnalysisProductVo> findPagerList(AnalysisProductVo productVo);
	
	/**
	 * @Title: getPageListSize 
	 * @Description: 商品数据分析分页总数
	 * @param  productVo
	 * @return int   返回类型
	 * @author 元宝	yuanbao@izjjf.cn
     */
	public int getPagerListSize(AnalysisProductVo productVo);
	
	
	/**
	 * @Title: getProductDetailById 
	 * @Description: 根据商品ID查询对应明细
	 * @param  productVo
	 * @return productVo   返回类型
	 * @author 元宝	yuanbao@izjjf.cn
     */
	public List<AnalysisProductVo> getProductDetailById(AnalysisProductVo productVo);
}

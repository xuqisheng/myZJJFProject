package com.corner.data.analysis.service.bus;

import java.util.List;

import com.corner.core.beans.vo.Pager;
import com.corner.data.analysis.beans.vo.AnalysisProductVo;

/**
 * ClassName: AnalysisProductService
 * 
 * @Description: 商品数据分析业务接口
 * @author 元宝
 * @date 2016年01月8日
 */
public interface AnalysisProductService {
	/**
	 * @Description: 商品数据分析列表方法
	 * @param  productVo
	 * @return list
	 * @author 元宝	yuanbao@izjjf.cn
	 */
	public Pager<AnalysisProductVo> findPagerList(AnalysisProductVo productVo) throws Exception;
	
	/**
	 * @Title: getProductDetailById 
	 * @Description: 根据商品ID查询对应明细
	 * @param  productVo
	 * @return productVo   返回类型
	 * @author 元宝	yuanbao@izjjf.cn
     */
	public List<AnalysisProductVo> getProductDetailById(AnalysisProductVo productVo) throws Exception;
	
}

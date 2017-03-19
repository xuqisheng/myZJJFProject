package com.corner.data.analysis.dao;

import java.util.List;

import com.corner.data.analysis.beans.vo.AnalysisSupplierVo;

/**
 * ClassName: AnalysisSupplierMapper
 * 
 * @Description: 合作商数据分析 模型层接口
 * @author 元宝
 * @date 2016年01月8日
 */
public interface AnalysisSupplierMapper {
	/**
	 * @Description: 合作商数据分析列表查询
	 * @param shopVo
	 * @return list
	 * @author 元宝	yuanbao@izjjf.cn
	 */
	public List<AnalysisSupplierVo> findSupplierList(AnalysisSupplierVo supplierVo);
	
	/**
	 * @Description: 合作商基础信息列表查询
	 * @param shopVo
	 * @return list
	 * @author 元宝	yuanbao@izjjf.cn
	 */
	public List<AnalysisSupplierVo> findSupplierBaseList(AnalysisSupplierVo supplierVo);
	
	/**
	 * @Description: 合作商销售汇总列表查询
	 * @param shopVo
	 * @return list
	 * @author 元宝	yuanbao@izjjf.cn
	 */
	public List<AnalysisSupplierVo> findSupplierDetailList(AnalysisSupplierVo supplierVo);
}

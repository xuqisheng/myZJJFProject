package com.corner.data.analysis.service.bus;

import java.util.List;

import com.corner.data.analysis.beans.vo.AnalysisSupplierVo;

/**
 * ClassName: AnalysisSupplierService
 * 
 * @Description: 合作商数据分析 业务层接口
 * @author 元宝
 * @date 2016年01月8日
 */
public interface AnalysisSupplierService {
	/**
	 * @Description: 合作商数据分析列表查询
	 * @param shopVo
	 * @return list
	 * @author 元宝	yuanbao@izjjf.cn
	 */
	public List<AnalysisSupplierVo> findSupplierList(AnalysisSupplierVo supplierVo) throws Exception;
	
	/**
	 * @Description: 合作商基础信息列表查询
	 * @param shopVo
	 * @return list
	 * @author 元宝	yuanbao@izjjf.cn
	 */
	public List<AnalysisSupplierVo> findSupplierBaseList(AnalysisSupplierVo supplierVo) throws Exception;
	
	/**
	 * @Description: 合作商销售汇总列表查询
	 * @param shopVo
	 * @return list
	 * @author 元宝	yuanbao@izjjf.cn
	 */
	public List<AnalysisSupplierVo> findSupplierDetailList(AnalysisSupplierVo supplierVo) throws Exception;
}

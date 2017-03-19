package com.corner.data.analysis.service.bus.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.corner.data.analysis.beans.vo.AnalysisSupplierVo;
import com.corner.data.analysis.dao.AnalysisSupplierMapper;
import com.corner.data.analysis.service.bus.AnalysisSupplierService;
/**
 * ClassName: AnalysisShopServiceImpl
 * 
 * @Description: 合作商数据分析业务实现
 * @author 元宝
 * @date 2016年01月8日
 */
@Service
public class AnalysisSupplierServiceImpl implements AnalysisSupplierService {
	
	@Autowired
	private AnalysisSupplierMapper supplierMapper;
	
	/**
	 * @Description: 合作商数据分析列表查询
	 * @param shopVo
	 * @return list
	 * @author 元宝	yuanbao@izjjf.cn
	 */
	@Override
	public List<AnalysisSupplierVo> findSupplierList(AnalysisSupplierVo supplierVo) throws Exception{
		return supplierMapper.findSupplierList(supplierVo);
	}
	
	/**
	 * @Description: 合作商基础信息列表查询
	 * @param shopVo
	 * @return list
	 * @author 元宝	yuanbao@izjjf.cn
	 */
	@Override
	public List<AnalysisSupplierVo> findSupplierBaseList(AnalysisSupplierVo supplierVo) throws Exception{
		return supplierMapper.findSupplierBaseList(supplierVo);
	}
	
	/**
	 * @Description: 合作商销售汇总列表查询
	 * @param shopVo
	 * @return list
	 * @author 元宝	yuanbao@izjjf.cn
	 */
	@Override
	public List<AnalysisSupplierVo> findSupplierDetailList(AnalysisSupplierVo supplierVo) throws Exception{
		return supplierMapper.findSupplierDetailList(supplierVo);
	}

}

package com.zjjf.analysis.services.suppliser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.zjjf.analysis.beans.analysis.base.Region;
import com.zjjf.analysis.beans.analysis.base.SpGroup;
import com.zjjf.analysis.beans.analysis.supplier.Supplier;
import com.zjjf.analysis.beans.analysis.supplier.SupplierSpgroupSaleGoal;
import com.zjjf.analysis.beans.vo.ParamMapVo;
import com.zjjf.analysis.mapper.analysis.SupplierSpgroupSaleGoalMapper;
import com.zjjf.analysis.mapper.origin.RegionMapper;
import com.zjjf.analysis.mapper.origin.SpGroupMapper;
import com.zjjf.analysis.mapper.origin.SupplierMapper;
import com.zjjf.analysis.producer.authority.IAuthorityKeyArray;
import com.zjjf.analysis.producer.supplier.ISupplierSaleGoalService;
import com.zjjf.analysis.services.AbstractBaseService;

@Service(version = "1.0.0")
public class SupplierSaleGoalServiceImpl extends AbstractBaseService<SupplierSpgroupSaleGoal> implements ISupplierSaleGoalService{
	
	@Autowired
	private SupplierSpgroupSaleGoalMapper saleGoalSetMapper;
	
	@Autowired
	private IAuthorityKeyArray authorityKeyArray;
	
	@Autowired
	private RegionMapper regionMapper;
	
	@Autowired
	private SpGroupMapper spGroupMapper;
	
	@Autowired
	private SupplierMapper supplierMapper;
	
	@Override
	public List<Object[]> query(String userName, int menuId,
			HashMap<String, Object> paramMap) {
		
		Object[][] authorityArray = authorityKeyArray.getAuthorityKeyArray(userName, menuId);
		authorityKeyArray.getAuthorityCityOrArea(paramMap, userName, menuId);
		Object[] titelEn = getOrderTitleEn(authorityArray);
		//按合作商名字查询
		set_supplierId_paramMap(paramMap);
		
		List<SupplierSpgroupSaleGoal> saleGoalList = saleGoalSetMapper.querySaleGoalSet(paramMap);
		
		for (SupplierSpgroupSaleGoal bean : saleGoalList) {
			ParamMapVo vo = this.queryCnName(bean.getCityId(), bean.getAreaId(), bean.getSpGroupId(), bean.getSupplierId());
			bean.setCityName(vo.getCityName());
			bean.setAreaName(vo.getAreaName());
			bean.setSpGroupName(vo.getSpGroupName());
			bean.setSupplierName(vo.getSupplierName());
		}
		
		return saleGoalList.stream().map(s -> sort_by_viewTitle(s, titelEn)).collect(Collectors.toList());
	}

	@Override
	public Integer batchUpdate(HashMap<String, Object> hashMap) {
		
		return saleGoalSetMapper.batchUpdate(hashMap);
	}

	private ParamMapVo queryCnName(Integer cityId, Integer areaId, Integer spGroupId, String supplierId) {
		ParamMapVo vo = new ParamMapVo();
		Region city = regionMapper.getById(cityId);
		Region area = regionMapper.getById(areaId);
		SpGroup spGroup = spGroupMapper.getById(spGroupId);
		Supplier supplier = supplierMapper.getById(supplierId);
		
		vo.setCityName(city == null ? null : city.getName());
		vo.setAreaName(area == null ? null : area.getName());
		vo.setSpGroupName(spGroup == null ? null : spGroup.getName());
		vo.setSupplierName(supplier == null ? null : supplier.getSupplierName());
		return vo;
	}
	
	private void set_supplierId_paramMap(HashMap<String, Object> paramMap) {
		if (paramMap.containsKey("supplierName") && paramMap.get("supplierName") != null) {
			String supplierName = paramMap.get("supplierName") + "";
			List<Supplier> list = supplierMapper.getByName(supplierName);
			List<String> ids = new ArrayList<String>(50);
			if (list != null && list.size() != 0) {
				for (Supplier sup : list) {
					ids.add(sup.getId());
				}
				paramMap.put("spIds", ids);
			}
			else {
				ids.add("-1");
				paramMap.put("spIds", ids);
			}
		} else {
			return;
		}
	}
}

package com.corner.kefu.service.sp.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.beans.Brand;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.kefu.beans.ro.BrandRo;
import com.corner.kefu.beans.vo.BrandVo;
import com.corner.kefu.dao.sp.SpBrandMgMapper;
import com.corner.kefu.service.sp.SpBrandService;

@Service
public class SpBrandServiceImpl implements SpBrandService{

	@Autowired
	SpBrandMgMapper BrandMapper; 
	
	@Override
	public List<BrandVo> getBrandByName(Map<String, Object> map) {
		return BrandMapper.getBrandByName(map);
	}

	@Override
	public Pager<BrandVo> getAllBrandByParam(BrandRo brandRo) {
		List<BrandVo> brandList = BrandMapper.getAllBrandByParam(brandRo);
		int num = BrandMapper.getCount(brandRo);
		return new Pager<BrandVo>(num, brandList);
	}

	@Override
	public Pager<BrandVo> getAllBrandingByParam(BrandRo brandRo) {
		List<BrandVo> brandList = BrandMapper.getAllBrandingByParam(brandRo);
		int num = BrandMapper.getCountA(brandRo);
		return new Pager<BrandVo>(num, brandList);
	}

	@Override
	public BrandVo getBrandById(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return BrandMapper.getBrandById(map);
	}

	@Override
	public Brand getBrandingById(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return BrandMapper.getBrandingById(map);
	}

	@Override
	public int chickBrandNo(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return BrandMapper.chickBrandNo(map);
	}

	@Override
	public void addBrandAndBranding(Brand brand) {
		// TODO Auto-generated method stub
		BrandMapper.addBrandAndBranding(brand);
	}

	@Override
	public void updateBrandAndBranding(Brand brand) {
		// TODO Auto-generated method stub
		BrandMapper.updateBrandAndBranding(brand);
	}

	@Override
	public ModelMsg deleteBrandAndBranding(Map<String, Object> map,String differentiate) {
		if(differentiate != null && !"".equals(differentiate) && "brand".equals(differentiate)){
			//品牌删除
			int num = BrandMapper.getItemBaseIsNull(map);
			if(num > 0){
				return new ModelMsg(false, "此品牌下还有商品,不能被删除！");
			}
		}else if(differentiate != null && !"".equals(differentiate) && "branding".equals(differentiate)){
			//品牌商删除
			int num = BrandMapper.getBrandIsNull(map);
			if(num > 0){
				return new ModelMsg(false, "此品牌商下还有品牌,不能被删除！");			
			}
		}else{
			return new ModelMsg(false, "操作失败！");
		}
		BrandMapper.deleteBrandAndBranding(map);
		return new ModelMsg(true, "操作成功！");
	}

}

package com.corner.kefu.service.sp;

import java.util.List;
import java.util.Map;

import com.corner.core.beans.Brand;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.kefu.beans.ro.BrandRo;
import com.corner.kefu.beans.vo.BrandVo;

public interface SpBrandService {

	List<BrandVo> getBrandByName(Map<String, Object> map);

	Pager<BrandVo> getAllBrandByParam(BrandRo brandRo);

	Pager<BrandVo> getAllBrandingByParam(BrandRo brandRo);

	BrandVo getBrandById(Map<String, Object> map);

	Brand getBrandingById(Map<String, Object> map);

	int chickBrandNo(Map<String, Object> map);

	void addBrandAndBranding(Brand brand);

	void updateBrandAndBranding(Brand brand);

	ModelMsg deleteBrandAndBranding(Map<String, Object> map, String differentiate);

}

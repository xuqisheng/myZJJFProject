package com.corner.kefu.dao.sp;

import java.util.List;
import java.util.Map;

import com.corner.core.beans.Brand;
import com.corner.kefu.beans.ro.BrandRo;
import com.corner.kefu.beans.vo.BrandVo;

public interface SpBrandMgMapper {

	List<BrandVo> getBrandByName(Map<String, Object> map);

	List<BrandVo> getAllBrandByParam(BrandRo brandRo);

	int getCount(BrandRo brandRo);

	List<BrandVo> getAllBrandingByParam(BrandRo brandRo);

	int getCountA(BrandRo brandRo);

	BrandVo getBrandById(Map<String, Object> map);

	Brand getBrandingById(Map<String, Object> map);

	int chickBrandNo(Map<String, Object> map);

	void addBrandAndBranding(Brand brand);

	void updateBrandAndBranding(Brand brand);

	int getItemBaseIsNull(Map<String, Object> map);

	void deleteBrandAndBranding(Map<String, Object> map);

	int getBrandIsNull(Map<String, Object> map);

}

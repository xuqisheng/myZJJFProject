package com.corner.kefu.dao.sp;

import java.util.List;
import java.util.Map;

import com.corner.core.beans.Adboard;
import com.corner.kefu.beans.ro.AdvertisementRo;
import com.corner.kefu.beans.vo.AdvertisementVo;
import com.corner.kefu.beans.vo.sp.SpGroupVo;



public interface SpAdvertisementMgMapper {

	List<AdvertisementVo> getAdvertisementList(AdvertisementRo advertisement);
	int getAdvertisementListCount(AdvertisementRo advertisement);

//	int batchSave(List<Advertisement> list);

	void updateAdstatusByName(Map<String, Object> map);

	void deleteAdByName(String name);
	List<SpGroupVo> getSpGroupIdByAdId(Integer id);
	void deleteAd(Integer id);
	List<Adboard> getAllAdboards();
}

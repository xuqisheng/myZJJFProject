package com.corner.kefu.service.sp;

import java.util.List;
import java.util.Map;

import com.corner.core.beans.Adboard;
import com.corner.core.beans.Advertisement;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.kefu.beans.ro.AdvertisementRo;
import com.corner.kefu.beans.vo.AdvertisementVo;



public interface SpAdvertisementService {



	/**
	 * 查询广告位列表
	 * @param advertisement 
	 * @return AdvertisementVo 广告位视图类
	 * @throws Exception 
	 */
	public Pager<AdvertisementVo> getAdvertisementList(AdvertisementRo advertisement);
	
	
//	public void batchSave(List<Advertisement> list);

//	public void updateByPrimaryKeySelective(Advertisement advertisement);

	public AdvertisementVo getAdvertisementVoById(Integer id);

	public void updateAdstatusByName(Integer id, Byte status);

	public void deleteAdByName(Integer id);

	public void deleteAd(Integer id);


	public Map<String, Object> getEditDate(Integer id);


	public ModelMsg saveAdvertisement(Advertisement advertisement, String[] spgroupidArr);


	public List<Adboard> getAllAdboards();

	
	
}
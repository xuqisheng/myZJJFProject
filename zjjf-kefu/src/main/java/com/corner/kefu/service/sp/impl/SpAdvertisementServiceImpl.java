package com.corner.kefu.service.sp.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.beans.Adboard;
import com.corner.core.beans.Advertisement;
import com.corner.core.beans.AppItemTag;
import com.corner.core.beans.ItemCatelog;
import com.corner.core.beans.SpGroupAd2BordMapKey;
import com.corner.core.beans.SpGroupAdvertisment;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.core.dao.AdboardMapper;
import com.corner.core.dao.AdvertisementMapper;
import com.corner.core.dao.SpGroupAd2BordMapMapper;
import com.corner.core.dao.SpGroupAdvertismentMapper;
import com.corner.core.utils.JSONUtil;
import com.corner.core.utils.StringUtil;
import com.corner.kefu.beans.ro.AdvertisementRo;
import com.corner.kefu.beans.vo.AdvertisementVo;
import com.corner.kefu.beans.vo.sp.RegionVo;
import com.corner.kefu.beans.vo.sp.SpGroupVo;
import com.corner.kefu.dao.SpGroupAd2BordMapMgMapper;
import com.corner.kefu.dao.SpGroupAdvertismentMgMapper;
import com.corner.kefu.dao.sp.SpAdvertisementMgMapper;
import com.corner.kefu.service.AppModuleService;
import com.corner.kefu.service.sp.SpAdbordService;
import com.corner.kefu.service.sp.SpAdvertisementService;
import com.corner.kefu.service.sp.SpItemCatelogService;
import com.corner.kefu.service.sp.SpRegionService;
import com.corner.kefu.utils.BeanUtil;




@Service
public class SpAdvertisementServiceImpl implements SpAdvertisementService {
	
	@Autowired
	private SpAdvertisementMgMapper spAdvertisementMgMapper;
	@Autowired
	private AdvertisementMapper advertisementMapper;
	@Autowired
	SpRegionService regionService;
	@Autowired
	AppModuleService appModuleService;
	@Autowired
	SpItemCatelogService itemCatelogService;
	@Autowired
	SpAdbordService adbordService;
	@Autowired
	AdboardMapper adboardMapper;
	@Autowired
	SpGroupAdvertismentMapper spGroupAdvertismentMapper;
	@Autowired
	SpGroupAdvertismentMgMapper spGroupAdvertismentMgMapper;
	@Autowired
	SpGroupAd2BordMapMapper spGroupAd2BordMapMapper;
	@Autowired
	SpGroupAd2BordMapMgMapper spGroupAd2BordMapMgMapper;

	/**
	 * 查询广告列表
	 * @param map 
	 * @return AdvertisementVo 广告位视图类
	 * @throws Exception 
	 */
	@Override
	public Pager<AdvertisementVo> getAdvertisementList(AdvertisementRo advertisement)  {
		if(!StringUtil.stringIsNullOrEmpty(advertisement.getName())){
			advertisement.setName(advertisement.getName().trim());
		}
		if(advertisement.getEndTime() != null){
			Date endTime = advertisement.getEndTime();
			endTime = DateUtils.addDays(endTime, 1);
			advertisement.setEndTime(endTime);
		}
		
		List<AdvertisementVo> list = spAdvertisementMgMapper.getAdvertisementList(advertisement);
		int totalSize = spAdvertisementMgMapper.getAdvertisementListCount(advertisement);
		return new Pager<>(totalSize, list);
	}

//	@Override
//	public int batchSave(List<Advertisement> list) {
//		this.spAdvertisementMgMapper.batchSave(list);
//	}
//


/*	@Override
	public void updateByPrimaryKeySelective(Advertisement advertisement) {
		this.advertisementMapper.updateByPrimaryKeySelective(advertisement);
	}
*/


	@Override
	public AdvertisementVo getAdvertisementVoById(Integer id) {
		return BeanUtil.toObject(AdvertisementVo.class, advertisementMapper.selectByPrimaryKey(id));
	}
	@Override
	public void updateAdstatusByName(Integer id, Byte status) {
		 String name = getAdvertisementVoById(id).getName();
		  if(name!=null){
			  Map<String, Object> map = new HashMap<>();
			  map.put("name", name);
			  map.put("status", status);
			  spAdvertisementMgMapper.updateAdstatusByName(map);
		  }
	}

	@Override
	public void deleteAdByName(Integer id) {
		 String name = getAdvertisementVoById(id).getName();
		  if(name!=null){
			  spAdvertisementMgMapper.deleteAdByName(name);
		  }
	}
	@Override
	public void deleteAd(Integer id) {
		spAdvertisementMgMapper.deleteAd(id);
	}

	@Override
	public Map<String, Object> getEditDate(Integer id) {
		Map<String, Object> map = new HashMap<>();
		//获取区域定格组装数据
		List<RegionVo> regionList = regionService.getAllAreaAndSpGroup();
		if(regionList != null && regionList.size()>0){
			map.put("regionList", JSONUtil.objectToJSONString(regionList));
		}
		//获取广告位
		List<Adboard> adboardList = adbordService.getAdPositionList();
		if(adboardList != null && adboardList.size() >0){
			map.put("adboardList", adboardList);
		}
		//根据id查询
		if(id != null){
			Advertisement advertisement = advertisementMapper.selectByPrimaryKey(id);
			if(advertisement != null){
				String content = advertisement.getContent();
				advertisement.setContent("");
				map.put("jsonAd", JSONUtil.objectToJSONString(advertisement));
				advertisement.setContent(content);
				map.put("advertisement", advertisement);
				if(advertisement.getIsClick()){
					if(advertisement.getClickType()==2){
						//获取所有商品二级分类
						List<ItemCatelog> catelogList = itemCatelogService.getSecondCateList();
						if(catelogList != null && catelogList.size() >0){
							map.put("catelogList", catelogList);
						}
					}else if(advertisement.getClickType()==3){
						//获取所有商品标签
						List<AppItemTag> itemTagList = appModuleService.getAllItemTag();
						if(itemTagList != null && itemTagList.size()>0){
							map.put("itemTagList", itemTagList);
						}
					}
				}
				//获取当前广告的广告位
				Adboard adboard = adboardMapper.selectByPrimaryKey(advertisement.getBoardId());
				if(adboard != null){
					map.put("adboard", adboard);
				}
				//获取广告分配的定格
				if(!advertisement.getAsDefault()){
					List<SpGroupVo> spGroupList = spAdvertisementMgMapper.getSpGroupIdByAdId(advertisement.getId());
					if(spGroupList != null && spGroupList.size() >0){
						map.put("spGroupList", spGroupList);
					}
				}
			}
		}		
		return map;
	}

	@Override
	public ModelMsg saveAdvertisement(Advertisement advertisement, String[] spgroupidArr) {
		ModelMsg msg = new ModelMsg();
		if(advertisement.getIsClick()){
			if(advertisement.getClickType()==2){
				advertisement.setClassId(Integer.parseInt(advertisement.getCol1()));
			}else if(advertisement.getClickType()==3){
				advertisement.setItemTagId(advertisement.getCol1());
			}else if(advertisement.getClickType()==4){
				advertisement.setBrandId(Integer.parseInt(advertisement.getCol1()));
			}
		}
		advertisement.setSpGroupId(null);
		SpGroupAdvertisment spGroupAd = null;
		SpGroupAd2BordMapKey map = null;
		if (advertisement.getId() == null) {
			// 新增
			advertisement.setAddTime(new Date());
			int num = advertisementMapper.insertSelective(advertisement);
			if(num >0){
				//添加定格
				if(!advertisement.getAsDefault()){
					if(spgroupidArr != null && spgroupidArr.length >0){
						for (String i : spgroupidArr) {
							spGroupAd = new SpGroupAdvertisment();
							spGroupAd.setId(StringUtil.getUUID());
							spGroupAd.setSpGroupId(Integer.parseInt(i));
							spGroupAd.setAdvertismentId(advertisement.getId());
							spGroupAdvertismentMapper.insertSelective(spGroupAd);
						}
						//获取定格广告id和广告位关联
						List<SpGroupAdvertisment> groupAdvertisments =  spGroupAdvertismentMgMapper.getGroupAdIds(advertisement.getId());
						if(groupAdvertisments != null && groupAdvertisments.size() >0){
							//关联广告位id和定格广告id
							for (SpGroupAdvertisment spGroupAdvertisment : groupAdvertisments) {
								map = new SpGroupAd2BordMapKey();
								map.setSpGroupAdId(spGroupAdvertisment.getId());
								map.setAdBordId(advertisement.getBoardId());
								spGroupAd2BordMapMapper.insertSelective(map);
							}
						}
					}
				}
				msg.setSuccess(true);
				msg.setMessage("添加成功");
			}else{
				msg.setSuccess(false);
				msg.setMessage("添加失败");
			}
		} else {
			// 修改
			List<SpGroupAdvertisment> groupAdvertisments = null;
			int num = advertisementMapper.updateByPrimaryKeySelective(advertisement);
			if(num >0){
				if(!advertisement.getAsDefault()){
					//获取定格广告id和广告位关联(原)
					groupAdvertisments =  spGroupAdvertismentMgMapper.getGroupAdIds(advertisement.getId());
					if(groupAdvertisments != null && groupAdvertisments.size() >0){
						spGroupAdvertismentMgMapper.deleteDataByAdId(advertisement.getId());
					}
					for (SpGroupAdvertisment spGroupAdvertisment : groupAdvertisments) {
						//根据定格广告id取和广告位的对应关系
						List<SpGroupAd2BordMapKey> ad2BordMaps = spGroupAd2BordMapMgMapper.getBoardByGroupAdId(spGroupAdvertisment.getId());
						//如果有就删除
						if(ad2BordMaps != null && ad2BordMaps.size() >0){
							spGroupAd2BordMapMgMapper.delAd2BordMapByGroupAdId(spGroupAdvertisment.getId());
						}
					}
					if(spgroupidArr != null && spgroupidArr.length >0){
						for (String i : spgroupidArr) {
							spGroupAd = new SpGroupAdvertisment();
							spGroupAd.setId(StringUtil.getUUID());
							spGroupAd.setSpGroupId(Integer.parseInt(i));
							spGroupAd.setAdvertismentId(advertisement.getId());
							spGroupAdvertismentMapper.insertSelective(spGroupAd);
						}
						//获取广告位和定格广告关联关系（新）
						groupAdvertisments =  spGroupAdvertismentMgMapper.getGroupAdIds(advertisement.getId());
						if(groupAdvertisments != null && groupAdvertisments.size() >0){
							//关联广告位id和定格广告id
							for (SpGroupAdvertisment spGroupAdvertisment : groupAdvertisments) {
								map = new SpGroupAd2BordMapKey();
								map.setSpGroupAdId(spGroupAdvertisment.getId());
								map.setAdBordId(advertisement.getBoardId());
								spGroupAd2BordMapMapper.insertSelective(map);
							}
						}
					}else{
						spGroupAdvertismentMgMapper.deleteDataByAdId(advertisement.getId());
					}
				}else{
					spGroupAdvertismentMgMapper.deleteDataByAdId(advertisement.getId());
				}
				msg.setSuccess(true);
				msg.setMessage("修改成功");
			}else{
				msg.setSuccess(false);
				msg.setMessage("修改失败");
			}
		}
		return msg;
	}

	@Override
	public List<Adboard> getAllAdboards() {
		return spAdvertisementMgMapper.getAllAdboards();
	}
}

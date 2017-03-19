package com.corner.kefu.utils;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.corner.core.beans.Store;
import com.corner.core.utils.CreateSequence;
import com.corner.kefu.service.sp.SpRegionService;
import com.corner.kefu.service.sp.SpStoreService;
@Component
public class CreateNumberUtil {
	@Autowired
	private SpRegionService spRegionService;
	@Autowired
	private SpStoreService storeService;
	
	private static CreateNumberUtil createNumberUtil;
	
	@PostConstruct
    public  void init() {
    	createNumberUtil = this;
    	createNumberUtil.storeService = this.storeService;
    	createNumberUtil.spRegionService = this.spRegionService;
	}
	
	
	
	public static Map<String, String> createNumber(Store store){
		//区域编号
		String regionStr = createNumberUtil.spRegionService.getRegionStr(store.getProvinceId(),store.getCityId(),store.getAreaId());
		regionStr = regionStr==null || "".equals(regionStr)?"":regionStr;
		//根据区域查出生成的最大值
		Integer num = createNumberUtil.storeService.getMaxSequenceNum(store);
		if(num==null){
			num=0;
		}
		//获取生成的数字
		String sequenceNum = "";
		do {
			sequenceNum = CreateSequence.getSequence(num, 4);
			if(sequenceNum.contains("4")){
				num++;
				//不包含4的
				continue;
			}else{
				break;
			}
		} while (true);
		//拼接的店铺编号
		Map<String, String> map = new HashMap<String, String>();
		map.put("regionStr", regionStr);
		map.put("sequenceNum", sequenceNum);
		return map;
	}
}

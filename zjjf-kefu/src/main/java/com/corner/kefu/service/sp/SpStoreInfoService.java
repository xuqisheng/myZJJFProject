package com.corner.kefu.service.sp;

import com.corner.core.beans.StoreInfo;

public interface SpStoreInfoService {
	
	/**
	 * 保存店铺拓展信息
	 * @param storeInfo
	 * @return
	 */
	public int save(StoreInfo storeInfo);

	/**
	 * 更新店铺拓展信息
	 * @param storeInfo
	 * @return
	 */
	public int updateStore(StoreInfo storeInfo);
	
	/**
	 * 根据id查询StoreInfo的内容
	 * @param id
	 * @return
	 */
	public StoreInfo getStoreById(Integer id);
	
}

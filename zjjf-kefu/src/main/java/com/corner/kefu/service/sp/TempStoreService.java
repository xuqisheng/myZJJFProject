package com.corner.kefu.service.sp;

import java.util.List;
import java.util.Map;

import com.corner.kefu.beans.vo.sp.StoreVo;

/**
 * 
* @ClassName: TempStoreService 
* @Description:
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年4月13日 下午2:13:27 
*
 */
public interface TempStoreService {

	List<StoreVo> getStoreList(Map<String, Object> map) throws Exception;

	void delTempStore(Map<String, Object> map) throws Exception;

	/**
	 * 
	* @Title: batchSave 
	* @Description:往TempStore表中插入数据
	* @param @param map
	* @param @throws Exception
	* @return void    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	void batchSave(Map<String, Object> map) throws Exception;

	/**
	 * 
	* @Title: getTempStoreList 
	* @Description:获取TempStore表中的数据
	* @param @param map
	* @param @return
	* @param @throws Exception
	* @return List<StoreVo>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	List<StoreVo> getTempStoreList(Map<String, Object> map) throws Exception;
     
	/**
	 * 
	* @Title: getCountTempStoreList 
	* @Description:
	* @param @param map
	* @param @return
	* @param @throws Exception
	* @return Integer    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	Integer getCountTempStoreList(Map<String, Object> map) throws Exception;

}

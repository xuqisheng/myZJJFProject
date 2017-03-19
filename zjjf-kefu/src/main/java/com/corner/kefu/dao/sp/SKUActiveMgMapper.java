/**
 *@Title: SKUActiveMgMapper.java 
 *@Package com.corner.kefu.dao.sp 
 *@Description: TODO
 *@author 龙五  longwu@izjjf.cn
 *@date 2016年8月1日 下午5:42:34 
 */
package com.corner.kefu.dao.sp;

import java.util.List;
import java.util.Map;

import com.corner.core.beans.AppItemTagProductMapKey;
import com.corner.core.beans.PlantItemProduct;
import com.corner.core.beans.SKUActive;
import com.corner.core.beans.SKUActiveGoodInfo;
import com.corner.core.beans.SKUActivePublishTagMap;
import com.corner.kefu.beans.vo.SKUActiveTagVo;
import com.corner.kefu.beans.vo.sp.PlantItemVo;
import com.corner.kefu.beans.vo.sp.SKUActiveGoodInfoVo;
import com.corner.kefu.beans.vo.sp.SKUActiveVo;

/**
 * @author LONGWU-PC
 * @ClassName: SKUActiveMgMapper 
 * @Description: TODO
 * @date 2016年8月1日 下午5:42:34 
 * @author 龙五  longwu@izjjf.cn
 */
public interface SKUActiveMgMapper {

	List<SKUActiveVo> getAllSKUActive(SKUActive sKUActive);

	int getAllSKUActiveCount(SKUActive sKUActive);

	int updateSKUActiveStatus(Map<String,Object> map);
	
	/**
	 * 
	 * @Title: getPlatItemByMap
	 * @date 2016年8月30日  下午2:42:43
	 * @author 小武
	 * @version  七彩虹
	 * @param param
	 * @return
	 */
	List<PlantItemVo> getPlatItemByMap(Map<String,Object> param);
	
	/**
	 * 根据单品活动id获取单品活动数据。
	 * @Title: selectSKUActiveGoodInfoByActiveId
	 * @date 2016年8月30日  下午5:26:12
	 * @author 小武
	 * @version  飓风
	 * @param param
	 * @return
	 */
	List<SKUActiveGoodInfo> selectSKUActiveGoodInfoByActiveId(Map<String,Object> param);
	
	/**
	 * 生效单品活动，更新PlatItem数据
	 * @Title: effecSKUActive4PlantItem
	 * @date 2016年8月30日  下午5:24:21
	 * @author 小武
	 * @version  飓风
	 * @param param
	 * @return
	 */
	int effecSKUActive4PlantItem(Map<String,Object> param);
	/**
	 * 失效单品活动，更新PlatItem数据
	 * @Title: invalidSKUActive4PlantItem
	 * @date 2016年8月30日  下午5:24:53
	 * @author 小武
	 * @version  飓风
	 * @param param
	 * @return
	 */
	int invalidSKUActive4PlantItem(Map<String,Object> param);
	
	/**
	 * 逻辑删除数据
	 * @Title: deleteSKUActiveGoodInfoById
	 * @date 2016年9月1日  下午7:31:10
	 * @author 小武
	 * @version  飓风
	 * @param param
	 * @return
	 */
	int deleteSKUActiveGoodInfoById(Map<String,Object> param);
	
	List<SKUActiveGoodInfoVo> getSKUActiveGoodInfoByMap(Map<String,Object> param);
	
	/**
	 * 查询所有标签
	 * @Title: selectSKUActivePublishTag
	 * @date 2016年9月8日  上午10:11:24
	 * @author 小武
	 * @version  飓风
	 * @param param
	 * @return
	 */
	List<SKUActiveTagVo> selectSKUActivePublishTag(Map<String,Object> param);
	
	/**
	 * 查询活动的发布区域
	 * @Title: querySKUActivePublishTagMap
	 * @date 2016年9月8日  下午4:17:14
	 * @author 小武
	 * @version  飓风
	 * @param param
	 * @return
	 */
	List<SKUActivePublishTagMap> querySKUActivePublishTagMap(Map<String,Object> param);
	
	int updateSKUActiveGoodInfototalBuyNum(Map<String,Object> param);
	
	List<SKUActive> getTaskAllSKUActive(Map<String,Object> param);
	
	List<AppItemTagProductMapKey> queryAppItemTagProductMap(Map<String,Object> param);
	
	int delPlantItemProductBySKUActiveId(Map<String,Object> param);
	/**
	 * 根据plantItem查询单品产品的数据
	 * @Title: selectPlantItemProductBySKUActiveIdAndPlantItemId
	 * @date 2016年10月14日  上午11:02:26
	 * @author 小武
	 * @version  七彩虹
	 * @param param
	 * @return
	 */
	List<PlantItemProduct> selectPlantItemProductBySKUActiveIdAndPlantItemId(Map<String,Object> param);
	/**
	 * 更新产品的价格
	 * @Title: updateProductPrice4PlantItemProductById
	 * @date 2016年10月14日  上午11:02:53
	 * @author 小武
	 * @version  七彩虹
	 * @param param
	 * @return
	 */
	int updateProductPrice4PlantItemProductById(Map<String,Object> param);
	/**
	 * 失效单品促销活动，同步失效产品表产品为下架和删除状态
	 * @Title: invalidPlantItemProductBySKUActiveId
	 * @date 2016年10月14日  下午2:33:19
	 * @author 小武
	 * @version  七彩虹
	 * @param param
	 * @return
	 */
	int invalidPlantItemProductBySKUActiveId(Map<String,Object> param);
	/**
	 * 查询活动的所有的产品数据信息
	 * @Title: selectPlantItemProductBySKUActiveId
	 * @date 2016年10月14日  下午2:39:25
	 * @author 小武
	 * @version  七彩虹
	 * @param param
	 * @return
	 */
	List<PlantItemProduct> selectPlantItemProductBySKUActiveId(Map<String,Object> param);
	
}

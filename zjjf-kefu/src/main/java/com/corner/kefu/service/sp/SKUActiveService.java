/**
 *@Title: SKUActiveService.java 
 *@Package com.corner.kefu.service.sp 
 *@Description: TODO
 *@author 龙五  longwu@izjjf.cn
 *@date 2016年8月1日 下午5:28:41 
 */
package com.corner.kefu.service.sp;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.corner.core.beans.CustomerService;
import com.corner.core.beans.SKUActive;
import com.corner.core.beans.SKUActiveGoodInfo;
import com.corner.core.beans.vo.Pager;
import com.corner.core.beans.vo.ResponseVo;
import com.corner.kefu.beans.vo.SKUActiveTagVo;
import com.corner.kefu.beans.vo.sp.PlantItemVo;
import com.corner.kefu.beans.vo.sp.SKUActiveGoodInfoVo;
import com.corner.kefu.beans.vo.sp.SKUActiveSessionObject;
import com.corner.kefu.beans.vo.sp.SKUActiveVo;

/**
 * @author LONGWU-PC
 * @ClassName: SKUActiveService 
 * @Description: TODO
 * @date 2016年8月1日 下午5:28:41 
 * @author 龙五  longwu@izjjf.cn
 */
public interface SKUActiveService {
	Integer actype_confirmByUser = 1;
	Integer actype_stopByJob = 3;
	Integer actype_stopByUser = 2;
	/**
	 * 0：新建
	 * <br/> 0：新建；1：审批；2：生效；3：自动结束；4：手动结束 
	 */
	Integer status_init = 0;
	/**
	 * 1：审批
	 * <br/> 0：新建；1：审批；2：生效；3：自动结束；4：手动结束
	 */
	Integer status_confirm = 1;
	/**
	 * 2：生效
	 * <br/> 0：新建；1：审批；2：生效；3：自动结束；4：手动结束
	 */
	Integer status_effec = 2;
	/**
	 * 3：自动结束
	 * <br/> 0：新建；1：审批；2：生效；3：自动结束；4：手动结束
	 */
	Integer status_stopJob = 3;
	/**
	 * 4：手动结束
	 * <br/> 0：新建；1：审批；2：生效；3：自动结束；4：手动结束
	 */
	Integer status_stopUser = 4;
	
	Integer log_actype_effec = 1;
	Integer log_actype_invalid = 0;
	
	Integer goodsType_goods = 0;
	Integer goodsType_goodsGroup = 3;

	Pager<SKUActiveVo> getAllSKUActive(SKUActive sKUActive);

	boolean addSKUActive(String[] plantItemIds, String[] spGroupIds, String[] supplierIds, SKUActiveSessionObject sessionObject,
			CustomerService customer, String[] buyLimitNums, String[] activePrices, String[] tagIds,String[] brandIds,String[] totalLimitNums)throws Exception;

//	SessionObject addGoodInfo(SessionObject sessionObject);
	
	/**
	 * 更改单品促销活动的状态
	 * @Title: updateSKUActiveStatus
	 * @date 2016年8月29日  下午2:20:52
	 * @author 小武
	 * @version  七彩虹
	 * @param actType
	 * @param id
	 * @param opUser
	 * @return
	 */
	int updateSKUActiveStatus(Integer actType,String id,String opUser);
	

	/**
	 * 停止一个单品促销活动
	 * @Title: stopSKUActiveByUser
	 * @date 2016年8月29日  下午1:58:27
	 * @author 小武
	 * @version  七彩虹
	 * @param id
	 * @param opUser
	 * @return
	 */
	ResponseVo stopSKUActiveByUser(String id,String opUser);
	
	/**
	 * 审批一个单品促销活动
	 * @Title: confirmSKUActiveByUser
	 * @date 2016年8月29日  下午1:58:42
	 * @author 小武
	 * @version  七彩虹
	 * @param id
	 * @param opUser
	 * @return
	 */
	ResponseVo confirmSKUActiveByUser(String id,String opUser);
	
	/**
	 * 生效活动数据到数据
	 * @Title: effecSKUActive
	 * @date 2016年8月30日  下午4:53:28
	 * @author 小武
	 * @version  飓风
	 * @param activeId
	 * @param opUser
	 * @return
	 */
	ResponseVo effecSKUActive(String activeId,String opUser);
	
	ResponseVo effecSKUActiveTask(String activeId,String opUser);
	/**
	 * 清理单品促销活动的PlatItem里面的数据
	 * @Title: invalidSKUActive
	 * @date 2016年8月29日  下午2:19:09
	 * @author 小武
	 * @version  七彩虹
	 * @param id
	 * @param opUser
	 * @return
	 */
	ResponseVo invalidSKUActive(String id,String opUser);
	
	/**
	 * 根据参数集合查询符合条件的商品信息
	 * @Title: queryPlatItemList
	 * @date 2016年8月30日  下午2:46:42
	 * @author 小武
	 * @version  七彩虹
	 * @param sessionObject
	 * @return
	 */
	List<PlantItemVo> queryPlatItemList(SKUActiveSessionObject sessionObject);
	
	/**
	 * 
	 * @Title: getSKUActiveById
	 * @date 2016年8月30日  下午6:59:37
	 * @author 小武
	 * @version  飓风
	 * @param id
	 * @return
	 */
	SKUActive getSKUActiveById(String id);
	/**
	 * 
	 * @Title: queryListById
	 * @date 2016年8月30日  下午6:59:40
	 * @author 小武
	 * @version  飓风
	 * @param id
	 * @return
	 */
	List<SKUActiveGoodInfo> queryListById(String id);
	/**
	 * 
	 * @Title: queryPlatItemList
	 * @date 2016年8月30日  下午7:13:30
	 * @author 小武
	 * @version  飓风
	 * @param list
	 * @return
	 */
	List<PlantItemVo> queryPlatItemList(List<SKUActiveGoodInfo> list);
	
	/**
	 * 查询商品列表
	 * @Title: queryPlatItemList
	 * @date 2016年9月1日  下午3:18:15
	 * @author 小武
	 * @version  飓风
	 * @param spIds
	 * @param spGroupIds
	 * @param itemBaseIds
	 * @return
	 */
	List<PlantItemVo> queryPlatItemList(List<String> spIds,List<Integer> spGroupIds,List<Integer> itemBaseIds);
	
	/**
	 * 编辑单品促销活动的时候，添加GoodInfo的数据
	 * @Title: querySKUActiveGoodInfoList4AddGoodInfoList
	 * @date 2016年9月1日  下午4:20:43
	 * @author 小武
	 * @version  飓风
	 * @param obj
	 * @param spGroupIds
	 * @param itemBaseIds
	 * @return
	 */
	List<SKUActiveGoodInfo> querySKUActiveGoodInfoList4AddGoodInfoList(SKUActiveSessionObject obj,Integer[] spGroupIds,Integer[] itemBaseIds);
	
	/**
	 * 保存数据到数据库
	 * @Title: saveEditSKUActive
	 * @date 2016年9月1日  下午7:07:30
	 * @author 小武
	 * @version  飓风
	 * @param sessionObject
	 * @return
	 */
	ResponseVo saveEditSKUActive(String opUser,SKUActiveSessionObject sessionObject);
	/**
	 * 查询单品活动详情数据
	 * @Title: querySKUActiveInfoVoList
	 * @date 2016年9月2日  上午10:25:07
	 * @author 小武
	 * @version  飓风
	 * @param skuActiveId
	 * @return
	 */
	List<SKUActiveGoodInfoVo> querySKUActiveInfoVoList(String skuActiveId);
	/**
	 * 对象转换
	 * @Title: switchSKUActiveGoodInfoList
	 * @date 2016年9月2日  上午11:01:21
	 * @author 小武
	 * @version  飓风
	 * @param skuActive
	 * @param plantItemList
	 * @return
	 */
	public List<SKUActiveGoodInfo> switchSKUActiveGoodInfoList(SKUActive skuActive,List<PlantItemVo> plantItemList);
	
	/**
	 * 对象转换
	 * @Title: switchSKUActiveGoodInfo
	 * @date 2016年9月2日  上午11:01:30
	 * @author 小武
	 * @version  飓风
	 * @param skuActive
	 * @param vo
	 * @return
	 */
	public SKUActiveGoodInfo switchSKUActiveGoodInfo(SKUActive skuActive,PlantItemVo vo);
	
	/**
	 * 对象集合转换
	 * @Title: switchSKUActiveGoodInfoVoList
	 * @date 2016年9月2日  上午11:00:26
	 * @author 小武
	 * @version  飓风
	 * @param skuActive
	 * @param plantItemList
	 * @return
	 */
	public List<SKUActiveGoodInfoVo> switchSKUActiveGoodInfoVoList(SKUActive skuActive,List<PlantItemVo> plantItemList);
	/**
	 * 对象集合转换
	 * @Title: switchSKUActiveGoodInfoVo
	 * @date 2016年9月2日  上午11:00:41
	 * @author 小武
	 * @version  飓风
	 * @param skuActive
	 * @param vo
	 * @return
	 */
	public SKUActiveGoodInfoVo switchSKUActiveGoodInfoVo(SKUActive skuActive,PlantItemVo vo);
	
	public ResponseVo updateSKUActiveGoodInfo(String skuActiveId,SKUActiveGoodInfo info);
	
	public ResponseVo updateSKUActiveGoodInfoTagId(String skuActiveId,String plantItemId,String tagId);
	
	public ResponseVo updateSKUActiveGoodInfoLimitNum(String skuActiveId,String plantItemId,Integer num);
	
	public ResponseVo updateSKUActiveGoodInfototalLimitNum(String skuActiveId,String plantItemId,Integer num);
	
	public ResponseVo updateSKUActiveGoodInfoPrice(String skuActiveId,String plantItemId,BigDecimal price);
	
	public ResponseVo updateEffecSKUActiveGoodInfoPrice(String skuActiveId,String plantItemId,BigDecimal price);
	
	public ResponseVo updateSKUActiveGoodInfo(String skuActiveId,List<SKUActiveGoodInfo> listInfo);
	
	public ResponseVo delSKUActiveGoodInfo(String skuActiveId,String plantItemId);
	
	List<SKUActiveTagVo> selectSKUActivePublishTag();
	/**
	 * 查询单品促销活动的发布标签
	 * @Title: querySKUActivePublishTag
	 * @date 2016年9月8日  下午4:11:20
	 * @author 小武
	 * @version  飓风
	 * @param id
	 * @return
	 */
	List<SKUActiveTagVo> querySKUActivePublishTag(String id);
	
	
	Map<String,Object> taskSKUActive();
}

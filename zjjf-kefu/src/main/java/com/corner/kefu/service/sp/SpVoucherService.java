/**
 * 
 */
package com.corner.kefu.service.sp;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.corner.core.beans.SpVoucher;
import com.corner.core.beans.SpVoucherActive;
import com.corner.core.beans.SpVoucherTemp;
import com.corner.core.beans.Store;
import com.corner.core.beans.msg.MsgBean;
import com.corner.core.beans.vo.Pager;
import com.corner.kefu.beans.vo.ConfigShareVo;
import com.corner.kefu.beans.vo.sp.StoreVo;

/**
 * @ClassName: SpVoucherService
 * @Description: 优惠劵业务类
 * @author: 杨开泰
 * @date: 2015年11月24日 下午12:39:58
 */
public interface SpVoucherService {
	/**
	 * 
	* @Title: sendSpVoucherByActive 
	* @Description: TODO(活动类型(0-默认活动,1-注册送优惠券,2-订单满送优惠券,3-订单满减,4-订单满折,5-订单送实物,6-送积分,7-送兑奖码)活动类型(0-默认活动,1-注册送优惠券,2-订单满送优惠券,3-订单满减,4-订单满折,5-订单送实物,6-送积分,7-送兑奖码)) 
	* @param @return    设定文件 
	* @return MsgBean    返回类型 
	* @author 铁中棠 tiezhongtang@izjjf.cn
	* @date 2016年1月6日 下午7:57:32 
	* @throws
	 */
	public MsgBean sendSpVoucherByActive(
			Integer sendFlag,  //活动类型(0-默认活动,1-注册送优惠券,2-订单满送优惠券,3-订单满减,4-订单满折,5-订单送实物,6-送积分,7-送兑奖码)
			String orderId,  //满送订单id，因为那比订单送的现金券
			String orderNo,
			BigDecimal orderPrice, //满送订单价格
			Byte payType,   //满送订单id对应的支付方式
			Integer spGroupId,//满送订单id对应的定格
			String acGroupId,//活动对应的用户组id
			Integer storeId, //满送订单id对应的店铺id
			String storeName,  //满送订单id对应的店铺名称
			String storeMobile //店铺电话
	);
	/**
	 * 
	* @Title: sendSpVoucherAuto 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param storeId 关联店铺
	* @param @param ruleId:优惠券规则id
	* @param @param once：是否一个用户只能发放一次
	* @param @param isPublish：0-不发布，1-发布
	* @param @return    设定文件 
	* @return SpVoucher    返回类型 
	* @author 铁中棠 tiezhongtang@izjjf.cn
	* @throws
	 */
	public SpVoucher sendSpVoucherAuto(
			Integer sendFlag,//发送标识1-注册送，2-满减送等等在配置文件中配置
			SpVoucherActive rule,  //发送规则
			String orderId,  //满送订单id，因为那比订单送的现金券
			String orderNo, //满送订单编号
			BigDecimal orderPrice, //满送订单价格
			Byte payType,   //满送订单id对应的支付方式
			Integer spGroupId,//满送订单id对应的定格
			Integer storeId, //满送订单id对应的店铺id
			String storeName,  //满送订单id对应的店铺名称
			String storeMobile //店铺电话
	);
	
	/**
	 * 
	* @Title: getSpVoucherIsUsedList 
	* @Description: 根据优惠劵id判断优惠劵是否已经本使用
	* @param @param id
	* @param @return
	* @param @throws Exception
	* @return List<SpVoucher>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	public List<SpVoucher> getSpVoucherIsUsedList(Integer id) throws Exception;
	
	/**
	 * 
	* @Title: asyAddSpVoucher 
	* @Description:发送优惠劵
	* @param @param list
	* @param @param spVoucherTemp
	* @param @throws Exception
	* @return void    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	public void asyAddSpVoucher(List<StoreVo> list, SpVoucherTemp spVoucherTemp) throws Exception;
	
	/**
	 * 
	* @Title: getSpVoucherByRuleIdAndStoreId 
	* @Description:根据优惠劵id和店铺id查询优惠劵使用情况
	* @param @param map
	* @param @return
	* @param @throws Exception
	* @return SpVoucher    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	public List<SpVoucher> getSpVoucherByRuleIdAndStoreId(Map<String, Object> map) throws Exception;
	
	/**
	 * 
	* @Title: deleteByRuleIdAndStoreId 
	* @Description:
	* @param @param map
	* @param @throws Exception
	* @return void    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	public void deleteByRuleIdAndStoreId(Map<String, Object> map) throws Exception;
	
	/**
	 * 
	* @Title: getSpVoucherUsedLog 
	* @Description:获得优惠劵的使用记录
	* @param @param map
	* @param @return
	* @param @throws Exception
	* @return Pager<StoreVo>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	public Pager<StoreVo> getSpVoucherUsedLog(Map<String, Object> map) throws Exception;
	
	public List<SpVoucher> getSpVoucerIsEdit(Integer id) throws Exception;
	public boolean saveSpVoucher(Store store1, ConfigShareVo configShare);
	
	public SpVoucher selectByPrimaryKey(String spVoucherId) throws Exception;
	
	public void deleteByPrimaryKey(String spVoucherId) throws Exception;
}

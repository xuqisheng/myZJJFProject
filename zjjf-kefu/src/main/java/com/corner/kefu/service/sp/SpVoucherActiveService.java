/**   
* @Title: SpVoucherActiveService.java 
* @Package com.corner.kefu.service.sp 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年3月29日 上午9:56:42 
* @version V1.0   
*/

package com.corner.kefu.service.sp;

import com.corner.core.beans.SpOrderInfo;
import com.corner.core.beans.SpVoucherActive;
import com.corner.core.beans.SpVoucherActiveStoreLog;
import com.corner.core.beans.SpVoucherTemp;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.kefu.beans.ro.sp.SpVoucherActiveMgRo;
import com.corner.kefu.beans.ro.sp.SpVoucherActiveRo;
import com.corner.kefu.beans.vo.sp.SpVoucherActiveVo;
import com.corner.kefu.beans.vo.sp.StoreMgVo;
import com.corner.kefu.beans.vo.sp.SupplierVo;

import java.util.List;
import java.util.Map;

/** 
* @ClassName: SpVoucherActiveService 
* @Description:活动管理接口
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年3月29日 上午9:56:42 
*  
*/

public interface SpVoucherActiveService {


	Pager<SpVoucherActiveVo> getSpVoucherActiveList(SpVoucherActiveRo spVoucherActiveRo) throws Exception;

	SpVoucherActive selectByPrimaryKey(Integer id) throws Exception;

	void save(Map<String, Object> map) throws Exception;

	ModelMsg updateByPrimaryKeySelective(SpVoucherActive spVoucherActive) throws Exception;

	SpVoucherActiveVo getSpVoucherActiveVoById(int parseInt) throws Exception;

	Pager<SupplierVo> getActiveSupplierList(SpVoucherActiveMgRo spVoucherActiveMgRo) throws Exception;

	SpVoucherTemp getSpVoucherTempBySendId(Integer sendId) throws Exception;

	/**
	 * 
	* @Title: updateBatchSpVoucherActive 
	* @Description:关闭所有已经结束的活动
	* @param @throws Exception
	* @return void    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	void updateBatchSpVoucherActive() throws Exception;

	/**
	 * 
	* @Title: getOrders 
	* @Description:查看订单
	* @param @param activeIdStr
	* @param @return
	* @param @throws Exception
	* @return List<SpOrderInfo>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	Pager<SpOrderInfo> getOrders(Map<String, Object> map) throws Exception;

	List<SpVoucherActive> getAccumuLateActive(SpVoucherActive spVoucherActive);

	List<String> getAssignStoreAccumuLateGroupIds(String[] storeIdArr);


	SpVoucherActive getSpVoucherActiveById(Integer id);

	/**
	 * @Title: 获取累积送券活动发送优惠券要发送的店铺
	 * @Description:
	 * @param 
	 * @return 
	 * @throws
	 * @author 杨开泰 yangkaitai@izjjf.cn
	 * @date 2016/10/25 0025 14:34
	 */
	Pager<StoreMgVo> getAccumulateStore(SpVoucherActiveRo activeRo);

	/**
	 * @Title: 获取累积送券活动发券日志
	 * @Description:
	 * @param 
	 * @return 
	 * @throws
	 * @author 杨开泰 yangkaitai@izjjf.cn
	 * @date 2016/10/25 0025 19:43
	 */
	List<SpVoucherActiveStoreLog> getAccLog(SpVoucherActive active);
}

package com.corner.kefu.dao.sp;

import com.corner.core.beans.SpOrderInfo;
import com.corner.core.beans.SpVoucherActive;
import com.corner.core.beans.SpVoucherActiveMiddle;
import com.corner.kefu.beans.ro.sp.SpVoucherActiveMgRo;
import com.corner.kefu.beans.ro.sp.SpVoucherActiveRo;
import com.corner.kefu.beans.vo.sp.SpVoucherActiveVo;
import com.corner.kefu.beans.vo.sp.SpVoucherGradedVo;
import com.corner.kefu.beans.vo.sp.SupplierVo;

import java.util.List;
import java.util.Map;


/**
 * 
 * @ClassName: SpVoucherActiveDao
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 铁中棠 tiezhongtang@izjjf.cn
 * @date 2015年12月27日 下午1:41:37
 *
 */
public interface SpVoucherActiveMgMapper{
	/**
	 * 
	* @Title: getAcGroupSpVoucherActive 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param ro
	* @param @return    设定文件 
	* @return List<SpVoucherActive>    返回类型 
	* @author 铁中棠 tiezhongtang@izjjf.cn
	* @date 2016年1月8日 下午3:09:23 
	* @throws
	 */
	public List<SpVoucherActive> getAcGroupSpVoucherActive(SpVoucherActiveRo ro);
	/**
	 * 
	* @Title: getSpVoucherActiveByCondition 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param ro
	* @param @return    设定文件 
	* @return List<SpVoucherActive>    返回类型 
	* @author 铁中棠 tiezhongtang@izjjf.cn
	* @date 2016年1月8日 下午3:09:23 
	* @throws
	 */
	public List<SpVoucherActive> getSpGroupSpVoucherActive(SpVoucherActiveRo ro);
	
	/**
	 * 
	* @Title: getSpVoucherActiveList 
	* @Description:获取活动列表
	* @param @param spVoucherActiveRo
	* @param @return
	* @param @throws Exception
	* @return List<SpVoucherActiveVo>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	public List<SpVoucherActiveVo> getSpVoucherActiveList(SpVoucherActiveRo spVoucherActiveRo) throws Exception;
	
	/**
	 * 
	* @Title: getCountSpVoucherActiveList 
	* @Description:
	* @param @param spVoucherActiveRo
	* @param @return
	* @param @throws Exception
	* @return Integer    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	public Integer getCountSpVoucherActiveList(SpVoucherActiveRo spVoucherActiveRo) throws Exception;
	
	/**
	 * 
	* @Title: insertSelectiveAndGetIdBack 
	* @Description: 插入活动数据,并且返回id
	* @param @param spVoucherActive
	* @param @throws Exception    设定文件 
	* @return void    返回类型 
	* @author 杨开泰   yangkaitai@izjjf.cn
	* @throws
	 */
	public void insertSelectiveAndGetIdBack(SpVoucherActive spVoucherActive) throws Exception;
	
	/**
	 * 
	* @Title: saveSpVoucherActiceWithGroupId 
	* @Description:保存活动和定格的关联关系 
	* @param @param map
	* @param @throws Exception    设定文件 
	* @return void    返回类型 
	* @author 杨开泰   yangkaitai@izjjf.cn
	* @throws
	 */
	public void saveSpVoucherActiceWithGroupId(Map<String, Object> map) throws Exception;
	
	/**
	 * 
	* @Title: deleteSpVoucherActiceWithGroupId 
	* @Description:删除和活动关联的定格id 
	* @param @param spVoucherActive
	* @param @throws Exception    设定文件 
	* @return void    返回类型 
	* @author 杨开泰   yangkaitai@izjjf.cn
	* @throws
	 */
	public void deleteSpVoucherActiceWithGroupId(SpVoucherActive spVoucherActive) throws Exception;
	/**
	 * 
	* @Title: getCountParticipationInActive 
	* @Description:查询参与某个活动的批发商数
	* @param @param spVoucherActiveVo2
	* @param @return
	* @param @throws Exception
	* @return Integer    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	public List<SpVoucherActiveMiddle> getCountParticipationInActive(SpVoucherActiveVo spVoucherActiveVo2) throws Exception;
	
	/**
	 * 
	* @Title: getSpVoucherActiveVoById 
	* @Description:获取活动视图类
	* @param @param id
	* @param @return
	* @param @throws Exception
	* @return SpVoucherActiveVo    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	public SpVoucherActiveVo getSpVoucherActiveVoById(int id) throws Exception;
	
	/**
	 * 
	* @Title: getActiveSupplierList 
	* @Description:查询参与活动的批发商列表
	* @param @param spVoucherActiveMgRo
	* @param @return
	* @param @throws Exception
	* @return List<SupplierVo>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	public List<SupplierVo> getActiveSupplierList(SpVoucherActiveMgRo spVoucherActiveMgRo) throws Exception;
	
	
	/**
	 * 
	* @Title: getCountActiveSupplierList 
	* @Description:查询参与活动的批发商列表总数
	* @param @param spVoucherActiveMgRo
	* @param @return
	* @param @throws Exception
	* @return Integer    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	public Integer getCountActiveSupplierList(SpVoucherActiveMgRo spVoucherActiveMgRo) throws Exception;
	/**
	 * 
	* @Title: updateBatchSpVoucherActive 
	* @Description:批量结束已经到期的活动
	* @param @throws Exception
	* @return void    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	public void updateBatchSpVoucherActive() throws Exception;
	
	/**
	 * 
	* @Title: getTotalConsumerAndFee 
	* @Description:计算客户数和平台承担成本
	* @param @param map
	* @param @return
	* @param @throws Exception
	* @return Map<String,Object>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	public Map<String, Object> getTotalConsumerAndFee(Map<String, Object> map) throws Exception;
	
	/**
	 * 
	* @Title: getOrders 
	* @Description:查看订单
	* @param @param map
	* @param @return
	* @param @throws Exception
	* @return List<SpOrderInfo>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	public List<SpOrderInfo> getOrders(Map<String, Object> map) throws Exception;
	
	
	public void deleteFromMiddle(SpVoucherActive spVoucherActive) throws Exception;
	
	/**
	 * 
	* @Title: getAllFinishActive 
	* @Description:查询所有已经到期,而且status状态为1的活动
	* @param @return
	* @param @throws Exception
	* @return List<SpVoucherActive>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	public List<SpVoucherActive> getAllFinishActive() throws Exception;
	
	/**
	 * 
	* @Title: updateFinishActive 
	* @Description:从
	* @param @param list
	* @param @throws Exception
	* @return void    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	public void updateFinishActive(List<SpVoucherActive> list) throws Exception;
	
	/**
	 * 
	* @Title: updateAllFinishActive 
	* @Description:将所有已经到期的活动status变为0（已结束）
	* @param @throws Exception
	* @return void    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	public void updateAllFinishActive() throws Exception;
	
	/**
	 * 
	* @Title: updateStartAllActive 
	* @Description:将所有今天要开始的活动状态status改为1
	* @param @throws Exception
	* @return void    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	public void updateStartAllActive() throws Exception;
	/**
	 * 
	* @Title: getCountOrders 
	* @Description:
	* @param @param map
	* @param @return
	* @param @throws Exception
	* @return Integer    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	public Integer getCountOrders(Map<String, Object> map) throws Exception;

    List<SpVoucherActive> getAccumuLateActive(SpVoucherActive spVoucherActive);

	List<String> getAssignStoreAccumuLateGroupIds(String[] storeIdArr);

    List<SpVoucherGradedVo> getSpVoucherGradeListByActiveId(Integer id);
}

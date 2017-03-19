/**
 * 
 */
package com.corner.kefu.dao.sp;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.corner.core.beans.SpVoucher;
import com.corner.kefu.beans.ro.sp.SpVoucherRo;
import com.corner.kefu.beans.ro.sp.SpVoucherTempMgRo;
import com.corner.kefu.beans.vo.sp.SpVhVo;
import com.corner.kefu.beans.vo.sp.SpVoucherTempVo;
import com.corner.kefu.beans.vo.sp.StoreVo;

/**
 * 
 * @ClassName: SpVoucherDao
 * 
 * @Description: 优惠券Dao类
 * 
 * @author: 杨开泰
 * 
 * @date: 2015年11月24日 下午12:40:49
 */
@Repository
public interface SpVoucherMgMapper{
	/**
	 * 
	 * @author luke at 2015年11月24日下午8:43:01
	 * @email tiezhongtang@izjjf.cn
	 * @param List
	 *            <SpVoucherVo>
	 * @return
	 */
	public List<SpVhVo> getStoreVoucherVoList(SpVoucherRo spvRo);

	/**
	 * 
	* @Title: getSpVoucherIsUsedList 
	* @Description:根据优惠劵id判断优惠劵有没有被使用
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
	* @Title: deleteBySpVoucherTempId 
	* @Description:根据优惠劵模板id删除用户优惠劵模板
	* @param @param id
	* @param @throws Exception
	* @return void    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	public void deleteBySpVoucherTempId(Integer id) throws Exception;
     
    /**
     * 
    * @Title: getStoreIsHasSpVoucher 
    * @Description:
    * @param @param map
    * @param @return
    * @param @throws Exception
    * @return List<SpVoucher>    返回类型
    *@author 杨开泰 yangkaitai@izjjf.cn
    * @throws
     */
	public List<SpVoucher> getStoreIsHasSpVoucher(Map<String, Object> map)  throws Exception;

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
	* @Description:获取优惠劵使用记录表
	* @param @param map
	* @param @return
	* @param @throws Exception
	* @return List<StoreVo>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	public List<StoreVo> getSpVoucherUsedLog(Map<String, Object> map) throws Exception;

	/**
	 * 
	* @Title: getCountSpVoucherUsedLog 
	* @Description:
	* @param @param map
	* @param @return
	* @param @throws Exception
	* @return Integer    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	public Integer getCountSpVoucherUsedLog(Map<String, Object> map) throws Exception;

	/**
	 * 
	* @Title: getSupplierSpVoucherUsedList 
	* @Description:通过活动id 和批发商id 查询SpVoucher 表中的已经使用的SpVoucher集合数据
	* @param @param map
	* @param @return
	* @param @throws Exception
	* @return List<SpVoucher>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	public List<SpVoucher> getSupplierSpVoucherUsedList(Map<String, Object> map) throws Exception;

	public List<SpVoucher> getSpVoucerIsEdit(Integer id) throws Exception;

	/**
	 * 
	* @Title: batchRemoveSpVoucher 
	* @Description:清空店铺批发商优惠劵
	* @param @param storeIdArr
	* @return void    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	public void batchRemoveSpVoucher(String[] storeIdArr);

	/**
	 * 
	* @Title: getSpVoucherTempIndex 
	* @Description:优惠劵管理首页
	* @param @param spVoucherTempRo
	* @param @return
	* @param @throws Exception
	* @return List<SpVoucherTempVo>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	public List<SpVoucherTempVo> getSpVoucherTempIndex(SpVoucherTempMgRo spVoucherTempMgRo) throws Exception;

	/**
	 * 
	* @Title: getCountSpVoucherTempIndex 
	* @Description:
	* @param @param spVoucherTempRo
	* @param @return
	* @param @throws Exception
	* @return Integer    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	public Integer getCountSpVoucherTempIndex(SpVoucherTempMgRo spVoucherTempMgRo) throws Exception;

    void batchSave(List<SpVoucher> voucherlist);

    BigDecimal getTotalReturn(Map<String, Object> paramMap);
}

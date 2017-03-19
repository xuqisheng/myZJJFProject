package com.corner.kefu.dao.sp;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.corner.core.beans.SpVoucherTemp;
import com.corner.kefu.beans.ro.sp.SpVoucherTempRo;
import com.corner.kefu.beans.vo.sp.SpVoucherTempVo;

/**
 * 
 * @ClassName: SpVoucherTempDao
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 铁中棠 tiezhongtang@izjjf.cn
 * @date 2015年12月27日 下午1:41:37
 *
 */
@Repository
public interface SpVoucherTempMgMapper{
	/**
	 * 
	* @Title: getSpVoucherTempByCondition 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param ro
	* @param @return    设定文件 
	* @return List<SpVoucherTemp>    返回类型 
	* @author 铁中棠 tiezhongtang@izjjf.cn
	* @date 2016年1月7日 下午10:05:07 
	* @throws
	 */
	public List<SpVoucherTemp> getSpVoucherTempByCondition(SpVoucherTempRo ro);

	/**
	 * @param spVoucherTempRo 
	 * 
	* @Title: getPageSpVoucherTempList 
	* @Description:分页查询优惠劵模板列表
	* @param @return
	* @param @throws Exception
	* @return List<SpVoucherTemp>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	public List<SpVoucherTemp> getPageSpVoucherTempList(SpVoucherTempRo spVoucherTempRo) throws Exception;

	/**
	 * 
	* @Title: getCountPageSpVoucherTempList 
	* @Description:查询优惠劵模板总量
	* @param @param spVoucherTempRo
	* @param @return
	* @param @throws Exception
	* @return Integer    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	public Integer getCountPageSpVoucherTempList(SpVoucherTempRo spVoucherTempRo) throws Exception;

	/**
	 * 
	* @Title: getSpVoucherTemp 
	* @Description:更具id查询优惠劵信息
	* @param @param spVoucherTemp
	* @param @return
	* @param @throws Exception
	* @return SpVoucherActiveVo    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	public SpVoucherTempVo getSpVoucherTemp(SpVoucherTemp spVoucherTemp) throws Exception;

	/**
	 * 
	* @Title: getCountSpVoucherTempList 
	* @Description:获取优惠劵列表总量
	* @param @param map
	* @param @return
	* @param @throws Exception
	* @return Integer    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	public Integer getCountSpVoucherTempList(Map<String, Object> map) throws Exception;

	/**
	 * 
	* @Title: getSpVoucherTempList 
	* @Description:查询优惠劵列表
	* @param @param map
	* @param @return
	* @param @throws Exception
	* @return List<SpVoucherTempVo>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	public List<SpVoucherTempVo> getSpVoucherTempList(Map<String, Object> map) throws Exception;

	/**
	 * 
	* @Title: getGrantCountAndUsedCount 
	* @Description:查询优惠劵使用数
	* @param @param id
	* @param @return
	* @param @throws Exception
	* @return Integer    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	public Integer getGrantCountAndUsedCount(Integer id) throws Exception;

	/**
	 * 
	* @Title: getIsElegalSpVoucherTemp 
	* @Description:判断优惠劵模板是否合法
	* @param @param id
	* @param @return
	* @param @throws Exception
	* @return SpVoucherTemp    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	public SpVoucherTemp getIsElegalSpVoucherTemp(Integer id) throws Exception;

	/**
	 * 
	* @Title: removeSpvoucherTempById 
	* @Description:逻辑删除优惠劵模板
	* @param @param id
	* @param @throws Exception
	* @return void    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	public void removeSpvoucherTempById(Integer id) throws Exception;
}

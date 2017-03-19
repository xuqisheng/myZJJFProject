/**   
* @Title: SpVoucherTempService.java 
* @Package com.corner.kefu.service.sp 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年3月30日 下午6:36:07 
* @version V1.0   
*/

package com.corner.kefu.service.sp;

import java.util.List;
import java.util.Map;

import com.corner.core.beans.SpVoucherTemp;
import com.corner.core.beans.vo.Pager;
import com.corner.kefu.beans.ro.sp.SpVoucherTempMgRo;
import com.corner.kefu.beans.ro.sp.SpVoucherTempRo;
import com.corner.kefu.beans.vo.sp.SpVoucherTempVo;

/** 
* @ClassName: SpVoucherTempService 
* @Description:优惠劵模板业务类
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年3月30日 下午6:36:07 
*  
*/

public interface SpVoucherTempService {

	Pager<SpVoucherTemp> getPageSpVoucherTempList(SpVoucherTempRo spVoucherTempRo) throws Exception;

	/**
	 * 
	* @Title: selectByPrimaryKey 
	* @Description:根据优惠劵id查询优惠劵
	* @param @param sendId
	* @param @return
	* @return SpVoucherTemp    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	SpVoucherTemp selectByPrimaryKey(Integer sendId);

	/**
	 * 
	* @Title: getSpVoucherTemp 
	* @Description:根据id查询优惠劵信息
	* @param @param spVoucherTemp
	* @param @return
	* @param @throws Exception
	* @return SpVoucherActiveVo    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	SpVoucherTempVo getSpVoucherTemp(SpVoucherTemp spVoucherTemp) throws Exception;

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
	Integer getCountSpVoucherTempList(Map<String, Object> map) throws Exception;

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
	List<SpVoucherTempVo> getSpVoucherTempList(Map<String, Object> map) throws Exception;

	/**
	 * 
	* @Title: getIsElegalSpVoucherTemp 
	* @Description:查询是否合法优惠劵
	* @param @param id
	* @param @return
	* @param @throws Exception
	* @return SpVoucherTemp    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	SpVoucherTemp getIsElegalSpVoucherTemp(Integer id) throws Exception;

	/**
	 * 
	* @Title: addSpVoucherTemp 
	* @Description:新增优惠劵模板
	* @param @param spVoucherTemp
	* @param @throws Exception
	* @return void    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	void addSpVoucherTemp(SpVoucherTemp spVoucherTemp) throws Exception;

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
	void removeSpvoucherTempById(Integer id) throws Exception;

	/**
	 * 
	* @Title: updatSpVoucherTemp 
	* @Description:update优惠劵
	* @param @param spVoucherTemp
	* @param @throws Exception
	* @return void    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	void updatSpVoucherTemp(SpVoucherTemp spVoucherTemp) throws Exception;

	/**
	 * 
	* @Title: getSpVoucherTempIndex 
	* @Description:查询优惠劵管理页面首页
	* @param @param spVoucherTempRo
	* @param @return
	* @param @throws Exception
	* @return Pager<SpVoucherTempVo>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	Pager<SpVoucherTempVo> getSpVoucherTempIndex(SpVoucherTempMgRo spVoucherTempMgRo) throws Exception;

}

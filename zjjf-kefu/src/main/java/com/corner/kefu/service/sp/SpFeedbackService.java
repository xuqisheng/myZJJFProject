/**
 * 
 */
package com.corner.kefu.service.sp;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.corner.core.beans.SpFeedback;
import com.corner.core.beans.vo.Pager;
import com.corner.kefu.beans.ro.sp.SpFeedbackRo;
import com.corner.kefu.beans.vo.sp.SpFeedbackVo;

/**
 * 
 * @ClassName: SpFeedbackService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 铁中棠 tiezhongtang@izjjf.cn
 * @date 2016年1月30日 上午12:17:00
 *
 */
@Service
public interface SpFeedbackService {

	
	/**
	 * 
	 * @Title: getStoreFeedbackVoList
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param spfbRo
	 * @param @return 设定文件
	 * @return List<SpFeedbackVo> 返回类型
	 * @author 铁中棠 tiezhongtang@izjjf.cn
	 * @date 2016年1月30日 下午1:35:49
	 * @throws
	 */
	public List<SpFeedbackVo> getStoreFeedbackVoList(SpFeedbackRo spfbRo);

	/**
	 * 
	 * @Title: saveStoreFeedback
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param spfbRo
	 * @param @return 设定文件
	 * @return List<SpFeedbackVo> 返回类型
	 * @author 铁中棠 tiezhongtang@izjjf.cn
	 * @date 2016年1月30日 下午1:41:22
	 * @throws
	 */
	public List<SpFeedbackVo> saveStoreFeedback(SpFeedbackRo spfbRo) ;

	
	/**
	 * 根据条件查出所有的反馈消息
	 * @Title
	 * @Description: TODO 
	 * @param @param spFeedbackRo
	 * @param @return
	 * @2016年1月31日     
	 * @author 龙五  longwu@izjjf.cn
	 * @return
	 * @throws
	 */
	public Pager<SpFeedbackVo> getAllSpFeedbackByParam(SpFeedbackRo spFeedbackRo);
	
//	/**
//	 * 根据批发商id查出所有的反馈内容和客服回复内容
//	* @Title
//	* @Description: TODO 
//	* @param @param storeId
//	* @param @return
//	* @2016年2月25日     
//	* @author 龙五  longwu@izjjf.cn
//	* @return
//	* @throws
//	 */
//	public Pager<SpFeedbackVo> getAllFeedbackByStoreId(SpFeedbackRo spFeedbackRo);
//	/**
//	 * 保存客服回复内容
//	* @Title
//	* @Description: TODO 
//	* @param @param spFeedbackRo
//	* @2016年1月31日     
//	* @author 龙五  longwu@izjjf.cn
//	* @return
//	* @throws
//	 */
//	public void saveCustomerServiceHuiFu(SpFeedback spFeedback);

	public SpFeedbackVo getFeedbackById(Map<String, Object> map);

	public void updateFeedback(SpFeedback spFeedback);
}

/**
 * 
 */
package com.corner.kefu.service.sp.impl;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.beans.SpFeedback;
import com.corner.core.beans.vo.Pager;
import com.corner.core.dao.SpFeedbackMapper;
import com.corner.kefu.beans.ro.sp.SpFeedbackRo;
import com.corner.kefu.beans.vo.sp.SpFeedbackVo;
import com.corner.kefu.dao.sp.SpFeedbackMgMapper;
import com.corner.kefu.service.sp.SpFeedbackService;

/**
 * 
 * @ClassName: SpFeedbackService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 铁中棠 tiezhongtang@izjjf.cn
 * @date 2016年1月30日 上午12:17:00
 *
 */
@Service
public class SpFeedbackServiceImpl implements SpFeedbackService{

	public static Logger logger = LoggerFactory.getLogger(SpFeedbackServiceImpl.class);

	@Autowired
	SpFeedbackMgMapper spFeedbackMgMapper;
	@Autowired
	SpFeedbackMapper spFeedbackMapper;
	
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
	@Override
	public List<SpFeedbackVo> getStoreFeedbackVoList(SpFeedbackRo spfbRo) {
		List<SpFeedbackVo> list = spFeedbackMgMapper.getStoreSpFeedbackVoList(spfbRo);
		if (list == null || list.isEmpty()) {
			return list;
		} else {
			Collections.reverse(list);
			return list;
		}
	}

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
	@Override
	public List<SpFeedbackVo> saveStoreFeedback(SpFeedbackRo spfbRo) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		spfbRo.setToday(cal.getTime());
		int seedLimt = spFeedbackMgMapper.checkSendLimit(spfbRo);
		if (seedLimt > 20) {
			return null;
		}
		SpFeedback spFeedback = new SpFeedback();
		spFeedback.setAddTime(new Date());
		spFeedback.setStoreId(spfbRo.getStoreId());
		spFeedback.setTitle(spfbRo.getTitle());
		spFeedback.setContent(spfbRo.getContent());
		spFeedback.setSendUser((byte)0);
		int success = spFeedbackMapper.insertSelective(spFeedback);
		if (success == 1) {
			return getStoreFeedbackVoList(spfbRo);
		}
		return null;
	}

	
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
	@Override
	public Pager<SpFeedbackVo> getAllSpFeedbackByParam(SpFeedbackRo spFeedbackRo){
		List<SpFeedbackVo> SpFeedbackList = spFeedbackMgMapper.getAllSpFeedbackByParam(spFeedbackRo);
		int num = spFeedbackMgMapper.countSpFeedback(spFeedbackRo);
		return new Pager<SpFeedbackVo>(num, SpFeedbackList);
	}
//	
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
//	@Override
//	public Pager<SpFeedbackVo> getAllFeedbackByStoreId(SpFeedbackRo spFeedbackRo){
//		List<SpFeedbackVo> spFeedbackVoList =  spFeedbackMgMapper.getAllFeedbackByStoreId(spFeedbackRo);
//		int num = spFeedbackMgMapper.getAllFeedbackByStoreIdCount(spFeedbackRo);
//		
//		return new Pager<SpFeedbackVo>(num, spFeedbackVoList);
//	}
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
//	@Override
//	public void saveCustomerServiceHuiFu(SpFeedback spFeedback){
//		spFeedbackMapper.insertSelective(spFeedback);
//	}

	@Override
	public SpFeedbackVo getFeedbackById(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return spFeedbackMgMapper.getFeedbackById(map);
	}

	@Override
	public void updateFeedback(SpFeedback spFeedback) {
		// TODO Auto-generated method stub
		spFeedbackMapper.updateByPrimaryKeySelective(spFeedback);
	}

}

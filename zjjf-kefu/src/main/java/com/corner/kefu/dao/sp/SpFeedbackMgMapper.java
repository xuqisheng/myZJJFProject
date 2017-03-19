package com.corner.kefu.dao.sp;

import java.util.List;
import java.util.Map;

import com.corner.kefu.beans.ro.sp.SpFeedbackRo;
import com.corner.kefu.beans.vo.sp.SpFeedbackVo;

public interface SpFeedbackMgMapper {

	List<SpFeedbackVo> getStoreSpFeedbackVoList(SpFeedbackRo spfbRo);

	int checkSendLimit(SpFeedbackRo spfbRo);

	List<SpFeedbackVo> getAllSpFeedbackByParam(SpFeedbackRo spFeedbackRo);

	int countSpFeedback(SpFeedbackRo spFeedbackRo);

//	List<SpFeedbackVo> getAllFeedbackByStoreId(SpFeedbackRo spFeedbackRo);

//	int insertSelective(SpFeedback spFeedback);

	int getAllFeedbackByStoreIdCount(SpFeedbackRo spFeedbackRo);

	SpFeedbackVo getFeedbackById(Map<String, Object> map);


	
}

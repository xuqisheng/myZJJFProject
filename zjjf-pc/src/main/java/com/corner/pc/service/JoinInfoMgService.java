package com.corner.pc.service;

import java.util.List;

import com.corner.pc.beans.JoinInfo;
import com.corner.pc.beans.ro.JoinCondition;
import com.corner.pc.beans.vo.ModelMsg;


public interface JoinInfoMgService {

	ModelMsg submitJoinInfo(JoinCondition joinCondition);

	List<JoinInfo> getJoinfoList() throws Exception;

}

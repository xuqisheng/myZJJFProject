package com.corner.pc.dao;

import java.util.List;

import com.corner.pc.beans.JoinInfo;


public interface JoinInfoMapper {

	int insertSelective(JoinInfo joinInfo);

	List<JoinInfo> getJoinfoList();

}

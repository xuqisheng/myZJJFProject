package com.corner.kefu.dao.sp;

import java.util.List;

import com.corner.core.beans.Adboard;

public interface SpAdboardMgMapper {

	List<Adboard> getAdPositionList();

	void updateByPrimaryKeySelective(Adboard adboard);

	void insertSelective(Adboard adboard);

	Adboard getAdboardById(Integer id);

	void removeAdPosition(Integer id);

	void deleteByPrimaryKey(Integer id);

	void deleteAdByAdBoardId(Integer id);

	Adboard getadBoardByAdId(Integer id);
	
	List<Adboard> getAllAdboard();

	
	
}

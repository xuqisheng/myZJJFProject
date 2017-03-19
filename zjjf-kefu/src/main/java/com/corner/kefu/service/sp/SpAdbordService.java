package com.corner.kefu.service.sp;

import java.util.List;

import com.corner.core.beans.Adboard;


public interface SpAdbordService {

	/**
	 * 查询广告位列表
	 * @return
	 * @throws Exception 
	 */
	public List<Adboard> getAdPositionList();

	public void updateByPrimaryKeySelective(Adboard adboard);

	public void saveAdboard(Adboard adboard);

	public Adboard getAdboardById(Integer id);

	public void removeAdPosition(Integer id);

	public Adboard getadBoardByAdId(Integer id);
	
	public List<Adboard> getAllAdboard();

}
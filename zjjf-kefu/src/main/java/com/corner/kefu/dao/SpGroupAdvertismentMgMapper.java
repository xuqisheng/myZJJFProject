package com.corner.kefu.dao;

import java.util.List;

import com.corner.core.beans.SpGroupAdvertisment;

public interface SpGroupAdvertismentMgMapper {
	public List<SpGroupAdvertisment> getGroupAdIds(Integer id);

	public void deleteDataByAdId(Integer id);
}

package com.corner.kefu.dao;

import java.util.List;

import com.corner.core.beans.SpGroupAd2BordMapKey;

public interface SpGroupAd2BordMapMgMapper {

	List<SpGroupAd2BordMapKey> getBoardByGroupAdId(String id);

	void delAd2BordMapByGroupAdId(String id);

}

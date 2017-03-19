package com.corner.account.service;

import java.util.List;

import com.corner.core.beans.Region;

public interface PublicService extends BaseService {
	List<Region> getRegions();
}

package com.corner.kefu.service.scms;

import java.util.List;

import com.corner.core.beans.ScmsFreightTpl;
import com.corner.core.beans.ScmsFreightTplMap;
import com.corner.core.beans.vo.Pager;
import com.corner.kefu.beans.ro.ScmsFreightTplRo;
import com.corner.kefu.service.BaseService;

public interface TempletService extends BaseService{

	Pager<ScmsFreightTpl> findAllTemplet(ScmsFreightTpl condition);

	Integer deleteObject(String id);

	int addObject(ScmsFreightTplRo condition);

	ScmsFreightTpl findTelById(String id);

	List<ScmsFreightTplMap> findAllTplMapByUid(String id);

	int edit(ScmsFreightTplRo condition);

}

package com.corner.kefu.dao.sp;

import java.util.List;
import java.util.Map;

import com.corner.core.beans.SpHelp;
import com.corner.kefu.beans.vo.sp.SpHelpVo;

public interface SpSpHelpMgMapper {

	List<SpHelpVo> getAllHelpByParameter(SpHelp spHelp);

	void orderHelpList(Map<String, Object> param);
}

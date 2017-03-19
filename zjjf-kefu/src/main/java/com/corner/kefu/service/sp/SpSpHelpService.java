package com.corner.kefu.service.sp;

import java.util.List;
import java.util.Map;

import com.corner.core.beans.SpHelp;
import com.corner.kefu.beans.vo.sp.SpHelpVo;

public interface SpSpHelpService {

	List<SpHelpVo> getAllHelpByParameter(SpHelp spHelp);

	void addHelp(SpHelp spHelp);

	void updateHelp(SpHelp spHelp);

	void orderHelpList(Map<String, Object> param);

	SpHelp getHelpById(int id);

}

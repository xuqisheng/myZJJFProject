package com.corner.auth.dao.mg;

import java.util.List;

import com.corner.auth.beans.Position;
import com.corner.auth.beans.ro.JobRo;


public interface PositionMgMapper {
	public Integer getPositionListCount(JobRo jobRo);
	public List<Position> getPositionList(JobRo jobRo);
}
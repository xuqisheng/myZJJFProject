package com.corner.pc.beans.vo;

import java.util.ArrayList;
import java.util.List;

import com.corner.pc.beans.Recruit;
import com.corner.pc.beans.RecruitType;

public class RecruitTypeVo extends RecruitType {
	
    List<Recruit> recruits = new ArrayList<Recruit>();

	public List<Recruit> getRecruits() {
		return recruits;
	}

	public void setRecruits(List<Recruit> recruits) {
		this.recruits = recruits;
	}



}
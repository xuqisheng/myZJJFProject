package com.zjjf.analysis.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zjjf.analysis.controller.BaseController;

public abstract class GoalSetBase extends BaseController{
	
	protected Boolean verifyParam(Object obj) {
		Boolean isEffect = true;
		if ( (obj == null) || ("".equals(obj)) || (obj instanceof Double) ) {
			isEffect = false;
		}
		return isEffect;
	}
	
	protected void putTemplate(HashMap<String, Object> colMap, List<String> keys, Object... params) {
		if (keys.size() != params.length) {
			throw new RuntimeException("key,value参数不一致,key=[" + keys + "],value=[" + params + "]");
		} else {
			for (int i = 0; i < keys.size(); i++) {
				colMap.put(keys.get(i), params[i]);
			}
		}
	}
	
	protected Integer getDaily(Object obj) {
		Integer daily = 0;
		if (obj == null || ("".equals(obj))) {
		} else {
			Integer goal = Integer.valueOf(obj + "");
			if (goal % 30 != 0) {
				daily = goal / 30 + 1;
			} else {
				daily = goal / 30;
			}
		}
		return daily;
	}
	
	protected void setSpMap (Object[] obj, HashMap<String, Object> colMap) {
			
		List<String> keys = new ArrayList<String>(2);
		keys.add(0, "goal");
		keys.add(1, "dailyGoal");
		
		if ( !this.verifyParam(obj[5]) ) {
			putTemplate(colMap, keys, 0, 0);
		} else {
			putTemplate(colMap, keys, obj[5], getDaily(obj[5]));
		}
			
	}
	
	protected void setStoreMap (Object[] obj, HashMap<String, Object> colMap) {
		List<String> keys = new ArrayList<String>(4);
		keys.add(0, "regGoal");
		keys.add(1, "actGoal");
		keys.add(2, "newRisteredGoleDaily");
		keys.add(3, "activeStoreGoleDaily");
		
		if ( (!this.verifyParam(obj[4])) && (!this.verifyParam(obj[5])) ) {
			putTemplate(colMap, keys, 0, 0, 0, 0);
		} else if (!this.verifyParam(obj[4])) {
			putTemplate(colMap, keys, 0, obj[5], 0, getDaily(obj[5]));
		} else if (!this.verifyParam(obj[5])) {
			putTemplate(colMap, keys, obj[4], 0, getDaily(obj[4]), 0);
		} else {
			putTemplate(colMap, keys, obj[4], obj[5], getDaily(obj[4]), getDaily(obj[5]));
		}
	}
}

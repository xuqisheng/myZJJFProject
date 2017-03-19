package com.corner.task.service;

import java.util.List;

import com.corner.task.beans.SKUActive;
import com.corner.task.beans.msg.ModelMsg;

public interface SKUActiveTaskService {
	Integer actype_confirmByUser = 1;
	Integer actype_stopByJob = 3;
	Integer actype_stopByUser = 2;
	//0：新建；1：审批；2：生效；3：自动结束；4：手动结束
	Integer status_init = 0;
	Integer status_confirm = 1;
	Integer status_effec = 2;
	Integer status_stopJob = 3;
	Integer status_stopUser = 4;
	
	Integer log_actype_effec = 1;
	Integer log_actype_invalid = 0;
	
	Integer goodsType_goods = 0;
	Integer goodsType_goodsGroup = 3;
	
	ModelMsg effecSKUActive();
	ModelMsg invalidSKUActive();
	ModelMsg taskSKUActive();
	String queryKefuHost();
	String queryKefuAuth();
	void schedulerSKUActive(List<SKUActive> schedulerList);
	void taskAllSKUActive();
	void taskInvalidNow(SKUActive one);
	void taskEffecNow(SKUActive one);
	
}

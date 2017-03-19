package com.corner.task.tasks;

import org.apache.poi.ss.formula.functions.T;

import com.corner.task.beans.msg.ModelMsg;
import com.corner.task.util.SpringContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class TaskBase {
	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * 
	* @Title: taskBase 
	* @Description: 定时任务抽象方法
	* @param: taskParams	接收参数json格式数据
	* @return ModelMsg    返回类型 
	* @author 孟星魂	mengxinghun@izjjf.cn
	 */
	public abstract  ModelMsg taskBase(String taskParams);
	
	/**
	 * 
	* @Title: getBean 
	* @Description: 加载bean方法
	* @param: clazz
	* @return	设定文件 
	* @return T    返回类型 
	* @author 孟星魂	mengxinghun@izjjf.cn
	 */
	@SuppressWarnings("hiding")
	public <T> T getBean(Class<T> clazz){
		return SpringContextHolder.getBean(clazz);
	}
}

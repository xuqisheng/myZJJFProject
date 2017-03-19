package com.corner.kefu.service.scms;

import java.util.List;

import org.springframework.stereotype.Service;

import com.corner.core.beans.ScmsItem;
import com.corner.core.beans.ScmsMinimum;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.kefu.beans.ro.scms.ScmsMinimumMgRo;
import com.corner.kefu.service.BaseService;
/**
 * 
 * @ClassName: ScmsMinimumMgService 
 * @Description: 品牌起购量信息操作
 * @author 孟星魂	mengxinghun@izjjf.cn
 * @date 2015年12月28日 上午11:45:29 
 *
 */
@Service
public interface ScmsMinimumMgService extends BaseService{
	/**
	 * 
	* @Title: getAllScmsManager 
	* @Description: 获取起购量信息
	* @param @return    设定文件 
	* @return List<ScmsManager>    返回类型 
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @throws
	 */
	List<ScmsMinimum> getScmsMinimumByManagerId(String managerId);
	
	/**
	 * 
	* @Title: updateScmsMinimum 
	* @Description: 修改起购量信息 
	* @param @param command
	* @param @return    设定文件 
	* @return ModelMsg    返回类型 
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @throws
	 */
	ModelMsg updateScmsMinimum(ScmsMinimumMgRo command);
	
	/**
	 * 
	* @Title: insert
	* @Description: 修改起购量信息 
	* @param @param command
	* @param @return    设定文件 
	* @return ModelMsg    返回类型 
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @throws
	 */
	ModelMsg insertScmsMinimum(ScmsItem command);
}

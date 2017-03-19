package com.corner.kefu.service.scms;

import java.util.List;

import org.springframework.stereotype.Service;

import com.corner.core.beans.ScmsManager;
import com.corner.core.beans.Supplier;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.kefu.beans.ro.scms.ScmsManagerMgRo;
import com.corner.kefu.service.BaseService;
/**
 * 
 * @ClassName: ScmsOrderInfoMgServiceImpl 
 * @Description: 经销商信息处理
 * @author 孟星魂	mengxinghun@izjjf.cn
 * @date 2015年12月28日 上午11:45:29 
 *
 */
@Service
public interface ScmsManagerMgService extends BaseService{
	/**
	 * 
	* @Title: getAllScmsManager 
	* @Description: 获取所有信息
	* @param @return    设定文件 
	* @return List<ScmsManager>    返回类型 
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @throws
	 */
	List<ScmsManager> getAllScmsManager(ScmsManagerMgRo command);
	
	/**
	 * 
	 * @Title: getScmsManagerPageList 
	 * @Description: 经销商列表查询 
	 * @param @return    设定文件
	 * @return Pager<ScmsOrderInfo>    返回类型
	 * @author 孟星魂	mengxinghun@izjjf.cn
	 * @throws
	 */
	Pager<ScmsManager> getScmsManagerPageList(ScmsManagerMgRo command);
	
	/**
	 * 
	* @Title: addScmsManager 
	* @Description: 添加经销商 
	* @param @param command
	* @param @return    设定文件
	* @return Pager<ScmsManager>    返回类型
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @throws
	 */
	ModelMsg addScmsManager(ScmsManagerMgRo command);
	
	/**
	 * 
	* @Title: updateScmsManager 
	* @Description: 修改经销商信息 
	* @param @param command
	* @param @return    设定文件
	* @return ModelMsg    返回类型
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @throws
	 */
	ModelMsg updateScmsManager(ScmsManagerMgRo command);
	
	/**
	 * 
	* @Title: findById 
	* @Description: 查询单条详情记录
	* @param @param command
	* @param @return    设定文件
	* @return ScmsManagerMgRo    返回类型
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @throws
	 */
	ScmsManagerMgRo findById(String id);
	
	/**
	 * 
	* @Title: checkManagerCode 
	* @Description: 校验经销商编号
	* @param @param command
	* @param @return    设定文件
	* @return ModelMsg    返回类型
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @throws
	 */
	ModelMsg checkManagerCode(ScmsManagerMgRo command);
	
	/**
	 * 
	* @Title: deleteManager 
	* @Description:  删除经销商
	* @param @param command
	* @param @return    设定文件
	* @return ModelMsg    返回类型
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @throws
	 */
	ModelMsg deleteManager(String id);
	
	
	/**
	 * 
	* @Title: updateSupplier 
	* @Description: 为区域添加删除批发商 
	* @return ModelMsg    返回类型
	* @author 孟星魂	mengxinghun@izjjf.cn
	*/
	ModelMsg updateSupplier(Supplier command);
	
	/**
	 * 
	* @Title: getSupplierPageList 
	* @Description: 查询批发商信息表
	* @return ModelMsg    返回类型
	* @author 孟星魂	mengxinghun@izjjf.cn
	*/
	Pager<Supplier> getSupplierPageList(ScmsManagerMgRo command);
}

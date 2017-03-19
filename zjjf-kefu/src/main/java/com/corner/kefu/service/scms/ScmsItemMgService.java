package com.corner.kefu.service.scms;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.corner.core.beans.ScmsItem;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.kefu.beans.ro.scms.ScmsItemMgRo;
import com.corner.kefu.service.BaseService;
/**
 * 
 * @ClassName: ScmsOrderInfoMgServiceImpl 
 * @Description: 商品信息处理
 * @author 孟星魂	mengxinghun@izjjf.cn
 * @date 2015年12月28日 上午11:45:29 
 *
 */
@Service
public interface ScmsItemMgService extends BaseService{
	/**
	 * 
	 * @Title: getScmsItemPageList 
	 * @Description: 商品列表查询 
	 * @param @return    设定文件
	 * @return Pager<ScmsOrderInfo>    返回类型
	 * @author 孟星魂	mengxinghun@izjjf.cn
	 * @throws
	 */
	Pager<ScmsItemMgRo> getScmsItemPageList(ScmsItemMgRo command);
	
	/**
	 * 
	* @Title: addScmsItem 
	* @Description: 添加商品 
	* @param @param command
	* @param @return    设定文件
	* @return Pager<ScmsItem>    返回类型
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @throws
	 */
	ModelMsg addScmsItem(ScmsItemMgRo command);
	
	/**
	 * 
	* @Title: updateScmsItem 
	* @Description: 修改商品信息多条
	* @param @param command
	* @param @return    设定文件
	* @return ModelMsg    返回类型
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @throws
	 */
	ModelMsg updateScmsItem(ScmsItemMgRo command);
	
	/**
	 * 
	* @Title: updateScmsItem 
	* @Description: 修改商品信息单条
	* @param @param command
	* @param @return    设定文件
	* @return ModelMsg    返回类型
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @throws
	 */
	ModelMsg updateScmsItemById(ScmsItemMgRo command);
	
	/**
	 * 
	* @Title: findById 
	* @Description: 查询多条记录通过商品编号
	* @param @param command
	* @param @return    设定文件
	* @return ScmsItemMgRo    返回类型
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @throws
	 */
	List<ScmsItemMgRo> findByItemIdAndMonthAndYear(ScmsItemMgRo command);
	/**
	 * 
	* @Title: findById 
	* @Description: 查询单条记录通过ID
	* @param @param command
	* @param @return    设定文件
	* @return ScmsItemMgRo    返回类型
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @throws
	 */
	ScmsItemMgRo findById(ScmsItemMgRo command);
	
	/**
	 * 
	* @Title: findByMdseIdOrName 
	* @Description: 查询商品基础表数据，通过条形码 或者name 
	* @param @param command
	* @param @return    设定文件
	* @return ScmsItemMgRo    返回类型
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @throws
	 */
	ScmsItemMgRo findByMdseIdOrName(ScmsItemMgRo command);
	ModelMsg findByMdseId(ScmsItemMgRo command);
	
	ModelMsg getItemBaseList(ScmsItemMgRo command);
	/**
	 * 
	* @Title: findByItemId 
	* @Description: 删除商品信息多条
	* @param @param command
	* @param @return    设定文件
	* @return ModelMsg    返回类型
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @throws
	 */
	ModelMsg updateScmsItemStatusOrIsDelete(ScmsItemMgRo command);
	
	/**
	 * 
	* @Title: deleteByIteId 
	* @Description: 删除商品信息单条
	* @param @param command
	* @param @return    设定文件
	* @return ModelMsg    返回类型
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @throws
	 */
	ModelMsg deleteById(ScmsItemMgRo command);
	
	ModelMsg findItemByName(Map<String, Object> map);
	
	ScmsItem selectByPrimaryKey(String id);
}

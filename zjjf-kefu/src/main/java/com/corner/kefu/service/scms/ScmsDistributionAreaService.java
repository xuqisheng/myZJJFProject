package com.corner.kefu.service.scms;

import java.util.List;

import org.springframework.stereotype.Service;

import com.corner.core.beans.ScmsGroup;
import com.corner.core.beans.ScmsItem;
import com.corner.core.beans.Supplier;
import com.corner.core.beans.vo.Pager;
import com.corner.kefu.beans.ro.scms.ScmsDistributionAreaRo;
import com.corner.kefu.beans.vo.ScmsGroupVo;
import com.corner.kefu.service.BaseService;
@Service
public interface ScmsDistributionAreaService extends BaseService{

	List<ScmsGroup> findAllArea();
	
	Pager<ScmsGroupVo> findAllScmsDistributionArea(ScmsDistributionAreaRo condition);
	int addScmsGroup(ScmsGroup scmsGroup);
	int deleteArea(Integer id);
	ScmsGroup findById(String id);
	int update(ScmsGroup group);
	
	
	/**
	 * 
	* @Title: findScmsGroupByUpId 
	* @Description: 根据UpId 获取子分类信息
	* @param @param upId
	* @param @return    设定文件 
	* @return List<ScmsGroup>    返回类型 
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @throws
	 */
	List<ScmsGroup> findScmsGroupByUpId(String upId);
	/**
	 * 
	* @Title: findScmsGroupBy0 
	* @Description: 查询分类信息	无区域信息的不显示
	* @param @return    设定文件 
	* @return List<ScmsGroup>    返回类型 
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @throws
	 */
	List<ScmsGroup> findScmsGroupBy0();
	
	//添加经销商时根据 分销区域的名字 模糊查询所有的  分校区域接口
	List<ScmsGroup> getScmsGroupname(String name);

	int deletecategory(Integer id);

	int updateCategory(ScmsGroup group);

	List<ScmsItem> findItemByAreaid(Integer id);

	List<Supplier> findSupplierByAreaId(Integer id);

	int findOrders(String warehouseId);
	
}

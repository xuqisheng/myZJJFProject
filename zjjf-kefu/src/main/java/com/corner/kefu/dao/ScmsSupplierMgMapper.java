package com.corner.kefu.dao;

import java.util.List;

import com.corner.core.beans.Supplier;
import com.corner.kefu.beans.ro.scms.ScmsManagerMgRo;

/** 
 * @ClassName: ScmsSupplierMgMapper 
 * @Description:
 * @author 杨开泰  yangkaitai@izjjf.cn
 * @date 2015年12月11日 下午4:40:20 
 *  
 */

public interface ScmsSupplierMgMapper {
	/**
	 * 
	* @Title: getPageList 
	* @Description: 查询集合 
	* @param command
	* @return List<ScOrderInfo>    返回类型
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @throws
	 */
    List<Supplier> getPageList(ScmsManagerMgRo command);
    
    /**
     * 
    * @Title: getPageListSize 
    * @Description: 查询总条数 
    * @param command
    * @return int    返回类型
    * @author 孟星魂	mengxinghun@izjjf.cn
    * @throws
     */
    int getPageListSize(ScmsManagerMgRo command);
}

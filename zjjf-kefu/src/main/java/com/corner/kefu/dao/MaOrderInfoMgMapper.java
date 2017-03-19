package com.corner.kefu.dao;

import java.util.List;

import com.corner.kefu.beans.ro.scms.MaOrderInfoMgRo;
import com.corner.kefu.beans.vo.MaOrderInfoVo;

public interface MaOrderInfoMgMapper { 
	/**
	 * 
	* @Title: getPageList 
	* @Description: 查询集合 
	* @param command
	* @return List<ScOrderInfo>    返回类型
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @throws
	 */
    List<MaOrderInfoVo> getPageList(MaOrderInfoMgRo command);
    
    /**
     * 
    * @Title: getPageListSize 
    * @Description: 查询总条数 
    * @param command
    * @return int    返回类型
    * @author 孟星魂	mengxinghun@izjjf.cn
    * @throws
     */
    int getPageListSize(MaOrderInfoMgRo command);
}
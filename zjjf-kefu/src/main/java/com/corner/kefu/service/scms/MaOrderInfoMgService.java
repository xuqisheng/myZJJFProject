package com.corner.kefu.service.scms;

import org.springframework.stereotype.Service;

import com.corner.core.beans.MaOrderInfo;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.kefu.beans.ro.scms.MaOrderInfoMgRo;
import com.corner.kefu.beans.vo.MaOrderInfoVo;
import com.corner.kefu.service.BaseService;

@Service
public interface MaOrderInfoMgService extends BaseService{
	
	/**
	 * 
	* @Title: getPageList 
	* @Description: 获取经销商订单列表
	* @param @return    设定文件 
	* @return Pager<MaOrderInfo>    返回类型 
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @throws
	 */
	Pager<MaOrderInfoVo> getPageList(MaOrderInfoMgRo command);
	
	/**
	 * 
	* @Title: selectByPrimaryKey 
	* @Description: 通过ID获取经销商订单
	* @param @param id
	* @param @return    设定文件 
	* @return MaOrderInfo    返回类型 
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @throws
	 */
	MaOrderInfo selectByPrimaryKey(String id);
	
	/**
	 * 
	* @Title: updateByPrimaryKeySelective 
	* @Description: 修改订单信息
	* @param @param info
	* @param @return    设定文件 
	* @return ModelMsg    返回类型 
	* @author 孟星魂	mengxinghun@izjjf.cn
	* @throws
	 */
	ModelMsg updateByPrimaryKeySelective(MaOrderInfo info);
}

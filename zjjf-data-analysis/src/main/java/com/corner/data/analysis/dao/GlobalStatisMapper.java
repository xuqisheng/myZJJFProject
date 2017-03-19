package com.corner.data.analysis.dao;

import java.util.List;

import com.corner.data.analysis.beans.vo.GlobalStatisVo;
/**
 * ClassName: GlobalStatisMapper
 * 
 * @Description: 全局统计 模型层接口
 * @author 元宝
 * @date 2016年01月8日
 */
public interface GlobalStatisMapper {

	/**
	 * @Description: 跳转到全局统计页面
	 * @param statisVo
	 * @return 
	 * @author 元宝	yuanbao@izjjf.cn
	 */
	public List<GlobalStatisVo> findGlobalStatisList(GlobalStatisVo statisVo);
	
	/**
	 * @Title: getPageListSize 
	 * @Description: 跳转到全局统计页面
	 * @param statisVo
	 * @return int   返回类型
	 * @author 元宝	yuanbao@izjjf.cn
     */
	public int getGlobalStatisListSize(GlobalStatisVo statisVo);
	
	/**
	 * @Title: getFullYearStatisInfo 
	 * @Description: 跳转到全局统计页面
	 * @param statisVo
	 * @return GlobalStatisVo   返回类型
	 * @author 元宝	yuanbao@izjjf.cn
     */
	public GlobalStatisVo getFullYearStatisInfo(GlobalStatisVo statisVo);
	
	
	/**
	 * @Description: 根据区域统计订单信息
	 * @param statisVo
	 * @return 
	 * @author 元宝	yuanbao@izjjf.cn
	 */
	public List<GlobalStatisVo> getOrderStatisList(GlobalStatisVo statisVo);
	
	/**
	 * @Title: getPageListSize 
	 * @Description: 跳转到全局统计页面
	 * @param statisVo
	 * @return int   返回类型
	 * @author 元宝	yuanbao@izjjf.cn
     */
	public int getOrderStatisSize(GlobalStatisVo statisVo);
	
	/**
	 * @Title: findAreaGpzdList 
	 * @Description: 查询高频商铺
	 * @param statisVo
	 * @return int   返回类型
	 * @author 元宝	yuanbao@izjjf.cn
     */
	public List<GlobalStatisVo> findAreaGpzdList(GlobalStatisVo statisVo);
	
	/**
	 * @Title: findAreaGpzdList 
	 * @Description: 查询销售量
	 * @param statisVo
	 * @return int   返回类型
	 * @author 元宝	yuanbao@izjjf.cn
     */
	public List<GlobalStatisVo> findSalesVolumelList(GlobalStatisVo statisVo);
	
	/**
	 * @Title: findAreaGpzdList 
	 * @Description: 查询新增门店
	 * @param statisVo
	 * @return int   返回类型
	 * @author 元宝	yuanbao@izjjf.cn
     */
	public List<GlobalStatisVo> findNewStorelList(GlobalStatisVo statisVo);
}

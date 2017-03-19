package com.corner.data.analysis.service.bus;

import java.util.List;

import com.corner.core.beans.vo.Pager;
import com.corner.data.analysis.beans.vo.GlobalStatisVo;

/**
 * ClassName: GlobalStatisService
 * 
 * @Description: 全局统计业务层接口
 * @author 元宝
 * @date 2016年01月8日
 */
public interface GlobalStatisService {
	/**
	 * @Description: 全局统计查询列表
	 * @param statisVo
	 * @return 
	 * @author 元宝	yuanbao@izjjf.cn
	 */
	public List<GlobalStatisVo> findGlobalStatisList(GlobalStatisVo statisVo) throws Exception;
	
	/**
	 * @Description: 跳转到全局统计页面
	 * @param statisVo
	 * @return 
	 * @author 元宝	yuanbao@izjjf.cn
	 */
	public Pager<GlobalStatisVo> findPagerList(GlobalStatisVo statisVo) throws Exception;
	
	/**
	 * @Title: getFullYearStatisInfo 
	 * @Description: 跳转到全局统计页面
	 * @param statisVo
	 * @return GlobalStatisVo   返回类型
	 * @author 元宝	yuanbao@izjjf.cn
     */
	public GlobalStatisVo getFullYearStatisInfo(GlobalStatisVo statisVo) throws Exception;
}

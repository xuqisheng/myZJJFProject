package com.corner.data.analysis.service.bus.impl;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.beans.vo.Pager;
import com.corner.data.analysis.beans.vo.GlobalStatisVo;
import com.corner.data.analysis.dao.GlobalStatisMapper;
import com.corner.data.analysis.service.bus.GlobalStatisService;

/**
 * ClassName: GlobalStatisServiceImpl
 * 
 * @Description: 全局统计业务逻辑层
 * @author 元宝
 * @date 2016年01月8日
 */
@Service
public class GlobalStatisServiceImpl implements GlobalStatisService {
	
	@Autowired
	private GlobalStatisMapper statisMapper;

	/**
	 * @Description: 跳转到全局统计页面
	 * @param statisVo
	 * @return 
	 * @author 元宝	yuanbao@izjjf.cn
	 */
	@Override
	public List<GlobalStatisVo> findGlobalStatisList(GlobalStatisVo statisVo) throws Exception {
		//return statisMapper.findGlobalStatisList(statisVo);

		List<GlobalStatisVo> list = statisMapper.getOrderStatisList(statisVo);
		List<GlobalStatisVo> gpzdList = statisMapper.findAreaGpzdList(statisVo);//高频列表
		List<GlobalStatisVo> xlList = statisMapper.findSalesVolumelList(statisVo);//下单列表
		List<GlobalStatisVo> storeList = statisMapper.findNewStorelList(statisVo);//新增门店列表
		
		for (GlobalStatisVo orderVo : list) {
			String areaId = orderVo.getAreaId();
			if(null == areaId){
				continue;
			}
			//高频商铺信息回填（根据订单统计的区域ID回填对应数据）
			for (GlobalStatisVo gpVo : gpzdList) {
				String gpAreaId = gpVo.getAreaId();
				if(areaId.equals(gpAreaId)){
					orderVo.setGpzd(gpVo.getGpzd());
				}
			}
			//下单信息回填（根据订单统计的区域ID回填对应数据）
			for (GlobalStatisVo xdVo : xlList) {
				String xdAreaId = xdVo.getAreaId();
				if(areaId.equals(xdAreaId)){
					orderVo.setXdsku(xdVo.getXdsku());
					orderVo.setXl(xdVo.getXl());
					orderVo.setYlspxse(xdVo.getYlspxse());
					orderVo.setYlsplr(xdVo.getYlsplr());
					orderVo.setFyje(xdVo.getFyje());
				}
			}
			//新增店铺信息回填（根据订单统计的区域ID回填对应数据）
			for (GlobalStatisVo storeVo : storeList) {
				String storeAreaId = storeVo.getAreaId();
				if(areaId.equals(storeAreaId)){
					orderVo.setXzzd(storeVo.getXzzd());
				}
			}
			
			//list.fyje/list.xsje*100
			Double fyje = orderVo.getFyje();
			Double xsje = orderVo.getXsje();
			if(null != fyje && null != xsje){
				double fyl = fyje/xsje*100;
				String fylStr = new java.text.DecimalFormat("#0.00").format(fyl);
				orderVo.setFyl(fylStr+"%");
			}
		}
		return list;
	}
	
	/**
	 * @Description: 跳转到全局统计页面
	 * @param statisVo
	 * @return 
	 * @author 元宝	yuanbao@izjjf.cn
	 */
	@Override
	public Pager<GlobalStatisVo> findPagerList(GlobalStatisVo statisVo) throws Exception {
		/*List<GlobalStatisVo> list = statisMapper.findGlobalStatisList(statisVo);
		int size = statisMapper.getGlobalStatisListSize(statisVo);
		return new Pager<GlobalStatisVo>(size,list);*/
		
		List<GlobalStatisVo> list = statisMapper.getOrderStatisList(statisVo);
		int size = statisMapper.getOrderStatisSize(statisVo);
		
		List<GlobalStatisVo> gpzdList = statisMapper.findAreaGpzdList(statisVo);//高频列表
		List<GlobalStatisVo> xlList = statisMapper.findSalesVolumelList(statisVo);//下单列表
		List<GlobalStatisVo> storeList = statisMapper.findNewStorelList(statisVo);//新增门店列表
		
		for (GlobalStatisVo orderVo : list) {
			String areaId = orderVo.getAreaId();
			//高频商铺信息回填（根据订单统计的区域ID回填对应数据）
			for (GlobalStatisVo gpVo : gpzdList) {
				String gpAreaId = gpVo.getAreaId();
				if(areaId.equals(gpAreaId)){
					orderVo.setGpzd(gpVo.getGpzd());
				}
			}
			//下单信息回填（根据订单统计的区域ID回填对应数据）
			for (GlobalStatisVo xdVo : xlList) {
				String xdAreaId = xdVo.getAreaId();
				if(areaId.equals(xdAreaId)){
					orderVo.setXdsku(xdVo.getXdsku());
					orderVo.setXl(xdVo.getXl());
					orderVo.setYlspxse(xdVo.getYlspxse());
					orderVo.setYlsplr(xdVo.getYlsplr());
					orderVo.setFyje(xdVo.getFyje());
				}
			}
			//新增店铺信息回填（根据订单统计的区域ID回填对应数据）
			for (GlobalStatisVo storeVo : storeList) {
				String storeAreaId = storeVo.getAreaId();
				if(areaId.equals(storeAreaId)){
					orderVo.setXzzd(storeVo.getXzzd());
				}
			}
		}
		
		return new Pager<GlobalStatisVo>(size,list);
	}

	/**
	 * @Title: getFullYearStatisInfo 
	 * @Description: 跳转到全局统计页面
	 * @param statisVo
	 * @return GlobalStatisVo   返回类型
	 * @author 元宝	yuanbao@izjjf.cn
     */
	@Override
	public GlobalStatisVo getFullYearStatisInfo(GlobalStatisVo statisVo) throws Exception {
		return statisMapper.getFullYearStatisInfo(statisVo);
	}

}

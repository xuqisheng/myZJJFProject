package com.corner.scms.service.sc.impl;

import com.corner.core.beans.*;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.core.dao.*;
import com.corner.core.utils.CreateOrderIdUtil;
import com.corner.core.utils.ResponseUtils;
import com.corner.scms.beans.ro.sc.MaOrderInfoMgRo;
import com.corner.scms.beans.ro.sc.ScOrderInfoMgRo;
import com.corner.scms.beans.vo.ScmsShoppingCartVo;
import com.corner.scms.beans.vo.sc.MaOrderInfoMgVo;
import com.corner.scms.beans.vo.sc.ScOrderDetailVo;
import com.corner.scms.beans.vo.sc.ScOrderInfoMgVo;
import com.corner.scms.beans.vo.sc.ScOrderInfoVo;
import com.corner.scms.dao.*;
import com.corner.scms.service.sc.ScOrderInfoMgService;
import com.corner.scms.service.sp.ScmsPlantItemMgService;
import com.corner.scms.utils.BeanUtil;
import com.corner.scms.utils.CompoundOrdersRunnable;
import com.corner.scms.utils.CompoundOrdersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * 
 * @ClassName: ScOrderInfoMgServiceImpl 
 * @Description: 订单信息处理
 * @author 孟星魂	mengxinghun@izjjf.cn
 * @date 2015年12月4日 下午6:08:25 
 *
 */
@Service
public class ScOrderInfoMgServiceImpl implements ScOrderInfoMgService {

	private static Logger logger = LoggerFactory.getLogger(ScOrderInfoMgServiceImpl.class);

	@Autowired
	ScOrderInfoMgMapper scOrderInfoMgMapper;

	@Autowired
	private ScmsPlantItemMgService plantItemMgService;
	@Autowired
	ScOrderInfoMapper scOrderInfoMapper;
	@Autowired
	ScmsItemMgMapper scmsItemMgMapper;
	@Autowired
	ScmsItemMapper scmsItemMapper;
	@Autowired
	ScmsGroupMapMgMapper scmsGroupMapMgMapper;
	@Autowired
	ScmsManagerMgMapper scmsManagerMgMapper;
	@Autowired
	SupplierMapper supplierMapper;
	@Autowired
	ScmsShoppingCartMgMapper scmsShoppingCartMgMapper;
	@Autowired
	ScmsGroupMapper scmsGroupMapper;
	@Autowired
	ScOrderDetailMgMapper scOrderDetailMgMapper;
	@Autowired
	ScOrderDetailMapper scOrderDetailMapper;
	@Autowired
	ScmsSupplierMgMapper scmsSupplierMgMapper;
	@Autowired
	SpWalletLogMapper spWalletLogMapper;

	@Autowired
	ThreadPoolTaskExecutor taskExecutor;
	@Autowired
	CompoundOrdersService compoundOrdersService;
	@Autowired
	MaWalletLogMapper maWalletLogMapper;

	@Autowired
	PlantWalletMapper plantWalletMapper;

	@Autowired
	PlantWalletMgMapper plantWalletMgMapper;

	@Autowired
	PlantWalletLogMapper plantWalletLogMapper;
	
	@Autowired
	MaOrderInfoMapper maOrderInfoMapper;

	@Override
	public ModelMsg deleteObjects(String tableName, String[] array) {
		return null;
	}

	/**
	 * 获得经销商所有的订单
	* @Title
	* @Description: TODO 
	* @param @param command
	* @param @return
	* @2016年1月25日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	@Override
	public Pager<MaOrderInfoMgVo> getScOrderInfoList(MaOrderInfoMgRo maOrderInfoMgRo) {
		List<MaOrderInfoMgVo> maOrderInfoMgVoList = scOrderInfoMgMapper.getPageList(maOrderInfoMgRo);
		if (maOrderInfoMgVoList != null && maOrderInfoMgVoList.size() > 0) {
			Map<String, Object> map = null;
			for (MaOrderInfoMgVo maOrderInfoMgVo : maOrderInfoMgVoList) {
				map = new HashMap<String, Object>();
				map.put("maOrderInfoId", maOrderInfoMgVo.getId());
				List<ScOrderDetailVo> scOrderDetailVoList = scOrderDetailMgMapper.getScOrderDetailById(map);
				if (scOrderDetailVoList != null && scOrderDetailVoList.size() > 0) {
					maOrderInfoMgVo.setScOrderDetailVoList(scOrderDetailVoList);
					Integer countNumber = 0;
					for (ScOrderDetailVo scOrderDetailVo : scOrderDetailVoList) {
						countNumber += scOrderDetailVo.getCountNum();
					}
					maOrderInfoMgVo.setCountNumber(countNumber);
				}
			}
		}
		int num = scOrderInfoMgMapper.getPageListSize(maOrderInfoMgRo);
		return new Pager<MaOrderInfoMgVo>(num, maOrderInfoMgVoList);
	}

	/**
	 * 根据id获取经销商订单详情
	* @Title
	* @Description: TODO 
	* @param @param map
	* @param @return
	* @2016年1月25日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	@Override
	public MaOrderInfoMgVo getScOrderInfoById(String id) {
		MaOrderInfoMgVo maOrderInfoMgVo = BeanUtil.toObject(MaOrderInfoMgVo.class, this.selectMaOrderInfoById(id));
		if (maOrderInfoMgVo != null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("maOrderInfoId", maOrderInfoMgVo.getId());
			List<ScOrderDetailVo> scOrderDetailVoList = scOrderDetailMgMapper.getScOrderDetailById(map);
			if (scOrderDetailVoList != null && scOrderDetailVoList.size() > 0) {
				maOrderInfoMgVo.setScOrderDetailVoList(scOrderDetailVoList);
				Integer countNumber = 0;
				for (ScOrderDetailVo scOrderDetailVo : scOrderDetailVoList) {
					countNumber += scOrderDetailVo.getCountNum();
				}
				maOrderInfoMgVo.setCountNumber(countNumber);
			}
		}
		return maOrderInfoMgVo;
	}

	/**查询仓库配送单信息**/
	@Override
	public Pager<ScOrderInfoMgVo> selectWarehouseOrderPageList(ScOrderInfoMgRo scOrderInfoMgRo) {
		int size = scOrderInfoMgMapper.selectWarehouseOrderPageListCount(scOrderInfoMgRo);
		List<ScOrderInfoMgVo> orderInfoMgVos = scOrderInfoMgMapper.selectWarehouseOrderPageList(scOrderInfoMgRo);
		for (ScOrderInfoMgVo scOrderInfoMgVo : orderInfoMgVos) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("orderId", scOrderInfoMgVo.getOrderId());
			List<ScOrderDetailVo> detailVos = scOrderDetailMgMapper.findDetailByOrderIdOrOrderId2(map);
			for (ScOrderDetailVo scOrderDetailVo : detailVos) {
				if(scOrderDetailVo.getFreight().compareTo(new BigDecimal(0)) != 0)
					scOrderDetailVo.setZjjfPrice(scOrderDetailVo.getZjjfPrice().add(scOrderDetailVo.getFreight().divide(new BigDecimal(scOrderDetailVo.getQuantity()))));
			}
			scOrderInfoMgVo.setScOrderDetailVos(detailVos);
		}
		return new Pager<ScOrderInfoMgVo>(size, orderInfoMgVos);
	}

	/**
	 * 获取仓库入库订单列表
	* @Title
	* @Description: TODO 
	* @param @param scOrderInfoMgRo
	* @param @return
	* @2016年1月27日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	@Override
	public Pager<MaOrderInfoMgVo> getWarehouseOrderList(MaOrderInfoMgRo maOrderInfoMgRo) {
		List<MaOrderInfoMgVo> maOrderInfoMgVoList = scOrderInfoMgMapper.getWarehouseOrderList(maOrderInfoMgRo);
		if (maOrderInfoMgVoList != null && maOrderInfoMgVoList.size() > 0) {
			Map<String, Object> map = null;
			for (MaOrderInfoMgVo maOrderInfoMgVo : maOrderInfoMgVoList) {
				map = new HashMap<String, Object>();
				map.put("maOrderInfoId", maOrderInfoMgVo.getId());
				List<ScOrderDetailVo> scOrderDetailVoList = scOrderDetailMgMapper.getScOrderDetailById(map);
				if (scOrderDetailVoList != null && scOrderDetailVoList.size() > 0) {
					maOrderInfoMgVo.setScOrderDetailVoList(scOrderDetailVoList);
					Integer countNumber = 0;
					for (ScOrderDetailVo scOrderDetailVo : scOrderDetailVoList) {
						countNumber += scOrderDetailVo.getCountNum();
					}
					maOrderInfoMgVo.setCountNumber(countNumber);
				}
			}
		}
		int num = scOrderInfoMgMapper.getWarehouseOrderCount(maOrderInfoMgRo);
		return new Pager<MaOrderInfoMgVo>(num, maOrderInfoMgVoList);
	}

	/**************************************仓库提单************************************************/
	/**
	 * 	自提或配送状态修改
	* @Title
	* @Description: TODO 
	* @param @param map
	* @param @return
	* @2016年3月2日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	public ModelMsg updateGoodsStatus(String orderId, String[] ids, String[] names) {

		// 查出商品是否入库
		Map<String, Object> map = null;
		int num = 0;
		String msg = "";
		if (ids == null || ids.length == 0) {
			return new ModelMsg(false, "请选择商品！");
		}
		for (int i = 0; i < ids.length; i++) {
			map = new HashMap<String, Object>();
			map.put("orderId", orderId);
			map.put("id", ids[i]);
			Byte status1 = scOrderDetailMgMapper.getGoodsStatusById(map);
			if (status1 < 1) {
				num = 1;
				msg = "商品：" + names[i] + ",未入库！";
				break;
			} else if (status1 > 1) {
				num = 2;
				msg = "商品：" + names[i] + ",已出库！";
				break;
			}
			Byte status = scOrderInfoMgMapper.getStatusById(map);
			if (status < 2) {
				num = 1;
				msg = "商品：" + names[i] + ",未入库！";
				break;
			} else if (status > 2) {
				num = 2;
				msg = "商品：" + names[i] + ",已出库！";
				break;
			}
		}
		if (num == 1) {
			return new ModelMsg(false, msg);
		} else if (num == 2) {
			return new ModelMsg(false, msg);
		}
		// 修改商品状态为配送或提货
		for (String id : ids) {
			map = new HashMap<String, Object>();
			map.put("id", id);
			map.put("orderId", orderId);
			map.put("status", Byte.parseByte("2"));
			scOrderDetailMgMapper.updateGoodsStatus(map);
			// 查出采购单下的商品状态是否都为已派送
			int status = scOrderDetailMgMapper.getAllGoodsStatus(map);
			if (status == 0) {
				Map<String, Object> map1 = new HashMap<String, Object>();
				map1.put("fistTime", new Date());
				map1.put("warehouseStatus", Byte.parseByte("3"));
				map1.put("orderId", orderId);
				map1.put("id", id);
				scOrderInfoMgMapper.updatePurchaseOrderStatus(map1);
			}
			// 查出批发商下的商品状态是否都为已派送
			int status1 = scOrderDetailMgMapper.getSupplierAllGoodsStatus(map);
			if (status1 == 0) {
				Map<String, Object> map2 = new HashMap<String, Object>();
				map2.put("fistTime", new Date());
				map2.put("status", Byte.parseByte("3"));
				map2.put("orderId", orderId);
				scOrderInfoMgMapper.updateSupplierGoodsStatus(map2);
			}
		}
		return new ModelMsg(true, "操作成功！");
	}

	/**
	 * 仓库打印订单
	* @Title
	* @Description: TODO 
	* @param @param map
	* @param @return
	* @2016年3月3日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	public ScOrderInfoMgVo printWarehouseOrder(String orderId, String[] ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderId", orderId);
		ScOrderInfoMgVo scOrderInfoMgVo = scOrderInfoMgMapper.printWarehouseOrder(map);
		if (scOrderInfoMgVo != null) {
			map = new HashMap<String, Object>();
			map.put("orderId", orderId);
			map.put("ids", ids);
			List<ScOrderDetailVo> scOrderDetailVoList = scOrderInfoMgMapper.getPrintGoodsById(map);
			Integer countNumber = 0;
			BigDecimal printPrice = new BigDecimal(0);
			for (ScOrderDetailVo scOrderDetailVo : scOrderDetailVoList) {
				countNumber += scOrderDetailVo.getQuantity();
				if(scOrderDetailVo.getFreight().compareTo(new BigDecimal(0)) != 0)
					scOrderDetailVo.setZjjfPrice(scOrderDetailVo.getZjjfPrice().add(scOrderDetailVo.getFreight().divide(new BigDecimal(scOrderDetailVo.getQuantity()))));
				printPrice = printPrice.add(scOrderDetailVo.getTotalPrice().add(scOrderDetailVo.getFreight()));
			}
			scOrderInfoMgVo.setPrintPrice(printPrice);
			scOrderInfoMgVo.setCountNumber(countNumber);
			scOrderInfoMgVo.setScOrderDetailVos(scOrderDetailVoList);
		}
		return scOrderInfoMgVo;
	}

	/**************************************************************************************/

	// /**
	// * 仓库配送单列表
	// * @Title
	// * @Description: TODO
	// * @param @param scOrderInfoMgRo
	// * @param @return
	// * @2016年3月1日
	// * @author 龙五 longwu@izjjf.cn
	// * @return
	// * @throws
	// */
	// public Pager<ScOrderInfoMgVo> getWarehouseDeliveryList(ScOrderInfoMgRo
	// scOrderInfoMgRo){
	// int num =
	// scOrderInfoMgMapper.getWarehouseDeliveryListCount(scOrderInfoMgRo);
	// List<ScOrderInfoMgVo> ScOrderInfoMgVoList =
	// scOrderInfoMgMapper.getWarehouseDeliveryList(scOrderInfoMgRo);
	// if (ScOrderInfoMgVoList != null && ScOrderInfoMgVoList.size()>0) {
	// for (ScOrderInfoMgVo scOrderInfoMgVo : ScOrderInfoMgVoList) {
	// scOrderInfoMgVo.setScOrderDetailVos(scOrderInfoMgMapper.getWarehouseDeliveryGoods(scOrderInfoMgVo.getOrderId()));
	// }
	// return new Pager<ScOrderInfoMgVo>(num, ScOrderInfoMgVoList);
	// }else{
	// return null;
	// }
	// }
	//

	/**
	 * 
	* Title: confiremdOrderInfo 
	* Description:提交批发商订单 
	* @param supplier
	* @throws Exception 
	* @see com.corner.scms.service.sc.ScOrderInfoMgService#confiremdOrderInfo(com.corner.core.beans.Supplier)
	 */
	@Override
	public void confiremdOrderInfo(Supplier supplier) throws Exception {
		// List<ScmsShoppingCart>
		// scmsShoppingCartList=scmsShoppingCartMgMapper.getCheckedCartListBySupplierId(supplier);
		List<ScmsShoppingCartVo> scmsShoppingCartVoList = scmsShoppingCartMgMapper
				.getCheckedScmsShoppingCartVoListBySupplierId(supplier);
		BigDecimal totalPrice = new BigDecimal(0);// 订单总额
		/*
		 * for (ScmsShoppingCart scmsShoppingCart : scmsShoppingCartList) {
		 * totalPrice.add(scmsShoppingCart.getTotalPrice()); }
		 */
		for (ScmsShoppingCartVo scmsShoppingCartVo : scmsShoppingCartVoList) {
			totalPrice.add(scmsShoppingCartVo.getTotalPrice());
		}
		// 查询经销区域
		ScmsGroup scmsGroup = scmsGroupMapper.selectByPrimaryKey(supplier.getBsCircleId());
		/***************ScOrderInfo插入数据 begin**********************/
		String orderId = CreateOrderIdUtil.dateToString();// 订单号
		Date addTime = new Date();// 订单生成时间
		ScOrderInfo scOrderInfo = new ScOrderInfo();
		scOrderInfo.setOrderId(orderId);
		scOrderInfo.setAddTime(addTime);
		// TODO 计算商品总额
		scOrderInfo.setOrderPrice(totalPrice);
		scOrderInfo.setSupplierId(supplier.getId());
		scOrderInfo.setSupplierName(supplier.getSupplierName());
		scOrderInfo.setGroupId(supplier.getBsCircleId());
		scOrderInfo.setGroupName(scmsGroup.getName());
		// TODO 设置收货人
		scOrderInfo.setMobile(supplier.getMobile());
		scOrderInfo.setAddress(supplier.getSupplierAddress());
		scOrderInfo.setStatus(new Byte("1"));
		// TODO 计算总毛利,总金额,运费
		scOrderInfoMapper.insertSelective(scOrderInfo);
		/***************ScOrderInfo插入数据 end**********************/

		/***************ScOrderDetail插入数据 begin**********************/
		List<ScOrderDetailVo> scOrderDetailList = new ArrayList<ScOrderDetailVo>();
		/*
		 * for (ScmsShoppingCart scmsShoppingCart : scmsShoppingCartList) {
		 * ScOrderDetail scOrderDetail = new ScOrderDetail();
		 * scOrderDetail.setAddTime(addTime); }
		 */
		for (ScmsShoppingCartVo scmsShoppingCartVo : scmsShoppingCartVoList) {
			ScOrderDetail scOrderDetail = new ScOrderDetail();
			scOrderDetail.setAddTime(addTime);
			scOrderDetail.setOrderId(scOrderInfo.getId());
			scOrderDetail.setItemId(scmsShoppingCartVo.getScmsItemId());
			scOrderDetail.setQuantity(scmsShoppingCartVo.getNum());
			scOrderDetail.setPrice(scmsShoppingCartVo.getPrice());
			scOrderDetail.setTotalPrice(scmsShoppingCartVo.getTotalPrice());
			scOrderDetail.setScmsGroupId(supplier.getBsCircleId());
			scOrderDetail.setManagerId(scmsShoppingCartVo.getManagerId());
			scOrderDetail.setStatus(new Byte("0"));
		}
		// scOrderDetailMgMapper.batchSave(scOrderDetailList);
		/***************ScOrderDetail插入数据 end**********************/

	}

	/**
	 * 
	* Title: getScOrderInfo 
	* Description:更具批发商Id和ScOrderInfo Id查询订单 
	* @param map
	* @return
	* @throws Exception 
	* @see com.corner.scms.service.sc.ScOrderInfoMgService#getScOrderInfo(java.util.Map)
	 */
	@Override
	public ScOrderInfo getScOrderInfo(Map<String, Object> map){
		return scOrderInfoMgMapper.getScOrderInfo(map);
	}

	/**
	 * 
	* Title: updateAddScOrderInfo 
	* Description: 合单
	 */
	@Override
	public void updateAddScOrderInfo(Map<String, Object> map) throws Exception {

		taskExecutor.execute(new CompoundOrdersRunnable(map, compoundOrdersService));
	}

	/**
	 * 
	* Title: selectByPrimaryKey 
	* Description:查询订单 
	* @param orderid
	* @return 
	* @see com.corner.scms.service.sc.ScOrderInfoMgService#selectByPrimaryKey(java.lang.String)
	 */
	@Override
	public ScOrderInfo selectByPrimaryKey(String orderid) {
		return scOrderInfoMapper.selectByPrimaryKey(orderid);
	}
	@Override
	public List<ScOrderInfo> findSonInfosByPid(String pid , Integer level) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pId", pid);
		map.put("level", level);
		return scOrderInfoMgMapper.findSonInfosByPid(map);
	}

	@Override
	public List<ScOrderInfoVo> findMyOrderInfo(ScOrderInfoMgRo scOrderInfoMgRo) {
		scOrderInfoMgRo.setPageSize(5);
		List<ScOrderInfoVo> vo = this.scOrderInfoMgMapper.findMyOrderInfo(scOrderInfoMgRo);
		if (vo != null && vo.size() > 0) {
			for (ScOrderInfoVo v : vo) {
				List<ScOrderDetail> detail = this.scOrderDetailMgMapper.findDetailByOid(v.getOrderId());
				for (ScOrderDetail scOrderDetail : detail) {
					if(!new BigDecimal(0).equals(scOrderDetail.getFreight())){
						scOrderDetail.setZjjfPrice(scOrderDetail.getFreight().divide(new BigDecimal(scOrderDetail.getQuantity())).add(scOrderDetail.getZjjfPrice()));
						scOrderDetail.setTotalPrice(scOrderDetail.getTotalPrice().add(scOrderDetail.getFreight()));
					}
				}
				v.setScorders(detail);
			}
		}

		return vo;
	}

	/**
	 * 
	* Title: getAllDetail 
	* Description:查询所有订单的详情页,包括被删除的订单 
	* @param scOrderInfoMgRo
	* @return
	* @throws Exception 
	* @see com.corner.scms.service.sc.ScOrderInfoMgService#getAllDetail(com.corner.scms.beans.ro.sc.ScOrderInfoMgRo)
	 */
	@Override
	public List<ScOrderInfoVo> getAllDetail(ScOrderInfoMgRo scOrderInfoMgRo) throws Exception {
		if (scOrderInfoMgRo.getPageIndex() <= 0) {
			scOrderInfoMgRo.setPageIndex(1);
		}
		if (scOrderInfoMgRo.getPageSize() <= 5) {
			scOrderInfoMgRo.setPageSize(5);
		}
		List<ScOrderInfoVo> vo = this.scOrderInfoMgMapper.getAllOrderInfo(scOrderInfoMgRo);
		if (vo != null && vo.size() > 0) {
			for (ScOrderInfoVo v : vo) {
				List<ScOrderDetail> detail = this.scOrderDetailMgMapper.findDetailByOid(v.getOrderId());
				v.setScorders(detail);
			}
		}
		return vo;
	}

	/**
	 * @throws Exception 
	 * @throws Exception 
	 * 修改订单状态
	* @Title
	* @Description: TODO 
	* @param @param str
	* @param @param status
	* @param @return
	* @2016年2月3日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	@Override
	public void updateOrderStatus(Map<String, Object> map) throws Exception {
		scOrderInfoMgMapper.updateOrderStatus(map);

		/**用于仓库确认收货的时候给经销商打钱
		 * String scOrderInfoId = (String) map.get("id");
		//给对应经销商钱包加钱
		ScOrderInfo scOrderInfo = scOrderInfoMapper.selectByPrimaryKey(scOrderInfoId);
		BigDecimal orderPrice = scOrderInfo.getOrderPrice();//订单金额
		String managerId = scOrderInfo.getManagerId();//经销商id
		
		MaWallet maWallet = maWalletMgService.selectById(managerId);
		
		map.put("managerId", managerId);
		map.put("operateMoney", orderPrice);
		maWalletMgService.addManagerWallet(map);//批发商钱包加钱
		
		MaWalletLog maWalletLog = new MaWalletLog();
		maWalletLog.setManagerId(managerId);//经销商id
		maWalletLog.setActionType(new Byte("1"));//收入
		maWalletLog.setOptType(new Byte("1"));//订单金额
		maWalletLog.setActionTime(new Date());//操作时间
		maWalletLog.setTradeWay(new Byte("4"));//微信支付
		maWalletLog.setOrderId(scOrderInfo.getOrderId());//订单号
		maWalletLog.setBalance(maWallet.getWallet());//余额是操作前的余额;
		maWalletLog.setMoney(orderPrice);//操作金额
		maWalletLogMgService.addMaWalletLog(maWalletLog);//插入记录
		*/
	}

	@Override
	public int findMyOrderInfoSize(ScOrderInfoMgRo scOrderInfoMgRo) {
		return this.scOrderInfoMgMapper.findMyOrderInfoSize(scOrderInfoMgRo);
	}

	/**
	 * 
	* Title: getAllNoPayOrderInfoList 
	* Description:查询所有未支付的订单 
	* @return
	* @throws Exception 
	* @see com.corner.scms.service.sc.ScOrderInfoMgService#getAllNoPayOrderInfoList()
	 */
	@Override
	public List<ScOrderInfo> getAllNoPayOrderInfoList() throws Exception {
		return scOrderInfoMgMapper.getAllNoPayOrderInfoList();
	}

	@Override
	public int updateByPrimaryKeySelective(ScOrderInfo scOrderInfo) throws Exception {
		return scOrderInfoMapper.updateByPrimaryKeySelective(scOrderInfo);
	}
	@Override
	public Object deleteinfo(ScOrderInfoMgRo scOrderInfoMgRo) {
		// 根据订单的Id和批发商的Id去查询 该订单 防止 非法 操作 别人的 订单
		ScOrderInfo info = this.scOrderInfoMgMapper.findMyorderInfoByRo(scOrderInfoMgRo);
		if (info == null) {
			return ResponseUtils.sendMsg(false, "无权操作该订单!");
		}
		if (info.getStatus() < 4) {
			return ResponseUtils.sendMsg(false, "该订单状态不能做删除操作!");
		}
		this.scOrderInfoMgMapper.deleteMyOrderinfo(scOrderInfoMgRo);// 删除 总单
		this.scOrderInfoMgMapper.deletedetail(scOrderInfoMgRo);// 删除 子单
		return ResponseUtils.sendMsg(true, "操作成功!");
	}

	@Override
	public ScOrderInfo selectByForWxTradeNo(String out_trade_no) {
		return scOrderInfoMgMapper.selectByForWxTradeNo(out_trade_no);
	}

	/**
	 * 
	* Title: updateThirdPayment 
	* Description:更新订单第三方支付方式 
	* @param scOrderInfo
	* @throws Exception 
	* @see com.corner.scms.service.sc.ScOrderInfoMgService#updateThirdPayment(com.corner.core.beans.ScOrderInfo)
	 */
	@Override
	public void updateThirdPayment(ScOrderInfo scOrderInfo) throws Exception {
		scOrderInfoMgMapper.updateThirdPayment(scOrderInfo);
	}

	@Override
	public MaOrderInfo selectMaOrderInfoById(String id) {
		return maOrderInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public ModelMsg updateMaOrderInfo(MaOrderInfo maOrderInfo) throws Exception {
		int result = maOrderInfoMapper.updateByPrimaryKeySelective(maOrderInfo);
		if(result == 0)
			throw new Exception("未修改成功交易异常");
		return new ModelMsg(true, "修改成功");
	}

}

/**   
* @Title: ScmsShoppingCartServiceImpl.java 
* @Package com.corner.scms.service.sc.impl 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年1月19日 下午2:53:20 
* @version V1.0   
*/

package com.corner.scms.service.sc.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.beans.PlantWallet;
import com.corner.core.beans.PlantWalletLog;
import com.corner.core.beans.ScOrderDetail;
import com.corner.core.beans.ScOrderInfo;
import com.corner.core.beans.ScmsFreightTplMap;
import com.corner.core.beans.ScmsGroup;
import com.corner.core.beans.ScmsManager;
import com.corner.core.beans.ScmsShoppingCart;
import com.corner.core.beans.ScmsWarehouse;
import com.corner.core.beans.SpWalletLog;
import com.corner.core.beans.Supplier;
import com.corner.core.beans.SystemInfo;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.dao.ScOrderInfoMapper;
import com.corner.core.dao.ScmsGroupMapper;
import com.corner.core.dao.ScmsManagerMapper;
import com.corner.core.dao.ScmsShoppingCartMapper;
import com.corner.core.dao.SpWalletLogMapper;
import com.corner.core.dao.SupplierMapper;
import com.corner.core.utils.CreateOrderIdUtil;
import com.corner.core.utils.StringUtil;
import com.corner.scms.beans.vo.ScmsFreightTplVo;
import com.corner.scms.beans.vo.ScmsItemVo;
import com.corner.scms.beans.vo.ScmsMinimumVo;
import com.corner.scms.beans.vo.SupplierVo;
import com.corner.scms.beans.vo.sc.ScOrderDetailVo;
import com.corner.scms.config.SCMSConstants;
import com.corner.scms.dao.ScOrderDetailMgMapper;
import com.corner.scms.dao.ScOrderInfoMgMapper;
import com.corner.scms.dao.ScmsFreightTplMgMapper;
import com.corner.scms.dao.ScmsItemMgMapper;
import com.corner.scms.dao.ScmsShoppingCartMgMapper;
import com.corner.scms.dao.ScmsSupplierMgMapper;
import com.corner.scms.dao.ScmsWarehouseMgMapper;
import com.corner.scms.service.sc.ScmsShoppingCartService;
import com.corner.scms.utils.SortUtil;

/**
 * @ClassName: ScmsShoppingCartServiceImpl
 * @Description:
 * @author 杨开泰 yangkaitai@izjjf.cn
 * @date 2016年1月19日 下午2:53:20
 * 
 */
@Service
public class ScmsShoppingCartServiceImpl implements ScmsShoppingCartService {

	@Autowired
	ScmsShoppingCartMgMapper scmsShoppingCartMgMapper;

	@Autowired
	ScmsShoppingCartMapper scmsShoppingCartMapper;

	@Autowired
	ScmsFreightTplMgMapper scmsFreightTplMgMapper;

	@Autowired
	ScOrderInfoMapper scOrderInfoMapper;
	@Autowired
	ScmsGroupMapper scmsGroupMapper;

	@Autowired
	ScOrderDetailMgMapper scOrderDetailMgMapper;

	@Autowired
	ScmsSupplierMgMapper scmsSupplierMgMapper;

	@Autowired
	ScmsWarehouseMgMapper scmsWarehouseMgMapper;

	@Autowired
	ScOrderInfoMgMapper scOrderInfoMgMapper;
	
	@Autowired
	ScmsItemMgMapper scmsItemMgMapper;
	
	@Autowired
	SpWalletLogMapper spWalletLogMapper;
	@Autowired
	SupplierMapper supplierMapper;
	
	@Autowired
	ScmsManagerMapper scmsManagerMapper;
	

	/**
	 * 
	 * Title: getCartList Description:获取批发商购物车列表
	 * 
	 *            登入的批发商对象
	 * @return
	 * @throws Exception
	 */
	@Override
	public Map<String, Object> getCartList(Map<String, Object> map) throws Exception {
		map = caculatTotalProductAndTotalPriceAndLackNum(map);
		return map;
	}

	/**
	 * @throws Exception 
	 * 
	* @Title: caculatTotalProductAndTotalPriceAndLackNum 
	* @Description:计算批发商购物车中被选中的商品件数,总额以及购物车中所有商品距离起购量还差多少件
	* @param @param list
	* @param @return
	* @return Map<String,Object>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	private Map<String, Object> caculatTotalProductAndTotalPriceAndLackNum(Map<String, Object> map) throws Exception {
		List<ScmsMinimumVo> list = scmsShoppingCartMgMapper.getCartList((Supplier) map.get("supplier"));
		if (list == null || list.size() == 0) {
			map.put("noShopping", true);
			return map;
		}
		map.put("scmsMinimumVoList", list);
		BigDecimal totalPrice = new BigDecimal("0");// 购物车选中的商品总价
		Integer totalProduct = 0;// 购物车中选中商品件数
		for (ScmsMinimumVo scmsMinimumVo : list) {
			List<ScmsItemVo> list2 = scmsMinimumVo.getList();
			Integer mininum = Integer.parseInt(scmsMinimumVo.getMinimum().toString());
			Integer purchases = 0;
			Integer checkedCount = 0;
			for (ScmsItemVo scmsItemVo : list2) {
				if (scmsItemVo.getStatus().toString().equals("1")) {// 表示选中购物车中的商品
					purchases += Integer.parseInt(scmsItemVo.getNum().toString());
					totalPrice = totalPrice.add(scmsItemVo.getTotalPrice());
					totalProduct += Integer.parseInt(scmsItemVo.getNum().toString());
					checkedCount += 1;
				}
			}
			// 表示某品牌下的商品有被选中到购物车
			// 某品牌下有商品在购物车中选中,而且不满足起购量才回在页面显示提示信息
			if (checkedCount > 0) {
				scmsMinimumVo.setIsShowLackNumMessage(true);
			}
			Integer lackNum = mininum - purchases;
			if (lackNum > 0) {// 计算购物车中该品牌商品距离品牌的起购量还差多少
				scmsMinimumVo.setLackNum(Short.parseShort(lackNum.toString()));
			} else {
				scmsMinimumVo.setLackNum(Short.parseShort("0"));
			}
		}
		map.put("totalPrice", totalPrice);
		map.put("totalProduct", totalProduct);
		return map;
	}

	/**
	 * 
	* Title: updateCartListBySupplierId 
	* Description: 
	* @param map  key=supplier,登入的批发商对象;key=cartIdList,选中的购物车id集合;key=type,check表示选中
	* @return 选中的购物车商品价格总量
	* @throws Exception 
	* @see com.corner.scms.service.sc.ScmsShoppingCartService#updateCartListBySupplierId(java.util.Map)
	 */
	@Override
	public Map<String, Object> updateCartListBySupplierId(Map<String, Object> map) throws Exception {
		// 修改购物车中商品是否选中转态
		scmsShoppingCartMgMapper.checkCartListBySupplierId(map);
		map = caculatTotalProductAndTotalPriceAndLackNum(map);
		map.remove("supplier");
		map.remove("cartIdList");
		map.remove("type");
		return map;
	}

	/**
	 * 
	* Title: addCartListBySupplierId 
	* Description: 
	* @param map 返回选中的购物车商品总价和当前增加或减少商品的价格总和
	* @return
	* @throws Exception 
	* @see com.corner.scms.service.sc.ScmsShoppingCartService#addCartListBySupplierId(java.util.Map)
	 */
	@Override
	public Map<String, Object> addCartListBySupplierId(Map<String, Object> map) throws Exception {
		ScmsShoppingCart scmsShoppingCart = scmsShoppingCartMgMapper.selectShoppingCartById((String) map.get("cartId"));
		if (scmsShoppingCart == null) {
			return null;
		}
		// 商品单价
		map.put("singlePrice", scmsShoppingCart.getPrice());
		BigDecimal price = scmsShoppingCart.getPrice();
		String quantity = (String) map.get("quantity");
		BigDecimal quantityBigDecimal = new BigDecimal(quantity);
		BigDecimal singleTotalPrice = price.multiply(quantityBigDecimal);
		scmsShoppingCart.setNum(Short.parseShort(quantity));
		scmsShoppingCart.setTotalPrice(singleTotalPrice);
		scmsShoppingCart.setUpdateTime(new Date());
		scmsShoppingCartMapper.updateByPrimaryKeySelective(scmsShoppingCart);
		map.put("singleTotalPrice", singleTotalPrice);
		// 计算单件商品起购量
		// 计算选中了多少件商品,选中商品的总额
		map = caculatTotalProductAndTotalPriceAndLackNum(map);
		map.remove("supplier");
		map.remove("quantity");
		map.remove("cartId");
		return map;
	}

	/**
	 * 
	 * Title: deleteShoppingCartBySupplierId Description:删除购物车中的商品
	 * 
	 * @param map
	 * @return
	 * @see com.corner.scms.service.sc.ScmsShoppingCartService#deleteShoppingCartBySupplierId(java.util.Map)
	 */
	@Override
	public Map<String, Object> deleteShoppingCartBySupplierId(Map<String, Object> map) throws Exception {
		scmsShoppingCartMgMapper.deleteShoppingCartBySupplierId(map);
		map = caculatTotalProductAndTotalPriceAndLackNum(map);
		map.remove("supplier");
		map.remove("cartIdList");
		return map;
	}

	/**
	 * 
	 * Title: getConfirmedCartList Description:查询供应商需要买的商品列表集合
	 * 
	 * @param supplier
	 * @return
	 * @throws Exception
	 * @see com.corner.scms.service.sc.ScmsShoppingCartService#getConfirmedCartList(com.corner.core.beans.Supplier)
	 */
	@Override
	public Map<String, Object> getConfirmedCartList(Supplier supplier) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("supplier", supplier);
		map = caculatTotalProductAndTotalPriceAndLackNum(map);
		if ((Boolean) map.get("noShopping") != null && (Boolean) map.get("noShopping") == true) {
			return map;
		}
		// 因为浏览器后退,前进功能,所以在跳转到确认订单页面的时候,再次校验购物车中选中的商品是否符合起购量(页面禁缓存)
		@SuppressWarnings("unchecked")
		List<ScmsMinimumVo> scmsMinimumVoList = (List<ScmsMinimumVo>) map.get("scmsMinimumVoList");
		for (ScmsMinimumVo scmsMinimumVo : scmsMinimumVoList) {
			if (scmsMinimumVo.getIsShowLackNumMessage()) {
				if (scmsMinimumVo.getLackNum() > 0) {
					return null;
				}
			}
		}
		// 查询批发商所属经销区域相关联的仓库
		ScmsWarehouse scmsWarehouse = scmsWarehouseMgMapper.selectScmsWarehouseBySupplier(supplier);
		// 计算运费
		Map<String, Object> freight = getFreight(supplier, (BigDecimal) map.get("totalPrice"),
				(Integer) map.get("totalProduct"));
		map.put("freight", freight.get("freight"));
		map.put("dfFee", freight.containsKey("dfFee"));	//有代付字段	为true	没有false
		map.put("scmsWarehouse", scmsWarehouse);
		return map;
	}

	/**
	 * 
	* Title: getCheckedShoppingCartProductIsLegalBySupplier 
	* Description:判断批发商购物车中选中的商品是否满足起购量
	* @param supplier
	* @return
	* @throws Exception 
	* @see com.corner.scms.service.sc.ScmsShoppingCartService#getCheckedShoppingCartProductIsLegalBySupplier(com.corner.core.beans.Supplier)
	 */
	@Override
	public Boolean getCheckedShoppingCartProductIsLegalBySupplier(Supplier supplier) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("supplier", supplier);
		map = caculatTotalProductAndTotalPriceAndLackNum(map);

		@SuppressWarnings("unchecked")
		List<ScmsMinimumVo> scmsMinimumVoList = (List<ScmsMinimumVo>) map.get("scmsMinimumVoList");
		for (ScmsMinimumVo scmsMinimumVo : scmsMinimumVoList) {
			if (scmsMinimumVo.getIsShowLackNumMessage()) {
				if (scmsMinimumVo.getLackNum() > 0) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 
	 * Title: generateOrders Description:提交订单
	 * 
	 * @param map
	 * @return
	 * @see com.corner.scms.service.sc.ScmsShoppingCartService#generateOrders(java.util.Map)
	 */
	@Override
	public Map<String, Object> addOrders(Map<String, Object> map) throws Exception {
		String ordertype = (String) map.get("ordertype");
		Supplier supplier = (Supplier) map.get("supplier");
		map = caculatTotalProductAndTotalPriceAndLackNum(map);

		// 判断购物车中是否有商品
		if ((Boolean) map.get("noShopping") != null && (Boolean) map.get("noShopping") == true) {
			return map;
		}
		// 计算选中商品是否满足起购量
		@SuppressWarnings("unchecked")
		List<ScmsMinimumVo> scmsMinimumVoList = (List<ScmsMinimumVo>) map.get("scmsMinimumVoList");
		for (ScmsMinimumVo scmsMinimumVo : scmsMinimumVoList) {
			if (scmsMinimumVo.getIsShowLackNumMessage()) {
				if (scmsMinimumVo.getLackNum() > 0) {
					return null;
				}
			}
		}
		Integer totoalProduct = (Integer) map.get("totalProduct");
		// 判断购物车中是否有选中的商品
		if (totoalProduct == 0) {
			return map;
		}

		BigDecimal freight = new BigDecimal("0");
		boolean dfFee = false;
		BigDecimal oneFreight = new BigDecimal("0");
		
		if (ordertype.equals("0")) {// 需要计算运费
			Map<String, Object> freightDfFee = getFreight(supplier, (BigDecimal) map.get("totalPrice"),
					(Integer) map.get("totalProduct"));
			freight = new BigDecimal(freightDfFee.get("freight").toString());
			dfFee = freightDfFee.containsKey("dfFee");
			map.put("freight", freight);
			map.put("dfFee", dfFee);	//有代付字段	为true	没有false
			oneFreight = new BigDecimal(freightDfFee.containsKey("dfFee") ? freightDfFee.get("oneFreight").toString() : "0");
			
			map.put("oneFreight", freightDfFee.containsKey("dfFee") ? freightDfFee.get("oneFreight") : 0);
			map.put("paidMethods", freightDfFee.get("paidMethods"));
		}
		Date addTime = new Date();//订单生成时间
		SupplierVo supplierVo = scmsSupplierMgMapper.getSupplierVo(supplier);//批发商视图类
		ScmsGroup scmsGroup = scmsGroupMapper.selectByPrimaryKey(supplierVo.getBsCircleId());//经销区域
		
		//level=1的scOrderInfo
		ScOrderInfo scOrderInfoLevel1=new ScOrderInfo();
		scOrderInfoLevel1.setLevel((byte)1);//level
		scOrderInfoLevel1.setOrderId(CreateOrderIdUtil.dateToString());//orderId
		scOrderInfoLevel1.setAddTime(addTime);//addTime
		scOrderInfoLevel1.setConsignee(supplierVo.getManagerName());// 收货人
		scOrderInfoLevel1.setMobile(supplierVo.getMobile());//收货人联系手机
		scOrderInfoLevel1.setAddress(supplierVo.getSupplierAddress());// 收货人收货地址
		scOrderInfoLevel1.setStatus((byte)1);// 订单状态,1表示已下单
		scOrderInfoLevel1.setSupportStatus((byte)0);// 支付转态,0表示未支付
		scOrderInfoLevel1.setOrdertype(new Byte(ordertype));// 配送方式
		scOrderInfoLevel1.setWarehouseId(supplierVo.getWarehouseId());// 仓库id
		scOrderInfoLevel1.setWarehouseName(supplierVo.getWarehouseName());// 仓库名
		scOrderInfoLevel1.setSupplierId(supplier.getId());// 登录的批发商id
		scOrderInfoLevel1.setSupplierName(supplier.getSupplierName());// 批发商名字
		scOrderInfoLevel1.setGroupId(supplierVo.getBsCircleId());// 经销区域id
		scOrderInfoLevel1.setGroupName(scmsGroup.getName());//经销区域名字
		scOrderInfoLevel1.setFreight(freight);//运费
		scOrderInfoLevel1.setDfFee(dfFee);//代付运费
		scOrderInfoLevel1.setpId(scOrderInfoLevel1.getId());//Pid
		
		
		
		/******************** 生成ScorderDetail begin********************************/
		List<ScOrderDetailVo> orderDetailList = scOrderDetailMgMapper.getOrderDetailListBySupplier(supplier);
		for (ScOrderDetailVo scOrderDetail : orderDetailList) {
			scOrderDetail.setZjjfPrice(scOrderDetail.getZjjfPrice().subtract(oneFreight));
			scOrderDetail.setFreight(new BigDecimal(scOrderDetail.getQuantity()).multiply(oneFreight));
			scOrderDetail.setTotalPrice(new BigDecimal(scOrderDetail.getQuantity()).multiply(scOrderDetail.getZjjfPrice()));
			scOrderDetail.setAddTime(addTime);
		}
		/******************** 生成ScorderDetail end********************************/
		
		/******************** 生成ScorderInfo begin********************************/
		//每一个经销商的商品生成一个ScOrderInfo,level=2
		//level=2的ScOrderInfo再合成一个level=1的ScOrderInfo,通过Pid进行关联
		//scOrderInfoMap key为managerId,value为ScOrderInfo对象
		boolean scOrderInfoLevel2Freight = true;
		Map<String, ScOrderInfo> scOrderInfoMap=new HashMap<String,ScOrderInfo>();
		for (ScOrderDetailVo scOrderDetailVo : orderDetailList) {
			ScOrderInfo scOrderInfoLevel2 = scOrderInfoMap.get(scOrderDetailVo.getManagerId());
			if(scOrderInfoLevel2==null){
				scOrderInfoLevel2=new ScOrderInfo();
				scOrderInfoLevel2.setLevel((byte)2);//订单层级
				scOrderInfoLevel2.setOrderId(CreateOrderIdUtil.dateToString());//orderId
				scOrderInfoLevel2.setAddTime(addTime);//addTime
				scOrderInfoLevel2.setConsignee(supplierVo.getManagerName());// 收货人
				scOrderInfoLevel2.setMobile(supplierVo.getMobile());//收货人联系手机
				scOrderInfoLevel2.setAddress(supplierVo.getSupplierAddress());// 收货人收货地址
				scOrderInfoLevel2.setStatus((byte)1);// 订单状态,1表示已下单
				scOrderInfoLevel2.setSupportStatus((byte)0);// 支付转态,0表示未支付
				scOrderInfoLevel2.setOrdertype(new Byte(ordertype));// 配送方式
				scOrderInfoLevel2.setWarehouseId(supplierVo.getWarehouseId());// 仓库id
				scOrderInfoLevel2.setWarehouseName(supplierVo.getWarehouseName());// 仓库名
				scOrderInfoLevel2.setSupplierId(supplier.getId());// 登录的批发商id
				scOrderInfoLevel2.setSupplierName(supplier.getSupplierName());// 批发商名字
				scOrderInfoLevel2.setGroupId(supplierVo.getBsCircleId());// 经销区域id
				scOrderInfoLevel2.setGroupName(scmsGroup.getName());//经销区域名字
				scOrderInfoLevel2.setManagerId(scOrderDetailVo.getManagerId());//经销商id
				ScmsManager scmsManager = scmsManagerMapper.selectByPrimaryKey(scOrderDetailVo.getManagerId());
				scOrderInfoLevel2.setManagerName(scmsManager.getManagerName());//经销商名字
				scOrderInfoLevel2.setManagerTel(scmsManager.getMobile());//经销商手机号
				
				scOrderInfoLevel2.setOrderPrice(new BigDecimal(0));
				scOrderInfoLevel2.setMarkePrice(new BigDecimal(0));//marketPrice
				scOrderInfoLevel2.setGoodsPrice(new BigDecimal(0));//goodsPrice
				scOrderInfoLevel2.setFreight(new BigDecimal(0));	//运费
				
			}
			//scOrderDetail 插入orderId和orderId2
			scOrderDetailVo.setOrderId2(scOrderInfoLevel2.getOrderId());//orderId2
			scOrderDetailVo.setOrderId(scOrderInfoLevel1.getOrderId());//orderId
			
			BigDecimal orderPrice = scOrderInfoLevel2.getOrderPrice();
			orderPrice = orderPrice.add(scOrderDetailVo.getTotalPrice().add(scOrderDetailVo.getFreight()));
		    scOrderInfoLevel2.setOrderPrice(orderPrice);//orderPrice
		    
			BigDecimal marketPrice = scOrderInfoLevel2.getMarkePrice();
			marketPrice = marketPrice.add(scOrderDetailVo.getMarketTotalPrice());
			scOrderInfoLevel2.setMarkePrice(marketPrice);//marketPrice
			
			BigDecimal goodsPrice = scOrderInfoLevel2.getGoodsPrice();
			goodsPrice = goodsPrice.add(scOrderDetailVo.getTotalPrice());
			scOrderInfoLevel2.setGoodsPrice(goodsPrice);//goodsPrice
			
			if(dfFee){
				scOrderInfoLevel2.setFreight(scOrderInfoLevel2.getFreight().add(scOrderDetailVo.getFreight()));	//运费
			}else{
				if(scOrderInfoLevel1.getFreight().compareTo(new BigDecimal(0)) > 0 && scOrderInfoLevel2Freight){
					scOrderInfoLevel2Freight = false;
					scOrderInfoLevel2.setFreight(scOrderInfoLevel2.getFreight().add(scOrderDetailVo.getFreight()));	//运费
				}
			}
			scOrderInfoMap.put(scOrderDetailVo.getManagerId(), scOrderInfoLevel2);
		}
		
		//scOrderInfoMap 将level=2的ScOrderInfo 放到List中
		List<ScOrderInfo> scOrderInfoLevel2s = new ArrayList<ScOrderInfo>();
		
		
		BigDecimal orderPirce = scOrderInfoLevel1.getOrderPrice();
		BigDecimal marketPrice = scOrderInfoLevel1.getMarkePrice();
		for(String managerId:scOrderInfoMap.keySet()){
			ScOrderInfo scOrderInfoLevl2=scOrderInfoMap.get(managerId);
			scOrderInfoLevl2.setpId(scOrderInfoLevel1.getId());//pid
			orderPirce = orderPirce.add(scOrderInfoLevl2.getOrderPrice());
			marketPrice=marketPrice.add(scOrderInfoLevl2.getMarkePrice());
			scOrderInfoLevel2s.add(scOrderInfoLevl2);
		}
		scOrderInfoLevel1.setGoodsPrice(orderPirce);//goodsPrice
		scOrderInfoLevel1.setMarkePrice(marketPrice);//marketPrice
		
		if(!dfFee){
			orderPirce = orderPirce.add(freight);
		}
		scOrderInfoLevel1.setOrderPrice(orderPirce);//orderPrice
		String remark = (String) map.get("remark");
		if (!StringUtil.stringIsNullOrEmpty(remark)) {
			scOrderInfoLevel1.setSpRemark(remark);// 批发商备注
		}
		map.put("scOrderInfoId", scOrderInfoLevel1.getId());
		/******************** 生成ScorderInfo end********************************/
		
		//插入数据
		scOrderInfoMapper.insertSelective(scOrderInfoLevel1);//插入ScOrderInfo level=1数据
		scOrderInfoMgMapper.batchSaveLevel2(scOrderInfoLevel2s);//批量新增ScOrderInfo lvel=2数据
		scOrderDetailMgMapper.batchSave(orderDetailList);//批量新增ScOrderDetail数据
		scmsItemMgMapper.batchUpdateSales(orderDetailList);//批量更新ScmsItem销量
		
		// 删除批发商已经购买了的商品
		scmsShoppingCartMgMapper.deleteBuyedShoppingCartBySullieper(supplier);
		return map;
	}

	/**
	 * 
	* @Title: getFreight 
	* @Description:计算运费
	* @param @param supplier
	* @param @param totalPrice
	* @param @param totoalProduct
	* @param @return
	* @param @throws Exception
	* @return BigDecimal    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	@SuppressWarnings("unchecked")
	private Map<String, Object> getFreight(Supplier supplier, BigDecimal totalPrice, Integer totoalProduct) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		// 获取模板
		ScmsFreightTplVo scmsFreightTplVo = scmsFreightTplMgMapper.getFreightTpl(supplier);
		if (scmsFreightTplVo == null) {
			throw new Exception("登录批发商没有关联区域或者关联运费模板");
		}
		// 按照订单数量计算运费,前提是购买的商品以箱来计算
		Byte paidMethods = scmsFreightTplVo.getPaidMethods();
		BigDecimal freight = new BigDecimal(0);
		List<ScmsFreightTplMap> freightTplMaps = scmsFreightTplVo.getList();
		ScmsFreightTplMap tempScmsFreightTplMap = null;
		// 如果当前订单金额或者订单件数大于模板设置的最大值,则以模板中的最大值为标准
		if (paidMethods == 0) {// 按订单金额算
			// 将运费模板正序排序
			freightTplMaps = SortUtil.sort(freightTplMaps, "orderPrice", "asc");
			for (ScmsFreightTplMap scmsFreightTplMap : freightTplMaps) {
				if (totalPrice.compareTo(scmsFreightTplMap.getOrderPrice()) <= 0) {
					tempScmsFreightTplMap = scmsFreightTplMap;
					break;
				}
			}
			if (tempScmsFreightTplMap == null) {
				tempScmsFreightTplMap = freightTplMaps.get(freightTplMaps.size() - 1);
			}
			freight = totalPrice.multiply(new BigDecimal(tempScmsFreightTplMap.getFreight() / 100f + ""));
			freight = freight.setScale(0, BigDecimal.ROUND_UP);
		} else if(paidMethods == 1){// 按订单件数算
				// 将运费模板正序排序
			freightTplMaps = SortUtil.sort(freightTplMaps, "orderNum", "asc");
			for (ScmsFreightTplMap scmsFreightTplMap : freightTplMaps) {
				
				if(totoalProduct<=Integer.valueOf(scmsFreightTplMap.getOrderNum())){
					tempScmsFreightTplMap = scmsFreightTplMap;
					break;
				}
			}
			if (tempScmsFreightTplMap == null) {
				tempScmsFreightTplMap = freightTplMaps.get(freightTplMaps.size() - 1);
			}
			freight = tempScmsFreightTplMap.getFreightPrice();
		}else if(paidMethods == 2){	//每件收取费用
			if(freightTplMaps.get(0) != null){
				map.put("oneFreight", freightTplMaps.get(0).getFreightPrice());
				freight = freightTplMaps.get(0).getFreightPrice().multiply(new BigDecimal(totoalProduct));
			}
			map.put("dfFee", true);
		}
		map.put("freight", freight);
		map.put("paidMethods", paidMethods.toString());
		return map;
	}

	/**
	 * 
	* Title: updateScOrderInfoToSettlement 
	* Description: 跳转到结算页面前,再次关联查询订单中包含的商品价格,并更新订单信息
	* @param map
	* @return
	* @throws Exception 
	* @see com.corner.scms.service.sc.ScmsShoppingCartService#updateScOrderInfoToSettlement(java.util.Map)
	 */
	@Override
	public Map<String, Object> updateScOrderInfoToSettlement(Map<String, Object> map) throws Exception {
		//校验订单价格,并更新
		ScOrderInfo scOrderInfo = updateNewestScOrderInfoPrice(map);
		map.put("totalPrice", scOrderInfo.getOrderPrice());
		map.put("orderId", scOrderInfo.getOrderId());
		map.put("scOrderInfoId", scOrderInfo.getId());
		return map;
	}

	/** 
	* @Title: updateNewestScOrderInfoPrice 
	* @Description:更新订单价格信息
	* @param @param map(map必须要有key为scOrderInfo,value为ScOrderInfo对象的键值对   key为supplier,value为Supplier对象)
	* @param @return 当前ScOrderInfo 最新价格
	* @param @throws Exception
	* @return ScOrderInfo    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws 
	*/
	private ScOrderInfo updateNewestScOrderInfoPrice(Map<String, Object> map) throws Exception {
		ScOrderInfo scOrderInfo = (ScOrderInfo) map.get("scOrderInfo");
		List<ScOrderDetail> list = scOrderDetailMgMapper.getNewestPriceByScOrderInfo(map);
		BigDecimal totalPrice = new BigDecimal(0);
		Integer totalProduct = 0;
		// update ScOrderDetail价格
		for (ScOrderDetail scOrderDetail : list) {
			totalPrice = totalPrice.add(scOrderDetail.getTotalPrice());
			totalProduct += scOrderDetail.getQuantity();
		}
		Supplier supplier = (Supplier) map.get("supplier");
		// update ScOrderInfo
		scOrderInfo.setGoodsPrice(totalPrice);// 商品总额
		if (scOrderInfo.getOrdertype().toString().equals("0")) {// 判断是否计算运费
			Map<String, Object> freightDfFee = getFreight(supplier, totalPrice, totalProduct);
			scOrderInfo.setFreight(new BigDecimal(freightDfFee.get("freight").toString()));
			scOrderInfo.setDfFee(freightDfFee.containsKey("dfFee"));
			totalPrice = totalPrice.add(scOrderInfo.getFreight());// 订单总额
		}
		scOrderInfo.setOrderPrice(totalPrice);
		scOrderInfoMgMapper.updateOrderPrice(scOrderInfo);
		
		//update ScOrderDetail
	    scOrderDetailMgMapper.batchUpdatePrice(list);
		return scOrderInfo;
	}

	/**
	 * 
	* Title: updateScOrderInfoPayPriceAndGetNewestScOrderInfo 
	* Description:用于更新ScOrderInfo最新价格并返回最新价格的ScOrderInfo 
	* @param map
	* @return
	* @throws Exception 
	* @see com.corner.scms.service.sc.ScmsShoppingCartService#updateScOrderInfoPayPriceAndGetNewestScOrderInfo(java.util.Map)
	 */
	@Override
	public Map<String, Object> updateScOrderInfoPayPriceAndGetNewestScOrderInfo(Map<String, Object> map)
			throws Exception {
		updateNewestScOrderInfoPrice(map);
		return map;
	}

	@Override
	public SystemInfo getThirdFee() throws Exception {
		return scmsShoppingCartMgMapper.getThirdFee();
	}

	/*	TODO(用一句话描述这个变量表示什么) 
	* <p>Title: deleteObjects</p> 
	* <p>Description: </p> 
	* @param tableName
	* @param array
	* @return 
	* @see com.corner.scms.service.BaseService#deleteObjects(java.lang.String, java.lang.String[]) 
	*/ 
	@Override
	public ModelMsg deleteObjects(String tableName, String[] array) {
		// TODO Auto-generated method stub
		return null;
	}

}

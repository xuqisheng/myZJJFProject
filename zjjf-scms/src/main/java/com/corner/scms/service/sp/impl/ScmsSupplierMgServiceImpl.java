/**  
 * @Title: ScMgSupplierServiceImpl.java
 * @Package com.corner.scms.service.sp.impl
 * @Description: TODO
 * @author 杨开泰
 * @date 2015年12月3日
 */
package com.corner.scms.service.sp.impl;

import com.corner.core.beans.ScOrderInfo;
import com.corner.core.beans.SpGroup;
import com.corner.core.beans.SpWalletLog;
import com.corner.core.beans.Supplier;
import com.corner.core.dao.SpWalletLogMapper;
import com.corner.core.dao.SupplierMapper;
import com.corner.scms.dao.ScOrderInfoMgMapper;
import com.corner.scms.dao.ScmsSupplierMgMapper;
import com.corner.scms.service.impl.BaseServiceImpl;
import com.corner.scms.service.sp.ScmsSupplierMgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 
* @ClassName: SupplierMgServiceImpl 
* @Description: 供应商业务层实现类 
* @author 杨开泰  yangkaitai@izjjf.cn 
* @date 2015年12月4日 下午12:02:45
 */
@Service
public class ScmsSupplierMgServiceImpl extends BaseServiceImpl implements ScmsSupplierMgService {

	@Autowired
	SupplierMapper supplierMapper;// core 工程下

	@Autowired
	ScmsSupplierMgMapper scmsSupplierMgMapper;

	@Autowired
	ScOrderInfoMgMapper scOrderInfoMgMapper;

	@Autowired
	SpWalletLogMapper spWalletLogMapper;
	@Override
	public Supplier selectByPrimaryKey(String id) {
		return supplierMapper.selectByPrimaryKey(id);
	}

	/**
	 * 
	* @Title: getSupplierAndSpGroup 
	* @Description:查询供应商所属的定格
	* @param @param id 供应商id
	* @param @return
	* @return List<SpGroup>
	* @author 杨开泰  yangkaitai@izjjf.cn
	* @throws
	 */
	@Override
	public List<SpGroup> getSupplierAndSpGroup(String spId) {
		return scmsSupplierMgMapper.getSupplierAndSpGroup(spId);
	}

	/**
	 * 
	* Title: updateSupplierWallet 
	* Description:更新钱包余额 
	* @param map 
	 * @throws Exception 
	* @see com.corner.scms.service.sp.ScmsSupplierMgService#updateSupplierWallet(java.util.Map)
	 */
	@Override
	public void updateSupplierWallet(Map<String, Object> map) throws Exception {
		Supplier supplier = (Supplier) map.get("supplier");
		ScOrderInfo scOrderInfo = (ScOrderInfo) map.get("scOrderInfo");
		BigDecimal wallet = supplier.getWallet();
		wallet = wallet.subtract(scOrderInfo.getOrderPrice());
		supplier.setWallet(wallet);
		// 更新钱包余额
		map.put("operate", "-");
		map.put("operateMoney", scOrderInfo.getOrderPrice());
		scmsSupplierMgMapper.updateSupplierWallet(map);
		// 生成消费记录,记录到SpWalletLog表中
		SpWalletLog spWalletLog = new SpWalletLog();
		spWalletLog.setSpId(supplier.getId());// 批发商id
		spWalletLog.setActionType(new Byte("2"));// 支出
		spWalletLog.setOptType(new Byte("5"));// 进货
		spWalletLog.setActionTime(new Date());// 操作时间
		spWalletLog.setOrderId(scOrderInfo.getId());// 订单主键
		spWalletLog.setMoney(scOrderInfo.getOrderPrice());// 操作金额
		spWalletLog.setBalance(wallet);// 余额
		spWalletLogMapper.insertSelective(spWalletLog);
		// 更新订单支付信息
		scOrderInfo.setIsUsedBalance(true);// 使用钱包余额
		scOrderInfo.setBalanceUsedNum(scOrderInfo.getOrderPrice());// 钱包扣款数量
		scOrderInfo.setSupportTime(new Date());// 在线支付时间
		scOrderInfo.setSupportStatus(new Byte("0"));// 未支付,这里仅用钱包支付(该字段禁用记录在线支付是否已经支付)
		scOrderInfo.setStatus(new Byte("2"));// 已支付
		scOrderInfo.setOutOfPrice(scOrderInfo.getOrderPrice());// 实际付款金额
		scOrderInfoMgMapper.updatePaymentStatus(scOrderInfo);// 更新订单支付状态和支付方式
	}

	/**
	 * 本方法暂时没用
	* Title: updateScOrderInfoPayStatus 
	* Description:更新钱包余额信息,更新订单支付信息 
	* @param map
	* @return
	* @throws Exception 
	* @see com.corner.scms.service.sp.ScmsSupplierMgService#updateScOrderInfoPayStatus(java.util.Map)
	 */
	@Override
	public Map<String, Object> updateScOrderInfoPayStatus(Map<String, Object> map) throws Exception {
		Supplier supplier = (Supplier) map.get("supplier");
		ScOrderInfo scOrderInfo = (ScOrderInfo) map.get("scOrderInfo");
		BigDecimal wallet = supplier.getWallet();

		// 钱包使用记录对象
		SpWalletLog spWalletLog = new SpWalletLog();
		spWalletLog.setSpId(supplier.getId());// 批发商id
		spWalletLog.setActionType(new Byte("2"));// 支出
		spWalletLog.setOptType(new Byte("5"));// 进货
		spWalletLog.setActionTime(new Date());// 操作时间
		spWalletLog.setOrderId(scOrderInfo.getOrderId());// 订单主键
		spWalletLog.setBalance(wallet);// SpWalletLog
										// 中的balance字段记录的是该笔订单使用钱,钱包余额
		BigDecimal orderPrice = scOrderInfo.getOrderPrice();// 订单总金额

		// 用于更新钱包余额
		map.put("operate", "-");

		// 更新订单支付信息,生成交易记录
		scOrderInfo.setIsUsedBalance(true);// 是否使用钱包
		scOrderInfo.setSupportTime(new Date());// 使用钱包的支付时间,并不是最终的支付时间
		if (wallet.compareTo(orderPrice) < 0) {// 钱包的钱少于订单的钱
			scOrderInfo.setBalanceUsedNum(wallet);// 使用钱包的金额
			supplier.setWallet(new BigDecimal("0"));// 本次支付完后,用户钱包余额,这里进行计算,用于页面显示

			// 用于更新钱包余额
			map.put("operateMoney", wallet);

			wallet = wallet.multiply(new BigDecimal("-1"));
			spWalletLog.setMoney(wallet);// 操作金额
			spWalletLog.setBalance(new BigDecimal("0"));// 余额
		} else {
			// 用于更新钱包余额
			map.put("operateMoney", orderPrice);
			scOrderInfo.setBalanceUsedNum(orderPrice);// 使用钱包的金额
			wallet = wallet.subtract(orderPrice);// 本次支付后的余额
			supplier.setWallet(wallet);// 计算用户钱包余额,用于页面显示
			orderPrice = orderPrice.multiply(new BigDecimal("-1"));
			spWalletLog.setMoney(orderPrice);// 操作金额
			scOrderInfo.setSupportStatus(new Byte("1"));// 是否完成支付
			scOrderInfo.setStatus(new Byte("2"));// 已支付
			scOrderInfo.setOutOfPrice(scOrderInfo.getOrderPrice());// 实际付款金额
		}

		scmsSupplierMgMapper.updateSupplierWallet(map);// 更新钱包余额
		spWalletLogMapper.insertSelective(spWalletLog);// 生成交易记录
		scOrderInfoMgMapper.updatePaymentStatus(scOrderInfo);// 更新订单支付状态和支付方式
		map.put("scOrderInfo", scOrderInfo);
		map.put("supplier", supplier);

		return map;
	}

	/**
	 * 
	* Title: updateSupplierPayPassword 
	* Description:修改批发商支付密码信息 
	* @param supplier
	* @return
	* @throws Exception 
	* @see com.corner.scms.service.sp.ScmsSupplierMgService#updateSupplierPayPassword(com.corner.core.beans.Supplier)
	 */
	@Override
	public int updateSupplierPayPassword(Supplier supplier) throws Exception {
		return scmsSupplierMgMapper.updateSupplierPayPassword(supplier);
	}

	/**
	 * 
	* Title: addSupplierWallet 
	* Description:给批发商钱包加钱 
	* @param map
	* @throws Exception 
	* @see com.corner.scms.service.sp.ScmsSupplierMgService#addSupplierWallet(java.util.Map)
	 */
	@Override
	public void addSupplierWallet(Map<String, Object> map) throws Exception {
		ScOrderInfo scOrderInfo = (ScOrderInfo) map.get("scOrderInfo");
		Supplier supplier = supplierMapper.selectByPrimaryKey(scOrderInfo.getSupplierId());
        BigDecimal zhifuOrderAmout = (BigDecimal) map.get("zhifuOrderAmout");
        zhifuOrderAmout = zhifuOrderAmout.divide(new BigDecimal("100"));
		BigDecimal wallet = supplier.getWallet();

		// 钱包使用记录对象
		SpWalletLog spWalletLog = new SpWalletLog();
		spWalletLog.setSpId(supplier.getId());// 批发商id
		spWalletLog.setActionType(new Byte("1"));// 收入
		spWalletLog.setOptType(new Byte("10"));// 退款
		spWalletLog.setActionTime(new Date());// 操作时间
		spWalletLog.setOrderId(scOrderInfo.getOrderId());// 订单主键
		spWalletLog.setBalance(wallet);// SpWalletLog
										// 中的balance字段记录的是该笔订单使用钱,钱包余额

		// 用于更新钱包余额
		map.put("operate", "+");

		// 用于更新钱包余额
		map.put("operateMoney", zhifuOrderAmout);

		spWalletLog.setMoney(zhifuOrderAmout);// 操作金额

		scmsSupplierMgMapper.updateSupplierWallet(map);// 更新钱包余额
		spWalletLogMapper.insertSelective(spWalletLog);// 生成交易记录
	}
	@Override
	public int updateSupplier(Supplier supplier) throws Exception {
		return supplierMapper.updateByPrimaryKeySelective(supplier);
	}

}

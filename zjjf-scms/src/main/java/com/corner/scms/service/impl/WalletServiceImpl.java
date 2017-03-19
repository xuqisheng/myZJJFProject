package com.corner.scms.service.impl;

import com.corner.core.beans.FinWallet;
import com.corner.core.beans.SpWithDraw;
import com.corner.core.beans.Supplier;
import com.corner.core.beans.User;
import com.corner.core.beans.vo.Pager;
import com.corner.core.dao.FinWalletLogMapper;
import com.corner.core.dao.FinWalletMapper;
import com.corner.core.dao.SpWithDrawMapper;
import com.corner.core.utils.StringUtil;
import com.corner.scms.beans.ro.FinWalletLogRo;
import com.corner.scms.beans.vo.FinWalletLogVo;
import com.corner.scms.dao.FinWalletMgMapper;
import com.corner.scms.service.UserService;
import com.corner.scms.service.WalletService;
import com.corner.scms.service.sp.ScmsSpWalletMgService;
import com.corner.scms.service.sp.ScmsSupplierMgService;
import com.corner.scms.utils.enums.BusinessType;
import com.corner.scms.utils.enums.Purpose;
import com.corner.scms.utils.enums.SystemCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WalletServiceImpl implements WalletService {
	@Autowired
	ScmsSupplierMgService scmsSupplierMgService;
	@Autowired
	UserService userService;
	@Autowired
	FinWalletMapper finWalletMapper;
	@Autowired
	FinWalletLogMapper finWalletLogMapper;
	@Autowired
	SpWithDrawMapper spWithDrawMapper;
	@Autowired
	FinWalletMgMapper finWalletMgMapper;
	@Autowired
	ScmsSpWalletMgService scmsSpWalletMgService;
	@Override
	public FinWallet selectOne(String userId) {
		Supplier supplier = scmsSupplierMgService.selectByPrimaryKey(userId);
		User user = userService.selectUserBySpId(supplier.getId());
		return finWalletMapper.selectByPrimaryKey(user.getWalletId());
	}

	@Override
	public Pager<FinWalletLogVo> selectFinWalletLogPageList(FinWalletLogRo finWalletLogRo) {
		finWalletLogRo.setSortName("a.sort");
		finWalletLogRo.setSortOrder("desc");
		List<FinWalletLogVo> list = finWalletMgMapper.selectFinWalletLogPageList(finWalletLogRo);
		int totalSize = finWalletMgMapper.selectCountFinWalletLog(finWalletLogRo);
		return new Pager<FinWalletLogVo>(totalSize, list);
	}
	@Override
	public Map<String , Object> selectAllIncomeAndOutlay(String walletId) {
		Map<String , Object> map = new HashMap<>();
		map.put("type" , 1);
		map.put("walletId" , walletId);
		BigDecimal income = finWalletMgMapper.selectAllIncomeAndOutlay(map);
		if(income == null)
			map.put("income" , "0.00");
		else
			map.put("income" , income.setScale(2).toString());
		map.put("type" , 0);
		BigDecimal outlay =finWalletMgMapper.selectAllIncomeAndOutlay(map);
		if(outlay == null)
			map.put("outlay" , "0.00");
		else
			map.put("outlay" , "-"+outlay.setScale(2).toString());
		return map;
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRED , rollbackFor = RuntimeException.class)
	public Long doWithDraw(String walletId, BigDecimal bigDecimal , String spId)  {
		Supplier supplier = scmsSupplierMgService.selectByPrimaryKey(walletId);
		FinWallet finWallet = finWalletMapper.selectByPrimaryKey(walletId);
		if(finWallet.getBalance().compareTo(bigDecimal) == -1)
			throw new RuntimeException("账户余额不足");
		else if(bigDecimal.compareTo(new BigDecimal("1")) == -1)
			throw new RuntimeException("提现金额不能小于一元");
		else if(supplier == null)
			throw new RuntimeException("批发商用户信息不存在");
		else if(StringUtil.stringIsNullOrEmpty(supplier.getBankName() , supplier.getCardUser() , supplier.getBankNum()))
			throw new RuntimeException("缺少银行账户信息");

		BigDecimal fee = bigDecimal.multiply(new BigDecimal(finWallet.getRates() + "")).setScale(2, BigDecimal.ROUND_UP);//手续费金额
		BigDecimal money = bigDecimal.subtract(fee);	//到账金额
		Date date = new Date();
		SpWithDraw spWithDraw = new SpWithDraw();
		spWithDraw.setSupplierId(supplier.getId());
		spWithDraw.setCardBankName(supplier.getBankName());	//开户行名
		spWithDraw.setCardUserName(supplier.getCardUser());	//开户名
		spWithDraw.setCardNo(supplier.getBankNum());	//银行卡号
		spWithDraw.setAmount(money.divide(new BigDecimal("-1")));	//申请提取金额
		spWithDraw.setApplyTime(date);
		spWithDraw.setApplyRemark("提现支出");
		spWithDrawMapper.insertSelective(spWithDraw);

		scmsSpWalletMgService.checkoutAndLog(SystemCode.SUPPLIER , BusinessType.WITHDRAW , spWithDraw.getId().toString() , Purpose.WITHDRAW , fee);
		return spWithDraw.getId();
	}

}

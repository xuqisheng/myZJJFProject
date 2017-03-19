package com.corner.account.service.impl;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.account.dao.PlantWalletLogMgMapper;
import com.corner.account.dao.SpWalletLogMgMapper;
import com.corner.account.service.PlantWalletService;
import com.corner.core.beans.MaWallet;
import com.corner.core.beans.MaWithDraw;
import com.corner.core.beans.MaWithdrawDealLog;
import com.corner.core.beans.PlantWalletLog;
import com.corner.core.beans.SpWalletLog;
import com.corner.core.beans.WhWallet;
import com.corner.core.beans.WhWithDraw;
import com.corner.core.beans.WhWithdrawDealLog;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.dao.MaWalletMapper;
import com.corner.core.dao.MaWithDrawMapper;
import com.corner.core.dao.MaWithdrawDealLogMapper;
import com.corner.core.dao.SpWalletLogMapper;
import com.corner.core.dao.WhWalletMapper;
import com.corner.core.dao.WhWithDrawMapper;
import com.corner.core.dao.WhWithdrawDealLogMapper;
import com.corner.core.utils.StringUtil;

@Service
public class PlantWalletServiceImpl implements PlantWalletService {
	@Autowired
	PlantWalletLogMgMapper plantWalletLogMgMapper;
	@Autowired
	WhWalletMapper whWalletMapper;
	@Autowired
	MaWalletMapper maWalletMapper;
	@Autowired
	SpWalletLogMapper spWalletLogMapper;
	@Autowired
	WhWithDrawMapper whWithDrawMapper;
	@Autowired
	MaWithDrawMapper maWithDrawMapper;
	@Autowired
	WhWithdrawDealLogMapper whWithdrawDealLogMapper;
	@Autowired
	MaWithdrawDealLogMapper maWithdrawDealLogMapper;
	@Autowired
	SpWalletLogMgMapper spWalletLogMgMapper;
	
	
	@Override
	public ModelMsg updatePlantWalletAndLog(PlantWalletLog plantWalletLog) throws Exception {
		plantWalletLog.setActionTime(new Date());
		if(StringUtil.stringIsNullOrEmpty(plantWalletLog.getPlantWalletId()))
			return new ModelMsg(false , "缺少必要数据");
		else if(plantWalletLog.getMoney() == null || plantWalletLog.getOptType() == null || plantWalletLog.getActionType() ==null)
			return new ModelMsg(false , "缺少必要数据");
		int result = plantWalletLogMgMapper.updateWalletByLog(plantWalletLog);
		if(result == 0)
			throw new Exception("修改钱包失败");
		return new ModelMsg(true , "更新钱包成功");
	}
	@Override
	public ModelMsg updateSupplerWalletAndLog(SpWalletLog spWalletLog) throws Exception {
		spWalletLog.setActionTime(new Date());
		if(StringUtil.stringIsNullOrEmpty(spWalletLog.getSpId()))
			return new ModelMsg(false , "缺少必要数据");
		else if(spWalletLog.getMoney() == null || spWalletLog.getOptType() == null || spWalletLog.getActionType() ==null)
			return new ModelMsg(false , "缺少必要数据");
		int result = spWalletLogMgMapper.updateWalletByLog(spWalletLog);
		if(result == 0)
			throw new Exception("修改钱包失败");
		return new ModelMsg(true, "成功");
	}
	@Override
	public BigDecimal findWhWalletById(String id) {
		WhWallet wallet = whWalletMapper.selectByPrimaryKey(id);
		if(wallet == null)
			return new BigDecimal(0);
		else
			return wallet.getWallet();
	}
	@Override
	public ModelMsg insertWhWithDraw(WhWithDraw whWithDraw) throws Exception {
		whWithDraw.setApplyTime(new Date());
		int result = whWithDrawMapper.insertSelective(whWithDraw);
		if(result == 0)
			throw new Exception("新增提现记录失败");
		WhWithdrawDealLog dealLog = new WhWithdrawDealLog();
		dealLog.setWhWithDrawId(whWithDraw.getId());
		dealLog.setObjectStatus(Byte.valueOf("1"));
		dealLog.setObjectDate("申请提现");
		dealLog.setDealTime(new Date());
		dealLog.setDealerId(whWithDraw.getWarehouseId());
		result = whWithdrawDealLogMapper.insertSelective(dealLog);
		if(result == 0)
			throw new Exception("新增提现记录失败");
		return new ModelMsg(true, "新增成功");
	}
	@Override
	public BigDecimal findMaWalletById(String id) {
		MaWallet maWallet = maWalletMapper.selectByPrimaryKey(id);
		if(maWallet == null)
			return new BigDecimal(0);
		else
			return maWallet.getWallet();
	}
	@Override
	public ModelMsg insertMaWithDraw(MaWithDraw maWithDraw) throws Exception {
		maWithDraw.setApplyTime(new Date());
		int result = maWithDrawMapper.insertSelective(maWithDraw);
		if(result == 0)
			throw new Exception("新增提现记录失败");
		MaWithdrawDealLog dealLog = new MaWithdrawDealLog();
		dealLog.setWithDrawId(maWithDraw.getId());
		dealLog.setObjectStatus(Byte.valueOf("1"));
		dealLog.setObjectDate("申请提现");
		dealLog.setDealTime(new Date());
		dealLog.setDealerId(maWithDraw.getManagerId());
		result = maWithdrawDealLogMapper.insertSelective(dealLog);
		if(result == 0)
			throw new Exception("新增提现记录失败");
		return new ModelMsg(true, "新增成功");
	}
}

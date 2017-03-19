package com.corner.account.service.impl;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.corner.account.beans.ro.MaWalletCheckMgCondition;
import com.corner.account.beans.ro.MaWithDrawMgCondition;
import com.corner.account.beans.vo.MaWalletCheckVo;
import com.corner.account.beans.vo.MaWithDrawVo;
import com.corner.account.beans.vo.ManagerWithDrawVo;
import com.corner.account.config.SessionConfig;
import com.corner.account.dao.MaWDSheetMgMapper;
import com.corner.account.dao.MaWithDrawMgMapper;
import com.corner.account.service.MaWithDrawService;
import com.corner.account.utils.ExcelToll;
import com.corner.core.beans.AcActionRecord;
import com.corner.core.beans.Accounter;
import com.corner.core.beans.MaWDSheet;
import com.corner.core.beans.MaWDSheetMapKey;
import com.corner.core.beans.MaWithDraw;
import com.corner.core.beans.MaWithdrawDealLog;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.core.dao.AcActionRecordMapper;
import com.corner.core.dao.MaWDSheetMapper;
import com.corner.core.dao.MaWalletLogMapper;
import com.corner.core.dao.MaWithDrawMapper;
import com.corner.core.dao.MaWithdrawDealLogMapper;
import com.corner.core.utils.CreateOrderIdUtil;
import com.corner.core.utils.fastdfs.FastDFSFile;
import com.corner.core.utils.fastdfs.FileManager;
import com.corner.core.utils.json.JacksonUtil;

@Service
public class MaWithDrawServiceImpl extends BaseServiceImpl implements MaWithDrawService {

	private static Logger logger = LoggerFactory.getLogger(MaWithDrawServiceImpl.class);
	
	@Autowired
	MaWithDrawMapper maWithDrawMapper;
	@Autowired
	MaWithDrawMgMapper maWithDrawMgMapper;
	@Autowired
	MaWithdrawDealLogMapper maWithdrawDealLogMapper;
	@Autowired
	MaWalletLogMapper maWalletLogMapper;
	@Autowired
	AcActionRecordMapper acActionRecordMapper;
	@Autowired
	MaWDSheetMgMapper maWDSheetMgMapper;
	@Autowired
	MaWDSheetMapper maWDSheetMapper;

	@Override
	public Pager<ManagerWithDrawVo> getMaWithDrawList(MaWithDrawMgCondition command) {
		List<ManagerWithDrawVo> list=maWithDrawMgMapper.getMaWithDrawList(command);
		int size = maWithDrawMgMapper.getMaWithDrawListCount(command);
		return new Pager<ManagerWithDrawVo>(size, list);
	}

	@Override
	public Pager<MaWithDrawVo> getMaWithDrawDetailList(MaWithDrawMgCondition command) {
		List<MaWithDrawVo> list=maWithDrawMgMapper.getMaWithDrawDetailList(command);
		int size = maWithDrawMgMapper.getMaWithDrawDetailListCount(command);
		return new Pager<MaWithDrawVo>(size, list);
	}

	@Override
	public ModelMsg updateWithDrawStatus(MaWithDrawMgCondition command) {
		if(command ==null || command.getStatus()==null || StringUtils.isEmpty(command.getWithDrawIds())){
			return new ModelMsg(false, "参数错误");
		}else{
			Accounter current = (Accounter)SecurityUtils.getSubject().getSession().getAttribute(SessionConfig.USER_SESSION_KEY);
			if(current==null){
				return new ModelMsg(false,"请登录后进行操作");
			}
			//根据传入状态
			if(command.getStatus()==2){
				command.setCheckId(current.getId());
				command.setCheckTime(new Date());
				command.setCheckRemark(command.getUserRemark());
			}else if(command.getStatus()==3){
				command.setDenyId(current.getId());
				command.setDenyTime(new Date());
				command.setDenyRemark(command.getUserRemark());
			}else if(command.getStatus()==4){
				command.setPayerId(current.getId());
				command.setPayTime(new Date());
				command.setPayRemark(command.getUserRemark());
			}else if(command.getStatus()==5){
				command.setPayerId(current.getId());
				command.setPayTime(new Date());
				command.setPayRemark(command.getUserRemark());
			}else if(command.getStatus()==6){

			}else if(command.getStatus()==1){
				
			}else{
				return new ModelMsg(false,"状态参数不正确");
			}
			//修改状态
			String [] withDrawIdsArray = command.getWithDrawIds().split(",");
			command.setWithDrawIdsArray(withDrawIdsArray);
			int count = maWithDrawMgMapper.updateMaWithDrawBatch(command);
			
			//保存操作记录
			AcActionRecord acActionRecord =new AcActionRecord();
			acActionRecord.setUserId(current.getId());
			acActionRecord.setObjectName("提现审核：");
			acActionRecord.setActionDate(JacksonUtil.objectToJSONString(command));
			acActionRecord.setActionTime(new Date());
			acActionRecord.setActionType(2);
			acActionRecordMapper.insertSelective(acActionRecord);
			
			//保存日志记录
			for (int i = 0; i < withDrawIdsArray.length; i++) {
				MaWithdrawDealLog maWithdrawDealLog =new MaWithdrawDealLog();
				maWithdrawDealLog.setWithDrawId(Long.valueOf(withDrawIdsArray[i]));
				maWithdrawDealLog.setObjectStatus(Byte.valueOf(command.getStatus()+""));
				maWithdrawDealLog.setObjectDate(command.getUserRemark());
				maWithdrawDealLog.setDealerId(current.getId());
				maWithdrawDealLog.setDealerName(current.getUserName());
				maWithdrawDealLog.setDealTime(new Date());
				maWithdrawDealLogMapper.insertSelective(maWithdrawDealLog);
			}
			
			if (count > 0) {
				return new ModelMsg(true, "修改成功");
			} else {
				return new ModelMsg(false, "修改失败");
			}
		}
	}

	@Override
	public ModelMsg addDWSheet(MaWithDrawMgCondition command, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (command == null || StringUtils.isEmpty(command.getWithDrawIds())) {
			return new ModelMsg(false, "参数出错");
		}
		
		Accounter current = (Accounter)SecurityUtils.getSubject().getSession().getAttribute(SessionConfig.USER_SESSION_KEY);
		if(current==null){
			return new ModelMsg(false,"请登录后进行操作");
		}
		//把id转成数组
		command.setWithDrawIdsArray(command.getWithDrawIds().split(","));
		
		// 检查是否存在重复订单插入映射表
		command.setWithDrawIdsArray(command.getWithDrawIdsArray());
		List<MaWDSheetMapKey> listMapKey = maWDSheetMgMapper.checkSheetExit(command);
		if (listMapKey != null && listMapKey.size() > 0) {
			return new ModelMsg(false,"结算单重复");
		}
		
		//生成结算单编号
		String dwSheetNum =CreateOrderIdUtil.dateToString();
		
		// 生成excel表格
		MaWithDrawMgCondition ordercmd = new MaWithDrawMgCondition();
		ordercmd.setWithDrawIdsArray(command.getWithDrawIdsArray());
		ordercmd.setStatus(2);
		ordercmd.setSort("applyTime");
		ordercmd.setOrder("asc");
		ordercmd.setPageIndex(0);
		ordercmd.setPageSize(2000);
		List<MaWithDrawVo> list=maWithDrawMgMapper.getMaWithDrawDetailList(ordercmd);
		List<String> titles = new ArrayList<String>();// 表头
		titles.add("提现申请汇总结算表");
		titles.add("结算单编号："+dwSheetNum+"         时间区间："+command.getSheetRemark()+"      单位：元");
		LinkedHashMap<String, String> attrs = new LinkedHashMap<String, String>();//表身
		attrs.put("id", "提现申请编号");
		attrs.put("supplierName", "合作商名称");
		attrs.put("supplierCode", "合作商编号");
		attrs.put("amount", "申请提现金额");
		attrs.put("applyTime", "申请提现时间");
		attrs.put("checkTime", "会计审核时间");
		attrs.put("checkRemark", "会计审核备注");
		List<String> conclusions = new ArrayList<String>();// 表尾
		conclusions.add("制表：        财务复核：       客服复核：             渠道经理：            渠道总监审核：        总经理审批：      ");
		HSSFWorkbook workbook = ExcelToll.getMaWDSheetWorkBook(list, attrs, titles, conclusions);
		String uploadPath;
		try {
			ByteArrayOutputStream baout = new ByteArrayOutputStream();
			workbook.write(baout);
			FastDFSFile dfsFile = new FastDFSFile("acountWDSettleXml",baout.toByteArray(), "xls");
			uploadPath = FileManager.upload(dfsFile);
			baout.close();
			if(StringUtils.isEmpty(uploadPath)){
				logger.error("生成结算单失败");
				return new ModelMsg(false, "保存文件出错");
			}
		} catch (Exception e) {
			logger.error("生成结算单失败",e);
			return new ModelMsg(false, "保存文件出错");
		}

		// 结算单初始化数据
		MaWDSheet maWDSheet = new MaWDSheet();
		maWDSheet.setSheetName(command.getSheetName());
		maWDSheet.setSheetNum(dwSheetNum);
		maWDSheet.setCreateTime(new Date());
		maWDSheet.setCreateUser(current.getId());
		maWDSheet.setSheetRemark(command.getSheetRemark());
		maWDSheet.setXtype(command.getXtype().byteValue());
		//maWDSheet.setOrdId(command.getOrdid());
		maWDSheet.setSheetPath(uploadPath);

		// 最后插入结算单，插入映射表，修改订单状态
		int sheetcount = maWDSheetMapper.insertSelective(maWDSheet);
		//更新订单状态，插入map表
		command.setId(maWDSheet.getId());
		int count = maWDSheetMgMapper.addWDSheetMapBatch(command);
		logger.info("插入提现申请汇总单{}条，提现申请汇总单id:{},更新了和添加了{},实际数组id数{}", sheetcount, maWDSheet.getId(), count, command.getWithDrawIdsArray().length);
		
		//保存操作记录
		AcActionRecord acActionRecord =new AcActionRecord();
		acActionRecord.setUserId(current.getId());
		acActionRecord.setObjectName("生成提现申请汇总单：");
		acActionRecord.setActionDate(JacksonUtil.objectToJSONString(command));
		acActionRecord.setActionTime(new Date());
		acActionRecord.setActionType(2);
		acActionRecordMapper.insertSelective(acActionRecord);
		
		//保存订单状态改变记录
		//保存日志记录
		for (int i = 0; i < command.getWithDrawIdsArray().length; i++) {
			MaWithdrawDealLog maWithdrawDealLog =new MaWithdrawDealLog();
			maWithdrawDealLog.setWithDrawId(Long.valueOf(command.getWithDrawIdsArray()[i]));
			maWithdrawDealLog.setObjectStatus((byte)4);//由3到4
			maWithdrawDealLog.setObjectDate(command.getUserRemark());
			maWithdrawDealLog.setDealerId(current.getId());
			maWithdrawDealLog.setDealerName(current.getUserName());
			maWithdrawDealLog.setDealTime(new Date());
			maWithdrawDealLogMapper.insertSelective(maWithdrawDealLog);
		}
		
		//返回记录
		return new ModelMsg(true, "提现申请汇总单生成成功", maWDSheet);
	}

	@Override
	public Pager<MaWDSheet> getMaWDSheetList(MaWithDrawMgCondition command) {
		List<MaWDSheet> list = maWDSheetMgMapper.getPageList(command);
		int size = maWDSheetMgMapper.getPageListSize(command);
		return new Pager<MaWDSheet>(size, list);
	}
	
	@Override
	public ModelMsg updateWDsheetByCondition(MaWithDrawMgCondition command) {
		if(command != null 
				&& !StringUtils.isEmpty(command.getBankDealNo())
				&& !StringUtils.isEmpty(command.getId())){
			Accounter current = (Accounter)SecurityUtils.getSubject().getSession().getAttribute(SessionConfig.USER_SESSION_KEY);
			if(current==null){
				return new ModelMsg(false,"请登录后进行操作");
			}
			//抓取记录数
			List<MaWithDraw> listMaWithDraw=new ArrayList<MaWithDraw>();
			String[] ids = command.getId().split(",");
			if(ids ==null || ids.length < 1){
				return new ModelMsg(false,"主键不能为空");
			}else{
				for(int i=0 ; i< ids.length;i++){
					MaWithDraw maWithDrawTemp=maWithDrawMapper.selectByPrimaryKey(Long.valueOf(ids[i]));
					if(maWithDrawTemp==null){
						return new ModelMsg(false,"主键异常,没有获取相应记录");
					}else{
						listMaWithDraw.add(maWithDrawTemp);
					}
				}
				//校验长度
				if(listMaWithDraw.size() != ids.length){
					return new ModelMsg(false,"记录数不匹配");
				}
				//校验是不是同一个批发部的
				String lastSpid=listMaWithDraw.get(0).getManagerId();
				for (MaWithDraw maWithDraw : listMaWithDraw) {
					if(StringUtils.isEmpty(maWithDraw.getManagerId()) || !maWithDraw.getManagerId().equals(lastSpid)){
						return new ModelMsg(false,"请选择相同批发商的体现记录");
					}else{
						lastSpid=maWithDraw.getManagerId();
					}
				}
			}
			//
			int sumCount=0;
			int count =0;
			BigDecimal allmoney=new BigDecimal(0);
			for (MaWithDraw maWithDrawTmp : listMaWithDraw) {
				//插入回执记录
				allmoney=allmoney.add(maWithDrawTmp.getAmount());
				MaWithDraw maWithDraw =  new MaWithDraw();
				maWithDraw.setId(maWithDrawTmp.getId());//id
				maWithDraw.setPayTime(command.getPayTime());
				maWithDraw.setPayerId(current.getId());
				maWithDraw.setPayRemark(command.getPayRemark()+"(批量录入回执,总金额："+command.getPayment()+")");
				maWithDraw.setPayState((byte)1);
				maWithDraw.setBankDealNo(command.getBankDealNo());
				maWithDraw.setBankcode(command.getBankcode());
				maWithDraw.setPayment(maWithDrawTmp.getAmount());//默认全额付款
				maWithDraw.setTradeNo(command.getTradeNo());
				maWithDraw.setTradePlant(command.getTradePlant());
				maWithDraw.setStatus((byte)5);//设置已支付状态
				count =  maWithDrawMapper.updateByPrimaryKeySelective(maWithDraw);
				sumCount+=count;
			}
			//记录操作日志
			AcActionRecord acrecord =new  AcActionRecord();
			acrecord.setUserId(current.getId());
			acrecord.setObjectId(command.getMaId());
			acrecord.setObjectName("提现申请录入银行回执");
			acrecord.setActionDate(JacksonUtil.objectToJSONString(command));
			acrecord.setActionTime(new Date());
			acrecord.setActionType(2);
			acActionRecordMapper.insertSelective(acrecord);
			if(count>0 && sumCount == ids.length){
				return new ModelMsg(true, "录入成功,共计"+listMaWithDraw.size()+"条申请,总提现"+allmoney+"元");				
			}else{
				return new ModelMsg(false, "录入失败");					
			}
		}else{
			return new ModelMsg(false, "入参错误");	
		}
	}

	/**
	 * huo
	 */
	@Override
	public Pager<MaWalletCheckVo> getMaOneDayCheckList(MaWalletCheckMgCondition command) {
		if(command == null || command.getStartDay() == null || command.getEndDay() == null){
			return null;
		}
		List<MaWalletCheckVo> listAll = maWithDrawMgMapper.getMaWithDrawCheckList(command);
		if(listAll!=null && listAll.size()>0){
			return new Pager<MaWalletCheckVo>(listAll.size(), listAll) ;			
		}else{
			return null;
		}
	}
}

package com.corner.account.service.impl;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.corner.account.beans.ro.BillSheetCondition;
import com.corner.account.beans.ro.SpOrderMgCondition;
import com.corner.account.config.SessionConfig;
import com.corner.account.dao.AcSheetMgMapper;
import com.corner.account.dao.SpOrderInfoMgMapper;
import com.corner.account.service.AcSheetService;
import com.corner.account.utils.ExcelToll;
import com.corner.core.beans.AcActionRecord;
import com.corner.core.beans.AcSheet;
import com.corner.core.beans.AcSheetOrderMapKey;
import com.corner.core.beans.Accounter;
import com.corner.core.beans.SpOrderInfo;
import com.corner.core.beans.Supplier;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.core.dao.AcActionRecordMapper;
import com.corner.core.dao.AcSheetMapper;
import com.corner.core.dao.SpOrderInfoMapper;
import com.corner.core.dao.SupplierMapper;
import com.corner.core.utils.CreateOrderIdUtil;
import com.corner.core.utils.fastdfs.FastDFSFile;
import com.corner.core.utils.fastdfs.FileManager;
import com.corner.core.utils.json.JacksonUtil;

@Service
public class AcSheetServiceImpl implements AcSheetService {

	private static Logger logger = LoggerFactory.getLogger(AcSheetServiceImpl.class);

	@Autowired
	AcSheetMapper acSheetMapper;
	
	@Autowired
	AcActionRecordMapper acActionRecordMapper;

	@Autowired
	SupplierMapper supplierMapper;

	@Autowired
	AcSheetMgMapper acSheetMgMapper;

	@Autowired
	SpOrderInfoMapper spOrderInfoMapper;
	
	@Autowired
	SpOrderInfoMgMapper spOrderInfoMgMapper;

	@Override
	public Pager<AcSheet> getAcSheetList(BillSheetCondition command) {
		List<AcSheet> list = acSheetMgMapper.getPageList(command);
		int size = acSheetMgMapper.getPageListSize(command);
		return new Pager<AcSheet>(size, list);
	}

	@Override
	public ModelMsg addAcSheet(BillSheetCondition command, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (command == null || StringUtils.isEmpty(command.getSpOrderIds())) {
			return new ModelMsg(false, "参数出错");
		}
		
		Accounter current = (Accounter)SecurityUtils.getSubject().getSession().getAttribute(SessionConfig.USER_SESSION_KEY);
		if(current==null){
			return new ModelMsg(false,"请登录后进行操作");
		}
		
		// 检查是否存在重复订单插入映射表
		command.setSpOrderIdArray(command.getSpOrderIds().split(","));
		List<AcSheetOrderMapKey> list = acSheetMgMapper.checkSheetExit(command);
		if (list != null && list.size() > 0) {
			return new ModelMsg(false,"结算单重复");
		}
		
		//生成结算单编号
		String acSheetNum =CreateOrderIdUtil.dateToString();
		
		// 生成excel表格
		SpOrderMgCondition ordercmd = new SpOrderMgCondition();
		ordercmd.setSpOrderIdArray(command.getSpOrderIds().split(","));
		ordercmd.setKfStatus(2);
		ordercmd.setSpStatus(2);
		ordercmd.setAcStatus(2);
		ordercmd.setSort("orderId");
		ordercmd.setOrder("asc");
		ordercmd.setPageIndex(0);
		ordercmd.setPageSize(2000);
		List<SpOrderInfo> listOrder = spOrderInfoMgMapper.getSpOrderInfoByCondition(ordercmd);
		//int size = spOrderInfoMgMapper.getSpOrderInfoByConditionCount(ordercmd);
		Supplier sp = supplierMapper.selectByPrimaryKey(command.getSupplierId());
		List<String> titles = new ArrayList<String>();// 表头
		titles.add(sp.getSupplierName()+ "费用汇总表");
		titles.add("结算单编号："+acSheetNum+"         时间区间："+command.getSheetremark()+"    单位：元");
		LinkedHashMap<String, String> attrs = new LinkedHashMap<String, String>();// 表身
		attrs.put("orderid", "订单号");
		attrs.put("storename", "便利店全称");
		attrs.put("storeid", "便利店编码");
		attrs.put("acktime", "送达时间");
		attrs.put("orderprice", "交易金额");
		attrs.put("zfee", "费用金额");
		attrs.put("zfeeRate", "费用率");
		attrs.put("acremark", "备注");
		List<String> conclusions = new ArrayList<String>();// 表尾
		conclusions.add("制表：        财务复核：       客服复核：             渠道经理：            渠道总监审核：        总经理审批：      ");
		HSSFWorkbook workbook = ExcelToll.getWorkBook(listOrder, attrs, titles, conclusions);
		String uploadPath;
		try {
			ByteArrayOutputStream baout = new ByteArrayOutputStream();
			workbook.write(baout);
			FastDFSFile dfsFile = new FastDFSFile("acountSettleXml",baout.toByteArray(), "xls");
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
		AcSheet acSheet = new AcSheet();
		acSheet.setSheetName(command.getSheetname());
		acSheet.setSheetNum(acSheetNum);
		acSheet.setCreateTime(new Date());
		acSheet.setCreateUser(current.getId());
		acSheet.setSheetRemark(command.getSheetremark());
		acSheet.setXtype(command.getXtype().byteValue());
		acSheet.setOrdId(command.getOrdid());
		acSheet.setSheetPath(uploadPath);

		// 最后插入结算单，插入映射表，修改订单状态
		int sheetcount = acSheetMapper.insertSelective(acSheet);
		command.setId(acSheet.getId());
		int count = acSheetMgMapper.addOrderSheetMapBatch(command);
		logger.info("插入结算单{}条，结算单id:{},更新了和添加了{},实际数组id数{}", sheetcount, acSheet.getId(), count, command.getSpOrderIdArray().length);
		
		//保存操作记录
		AcActionRecord acActionRecord =new AcActionRecord();
		acActionRecord.setUserId(current.getId());
		acActionRecord.setObjectName("生成对账单：");
		acActionRecord.setActionDate(JacksonUtil.objectToJSONString(command));
		acActionRecord.setActionTime(new Date());
		acActionRecord.setActionType(2);
		acActionRecordMapper.insertSelective(acActionRecord);
		//返回记录
		return new ModelMsg(true, "结算单生成成功", acSheet);
	}

	@Override
	public ModelMsg updateAcsheetByCondition(BillSheetCondition command) {
		if(command != null 
				&& !StringUtils.isEmpty(command.getPaybanknum())
				&& !StringUtils.isEmpty(command.getId())){
			Accounter current = (Accounter)SecurityUtils.getSubject().getSession().getAttribute(SessionConfig.USER_SESSION_KEY);
			if(current==null){
				return new ModelMsg(false,"请登录后进行操作");
			}
			//插入回执记录
			AcSheet acSheet =  new AcSheet();
			acSheet.setId(command.getId());
			acSheet.setPayBankName(command.getPaybankname());
			acSheet.setPayBankNum(command.getPaybanknum());
			acSheet.setPayTime(command.getPaytime());
			acSheet.setFillTime(new Date());
			acSheet.setFillRemark(command.getFillremark());
			acSheet.setFillUserId(current.getId());
			acSheet.setStatus((byte)2);
			int count =  acSheetMapper.updateByPrimaryKeySelective(acSheet);
			
			//更新订单状态
			spOrderInfoMgMapper.updateSpOrderInfoBatchWithAcSheet(acSheet.getId());
			List<SpOrderInfo> listOrder = spOrderInfoMgMapper.getSpOrderInfoByAcSheetId(acSheet.getId());
			Map<String,Object> map =new HashMap<String,Object>();
			map.put("optType", 1);//订单费用
			map.put("spId", null);
			map.put("wdMoney", null);
			map.put("wdRemark", null);
			map.put("wdId", null);
			for (Iterator<SpOrderInfo> iterator = listOrder.iterator(); iterator.hasNext();) {
				SpOrderInfo spOrderInfo = iterator.next();
				map.put("orderId", spOrderInfo.getId());
				int success1 = spOrderInfoMgMapper.alertSpWallet(map);
				if(success1 != 1){
					logger.error("订单id:{} 订单号：{} 费用打入钱包异常",spOrderInfo.getId(),spOrderInfo.getOrderId());					
				}
			}
			
			//记录操作日志
			AcActionRecord acrecord =new  AcActionRecord();
			acrecord.setUserId(current.getId());
			acrecord.setObjectId(command.getId());
			acrecord.setObjectName("录入银行回执");
			acrecord.setActionDate(JacksonUtil.objectToJSONString(acSheet));
			acrecord.setActionTime(new Date());
			acrecord.setActionType(2);
			acActionRecordMapper.insertSelective(acrecord);
			if(count>0){
				return new ModelMsg(true, "录入成功");				
			}else{
				return new ModelMsg(false, "录入失败");					
			}
		}else{
			return new ModelMsg(false, "入参错误");	
		}
	}

}

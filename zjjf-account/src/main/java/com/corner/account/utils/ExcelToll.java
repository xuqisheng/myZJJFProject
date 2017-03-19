package com.corner.account.utils;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.util.CellRangeAddress;

import com.corner.account.beans.vo.MaWithDrawVo;
import com.corner.account.beans.vo.SpWithDrawVo;
import com.corner.account.beans.vo.WhWithDrawVo;
import com.corner.core.beans.SpOrderInfo;
import com.corner.core.utils.DateUtil;

public class ExcelToll {

	/**
	 * 
	 * @param date
	 *            实体类
	 * @param attrs
	 *            键：属性（全部小写），值：打印表头
	 * @param titles
	 *            标题
	 * @param conclusions
	 *            结语
	 * @return
	 * @throws IntrospectionException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	@SuppressWarnings("deprecation")
	public static HSSFWorkbook getWorkBook(List<SpOrderInfo> date, LinkedHashMap<String, String> attrs, List<String> titles, List<String> conclusions) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// 第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("结算单一");
		// 声明一个变量用来代表目前在第几行
		int i = 0;
		int cellnum = 0;
		// 设置一个居中样式
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFFont font = wb.createFont();
		font.setFontName("黑体");
		font.setFontHeightInPoints((short) 20);
		// 第三步，在sheet中添加标题
		for (String string : titles) {
			CellRangeAddress cra = new CellRangeAddress(i, i, 0, attrs.size() - 1);
			sheet.addMergedRegion(cra);
			HSSFRow row = sheet.createRow(i);
			row.setHeight((short) 600);
			Cell cell = row.createCell(0);
			RichTextString Rtext = new HSSFRichTextString(string);
			cell.setCellValue(Rtext);
			if (i == 0) {
				HSSFCellStyle titlestyle = wb.createCellStyle();
				titlestyle.setFont(font);
				titlestyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				cell.setCellStyle(titlestyle);
			} else {
				cell.setCellStyle(style);
			}
			++i;
		}

		
		// 第四步,设置表头
		cellnum = 0;
		HSSFRow row = sheet.createRow(i++);
		Set<String> set = attrs.keySet();
		for (String string : set) {
			Cell cell = row.createCell(cellnum);
			cell.setCellValue(attrs.get(string));
			cell.setCellStyle(style);
			++cellnum;
		}

		// 第五步,填充表信息
		cellnum = 0;
		//Map<String, Cell> cells = new HashMap<>();
		DecimalFormat df = new DecimalFormat("###0.000%");
		BigDecimal totalPrice=new BigDecimal(0);
		BigDecimal totalfee=new BigDecimal(0);
		for (Iterator<SpOrderInfo> iterator = date.iterator(); iterator.hasNext();) {
			SpOrderInfo spWithDrawVo =iterator.next();
			HSSFRow row1 = sheet.createRow(i++);
			row1.createCell(0).setCellValue(spWithDrawVo.getOrderId());
			row1.createCell(1).setCellValue(spWithDrawVo.getStoreName());
			row1.createCell(2).setCellValue(spWithDrawVo.getStoreId());
			row1.createCell(3).setCellValue(DateUtil.getSimpleDate(spWithDrawVo.getAckTime()));
			row1.createCell(4).setCellValue(spWithDrawVo.getOrderPrice().doubleValue());
			row1.createCell(5).setCellValue(spWithDrawVo.getZfee().doubleValue());
			if(spWithDrawVo.getOrderPrice().compareTo(new BigDecimal(0)) == 0 ){
				row1.createCell(6).setCellValue("0.000%");
			}else{
				BigDecimal result=spWithDrawVo.getZfee().divide(spWithDrawVo.getOrderPrice(),6,BigDecimal.ROUND_HALF_UP);
				row1.createCell(6).setCellValue(df.format(result));
			}
			row1.createCell(7).setCellValue(spWithDrawVo.getAcRemark());
			if(spWithDrawVo.getOrderPrice() == null){
				totalPrice=totalPrice.add(new BigDecimal(0));				
			}else{
				totalPrice=totalPrice.add(spWithDrawVo.getOrderPrice());
			}
			if(spWithDrawVo.getZfee() == null){
				totalfee=totalfee.add(new BigDecimal(0));				
			}else{
				totalfee=totalfee.add(spWithDrawVo.getZfee());
			}
		}

//		for (Object o : date) {
//			Class c = o.getClass();
//			Method[] m = c.getMethods();
//			HSSFRow row1 = sheet.createRow(i);
//			for (String string : set) {
//				Cell cell = row1.createCell(cellnum);
//				cells.put(string, cell);
//				++cellnum;
//			}
//			for (int j = 0; j < m.length; j++) {
//				if (attrs.get(m[j].getName().substring(3).toLowerCase()) != null && m[j].getName().substring(0, 3).equals("get")) {
//
//					try {
//						cells.get(m[j].getName().substring(3).toLowerCase()).setCellValue(m[j].invoke(o).toString());
//					} catch (NullPointerException e) {
//						cells.get(m[j].getName().substring(3).toLowerCase()).setCellValue("");
//					}
//					cells.get(m[j].getName().substring(3).toLowerCase()).setCellStyle(style);
//				}
//			}
//			cellnum = 0;
//			++i;
//		}
		//加入合计列
		CellRangeAddress craall = new CellRangeAddress(i, i, 0, attrs.size() - 1);
		sheet.addMergedRegion(craall);
		HSSFRow rowAll = sheet.createRow(i++);
		Cell cellall = rowAll.createCell(0);
		String alltest="交易金额合计:"+totalPrice+"      费用合计："+totalfee;
		RichTextString RtextAll = new HSSFRichTextString(alltest);
		cellall.setCellValue(RtextAll);
		cellall.setCellStyle(style);
		
		// 第六步，在sheet中添加表尾
		for (String string : conclusions) {
			CellRangeAddress cra = new CellRangeAddress(i, i, 0, attrs.size() - 1);
			sheet.addMergedRegion(cra);
			HSSFRow row1 = sheet.createRow(i);
			Cell cell = row1.createCell(0);
			RichTextString Rtext = new HSSFRichTextString(string);
			cell.setCellValue(Rtext);
			cell.setCellStyle(style);
			++i;
		}

		return wb;
	}
	
	@SuppressWarnings("deprecation")
	public static HSSFWorkbook getWDSheetWorkBook(List<SpWithDrawVo> date, LinkedHashMap<String, String> attrs, List<String> titles, List<String> conclusions) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// 第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("提现申请汇总表一");
		// 声明一个变量用来代表目前在第几行
		int i = 0;
		int cellnum = 0;
		// 设置一个居中样式
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFFont font = wb.createFont();
		font.setFontName("黑体");
		font.setFontHeightInPoints((short) 20);
		// 第三步，在sheet中添加标题
		for (String string : titles) {
			CellRangeAddress cra = new CellRangeAddress(i, i, 0, attrs.size() - 1);
			sheet.addMergedRegion(cra);
			HSSFRow row = sheet.createRow(i);
			row.setHeight((short) 600);
			Cell cell = row.createCell(0);
			RichTextString Rtext = new HSSFRichTextString(string);
			cell.setCellValue(Rtext);
			if (i == 0) {
				HSSFCellStyle titlestyle = wb.createCellStyle();
				titlestyle.setFont(font);
				titlestyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				cell.setCellStyle(titlestyle);
			} else {
				cell.setCellStyle(style);
			}
			++i;
		}

		
		// 第四步,设置表头
		cellnum = 0;
		HSSFRow row = sheet.createRow(i++);
		Set<String> set = attrs.keySet();
		for (String string : set) {
			Cell cell = row.createCell(cellnum);
			cell.setCellValue(attrs.get(string));
			cell.setCellStyle(style);
			++cellnum;
		}

		// 第五步,填充表信息
		cellnum = 0;
		//Map<String, Cell> cells = new HashMap<>();
		BigDecimal totalMoney=new BigDecimal(0);
		for (Iterator<SpWithDrawVo> iterator = date.iterator(); iterator.hasNext();) {
			SpWithDrawVo spWithDrawVo =iterator.next();
			HSSFRow row1 = sheet.createRow(i++);
			row1.createCell(0).setCellValue(spWithDrawVo.getId());//申请编号
			row1.createCell(1).setCellValue(spWithDrawVo.getSupplierName());//合作商名称
			row1.createCell(2).setCellValue(spWithDrawVo.getSupplierCode());//合作商编号
			row1.createCell(3).setCellValue(spWithDrawVo.getAmount().doubleValue());//申请提现金额
			row1.createCell(4).setCellValue(DateUtil.getSimpleDate(spWithDrawVo.getApplyTime()));//申请提现时间
			row1.createCell(5).setCellValue(DateUtil.getSimpleDate(spWithDrawVo.getCheckTime()));//会计审核时间
			row1.createCell(6).setCellValue(spWithDrawVo.getCheckRemark());//会计审核备注
			
			if(spWithDrawVo.getAmount() == null){
				totalMoney=totalMoney.add(new BigDecimal(0));				
			}else{
				totalMoney=totalMoney.add(spWithDrawVo.getAmount());
			}
		}

		//加入合计列
		CellRangeAddress craall = new CellRangeAddress(i, i, 0, attrs.size() - 1);
		sheet.addMergedRegion(craall);
		HSSFRow rowAll = sheet.createRow(i++);
		Cell cellall = rowAll.createCell(0);
		String alltest="总提现金额合计:"+totalMoney;
		RichTextString RtextAll = new HSSFRichTextString(alltest);
		cellall.setCellValue(RtextAll);
		cellall.setCellStyle(style);
		
		// 第六步，在sheet中添加表尾
		for (String string : conclusions) {
			CellRangeAddress cra = new CellRangeAddress(i, i, 0, attrs.size() - 1);
			sheet.addMergedRegion(cra);
			HSSFRow row1 = sheet.createRow(i);
			Cell cell = row1.createCell(0);
			RichTextString Rtext = new HSSFRichTextString(string);
			cell.setCellValue(Rtext);
			cell.setCellStyle(style);
			++i;
		}

		return wb;
	}
	
	
	@SuppressWarnings("deprecation")
	public static HSSFWorkbook getMaWDSheetWorkBook(List<MaWithDrawVo> date, LinkedHashMap<String, String> attrs, List<String> titles, List<String> conclusions) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// 第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("提现申请汇总表一");
		// 声明一个变量用来代表目前在第几行
		int i = 0;
		int cellnum = 0;
		// 设置一个居中样式
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFFont font = wb.createFont();
		font.setFontName("黑体");
		font.setFontHeightInPoints((short) 20);
		// 第三步，在sheet中添加标题
		for (String string : titles) {
			CellRangeAddress cra = new CellRangeAddress(i, i, 0, attrs.size() - 1);
			sheet.addMergedRegion(cra);
			HSSFRow row = sheet.createRow(i);
			row.setHeight((short) 600);
			Cell cell = row.createCell(0);
			RichTextString Rtext = new HSSFRichTextString(string);
			cell.setCellValue(Rtext);
			if (i == 0) {
				HSSFCellStyle titlestyle = wb.createCellStyle();
				titlestyle.setFont(font);
				titlestyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				cell.setCellStyle(titlestyle);
			} else {
				cell.setCellStyle(style);
			}
			++i;
		}

		
		// 第四步,设置表头
		cellnum = 0;
		HSSFRow row = sheet.createRow(i++);
		Set<String> set = attrs.keySet();
		for (String string : set) {
			Cell cell = row.createCell(cellnum);
			cell.setCellValue(attrs.get(string));
			cell.setCellStyle(style);
			++cellnum;
		}

		// 第五步,填充表信息
		cellnum = 0;
		//Map<String, Cell> cells = new HashMap<>();
		BigDecimal totalMoney=new BigDecimal(0);
		for (Iterator<MaWithDrawVo> iterator = date.iterator(); iterator.hasNext();) {
			MaWithDrawVo spWithDrawVo =iterator.next();
			HSSFRow row1 = sheet.createRow(i++);
			row1.createCell(0).setCellValue(spWithDrawVo.getId());//申请编号
			row1.createCell(1).setCellValue(spWithDrawVo.getManagerName());//合作商名称
			row1.createCell(2).setCellValue(spWithDrawVo.getManagerCode());//合作商编号
			row1.createCell(3).setCellValue(spWithDrawVo.getAmount().doubleValue());//申请提现金额
			row1.createCell(4).setCellValue(DateUtil.getSimpleDate(spWithDrawVo.getApplyTime()));//申请提现时间
			row1.createCell(5).setCellValue(DateUtil.getSimpleDate(spWithDrawVo.getCheckTime()));//会计审核时间
			row1.createCell(6).setCellValue(spWithDrawVo.getCheckRemark());//会计审核备注
			
			if(spWithDrawVo.getAmount() == null){
				totalMoney=totalMoney.add(new BigDecimal(0));				
			}else{
				totalMoney=totalMoney.add(spWithDrawVo.getAmount());
			}
		}

		//加入合计列
		CellRangeAddress craall = new CellRangeAddress(i, i, 0, attrs.size() - 1);
		sheet.addMergedRegion(craall);
		HSSFRow rowAll = sheet.createRow(i++);
		Cell cellall = rowAll.createCell(0);
		String alltest="总提现金额合计:"+totalMoney;
		RichTextString RtextAll = new HSSFRichTextString(alltest);
		cellall.setCellValue(RtextAll);
		cellall.setCellStyle(style);
		
		// 第六步，在sheet中添加表尾
		for (String string : conclusions) {
			CellRangeAddress cra = new CellRangeAddress(i, i, 0, attrs.size() - 1);
			sheet.addMergedRegion(cra);
			HSSFRow row1 = sheet.createRow(i);
			Cell cell = row1.createCell(0);
			RichTextString Rtext = new HSSFRichTextString(string);
			cell.setCellValue(Rtext);
			cell.setCellStyle(style);
			++i;
		}

		return wb;
	}
	
	
	@SuppressWarnings("deprecation")
	public static HSSFWorkbook getWhWDSheetWorkBook(List<WhWithDrawVo> date, LinkedHashMap<String, String> attrs, List<String> titles, List<String> conclusions) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// 第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("提现申请汇总表一");
		// 声明一个变量用来代表目前在第几行
		int i = 0;
		int cellnum = 0;
		// 设置一个居中样式
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFFont font = wb.createFont();
		font.setFontName("黑体");
		font.setFontHeightInPoints((short) 20);
		// 第三步，在sheet中添加标题
		for (String string : titles) {
			CellRangeAddress cra = new CellRangeAddress(i, i, 0, attrs.size() - 1);
			sheet.addMergedRegion(cra);
			HSSFRow row = sheet.createRow(i);
			row.setHeight((short) 600);
			Cell cell = row.createCell(0);
			RichTextString Rtext = new HSSFRichTextString(string);
			cell.setCellValue(Rtext);
			if (i == 0) {
				HSSFCellStyle titlestyle = wb.createCellStyle();
				titlestyle.setFont(font);
				titlestyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				cell.setCellStyle(titlestyle);
			} else {
				cell.setCellStyle(style);
			}
			++i;
		}

		
		// 第四步,设置表头
		cellnum = 0;
		HSSFRow row = sheet.createRow(i++);
		Set<String> set = attrs.keySet();
		for (String string : set) {
			Cell cell = row.createCell(cellnum);
			cell.setCellValue(attrs.get(string));
			cell.setCellStyle(style);
			++cellnum;
		}

		// 第五步,填充表信息
		cellnum = 0;
		//Map<String, Cell> cells = new HashMap<>();
		BigDecimal totalMoney=new BigDecimal(0);
		for (Iterator<WhWithDrawVo> iterator = date.iterator(); iterator.hasNext();) {
			WhWithDrawVo spWithDrawVo =iterator.next();
			HSSFRow row1 = sheet.createRow(i++);
			row1.createCell(0).setCellValue(spWithDrawVo.getId());//申请编号
			row1.createCell(1).setCellValue(spWithDrawVo.getName());//合作商名称
			row1.createCell(2).setCellValue(spWithDrawVo.getHouseCode());//合作商编号
			row1.createCell(3).setCellValue(spWithDrawVo.getAmount().doubleValue());//申请提现金额
			row1.createCell(4).setCellValue(DateUtil.getSimpleDate(spWithDrawVo.getApplyTime()));//申请提现时间
			row1.createCell(5).setCellValue(DateUtil.getSimpleDate(spWithDrawVo.getCheckTime()));//会计审核时间
			row1.createCell(6).setCellValue(spWithDrawVo.getCheckRemark());//会计审核备注
			
			if(spWithDrawVo.getAmount() == null){
				totalMoney=totalMoney.add(new BigDecimal(0));				
			}else{
				totalMoney=totalMoney.add(spWithDrawVo.getAmount());
			}
		}

		//加入合计列
		CellRangeAddress craall = new CellRangeAddress(i, i, 0, attrs.size() - 1);
		sheet.addMergedRegion(craall);
		HSSFRow rowAll = sheet.createRow(i++);
		Cell cellall = rowAll.createCell(0);
		String alltest="总提现金额合计:"+totalMoney;
		RichTextString RtextAll = new HSSFRichTextString(alltest);
		cellall.setCellValue(RtextAll);
		cellall.setCellStyle(style);
		
		// 第六步，在sheet中添加表尾
		for (String string : conclusions) {
			CellRangeAddress cra = new CellRangeAddress(i, i, 0, attrs.size() - 1);
			sheet.addMergedRegion(cra);
			HSSFRow row1 = sheet.createRow(i);
			Cell cell = row1.createCell(0);
			RichTextString Rtext = new HSSFRichTextString(string);
			cell.setCellValue(Rtext);
			cell.setCellStyle(style);
			++i;
		}

		return wb;
	}
}

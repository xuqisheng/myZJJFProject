package com.corner.kefu.utils;

import java.beans.IntrospectionException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.stereotype.Service;

import com.corner.core.beans.SpOrderDetail;
import com.corner.core.beans.SpOrderInfo;
import com.corner.kefu.beans.vo.sp.SpOrderInfoVo;



@Service
public class ExportExcel {
	public Boolean exportExcel(Map<String, Object> dataset, String filladdress) {
		return exportExcel("测试POI导出EXCEL文档", null, dataset, "yyyy-MM-dd", filladdress);
	}

	public Boolean exportExcel(String[] headers, Map<String, Object> dataset, String filladdress) {
		return exportExcel("测试POI导出EXCEL文档", headers, dataset, "yyyy-MM-dd", filladdress);
	}

	public Boolean exportExcel(String[] headers, Map<String, Object> dataset, String pattern, String filladdress) {
		return exportExcel("测试POI导出EXCEL文档", headers, dataset, pattern, filladdress);
	}
	
	public HSSFWorkbook exportExceld(List date,LinkedHashMap<String,String> attrs,List<String> titles,List<String> conclusions)throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		return getWorkBook(date,attrs,titles,conclusions);
	}

	/**
	 * 这是一个通用的方法，利用了JAVA的反射机制，可以将放置在JAVA集合中并且符号一定条件的数据以EXCEL 的形式输出到指定IO设备上
	 * 
	 * @param title
	 *            表格标题名
	 * @param headers
	 *            表格属性列名数组
	 * @param dataset
	 *            需要显示的数据集合,集合中一定要放置符合javabean风格的类的对象。此方法支持的
	 *            javabean属性的数据类型有基本数据类型及String,Date,byte[](图片数据)
	 * @param out
	 *            与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
	 * @param pattern
	 *            如果有时间数据，设定输出格式。默认为"yyy-MM-dd"
	 */
	@SuppressWarnings("unchecked")
	public Boolean exportExcel(String title, String[] headers, Map<String, Object> dataset, String pattern, String filladdress) {
		HSSFWorkbook workbook = hasSheet(filladdress);
		Integer sheetIndex = 0;
		// 声明一个工作薄
		if (workbook == null) {
			workbook = new HSSFWorkbook();

		} else {
			sheetIndex = workbook.getNumberOfSheets();
		}

		System.out.println(sheetIndex);
		// 生成一个表格
		HSSFSheet sheet = workbook.createSheet();
		SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd"); 
		
		workbook.setSheetName(sheetIndex,time.format(new Date())+""+sheetIndex);
		/* workbook.setSheetName(sheetIx, name); */
		// 设置表格默认列宽度为15个字节
		sheet.setDefaultColumnWidth((short) 15);

		// 生成一个样式
		HSSFCellStyle style = workbook.createCellStyle();
		// 设置这些样式
		style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 生成一个字体
		HSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.VIOLET.index);
		font.setFontHeightInPoints((short) 12);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 把字体应用到当前的样式
		style.setFont(font);
		// 生成并设置另一个样式
		HSSFCellStyle style2 = workbook.createCellStyle();
		style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
		style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 生成另一个字体
		HSSFFont font2 = workbook.createFont();
		font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		// 把字体应用到当前的样式
		style2.setFont(font2);

		// 声明一个画图的顶级管理器
		HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
		// 定义注释的大小和位置,详见文档
		HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0, 0, 0, 0, (short) 4, 2, (short) 6, 5));
		// 设置注释内容
		/* comment.setString(new HSSFRichTextString("可以在POI中添加注释！")); */
		// 设置注释作者，当鼠标移动到单元格上是可以在状态栏中看到该内容.
		// 产生表格标题行
		/*
		 * HSSFRow row = sheet.createRow(0); for (short i = 0; i <
		 * headers.length; i++) { HSSFCell cell = row.createCell(i);
		 * cell.setCellStyle(style); HSSFRichTextString text = new
		 * HSSFRichTextString(headers[i]); cell.setCellValue(text); }
		 */
		HSSFRow row = null;
		try {
			SpOrderInfo o = (SpOrderInfo) dataset.get("orderInfo");
			row = sheet.createRow(0);
			this.fillValue(row, "配送店主", o.getUserName(), "手机号码", o.getMobile(), "配送时间", o.getSendDate(), style2);
			row = sheet.createRow(1);
			this.fillValue(row, "店铺名称", o.getStoreName(), "支付方式", o.getSupportmetho(), "订单编号", o.getOrderId(), style2);
			row = sheet.createRow(2);
			this.fillValue(row, "店铺地址", o.getAddress(), "备注信息", o.getUserRemark(), "", null, style2);
			row = sheet.createRow(4);
			List<String> listHeader = new ArrayList<String>();
			listHeader.add("商品条码");
			listHeader.add("商品名称");
			listHeader.add("规格");
			listHeader.add("数量");
			listHeader.add("单价");
			listHeader.add("总金额");

			filltableheader(row, style, listHeader);
			List<SpOrderDetail> list = (List<SpOrderDetail>) dataset.get("orderDetail");
			row = sheet.createRow(5);
			filltable(list, row, style2);

			row = sheet.createRow(6);
			fillfoot(row, style2, "", dataset.get("total").toString(), dataset.get("ordernum").toString());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
		try {
			OutputStream out = new FileOutputStream(filladdress);
			workbook.write(out);
			out.close();
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	private void fillfoot(HSSFRow row, HSSFCellStyle style, String v1, String v2, String v3) {
		this.fillTitle(row, "金额合计", 0, style);
		HSSFCell c1 = row.createCell(1);
		c1.setCellValue(v1);

		this.fillTitle(row, "总数量", 3, style);
		c1 = row.createCell(4);
		c1.setCellValue(v2);

		this.fillTitle(row, "小写", 5, style);
		c1 = row.createCell(5);
		c1.setCellValue(v3);
	}

	private void filltableheader(HSSFRow row, HSSFCellStyle style, List<String> list) {
		for (int i = 0; i < list.size(); i++) {
			HSSFCell c1 = row.createCell(i);
			c1.setCellValue(list.get(i));
			c1.setCellStyle(style);
		}
	}

	private void filltable(List<SpOrderDetail> list, HSSFRow row, HSSFCellStyle style) {
		for (SpOrderDetail sd : list) {
			HSSFCell c1 = row.createCell(0);
			c1.setCellValue(sd.getBarCode());
			c1.setCellStyle(style);

			HSSFCell c2 = row.createCell(1);
			c2.setCellValue(sd.getName());
			c2.setCellStyle(style);

			HSSFCell c3 = row.createCell(2);
			c3.setCellValue(sd.getSpec());
			c3.setCellStyle(style);

			HSSFCell c4 = row.createCell(3);
			c4.setCellValue(sd.getQuantity());
			c4.setCellStyle(style);

			HSSFCell c5 = row.createCell(4);
			c5.setCellValue(sd.getPrice().toString());
			c5.setCellStyle(style);

			HSSFCell c6 = row.createCell(5);
			c6.setCellValue(sd.getTotalPrice().toString());
			c6.setCellStyle(style);
		}
	}

	private void fillValue(HSSFRow row, String name1, Object va1, String name2, Object va2, String name3, Object va3, HSSFCellStyle style2) {
		this.fillTitle(row, name1, 0, style2);
		HSSFCell c1 = row.createCell(1);
		HSSFRichTextString v1 = new HSSFRichTextString(this.isnull(va1).toString());
		c1.setCellValue(v1);

		this.fillTitle(row, name2, 3, style2);
		HSSFCell c2 = row.createCell(4);
		HSSFRichTextString v2 = new HSSFRichTextString(this.isnull(va2).toString());
		c2.setCellValue(v2);

		this.fillTitle(row, name3, 6, style2);
		HSSFCell cell2 = row.createCell(7);
		HSSFRichTextString v3 = new HSSFRichTextString(this.isnull(va3).toString());
		cell2.setCellValue(v3);
	}

	private void fillTitle(HSSFRow row, String name, Integer columIndex, HSSFCellStyle style2) {
		HSSFCell c0 = row.createCell(columIndex);
		HSSFRichTextString v1 = new HSSFRichTextString(name);
		c0.setCellValue(v1);

		/* c0.setCellStyle(style2); */
	}

	
	private HSSFWorkbook hasSheet(String fileaddress) {
		HSSFWorkbook wb = null;
		POIFSFileSystem fs = null;
		try {
			File f = new File(fileaddress);
			FileInputStream fileinput = new FileInputStream(f);
			// 设置要读取的文件路径
			fs = new POIFSFileSystem(fileinput);
			fileinput.close();
			// HSSFWorkbook相当于一个excel文件，HSSFWorkbook是解析excel2007之前的版本（xls）
			// 之后版本使用XSSFWorkbook（xlsx）
			wb = new HSSFWorkbook(fs);
			return wb;
		} catch (Exception e) {
			System.out.println(e);
			return wb;
		}
	}

	private HSSFCellStyle createCellStyle(HSSFWorkbook workbook) {
		HSSFCellStyle style = workbook.createCellStyle();
		HSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.VIOLET.index);
		font.setFontHeightInPoints((short) 12);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 把字体应用到当前的样式
		style.setFont(font);
		return style;
	}

	private Object isnull(Object obj) {
		if (obj == null) {
			return "";
		} else {
			return obj;
		}
	}
	/**
	 * 
	 * @param date 实体类
	 * @param attrs 键：属性（全部小写），值：打印表头
	 * @param titles 标题
	 * @param conclusions 结语
	 * @return
	 * @throws IntrospectionException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	@SuppressWarnings("unchecked")
	public static HSSFWorkbook getWorkBook(List date,LinkedHashMap<String,String> attrs,List<String> titles,List<String> conclusions ) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		for (Object string : date) {
			System.out.println(((SpOrderInfoVo)string).getStoreName());
		}
		// 第一步，创建一个webbook，对应一个Excel文件  
        HSSFWorkbook wb = new HSSFWorkbook();  
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
        HSSFSheet sheet = wb.createSheet("学生表一");  
        //声明一个变量用来代表目前在第几行
        int i=0;
        //设置一个居中样式
        HSSFCellStyle style = wb.createCellStyle();  
	    style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFFont font=wb.createFont();  
		font.setFontName("黑体");
		font.setFontHeightInPoints((short) 20);
        // 第三步，在sheet中添加标题 
        for (String string : titles) {
        	CellRangeAddress cra=new CellRangeAddress(i, i, 0,attrs.size()-1 );
        	sheet.addMergedRegion(cra);
			HSSFRow row =sheet.createRow(i);
			row.setHeight((short) 600);
			Cell cell = row.createCell(0);
			RichTextString Rtext = new HSSFRichTextString(string);
			cell.setCellValue(Rtext);
			if(i==0){
			HSSFCellStyle titlestyle =wb.createCellStyle();
			titlestyle.setFont(font);
			titlestyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			cell.setCellStyle(titlestyle);
			}else{
				cell.setCellStyle(style);
			}
			++i;
		}

		int cellnum=0;
        //第四步,设置表头
        HSSFRow row=sheet.createRow(i);
        ++i;
        Set<String> set=attrs.keySet();
        
        for (String string : set) {
        	Cell cell=row.createCell(cellnum);
			cell.setCellValue(attrs.get(string));
		    cell.setCellStyle(style);
		    ++cellnum;
		}
        
        //第五步,填充表信息
        cellnum=0;
        Map<String, Cell> cells=new HashMap<>();
       
        for (Object o : date) {
        Class c=o.getClass();
		Method[] m=c.getMethods();
		HSSFRow row1=sheet.createRow(i);
		 for (String string : set) {
	        	Cell cell=row1.createCell(cellnum);
	        	cells.put(string, cell);
			    ++cellnum;
			}
	    for (int j = 0; j < m.length; j++) {
			if(attrs.get(m[j].getName().substring(3).toLowerCase())!=null && m[j].getName().substring(0,3).equals("get")){
				
				try{
					cells.get(m[j].getName().substring(3).toLowerCase()).setCellValue(m[j].invoke(o).toString());
				}catch(NullPointerException e){
					cells.get(m[j].getName().substring(3).toLowerCase()).setCellValue("");
				}
				cells.get(m[j].getName().substring(3).toLowerCase()).setCellStyle(style);
			}
		}
	    cellnum=0;
			++i;
		}
    	// 第六步，在sheet中添加表尾
        for (String string : conclusions) {
        	CellRangeAddress cra=new CellRangeAddress(i, i, 0,attrs.size()-1 );
        	sheet.addMergedRegion(cra);
			HSSFRow row1 =sheet.createRow(i);
			Cell cell = row1.createCell(0);
			RichTextString Rtext = new HSSFRichTextString(string);
			cell.setCellValue(Rtext);
		    cell.setCellStyle(style);
			++i;
		}
       
		return wb;
	}
}

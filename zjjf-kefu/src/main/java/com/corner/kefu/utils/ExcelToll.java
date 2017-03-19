package com.corner.kefu.utils;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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

import com.corner.kefu.beans.vo.CheckBillVo;

public class ExcelToll {
		
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
		@SuppressWarnings("deprecation")
		public static HSSFWorkbook getWorkBook(List date,LinkedHashMap<String,String> attrs,List<String> titles,List<String> conclusions ) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
			for (Object string : date) {
				System.out.println(((CheckBillVo)string).getSortName());
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

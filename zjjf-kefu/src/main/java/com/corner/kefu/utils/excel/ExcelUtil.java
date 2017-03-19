/**   
* @Title: ExcelUtil.java 
* @Package com.corner.kefu.utils.excel 
* @Description:
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年5月29日 下午8:22:06 
* @version V1.0   
*/

package com.corner.kefu.utils.excel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/** 
* @ClassName: ExcelUtil 
* @Description:Excel处理工具类
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年5月29日 下午8:22:06 
*  
*/

public class ExcelUtil {

	/**
	 * @throws ExcelProcessException 
	 * 
	* @Title: getWorkBook 
	* @Description: 获取指定的excel工作簿
	* @param @param fileName 文件名称
	* @return Workbook    工作簿对象
	* @author 杨开泰   yangkaitai@izjjf.cn
	* @throws
	 */
	public static final Workbook  getWorkBook(String fileName) throws ExcelProcessException {
		Workbook workbook = null;
		if(!fileName.toLowerCase().endsWith(".xls") && !fileName.toLowerCase().endsWith(".xlsx")){//通过后缀名校验excel格式
			throw new ExcelProcessException("导入失败,只能导入EXCEL文件!");
		}else {
			try {
				InputStream in = new FileInputStream(fileName);
				if(fileName.toLowerCase().endsWith(".xls")){
					workbook = new HSSFWorkbook(in);
				}else if(fileName.toLowerCase().endsWith(".xlsx")){
					workbook = new XSSFWorkbook(in);
				}
			} catch (FileNotFoundException e) {
				throw new ExcelProcessException("找不到该文件!");
			} catch (IOException e) {
				throw new ExcelProcessException("处理excel文件时发生IO异常!");
			}
		}
		return workbook;
	}
}

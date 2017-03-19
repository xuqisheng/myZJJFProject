/**   
* @Title: SendSpVoucherParseExcelUtil.java 
* @Package com.corner.kefu.utils.excel 
* @Description:
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年5月29日 下午8:19:38 
* @version V1.0   
*/

package com.corner.kefu.utils.excel;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.corner.core.utils.StringUtil;

/** 
* @ClassName: SendSpVoucherParseExcelUtil 
* @Description: 客服后台发送优惠劵读取excel工具类
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年5月29日 下午8:19:38 
*  
*/

public class SendSpVoucherParseExcelUtil {

	private static Logger logger = LoggerFactory.getLogger(SendSpVoucherParseExcelUtil.class);

	private static Workbook workbook;
	private static List<String> storeIdList = new ArrayList<String>();
	private static Sheet sheet;

	public static final List<String> readExcel(String fileName) throws FileNotFoundException, ExcelProcessException {
		if (StringUtil.stringIsNullOrEmpty(fileName)) {
			throw new FileNotFoundException("找不到文件请重试!");
		}
		workbook = ExcelUtil.getWorkBook(fileName);
		readExcelData();
		return storeIdList;
	}

	/**
	 * @throws ExcelProcessException 
	 * 
	* @Title: readExcelData 
	* @Description:开始从excel读取数据
	* @param     设定文件 
	* @return void    返回类型 
	* @author 杨开泰   yangkaitai@izjjf.cn
	* @throws
	 */
	private static void readExcelData() throws ExcelProcessException {
		int sheetSize = workbook.getNumberOfSheets();
		for (int i = 0; i < sheetSize; i++) {
			sheet = workbook.getSheetAt(i);
			//checkSheet(sheet);//判断sheet是否为空
			readSheetData(sheet);//读取sheet数据
		}
	}

	/**
	 * 
	* @Title: readSheetData 
	* @Description:读取每个sheet页数据 
	* @param @param sheet    sheet对象 
	* @return void    返回类型 
	* @author 杨开泰   yangkaitai@izjjf.cn
	* @throws
	 */
	private static void readSheetData(Sheet sheet) {
		// 客服后台发送优惠劵的excel模板默认就只有1列storeId,并且没有表头,所以只要读取每个sheet页的第一列就可以了
		if(sheet.getPhysicalNumberOfRows() > 0){
			int lastRowNum = sheet.getLastRowNum();// sheet 页的最后最后一行
			for (int i = 0; i <=lastRowNum; i++) {
	          Row row = sheet.getRow(i);//获取行
	          if(row!=null){
	        	  //客服后台发送优惠劵解析excel 默认只读取第一列
	        	  Cell cell = row.getCell(0);
	        	  cell.setCellType(Cell.CELL_TYPE_STRING);//设置单元格为String类型
	        	  String storeIdStr = cell.getStringCellValue();//将数据读取成String类型
	        	  if(!StringUtils.isBlank(storeIdStr)){
	        		  //TODO 正则表达式
	        		  storeIdList.add(StringUtils.strip(storeIdStr));
	        	  }
	          }
			}
		}
	}

	/**
	 * @throws ExcelProcessException 
	 * 
	* @Title: checkSheet 
	* @Description:验证sheet 
	* @param @param sheet    设定文件 
	* @return void    返回类型 
	* @author 杨开泰   yangkaitai@izjjf.cn
	* @throws
	 */
	private static void checkSheet(Sheet sheet) throws ExcelProcessException {
		if (sheet == null || sheet.getPhysicalNumberOfRows() <= 0) {
			logger.error("▇▇▇▇▇▇▇▇" + sheet.getSheetName() + "内容为空!");
			throw new ExcelProcessException(sheet.getSheetName() + "内容为空");
		}
	}
}

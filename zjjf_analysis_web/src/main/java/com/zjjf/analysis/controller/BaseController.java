package com.zjjf.analysis.controller;

import com.zjjf.analysis.common.constants.SessionConfig;
import org.apache.poi.hssf.usermodel.*;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

@Controller
public class BaseController {

	private static Logger logger = LoggerFactory.getLogger(BaseController.class);

	/****************************************************************************************************************
	 * 获取当前sesseion
	 * 
	 * @param t
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T getCurrentUser(Class<T> t, HttpServletRequest request) {
		logger.debug("enter in getCurrentUser function");
		Object object = SecurityUtils.getSubject().getSession().getAttribute(SessionConfig.user_session_key);
		if (object != null) {
			return (T) object;
		}
		return null;
	}

	/**
	 * 将request的参数转换成map
	 * 
	 * @param request
	 * @return
	 */
	public HashMap<String, Object> getQueryMap(HttpServletRequest request, List<String> keyList) {
		// 参数Map
		Map<String, String[]> properties = request.getParameterMap();
		// 返回值Map
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		Iterator<Entry<String, String[]>> entries = properties.entrySet().iterator();
		Map.Entry<String, String[]> entry;
		String name = "";
		String value = "";
		try {
			while (entries.hasNext()) {
				entry = entries.next();
				name = entry.getKey();
				Object valueObj = entry.getValue();
				if (valueObj instanceof String[]) {
					String[] values = (String[]) valueObj;
					for (int i = 0; i < values.length; i++) {
						value = values[i] + ",";
					}
					value = new String(value.getBytes("ISO8859-1"), "UTF-8");
					value = value.substring(0, value.length() - 1);
				} else {
					value = valueObj.toString();
				}
				if (keyList.contains(name)) {
					returnMap.put(name, value);
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return returnMap;
	}

	/**
	 * 第一步，创建一个webbook，对应一个Excel文件 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
	 * 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short 第四步，创建单元格，并设置值表头 设置表头居中
	 * 
	 * @param sheetName
	 * @param titleColumn
	 * @param dataList
	 * @return
	 */
	@SuppressWarnings({ "resource" })
	public InputStream createExcel(String sheetName, Object[] titleColumn, List<Object[]> dataList) {

		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet(sheetName);
		HSSFRow row = sheet.createRow((int) 0);
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		sheet.setDefaultRowHeightInPoints(10);
		sheet.setDefaultColumnWidth(10);
		sheet.setColumnWidth(4, 20 * 256);
		sheet.setColumnWidth(5, 30 * 256);
		sheet.setColumnWidth(6, 30 * 256);

		for (int i = 0; i < titleColumn.length; i++) {
			String titleName = "" + titleColumn[i];
			HSSFCell cell = row.createCell(i);
			cell.setCellValue(titleName);
			cell.setCellStyle(style);
		}
		for (int i = 0; i < dataList.size(); i++) {
			Object[] rowMap = dataList.get(i);
			row = sheet.createRow(i + 1);
			for (int j = 0; j < rowMap.length; j++) {
				Object value = rowMap[j];
				row.createCell(j).setCellValue(value + "");
			}
		}
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			wb.write(os);
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] content = os.toByteArray();
		InputStream is = new ByteArrayInputStream(content);
		return is;
	}
}

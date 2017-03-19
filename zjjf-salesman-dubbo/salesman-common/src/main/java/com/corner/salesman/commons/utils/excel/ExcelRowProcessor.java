package com.corner.salesman.commons.utils.excel;

import java.io.IOException;

/**
 * 接口，Excel行级处理器
 * @author zhangchaofeng
 * @version 1.0
 * @date Sep 27, 2011
 */
public interface ExcelRowProcessor{
	public void processByRow() throws Exception;
	public void processByRow(int sheetIndex) throws Exception;
	public void processRow(XRow row);
	public void stop() throws IOException;
}

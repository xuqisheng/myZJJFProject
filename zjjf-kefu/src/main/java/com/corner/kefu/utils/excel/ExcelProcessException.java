/**   
* @Title: ExcelProcessException.java 
* @Package com.corner.kefu.utils.excel 
* @Description:
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年5月29日 下午8:25:47 
* @version V1.0   
*/

package com.corner.kefu.utils.excel;

/** 
* @ClassName: ExcelProcessException 
* @Description: Excel异常处理类
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年5月29日 下午8:25:47 
*  
*/

public class ExcelProcessException extends Exception {

	private static final long serialVersionUID = 6637659210249956599L;

	public ExcelProcessException() {
		super();
	}

	public ExcelProcessException(String message, Throwable cause) {
		super(message, cause);
	}

	public ExcelProcessException(String message) {
		super(message);
	}

	public ExcelProcessException(Throwable cause) {
		super(cause);
	}

}

package com.zjjf.analysis.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import jxl.CellView;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class CreateExcelUtils {

	/**
	 * 在指定目录下创建Excel文件;
	 * 
	 * @param path
	 * @throws IOException
	 * @throws WriteException
	 * @throws RowsExceededException
	 */
	public static void createExcelFile(String fileName, String path, Object[] titleColumn, List<Object[]> dataList, Integer index)
			throws IOException, RowsExceededException, WriteException {

		String filePath = path + "/" + new SimpleDateFormat("yyyyMMddHH").format(new Date()) + fileName +  "_" + index + ".xls";
		WritableWorkbook workbook = Workbook.createWorkbook(new File(filePath));
		WritableSheet sheet = workbook.createSheet("导出Excel文件", 0);
		//sheet.getSettings().setDefaultColumnWidth(30);
		CellView cellView = new CellView();  
		cellView.setAutosize(true); //设置自动大小  
		WritableCellFormat writableTitle = createTileCell();
		for (int m = 0; m < titleColumn.length; m++) {
			String titleName = "" + titleColumn[m];
			Label cellTitle = new Label(m, 0, titleName, writableTitle);
			sheet.setColumnView(m, cellView);//根据内容自动设置列宽  
			sheet.addCell(cellTitle);
		}
		for (int n = 0; n < dataList.size(); n++) {
			WritableCellFormat writableCellData = createDataCell();
			Object[] rowMap = dataList.get(n);
			for (int k = 0; k < rowMap.length; k++) {
				Object cellData = rowMap[k];
				Label labelData = new Label(k, n + 1, cellData + "", writableCellData);
				sheet.setColumnView(k, cellView);//根据内容自动设置列宽  
				sheet.addCell(labelData);
			}
		}
		workbook.write();
		workbook.close();
	}

	private static WritableCellFormat createTileCell() throws WriteException {

		WritableFont font1 = new WritableFont(WritableFont.ARIAL, 14, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
				Colour.DEFAULT_BACKGROUND);
		WritableCellFormat cellFormat1 = new WritableCellFormat(font1);
		//cellFormat1.setBackground(Colour.BLUE_GREY);
		cellFormat1.setBorder(Border.ALL, BorderLineStyle.DASH_DOT);
		cellFormat1.setWrap(true);
		cellFormat1.setAlignment(Alignment.CENTRE);
		cellFormat1.setVerticalAlignment(VerticalAlignment.CENTRE);
		return cellFormat1;
	}

	private static WritableCellFormat createDataCell() throws WriteException {

		WritableFont font2 = new WritableFont(WritableFont.ARIAL, 14, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,
				Colour.DEFAULT_BACKGROUND);
		WritableCellFormat cellFormat2 = new WritableCellFormat(font2);
		cellFormat2.setAlignment(Alignment.CENTRE);
		//cellFormat2.setBackground(Colour.PINK);
		cellFormat2.setBorder(Border.ALL, BorderLineStyle.THIN);
		cellFormat2.setWrap(true);
		return cellFormat2;
	}
}

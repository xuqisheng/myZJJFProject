package com.zjjf.analysis.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;


public class ZipDemo {

	/**
	 * @param args
	 * @throws IOException
	 * @throws WriteException
	 * @throws RowsExceededException
	 */
	public static void main(String[] args) throws RowsExceededException, WriteException, IOException {

		String path = "C:/Users/Administrator/Desktop/ab";
		// 创建文件夹;
		FileUtils.createFile(path);
		Object[] titleColumn = new Object[4];
		titleColumn[0] = "AAA";
		titleColumn[1] = "BBB";
		titleColumn[2] = "CCC";
		titleColumn[3] = "DDD";
		List<Object[]> dataList = new ArrayList<Object[]>();
		Object[] a = { 1, 2, 3, 4 };
		Object[] b = { 5, 6, 7, 8 };
		dataList.add(a);
		dataList.add(b);
		// 创建Excel文件;
		for (int i = 0; i < 3; i++) {
			CreateExcelUtils.createExcelFile("demo", path, titleColumn, dataList, i);
		}
		// 生成.zip文件;
		CreateZipUtils.createZipPath(path);
		// 删除目录下所有的文件;
		File file = new File(path);
		// 重新创建文件;
		file.mkdirs();
		// 删除文件;
		FileUtils.deleteExcelPath(file);
	}
}
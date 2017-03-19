package com.zjjf.analysis.utils;

import java.io.File;

public class FileUtils {

	/**
	 * 创建文件夹;
	 * 
	 * @param path
	 * @return
	 */
	public static String createFile(String path) {

		File file = new File(path);
		// 判断文件是否存在;
		if (!file.exists()) {
			// 创建文件;
			boolean bol = file.mkdirs();
			if (bol) {
				System.out.println(path + " 路径创建成功!");
			} else {
				System.out.println(path + " 路径创建失败!");
			}
		} else {
			System.out.println(path + " 文件已经存在!");
		}
		return path;
	}

	/**
	 * 删除目录下所有的文件;
	 * 
	 * @param path
	 */
	public static boolean deleteExcelPath(File file) {

		String[] files = null;
		if (file != null) {
			files = file.list();
		}
		if (file.isDirectory()) {
			for (int i = 0; i < files.length; i++) {
				boolean bol = deleteExcelPath(new File(file, files[i]));
				if (bol) {
					System.out.println("删除成功!");
				} else {
					System.out.println("删除失败!");
				}
			}
		}
		return file.delete();
	}
}

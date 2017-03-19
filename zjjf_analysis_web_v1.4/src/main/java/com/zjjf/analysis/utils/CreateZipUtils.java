package com.zjjf.analysis.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class CreateZipUtils {
	
	/**
	 * 生成.zip文件;
	 * 
	 * @param path
	 * @throws IOException
	 */
	public static void createZipPath(String path) throws IOException {

		ZipOutputStream zipOutputStream = null;
		File file = new File(path + ".zip");
		zipOutputStream = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
		File[] files = new File(path).listFiles();
		FileInputStream fileInputStream = null;
		byte[] buf = new byte[1024];
		int len = 0;
		if (files != null && files.length > 0) {
			for (File excelFile : files) {
				String fileName = excelFile.getName();
				fileInputStream = new FileInputStream(excelFile);
				// 放入压缩zip包中;
				zipOutputStream.putNextEntry(new ZipEntry(path + "/" + fileName));
				// 读取文件;
				while ((len = fileInputStream.read(buf)) > 0) {
					zipOutputStream.write(buf, 0, len);
				}
				// 关闭;
				zipOutputStream.closeEntry();
				if (fileInputStream != null) {
					fileInputStream.close();
				}
			}
		}
		if (zipOutputStream != null) {
			zipOutputStream.close();
		}
	}
}

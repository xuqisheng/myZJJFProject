package com.corner.core.utils.code;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.corner.core.beans.ro.AppCodeRo;
import com.corner.core.utils.fastdfs.FastDFSFile;
import com.corner.core.utils.fastdfs.FileManager;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

@Component
public class AppcodeService {

	private static final Logger logger = LoggerFactory.getLogger(AppcodeService.class);

	public String getQrcode(AppCodeRo codeRo) {
		ByteArrayOutputStream baout = new ByteArrayOutputStream();
		BitMatrix bitMatrix = MatrixUtil.toQRCodeMatrix(codeRo.getContent(), codeRo.getWidth(), codeRo.getHeight());
		try {
			MatrixToImageWriter.writeToStream(bitMatrix, MatrixUtil.format, baout);
			FastDFSFile dfsFile = new FastDFSFile("tempQrcodeimage", baout.toByteArray(), "png");
			String uploadPath = FileManager.upload(dfsFile);
			baout.close();
			return uploadPath;
		} catch (IOException e) {
			logger.error("二维码上传FastFds异常", e);
			try {
				baout.close();
			} catch (IOException e1) {
				logger.error("关闭二维码内存流异常", e);
				baout = null;
			}
		}
		return null;
	}

	public String getQrcodeWithLogo(AppCodeRo codeRo) {
		ByteArrayOutputStream baout = new ByteArrayOutputStream();
		BitMatrix bitMatrix = MatrixUtil.toQRCodeMatrix(codeRo.getContent(), codeRo.getWidth(), codeRo.getHeight());
		try {
			int width = bitMatrix.getWidth();
			int height = bitMatrix.getHeight();
			BufferedImage bfImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			for (int x = 0; x < width; x++) {
				for (int y = 0; y < height; y++) {
					bfImage.setRGB(x, y, bitMatrix.get(x, y) ? MatrixUtil.BLACK : MatrixUtil.WHITE);
				}
			}
			Graphics2D grap2D = bfImage.createGraphics();
			BufferedImage logoImg = MatrixUtil.logoImg;
			// int widthLogo = logoImg.getWidth(), heightLogo =
			// logoImg.getHeight();
			// 计算图片放置位置
			int x = (width - width / codeRo.getRage()) / 2;
			int y = (height - width / codeRo.getRage()) / 2;
			grap2D.drawImage(logoImg, x, y, width / codeRo.getRage(), height / codeRo.getRage(), null);
			grap2D.dispose();
			if (!ImageIO.write(bfImage, MatrixUtil.format, baout)) {
				throw new IOException("二维码写入流错误");
			}
			FastDFSFile dfsFile = new FastDFSFile("tempQrcodeimageWithlogo", baout.toByteArray(), "png");
			String uploadPath = FileManager.upload(dfsFile);
			baout.close();
			return uploadPath;
		} catch (IOException e) {
			logger.error("二维码上传FastFds异常", e);
			try {
				baout.close();
			} catch (IOException e1) {
				logger.error("关闭二维码内存流异常", e);
				baout = null;
			}
		}
		return null;
	}

}

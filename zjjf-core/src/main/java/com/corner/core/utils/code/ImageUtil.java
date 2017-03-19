package com.corner.core.utils.code;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public final class ImageUtil {

	// 图片缩放。
	public static BufferedImage reSize(BufferedImage srcBufImage, int width, int height) {
		BufferedImage bufImg = new BufferedImage(width,	height, BufferedImage.TYPE_INT_RGB);
		// 计算图片缩放比例
		float widthBo = (float) width / (float) srcBufImage.getWidth();
		float heightBo = (float) height / (float) srcBufImage.getHeight();
		AffineTransform transform = new AffineTransform();   
		transform.setToScale(widthBo, heightBo);   
		
		// 根据原始图片生成处理后的图片。   
//		AffineTransformOp ato = new AffineTransformOp(transform, null);   
//		ato.filter(srcBufImage, bufImg);
		Graphics2D g = (Graphics2D) bufImg.createGraphics();
		g.drawImage(srcBufImage, transform, null);
		g.dispose();
		return bufImg;
	}

}

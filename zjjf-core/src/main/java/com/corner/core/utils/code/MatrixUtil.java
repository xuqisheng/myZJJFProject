package com.corner.core.utils.code;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.corner.core.config.PropertieNameConts;
import com.corner.core.utils.PropertiesCacheUtil;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * 使用ZXing2.3，生成条码的辅助类。可以编码、解码。编码使用code包，解码需要javase包。
 * 
 * <br/>
 * <br/>
 * 作者：wallimn<br/>
 * 联系：54871876@qq.com，http://wallimn.iteye.com<br/>
 * 时间：2014年5月25日　　下午10:33:05<br/>
 */
public final class MatrixUtil {

	private static final Logger logger = LoggerFactory.getLogger(MatrixUtil.class);
	
	public static final String CHARSET = "utf-8";
	public static final int BLACK = 0xFF000000;
	public static final int WHITE = 0xFFFFFFFF;
	
	public static  String format = PropertiesCacheUtil.getValue("appcodeformat", PropertieNameConts.UPLOAD);
	public static  String fastfdspreurl = PropertiesCacheUtil.getValue("fastfdspreurl", PropertieNameConts.UPLOAD);
	public static  String qrcodeLogoUrl = PropertiesCacheUtil.getValue("qrcodeLogoUrl", PropertieNameConts.UPLOAD);
	public static  String defaultImgUrl = PropertiesCacheUtil.getValue("defaultImgUrl", PropertieNameConts.UPLOAD);
	public static  BufferedImage logoImg = null;
	
	static{
		try {
			logoImg = ImageIO.read(new URL(MatrixUtil.qrcodeLogoUrl));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 禁止生成实例，生成实例也没有意义。
	 */
	private MatrixUtil() { 	}
	
	
    /**
     * 设置二维码的格式参数
     * 
     * @return
     */
    public static Map<EncodeHintType, Object> getDecodeHintType()
    {
        // 用于设置QR二维码参数
        Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
        // 设置QR二维码的纠错级别（H为最高级别）具体级别信息
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        // 设置编码方式
        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
        hints.put(EncodeHintType.MAX_SIZE, 350);
        hints.put(EncodeHintType.MIN_SIZE, 100);
        hints.put(EncodeHintType.MARGIN, 1);
        return hints;
    }

	/**
	 * 
	* @Title: toBarCodeMatrix
	* @Author luke luke@mibodoctor.com
	* @Description: TODO(一维码)
	* @param @param str
	* @param @param width
	* @param @param height
	* @param @return    设定文件
	* @return BitMatrix    返回类型
	* @throws
	 */
	public static BitMatrix toBarCodeMatrix(String str, Integer width,	Integer height) {
		if (width == null || width < 200) {
			width = 200;
		}
		if (height == null || height < 50) {
			height = 50;
		}
		try {
			Map<EncodeHintType, Object> hints = getDecodeHintType();
			BitMatrix bitMatrix = new MultiFormatWriter().encode(str,BarcodeFormat.CODE_128, width, height, hints);
			return bitMatrix;
		} catch (Exception e) {
			logger.error("生成一维码（BarcodeFormat.CODE_128）BitMatrix错误",e);
		}
		return null;
	}
	
	
	/**
	 * 
	* @Title: toQRCodeMatrix
	* @Author luke luke@mibodoctor.com
	* @Description: TODO( 二维码入参)
	* @param @param text
	* @param @param width
	* @param @param height
	* @param @return    设定文件
	* @return BitMatrix    返回类型
	* @throws
	 */
	public static BitMatrix toQRCodeMatrix(String text, Integer width,Integer height) {
		if (width == null || width < 40) {
			width = 300;
		}

		if (height == null || height < 40) {
			height = 300;
		}
		Map<EncodeHintType, Object> hints = getDecodeHintType();
		BitMatrix bitMatrix = null;
		try {
			bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hints);
		} catch (WriterException e) {
			logger.error("生成一维码（BarcodeFormat.QR_CODE）BitMatrix错误",e);
		}
		return bitMatrix;
	}

	/**
	 * 
	* @Title: toBarcodeImage
	* @Author luke luke@mibodoctor.com
	* @Description: TODO(一维码图片)
	* @param @param str
	* @param @param width
	* @param @param height
	* @param @return    设定文件
	* @return BufferedImage    返回类型
	* @throws
	 */
	public static BufferedImage toBarcodeImage(String str, Integer width,Integer height){
		BitMatrix bitMatrix = toBarCodeMatrix(str,width,height);
		if(bitMatrix !=null){
			return MatrixToImageWriter.toBufferedImage(bitMatrix);
		}
		return null;
	}
	
	/**
	 * 
	* @Title: toQRcodeImage
	* @Author luke luke@mibodoctor.com
	* @Description: TODO(返回二维码)
	* @param @param str
	* @param @param width
	* @param @param height
	* @param @return    设定文件
	* @return BufferedImage    返回类型
	* @throws
	 */
	public static BufferedImage toQRcodeImage(String str, Integer width,Integer height){
		BitMatrix bitMatrix = toQRCodeMatrix(str,width,height);
		if(bitMatrix !=null){
			return MatrixToImageWriter.toBufferedImage(bitMatrix);
		}
		return null;
	}
	
	public boolean toQRcodeFile(String text, File file, String format) {
		BitMatrix matrix = toQRCodeMatrix(text, null, null);
		if (matrix != null) {
			try {
				writeToFile(matrix, format, file);
				return true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public static BufferedImage toBufferedImage(BitMatrix matrix) {
		int width = matrix.getWidth();
		int height = matrix.getHeight();
		BufferedImage image = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
			}
		}
		return image;
	}


	public static void writeToFile(BitMatrix matrix, String format, File file)	throws IOException {
		BufferedImage image = toBufferedImage(matrix);
		if (!ImageIO.write(image, format, file)) {
			throw new IOException("Could not write an image of format "+ format + " to " + file);
		}
	}

	public static void writeToStream(BitMatrix matrix, String format,
			OutputStream stream) throws IOException {
		BufferedImage image = toBufferedImage(matrix);
		if (!ImageIO.write(image, format, stream)) {
			throw new IOException("Could not write an image of format " + format);
		}
	}

	public static String decode(File file) {

		BufferedImage image;
		try {
			if (file == null || file.exists() == false) {
				throw new Exception(" File not found:" + file.getPath());
			}

			image = ImageIO.read(file);

			LuminanceSource source = new BufferedImageLuminanceSource(image);
			BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

			Result result;

			// 解码设置编码方式为：utf-8，
			Hashtable hints = new Hashtable();
			hints.put(DecodeHintType.CHARACTER_SET, CHARSET);

			result = new MultiFormatReader().decode(bitmap, hints);

			return result.getText();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
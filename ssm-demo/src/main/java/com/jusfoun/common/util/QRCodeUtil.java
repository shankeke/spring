package com.jusfoun.common.util;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * @name:QRCodeUtil.class
 * @description:
 * @author:闫帅兵
 * @date:2017年5月8日 下午5:55:03
 * @location:com.ysb.test.QRCodeUtil.class Copyright 2015 by Jusfoun.com. All
 *                                         Right Reserved
 */
public class QRCodeUtil {
	private static final String CHARSET = "utf-8";
	private static final String FORMAT_NAME = "JPG";
	private static final String FORMAT_PNGNAME = "PNG";
	// 二维码尺寸
	private static final int QRCODE_SIZE = 300;
	// LOGO宽度
	private static final int WIDTH = 60;
	// LOGO高度
	private static final int HEIGHT = 60;

	/**
	 *
	 * @title: createImage
	 * @description: TODO
	 * @author: 樊利安
	 * @date:2017年6月12日 上午11:41:34
	 * @param content
	 *            二维码存放内容
	 * @param imgPath
	 *            二维码里面存放logo图片路径
	 * @param needCompress
	 *            是否压缩logo图片
	 * @param destPath
	 *            存放文件路径
	 * @param positionflag
	 *            位置摆放，暂时支持横向摆放 1:左字右图 2:左图右字
	 * @param qrcodevo
	 *            生成文字图片需要的对象
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void createImage(String content, String imgPath, boolean needCompress, String destPath,
			String positionflag, QRCodeVo qrcodevo) throws Exception {
		Hashtable hints = new Hashtable();
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
		hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
		hints.put(EncodeHintType.MARGIN, 1);
		BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, QRCODE_SIZE, QRCODE_SIZE,
				hints);
		int width = bitMatrix.getWidth();
		int height = bitMatrix.getHeight();
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
			}
		}
		// if (imgPath == null || "".equals(imgPath)) {
		// return image;
		// }
		// 插入图片---- png格式为消费端用的
		QRCodeUtil.insertImage(image, imgPath, needCompress);
		mkdirs(destPath);
		ImageIO.write(image, FORMAT_PNGNAME, new File(destPath + "/" + qrcodevo.getTracingCode() + ".png"));

		// 创建文字图片
		BufferedImage fontImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		int fontSize = 20;
		Graphics g = fontImage.getGraphics();
		Font font = new Font("宋体", Font.BOLD, fontSize);
		// Font font = new Font("黑体", Font.PLAIN, 20)
		// Font font = new Font("黑体", Font.BOLD, 20),
		g.setClip(0, 0, width, height);
		g.setColor(Color.white);
		g.fillRect(0, 0, width, height);// 先用黑色填充整张图片,也就是背景
		g.setColor(Color.black);// 在换成白色
		g.setFont(font);// 设置画笔字体

		int initConHeight = fontSize;
		initConHeight = addwaterMarkContent("产品:" + qrcodevo.getProductName(), g, width, fontSize, initConHeight);
		initConHeight = addwaterMarkContent("公司名称:" + qrcodevo.getCompanyname(), g, width, fontSize, initConHeight);
		initConHeight = addwaterMarkContent("公司地址:" + qrcodevo.getCompanyAddr(), g, width, fontSize, initConHeight);
		initConHeight = addwaterMarkContent("溯源码:" + qrcodevo.getTracingCode(), g, width, fontSize, initConHeight);
		g.dispose();

		/* 1 读取二维码图片 */
		int[] imageArrayFirst = new int[width * height];// 从图片中读取RGB
		imageArrayFirst = image.getRGB(0, 0, width, height, imageArrayFirst, 0, width);

		/* 1 读取文字图片 */
		int[] FontimageArray = new int[width * height];
		FontimageArray = fontImage.getRGB(0, 0, width, height, FontimageArray, 0, width);

		// 生成新图片
		BufferedImage imageResult = new BufferedImage(width * 2, height, BufferedImage.TYPE_INT_RGB);
		if ("1".equals(positionflag)) {
			imageResult.setRGB(0, 0, width, height, FontimageArray, 0, width);// 设置左半部分的RGB
			imageResult.setRGB(width, 0, width, height, imageArrayFirst, 0, width);// 设置右半部分的RGB

		} else if ("2".equals(positionflag)) {
			imageResult.setRGB(0, 0, width, height, imageArrayFirst, 0, width);// 设置左半部分的RGB
			imageResult.setRGB(width, 0, width, height, FontimageArray, 0, width);// 设置右半部分的RGB
		}
		// 插入图片----jpg格式为打印标签用
		ImageIO.write(imageResult, FORMAT_NAME, new File(destPath + "/" + qrcodevo.getTracingCode() + ".jpg"));

	}

	/**
	 *
	 * @title: addwaterMarkContent
	 * @description: 增加文字图片，实现文字图片自动换行
	 * @author: 樊利安
	 * @date:2017年6月12日 下午3:46:34
	 * @param waterMarkContent
	 *            文字内容
	 * @param g
	 *            图片对象
	 * @param width
	 *            图片宽度
	 * @param fontSize
	 *            文字大小
	 * @param initConHeight
	 *            初始化文字高度
	 * @return
	 */
	public static int addwaterMarkContent(String waterMarkContent, Graphics g, int width, int fontSize,
			int initConHeight) {
		// 获取水印文字总长度
		int fontlen = g.getFontMetrics(g.getFont()).charsWidth(waterMarkContent.toCharArray(), 0,
				waterMarkContent.length());

		int line = fontlen / width;// 文字长度相对于图片宽度应该有多少行

		int y = initConHeight + (fontlen - width <= 0 ? line + 1 : line) * fontSize;
		// 文字叠加,自动换行叠加
		int tempX = 0;
		int tempY = y;
		int tempCharLen = 0;// 单字符长度
		int tempLineLen = 0;// 单行字符总长度临时计算
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < waterMarkContent.length(); i++) {
			char tempChar = waterMarkContent.charAt(i);
			tempCharLen = g.getFontMetrics(g.getFont()).charWidth(tempChar);

			tempLineLen += tempCharLen;

			if (tempLineLen >= width) {
				// 长度已经满一行,进行文字叠加
				g.drawString(sb.toString(), tempX, tempY);

				sb.delete(0, sb.length());// 清空内容,重新追加

				tempY += fontSize;

				tempLineLen = 0;
			}
			sb.append(tempChar);// 追加字符
		}

		g.drawString(sb.toString(), tempX, tempY);// 最后叠加余下的文字
		return tempY;
	}

	private static void insertImage(BufferedImage source, String imgPath, boolean needCompress) throws Exception {
		File file = new File(imgPath);
		if (!file.exists()) {
			System.err.println("" + imgPath + "   该文件不存在！");
			return;
		}
		Image src = ImageIO.read(new File(imgPath));
		int width = src.getWidth(null);
		int height = src.getHeight(null);
		if (needCompress) { // 压缩LOGO
			if (width > WIDTH) {
				width = WIDTH;
			}
			if (height > HEIGHT) {
				height = HEIGHT;
			}
			Image image = src.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics g = tag.getGraphics();
			g.drawImage(image, 0, 0, null); // 绘制缩小后的图
			g.dispose();
			src = image;
		}
		// 插入LOGO
		Graphics2D graph = source.createGraphics();
		int x = (QRCODE_SIZE - width) / 2;
		int y = (QRCODE_SIZE - height) / 2;
		graph.drawImage(src, x, y, width, height, null);
		Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);
		graph.setStroke(new BasicStroke(3f));
		graph.draw(shape);
		graph.dispose();
	}

	/**
	 *
	 * @title: encode
	 * @description: 链接跳转二维码
	 * @author: 闫帅兵
	 * @date:2017年5月9日 上午8:51:59
	 * @param content
	 *            链接
	 * @param imgPath
	 * @param destPath
	 * @param positionflag
	 *            位置摆放，暂时支持横向摆放 0:只放图片不放文字1:左字右图 2:左图右字
	 * @param qrcodevo
	 *            生成文字图片需要的对象
	 * @throws Exception
	 * @param txt
	 *            文件内容
	 */
	public static void encode(String txt, String content, String imgPath, String destPath, boolean needCompress,
			String positionflag, QRCodeVo qrcodevo) throws Exception {
		String file = txt;// 文件路径
		String contents = file + content + ".html";
		String destpath = "D:\\MyWorkDoc\\QR";
		QRCodeUtil.createImage(contents, imgPath, needCompress, destpath, positionflag, qrcodevo);

	}

	/**
	 *
	 * @title: mkdirs
	 * @description: 创建存二维码的文件夹
	 * @author: 闫帅兵
	 * @date:2017年5月9日 上午9:00:25
	 * @param destPath
	 *            文件夹地址
	 */
	public static void mkdirs(String destPath) {
		File file = new File(destPath);
		// 当文件夹不存在时，mkdirs会自动创建多层目录，区别于mkdir．(mkdir如果父目录不存在则会抛出异常)
		if (!file.exists() && !file.isDirectory()) {
			file.mkdirs();
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static String decode(File file) throws Exception {
		BufferedImage image;
		image = ImageIO.read(file);
		if (image == null) {
			return null;
		}
		BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(image);
		BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
		Result result;
		Hashtable hints = new Hashtable();
		hints.put(DecodeHintType.CHARACTER_SET, CHARSET);
		result = new MultiFormatReader().decode(bitmap, hints);
		String resultStr = result.getText();
		return resultStr;
	}

	public static String decode(String path) throws Exception {
		return QRCodeUtil.decode(new File(path));
	}

	/**
	 *
	 * @title: generateCode
	 * @description: 对外统一调用生成二维码接口
	 * @author: 樊利安
	 * @date:2017年6月15日 下午6:48:45
	 * @param imgPath
	 *            二维码里面存放logo图片路径
	 * @param destPath
	 *            二维码生成存放路径
	 * @param traceabilityCode
	 *            溯源码
	 * @param needCompress
	 *            是否压缩logo图片
	 * @param positionflag
	 *            位置摆放，暂时支持横向摆放 1:左字右图 2:左图右字
	 * @param productName
	 *            产品名称
	 * @param companyName
	 *            公司名称
	 * @param companyAddr
	 *            公司地址
	 * @throws Exception
	 */
	public static void generateCode(String imgPath, String destPath, String traceabilityCode, boolean needCompress,
			String positionflag, String productName, String companyName, String companyAddr) throws Exception {
		try {

			QRCodeVo qrcodevo = new QRCodeVo();
			qrcodevo.setProductName(productName);
			qrcodevo.setCompanyname(companyName);
			qrcodevo.setCompanyAddr(companyAddr);
			qrcodevo.setTracingCode(traceabilityCode);

			String contents = "http://localhost:60028/sysGenerator/view?code=" + traceabilityCode; // 二维码存放内容，包括范围地址和二维码代码
			QRCodeUtil.createImage(contents, imgPath, needCompress, positionflag, destPath, qrcodevo);
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}
	}

	// 读取文件内容txt static
	public String readTxtFile(String filePath, String text) {
		String txtcontent = "";
		// text = "http://202.106.10.250:60027/sy/";
		try {
			String encoding = "GBK";
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				QRCodeVo qrcodevo = new QRCodeVo();
				qrcodevo.setProductName("仲巴县9");
				qrcodevo.setCompanyname("农业化生产态标准化管理");
				qrcodevo.setCompanyAddr("北京市海淀区曙光花园中路");
				qrcodevo.setTracingCode("0000112293");
				while ((lineTxt = bufferedReader.readLine()) != null) {
					// System.out.println(lineTxt);
					txtcontent = lineTxt;
					QRCodeUtil.encode(text, txtcontent, "d:/MyWorkDoc/logo/logo.jpg", "d:/MyWorkDoc/QR", true, "0",
							qrcodevo);
				}
				read.close();
			} else {
				System.out.println("找不到指定的文件");
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}
		return txtcontent;

	}

}

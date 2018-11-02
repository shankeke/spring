package com.jusfoun.common.utils.zxing;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.FileSystems;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * 说明： 二维码生成工具. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年10月11日 下午7:23:16
 */
public class ZXingUtils {
	// 二维码颜色
	// public static final int RGB_BLACK = 0xFF000000;// 黑色
	// public static final int RGB_WHITE = 0xFFFFFFFF;// 白色
	// public static final int RGB_BLUE = 0xFF0000FF;// 蓝色
	// public static final int RGB_RED = 0xFFFF0000;// 红色

	/**
	 * 前景色
	 */
	public static final int DEF_FG_RGB = Colors.Black.getRGB();// 黑色
	/**
	 * 背景色
	 */
	public static final int DEF_BG_RGB = Colors.White.getRGB();;// 白色

	/**
	 * 默认文件格式
	 */
	public static final String DEF_FORMAT = "png";
	/**
	 * 默认编码方式
	 */
	public static final String DEF_CHARACTER_SET = "UTF-8";
	/**
	 * 默认宽度
	 */
	public static final int DEF_WIDTH = 200;
	/**
	 * 默认高度
	 */
	public static final int DEF_HEIGHT = 200;

	/**
	 * 说明： 二维码生成. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年10月11日 下午7:23:26
	 * @param content
	 *            二维码内容
	 * @param out
	 *            输出
	 * @throws IOException
	 * @throws WriterException
	 */
	public static void write(String content, OutputStream out) throws IOException, WriterException {
		write(content, DEF_WIDTH, DEF_HEIGHT, DEF_FORMAT, DEF_CHARACTER_SET, out);
	}

	/**
	 * 说明： 二维码生成. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年10月11日 下午7:24:21
	 * @param content
	 *            二维码内容
	 * @param width
	 *            图片宽度
	 * @param height
	 *            图片高度
	 * @param out
	 *            输出
	 * @throws IOException
	 * @throws WriterException
	 */
	public static void write(String content, int width, int height, OutputStream out) throws IOException, WriterException {
		write(content, width, height, DEF_FORMAT, DEF_CHARACTER_SET, out);
	}

	/**
	 * 说明： 二维码生成. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年10月11日 下午7:25:07
	 * @param content
	 *            二维码内容
	 * @param width
	 *            图片宽度 200
	 * @param height
	 *            图片高度 200
	 * @param format
	 *            文件后缀 png,bmp,jpg
	 * @param filename
	 *            文件名"d:/zxing.png"
	 * @param charset
	 *            文件名 "UTF-8"
	 * @throws WriterException
	 * @throws IOException
	 */
	public static void write(String content, int width, int height, String format, String charset, String filename) throws WriterException, IOException {
		MatrixToImageWriter.writeToPath(encode(content, width, height, charset), format, FileSystems.getDefault().getPath(filename));// 输出图像
	}

	/**
	 * 说明： 二维码生成. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年10月11日 下午7:25:49
	 * @param content
	 *            二维码内容
	 * @param width
	 *            图片宽度 200
	 * @param height
	 *            图片高度 200
	 * @param format
	 *            文件后缀 png,bmp,jpg
	 * @param charset
	 *            文件名 "UTF-8"
	 * @param out
	 *            输出流
	 * @throws WriterException
	 * @throws IOException
	 */
	public static void write(String content, int width, int height, String format, String charset, OutputStream out) throws WriterException, IOException {
		BitMatrix encode = encode(content, width, height, charset);
		MatrixToImageWriter.writeToStream(encode, format, out);
	}

	/**
	 * 说明：  二维码生成. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年10月11日 下午7:27:04
	 * @param content
	 *            二维码内容
	 * @param width
	 *            宽度
	 * @param height
	 *            高度
	 * @param charset
	 *            字符集
	 * @return
	 * @throws WriterException
	 */
	public static BitMatrix encode(String content, int width, int height, String charset) throws WriterException {
		Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
		hints.put(EncodeHintType.CHARACTER_SET, charset);
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H); // 容错率
		hints.put(EncodeHintType.MARGIN, 1); // 二维码边框宽度，这里文档说设置0-4，但是设置后没
		return new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);// 生成矩阵
	}

	/**
	 * 说明： 二维码生成. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年10月11日 下午7:25:49
	 * @param content
	 *            二维码内容
	 * @param out
	 *            输出流
	 * @throws WriterException
	 * @throws IOException
	 */
	public static void writeImgToFile(String content, File file) throws WriterException, IOException {
		ImageIO.write(encodeImg(content, DEF_WIDTH, DEF_HEIGHT, DEF_CHARACTER_SET, DEF_FG_RGB, DEF_BG_RGB), DEF_FORMAT, file);
	}

	/**
	 * 说明： 二维码生成. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年10月11日 下午7:25:49
	 * @param content
	 *            二维码内容
	 * @param width
	 *            图片宽度
	 * @param height
	 *            图片高度
	 * @param out
	 *            输出流
	 * @throws WriterException
	 * @throws IOException
	 */
	public static void writeImgToFile(String content, int width, int height, File file) throws WriterException, IOException {
		ImageIO.write(encodeImg(content, DEF_WIDTH, DEF_HEIGHT, DEF_CHARACTER_SET, DEF_FG_RGB, DEF_BG_RGB), DEF_FORMAT, file);
	}

	/**
	 * 说明： 二维码生成. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年10月11日 下午7:25:49
	 * @param content
	 *            二维码内容
	 * @param width
	 *            图片宽度
	 * @param height
	 *            图片高度
	 * @param format
	 *            文件后缀 png,bmp,jpg
	 * @param charset
	 *            文件名 "UTF-8"
	 * @param fgColor
	 *            前景色
	 * @param bgColor
	 *            背景色
	 * @param out
	 *            输出流
	 * @throws WriterException
	 * @throws IOException
	 */
	public static void writeImgToFile(String content, int width, int height, String format, String charset, int fgColor, int bgColor, File file)
			throws WriterException, IOException {
		ImageIO.write(encodeImg(content, width, height, charset, fgColor, bgColor), format, file);
	}

	/**
	 * 说明： 二维码生成. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年10月11日 下午7:25:49
	 * @param content
	 *            二维码内容
	 * @param out
	 *            输出流
	 * @throws WriterException
	 * @throws IOException
	 */
	public static void writeImgToStream(String content, OutputStream out) throws WriterException, IOException {
		ImageIO.write(encodeImg(content, DEF_WIDTH, DEF_HEIGHT, DEF_CHARACTER_SET, DEF_FG_RGB, DEF_BG_RGB), DEF_FORMAT, out);
	}

	/**
	 * 说明： 二维码生成. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年10月11日 下午7:25:49
	 * @param content
	 *            二维码内容
	 * @param width
	 *            图片宽度
	 * @param height
	 *            图片高度
	 * @param out
	 *            输出流
	 * @throws WriterException
	 * @throws IOException
	 */
	public static void writeImgToStream(String content, int width, int height, OutputStream out) throws WriterException, IOException {
		ImageIO.write(encodeImg(content, width, height, DEF_CHARACTER_SET, DEF_FG_RGB, DEF_BG_RGB), DEF_FORMAT, out);
	}

	/**
	 * 说明： 二维码生成. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年10月11日 下午7:25:49
	 * @param content
	 *            二维码内容
	 * @param width
	 *            图片宽度
	 * @param height
	 *            图片高度
	 * @param format
	 *            文件后缀 png,bmp,jpg
	 * @param charset
	 *            文件名 "UTF-8"
	 * @param fgColor
	 *            前景色
	 * @param bgColor
	 *            背景色
	 * @param out
	 *            输出流
	 * @throws WriterException
	 * @throws IOException
	 */
	public static void writeImgToStream(String content, int width, int height, String format, String charset, int fgColor, int bgColor, OutputStream out)
			throws WriterException, IOException {
		ImageIO.write(encodeImg(content, width, height, charset, fgColor, bgColor), format, out);
	}

	/**
	 * 说明： 二维码生成. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年10月11日 下午7:27:04
	 * @param content
	 *            二维码内容
	 * @param width
	 *            宽度
	 * @param height
	 *            高度
	 * @param charset
	 *            字符集
	 * @param fgColor
	 *            前景色
	 * @param bgColor
	 *            背景色
	 * @return
	 * @throws WriterException
	 */
	public static BufferedImage encodeImg(String content, int width, int height, String charset, int fgColor, int bgColor) throws WriterException {
		BitMatrix matrix = encode(content, width, height, charset);
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, matrix.get(x, y) ? fgColor : bgColor);
			}
		}
		return image;
	}

	public static void writeImgToFileWithLogo(String content, File logoFile, File file) throws WriterException, IOException {
		writeImgToFileWithLogo(content, logoFile, Position.CENTER, file);
	}

	public static void writeImgToFileWithLogo(String content, File logoFile, Position position, File file) throws WriterException, IOException {
		writeImgToFileWithLogo(content, DEF_WIDTH, DEF_HEIGHT, DEF_CHARACTER_SET, DEF_FORMAT, DEF_FG_RGB, DEF_BG_RGB, logoFile, position, file);
	}

	public static void writeImgToFileWithLogo(String content, int width, int height, String charset, String format, int fgColor, int bgColor, File logoFile, Position position,
			File file) throws WriterException, IOException {
		ImageIO.write(encodeImgWithLogo(content, width, height, charset, fgColor, bgColor, logoFile, position), format, file);
	}

	public static void writeImgToStreamWithLogo(String content, File logoFile, OutputStream out) throws WriterException, IOException {
		writeImgToStreamWithLogo(content, logoFile, Position.CENTER, out);
	}

	public static void writeImgToStreamWithLogo(String content, File logoFile, Position position, OutputStream out) throws WriterException, IOException {
		writeImgToStreamWithLogo(content, DEF_WIDTH, DEF_HEIGHT, DEF_CHARACTER_SET, DEF_FORMAT, DEF_FG_RGB, DEF_BG_RGB, logoFile, position, out);
	}

	public static void writeImgToStreamWithLogo(String content, int width, int height, String charset, String format, int fgColor, int bgColor, File logoFile, Position position,
			OutputStream out) throws WriterException, IOException {
		ImageIO.write(encodeImgWithLogo(content, width, height, charset, fgColor, bgColor, logoFile, position), format, out);
	}

	public static BufferedImage encodeImgWithLogo(String content, int width, int height, String charset, int fgColor, int bgColor, File logoFile, Position position)
			throws WriterException, IOException {
		BufferedImage encodeImg = null;
		if (!logoFile.isFile()) {
			return null;
		}
		// 读取二维码图片
		encodeImg = encodeImg(content, width, height, charset, fgColor, bgColor);
		// 获取画笔
		Graphics2D g = encodeImg.createGraphics();
		// 读取logo图片
		BufferedImage logo = ImageIO.read(logoFile);
		// 设置二维码大小，太大，会覆盖二维码，此处20%
		int logoWidth = logo.getWidth(null) > width * 3 / 10 ? (width * 3 / 10) : logo.getWidth(null);
		int logoHeight = logo.getHeight(null) > height * 3 / 10 ? (height * 3 / 10) : logo.getHeight(null);
		// 设置logo图片放置位置
		int x = 0;
		int y = 0;
		int offset = 15;
		switch (position) {
			case LEFT_TOP :
				x = offset;
				y = offset;
				break;
			case LEFT_BUTTOM :
				// 左下角，5为调整值
				x = offset;
				y = height - logoHeight - offset;
				break;
			case RIGHT_TOP :
				// 右上角，5为调整值
				x = width - logoWidth - offset;
				y = offset;
				break;
			case RIGHT_BUTTOM :
				// 右下角，15为调整值
				x = width - logoWidth - offset;
				y = height - logoHeight - offset;
				break;
			default :
				// 中心
				x = (width - logoWidth) / 2;
				y = (height - logoHeight) / 2;
				break;
		}

		// 开始合并绘制图片
		g.drawImage(logo, x, y, logoWidth, logoHeight, null);
		g.drawRoundRect(x, y, logoWidth, logoHeight, offset, offset);
		// logo边框大小
		g.setStroke(new BasicStroke(2));
		// logo边框颜色
		g.setColor(Color.WHITE);
		g.drawRect(x, y, logoWidth, logoHeight);
		g.dispose();
		logo.flush();
		encodeImg.flush();

		return encodeImg;
	}

	/**
	 * 给图片添加水印、可设置水印图片旋转角度
	 * 
	 * @param iconPath
	 *            水印图片路径
	 * @param srcImgPath
	 *            源图片路径
	 * @param targerPath
	 *            目标图片路径
	 * @param degree
	 *            水印图片旋转角度
	 */
	public static void makeImageByIcon(String iconPath, String srcImgPath, String targerPath, Integer degree) {
		OutputStream os = null;
		try {
			Image srcImg = ImageIO.read(new File(srcImgPath));
			BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null), srcImg.getHeight(null), BufferedImage.TYPE_INT_RGB);
			// 得到画笔对象
			// Graphics g= buffImg.getGraphics();
			Graphics2D g = buffImg.createGraphics();
			// 设置对线段的锯齿状边缘处理
			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g.drawImage(srcImg.getScaledInstance(srcImg.getWidth(null), srcImg.getHeight(null), Image.SCALE_SMOOTH), 0, 0, null);
			if (null != degree) {
				// 设置水印旋转
				g.rotate(Math.toRadians(degree), (double) buffImg.getWidth() / 2, (double) buffImg.getHeight() / 2);
			}
			// 水印图象的路径 水印一般为gif或者png的，这样可设置透明度
			ImageIcon imgIcon = new ImageIcon(iconPath);
			// 得到Image对象。
			Image img = imgIcon.getImage();
			float alpha = 0.5f; // 透明度
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
			// 表示水印图片的位置
			g.drawImage(img, 150, 300, null);
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
			g.dispose();
			os = new FileOutputStream(targerPath);
			// 生成图片
			ImageIO.write(buffImg, "JPG", os);
			System.out.println("图片完成添加Icon印章。。。。。。");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != os)
					os.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 说明： 二维码解码. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年10月11日 下午7:27:38
	 * @param filePath
	 *            文件路径
	 * @return
	 * @throws IOException
	 * @throws NotFoundException
	 */
	public static String decode(String filePath) throws IOException, NotFoundException {
		Map<DecodeHintType, Object> hints = new HashMap<DecodeHintType, Object>();
		hints.put(DecodeHintType.CHARACTER_SET, DEF_CHARACTER_SET);
		return new MultiFormatReader().decode(new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(ImageIO.read(new File(filePath))))), hints).getText();// 对图像进行解码
	}

	/*
	 * public static void main(String[] args) throws FileNotFoundException,
	 * IOException, WriterException, NotFoundException { // write("hh", new
	 * FileOutputStream(new File("D:/zxing.png"))); // writeImgToFile("hh", new
	 * File("D:/zxing.png")); // System.out.println(decode("D:/zxing.png"));
	 * String content = "天津奥博七里生态农业科技有限公司"; // String content =
	 * "http://192.168.1.207:8080/aobo/bigData/index.html"; // File logoFile =
	 * new File("d:/favicon.png"); // File logoFile = new File("d:/1.jpg"); //
	 * File qCodeFile = new File("d:/zxing.png"); //
	 * writeImgToFileWithLogo(content, logoFile, Position.RIGHT_TOP, //
	 * qCodeFile); for (int i = 0; i <= 101; i++) { File logoFile = new
	 * File("d:/logos/" + i + ".jpg"); File qCodeFile = new File("d:/logos/" + i
	 * + ".png"); writeImgToFileWithLogo(content, 320, 320, DEF_CHARACTER_SET,
	 * DEF_FORMAT, Colors.Red4.getRGB(), DEF_BG_RGB, logoFile, Position.CENTER,
	 * qCodeFile); } }
	 */
}
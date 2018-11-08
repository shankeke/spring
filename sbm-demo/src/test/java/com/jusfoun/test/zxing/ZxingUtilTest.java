package com.jusfoun.test.zxing;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.Test;

import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import com.jusfoun.common.utils.zxing.Colors;
import com.jusfoun.common.utils.zxing.Position;
import com.jusfoun.common.utils.zxing.ZXingUtils;

public class ZxingUtilTest extends ZXingUtils {
	// String content = "天津奥博七里生态农业科技有限公司";
	String content = "http://wwww.baidu.com?version=1.0&name=zhangsan";

	@Test
	public void write() throws FileNotFoundException, IOException, WriterException, NotFoundException {
		File file = new File("D:/logos/baidu.png");
		write(content, new FileOutputStream(file));
		// writeImgToFile(content, file);
	}

	@Test
	public void writeColors() throws FileNotFoundException, IOException, WriterException, NotFoundException {
		Colors[] values = Colors.values();
		int i = 0;
		int rgb1 = 0;
		int rgb2 = 0;
		for (Colors color1 : values) {
			for (Colors color2 : values) {
				rgb1 = color1.getRGB();
				rgb2 = color2.getRGB();
				File file = new File("D:/logos/colorQcode/" + String.format("%05d", i++) + "_" + color1 + "_" + color2 + ".png");
				System.out.println(file.getName());
				writeImgToFile(content, DEF_WIDTH, DEF_HEIGHT, DEF_FORMAT, DEF_CHARACTER_SET, rgb1, rgb2, file);
			}
		}
	}

	@Test
	public void writeImgToFileWithLogo() throws FileNotFoundException, IOException, WriterException, NotFoundException {
		for (int i = 0; i <= 101; i++) {
			File logoFile = new File("d:/logos/logo/" + i + ".jpg");
			File qCodeFile = new File("d:/logos/qcode/" + i + ".png");
			writeImgToFileWithLogo(content, 320, 320, DEF_CHARACTER_SET, DEF_FORMAT, Colors.random().getRGB(), Colors.random().getRGB(), logoFile, Position.RIGHT_BUTTOM,
					qCodeFile);
		}
	}

	@Test
	public void markImageByIcon() {
		File logoFile = new File("d:/logos/logo/" + 2 + ".jpg");
		File qCodeFile = new File("d:/logos/qcode/" + 1 + ".png");
		makeImageByIcon(logoFile.getAbsolutePath(), qCodeFile.getAbsolutePath(), "d:/logos/qcode/" + 1111 + ".png", null);
	}
}

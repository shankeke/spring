/*
 * Copyright (C) 2015 YSYC
 *
 */
package com.shanke.dubbo.utils.io;

import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;

/**
 * 文件大小计算工具类，用于文件长度KB,MB,GB,TB,PB等转换
 * 
 * @author yjw
 * 
 */
public class FileSizeUtil {
	/**
	 * 取得文件大小
	 * 
	 * @param f
	 *            读取的文件
	 * @return 文件长度
	 * @throws Exception
	 */
	@SuppressWarnings("resource")
	public static long getFileSizes(File f) throws Exception {
		long s = 0;
		if (f.exists()) {
			new FileInputStream(f).available();
		} else {
			f.createNewFile();
		}
		return s;
	}

	/**
	 * 取得文件夹所有数据总大小
	 * 
	 * @param f
	 *            读取的文件
	 * @return 文件长度
	 * @throws Exception
	 */
	public static long getFileSize(File f) throws Exception {
		long size = 0;
		File flist[] = f.listFiles();
		for (int i = 0; i < flist.length; i++) {
			if (flist[i].isDirectory()) {
				size = size + getFileSize(flist[i]);
			} else {
				size = size + flist[i].length();
			}
		}
		return size;
	}

	/**
	 * 格式化文件大小
	 * 
	 * @param fileS
	 *            文件长度
	 * @return 文件大小格式化字符串
	 */
	public static String FormetFileSize(long fileS) {
		DecimalFormat df = new DecimalFormat("#.00");
		String fileSizeString = "";
		if (fileS < 1024) {
			fileSizeString = df.format((double) fileS) + "B";
		} else if (fileS < 1048576) {
			fileSizeString = df.format((double) fileS / 1024) + "K";
		} else if (fileS < 1073741824) {
			fileSizeString = df.format((double) fileS / 1048576) + "M";
		} else {
			fileSizeString = df.format((double) fileS / 1073741824) + "G";
		}
		return fileSizeString;
	}

	/**
	 * 递归求取目录文件个数
	 * 
	 * @param f
	 *            读取的文件
	 * @return 文件字节长度
	 */
	public static long getlist(File f) {
		long size = 0;
		File flist[] = f.listFiles();
		size = flist.length;
		for (int i = 0; i < flist.length; i++) {
			if (flist[i].isDirectory()) {
				size = size + getlist(flist[i]);
				size--;
			}
		}
		return size;
	}

	// /**
	// * 测试
	// * @param args
	// */
	// public static void main(String args[]) {
	// FileSizeUtil g = new FileSizeUtil();
	// long startTime = System.currentTimeMillis();
	// try {
	// long l = 0;
	// String path = "C:\\WINDOWS";
	// File ff = new File(path);
	// if (ff.isDirectory()) { // 如果路径是文件夹的时候
	// System.out.println("文件个数           " + g.getlist(ff));
	// System.out.println("目录");
	// l = g.getFileSize(ff);
	// System.out.println(path + "目录的大小为：" + g.FormetFileSize(l));
	// } else {
	// System.out.println("     文件个数           1");
	// System.out.println("文件");
	// l = g.getFileSizes(ff);
	// System.out.println(path + "文件的大小为：" + g.FormetFileSize(l));
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// long endTime = System.currentTimeMillis();
	// System.out.println("总共花费时间为：" + (endTime - startTime) + "毫秒...");
	// }
}

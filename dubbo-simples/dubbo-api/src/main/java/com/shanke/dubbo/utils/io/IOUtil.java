/*
 * Copyright (C) 2015 YSYC
 *
 */
package com.shanke.dubbo.utils.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * 文件数据流操作，文件复制，文件路径创建，文件重命名等
 * 
 * @author yjw
 * 
 */
public class IOUtil {
	/**
	 * 从输入流写到输出流
	 * 
	 * @param in
	 *            输入流
	 * @param out
	 *            输出流
	 * @param b
	 *            是否关闭输入输出流
	 * @throws IOException
	 */
	public static void write(InputStream in, OutputStream out, boolean b)
			throws IOException {
		byte[] buf = new byte[4096];
		int len = -1;
		while ((len = in.read(buf)) != -1) {
			out.write(buf, 0, len);
		}
		if (b) {
			out.flush();
			in.close();
			out.close();
		}
	}

	/**
	 * 从输入流写到输出流
	 * 
	 * @param in
	 *            输入流
	 * @param out
	 *            输出流
	 * @throws IOException
	 */
	public static void write(InputStream in, OutputStream out)
			throws IOException {
		write(in, out, true);
	}

	/**
	 * 从输入流写到本地文件输出流
	 * 
	 * @param in
	 *            输入流
	 * @param outFile
	 *            输出本地文件
	 * @throws IOException
	 */
	public static void write(InputStream in, File outFile) throws IOException {
		write(in, new FileOutputStream(outFile));
	}

	/**
	 * 从本地文件输入流写到输出流
	 * 
	 * @param inFile
	 *            本地文件
	 * @param out
	 *            输出流
	 * @throws IOException
	 */
	public static void write(File inFile, OutputStream out) throws IOException {
		write(new FileInputStream(inFile), out);
	}

	/**
	 * <pre>
	 * Description: 
	 *        向文件中写入字符串，并指定编码
	 * </pre>
	 * 
	 * @author yjw
	 * @date 2015-9-17 下午8:04:17
	 * @param in
	 *            字符串
	 * @param file
	 *            目标文件
	 * @param charset
	 *            字符集
	 * @throws Exception
	 */
	public static void write(String in, File file, String charset)
			throws Exception {
		PrintWriter out = new PrintWriter(new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(file), charset)));
		out.write(in);
		out.flush();
		out.close();
	}

	/**
	 * <pre>
	 * Description: 
	 *        向文件中写入字符串，并指定编码,默认字符集utf-8
	 * </pre>
	 * 
	 * @author yjw
	 * @date 2015-9-17 下午8:04:17
	 * @param in
	 *            字符串
	 * @param file
	 *            目标文件
	 * @throws Exception
	 */
	public static void write(String in, File file) throws Exception {
		write(in, file, "utf-8");
	}

	/**
	 * 文件copy
	 * 
	 * @param in
	 *            输入流
	 * @param out
	 *            输出流
	 * @throws IOException
	 */
	public static void copy(InputStream in, OutputStream out)
			throws IOException {
		write(in, out);
	}

	/**
	 * 文件copy
	 * 
	 * @param src
	 *            源文件
	 * @param dest
	 *            目标文件
	 * @throws IOException
	 * @throws Exception
	 */
	public static void copy(File src, File dest) throws IOException {
		write(new FileInputStream(src), new FileOutputStream(dest));
	}

	/**
	 * 文件copy
	 * 
	 * @param src
	 *            源文件路径
	 * @param dest
	 *            目标文件路径
	 * @throws IOException
	 * @throws Exception
	 */
	public static void copy(String src, String dest) throws IOException {
		copy(new File(src), new File(dest));
	}

	/**
	 * 文件copy
	 * 
	 * @param src
	 *            源文件
	 * @param dest
	 *            目标文件路径
	 * @throws IOException
	 * @throws Exception
	 */
	public static void copy(File src, String dest) throws IOException {
		copy(src, new File(dest));
	}

	/**
	 * 文件copy
	 * 
	 * @param src
	 *            源文件路径
	 * @param dest
	 *            目标文件
	 * @throws IOException
	 * @throws Exception
	 */
	public static void copy(String src, File dest) throws IOException {
		copy(new File(src), dest);
	}

	/**
	 * 创建一个空文件目录
	 * 
	 * @param dir
	 *            目录文件
	 */
	public static void makdir(File dir) {
		if (!dir.exists()) {
			dir.mkdir();
		}
	}

	/**
	 * 创建一个空文件目录
	 * 
	 * @param dir
	 *            目录绝对路径
	 */
	public static void makdir(String dir) {
		makdir(new File(dir));
	}

	/**
	 * 创建一个空文件目录
	 * 
	 * @param dir
	 *            目录路径
	 */
	public static void makdirLoopDir(File rootdir, String subDir) {
		String[] dirs = subDir.replaceAll("//", "/").split("/");
		for (int i = 0; i < dirs.length; i++) {
			rootdir = new File(rootdir, dirs[i]);
			makdir(rootdir);
		}
	}

	/**
	 * 创建一个空文件目录
	 * 
	 * @param dir
	 *            目录路径
	 */
	public static void makdirLoopDir(String rootdir, String subDir) {
		makdirLoopDir(new File(rootdir), subDir);
	}

	/**
	 * 创建指定的目录
	 * 
	 * @param directory
	 *            父文件路径
	 * @param subDirectory
	 *            子文件路径
	 * @throws Exception
	 */
	public static void createDirectory(String directory, String subDirectory) {
		File fl = new File(directory);
		String[] dir;
		if (!fl.exists()) {
			fl.mkdir();
		}
		File subFile = null;
		if (subDirectory != "") {
			dir = subDirectory.replace('\\', '/').split("/");
			for (int i = 0; i < dir.length; i++) {
				subFile = new File(directory + File.separator + dir[i]);
				if (!subFile.exists()) {
					subFile.mkdir();
				}
			}
		}
	}

	/**
	 * 重命名文件
	 * 
	 * @param filePath1
	 *            原文件名
	 * @param filePath2
	 *            新文件名
	 * @throws Exception
	 *             异常
	 */
	public static boolean rename(String filePath1, String filePath2) {
		return new File(filePath1).renameTo(new File(filePath2));
	}

	/**
	 * 重命名文件
	 * 
	 * @param file
	 *            原文件
	 * @param filePath2
	 *            新文件名
	 * @throws Exception
	 *             异常
	 */
	public static boolean rename(File file, String filePath2) {
		return file.renameTo(new File(filePath2));
	}

	/**
	 * 重命名文件
	 * 
	 * @param filePath1
	 *            原文件
	 * @param file
	 *            新文件
	 * @throws Exception
	 *             异常
	 */
	public static boolean rename(String filePath1, File file) {
		return new File(filePath1).renameTo(file);
	}

	/**
	 * <pre>
	 * Description: 
	 *       递归删除目录下的所有文件及子目录下所有文件
	 * </pre>
	 * 
	 * @author yjw
	 * @date 2015-9-18 下午4:40:21
	 * @param file
	 *            将要删除的文件目录
	 * @param b
	 *            是否递归删除
	 * @return
	 */
	public static boolean delete(File file, boolean b) {
		if (file.isDirectory() && b) {
			// 递归删除目录中的子目录下
			for (String children : file.list()) {
				if (!delete(new File(file, children), b)) {
					return false;
				}
			}
		}
		// 目录此时为空，可以删除
		return file.delete();
	}

	/**
	 * <pre>
	 * Description: 
	 *       删除文件和目录，如果目录下有子文件，遍历删除
	 * </pre>
	 * 
	 * @author yjw
	 * @date 2015-9-18 下午4:40:21
	 * @param file
	 *            将要删除的文件或目录
	 * @return
	 */
	public static boolean delete(File file) {
		return delete(file, true);
	}

	// public static void main(String[] args) {
	// System.out.println(delete(new File("D:/usr"), false));
	// System.out.println(delete(new File("D:/usr"), true));
	// }
}

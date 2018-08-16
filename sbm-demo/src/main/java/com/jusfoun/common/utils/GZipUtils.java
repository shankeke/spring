package com.jusfoun.common.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jusfoun.common.utils.io.IoUtils;

/**
 * 描述 : Gzip文件格式处理. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年11月16日 下午3:29:07
 */
public class GZipUtils {
	private static final Logger log = LoggerFactory.getLogger(GZipUtils.class);

	/**
	 * 描述 :压缩. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2017年11月16日 下午3:29:33
	 * @param filename
	 *            压缩文件
	 */
	public static String gzip(String filename) {
		String outFileName = filename + ".gz";
		try {
			if (log.isDebugEnabled()) {
				log.debug("Creating the GZIP file [" + outFileName + "] from [" + filename + "]");
			}
			IoUtils.copy(new FileInputStream(filename), new GZIPOutputStream(new FileOutputStream(outFileName)));
		} catch (FileNotFoundException e) {
			if (log.isErrorEnabled()) {
				log.error("File not found : " + filename);
			}
			return null;
		} catch (IOException e) {
			if (log.isErrorEnabled()) {
				log.error("Could not create file: " + outFileName);
			}
			return null;
		}
		return outFileName;
	}

	/**
	 * 描述 :解压缩. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2017年11月16日 下午3:29:50
	 * @param gzipFileName
	 *            解压文件名称
	 */
	public static String unGzip(String gzipFileName) {
		String outFileName = null;
		if (!getExtension(gzipFileName).equalsIgnoreCase("gz")) {
			if (log.isDebugEnabled()) {
				log.debug("File name must have extension of \".gz\", gzip filename :" + gzipFileName);
			}
			return null;
		}
		try {
			outFileName = getFileName(gzipFileName);
			if (log.isDebugEnabled()) {
				log.debug("Ungzip file [" + outFileName + "] to [" + gzipFileName + "]");
			}
			IoUtils.copy(new GZIPInputStream(new FileInputStream(gzipFileName)), new FileOutputStream(outFileName));
		} catch (FileNotFoundException e) {
			if (log.isErrorEnabled()) {
				log.error("File not found. " + gzipFileName);
			}
			return null;
		} catch (IOException e) {
			if (log.isErrorEnabled()) {
				log.error("Could not create file: " + outFileName);
			}
			return null;
		}
		return outFileName;
	}

	/**
	 * Used to extract and return the extension of a given file.
	 * 
	 * @param f
	 *            Incoming file to get the extension of
	 * @return <code>String</code> representing the extension of the incoming
	 *         file.
	 */
	private static String getExtension(String f) {
		String ext = "";
		int i = f.lastIndexOf('.');

		if (i > 0 && i < f.length() - 1) {
			ext = f.substring(i + 1);
		}
		return ext;
	}

	/**
	 * Used to extract the filename without its extension.
	 * 
	 * @param f
	 *            Incoming file to get the filename
	 * @return <code>String</code> representing the filename without its
	 *         extension.
	 */
	private static String getFileName(String f) {
		String fname = "";
		int i = f.lastIndexOf('.');

		if (i > 0 && i < f.length() - 1) {
			fname = f.substring(0, i);
		}
		return fname;
	}

}

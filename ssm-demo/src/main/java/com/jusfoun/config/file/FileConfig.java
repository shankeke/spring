package com.jusfoun.config.file;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.jusfoun.common.utils.ICollections;
import com.jusfoun.common.utils.date.DateUtil;

/**
 * 描述 : 上传文件配置和文件处理的公共类，主要用于文件上传路径处理，文件的删除等操作. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月16日 下午1:40:24
 */
@Configuration
@ConfigurationProperties(prefix = FileConfig.PREFIX)
public class FileConfig {
	public static final String PREFIX = "system.file.upload";

	private String root;// 文件目录
	private String rootDir;// 根文件夹
	private String separator;// 多个文件路径之间的分隔符
	private String suffix;// 允许的文件格式

	public String getRootDir() {
		return rootDir;
	}

	public void setRootDir(String rootDir) {
		this.rootDir = rootDir;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getRoot() {
		return root;
	}

	public void setRoot(String root) {
		this.root = root;
	}

	public String getSeparator() {
		return separator;
	}

	public void setSeparator(String separator) {
		this.separator = separator;
	}

	/**
	 * 描述 : 获取一个信息UUID. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年9月16日 下午1:44:53
	 * @return
	 */
	public String getUUID() {
		return UUID.randomUUID().toString();
	}

	/**
	 * 描述 : 新生成一个文件名称>. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年9月16日 下午1:42:59
	 * @param originalFilename
	 *            原文件名
	 * @return 新文件名称
	 */
	public String getRandomFileName(String originalFilename) {
		return getUUID() + "." + FilenameUtils.getExtension(originalFilename);// 文件新名称
	}

	/**
	 * 描述 : 随机生成一个文件相对路径. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年9月16日 下午1:42:59
	 * @param originalFilename
	 *            原文件名
	 * @return 新文件相对路径
	 */
	public String getRandomRelativePath(String originalFilename) {
		return getRelativePath(getRandomFileName(originalFilename));// 新文件相对路径
	}

	/**
	 * 描述 : 拆分文件路径，有的对象附件是有多个，那么它的存储的文件路径是通过多个相对路径拼接而成的，<br>
	 * 中间以分隔符分割，这里通过分隔符分割将文件路径取出放入集合中. <br>
	 * 如： 原路径为-/2017/09/09/01.png;/2017/09/09/02.png;<br>
	 * 拆分后为-[/2017/09/09/01.png,/2017/09/09/02.png];<br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年9月16日 下午1:26:20
	 * @param filePaths
	 *            文件相对路径拼接的字符串
	 * @return 单个文件相对路径的集合
	 */
	public List<String> getFileRelativePaths(String filePaths) {
		return ICollections.strToStringList(filePaths, separator);
	}

	/**
	 * 描述 : 创建子目录 名称（根据日期：如当前时间 2017-09-15，生成路径 2017/09/15）. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年9月15日 上午11:48:57
	 * @param date
	 *            日期产生路径
	 * @return 文件夹路径
	 */
	public String getRelativeDir(Date date) {
		String format = DateUtil.format(DateUtil.DATE_SDF_DATE_PATH, date);
		String pathSeparator = "/";
		if (!format.startsWith(pathSeparator)) {
			format = pathSeparator + format;
		}
		if (!format.endsWith(pathSeparator)) {
			format = format + pathSeparator;
		}
		return format;
	}

	/**
	 * 描述 : 创建子目录 名称（根据日期：如当前时间 2017-09-15，生成路径 2017/09/15）. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年9月15日 上午11:48:57
	 * @return 文件夹路径，默认根据当前日期
	 */
	public String getRelativeDir() {
		return rootDir + getRelativeDir(new Date());
	}

	/**
	 * 描述 : 创建子目录 名称（根据日期：如当前时间 2017-09-15，生成路径 2017/09/15）. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年9月15日 上午11:48:57
	 * @return 文件夹路径
	 */
	public String getRelativePath(String filename) {
		return getRelativeDir() + filename;
	}

	/**
	 * 描述 : 创建子目录 名称（根据日期：如当前时间 2017-09-15，生成路径 2017/09/15）. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年9月15日 上午11:48:57
	 * @return 文件夹路径
	 */
	public String getRelativePath(Date date, String filename) {
		return rootDir + getRelativeDir(date) + filename;
	}

	/**
	 * 描述 : 获取绝对路径. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年9月16日 下午1:17:15
	 * @return 绝对路径
	 */
	public File getAbsoluteFile(String relativePath) {
		return new File(root, relativePath);
	}

	/**
	 * 描述 : 获取绝对路径. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年9月16日 下午1:17:15
	 * @return 绝对路径
	 */
	public File getAbsoluteFile(Date date, String filename) {
		return getAbsoluteFile(getRelativePath(date, filename));
	}

	/**
	 * 描述 : 获取绝对路径. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年9月16日 下午1:17:15
	 * @return 绝对路径
	 */
	public String getAbsolutePath(Date date, String filename) {
		return getAbsoluteFile(date, filename).getAbsolutePath();
	}

	/**
	 * 描述 : 根据相对路径删除文件. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年9月16日 下午1:21:20
	 */
	public void deleteFile(String relativePath) {
		if (StringUtils.isNotEmpty(relativePath)) {
			File file = getAbsoluteFile(relativePath);
			if (file.exists()) {
				file.delete();
			}
		}
	}

	/**
	 * 描述 :根据相对路径删除多个文件. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年9月16日 下午1:21:20
	 */
	public void deleteFiles(List<String> relativePaths) {
		if (ICollections.hasData(relativePaths)) {
			for (String relativePath : relativePaths) {
				deleteFile(relativePath);
			}
		}
	}

	/**
	 * 描述 :根据相对路径删除多个文件. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年9月16日 下午1:21:20
	 */
	public void deleteFiles(String filePaths) {
		deleteFiles(getFileRelativePaths(filePaths));
	}
}

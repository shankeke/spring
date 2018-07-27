package com.jusfoun.web.interceptor;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.jusfoun.common.message.exception.CoreException;
import com.jusfoun.common.message.result.ErrType;

/**
 * 描述 : 文件类型限制拦截器. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年10月11日 下午3:41:44
 */
public class FileTypeInterceptor extends HandlerInterceptorAdapter {

	private String fileSuffix;

	public FileTypeInterceptor(String fileSuffix) {
		this.fileSuffix = fileSuffix;
	}

	public String getFileSuffix() {
		return fileSuffix;
	}

	public void setFileSuffix(String fileSuffix) {
		this.fileSuffix = fileSuffix;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws CoreException {
		// 判断是否为文件上传请求
		if (request instanceof MultipartHttpServletRequest) {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			Map<String, MultipartFile> files = multipartRequest.getFileMap();
			Iterator<String> iterator = files.keySet().iterator();
			// 对多部件请求资源进行遍历
			String formKey = null;
			MultipartFile multipartFile = null;
			String filename = null;
			while (iterator.hasNext()) {
				formKey = (String) iterator.next();
				multipartFile = multipartRequest.getFile(formKey);
				filename = multipartFile.getOriginalFilename();
				// 判断是否为限制文件类型
				if (!checkFile(filename)) {
					// 限制文件类型，请求转发到原始请求页面，并携带错误提示信息
					throw new CoreException(ErrType.FILE_FORMAT_NOT_ALLOW_ERROR, "文件格式错误，仅支持：" + fileSuffix + "格式！");
				}
			}
		}
		return true;
	}

	/**
	 * 判断是否为允许的上传文件类型,true表示允许
	 */
	private boolean checkFile(String fileName) {
		// 获取文件后缀
		String suffix = FilenameUtils.getExtension(fileName);
		return (fileSuffix == null || fileSuffix.contains(suffix.trim().toLowerCase()));
	}
}
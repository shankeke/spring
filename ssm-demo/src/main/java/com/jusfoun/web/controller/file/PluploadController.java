package com.jusfoun.web.controller.file;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jusfoun.common.log.Logable;
import com.jusfoun.config.file.FileConfig;
import com.jusfoun.web.controller.file.plupload.Plupload;
import com.jusfoun.web.controller.file.plupload.PluploadService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "文件断点续传接口类", value = "文件断点续传接口类")
@Controller
@RequestMapping("/file")
public class PluploadController {

	@Autowired
	private FileConfig fileConfig;

	/**
	 * Plupload文件上传处理方法
	 */
	@ApiOperation(value = "文件断点续传接口", notes = "文件断点续传接口", hidden = false)
	@Logable(desc = "Plupload文件上传处理", fullPath = "Plupload文件上传处理")
	@RequestMapping(value = "/pluploadUpload", method = {RequestMethod.POST})
	public void upload(Plupload plupload, HttpServletRequest request, HttpServletResponse response) {
		plupload.setRequest(request);// 手动传入Plupload对象HttpServletRequest属性
		// 文件存储绝对路径,会是一个文件夹，项目相应Servlet容器下的"pluploadDir"文件夹，还会以用户唯一id作划分
		File dir = new File(fileConfig.getRoot(), fileConfig.getRelativeDir());
		if (!dir.exists()) {
			dir.mkdirs();// 可创建多级目录，而mkdir()只能创建一级目录
		}
		// 开始上传文件
		PluploadService.upload(plupload, dir, plupload.getName());
	}

}

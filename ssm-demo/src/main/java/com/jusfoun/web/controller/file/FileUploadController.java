package com.jusfoun.web.controller.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jusfoun.common.annotation.Logable;
import com.jusfoun.common.exception.ControllerException;
import com.jusfoun.common.result.BaseResponse;
import com.jusfoun.common.result.ErrType;
import com.jusfoun.common.util.io.IOUtil;
import com.jusfoun.config.file.FileConfig;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 描述 : 文件上传管理功能. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月15日 上午11:39:50
 */
@Api(description = "文件上传接口", value = "文件上传接口类")
@Controller
@RequestMapping("/file")
public class FileUploadController {

	@Autowired
	private FileConfig fileConfig;

	/**
	 * 描述 : 单个文件上传. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年9月15日 下午1:09:05
	 * @param file
	 *            文件
	 * @return 文件上传结果
	 */
	@ApiOperation(value = "单个文件上传", notes = "单个文件上传", hidden = false)
	@Logable(desc = "单个文件上传", fullPath = "单个文件上传")
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse<FileItem> upload(@ApiParam(required = true) @RequestParam MultipartFile file) {
		try {
			return BaseResponse.success(saveFile(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new ControllerException(ErrType.FILE_NOT_FOUND_ERROR);
		} catch (IOException e) {
			e.printStackTrace();
			throw new ControllerException(ErrType.FILE_IO_WRITE_ERROR);
		}
	}

	// 处理文件上传
	// 多文件上传
	@ApiOperation(value = "多个文件上传", notes = "多个文件上传", hidden = false)
	@Logable(desc = "多个文件上传", fullPath = "多个文件上传")
	@RequestMapping(value = "/uploadBatch", method = RequestMethod.POST)
	public BaseResponse<List<FileItem>> uploadBatch(@ApiParam(required = true) @RequestParam List<MultipartFile> file, HttpServletRequest request) {
		// List<MultipartFile> files = ((MultipartHttpServletRequest)
		// request).getFiles("file");
		List<FileItem> list = null;
		try {
			list = new ArrayList<FileItem>();
			for (MultipartFile f : file)
				list.add(saveFile(f));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new ControllerException(ErrType.FILE_NOT_FOUND_ERROR);
		} catch (IOException e) {
			e.printStackTrace();
			throw new ControllerException(ErrType.FILE_IO_WRITE_ERROR);
		}
		return BaseResponse.success(list);
	}

	/**
	 * 描述 : 保存文件到本地. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年9月15日 下午1:14:09
	 * @param file
	 *            文件
	 * @return 保存处理结果
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	private FileItem saveFile(MultipartFile file) throws IOException, FileNotFoundException {
		String contentType = file.getContentType();// 文件类型
		String originalFilename = file.getOriginalFilename();// 文件原名
		String fileName = fileConfig.getRandomFileName(originalFilename);// 文件新名称
		String relativeDir = fileConfig.getRelativeDir();
		File subDir = new File(fileConfig.getRoot(), relativeDir);
		// 创建子目录
		IOUtil.makdirs(subDir);
		// 文件保存
		// IOUtil.copy(file.getInputStream(), new FileOutputStream(new
		// File(subDir, fileName)));
		file.transferTo(new File(subDir, fileName));
		// 返回上传结果给前端
		// Map<String, Object> map = new HashMap<String, Object>();
		// map.put("contentType", contentType);
		// map.put("originalFilename", originalFilename);
		// map.put("fileName", fileName);
		// map.put("filePath", relativeDir + fileName);

		return new FileItem(contentType, originalFilename, fileName, relativeDir + fileName);
	}

	/**
	 * 描述 : 文件下载. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年9月15日 下午1:28:44
	 * @param json
	 *            参数
	 * @param response
	 *            相应
	 * @return null
	 */
	@ApiOperation(value = "文件下载", notes = "文件下载", hidden = false)
	@Logable(desc = "文件下载", fullPath = "文件下载")
	@RequestMapping(value = "/download", method = {RequestMethod.POST, RequestMethod.GET})
	public void download(@ApiParam(required = true) @RequestParam String filePath, HttpServletResponse response) {
		try {
			File file = new File(fileConfig.getRoot(), filePath);
			if (file.exists()) {
				response.setContentType("application/force-download");// 设置强制下载不打开
				response.addHeader("Content-Disposition", "attachment;fileName=" + file.getName());// 设置文件名
				IOUtil.write(file, response.getOutputStream());
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new ControllerException(ErrType.FILE_IO_READ_ERROR);
		}
	}
}

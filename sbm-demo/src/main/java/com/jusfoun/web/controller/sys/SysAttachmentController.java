package com.jusfoun.web.controller.sys;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Lists;
import com.jusfoun.common.log.Logable;
import com.jusfoun.common.message.exception.ControllerException;
import com.jusfoun.common.message.result.BaseResponse;
import com.jusfoun.common.message.result.ErrType;
import com.jusfoun.common.utils.EntityUtils;
import com.jusfoun.common.utils.io.IoUtils;
import com.jusfoun.config.file.FileConfig;
import com.jusfoun.entity.SysAttachment;
import com.jusfoun.service.SysAttachmentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 说明： 文件上传管理功能. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月15日 上午11:39:50
 */
@Api(tags = "SYS-SysAttachmentController", description = "文件上传接口", value = "文件上传接口类")
@RestController
@RequestMapping(value = {"/v1/sysattachment", "/app/sysattachment"})
public class SysAttachmentController {

	@Autowired
	private FileConfig fileConfig;

	@Autowired
	private SysAttachmentService sysAttachmentService;

	/**
	 * 说明： 单个文件上传. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年9月15日 下午1:09:05
	 * @param file
	 *            文件
	 * @param objId
	 *            对象ID
	 * @param objType
	 *            对象类型
	 * @return 文件上传结果
	 */
	@ApiOperation(value = "单个文件上传", notes = "单个文件上传", hidden = false)
	@Logable(value = "单个文件上传", path = "单个文件上传")
	@PostMapping("/upload")
	public BaseResponse<SysAttachment> upload(//
			@ApiParam(required = true) @RequestParam MultipartFile file, //
			@ApiParam("对象ID") @RequestParam(required = false) Long objId, //
			@ApiParam("对象类型") @RequestParam(required = false) Byte objType//
	) {
		try {
			SysAttachment t = saveFile(file, objId, objType);
			sysAttachmentService.insertSelective(t);
			return BaseResponse.success(t);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new ControllerException(ErrType.FILE_NOT_FOUND_ERROR);
		} catch (IOException e) {
			e.printStackTrace();
			throw new ControllerException(ErrType.FILE_IO_WRITE_ERROR);
		}
	}

	/**
	 * 说明：多文件上传. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年11月13日 下午5:05:22
	 * @param request
	 * @param file
	 *            文件列表
	 * @param objId
	 *            对象ID
	 * @param objType
	 *            对象类型
	 * @return
	 */
	@ApiOperation(value = "多个文件上传", notes = "多个文件上传", hidden = false)
	@Logable(value = "多个文件上传", path = "多个文件上传")
	@PostMapping("/uploadBatch")
	public BaseResponse<List<SysAttachment>> uploadBatch(//
			@ApiParam(value = "文件列表", required = true) @RequestParam List<MultipartFile> file, //
			@ApiParam("对象ID") @RequestParam(required = false) Long objId, //
			@ApiParam("对象类型") @RequestParam(required = false) Byte objType//
	) {
		List<SysAttachment> list = null;
		try {
			list = Lists.newArrayList();
			for (MultipartFile f : file)
				list.add(saveFile(f, objId, objType));

			sysAttachmentService.insertListSelective(list);
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
	 * 说明： 保存文件到本地. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年9月15日 下午1:14:09
	 * @param file
	 *            文件
	 * @return 保存处理结果
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	private SysAttachment saveFile(MultipartFile file, Long objId, Byte objType) throws IOException, FileNotFoundException {
		String contentType = file.getContentType();// 文件类型
		String originalName = file.getOriginalFilename();// 文件原名
		String fileName = fileConfig.getRandomFileName(originalName);// 文件新名称
		String relativeDir = fileConfig.getRelativeDir();
		File subDir = new File(fileConfig.getRoot(), relativeDir);
		// 创建子目录
		IoUtils.makdirs(subDir);
		// 文件保存
		file.transferTo(new File(subDir, fileName));
		// 返回上传结果给前端
		return new SysAttachment(contentType, originalName, fileName, relativeDir + fileName, objId, objType);
	}

	/**
	 * 说明： 文件下载. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年9月15日 下午1:28:44
	 * @param json
	 *            参数
	 * @param response
	 *            相应
	 */
	@ApiOperation(value = "文件下载", notes = "文件下载", hidden = false)
	@Logable(value = "文件下载", path = "文件下载")
	@GetMapping("/download")
	public void download(//
			@ApiParam(value = "附件记录ID") @RequestParam(required = false) Long id, //
			@ApiParam(value = "附件相对路径") @RequestParam(required = false) String filePath, //
			HttpServletResponse response//
	) {
		try {
			String fileName = null;
			if (null != id) {
				SysAttachment t = sysAttachmentService.selectByPrimaryKey(id);
				if (null != t) {
					fileName = t.getOriginalName();
					filePath = t.getFilePath();
				}
			}
			if (StringUtils.isNotEmpty(filePath)) {
				File file = new File(fileConfig.getRoot(), filePath);
				if (file.exists()) {
					response.setContentType("application/force-download");// 设置强制下载不打开
					response.addHeader("Content-Disposition", "attachment;fileName=" + EntityUtils.getDefaultIfNull(fileName, file.getName()));// 设置文件名
					IoUtils.write(file, response.getOutputStream());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new ControllerException(ErrType.FILE_IO_READ_ERROR);
		}
	}

	@ApiOperation(value = "删除附件", notes = "删除附件", hidden = false)
	@PostMapping("/delete")
	public BaseResponse<?> delete(//
			@ApiParam(value = "附件记录ID") @RequestParam(required = false) Long id, //
			@ApiParam(value = "附件相对路径") @RequestParam(required = false) String filePath//
	) {
		return BaseResponse.success(sysAttachmentService.deleteByIdOrPath(id, filePath));
	}
}

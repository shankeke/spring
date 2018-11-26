package com.jusfoun.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jusfoun.common.message.exception.ServiceException;
import com.jusfoun.common.message.result.ErrType;
import com.jusfoun.common.mybatis.mapper.MyBaseMapper;
import com.jusfoun.common.mybatis.mapper.MyIdableMapper;
import com.jusfoun.common.mybatis.mapper.extension.BaseExtensionSelectMapper;
import com.jusfoun.config.file.FileConfig;
import com.jusfoun.entity.SysAttachment;
import com.jusfoun.mapper.ds0.SysAttachmentMapper;
import com.jusfoun.service.SysAttachmentService;

@Service
public class SysAttachmentServiceImpl implements SysAttachmentService {
	@Autowired
	private SysAttachmentMapper sysAttachmentMapper;

	@Autowired
	private FileConfig fileConfig;

	@Override
	public BaseExtensionSelectMapper<SysAttachment> getBaseExtensionSelectMapper() {
		return sysAttachmentMapper;
	}

	@Override
	public MyBaseMapper<SysAttachment> getMyBaseMapper() {
		return sysAttachmentMapper;
	}

	@Override
	public MyIdableMapper<SysAttachment> getMyIdableMapper() {
		return sysAttachmentMapper;
	}

	@Override
	public boolean deleteByIdOrPath(Long id, String filePath) {
		if (null != id) {
			SysAttachment t = selectByPrimaryKey(id);
			if (t != null) {
				filePath = t.getFilePath();
				SysAttachmentService.super.deleteByPrimaryKey(id);
			}
		}
		if (!StringUtils.isEmpty(filePath)) {
			try {
				return fileConfig.deleteFile(filePath);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServiceException(ErrType.INTERNAL_SERVER_ERROR, "附件删除失败");
			}
		}
		return true;
	}

}

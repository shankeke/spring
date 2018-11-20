package com.jusfoun.service;

import com.jusfoun.common.base.service.BaseIdableExtensionService;
import com.jusfoun.entity.SysAttachment;

public interface SysAttachmentService extends BaseIdableExtensionService<SysAttachment> {

	/**
	 * 说明：根据附件ID或者文件相对路径删除附件. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年11月15日 下午4:13:38
	 * @param id
	 *            附件ID
	 * @param filePath
	 *            相对路径
	 * @return 删除结果
	 */
	boolean deleteByIdOrPath(Long id, String filePath);

}

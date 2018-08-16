package com.jusfoun.config.file.progress;

import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.ProgressListener;
import org.springframework.stereotype.Component;

@Component
public class UploadProgressListener implements ProgressListener {

	private HttpSession session;

	public void setSession(HttpSession session) {
		this.session = session;
		ProgressEntity status = new ProgressEntity();
		session.setAttribute("status", status);
	}

	/**
	 * @param pBytesRead
	 *            到目前为止读取文件的比特数
	 * @param pContentLength
	 *            文件总大小
	 * @param pItems
	 *            目前正在读取第几个文件
	 * @see org.apache.commons.fileupload.ProgressListener#update(long, long,
	 *      int)
	 */
	@Override
	public void update(long pBytesRead, long pContentLength, int pItems) {
		ProgressEntity status = (ProgressEntity) session.getAttribute("status");
		status.setpBytesRead(pBytesRead);
		status.setpContentLength(pContentLength);
		status.setpItems(pItems);
		// System.out.println("当前文件上传进度：" + status);
	}
}

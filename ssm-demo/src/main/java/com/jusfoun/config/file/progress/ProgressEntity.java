package com.jusfoun.config.file.progress;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class ProgressEntity implements Serializable {
	private static final long serialVersionUID = 1963581160005412309L;
	private long pBytesRead = 0L; // 到目前为止读取文件的比特数
	private long pContentLength = 0L; // 文件总大小
	private int pItems; // 目前正在读取第几个文件

	public long getpBytesRead() {
		return pBytesRead;
	}

	public void setpBytesRead(long pBytesRead) {
		this.pBytesRead = pBytesRead;
	}

	public long getpContentLength() {
		return pContentLength;
	}

	public void setpContentLength(long pContentLength) {
		this.pContentLength = pContentLength;
	}

	public int getpItems() {
		return pItems;
	}

	public void setpItems(int pItems) {
		this.pItems = pItems;
	}

	@Override
	public String toString() {
		float tmp = (float) pBytesRead;
		float result = tmp / pContentLength * 100;
		return "ProgressEntity [pBytesRead=" + pBytesRead + ", pContentLength=" + pContentLength + ", percentage="
				+ result + "% , pItems=" + pItems + "]";
	}
}

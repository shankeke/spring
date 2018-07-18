package com.shanke.task.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.shanke.task.MyTask;

@Component
public class MyTaskImpl implements MyTask {

	private static final Logger logger = LoggerFactory
			.getLogger(MyTaskImpl.class);

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.shanke.task.MyTask#excute()
	 */
	@Override
	// 每5秒执行一次
	@Scheduled(cron = "* * 0/5 * * ? ")
	// @Scheduled(fixedRate = 1000)
	public void excute() {
		// TODO Auto-generated method stub
		// logger.info("定时任务执行了一次......");
	}

	public static void main(String[] args) {
		String obj = "我不是王毛！";
		logger.debug("王大治说：%d", obj);
		logger.info("王大治说：%d", obj);
		logger.error("王大治说：%d", obj);
	}
}

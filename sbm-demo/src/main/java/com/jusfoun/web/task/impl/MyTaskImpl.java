package com.jusfoun.web.task.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 描述 : sql脚本执行定时任务. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年10月24日 下午2:22:04
 */
@Component
public class MyTaskImpl {

	private static final Logger log = LoggerFactory.getLogger(MyTaskImpl.class);

	@Scheduled(cron = "0 40 10 * * ?") // 每天10:40执行
	@Scheduled(cron = "0 0 19 * * ?") // 每天19执行
	public void excute() {
		log.info(String.format("定时任务执行，当前时间戳：%s", System.currentTimeMillis()));
	}
}

package com.jusfoun.web.listener;

import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.ContextStoppedEvent;

/**
 * 说明： 监听Spring Boot的启动、停止、重启、关闭. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年10月14日 上午10:26:18
 */
public class ApplicationEventListener implements ApplicationListener<ApplicationEvent> {

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		// 在这里可以监听到Spring Boot的生命周期
		if (event instanceof ApplicationEnvironmentPreparedEvent) { // 初始化环境变量
		} else if (event instanceof ApplicationPreparedEvent) { // 初始化完成
		} else if (event instanceof ContextRefreshedEvent) { // 应用刷新
		} else if (event instanceof ApplicationReadyEvent) {// 应用已启动完成
		} else if (event instanceof ContextStartedEvent) { // 应用启动，需要在代码动态添加监听器才可捕获
		} else if (event instanceof ContextStoppedEvent) { // 应用停止
		} else if (event instanceof ContextClosedEvent) { // 应用关闭
		} else {
		}
	}
}

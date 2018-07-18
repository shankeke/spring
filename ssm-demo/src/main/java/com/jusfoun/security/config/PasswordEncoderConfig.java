package com.jusfoun.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 描述 :配置全局的加密器. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年11月8日 下午2:48:02
 */
@Configuration
public class PasswordEncoderConfig {
	@Bean
	protected BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}

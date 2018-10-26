package com.jusfoun.security.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 说明：jwt属性配置. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年11月8日 下午3:05:41
 */
@Configuration
@ConfigurationProperties(prefix = "security.jwt")
public class JwtSettings {

	/**
	 * access_token有效期
	 */
	private Integer accessTokenExpIn;

	/**
	 * refresh_token有效期
	 */
	private Integer refreshTokenExpIn;

	/**
	 * token issuer
	 */
	private String tokenIssuer;

	/**
	 * token signing key
	 */
	private String tokenSigningKey;

	public Integer getAccessTokenExpIn() {
		return accessTokenExpIn;
	}

	public void setAccessTokenExpIn(Integer accessTokenExpIn) {
		this.accessTokenExpIn = accessTokenExpIn;
	}

	public Integer getRefreshTokenExpIn() {
		return refreshTokenExpIn;
	}

	public void setRefreshTokenExpIn(Integer refreshTokenExpIn) {
		this.refreshTokenExpIn = refreshTokenExpIn;
	}

	public String getTokenIssuer() {
		return tokenIssuer;
	}

	public void setTokenIssuer(String tokenIssuer) {
		this.tokenIssuer = tokenIssuer;
	}

	public String getTokenSigningKey() {
		return tokenSigningKey;
	}

	public void setTokenSigningKey(String tokenSigningKey) {
		this.tokenSigningKey = tokenSigningKey;
	}

}

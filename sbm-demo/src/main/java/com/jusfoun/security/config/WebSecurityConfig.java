package com.jusfoun.security.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jusfoun.security.ClientDetailsService;
import com.jusfoun.security.support.auth.RawBasicAuthenticationEntryPoint;
import com.jusfoun.security.support.auth.SkipPathRequestMatcher;
import com.jusfoun.security.support.auth.TokenAuthenticationProcessingFilter;
import com.jusfoun.security.support.auth.TokenAuthenticationProvider;
import com.jusfoun.security.support.cors.SimpleCorsFilter;
import com.jusfoun.security.support.login.LoginAuthenticationFailureHandler;
import com.jusfoun.security.support.login.LoginAuthenticationProvider;
import com.jusfoun.security.support.login.LoginAuthenticationSuccessHandler;
import com.jusfoun.security.support.login.LoginProcessingFilter;
import com.jusfoun.security.support.token.extract.adapter.TokenExtractAdapter;
import com.jusfoun.security.support.token.factory.SimpleTokenFactory;
import com.jusfoun.security.support.token.factory.TokenFactory;
import com.jusfoun.security.support.token.parser.JwtTokenParser;
import com.jusfoun.security.support.token.parser.TokenParser;
import com.jusfoun.security.support.token.verifier.SimpleTokenVerifier;
import com.jusfoun.security.support.token.verifier.TokenVerifier;
import com.jusfoun.service.TokenUserDetailsService;

/**
 * 说明： security安全配置. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年11月8日 下午3:06:05
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	public static final String TOKEN_HEADER_PARAM = "Authorization";// token参数名
	public static final String TOKEN_ENTRY_POINT = "/auth/token";// 获取token接口
	public static final String TOKEN_REFRESH_ENTRY_POINT = "/auth/refreshToken";// 刷新token接口
	public static final String TOKEN_REVOKE_ENTRY_POINT = "/auth/revokeToken";// 注销token接口
	public static final String TOKEN_AUTH_ENTRY_POINT = "/**";// 需要鉴权的路径

	@Autowired
	private TokenIgnoredConfig securityCustomConfig;

	@Autowired
	private JwtSettings jwtSettings;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private ClientDetailsService clientDetailsService;
	@Autowired
	private TokenUserDetailsService tokenUserDetailsService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private TokenExtractAdapter tokenExtractAdapter;
	@Autowired
	private TokenAuthenticationProvider tokenAuthenticationProvider;
	@Autowired
	private RawBasicAuthenticationEntryPoint basicAuthenticationEntryPoint;

	@Bean
	protected BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public LoginAuthenticationProvider loginAuthenticationProvider() {
		return new LoginAuthenticationProvider(userDetailsService, passwordEncoder(), clientDetailsService);
	}

	@Bean
	public AuthenticationSuccessHandler authenticationSuccessHandler() {
		return new LoginAuthenticationSuccessHandler(objectMapper, tokenFactory(), tokenUserDetailsService);
	}

	@Bean
	public AuthenticationFailureHandler authenticationFailureHandler() {
		return new LoginAuthenticationFailureHandler(objectMapper);
	}

	@Bean
	public TokenVerifier tokenVerifier() {
		return new SimpleTokenVerifier();
	}

	@Bean
	public TokenParser tokenParser() {
		// return new DesTokenParser(tokenVerifier());
		return new JwtTokenParser(tokenExtractAdapter, tokenVerifier(), jwtSettings);
	}

	@Bean
	public TokenFactory tokenFactory() {
		return new SimpleTokenFactory(tokenParser());
	}

	protected LoginProcessingFilter buildLoginProcessingFilter() {
		LoginProcessingFilter filter = new LoginProcessingFilter(TOKEN_ENTRY_POINT, authenticationSuccessHandler(), authenticationFailureHandler(), objectMapper,
				tokenExtractAdapter);
		filter.setAuthenticationManager(authenticationManager);
		return filter;
	}

	protected TokenAuthenticationProcessingFilter buildTokenAuthenticationProcessingFilter() {
		List<String> skipPaths = new ArrayList<String>();
		skipPaths.addAll(Arrays.asList(tokenSkipPaths));
		skipPaths.addAll(Arrays.asList(securityCustomConfig.getStaticResources()));
		skipPaths.addAll(Arrays.asList(securityCustomConfig.getAnyMethods()));
		skipPaths.addAll(Arrays.asList(securityCustomConfig.getGetMethods()));
		skipPaths.addAll(Arrays.asList(securityCustomConfig.getPostMethods()));
		SkipPathRequestMatcher matcher = new SkipPathRequestMatcher(skipPaths, TOKEN_AUTH_ENTRY_POINT);
		TokenAuthenticationProcessingFilter filter = new TokenAuthenticationProcessingFilter(matcher, authenticationFailureHandler(), tokenFactory());
		filter.setAuthenticationManager(authenticationManager);
		return filter;
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(securityCustomConfig.getStaticResources());
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) {
		auth.authenticationProvider(loginAuthenticationProvider()).authenticationProvider(tokenAuthenticationProvider);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http//
				.csrf().disable() //
				.exceptionHandling().authenticationEntryPoint(basicAuthenticationEntryPoint)//
				.and()//
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)//
				.and()//
				.authorizeRequests()//
				.antMatchers(TOKEN_ENTRY_POINT).fullyAuthenticated() //
				.antMatchers(TOKEN_REFRESH_ENTRY_POINT).permitAll()//
				.antMatchers(securityCustomConfig.getAnyMethods()).permitAll()//
				.antMatchers(HttpMethod.GET, securityCustomConfig.getGetMethods()).permitAll()//
				.antMatchers(HttpMethod.POST, securityCustomConfig.getPostMethods()).permitAll()//
				.antMatchers(TOKEN_AUTH_ENTRY_POINT).authenticated() //
				.and()//
				.addFilterBefore(corsFilter(), UsernamePasswordAuthenticationFilter.class)//
				.addFilterBefore(buildLoginProcessingFilter(), UsernamePasswordAuthenticationFilter.class)//
				.addFilterBefore(buildTokenAuthenticationProcessingFilter(), UsernamePasswordAuthenticationFilter.class);
	}

	public SimpleCorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", new CorsConfiguration());
		return new SimpleCorsFilter(source);
	}

	// 登录过滤器需要跳过的资源
	private static final String[] tokenSkipPaths = new String[]{ //
			TOKEN_REFRESH_ENTRY_POINT, //
			TOKEN_ENTRY_POINT//
	};
}

package com.jusfoun.security.exceptions;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.ProviderNotFoundException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedCredentialsNotFoundException;
import org.springframework.security.web.authentication.rememberme.CookieTheftException;
import org.springframework.security.web.authentication.rememberme.InvalidCookieException;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationException;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.security.web.authentication.www.NonceExpiredException;

import com.jusfoun.common.message.result.BaseResponse;
import com.jusfoun.common.message.result.ErrType;

/**
 * 认证异常处理器
 * 
 * @author yjw@jusfoun.com
 * @date 2017年11月14日 下午1:45:19
 */
public class AuthenticationExceptionHandler {

	public BaseResponse<?> handle(AuthenticationException e) {
		BaseResponse<?> result = null;
		if (e instanceof AccountExpiredException) {
			result = BaseResponse.fail(ErrType.AUTH_FAILED, "账户过期或锁定");
		} else if (e instanceof CredentialsExpiredException) {
			result = BaseResponse.fail(ErrType.AUTH_FAILED, "登录凭证失效");
		} else if (e instanceof DisabledException) {
			result = BaseResponse.fail(ErrType.AUTH_FAILED, "账户已停用，请联系管理员");
		} else if (e instanceof LockedException) {
			result = BaseResponse.fail(ErrType.AUTH_FAILED, "账户锁定或失效，请联系管理员");
		} else if (e instanceof AuthenticationCredentialsNotFoundException) {
			result = BaseResponse.fail(ErrType.AUTH_FAILED, "无效凭证");
		} else if (e instanceof AuthMethodNotSupportedException) {
			result = BaseResponse.fail(ErrType.AUTH_FAILED, "认证错误");
		} else if (e instanceof UnAuthorizedException) {
			result = BaseResponse.fail(ErrType.UN_AUTHORIZED, "未授权的请求");
		} else if (e instanceof InternalAuthenticationServiceException) {
			result = BaseResponse.fail(ErrType.UN_AUTHORIZED, "账户内部认证出错");
		} else if (e instanceof BadCredentialsException) {
			result = BaseResponse.fail(ErrType.AUTH_FAILED, "请输入正确的用户名或密码");
		} else if (e instanceof InsufficientAuthenticationException) {
			result = BaseResponse.fail(ErrType.UN_AUTHORIZED, "认证不足，不能登录系统");
		} else if (e instanceof NoGrantedAnyAuthorityException) {
			result = BaseResponse.fail(ErrType.UN_AUTHORIZED, "未分配任何权限，不能登录系统");
		} else if (e instanceof NonceExpiredException) {
			result = BaseResponse.fail(ErrType.AUTH_FAILED, "授权摘要失效，请重新获取");
		} else if (e instanceof PreAuthenticatedCredentialsNotFoundException) {
			result = BaseResponse.fail(ErrType.AUTH_FAILED, "前置凭证失效，请重新获取");
		} else if (e instanceof ProviderNotFoundException) {
			result = BaseResponse.fail(ErrType.AUTH_FAILED, "授权失败");
		} else if (e instanceof CookieTheftException) {
			result = BaseResponse.fail(ErrType.AUTH_FAILED, "无效的Cookie");
		} else if (e instanceof InvalidCookieException) {
			result = BaseResponse.fail(ErrType.AUTH_FAILED, "无效的Cookie");
		} else if (e instanceof SessionAuthenticationException) {
			result = BaseResponse.fail(ErrType.AUTH_FAILED, "错误的会话");
		} else if (e instanceof UsernameNotFoundException) {
			result = BaseResponse.fail(ErrType.AUTH_FAILED, "用户名错误");
		} else if (e instanceof TokenExpiredException) {
			result = BaseResponse.fail(ErrType.AUTH_FAILED, "认证过期");
		} else if (e instanceof RememberMeAuthenticationException) {
			result = BaseResponse.fail(ErrType.AUTH_FAILED, "认证失败");
		} else if (e instanceof ClientIdNotFoundException) {
			result = BaseResponse.fail(ErrType.AUTH_FAILED, "非法客户端");
		} else if (e instanceof ClientBadSecretException) {
			result = BaseResponse.fail(ErrType.AUTH_FAILED, "客户端未通过认证");
		} else if (e instanceof ClientException) {
			result = BaseResponse.fail(ErrType.AUTH_FAILED, "客户端认证失败");
		} else if (e instanceof TokenException) {
			result = BaseResponse.fail(ErrType.AUTH_FAILED, "认证错误");
		} else if (e instanceof AccountStatusException) {
			result = BaseResponse.fail(ErrType.AUTH_FAILED, "账户状态异常");
		} else if (e instanceof AuthenticationServiceException) {
			result = BaseResponse.fail(ErrType.AUTH_FAILED, "账户认证失败");
		} else {
			result = BaseResponse.fail(ErrType.AUTH_FAILED, "认证错误");
		}
		return result;
	}
}

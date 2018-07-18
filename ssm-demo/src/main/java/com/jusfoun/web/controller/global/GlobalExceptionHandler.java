package com.jusfoun.web.controller.global;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.jusfoun.common.exception.CoreException;
import com.jusfoun.common.result.BaseResponse;
import com.jusfoun.common.result.ErrType;
import com.jusfoun.security.exceptions.AuthenticationExceptionHandler;
import com.jusfoun.security.exceptions.UnAuthorizedException;

/**
 * 描述 : 系统异常统一处理. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月9日 下午2:10:32
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	@ResponseBody // 在返回自定义相应类的情况下必须有，这是@ControllerAdvice注解的规定
	public BaseResponse<?> exceptionHandler(Exception e, HttpServletRequest request, HttpServletResponse response) {
		BaseResponse<?> result = null;
		// 自定义异常错误处理
		if (e != null) {
			e.printStackTrace();
			if (e instanceof CoreException || CoreException.class.isAssignableFrom(e.getClass())) {
				CoreException e1 = (CoreException) e;
				String message = e1.getMessage();
				result = BaseResponse.exception(e1.getCode(), message != null ? message : "未知错误，错误代码：" + e1.getCode());
			} else if (e instanceof NoHandlerFoundException) {
				result = BaseResponse.exception(ErrType.NOT_FOUND);
			} else if (e instanceof AuthenticationException || AuthenticationException.class.isAssignableFrom(e.getClass())) {
				result = BaseResponse.exception(ErrType.AUTH_FAILED);
			} else if (e instanceof HttpMessageNotReadableException) {
				result = BaseResponse.exception(ErrType.PARAMETERS_IS_NULL_ERROR);
			} else if (e instanceof HttpRequestMethodNotSupportedException) {
				result = BaseResponse.exception(ErrType.PARAMETERS_IS_NULL_ERROR);
			} else if (e instanceof MaxUploadSizeExceededException || e instanceof SizeLimitExceededException) {
				result = BaseResponse.exception(ErrType.FILE_MAX_UPLOAD_SIZE_EXCEEDED_ERROR);
			} else if (e instanceof MultipartException) {
				result = BaseResponse.exception(ErrType.FILE_IO_READ_ERROR);
			} else if (e instanceof UnAuthorizedException) {
				response.setStatus(ErrType.UN_AUTHORIZED.getCode());
				result = BaseResponse.fail(ErrType.UN_AUTHORIZED, "未授权的请求");
			} else if (e instanceof AuthenticationException) {
				AuthenticationExceptionHandler handler = new AuthenticationExceptionHandler();
				response.setStatus(ErrType.AUTH_FAILED.getCode());
				result = handler.handle((AuthenticationException) e);
			}
		}

		if (result != null) {
			return result;
		}
		// 其他异常
		return BaseResponse.exception(ErrType.ERROR);
	}
}

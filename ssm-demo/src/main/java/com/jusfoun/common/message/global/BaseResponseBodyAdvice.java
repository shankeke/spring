package com.jusfoun.common.message.global;

import java.lang.reflect.Type;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.jusfoun.common.message.annotation.JsonBody;
import com.jusfoun.common.result.BaseResponse;

/**
 * 描述 :自定义的返回报文包装. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年12月18日 下午6:12:38
 */
// @ControllerAdvice
public class BaseResponseBodyAdvice implements ResponseBodyAdvice<Object> {

	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		Type type = returnType.getGenericParameterType();
		return !type.equals(BaseResponse.class) && (returnType.hasMethodAnnotation(ResponseBody.class)
				|| returnType.hasMethodAnnotation(JsonBody.class));
	}

	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
			Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
			ServerHttpResponse response) {
		if (body instanceof BaseResponse) { // 不处理BaseResponse
			return body;
		}
		return BaseResponse.success(body);
	}
}

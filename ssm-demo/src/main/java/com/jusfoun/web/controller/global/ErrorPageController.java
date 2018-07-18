//package com.jusfoun.web.controller.global;
//
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.web.ErrorAttributes;
//import org.springframework.boot.autoconfigure.web.ErrorController;
//import org.springframework.boot.autoconfigure.web.ErrorProperties;
//import org.springframework.boot.autoconfigure.web.ServerProperties;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.util.Assert;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.context.request.RequestAttributes;
//import org.springframework.web.context.request.ServletRequestAttributes;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.jusfoun.common.result.BaseResponse;
//import com.jusfoun.common.result.ErrType;
//
///**
// * 描述 :定制错误页面. <br>
// *
// * @author yjw@jusfoun.com
// * @date 2017年9月29日 上午11:34:45
// */
//@RestController
//@RequestMapping("/error")
//@EnableConfigurationProperties({ ServerProperties.class })
//public class ErrorPageController implements ErrorController {
//
//	private ErrorAttributes errorAttributes;
//
//	@Autowired
//	private ServerProperties serverProperties;
//
//	/**
//	 * 初始化ExceptionController
//	 *
//	 * @param errorAttributes
//	 */
//	@Autowired
//	public ErrorPageController(ErrorAttributes errorAttributes) {
//		Assert.notNull(errorAttributes, "ErrorAttributes must not be null");
//		this.errorAttributes = errorAttributes;
//	}
//
//	/**
//	 * 定义404的返回页面
//	 *
//	 * @param request
//	 * @param response
//	 * @return
//	 */
//	@RequestMapping(produces = "application/json", consumes = "application/json", value = "401")
//	@ResponseBody
//	public BaseResponse errorHtml401(HttpServletRequest request, HttpServletResponse response) {
//		response.setStatus(getStatus(request).value());
//		return BaseResponse.fail(ErrType.AUTH_FAILED);
//	}
//
//	/**
//	 * 定义404的返回页面
//	 *
//	 * @param request
//	 * @param response
//	 * @return
//	 */
//	@RequestMapping(produces = "text/html", value = "404")
//	public ModelAndView errorHtml404(HttpServletRequest request, HttpServletResponse response) {
//		response.setStatus(getStatus(request).value());
//		Map<String, Object> model = getErrorAttributes(request, isIncludeStackTrace(request, MediaType.TEXT_HTML));
//		return new ModelAndView("error/404", model);
//	}
//
//	/**
//	 * 定义400的返回页面
//	 *
//	 * @param request
//	 * @param response
//	 * @return
//	 */
//	@RequestMapping(produces = "text/html", value = "400")
//	public ModelAndView errorHtml400(HttpServletRequest request, HttpServletResponse response) {
//		response.setStatus(getStatus(request).value());
//		Map<String, Object> model = getErrorAttributes(request, isIncludeStackTrace(request, MediaType.TEXT_HTML));
//		return new ModelAndView("error/400", model);
//	}
//
//	/**
//	 * 定义500的返回页面
//	 *
//	 * @param request
//	 * @param response
//	 * @return
//	 */
//	@RequestMapping(produces = "text/html", value = "500")
//	public ModelAndView errorHtml500(HttpServletRequest request, HttpServletResponse response) {
//		response.setStatus(getStatus(request).value());
//		Map<String, Object> model = getErrorAttributes(request, isIncludeStackTrace(request, MediaType.TEXT_HTML));
//		return new ModelAndView("error/500", model);
//	}
//
//	/**
//	 * Determine if the stacktrace attribute should be included.
//	 *
//	 * @param request
//	 *            the source request
//	 * @param produces
//	 *            the media type produced (or {@code MediaType.ALL})
//	 * @return if the stacktrace attribute should be included
//	 */
//	protected boolean isIncludeStackTrace(HttpServletRequest request, MediaType produces) {
//		ErrorProperties.IncludeStacktrace include = this.serverProperties.getError().getIncludeStacktrace();
//		if (include == ErrorProperties.IncludeStacktrace.ALWAYS) {
//			return true;
//		}
//		if (include == ErrorProperties.IncludeStacktrace.ON_TRACE_PARAM) {
//			return getTraceParameter(request);
//		}
//		return false;
//	}
//
//	/**
//	 * 获取错误的信息
//	 *
//	 * @param request
//	 * @param includeStackTrace
//	 * @return
//	 */
//	private Map<String, Object> getErrorAttributes(HttpServletRequest request, boolean includeStackTrace) {
//		RequestAttributes requestAttributes = new ServletRequestAttributes(request);
//		return this.errorAttributes.getErrorAttributes(requestAttributes, includeStackTrace);
//	}
//
//	/**
//	 * 是否包含trace
//	 *
//	 * @param request
//	 * @return
//	 */
//	private boolean getTraceParameter(HttpServletRequest request) {
//		String parameter = request.getParameter("trace");
//		if (parameter == null) {
//			return false;
//		}
//		return !"false".equals(parameter.toLowerCase());
//	}
//
//	/**
//	 * 获取错误编码
//	 *
//	 * @param request
//	 * @return
//	 */
//	private HttpStatus getStatus(HttpServletRequest request) {
//		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
//		if (statusCode == null) {
//			return HttpStatus.INTERNAL_SERVER_ERROR;
//		}
//		try {
//			return HttpStatus.valueOf(statusCode);
//		} catch (Exception ex) {
//			return HttpStatus.INTERNAL_SERVER_ERROR;
//		}
//	}
//
//	/**
//	 * 实现错误路径,暂时无用
//	 *
//	 * @return
//	 */
//	@Override
//	public String getErrorPath() {
//		return "";
//	}
//}

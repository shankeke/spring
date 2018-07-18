package com.jusfoun.common.base;

import java.util.Date;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.jusfoun.common.util.DateConvertEditor;

/**
 * 描述: Controller基类. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2018年7月12日 下午2:08:27
 */
public class BaseController {

	/**
	 * 描述:将前台传递过来的日期格式的字符串，自动转化为Date类型. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年7月12日 下午2:07:59
	 * @param binder
	 */
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		binder.registerCustomEditor(Date.class, new DateConvertEditor());
	}
}

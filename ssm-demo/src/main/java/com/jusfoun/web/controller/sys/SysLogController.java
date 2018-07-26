package com.jusfoun.web.controller.sys;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jusfoun.common.base.BaseController;
import com.jusfoun.entity.SysLog;

import io.swagger.annotations.Api;

/**
 * 描述 : 系统日志管理. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月23日 上午10:07:10
 */
@Api(description = "操作日志管理", value = "操作日志管理接口类")
@RestController
@RequestMapping("/syslog")
public class SysLogController extends BaseController<SysLog, Long> {
}

package com.jusfoun.web.controller.sys;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jusfoun.common.annotation.Logable;
import com.jusfoun.common.base.BaseController;
import com.jusfoun.common.exception.ControllerException;
import com.jusfoun.common.mybatis.page.IPage;
import com.jusfoun.common.result.BaseResponse;
import com.jusfoun.common.result.ErrType;
import com.jusfoun.entity.SysLog;
import com.jusfoun.security.config.WebSecurityConfig;
import com.jusfoun.service.SysLogService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * 描述 : 系统日志管理. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月23日 上午10:07:10
 */
@Api(description = "操作日志管理", value = "操作日志管理接口类")
@RestController
@RequestMapping("/syslog")
public class SysLogController extends BaseController {
	@Autowired
	private SysLogService sysLogService;

	/**
	 * 描述 : 登录日志查询. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年9月23日 上午10:09:02
	 * @param sysGov
	 *            组织机构
	 * @return
	 */
	@ApiOperation(value = "查询操作日志信息", notes = "查询操作日志信息", hidden = false)
	@Logable(desc = "日志查询", fullPath = "系统管理/日志管理/日志查询列表")
	// @PreAuthorize("hasPermission('user', '/sysLog/save')")
	@RequestMapping(value = "/list", method = {RequestMethod.POST})
	public BaseResponse<IPage<SysLog>> save(//
			@ApiParam(value = "是否分页：分页-true，不分页-false，默认true", defaultValue = "true") @RequestParam(name = "pageable", defaultValue = "true") boolean pageable, //
			@ApiParam(value = "页码", defaultValue = "1") @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum, //
			@ApiParam(value = "页长", defaultValue = "10") @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize, //
			@ApiParam(value = "用户名") @RequestParam(required = false) String username, //
			@ApiParam(value = "操作类型：0-查询所有，1-查询登录日志", defaultValue = "0") @RequestParam(defaultValue = "0") Integer type //
	) {

		IPage<SysLog> page = new IPage<>(pageable, pageNum, pageSize, "create_time desc");
		try {
			Example example = new Example(SysLog.class);
			// example.setOrderByClause("create_time desc");
			Criteria criteria = example.createCriteria();

			if (StringUtils.isNotEmpty(username)) {
				username = StringUtils.defaultString(username, "");
				criteria.andLike("username", "%" + username + "%");
			}
			if (type > 0) {
				criteria.andEqualTo("requestUri", WebSecurityConfig.TOKEN_ENTRY_POINT);
			}
			page = sysLogService.selectPageByExample(example, page);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ControllerException(ErrType.SYSLOG_QUERY_LIST_ERROR);
		}
		return BaseResponse.success(page);
	}
}

package com.jusfoun.web.controller.sys;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.beust.jcommander.internal.Maps;
import com.jusfoun.common.annotation.Logable;
import com.jusfoun.common.enums.UsingStatus;
import com.jusfoun.common.enums.YesNoType;
import com.jusfoun.common.exception.ControllerException;
import com.jusfoun.common.mybatis.page.IPage;
import com.jusfoun.common.result.BaseResponse;
import com.jusfoun.common.result.ErrType;
import com.jusfoun.entity.SysUser;
import com.jusfoun.security.util.SecurityUtils;
import com.jusfoun.service.SysUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 描述 : 系统用户管理接口. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月23日 上午11:45:00
 */
@Api(value = "系统用户管理接口类", description = "系统用户管理接口类")
@RestController
@RequestMapping("/sysuser")
public class SysUserController {

	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * 系统用户初始化密码
	 */
	@Value("${system.user.init-password}")
	private String initPassword;

	/**
	 * 描述 :用户信息保存. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年9月23日 上午11:48:44
	 * @param sysUser
	 *            用户信息
	 * @return 结果
	 */
	@ApiOperation(value = "保存系统用户信息", notes = "保存系统用户信息", hidden = false)
	@Logable(desc = "保存系统用户", fullPath = "系统管理/用户管理/保存系统用户")
	@RequestMapping(value = "/save", method = {RequestMethod.POST})
	public BaseResponse<?> save(@ApiParam(value = "可只传非空的字段", required = true) @RequestBody SysUser sysUser) {
		if (sysUser == null) {
			return BaseResponse.fail(ErrType.PARAMETERS_IS_INVALIDAT_ERROR);
		}
		if (StringUtils.isEmpty(sysUser.getUsername())) {
			return BaseResponse.fail(ErrType.PARAMETERS_IS_INVALIDAT_ERROR, "用户名称不能为空");
		}

		// 检查数据是否已经存在
		SysUser record = new SysUser();
		record.setUsername(sysUser.getUsername());
		int count = sysUserService.selectCount(record);
		if (count > 0) {
			return BaseResponse.fail(ErrType.SYSUSER_ENTITY_EXIST_ERROR, "系统已存在同名的账户");
		}

		// 保存数据
		try {
			sysUser.setPassword(passwordEncoder.encode(StringUtils.defaultIfEmpty(sysUser.getPassword(), initPassword)));
			if (sysUser.getStatus() == null) {
				sysUser.setStatus(UsingStatus.NotEnabled.getValue());
			}
			if (sysUser.getGender() == null) {
				sysUser.setGender(YesNoType.NO.getValue());
			}
			sysUserService.insertWithCache(sysUser);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ControllerException(ErrType.SYSUSER_SAVE_ERROR);
		}
		return BaseResponse.success();
	}

	/**
	 * 描述 :用户信息更新. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年9月23日 上午11:48:44
	 * @param sysUser
	 *            用户信息
	 * @return 结果
	 */
	@ApiOperation(value = "修改系统用户信息", notes = "修改系统用户信息", hidden = false)
	@Logable(desc = "修改系统用户", fullPath = "系统管理/用户管理/修改系统用户")
	@RequestMapping(value = "/update", method = {RequestMethod.POST})
	public BaseResponse<?> update(@ApiParam(required = true) @RequestBody SysUser sysUser) {
		if (sysUser == null || sysUser.getId() == null) {
			return BaseResponse.fail(ErrType.PARAMETERS_IS_INVALIDAT_ERROR);
		}
		// 更新数据
		try {
			sysUserService.updateByPrimaryKeySelectiveWithCache(sysUser);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ControllerException(ErrType.SYSUSER_UPDATE_ERROR);
		}
		return BaseResponse.success();
	}

	/**
	 * 描述 :用户信息删除. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年9月23日 上午11:48:44
	 * @param sysUser
	 *            用户信息
	 * @return 结果
	 */
	@ApiOperation(value = "删除系统用户信息", notes = "删除系统用户信息", hidden = false)
	@Logable(desc = "删除系统用户", fullPath = "系统管理/用户管理/删除系统用户")
	@RequestMapping(value = "/delete", method = {RequestMethod.POST, RequestMethod.GET})
	public BaseResponse<?> delete(@ApiParam(value = "删除的记录ID", required = true) @RequestParam Long id) {
		try {
			sysUserService.deleteWithRoles(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ControllerException(ErrType.SYSUSER_DELETE_ERROR);
		}
		return BaseResponse.success();
	}

	/**
	 * 描述 :用户信息列表查询. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年9月23日 上午11:48:44
	 * @param params
	 *            用户信息
	 * @return 结果
	 */
	@ApiOperation(value = "查询系统用户列表信息", notes = "查询系统用户列表信息", hidden = false)
	@Logable(desc = "查询系统用户列表", fullPath = "系统管理/用户管理/查询系统用户列表")
	@RequestMapping(value = "/list", method = {RequestMethod.POST})
	public BaseResponse<IPage<SysUser>> list(//
			@ApiParam(value = "是否分页：分页-true，不分页-false，默认true", defaultValue = "true") @RequestParam(name = "pageable", defaultValue = "true") boolean pageable, //
			@ApiParam(value = "页码", defaultValue = "1") @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum, //
			@ApiParam(value = "页长", defaultValue = "10") @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize, //
			@ApiParam(value = "用户名") @RequestParam(required = false) String username, //
			@ApiParam(value = "操作类型：0-查询所有，1-查询登录日志", defaultValue = "0") @RequestParam(defaultValue = "0") Integer type //
	) {
		try {
			IPage<SysUser> page = new IPage<>(pageable, pageNum, pageSize, "id desc");
			page = sysUserService.selectPageWithAssociate(Maps.newHashMap(), page);
			return BaseResponse.success(page);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ControllerException(ErrType.SYSUSER_QUERY_LIST_ERROR);
		}
	}

	/**
	 * 描述 :用户信息详情查询. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年9月23日 上午11:48:44
	 * @param sysUser
	 *            用户信息
	 * @return 结果
	 */
	@ApiOperation(value = "查询系统用户详情", notes = "查询系统用户详情", hidden = false)
	@Logable(desc = "查询系统用户详情", fullPath = "系统管理/用户管理/查询系统用户详情")
	@RequestMapping(value = "/info", method = {RequestMethod.POST})
	public BaseResponse<SysUser> info(@ApiParam(value = "查询的记录ID", required = true) @RequestParam Long id) {
		try {
			SysUser sysUser = sysUserService.selectPKWithCache(id);
			sysUser.setPassword(null);
			return BaseResponse.success(sysUser);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ControllerException(ErrType.SYSUSER_QUERY_INFO_ERROR);
		}
	}

	/**
	 * 描述 :用户面密码重置. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年9月23日 上午11:48:44
	 * @param sysUser
	 *            用户信息
	 * @return 结果
	 */
	@ApiOperation(value = "重置用户密码", notes = "重置用户密码", hidden = false)
	@Logable(desc = "重置用户密码", fullPath = "系统管理/用户管理/重置用户密码")
	@RequestMapping(value = "/resetPass", method = {RequestMethod.POST})
	public BaseResponse<?> resetPass(@ApiParam(value = "重置密码的记录ID", required = true) @RequestParam Long id) {

		try {
			SysUser sysUser = new SysUser();
			sysUser.setId(id);
			sysUser.setPassword(passwordEncoder.encode(initPassword));
			sysUserService.updateByPrimaryKeySelectiveWithCache(sysUser);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ControllerException(ErrType.SYSUSER_UPDATE_PASSWORD_ERROR);
		}
		return BaseResponse.success();
	}

	/**
	 * 描述 : 修改密码. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年9月26日 下午6:47:51
	 * @return 处理结果
	 */
	@ApiOperation(value = "修改用户密码", notes = "修改用户密码", hidden = false)
	@Logable(desc = "修改用户密码", fullPath = "系统管理/用户管理/修改用户密码")
	@RequestMapping(value = "/modifyPass", method = {RequestMethod.POST})
	public BaseResponse<?> modifyPass(//
			@ApiParam(value = "原密码", required = true) @RequestParam String oldPassword, //
			@ApiParam(value = "新密码", required = true) @RequestParam String password //
	) {
		try {
			String username = SecurityUtils.getCurrentUserUsername();
			SysUser user = sysUserService.selectByUsername(username);

			if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
				return BaseResponse.fail(ErrType.SYSUSER_AUTH_PASSWORD_ERROR);
			}
			user.setPassword(passwordEncoder.encode(password));
			sysUserService.updateByPrimaryKeySelectiveWithCache(user);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ControllerException(ErrType.SYSUSER_UPDATE_PASSWORD_ERROR);
		}
		return BaseResponse.success();
	}

	/**
	 * 描述 : 设置角色 <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年9月26日 下午6:47:51
	 * @return 处理结果
	 */
	@ApiOperation(value = "修改用户角色", notes = "修改用户角色", hidden = false)
	@Logable(desc = "修改用户角色", fullPath = "系统管理/用户管理/修改用户角色")
	@RequestMapping(value = "/modifyRoles", method = {RequestMethod.POST})
	public BaseResponse<?> modifyRoles(@ApiParam(value = "用户信息，包含用户的角色信息", required = true) @RequestBody SysUser sysUser) {
		if (sysUser == null || sysUser.getId() == null) {
			return BaseResponse.fail(ErrType.PARAMETERS_IS_INVALIDAT_ERROR);
		}
		try {
			sysUserService.modifyRoles(sysUser);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ControllerException(ErrType.SYSUSER_UPDATE_PASSWORD_ERROR);
		}
		return BaseResponse.success();
	}
}

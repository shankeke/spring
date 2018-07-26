package com.jusfoun.web.controller.sys;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jusfoun.common.annotation.Logable;
import com.jusfoun.common.base.BaseController;
import com.jusfoun.common.exception.ControllerException;
import com.jusfoun.common.result.BaseResponse;
import com.jusfoun.common.result.ErrType;
import com.jusfoun.common.util.entry.EntityUtils;
import com.jusfoun.entity.SysGov;
import com.jusfoun.entity.SysUser;
import com.jusfoun.service.SysGovService;
import com.jusfoun.service.SysUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 描述 : 组织机构管理. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月23日 上午10:07:10
 */
@Api(description = "组织机构管理", value = "组织机构管理接口类")
@RestController
@RequestMapping("/sysgov")
public class SysGovController extends BaseController<SysGov, Long> {

	@Autowired
	private SysGovService sysGovService;

	@Autowired
	private SysUserService sysUserService;

	/**
	 * 描述 : 保存组织结构. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年9月23日 上午10:09:02
	 * @param sysGov
	 *            组织机构
	 * @return
	 */
	@ApiOperation(value = "保存组织机构信息", notes = "保存组织机构信息", hidden = false)
	@Logable(desc = "保存机构信息", fullPath = "系统管理/组织机构/保存机构信息")
	// @PreAuthorize("hasPermission('user', '/sysgov/save')")
	@RequestMapping(value = "/save", method = {RequestMethod.POST})
	public BaseResponse<?> save(@ApiParam(value = "组织信息，可只传需要的字段", required = true) @RequestBody SysGov sysGov) {
		if (sysGov == null) {
			return BaseResponse.fail(ErrType.PARAMETERS_IS_INVALIDAT_ERROR);
		}

		if (StringUtils.isEmpty(sysGov.getFullName())) {
			return BaseResponse.fail(ErrType.ERROR, "组织机构名称不能为空");
		}

		SysGov record = new SysGov();
		record.setFullName(sysGov.getFullName());
		int c = sysGovService.selectCount(record);
		if (c > 0) {
			return BaseResponse.fail(ErrType.SYSGOV_ENTITY_EXIST_ERROR);
		}
		try {
			if (sysGov.getParentId() == null) {
				sysGov.setParentId(0L);
			}
			sysGov.setShortName(sysGov.getFullName());
			sysGovService.insert(sysGov);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ControllerException(ErrType.SYSGOV_SAVE_ERROR);
		}
		return BaseResponse.success();
	}

	 

	/**
	 * 描述 : 删除组织结构. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年9月23日 上午10:09:02
	 * @param sysGov
	 *            组织机构
	 * @return
	 */
	@ApiOperation(value = "删除组织机构信息", notes = "删除组织机构信息", hidden = false)
	@Logable(desc = "删除机构信息", fullPath = "系统管理/组织机构/删除机构信息")
	// @PreAuthorize("hasPermission('user', '/sysgov/delete')")
	@RequestMapping(value = "/delete", method = {RequestMethod.POST, RequestMethod.GET})
	public BaseResponse<?> delete(@ApiParam(value = "删除的记录ID", required = true) @RequestParam Long id) {

		try {
			// 判断机构下是否有用户
			SysUser t = new SysUser();
			t.setGovId(id);
			int count = sysUserService.selectCount(t);
			if (count > 0) {
				return BaseResponse.fail(ErrType.SYSGOV_DELETE_ERROR, "该机构下有用户不能删除");
			}

			// 查看该机构是否有子节点
			SysGov t1 = new SysGov();
			t1.setParentId(id);
			int c1 = sysGovService.selectCount(t1);
			if (c1 > 0) {
				return BaseResponse.fail(ErrType.SYSGOV_DELETE_ERROR, "该机构下存在子机构不能删除");
			}

			// 删除机构
			sysGovService.deleteByPrimaryKey(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ControllerException(ErrType.SYSGOV_DELETE_ERROR);
		}
		return BaseResponse.success();
	}

	/**
	 * 描述 : 查询组织结构列表. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年9月23日 上午10:09:02
	 * @param sysGov
	 *            组织机构
	 * @return
	 */
	@ApiOperation(value = "查询组织机构列表信息", notes = "查询组织机构列表信息", hidden = false)
	@Logable(desc = "查询机构列表", fullPath = "系统管理/组织机构/查询机构列表")
	// @PreAuthorize("hasPermission('user', '/sysgov/list')")
	@RequestMapping(value = "/list", method = {RequestMethod.POST})
	public BaseResponse<List<SysGov>> list(@ApiParam(required = true) @RequestBody SysGov sysGov) {
		if (sysGov == null) {
			sysGov = new SysGov();
		}
		if (sysGov.getParentId() == null) {
			sysGov.setParentId(0L);
		}
		List<SysGov> list = null;
		try {
			list = sysGovService.selectListWithAssociate(EntityUtils.transBean2Map(sysGov));
		} catch (Exception e) {
			e.printStackTrace();
			throw new ControllerException(ErrType.SYSGOV_QUERY_LIST_ERROR);
		}
		return BaseResponse.success(list);
	}

 
}

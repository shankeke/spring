package com.jusfoun.web.controller.dict;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jusfoun.common.enums.AccountStatus;
import com.jusfoun.common.enums.EnumUtils;
import com.jusfoun.common.enums.EquipmentType;
import com.jusfoun.common.enums.OperatingStatus;
import com.jusfoun.common.enums.UsingStatus;
import com.jusfoun.common.enums.YesNoType;
import com.jusfoun.common.message.result.BaseResponse;
import com.jusfoun.common.message.result.ErrType;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 说明：常量枚举定义接口类. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2018年11月22日 上午9:19:17
 */
@Api(tags = "ENUM-EnumsController", description = "常量枚举定义", value = "常量枚举定义")
@RestController
@RequestMapping(value = {"/enums"})
public class EnumsController {

	@ApiOperation(value = "系统状态码", notes = "系统状态码列表，大于1000的状态值在开发阶段可能随业务变动，仅供参考", hidden = false)
	@GetMapping({"/errType"})
	public BaseResponse<Map<Object, String>> ErrType() {
		return BaseResponse.success(EnumUtils.values(ErrType.class));
	}

	@ApiOperation(value = "账户状态码", notes = "账户状态码", hidden = false)
	@GetMapping({"/accountStatus"})
	public BaseResponse<Map<Object, String>> AccountStatus() {
		return BaseResponse.success(EnumUtils.values(AccountStatus.class));
	}

	@ApiOperation(value = "使用状态码", notes = "使用状态码", hidden = false)
	@GetMapping({"/usingStatus"})
	public BaseResponse<Map<Object, String>> UsingStatus() {
		return BaseResponse.success(EnumUtils.values(UsingStatus.class));
	}

	@ApiOperation(value = "Boolean状态码", notes = "Boolean状态码", hidden = false)
	@GetMapping({"/yesNoType"})
	public BaseResponse<Map<Object, String>> YesNoType() {
		return BaseResponse.success(EnumUtils.values(YesNoType.class));
	}

	@ApiOperation(value = "设备类型状态码", notes = "设备类型状态码", hidden = false)
	@GetMapping({"/equipmentType"})
	public BaseResponse<Map<Object, String>> EquipmentType() {
		return BaseResponse.success(EnumUtils.values(EquipmentType.class));
	}

	@ApiOperation(value = "经营状态码", notes = "经营状态码", hidden = false)
	@GetMapping({"/operatingStatus"})
	public BaseResponse<Map<Object, String>> OperatingStatus() {
		return BaseResponse.success(EnumUtils.values(OperatingStatus.class));
	}
}

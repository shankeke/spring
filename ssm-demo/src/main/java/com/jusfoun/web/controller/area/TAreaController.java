package com.jusfoun.web.controller.area;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jusfoun.common.base.BaseController;
import com.jusfoun.common.result.BaseResponse;
import com.jusfoun.entity.TArea;
import com.jusfoun.service.TAreaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;

@Api(description = "地区信息管理", value = "地区信息管理接口类")
@RestController
@RequestMapping("/area")
public class TAreaController extends BaseController<TArea, Long> {

	@Autowired
	private TAreaService tAreaService;

	/**
	 * 描述: 获取地区树. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年7月24日 上午10:58:38
	 * @param areaName
	 *            地区名称，根据名称检索
	 * @return 地区树图根节点
	 */
	@RequestMapping(value = "getAreaTree", method = {RequestMethod.POST, RequestMethod.GET})
	public BaseResponse<TArea> getAreaTree(@ApiParam(value = "检索地区名称") @RequestParam(required = false) String areaName) {
		TArea root = tAreaService.selectAreaTree(areaName);
		return BaseResponse.success(root);
	}
}

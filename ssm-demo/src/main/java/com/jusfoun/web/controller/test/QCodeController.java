package com.jusfoun.web.controller.test;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jusfoun.common.exception.ControllerException;
import com.jusfoun.common.result.ErrType;
import com.jusfoun.common.util.zxing.Colors;
import com.jusfoun.common.util.zxing.ZXingUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(description = "测试二维码生成接口", value = "测试二维码生成接口类")
@RestController
@RequestMapping("/qcode")
public class QCodeController {

	@ApiOperation(value = "生成二维码", notes = "生成二维码", hidden = false)
	@RequestMapping(value = "/showCode", method = {RequestMethod.GET})
	public void showCode(//
			HttpServletResponse response, //
			@ApiParam(value = "内容", required = true) @RequestParam String content, //
			@ApiParam("宽度") @RequestParam(defaultValue = "300") int width, //
			@ApiParam("高度") @RequestParam(defaultValue = "300") int height, //
			@ApiParam("后缀") @RequestParam(defaultValue = "png") String format//
	) {
		try {
			// ZXingUtil.write(content, 300, 300, response.getOutputStream());
			ZXingUtil.writeImgToStream(content, width, height, format, ZXingUtil.DEF_CHARACTER_SET, Colors.random().getRGB(), Colors.random().getRGB(), response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ControllerException(ErrType.ERROR);
		}
	}

}

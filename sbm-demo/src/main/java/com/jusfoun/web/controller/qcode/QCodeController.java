package com.jusfoun.web.controller.qcode;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jusfoun.common.message.exception.ControllerException;
import com.jusfoun.common.message.result.ErrType;
import com.jusfoun.common.utils.zxing.ZXingUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "COMMON-QCodeController", description = "二维码生成接口", value = "二维码生成接口类")
@RestController
@RequestMapping("/qcode")
public class QCodeController {

	@ApiOperation(value = "生成二维码", notes = "生成二维码", hidden = false)
	@GetMapping("/getCode")
	public void getCode(//
			HttpServletResponse response, //
			@ApiParam(value = "内容", required = true) @RequestParam String content, //
			@ApiParam("宽度") @RequestParam(defaultValue = "300") int width, //
			@ApiParam("高度") @RequestParam(defaultValue = "300") int height, //
			@ApiParam("后缀") @RequestParam(defaultValue = "png") String format//
	) {
		try {
			ZXingUtils.write(content, 300, 300, response.getOutputStream());
			// ZXingUtils.writeImgToStream(content, width, height, format,
			// ZXingUtils.DEF_CHARACTER_SET, Colors.random().getRGB(),
			// Colors.random().getRGB(),
			// response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ControllerException(ErrType.ERROR);
		}
	}

}

package com.jusfoun.web.controller.test;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
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

@Api(description = "测试二维码生成接口", value = "测试二维码生成接口类")
@RestController
@RequestMapping("/qcode")
public class QCodeController {

	@ApiOperation(value = "生成二维码", notes = "生成二维码", hidden = false)
	@RequestMapping(value = "/showCode", method = {RequestMethod.GET})
	public void showCode(@RequestParam(name = "content", required = true) String content, HttpServletResponse response) {
		if (StringUtils.isEmpty(content)) {
			throw new ControllerException(ErrType.PARAMETERS_IS_INVALIDAT_ERROR);
		}
		try {
			// ZXingUtil.write(content, 300, 300, response.getOutputStream());
			ZXingUtil.writeImgToStream(content, ZXingUtil.DEF_WIDTH, ZXingUtil.DEF_HEIGHT, ZXingUtil.DEF_FORMAT, ZXingUtil.DEF_CHARACTER_SET, Colors.random().getRGB(),
					Colors.random().getRGB(), response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ControllerException(ErrType.ERROR);
		}
	}

}

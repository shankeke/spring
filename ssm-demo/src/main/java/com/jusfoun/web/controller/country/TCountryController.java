package com.jusfoun.web.controller.country;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Maps;
import com.jusfoun.common.base.controller.BasePageableAndIdableController;
import com.jusfoun.common.base.service.BaseIdableService;
import com.jusfoun.common.base.service.BaseService;
import com.jusfoun.common.util.freemarker.TemplateUtil;
import com.jusfoun.config.TemplatesConfig;
import com.jusfoun.entity.TCountry;
import com.jusfoun.entity.vo.TCountryTotalVo;
import com.jusfoun.service.TCountryService;

import freemarker.template.TemplateException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 描述:国家信息管理接口类. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2018年7月24日 下午12:49:39
 */
@Api(description = "国家信息管理", value = "国家信息管理接口类")
@RestController
@RequestMapping("/country")
public class TCountryController implements BasePageableAndIdableController<TCountry, Long> {

	@Autowired
	private TCountryService tCountryService;
	@Autowired
	private TemplatesConfig templatesConfig;

	@Override
	public BaseIdableService<TCountry> getBaseIdableService() {
		return tCountryService;
	}

	@Override
	public BaseService<TCountry> getBaseService() {
		return tCountryService;
	}

	@ApiOperation(value = "导出国家信息表", notes = "导出国家信息表，并根据首字母分组", hidden = false)
	@RequestMapping(value = "/export", method = { RequestMethod.POST, RequestMethod.GET })
	public void export(HttpServletResponse response) throws FileNotFoundException, IOException, TemplateException {
		// 汇总
		Map<String, Object> data = Maps.newHashMap();
		TCountryTotalVo counties = tCountryService.selectCounties();
		data.put("total", counties == null ? new TCountryTotalVo() : counties);

		response.setHeader("Content-Type", "application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder
				.encode(String.format("国家信息表-%s.%s", DateTime.now().toString("yyyyMMddHHmmss"), "xls"), "UTF-8"));
		response.setBufferSize(4096);
		TemplateUtil.process(templatesConfig.getDir(), templatesConfig.getCountries(), response.getOutputStream(),
				data);
	}
}

package com.jusfoun.web.controller.country;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jusfoun.common.base.BaseController;
import com.jusfoun.entity.TCountry;

import io.swagger.annotations.Api;

/**
 * 描述:国家信息管理接口类. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2018年7月24日 下午12:49:39
 */
@Api(description = "国家信息管理", value = "国家信息管理接口类")
@RestController
@RequestMapping("/country")
public class TCountryController extends BaseController<TCountry, Long> {
}

package com.jusfoun.web.controller.test;

import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jusfoun.common.log.Logable;
import com.jusfoun.common.message.annotation.JsonBody;
import com.jusfoun.common.message.annotation.JsonBodys;
import com.jusfoun.common.message.result.BaseResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("test")
@Api(tags = "TEST-TestController", value = "报文过滤测试用例", description = "报文过滤测试用例")
public class TestController {

	@ApiOperation(value = "测试Json序列化", notes = "测试Json序列化，验证实体字段过滤功能", hidden = false)
	@GetMapping("getJson")
	@JsonBodys({@JsonBody(type = Person.class, excludes = {"weight", "wealth"})})
	public BaseResponse<Person> getJson() {
		return BaseResponse.success(new Person(1L, "Tom", (byte) 12, new Date(), 256, 175.3F, 79.5F, 12000000000000D));
	}

	@ApiOperation(value = "测试报文自动包装", notes = "测试报文自动包装，并过滤字段", hidden = false)
	@RequestMapping(value = "getProducts", method = {RequestMethod.POST, RequestMethod.GET})
	@JsonBodys({@JsonBody(type = Product.class, includes = {"id", "name"})})
	@Logable(value = "查询产品列表", path = "产品管理/查询产品列表/自动包装", message = "'请求' + #n + '条产品信息'")
	public List<Product> productList(@ApiParam(value = "数据节点数") @RequestParam(defaultValue = "5") Integer n) {
		return Products.getProducts(n);
	}

	@ApiOperation(value = "测试报文包装", notes = "测试报文包装", hidden = false)
	@RequestMapping(value = "getProductList", method = {RequestMethod.POST, RequestMethod.GET})
	@JsonBodys({@JsonBody(type = Product.class, includes = {"id", "name"})})
	@Logable(value = "查询产品列表", path = "产品管理/查询产品列表/非自动包装", message = "'请求' + #n + '条产品信息'")
	public BaseResponse<List<Product>> getProductList(@ApiParam(value = "数据节点数") @RequestParam(defaultValue = "5") Integer n) {
		return BaseResponse.success(Products.getProducts(n));
	}

	@ApiOperation(value = "测试报文自动包装", notes = "测试报文自动包装，并过滤字段", hidden = false)
	@RequestMapping(value = "getProduct", method = {RequestMethod.POST, RequestMethod.GET})
	@JsonBodys({@JsonBody(type = Product.class, excludes = {"desc"})})
	public Product getProduct() {
		return new Product(1, "充电宝", 12.3d, "这个充电宝很贵呀");
	}
}

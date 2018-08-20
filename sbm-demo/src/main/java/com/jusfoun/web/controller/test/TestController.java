package com.jusfoun.web.controller.test;

import java.util.ArrayList;
import java.util.List;

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

@Api(description = "测试接口类", value = "测试接口类")
@RestController
@RequestMapping("/test")
public class TestController {

	@ApiOperation(value = "测试报文自动包装", notes = "测试报文自动包装，并过滤字段", hidden = false)
	@RequestMapping(value = "/getProducts", method = {RequestMethod.POST, RequestMethod.GET})
	@JsonBodys({@JsonBody(type = Product.class, includes = {"id", "name"})})
	@Logable(value = "查询产品列表", path = "产品管理/查询产品列表/自动包装", message = "'请求' + #n + '条产品信息'")
	public List<Product> productList(@ApiParam(value = "数据节点数") @RequestParam(defaultValue = "5") Integer n) {
		return getProducts(n);
	}

	@ApiOperation(value = "测试报文包装", notes = "测试报文包装", hidden = false)
	@RequestMapping(value = "/getProductList", method = {RequestMethod.POST, RequestMethod.GET})
	@JsonBodys({@JsonBody(type = Product.class, includes = {"id", "name"})})
	@Logable(value = "查询产品列表", path = "产品管理/查询产品列表/非自动包装", message = "'请求' + #n + '条产品信息'")
	public BaseResponse<List<Product>> getProductList(@ApiParam(value = "数据节点数") @RequestParam(defaultValue = "5") Integer n) {
		return BaseResponse.success(getProducts(n));
	}

	private List<Product> getProducts(int i) {
		List<Product> products = new ArrayList<Product>();
		Product p = null;
		for (int j = 0; j < i; j++) {
			p = new Product();
			p.setId(j);
			p.setName("产品" + j);
			p.setPrice(0.5 * j);
			p.setDesc("Product:" + p.getName() + "单价是" + p.getPrice() + "元。");
			products.add(p);
		}
		return products;
	}

	@ApiOperation(value = "测试报文自动包装", notes = "测试报文自动包装，并过滤字段", hidden = false)
	@RequestMapping(value = "/getP", method = {RequestMethod.POST, RequestMethod.GET})
	@JsonBodys({@JsonBody(type = Product.class, excludes = {"desc"})})
	public Product getP() {
		return new Product(1, "充电宝", 12.3d, "这个充电宝很贵呀");
	}

}

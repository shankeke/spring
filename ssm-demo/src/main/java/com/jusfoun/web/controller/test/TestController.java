package com.jusfoun.web.controller.test;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.jusfoun.common.jackson.annotation.Json;
import com.jusfoun.common.jackson.annotation.JsonBody;
import com.jusfoun.common.result.BaseResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "测试接口类", value = "测试接口类")
@RestController
@RequestMapping("/test")
public class TestController {

	@ApiOperation(value = "测试报文自动包装", notes = "测试报文自动包装，并过滤字段", hidden = false)
	@RequestMapping(value = "/getProducts", method = {RequestMethod.POST})
	@JsonBody({@Json(type = Product.class, includes = {"id", "name"})})
	public List<Product> productList(@RequestBody JSONObject params) {
		return getProducts(params.getIntValue("n"));
	}

	@ApiOperation(value = "测试报文包装", notes = "测试报文包装", hidden = false)
	@RequestMapping(value = "/getProductList", method = {RequestMethod.POST})
	@JsonBody({@Json(type = Product.class, includes = {"id", "name"})})
	public BaseResponse<List<Product>> getProductList(@RequestBody JSONObject params) {
		return BaseResponse.success(getProducts(params.getIntValue("n")));
	}

	// @RequestMapping(value = { "/", "index" })
	// @PreAuthorize("hasRole('ADMIN') AND hasRole('DBA')")
	// public String index() {
	// return "index";
	// }
	// @RequestMapping(value = { "/", "index" }, method = RequestMethod.GET)
	// // @PreAuthorize("hasRole('ADMIN') AND hasRole('DBA')")
	// public String index(Model model) {
	// List<Product> products = getProducts(10);
	// String xml = JaxbUtil.java2xml(new Products(products), Products.class);
	// Map<String, Object> map = new HashMap<String, Object>();
	// map.put("xml", xml);
	// map.put("products", products);
	// model.addAttribute("model", map);
	//
	// ModelAndView modelAndView = new ModelAndView("index.html");
	// return "index";
	// }

	@ApiOperation(value = "随机生成产品数据", notes = "随机生成产品数据", hidden = true)
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
	@JsonBody({@Json(type = Product.class, excludes = {"desc"})})
	public Product getP() {
		return new Product(1, "充电宝", 12.3d, "这个充电宝很贵呀");
	}

}

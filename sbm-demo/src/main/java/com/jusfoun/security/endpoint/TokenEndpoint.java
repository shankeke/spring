package com.jusfoun.security.endpoint;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.jusfoun.common.log.Logable;
import com.jusfoun.common.message.result.BaseResponse;
import com.jusfoun.security.RawGrantedToken;
import com.jusfoun.security.config.WebSecurityConfig;
import com.jusfoun.security.support.login.LoginRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 说明：获取令牌（该类在业务中没有实际意义，这里只是为了方便Swagger2生成获取token的接口文档而添加了该类，获取token的请求会在过滤器中提前过滤并且跟据执行情况返回了获取结果，这里的方法始终执行不到）.
 * <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年11月8日 下午3:09:50
 */
@Api(tags = "AUTH-TokenEndpoint", value = "获取令牌接口类", description = "获取令牌接口类")
@RestController
public class TokenEndpoint {

	/**
	 * 说明：获取用户信息. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年9月26日 下午1:52:04
	 * @param user
	 *            用户信息
	 * @return 获取的token信息
	 */
	@ApiOperation(value = "获取令牌", notes = "获取令牌", hidden = false)
	@Logable(value = "获取令牌", path = "获取令牌")
	@PostMapping({WebSecurityConfig.TOKEN_ENTRY_POINT})
	public BaseResponse<RawGrantedToken> token(//
			@ApiParam(value = "客户端令牌", required = true, defaultValue = "Basic d2ViX2NsaWVudDoxMjM0NTY=") @RequestHeader(name = WebSecurityConfig.TOKEN_HEADER_PARAM, required = true) String token, //
			@ApiParam(value = "登录账户及密码对象", required = true) @RequestBody LoginRequest request//
	) {
		return BaseResponse.success(new RawGrantedToken());
	}
}
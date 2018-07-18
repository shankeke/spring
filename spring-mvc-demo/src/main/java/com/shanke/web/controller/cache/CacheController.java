package com.shanke.web.controller.cache;

import java.util.Collection;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shanke.common.result.BaseResult;
import com.shanke.common.result.BaseResult.ResultType;
import com.shanke.service.cache.CacheService;

@Controller
@RequestMapping("/cache")
public class CacheController {

	private static final Logger logger = Logger
			.getLogger(CacheController.class);

	@Resource
	private CacheService cacheService;

	@RequestMapping("cache_list")
	@ResponseBody
	public BaseResult cacheList() {
		BaseResult result = new BaseResult();
		try {
			result.setType(ResultType.SUCC, cacheService.getCacheNames());
		} catch (Exception e) {
			logger.error("获取缓存列表失败", e);
			result.setType(ResultType.ERROR, null);
		}
		return result;
	}

	/**
	 * 描述 : 清理缓存. <br>
	 * http://202.99.207.241:7080/Itaxer/v2/Tax/cache_clear<br>
	 * <p>
	 * 
	 * @author Uknower-yjw
	 * @date 2016-5-4 下午7:55:41
	 * @return
	 */
	@RequestMapping(name = "cache_clear")
	@ResponseBody
	public BaseResult cacheClear(
			@RequestParam(name = "cacheName", required = true) String cacheName,//
			@RequestParam(name = "paramKey", required = true) String paramKey //
	) {
		BaseResult result = new BaseResult();
		try {
			if (StringUtils.isEmpty(paramKey)) {
				if (StringUtils.isEmpty(cacheName))
					cacheService.clear();
				else
					cacheService.clear(cacheName);
			} else {
				if (StringUtils.isEmpty(cacheName)) {
					Collection<String> cacheNames = cacheService
							.getCacheNames();
					for (String cache : cacheNames) {
						cacheService.evict(cacheName, paramKey);
						logger.debug("清理缓存...#" + cache + "#" + paramKey);
					}
				} else {
					cacheService.evict(cacheName, paramKey);
					logger.debug("清理缓存...#" + cacheName + "#" + paramKey);
				}
			}
			result.setType(ResultType.SUCC, "缓存清理成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("清空缓存失败", e);
			result.setType(ResultType.ERROR, "缓存清理失败");
		}
		return result;
	}

	/**
	 * 描述 :添加缓存. <br>
	 * <p>
	 * 
	 * @author Uknower-yjw
	 * @date 2016-5-4 下午7:50:46
	 * @return
	 */
	@RequestMapping("cache_put")
	@ResponseBody
	public BaseResult cachePut(
			@RequestParam(name = "cacheName", required = true) String cacheName,//
			@RequestParam(name = "paramKey", required = true) String paramKey,//
			@RequestParam(name = "paramValue", required = true) String paramValue//
	) {
		BaseResult result = new BaseResult();
		try {
			/**
			 * 如果缓存键（paramKey）没有传，则判断是否有缓存名称（cacheName），如果有清理该缓存名称对应的所有缓存，
			 * 否则清理所有缓存
			 */
			cacheService.put(cacheName, paramKey, paramValue);
			result.setType(ResultType.SUCC, "添加缓存成功");
		} catch (Exception e) {
			logger.error("添加缓存失败", e);
			result.setType(ResultType.SUCC, "添加缓存成功");
		}
		return result;
	}
}

package com.jusfoun.common.base.tree;

import java.io.Serializable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jusfoun.common.result.BaseResponse;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 描述: Controller基类. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2018年7月12日 下午2:08:27
 */
public interface BaseTreeableController<T extends Treeable<T>, PK extends Serializable> {

	/**
	 * 描述: 获取树图操作业务实例. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年7月26日 下午2:11:41
	 * @return 树图操作业务实例
	 */
	TreeableService<T, PK> getTreeableService();

	/**
	 * 描述: 获取树图. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年7月24日 上午10:58:38
	 * @param rootId
	 *            根节点主键
	 * @param keyword
	 *            检索关键字
	 * @return 树根节点及后代节点
	 */
	@ApiOperation(value = "获取树数据", notes = "根据根节点主键和关键字查询树根节点及后代节点，如果主根节点主键为空则查询主根及后代节点，如果关键字不为空，则根据关键字查询所有符合的节点并返回树状数据结构", hidden = false)
	@RequestMapping(value = "getTree", method = {RequestMethod.POST, RequestMethod.GET})
	default BaseResponse<T> getTree(//
			@ApiParam(value = "根节点主键") @RequestParam(required = false) PK rootId, //
			@ApiParam(value = "检索关键字") @RequestParam(required = false) String keyword//
	) {
		T root = getTreeableService().selectTree(rootId, keyword);
		return BaseResponse.success(root);
	}

}

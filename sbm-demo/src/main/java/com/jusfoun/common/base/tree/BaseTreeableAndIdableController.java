package com.jusfoun.common.base.tree;

import java.io.Serializable;

import org.springframework.web.bind.annotation.RestController;

import com.jusfoun.common.base.controller.BaseIdableController;
import com.jusfoun.common.base.id.Idable;

/**
 * 说明： 实现树结构数据和存在主键的实体Controller基类. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2018年7月12日 下午2:08:27
 */
@RestController
public interface BaseTreeableAndIdableController<T extends Treeable<T> & Idable<PK>, PK extends Serializable>
		extends
			BaseTreeableSelectController<T, PK>,
			BaseIdableController<T, PK> {
}

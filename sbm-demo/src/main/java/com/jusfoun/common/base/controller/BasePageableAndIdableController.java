package com.jusfoun.common.base.controller;

import java.io.Serializable;

import com.jusfoun.common.base.id.Idable;
import com.jusfoun.common.base.page.IPageable;

/**
 * 描述 :可分页并且可根据ID操作的接口定义. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2018年7月27日 下午11:36:50
 */
public interface BasePageableAndIdableController<T extends IPageable & Idable<PK>, PK extends Serializable>
		extends //
			BasePageableController<T>, //
			BaseIdableController<T, PK> //
{
}

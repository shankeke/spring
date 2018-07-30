package com.jusfoun.common.base.extend.preprocessor;

import java.lang.annotation.Annotation;

/**
 * 描述:实体对象抽象预处理器. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2018年7月30日 上午10:31:09
 */
public abstract class AbstractBaseEntityPreprocessor implements BaseEntityPreprocessor {

	@Override
	public Object process(Long userId, String realName, Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean supports(Annotation annotation, Object obj) {
		// TODO Auto-generated method stub
		return false;
	}

}

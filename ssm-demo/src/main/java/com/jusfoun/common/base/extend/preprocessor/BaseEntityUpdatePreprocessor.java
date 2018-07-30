package com.jusfoun.common.base.extend.preprocessor;

import java.lang.annotation.Annotation;
import java.util.Date;

import com.jusfoun.common.base.extend.annotation.PreUpdate;
import com.jusfoun.common.base.extend.entity.BaseEntity;

/**
 * 描述:继承<code>BaseEntity</code>类的实体对象更新前预处理器. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2018年7月30日 上午10:34:03
 */
public class BaseEntityUpdatePreprocessor implements BaseEntityPreprocessor {

	@Override
	public Object process(Long userId, String realName, Object obj) {
		if (obj == null) {
			return null;
		}
		BaseEntity<?> entity = (BaseEntity<?>) obj;
		entity.setUpdaterId(userId);
		entity.setUpdaterName(realName);
		entity.setUpdateDate(new Date());
		return entity;
	}

	@Override
	public boolean supports(Annotation annotation, Object obj) {
		return annotation.getClass().isAssignableFrom(PreUpdate.class) && obj.getClass().isAssignableFrom(BaseEntity.class);
	}
}

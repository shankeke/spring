package com.jusfoun.common.base.extend.adapter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.beust.jcommander.internal.Lists;
import com.jusfoun.common.base.extend.preprocessor.BaseEntityInsertPreprocessor;
import com.jusfoun.common.base.extend.preprocessor.BaseEntityUpdatePreprocessor;
import com.jusfoun.common.base.extend.preprocessor.EntityPreprocessor;
import com.jusfoun.common.utils.ICollections;

/**
 * 描述:实体预处理适配器. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2018年7月30日 上午10:22:13
 */
@Component
public class SimpleEntityPreprocessAdapter implements EntityPreprocessAdapter {

	/**
	 * 可用的预处理器集合
	 */
	private List<EntityPreprocessor> preprocessors;

	public SimpleEntityPreprocessAdapter() {
		this(Arrays.asList(new BaseEntityInsertPreprocessor(), new BaseEntityUpdatePreprocessor()));
	}

	public SimpleEntityPreprocessAdapter(List<EntityPreprocessor> preprocessors) {
		this.preprocessors = preprocessors;
	}

	@Override
	public void preprocess(Long userId, String realName, Annotation annotation, Object obj) {
		if (ICollections.hasElements(preprocessors)) {
			EntityPreprocessor preprocessor = null;
			Class<? extends Object> clazz = obj.getClass();
			if (Iterable.class.isAssignableFrom(clazz)) {
				Iterator<?> iterator = ((Iterable<?>) obj).iterator();
				iterator.forEachRemaining(t -> {
					handle(userId, realName, annotation, preprocessor, t);
				});
			} else if (clazz.isArray()) {
				for (int i = 0; i < Array.getLength(obj); i++) {
					handle(userId, realName, annotation, preprocessor, Array.get(obj, i));
				}
			} else {
				handle(userId, realName, annotation, preprocessor, obj);
			}
		}
	}

	private void handle(Long userId, String realName, Annotation annotation, EntityPreprocessor preprocessor, Object obj) {
		if (preprocessor == null) {
			preprocessor = get(annotation, obj.getClass());
		}
		if (preprocessor != null) {
			preprocessor.process(userId, realName, obj);
		}
	}

	@Override
	public EntityPreprocessAdapter add(EntityPreprocessor preprocessor) {
		if (ICollections.hasNoElements(preprocessors)) {
			preprocessors = Lists.newArrayList();
		}
		preprocessors.add(preprocessor);
		return this;
	}

	@Override
	public EntityPreprocessor get(Annotation annotation, Class<? extends Object> clazz) {
		for (EntityPreprocessor preprocessor : preprocessors) {
			if (preprocessor.supports(annotation, clazz)) {
				return preprocessor;
			}
		}
		return null;
	}

}

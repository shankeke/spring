package com.jusfoun.common.base.extend.adapter;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.beust.jcommander.internal.Lists;
import com.jusfoun.common.base.extend.preprocessor.BaseEntityInsertPreprocessor;
import com.jusfoun.common.base.extend.preprocessor.EntityPreprocessor;
import com.jusfoun.common.base.extend.preprocessor.BaseEntityUpdatePreprocessor;
import com.jusfoun.common.utils.list.IListUtil;

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

	/**
	 * 描述:预处理方法. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年7月30日 上午10:49:53
	 * @param userId
	 *            用户ID
	 * @param realName
	 *            用户姓名
	 * @param obj
	 *            参数对象
	 */
	@Override
	public void preprocess(Annotation annotation, Long userId, String realName, Object obj) {
		if (IListUtil.hasData(preprocessors)) {
			Class<? extends Object> clazz = obj.getClass();
			if (Iterable.class.isAssignableFrom(clazz)) {
				Iterable<?> ite = (Iterable<?>) obj;
				Iterator<?> iterator = ite.iterator();
				while (iterator.hasNext()) {
					Object next = iterator.next();
					for (EntityPreprocessor preprocessor : preprocessors) {
						if (preprocessor.supports(annotation, next.getClass())) {
							preprocessor.process(userId, realName, next);
						}
					}
				}
			} else {
				for (EntityPreprocessor processor : preprocessors) {
					if (processor.supports(annotation, clazz)) {
						processor.process(userId, realName, obj);
					}
				}
			}
		}
	}

	@Override
	public void add(EntityPreprocessor preprocessor) {
		if (IListUtil.hasNoData(preprocessors)) {
			preprocessors = Lists.newArrayList();
		}
		preprocessors.add(preprocessor);
	}
}

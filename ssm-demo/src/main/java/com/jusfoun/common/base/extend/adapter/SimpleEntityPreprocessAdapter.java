package com.jusfoun.common.base.extend.adapter;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.jusfoun.common.base.extend.preprocessor.BaseEntityInsertPreprocessor;
import com.jusfoun.common.base.extend.preprocessor.BaseEntityPreprocessor;
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

	private List<BaseEntityPreprocessor> preprocessors;

	public SimpleEntityPreprocessAdapter() {
		this(Arrays.asList(new BaseEntityInsertPreprocessor(), new BaseEntityUpdatePreprocessor()));
	}

	public SimpleEntityPreprocessAdapter(List<BaseEntityPreprocessor> preprocessors) {
		this.preprocessors = preprocessors;
	}

	public List<BaseEntityPreprocessor> getPreprocessors() {
		return preprocessors;
	}

	public void setPreprocessors(List<BaseEntityPreprocessor> preprocessors) {
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
	 *            实体对象
	 * @return 预处理之后的对象
	 */
	@Override
	public Object preprocess(Annotation annotation, Long userId, String realName, Object obj) {
		if (IListUtil.hasData(preprocessors)) {
			for (BaseEntityPreprocessor preprocessor : preprocessors) {
				if (preprocessor.supports(annotation, obj)) {
					return preprocessor.process(userId, realName, obj);
				}
			}
		}
		return obj;
	}
}

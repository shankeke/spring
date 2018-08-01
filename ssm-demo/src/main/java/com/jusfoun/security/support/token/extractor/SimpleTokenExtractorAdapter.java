package com.jusfoun.security.support.token.extractor;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.beust.jcommander.internal.Lists;
import com.jusfoun.common.utils.ICollections;
import com.jusfoun.security.exceptions.TokenInvalidException;

/**
 * 描述 : 默认实现令牌信息抽取处理器适配器. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年11月8日 下午3:00:48
 */
@Component
public class SimpleTokenExtractorAdapter implements TokenExtractorAdapter {

	/**
	 * 处理器集合
	 */
	private List<TokenExtractor> tokenExtractors;

	public SimpleTokenExtractorAdapter() {
		this(Arrays.asList(new BasicHeaderTokenExtractor(), new BearerHeaderTokenExtractor()));
	}

	public SimpleTokenExtractorAdapter(List<TokenExtractor> tokenExtractors) {
		this.tokenExtractors = tokenExtractors;
	}

	@Override
	public String excute(String payload) throws TokenInvalidException {
		if (ICollections.hasData(tokenExtractors)) {
			for (TokenExtractor tokenExtractor : tokenExtractors) {
				if (tokenExtractor.supports(payload)) {
					return tokenExtractor.extract(payload);
				}
			}
		}
		return payload;
	}

	@Override
	public void add(TokenExtractor tokenExtractor) {
		if (ICollections.hasNoData(tokenExtractors)) {
			tokenExtractors = Lists.newArrayList();
		}
		tokenExtractors.add(tokenExtractor);
	}

}

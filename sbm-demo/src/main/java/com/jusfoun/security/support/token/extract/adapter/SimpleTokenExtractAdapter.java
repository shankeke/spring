package com.jusfoun.security.support.token.extract.adapter;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.beust.jcommander.internal.Lists;
import com.jusfoun.common.utils.ICollections;
import com.jusfoun.security.exceptions.TokenInvalidException;
import com.jusfoun.security.support.token.extract.extractor.BasicHeaderTokenExtractor;
import com.jusfoun.security.support.token.extract.extractor.BearerHeaderTokenExtractor;
import com.jusfoun.security.support.token.extract.extractor.TokenExtractor;

/**
 * 说明： 默认实现令牌信息抽取处理器适配器. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年11月8日 下午3:00:48
 */
@Component
public class SimpleTokenExtractAdapter implements TokenExtractAdapter {

	/**
	 * 处理器集合
	 */
	private List<TokenExtractor> tokenExtractors;

	public SimpleTokenExtractAdapter() {
		this(Arrays.asList(new BasicHeaderTokenExtractor(), new BearerHeaderTokenExtractor()));
	}

	public SimpleTokenExtractAdapter(List<TokenExtractor> tokenExtractors) {
		this.tokenExtractors = tokenExtractors;
	}

	@Override
	public String handle(String payload) throws TokenInvalidException {
		TokenExtractor tokenExtractor = get(payload);
		if (tokenExtractor != null) {
			return tokenExtractor.extract(payload);
		}
		return payload;
	}

	@Override
	public void add(TokenExtractor tokenExtractor) {
		if (ICollections.hasNoElements(tokenExtractors)) {
			tokenExtractors = Lists.newArrayList();
		}
		tokenExtractors.add(tokenExtractor);
	}

	@Override
	public TokenExtractor get(String payload) {
		if (ICollections.hasElements(tokenExtractors)) {
			for (TokenExtractor tokenExtractor : tokenExtractors) {
				if (tokenExtractor.supports(payload)) {
					return tokenExtractor;
				}
			}
		}
		return null;
	}
}

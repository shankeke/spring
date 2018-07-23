package com.jusfoun.common.mybatis.typehandler.blobtype;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import com.alibaba.fastjson.JSON;
import com.jusfoun.security.RawGrantedAuthority;

/**
 * 描述 : Blob转权限集合TypeHandler. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年11月10日 下午12:44:32
 */
public class BlobVsAuthorityCollectionTypeHandler
		extends AbstractBlobTypeHandler<Collection<? extends GrantedAuthority>> {

	@Override
	public String translate2Str(Collection<? extends GrantedAuthority> t) {
		return JSON.toJSON(t).toString();
	}

	@Override
	public Collection<? extends GrantedAuthority> translate2Bean(String result) {
		return JSON.parseArray(result, RawGrantedAuthority.class);
	}

}

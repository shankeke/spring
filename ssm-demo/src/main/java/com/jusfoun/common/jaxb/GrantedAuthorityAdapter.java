package com.jusfoun.common.jaxb;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.springframework.security.core.GrantedAuthority;

import com.jusfoun.security.RawGrantedAuthority;

/**
 * 描述 :定义一个GrantedAuthority的jaxb的适配器. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年12月7日 上午11:48:59
 */
public class GrantedAuthorityAdapter
		extends XmlAdapter<GrantedAuthorityAdapter.Converter, Collection<? extends GrantedAuthority>> {

	@XmlType(name = "Converter")
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class Converter {

		@XmlElementWrapper(name = "authorities")
		@XmlElement(name = "authority")
		private Collection<RawGrantedAuthority> authorities;

		public Collection<RawGrantedAuthority> getAuthorities() {
			return authorities;
		}

		public void setAuthorities(Collection<RawGrantedAuthority> authorities) {
			this.authorities = authorities;
		}

	}

	@Override
	public Collection<? extends GrantedAuthority> unmarshal(Converter v) throws Exception {
		return v.getAuthorities();
	}

	@Override
	public Converter marshal(Collection<? extends GrantedAuthority> authorities) throws Exception {
		if (authorities == null || authorities.isEmpty()) {
			return null;
		}
		Set<RawGrantedAuthority> set = authorities.stream().map(t -> new RawGrantedAuthority(t.getAuthority()))
				.collect(Collectors.toSet());
		Converter converter = new Converter();
		converter.setAuthorities(set);
		return converter;
	}

}

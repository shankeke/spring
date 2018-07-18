package com.jusfoun.security;

import org.springframework.security.core.GrantedAuthority;

/**
 * 描述 :
 * 简单的授权包装类继承于<code>GrantedAuthority</code>，区别于<code>SimpleGrantedAuthority</code>，这里需要有空参的构造器，避免一些序列化框架在序列化或者反序列化时因为没有空参构造器而报错.
 * <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年12月22日 下午5:30:59
 */
public class RawGrantedAuthority implements GrantedAuthority {
	private static final long serialVersionUID = -6829971453246039500L;

	/**
	 * 权限值
	 */
	private String authority;

	public RawGrantedAuthority() {
	}

	public RawGrantedAuthority(String authority) {
		this.authority = authority;
	}

	@Override
	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authority == null) ? 0 : authority.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RawGrantedAuthority other = (RawGrantedAuthority) obj;
		if (authority == null) {
			if (other.authority != null)
				return false;
		} else if (!authority.equals(other.authority))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RawGrantedAuthority [authority=" + authority + "]";
	}

}

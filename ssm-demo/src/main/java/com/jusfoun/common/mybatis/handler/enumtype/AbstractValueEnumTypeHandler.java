package com.jusfoun.common.mybatis.handler.enumtype;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.type.BaseTypeHandler;

/**
 * 描述 :Value类型枚举处理器. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年12月23日 下午2:30:26
 */
public abstract class AbstractValueEnumTypeHandler<E extends Enum<E>, V> extends BaseTypeHandler<E> implements ResultValueHandler<V> {
	private Class<E> type;
	private Map<V, E> map = new HashMap<V, E>();

	@SuppressWarnings("unchecked")
	public AbstractValueEnumTypeHandler(Class<E> type) {
		if (type == null) {
			throw new IllegalArgumentException("Type argument cannot be null");
		}
		this.type = type;
		E[] enums = type.getEnumConstants();
		if (enums == null) {
			throw new IllegalArgumentException(type.getSimpleName() + " does not represent an enum type.");
		}
		ValueEnum<V> valueEnum = null;
		for (E e : enums) {
			valueEnum = (ValueEnum<V>) e;
			map.put(valueEnum.getValue(), e);
		}
	}

	@Override
	public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
		if (!rs.wasNull()) {
			return getNullableResult(getResultValue(rs, columnName));
		}
		return null;
	}

	@Override
	public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		if (!rs.wasNull()) {
			return getNullableResult(getResultValue(rs, columnIndex));
		}
		return null;
	}

	@Override
	public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		if (!cs.wasNull()) {
			return getNullableResult(getResultValue(cs, columnIndex));
		}
		return null;
	}

	public E getNullableResult(V value) {
		try {
			return map.get(value);
		} catch (Exception ex) {
			throw new IllegalArgumentException("Cannot convert " + value + " to " + type.getSimpleName() + " by value.", ex);
		}
	}
}

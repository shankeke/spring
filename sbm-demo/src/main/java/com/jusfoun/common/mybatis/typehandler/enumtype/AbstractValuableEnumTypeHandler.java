package com.jusfoun.common.mybatis.typehandler.enumtype;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.type.BaseTypeHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jusfoun.common.enums.valuable.Valuable;

/**
 * 说明：Value类型枚举处理器. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年12月23日 下午2:30:26
 */
public abstract class AbstractValuableEnumTypeHandler<E extends Enum<E>, V> extends BaseTypeHandler<E> implements ResultValuableHandler<V> {

	private static final Logger log = LoggerFactory.getLogger(AbstractValuableEnumTypeHandler.class);

	private Class<E> type;
	private Map<V, E> map = new HashMap<V, E>();

	@SuppressWarnings("unchecked")
	public AbstractValuableEnumTypeHandler(Class<E> type) {
		if (type == null) {
			throw new IllegalArgumentException("Type argument cannot be null");
		}
		this.type = type;
		E[] enums = type.getEnumConstants();
		if (enums == null) {
			throw new IllegalArgumentException(type.getSimpleName() + " does not represent an enum type.");
		}
		Valuable<V> valueEnum = null;
		for (E e : enums) {
			valueEnum = (Valuable<V>) e;
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
		} catch (Exception e) {
			log.error("获取枚举值失败", e);
			throw new IllegalArgumentException("Cannot convert " + value + " to " + type.getSimpleName() + " by value.", e);
		}
	}
}

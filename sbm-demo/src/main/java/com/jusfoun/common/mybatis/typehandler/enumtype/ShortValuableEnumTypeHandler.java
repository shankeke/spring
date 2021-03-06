package com.jusfoun.common.mybatis.typehandler.enumtype;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;

import com.jusfoun.common.enums.valuable.ShortValuable;

/**
 * 说明：Short value类型枚举处理器. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年12月23日 下午2:30:26
 */
public class ShortValuableEnumTypeHandler<E extends Enum<E>> extends AbstractValuableEnumTypeHandler<E, Short> {

	public ShortValuableEnumTypeHandler(Class<E> type) {
		super(type);
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
		ShortValuable valueEnum = (ShortValuable) parameter;
		ps.setShort(i, valueEnum.getValue());
	}

	@Override
	public Short getResultValue(ResultSet rs, String columnName) throws SQLException {
		return rs.getShort(columnName);
	}

	@Override
	public Short getResultValue(ResultSet rs, int columnIndex) throws SQLException {
		return rs.getShort(columnIndex);
	}

	@Override
	public Short getResultValue(CallableStatement cs, int columnIndex) throws SQLException {
		return cs.getShort(columnIndex);
	}

}

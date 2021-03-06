package com.jusfoun.common.mybatis.typehandler.enumtype;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;

import com.jusfoun.common.enums.valuable.ByteValuable;

/**
 * 说明：Byte value类型枚举处理器. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年12月23日 下午2:30:26
 */
public class ByteValuableEnumTypeHandler<E extends Enum<E>> extends AbstractValuableEnumTypeHandler<E, Byte> {
	public ByteValuableEnumTypeHandler(Class<E> type) {
		super(type);
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
		ByteValuable valueEnum = (ByteValuable) parameter;
		ps.setByte(i, valueEnum.getValue());
	}

	@Override
	public Byte getResultValue(ResultSet rs, String columnName) throws SQLException {
		return rs.getByte(columnName);
	}

	@Override
	public Byte getResultValue(ResultSet rs, int columnIndex) throws SQLException {
		return rs.getByte(columnIndex);
	}

	@Override
	public Byte getResultValue(CallableStatement cs, int columnIndex) throws SQLException {
		return cs.getByte(columnIndex);
	}

}

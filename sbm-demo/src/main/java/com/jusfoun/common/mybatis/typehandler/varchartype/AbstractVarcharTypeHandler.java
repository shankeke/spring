package com.jusfoun.common.mybatis.typehandler.varchartype;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.JdbcType;

import com.jusfoun.common.mybatis.typehandler.AbstractComplexTypeHandler;

/**
 * 描述 : Varchar类型字段转换抽象TypeHandler. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年11月10日 下午12:44:32
 */
public abstract class AbstractVarcharTypeHandler<T> extends AbstractComplexTypeHandler<T> {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, T t, JdbcType jdbcType) throws SQLException {
		ps.setString(i, translate2Str(t));
	}

	@Override
	public T getNullableResult(ResultSet rs, String columnName) throws SQLException {
		return getNullableResult(rs.getString(columnName));
	}

	@Override
	public T getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		return getNullableResult(cs.getString(columnIndex));
	}

	@Override
	public T getNullableResult(ResultSet rs, int columnName) throws SQLException {
		return getNullableResult(rs.getString(columnName));
	}

	/**
	 * 描述: 将Varchar数据转化为实体对象. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年1月18日 上午9:59:50
	 * @param result
	 *            字符字段值
	 * @return 转化后实体对象
	 */
	public T getNullableResult(String result) {
		if (StringUtils.isNotEmpty(result)) {
			return translate2Bean(result);
		}
		return null;
	}

}

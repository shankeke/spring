package com.jusfoun.common.mybatis.typehandler.enumtype;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 描述: 获取结果方法. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2018年1月26日 下午3:09:07
 */
public interface ResultValueHandler<T> {

	T getResultValue(ResultSet rs, String columnName) throws SQLException;

	T getResultValue(ResultSet rs, int columnIndex) throws SQLException;

	T getResultValue(CallableStatement cs, int columnIndex) throws SQLException;

}

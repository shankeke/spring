package com.jusfoun.common.mybatis.mapper;

import java.util.Set;

import tk.mybatis.mapper.MapperException;
import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.mapperhelper.EntityHelper;
import tk.mybatis.mapper.mapperhelper.SqlHelper;

/**
 * 描述 :SqlHelper扩展. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年12月7日 上午9:33:54
 */
public class MySqlHelper extends SqlHelper {

	public static String replaceIntoTable(Class<?> entityClass, String defaultTableName) {
		StringBuilder sql = new StringBuilder();
		sql.append("REPLACE INTO ");
		sql.append(getDynamicTableName(entityClass, defaultTableName));
		sql.append(" ");
		return sql.toString();
	}

	public static String columnsIfNotNullCondition(Class<?> entityClass, String entityName, boolean empty) {
		StringBuilder sql = new StringBuilder();
		sql.append("<trim prefix=\"(\" suffix=\")\" prefixOverrides=\"AND\">");
		// 获取全部列
		Set<EntityColumn> columnList = EntityHelper.getColumns(entityClass);
		for (EntityColumn column : columnList) {
			sql.append(getIfNotNull(entityName, column, " AND " + column.getColumnEqualsHolder(entityName), empty));
		}
		sql.append("</trim>");
		return sql.toString();
	}

	public static String pKColumnsCondition(Class<?> entityClass, String entityName) {
		StringBuilder sql = new StringBuilder();
		sql.append("<trim prefix=\"(\" suffix=\")\" prefixOverrides=\"AND\">");
		// 获取全部主键列
		Set<EntityColumn> pKcolumnList = EntityHelper.getPKColumns(entityClass);
		if (pKcolumnList == null || pKcolumnList.size() < 1) {
			throw new MapperException("该方法的实体类[" + entityClass.getCanonicalName() + "]中必须只有一个带有 @Id 注解的字段");
		}
		for (EntityColumn column : pKcolumnList) {
			sql.append(" AND " + column.getColumnEqualsHolder(entityName));
		}
		sql.append("</trim>");
		return sql.toString();
	}

	public static String wherePKColumnsByList(Class<?> entityClass, String entityName) {
		StringBuilder sql = new StringBuilder();
		sql.append("<where>");
		sql.append("<foreach collection=\"list\" separator=\" or\" item=\"").append(entityName)
				.append("\" index=\"index\">");
		sql.append(pKColumnsCondition(entityClass, entityName));
		sql.append("</foreach>");
		sql.append("</where>");
		return sql.toString();
	}

	public static String whereAllColumnsByList(Class<?> entityClass, String entityName, boolean empty) {
		StringBuilder sql = new StringBuilder();
		sql.append("<where>");
		sql.append("<foreach collection=\"list\" separator=\" or\" item=\"").append(entityName)
				.append("\" index=\"index\">");
		sql.append(columnsIfNotNullCondition(entityClass, entityName, empty));
		sql.append("</foreach>");
		sql.append("</where>");
		return sql.toString();
	}
}

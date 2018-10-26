package com.jusfoun.common.mybatis.mapper.select;

import java.util.Set;

import org.apache.ibatis.mapping.MappedStatement;

import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.mapperhelper.EntityHelper;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.MapperTemplate;
import tk.mybatis.mapper.mapperhelper.SqlHelper;

/**
 * 说明：根据主键集合查询. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年12月22日 下午1:54:00
 */
public class SelectByPrimaryKeysProvider extends MapperTemplate {

	public SelectByPrimaryKeysProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
		super(mapperClass, mapperHelper);
	}

	/**
	 * 说明： 根据主键集合进行查询，类中只有存在一个带有@Id注解的字段，如果对应表存在联合主键或者没有主键则生成空实现，即sql为空. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2017年12月23日 下午12:43:55
	 * @param ms
	 *            映射语句
	 * @return 动态sql
	 */
	public String selectByPrimaryKeys(MappedStatement ms) {
		final Class<?> entityClass = getEntityClass(ms);
		// 将返回值修改为实体类型
		setResultType(ms, entityClass);
		StringBuilder sql = new StringBuilder();
		sql.append(SqlHelper.selectAllColumns(entityClass));
		sql.append(SqlHelper.fromTable(entityClass, tableName(entityClass)));
		Set<EntityColumn> columnList = EntityHelper.getPKColumns(entityClass);
		if (columnList.size() == 1) {
			EntityColumn column = columnList.iterator().next();
			sql.append("<where>");
			sql.append(column.getColumn());
			sql.append(" in ");
			sql.append("<foreach collection=\"list\" item=\"").append(column.getProperty()).append("\" open=\"(\" separator=\",\" close=\")\" index=\"index\" >");
			sql.append(column.getColumnHolder(null, null, null));
			sql.append("</foreach>");
			sql.append("</where>");
		} else {
			return "";
			// throw new MapperException("继承 selectByPrimaryKeys 方法的实体类[" +
			// entityClass.getCanonicalName() + "]中必须有且只有一个带有 @Id注解的字段");
		}
		return sql.toString();
	}

}

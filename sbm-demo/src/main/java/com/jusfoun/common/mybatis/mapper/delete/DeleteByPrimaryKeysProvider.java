package com.jusfoun.common.mybatis.mapper.delete;

import java.util.Set;

import org.apache.ibatis.mapping.MappedStatement;

import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.mapperhelper.EntityHelper;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.MapperTemplate;
import tk.mybatis.mapper.mapperhelper.SqlHelper;

/**
 * 描述 :根据主键集合批量删除提供器. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年12月22日 下午1:54:00
 */
public class DeleteByPrimaryKeysProvider extends MapperTemplate {

	public DeleteByPrimaryKeysProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
		super(mapperClass, mapperHelper);
	}

	/**
	 * 描述 :通过主键集合批量删除，并且该类只能有一个主键，如果对应表存在联合主键或者没有主键则生成空实现，即sql为空. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2017年12月23日 上午11:56:32
	 * @param ms
	 *            映射语句
	 * @return 根据主键集合批量删除动态sql
	 */
	public String deleteByPrimaryKeys(MappedStatement ms) {
		final Class<?> entityClass = getEntityClass(ms);
		StringBuilder sql = new StringBuilder();
		sql.append(SqlHelper.deleteFromTable(entityClass, tableName(entityClass)));
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
			// throw new MapperException("继承 deleteByPrimaryKeys 方法的实体类[" +
			// entityClass.getCanonicalName() + "]中必须有且只有一个带有 @Id注解的字段");
		}
		return sql.toString();
	}

}

//package com.shanke.common.jpa;
//
//import java.beans.Introspector;
//import java.beans.PropertyDescriptor;
//import java.io.Serializable;
//import java.lang.reflect.Field;
//import java.lang.reflect.Method;
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.persistence.EmbeddedId;
//import javax.persistence.Entity;
//import javax.persistence.EntityManager;
//import javax.persistence.Id;
//import javax.persistence.Query;
//
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.support.JpaEntityInformation;
//import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
//import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
//import org.springframework.data.repository.NoRepositoryBean;
//
//import com.shanke.utils.data.QueryResult;
//import com.shanke.utils.data.ReflectHelper;
//import com.shanke.utils.data.UUIDUtil;
//
///**
// * 描述 :自定义repository的方法接口实现类,该类主要提供自定义的公用方法 . <br>
// * <p>
// * 
// * Copyright (c) 2016 优识云创(YSYC)
// * 
// * @author Uknower-yjw
// * @date 2016年8月1日 下午8:33:47
// * @param <T>
// * @param <ID>
// */
//@NoRepositoryBean
//public class BaseRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID>
//		implements BaseRepository<T, ID> {
//
//	/**
//	 * 持久化上下文
//	 */
//	private final EntityManager entityManager;
//
//	/**
//	 * 构造函数
//	 * 
//	 * @param domainClass
//	 * @param em
//	 */
//	public BaseRepositoryImpl(final JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
//		super(entityInformation, entityManager);
//		this.entityManager = entityManager;
//	}
//
//	/**
//	 * 构造函数
//	 * 
//	 * @param domainClass
//	 * @param em
//	 */
//	public BaseRepositoryImpl(Class<T> domainClass, EntityManager em) {
//		this(JpaEntityInformationSupport.getEntityInformation(domainClass, em), em);
//	}
//
//	@Override
//	public void store(Object... item) {
//		if (null != item) {
//			for (Object entity : item) {
//				innerSave(entity);
//			}
//		}
//	}
//
//	@Override
//	public void update(Object... item) {
//		if (null != item) {
//			for (Object entity : item) {
//				entityManager.merge(entity);
//			}
//		}
//	}
//
//	@Override
//	public int executeUpdate(String qlString, Object... values) {
//		Query query = entityManager.createQuery(qlString);
//		if (values != null) {
//			for (int i = 0; i < values.length; i++) {
//				query.setParameter(i + 1, values[i]);
//			}
//		}
//		return query.executeUpdate();
//	}
//
//	@Override
//	public int executeUpdate(String qlString, Map<String, Object> params) {
//		Query query = entityManager.createQuery(qlString);
//		for (String name : params.keySet()) {
//			query.setParameter(name, params.get(name));
//		}
//		return query.executeUpdate();
//	}
//
//	@Override
//	public int executeUpdate(String qlString, List<Object> values) {
//		Query query = entityManager.createQuery(qlString);
//		for (int i = 0; i < values.size(); i++) {
//			query.setParameter(i + 1, values.get(i));
//		}
//		return query.executeUpdate();
//	}
//
//	/**
//	 * 保存对象
//	 * 
//	 * @param item
//	 *            保存对象
//	 * @return
//	 */
//	private Serializable innerSave(Object item) {
//		try {
//			if (item == null)
//				return null;
//			Class<?> clazz = item.getClass();
//			Field idField = ReflectHelper.getIdField(clazz);
//			Method getMethod = null;
//			if (idField != null) {
//				Class<?> type = idField.getType();
//				Object val = idField.get(item);
//				if (type == String.class && (val == null || "".equals(val))) {
//					idField.set(item, UUIDUtil.uuid());
//				}
//			} else {
//				Method[] methods = clazz.getDeclaredMethods();
//				for (Method method : methods) {
//					Id id = method.getAnnotation(Id.class);
//					if (id != null) {
//						Object val = method.invoke(item);
//						if (val == null || "".equals(val)) {
//							String methodName = "s" + method.getName().substring(1);
//							Method setMethod = clazz.getDeclaredMethod(methodName, method.getReturnType());
//							if (setMethod != null) {
//								setMethod.invoke(item, UUIDUtil.uuid());
//							}
//						}
//						getMethod = method;
//						break;
//					}
//				}
//			}
//			entityManager.persist(item);
//			entityManager.flush();
//			if (idField != null) {
//				return (Serializable) idField.get(item);
//			}
//			if (getMethod != null) {
//				return (Serializable) getMethod.invoke(item);
//			}
//			return null;
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new RuntimeException(e);
//		}
//	}
//
//	// ########################################################################
//
//	@Override
//	public void setQueryParams(Query query, Object[] queryParams) {
//
//		if (null != queryParams && queryParams.length != 0) {
//			for (int i = 0; i < queryParams.length; i++) {
//				query.setParameter(i + 1, queryParams[i]);
//			}
//		}
//	}
//
//	@Override
//	public String buildOrderby(LinkedHashMap<String, String> orderby) {
//		// TODO Auto-generated method stub
//		StringBuffer orderbyql = new StringBuffer("");
//		if (orderby != null && orderby.size() > 0) {
//			orderbyql.append(" order by ");
//			for (String key : orderby.keySet()) {
//				orderbyql.append("o.").append(key).append(" ").append(orderby.get(key)).append(",");
//			}
//			orderbyql.deleteCharAt(orderbyql.length() - 1);
//		}
//		return orderbyql.toString();
//	}
//
//	@Override
//	public String getEntityName(Class<T> entityClass) {
//		// TODO Auto-generated method stub
//		String entityname = entityClass.getSimpleName();
//		Entity entity = entityClass.getAnnotation(Entity.class);
//		if (entity.name() != null && !"".equals(entity.name())) {
//			entityname = entity.name();
//		}
//		return entityname;
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public QueryResult<T> getScrollDataByJpql(Class<T> entityClass, String whereJpql, Object[] queryParams,
//			LinkedHashMap<String, String> orderby, Pageable pageable) {
//
//		QueryResult<T> qr = new QueryResult<T>();
//		String entityname = getEntityName(entityClass);
//		String sql = "select o from " + entityname + " o ";
//		String sqlWhere = whereJpql == null ? "" : "where " + whereJpql;
//		Query query = entityManager.createQuery(sql + sqlWhere + buildOrderby(orderby));
//
//		setQueryParams(query, queryParams);
//		if (pageable.getPageNumber() != -1 && pageable.getPageSize() != -1)
//			query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize())
//					.setMaxResults(pageable.getPageSize());
//		qr.setResultList(query.getResultList());
//
//		query = entityManager
//				.createQuery("select count(" + getCountField(entityClass) + ") from " + entityname + " o " + sqlWhere);
//		setQueryParams(query, queryParams);
//		qr.setTotalRecord((Long) query.getSingleResult());
//
//		return qr;
//	}
//
//	@Override
//	public QueryResult<T> getScrollDataBySql(String sql, Object[] queryParams, Pageable pageable) {
//		// TODO Auto-generated method stub
//		// 查询记录数
//		QueryResult<T> qr = new QueryResult<T>();
//		Query query = entityManager.createNativeQuery(sql);
//		setQueryParams(query, queryParams);
//		if (pageable.getPageNumber() != -1 && pageable.getPageSize() != -1)
//			query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize())
//					.setMaxResults(pageable.getPageSize());
//		qr.setResultList(query.getResultList());
//
//		//
//		String from = getFromClause(sql);
//		// 查询总记录数
//		query = entityManager.createQuery("select count(*) " + from);
//		setQueryParams(query, queryParams);
//		qr.setTotalRecord((Long) query.getSingleResult());
//		return qr;
//	}
//
//	private String getCountField(Class<T> clazz) {
//
//		String out = "o";
//		try {
//			PropertyDescriptor[] propertyDescriptors = Introspector.getBeanInfo(clazz).getPropertyDescriptors();
//			for (PropertyDescriptor propertydesc : propertyDescriptors) {
//				Method method = propertydesc.getReadMethod();
//				if (method != null && method.isAnnotationPresent(EmbeddedId.class)) {
//					PropertyDescriptor[] ps = Introspector.getBeanInfo(propertydesc.getPropertyType())
//							.getPropertyDescriptors();
//					out = "o." + propertydesc.getName() + "."
//							+ (!ps[1].getName().equals("class") ? ps[1].getName() : ps[0].getName());
//					break;
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return out;
//	}
//
//	/**
//	 * 从sql中找出from子句
//	 * 
//	 * @param sql
//	 * @return
//	 */
//	private String getFromClause(String sql) {
//		String sql2 = sql.toLowerCase();
//		int index = sql2.indexOf(" from ");
//		if (index < 0) {
//			return null;
//		} else {
//			int i1 = sql2.lastIndexOf(" order by ");
//			int i2 = sql2.lastIndexOf(" group by ");
//
//			if (i1 >= 0 && i2 >= 0) {
//				return sql.substring(index, i1 > i2 ? i2 : i1);
//			} else if (i1 >= 0) {
//				return sql.substring(index, i1);
//			} else if (i2 >= 0) {
//				return sql.substring(index, i2);
//			} else {
//				return sql.substring(index);
//			}
//		}
//	}
//
//}

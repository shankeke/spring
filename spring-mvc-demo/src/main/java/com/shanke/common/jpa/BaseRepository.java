//package com.shanke.common.jpa;
//
//import java.io.Serializable;
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.persistence.Query;
//
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.NoRepositoryBean;
//
//import com.shanke.utils.data.QueryResult;
//
///**
// * 描述 : 自定义Repository的方法接口 . <br>
// * <p>
// * 
// * Copyright (c) 2016 优识云创(YSYC)
// * 
// * @author Uknower-yjw
// * @date 2016年8月1日 下午8:27:49
// * @param <T>
// *            领域对象即实体类
// * @param <ID>
// *            领域对象的注解
// */
//@NoRepositoryBean
//public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {
//
//	/**
//	 * 描述 : 保存对象. <br>
//	 * 注意：如果对象id是字符串，并且没有赋值，该方法将自动设置为uuid值 <br>
//	 * <p>
//	 * 
//	 * @author Uknower-yjw
//	 * @date 2016年8月1日 下午8:29:47
//	 * @param item
//	 *            持久对象，或者对象集合
//	 */
//	public void store(Object... item);
//
//	/**
//	 * 描述 : 更新对象数据. <br>
//	 * <p>
//	 * 
//	 * @author Uknower-yjw
//	 * @date 2016年8月1日 下午8:31:11
//	 * @param item
//	 *            持久对象，或者对象集合
//	 */
//	public void update(Object... item);
//
//	/**
//	 * 
//	 * 描述 : 执行ql语句. <br>
//	 * <p>
//	 * 
//	 * @author Uknower-yjw
//	 * @date 2016年8月1日 下午8:31:33
//	 * @param qlString
//	 *            基于jpa标准的ql语句
//	 * @param values
//	 *            ql中的参数值,单个参数值或者多个参数值
//	 * @return 返回执行后受影响的数据个数
//	 */
//	public int executeUpdate(String qlString, Object... values);
//
//	/**
//	 * 描述 : 执行ql语句. <br>
//	 * <p>
//	 * 
//	 * @author Uknower-yjw
//	 * @date 2016年8月1日 下午8:32:10
//	 * @param qlString
//	 *            基于jpa标准的ql语句
//	 * @param params
//	 *            key表示ql中参数变量名，value表示该参数变量值
//	 * @return 返回执行后受影响的数据个数
//	 */
//	public int executeUpdate(String qlString, Map<String, Object> params);
//
//	/**
//	 * 执行ql语句，可以是更新或者删除操作
//	 * 
//	 * @param qlString
//	 *            基于jpa标准的ql语句
//	 * @param values
//	 *            ql中的?参数值
//	 * @return 返回执行后受影响的数据个数
//	 * @throws Exception
//	 */
//
//	/**
//	 * 描述 : <方法描述>. <br>
//	 * <p>
//	 * 
//	 * @author Uknower-yjw
//	 * @date 2016年8月1日 下午8:32:40
//	 * @param qlString
//	 * @param values
//	 * @return
//	 */
//	public int executeUpdate(String qlString, List<Object> values);
//
//	/*** 还可以定义分页相关方法,此处暂不支持 **/
//	// JpaRepository本身是一个空接口，下面所有的方法声明都是自定义的
//	// ########################################################################
//	/**
//	 * 
//	 * 设置query的参数
//	 * 
//	 * @param query
//	 *            查询对象
//	 * 
//	 * @param queryParams
//	 *            参数
//	 */
//	public void setQueryParams(Query query, Object[] queryParams);
//
//	/**
//	 * 
//	 * 组装ORDER BY 语句
//	 * 
//	 * @param orderby
//	 * 
//	 * @return
//	 * 
//	 */
//
//	public String buildOrderby(LinkedHashMap<String, String> orderby);
//
//	/**
//	 * 
//	 * 获取实体名
//	 * 
//	 * @param entityClass
//	 * 
//	 * @return
//	 * 
//	 */
//
//	public String getEntityName(Class<T> entityClass);
//
//	/**
//	 * 
//	 * jpql语句查询
//	 * 
//	 * @param entityClass
//	 * 
//	 * @param whereSql
//	 * 
//	 * @param queryParams
//	 * 
//	 * @param orderby
//	 * 
//	 * @param pageable
//	 * 
//	 * @return
//	 * 
//	 */
//
//	public QueryResult<T> getScrollDataByJpql(Class<T> entityClass, String whereJpql, Object[] queryParams,
//
//			LinkedHashMap<String, String> orderby, Pageable pageable);
//
//	/**
//	 * 
//	 * sql语句查询
//	 * 
//	 * @param sql
//	 * 
//	 * @param queryParams
//	 * 
//	 * @param pageable
//	 * 
//	 * @return
//	 * 
//	 */
//
//	public QueryResult<T> getScrollDataBySql(String sql, Object[] queryParams, Pageable pageable);
//
//}

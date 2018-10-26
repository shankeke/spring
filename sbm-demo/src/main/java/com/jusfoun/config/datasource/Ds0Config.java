package com.jusfoun.config.datasource;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.zaxxer.hikari.HikariDataSource;

import tk.mybatis.spring.annotation.MapperScan;

/**
 * 说明：主数据源配置. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月29日 下午2:37:28
 */
@Configuration
@MapperScan(basePackages = "com.jusfoun.mapper.ds0", sqlSessionTemplateRef = "ds0SqlSessionTemplate")
public class Ds0Config {

	@Bean(name = "ds0DataSource")
	@ConfigurationProperties(prefix = "spring.datasource.hikari.ds0")
	@Primary
	public DataSource dataSource() {
		return DataSourceBuilder.create().type(HikariDataSource.class).build();
	}

	@Bean(name = "ds0SqlSessionFactory")
	@Primary
	public SqlSessionFactory sqlSessionFactory(@Qualifier("ds0DataSource") DataSource dataSource) throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mappers/ds0/*Mapper.xml"));
		return bean.getObject();
	}

	@Bean(name = "ds0TransactionManager")
	@Primary
	public DataSourceTransactionManager transactionManager(@Qualifier("ds0DataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean(name = "ds0SqlSessionTemplate")
	@Primary
	public SqlSessionTemplate sqlSessionTemplate(@Qualifier("ds0SqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

}
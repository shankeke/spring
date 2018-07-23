package com.jusfoun.config.ds;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import tk.mybatis.spring.annotation.MapperScan;

/**
 * 描述 :主数据源配置. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月29日 下午2:37:28
 */
@Configuration
@MapperScan(basePackages = "com.jusfoun.mapper.ds1", sqlSessionTemplateRef = "ds1SqlSessionTemplate")
public class Ds1Config {

	@Bean(name = "ds1DataSource")
	@ConfigurationProperties(prefix = "spring.datasource.ds1")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "ds1SqlSessionFactory")
	@Primary
	public SqlSessionFactory sqlSessionFactory(@Qualifier("ds1DataSource") DataSource dataSource) throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mappers/ds1/*Mapper.xml"));
		return bean.getObject();
	}

	@Bean(name = "ds1TransactionManager")
	@Primary
	public DataSourceTransactionManager transactionManager(@Qualifier("ds1DataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean(name = "ds1SqlSessionTemplate")
	@Primary
	public SqlSessionTemplate sqlSessionTemplate(@Qualifier("ds1SqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

}
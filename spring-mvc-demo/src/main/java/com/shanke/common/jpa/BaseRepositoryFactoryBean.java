//package com.shanke.common.jpa;
//
//import java.io.Serializable;
//
//import javax.persistence.EntityManager;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.support.JpaEntityInformation;
//import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
//import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
//import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
//import org.springframework.data.repository.core.RepositoryInformation;
//import org.springframework.data.repository.core.RepositoryMetadata;
//import org.springframework.data.repository.core.support.RepositoryFactorySupport;
//import org.springframework.util.Assert;
//
///**
// * 描述 : 创建一个自定义的FactoryBean去替代默认的工厂类 . <br>
// * <p>
// * 
// * Copyright (c) 2016 优识云创(YSYC)
// * 
// * @author Uknower-yjw
// * @date 2016年8月1日 下午8:37:55
// * @param <R>
// *            产品
// * @param <T>
// *            model
// * @param <I>
// *            ID
// */
//public class BaseRepositoryFactoryBean<R extends JpaRepository<T, I>, T, I extends Serializable>
//		extends JpaRepositoryFactoryBean<R, T, I> {
//
//	@SuppressWarnings("rawtypes")
//	protected RepositoryFactorySupport createRepositoryFactory(EntityManager em) {
//		return new BaseRepositoryFactory(em);
//	}
//
//	private static class BaseRepositoryFactory<T, I extends Serializable> extends JpaRepositoryFactory {
//
//		private final EntityManager entityManager;
//
//		public BaseRepositoryFactory(EntityManager entityManager) {
//			super(entityManager);
//			Assert.notNull(entityManager);
//			this.entityManager = entityManager;
//		}
//
//		@Override
//		protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
//			return BaseRepositoryImpl.class;
//		}
//
//		@SuppressWarnings({ "unchecked", "rawtypes" })
//		@Override
//		protected Object getTargetRepository(RepositoryInformation information) {
//			// TODO Auto-generated method stub
//			JpaEntityInformation<?, Serializable> entityInformation = getEntityInformation(information.getDomainType());
//			return new BaseRepositoryImpl(entityInformation, entityManager);
//		}
//
//		@SuppressWarnings({ "hiding", "unchecked", "rawtypes" })
//		@Override
//		protected <T, ID extends Serializable> BaseRepositoryImpl<?, ?> getTargetRepository(
//				RepositoryInformation information, EntityManager entityManager) {
//			// TODO Auto-generated method stub
//			JpaEntityInformation<?, Serializable> entityInformation = getEntityInformation(information.getDomainType());
//			return new BaseRepositoryImpl(entityInformation, entityManager); // custom
//		}
//	}
//}

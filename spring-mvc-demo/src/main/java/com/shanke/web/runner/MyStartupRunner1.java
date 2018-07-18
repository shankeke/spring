//package com.shanke.web.runner;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.annotation.Resource;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import com.shanke.dao.RightDao;
//import com.shanke.dao.RoleDao;
//import com.shanke.dao.UserDao;
//import com.shanke.entity.Right;
//import com.shanke.entity.Role;
//import com.shanke.entity.User;
//import com.shanke.utils.encrypt.Md5;
//import com.shanke.utils.list.IListUtil;
//
///**
// * 描述 : 服务启动执行初始化任务. <br>
// * <p>
// * 
// * Copyright (c) 2016 优识云创(YSYC)
// * 
// * @author Uknower-yjw
// * @date 2016年7月30日 下午11:46:58
// */
//@Component
//@Order(value = 1)
//public class MyStartupRunner1 implements CommandLineRunner {
//	private static final Logger logger = LoggerFactory.getLogger(MyStartupRunner1.class);
//
//	@Resource
//	private UserDao userDao;
//	@Resource
//	private RoleDao roleDao;
//	@Resource
//	private RightDao rightDao;
//
//	@Override
//	public void run(String... args) throws Exception {
//		logger.info(">>>>>>>>>>>>>>>服务启动执行,初始化系统权限配置信息开始.... <<<<<<<<<<<<<");
//		// InitManager data = JaxbUtil.xml2java(new File("D:/data.xml"),
//		// InitManager.class);
//		// if (data != null) {
//		// // 初始化用户
//		// initUsers(data.getUsers());
//		// // 初始化角色
//		// initRoles(data.getRoles());
//		// // 初始化权限
//		// initRights(data.getRights());
//		// }
//
//		initRights(new ArrayList<Right>() {
//			private static final long serialVersionUID = 2897410815726142349L;
//			{
//				add(new Right("添加", "/add", 1, "添加"));
//				add(new Right("删除", "/del", 1, "删除"));
//				add(new Right("修改", "/update", 1, "修改"));
//				add(new Right("查询", "/query", 1, "查询"));
//			}
//		});
//		initRoles(new ArrayList<Role>() {
//			private static final long serialVersionUID = 2226029338128649207L;
//
//			{
//				add(new Role("admin", "超级管理员"));
//				add(new Role("manager", "普通管理员"));
//				add(new Role("normal", "访客"));
//			}
//		});
//		initUsers(new ArrayList<User>() {
//			private static final long serialVersionUID = -334374095136516262L;
//
//			{
//				add(new User("admin", Md5.md5("123456"), "13460286086", 12, "13460286086@163.com"));
//				add(new User("yjw", Md5.md5("123456"), "13520439383", 28, "760474564@qq.com"));
//			}
//		});
//		logger.info(">>>>>>>>>>>>>>>服务启动执行,初始化系统权限配置信息结束.... <<<<<<<<<<<<<");
//	}
//
//	/**
//	 * 描述 :初始化系统管理员.<br>
//	 * <p>
//	 * 
//	 * @author Uknower-yjw
//	 * @date 2016年8月5日 下午9:17:19
//	 * @param users
//	 *            初始化用户信息
//	 */
//	private void initUsers(List<User> users) {
//		try {
//			if (IListUtil.hasData(users)) {
//				for (User user : users) {
//					String username = user.getUsername();
//					User u = userDao.findByUsername(username);
//					if (u == null) {
//						userDao.save(user);
//						logger.info("添加管理员" + user.toString() + "账户成功....");
//					} else {
//						logger.info("管理员" + username + "已存在，无需添加....");
//					}
//				}
//			} else {
//				logger.info("无初始化用户信息....");
//			}
//		} catch (Exception e) {
//			logger.error("初始化用户信息失败", e);
//		}
//	}
//
//	/**
//	 * 描述 :初始化系统角色.<br>
//	 * <p>
//	 * 
//	 * @author Uknower-yjw
//	 * @date 2016年8月5日 下午9:17:19
//	 * @param users
//	 *            初始化角色信息
//	 */
//	private void initRoles(List<Role> roles) {
//		try {
//			if (IListUtil.hasData(roles)) {
//				for (Role role : roles) {
//					String name = role.getName();
//					Role r = roleDao.findByName(name);
//					if (r == null) {
//						roleDao.save(role);
//						logger.info("添加角色" + role.toString() + "成功....");
//					} else {
//						logger.info("角色" + name + "已存在，无需添加....");
//					}
//				}
//			} else {
//				logger.info("无初始化角色信息....");
//			}
//		} catch (Exception e) {
//			logger.error("初始化角色信息失败", e);
//		}
//	}
//
//	/**
//	 * 描述 :初始化系统权限.<br>
//	 * <p>
//	 * 
//	 * @author Uknower-yjw
//	 * @date 2016年8月5日 下午9:17:19
//	 * @param rights
//	 *            初始化用户信息
//	 */
//	private void initRights(List<Right> rights) {
//		try {
//			if (IListUtil.hasData(rights)) {
//				for (Right right : rights) {
//					String name = right.getName();
//					Right r = rightDao.findByName(name);
//					if (r == null) {
//						rightDao.save(right);
//						logger.info("添加权限" + right.toString() + "成功....");
//					} else {
//						logger.info("权限" + name + "已存在，无需添加....");
//					}
//				}
//			} else {
//				logger.info("无初始化权限信息....");
//			}
//		} catch (Exception e) {
//			logger.error("初始化权限信息失败", e);
//		}
//	}
//}
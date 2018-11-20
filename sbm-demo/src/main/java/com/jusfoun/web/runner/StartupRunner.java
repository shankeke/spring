package com.jusfoun.web.runner;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import com.jusfoun.common.cache.service.CacheService;
import com.jusfoun.common.enums.UsingStatus;
import com.jusfoun.common.utils.ICollections;
import com.jusfoun.common.utils.JaxbUtils;
import com.jusfoun.config.InitConfig;
import com.jusfoun.entity.SysPrivs;
import com.jusfoun.entity.SysUser;
import com.jusfoun.entity.TokenClientDetails;
import com.jusfoun.service.SysPrivsService;
import com.jusfoun.service.SysUserService;
import com.jusfoun.service.TokenClientDetailsService;

/**
 * 说明： 服务启动执行初始化任务>. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月7日 上午11:07:09
 */
@Component
@Order(value = 1)
public class StartupRunner implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(StartupRunner.class);

	@Autowired
	private SysPrivsService sysPrivsService;
	@Autowired
	private CacheService cacheService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private TokenClientDetailsService tokenClientService;
	@Autowired
	private InitConfig initConfig;

	@Override
	public void run(String... args) throws Exception {
		log.info("--> 清理系统缓存.... ");
		cacheService.clear();
		if (!initConfig.isEnable()) {
			log.info(">> ### 服务系统初始化选项未开启，不执行初始化操作 ### <<");
			return;
		}
		log.info(">> ### 服务启动执行,系统初始化选项执行开始 ### <<");
		log.info("--> 初始化系统权限列表.... ");
		initSysPrivss();// 初始化企业信息
		log.info("--> 初始化客户端权限信息.... ");
		initClients();// 初始化企业信息
		log.info("--> 初始化系统管理员信息.... ");
		initAdmin();// 初始化系统管理员信息
		log.info(">> ### 服务启动执行，系统初始化选项执行结束 ### <<");
	}

	/**
	 * 说明：初始化系统权限数据. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2017年12月6日 下午5:12:14
	 * @throws FileNotFoundException
	 */
	private void initSysPrivss() throws FileNotFoundException {
		int count = sysPrivsService.selectCount(null);
		if (count == 0) {
			URL url = ResourceUtils.getURL(initConfig.getSysPrivssFile());
			SysPrivs root = JaxbUtils.xml2java(url, SysPrivs.class);

			SysUser admin = sysUserService.selectByUsername(initConfig.getUsername());
			if (admin != null) {
				root.setCreatorId(admin.getId());
				root.setUpdaterId(admin.getId());
				root.setCreatorName(admin.getRealName());
				root.setUpdaterName(admin.getRealName());
				Date now = new Date();
				root.setCreateDate(now);
				root.setUpdateDate(now);
				root.setStatus(UsingStatus.ENABLE.getValue());
			}
			sysPrivsService.initSysPrivss(root);
		}
	}

	/**
	 * 说明： 初始化系统超级管理员. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2017年12月6日 下午5:12:49
	 */
	private void initAdmin() {
		SysUser t = sysUserService.selectByUsername(initConfig.getUsername());
		if (t != null) {
			return;
		}
		t = new SysUser();
		t.setUsername("admin");
		t.setRealName("系统管理员");
		t.setPassword(passwordEncoder.encode(initConfig.getPassword()));
		t.setCreateDate(new Date());
		t.setUpdateDate(new Date());
		t.setCreatorId(1L);
		t.setUpdaterId(1L);
		t.setGovId(1L);
		t.setStatus(UsingStatus.ENABLE.getValue());
		t.setGender(0);
		t.setIsAdmin(true);
		t.setGovName("信息中心");
		t.setEmail("admin@qq.com");
		t.setMobile("13888888888");
		sysUserService.insert(t);
	}

	/**
	 * 说明： 初始化系统客户端信息. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @throws FileNotFoundException
	 * @date 2017年12月6日 下午5:13:11
	 */
	private void initClients() throws FileNotFoundException {
		URL url = ResourceUtils.getURL(initConfig.getClientDetailsFile());
		ClientDetailsRoot root = JaxbUtils.xml2java(url, ClientDetailsRoot.class);
		if (root == null) {
			return;
		}
		List<TokenClientDetails> list = root.getList();
		if (ICollections.hasElements(list)) {
			for (TokenClientDetails t : list) {
				t.setClientSecret(passwordEncoder.encode(t.getClientSecret()));
				t.setAuthorities(sysPrivsService.selectAuthorities(t.getClientId()));
				tokenClientService.replaceAndCache(t);
			}
		}
	}

}
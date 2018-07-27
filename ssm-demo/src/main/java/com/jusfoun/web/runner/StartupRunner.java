package com.jusfoun.web.runner;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import com.jusfoun.common.cache.service.CacheService;
import com.jusfoun.common.enums.UsingStatus;
import com.jusfoun.common.util.jaxb.JaxbUtil;
import com.jusfoun.common.util.list.IListUtil;
import com.jusfoun.entity.SysModule;
import com.jusfoun.entity.SysUser;
import com.jusfoun.entity.TokenClientDetails;
import com.jusfoun.service.SysModuleService;
import com.jusfoun.service.SysUserService;
import com.jusfoun.service.TokenClientDetailsService;

/**
 * 描述 : 服务启动执行初始化任务>. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月7日 上午11:07:09
 */
@Component
@Order(value = 1)
public class StartupRunner implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(StartupRunner.class);

	@Autowired
	private SysModuleService sysModuleService;
	@Autowired
	private CacheService cacheService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private TokenClientDetailsService tokenClientService;

	@Value("${system.sysModule.init-file}")
	private String sysModulesFile;// 初始化权限配置文件路径
	@Value("${system.clientDetails.init-file}")
	private String clientDetailsFile;// 初始化企业信息配置文件路径
	@Value("${system.user.init-password}")
	private String initPassword;// 系统管理员初始化密码

	private String adminName = "admin";

	@Override
	public void run(String... args) throws Exception {
		log.info(">> ### 服务启动执行,系统初始化选项执行开始 ### <<");
		log.info("--> 清理系统缓存.... ");
		cacheService.clear();
		log.info("--> 初始化系统权限列表.... ");
		initSysModules();// 初始化企业信息
		log.info("--> 初始化客户端权限信息.... ");
		initClients();// 初始化企业信息
		log.info("--> 初始化系统管理员信息.... ");
		initAdmin();// 初始化系统管理员信息
		log.info(">> ### 服务启动执行，系统初始化选项执行结束 ### <<");
	}

	/**
	 * 描述 :初始化系统权限数据. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2017年12月6日 下午5:12:14
	 * @throws FileNotFoundException
	 */
	private void initSysModules() throws FileNotFoundException {
		int count = sysModuleService.selectCount(null);
		if (count == 0) {
			URL url = ResourceUtils.getURL(sysModulesFile);
			SysModule root = JaxbUtil.xml2java(url, SysModule.class);

			SysUser admin = sysUserService.selectByUsername(adminName);
			if (admin != null) {
				root.setCreatorId(admin.getId());
				root.setUpdaterId(admin.getId());
				root.setCreatorName(admin.getRealName());
				root.setUpdaterName(admin.getRealName());
				Date now = new Date();
				root.setCreateDate(now);
				root.setUpdateDate(now);
				root.setStatus(UsingStatus.Enable.getValue());
			}
			sysModuleService.initSysModules(root);
		}
	}

	/**
	 * 描述 : 初始化系统超级管理员. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2017年12月6日 下午5:12:49
	 */
	private void initAdmin() {
		SysUser t = sysUserService.selectByUsername(adminName);
		if (t != null) {
			return;
		}
		t = new SysUser();
		t.setUsername("admin");
		t.setRealName("系统管理员");
		t.setPassword(passwordEncoder.encode(initPassword));
		t.setCreateDate(new Date());
		t.setUpdateDate(new Date());
		t.setCreatorId(1L);
		t.setUpdaterId(1L);
		t.setGovId(1L);
		t.setStatus(UsingStatus.Enable.getValue());
		t.setGender(0);
		t.setIsAdmin(true);
		t.setGovName("信息中心");
		t.setEmail("admin@qq.com");
		t.setMobile("13888888888");
		sysUserService.insert(t);
	}

	/**
	 * 描述 : 初始化系统客户端信息. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @throws FileNotFoundException
	 * @date 2017年12月6日 下午5:13:11
	 */
	private void initClients() throws FileNotFoundException {
		URL url = ResourceUtils.getURL(clientDetailsFile);
		ClientDetailsRoot root = JaxbUtil.xml2java(url, ClientDetailsRoot.class);
		if (root == null) {
			return;
		}
		List<TokenClientDetails> list = root.getList();
		if (IListUtil.hasData(list)) {
			for (TokenClientDetails t : list) {
				t.setClientSecret(passwordEncoder.encode(t.getClientSecret()));
				t.setAuthorities(sysModuleService.selectAuthorities(t.getClientId()));
				tokenClientService.replaceAndCache(t);
			}
		}
	}

}
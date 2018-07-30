package com.jusfoun.common.log;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

import javax.annotation.PreDestroy;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.jusfoun.common.cache.CacheConsts;
import com.jusfoun.common.cache.service.CacheService;
import com.jusfoun.common.log.Logable.LevelType;
import com.jusfoun.common.utils.list.IListUtil;
import com.jusfoun.common.utils.net.IpUtil;
import com.jusfoun.entity.SysLog;
import com.jusfoun.entity.SysUser;
import com.jusfoun.security.config.WebSecurityConfig;
import com.jusfoun.security.util.SecurityUtils;
import com.jusfoun.service.SysLogService;
import com.jusfoun.service.SysUserService;

/**
 * 描述 : 系统访问日志处理， 主要拦截 <code>@Logable</code>注解的类和方法。<br>
 * 如果用在类上使用，该类下面的所有方法会被拦截 ， 如果在方法上面则只对该方法生效，方法上的优先级高于类型上的注解。<br>
 * 切入点根据相应属性判断是否输出日志信息，如果需要输出根据级别输出日志，该注解主要是方便查看指定的方法输入参数和返回结果。 <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年10月10日 下午2:32:00
 */
@Aspect
@Component
public class LogableAspect {

	private static final Logger log = LoggerFactory.getLogger(LogableAspect.class);

	private static final String LOG_CACHE_KEY = "cache_logs";
	private static final int LOG_QUEUE_INIT_CAPACITY = 200;

	@Autowired
	private CacheService cacheService;
	@Autowired
	private SysLogService sysLogService;
	@Autowired
	private SysUserService sysUserService;
	// @Autowired
	// private IpService ipService;

	/**
	 * 描述 : 默认拦截controller层和授权鉴权的接口. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2017年11月2日 下午8:04:36
	 */
	@Pointcut("(execution(* com.jusfoun.web.controller..*(..)) || execution(* com.jusfoun.security..*(..))) && @annotation(com.jusfoun.common.log.Logable)")
	public void joinPointExpression() {
	}

	@Around(value = "joinPointExpression()")
	public Object aroundMethod(ProceedingJoinPoint pjd) throws Throwable {
		Object o = null;
		String message = "";
		boolean result = false;
		boolean logable = false;
		LevelType level = null;

		// 方法
		MethodSignature methodSignature = (MethodSignature) pjd.getSignature();
		Method method = methodSignature.getMethod();
		Logable l = method.getAnnotation(Logable.class);
		Object[] args = pjd.getArgs();

		try {
			if (l != null) {
				logable = l.enable();
				level = l.level();
			}

			if (logable) {
				message = "Method " + methodSignature + " begins with args: " + Arrays.asList(args);
				log(level, message);
			}

			o = pjd.proceed(args);
			result = true;

			if (logable) {
				message = "Method " + methodSignature + " ends with result: " + o;
				log(level, message);
			}
		} catch (Throwable e) {
			result = false;
			if (logable) {
				message = "Method " + methodSignature + " occurs exception: " + e;
				log.error(message, e);
			}

			/**
			 * 这里异常原封不动抛出去交给异常处理器处理，spring
			 * security的由框架自己处理，Controller中的异常由GlobalExceptionHandler统一处理
			 */
			throw e;
		} finally {
			try {
				if (l != null && l.enable()) {
					HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
					// 设置结果
					SysLog sysLog = new SysLog();
					String remoteHost = IpUtil.getIpAddr(request);
					request.getRemoteHost();
					sysLog.setRemoteHost(remoteHost);
					sysLog.setRemotePort(request.getRemotePort());
					sysLog.setRequestUrl(request.getRequestURL().toString());
					String servletPath = request.getServletPath();
					sysLog.setRequestUri(servletPath);
					sysLog.setModulePath(l.fullPath());
					sysLog.setModuleName(l.desc());
					// sysLog.setAreaName(ipService.getAreaByIP(remoteHost));
					sysLog.setCreateDate(new Date());
					sysLog.setResultType(result);

					// 查询用户:登录时需要单独处理
					String username = null;
					SysUser user = null;
					if (WebSecurityConfig.TOKEN_ENTRY_POINT.equals(servletPath)) {
						Authentication authentication = (Authentication) args[0];
						username = (String) authentication.getPrincipal();
					} else {
						username = SecurityUtils.getCurrentUserUsername();
					}

					if (user == null && StringUtils.isNotEmpty(username)) {
						user = sysUserService.selectByUsername(username);
						sysLog.setUsername(username);
					}

					// 设置用户属性
					if (user != null) {
						sysLog.setRealName(user.getRealName());
						sysLog.setGovId(user.getGovId());
						sysLog.setGovName(user.getGovName());
					}

					// 登录日志单独处理
					if (WebSecurityConfig.TOKEN_ENTRY_POINT.equals(servletPath)) {
						sysLogService.insert(sysLog);
					} else {
						cacheLog(sysLog, false);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				// 这里异常不用处理
				return o;
			}
		}
		return o;
	}

	/**
	 * 描述 : 将日志加入缓存中. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年10月14日 上午9:37:53
	 * @param sysLog
	 * @return
	 * @throws InterruptedException
	 */
	@SuppressWarnings("unchecked")
	public void cacheLog(SysLog sysLog, boolean clear) {
		Object object = cacheService.get(CacheConsts.CACHE_PERSISTENT, LOG_CACHE_KEY);
		LinkedBlockingQueue<SysLog> queue = null;
		if (object != null) {
			ValueWrapper valueWrapper = (ValueWrapper) object;
			queue = (LinkedBlockingQueue<SysLog>) valueWrapper.get();
		}

		if (queue == null) {
			queue = new LinkedBlockingQueue<SysLog>(LOG_QUEUE_INIT_CAPACITY);
		}
		if (sysLog != null) {
			queue.add(sysLog);
		}

		// 如果队列元素数量大于设置的容量或者指定清理队列则将队列的数据存入数据库中
		if (queue.size() >= LOG_QUEUE_INIT_CAPACITY || clear) {
			List<SysLog> list = new ArrayList<SysLog>();
			queue.drainTo(list);// 将队列数据全部放入集合中
			if (IListUtil.hasData(list)) {
				sysLogService.insertList(list);
				cacheService.evict(CacheConsts.CACHE_PERSISTENT, LOG_CACHE_KEY);// 清理缓存
			}
		} else {
			cacheService.put(CacheConsts.CACHE_PERSISTENT, LOG_CACHE_KEY, queue);
		}
	}

	// 根据指定级别输出对应的日志信息
	private void log(LevelType level, String message) {
		switch (level) {
			case TRACE :
				if (log.isTraceEnabled()) {
					log.trace(message);
				}
				break;
			case DEBUG :
				if (log.isDebugEnabled()) {
					log.debug(message);
				}
				break;
			case INFO :
				if (log.isInfoEnabled()) {
					log.info(message);
				}
				break;
			case WARN :
				if (log.isWarnEnabled()) {
					log.warn(message);
				}
				break;
			case ERROR :
				if (log.isErrorEnabled()) {
					log.error(message);
				}
				break;
			default :
				break;
		}
	}

	// 销毁之前现将缓存中的日志存库
	@PreDestroy
	public void destory() {
		cacheLog(null, true);
	}
}

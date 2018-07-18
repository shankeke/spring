package com.shanke.dubbo.comm.cache;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述 : 定义缓存的键和库常量. <br>
 * <p>
 * 
 * Copyright (c) 2016 优识云创(YSYC)
 * 
 * @author Uknower-yjw
 * @date 2016-4-30 下午2:00:58
 */
public interface CustomKeyConstant {
	// 分类缓存库名称
	public static final String STORE_SYSCLASSIFY_WEB = "sysclassify_web";
	public static final String STORE_SYSCLASSIFY_APP = "sysclassify_app";

	// 文章和专题缓存库名称
	public static final String STORE_ARTICLE_WEB = "article_web";
	public static final String STORE_ARTICLE_APP = "article_app";
	public static final String STORE_ARTICLE_APP_PRISE_COUNT_PREFIX = "store_article_app_prise_count_%s";
	public static final String STORE_ARTICLE_APP_READ_COUNT_PREFIX = "store_article_app_read_count_%s";

	// banner缓存库名称
	public static final String STORE_BANNER_WEB = "banner_web";
	public static final String STORE_BANNER_APP = "banner_app";

	// 收藏缓存库名称
	public static final String STORE_LAW_WEB = "law_web";
	public static final String STORE_LAW_APP = "law_app";

	// 解读缓存库名称
	public static final String STORE_EXPLAIN_WEB = "explain_web";
	public static final String STORE_EXPLAIN_APP = "explain_app";

	// 办税指南缓存库名称
	public static final String STORE_TAXREGISTRATION_WEB = "taxregistration_web";
	public static final String STORE_TAXREGISTRATION_APP = "taxregistration_app";

	// 后台用户缓存库名称
	public static final String STORE_SYSUSER_WEB = "sysuser_web";
	public static final String STORE_SYSUSER_ROLE_WEB = "sysuser_role_web";
	// 后台角色缓存库名称
	public static final String STORE_ROLE_WEB = "role_web";
	public static final String STORE_ROLE_RIGHT_WEB = "role_right_web";
	// 后台权限缓存库名称
	public static final String STORE_RIGHT_WEB = "right_web";

	// 律师事务所缓存
	public static final String STORE_LAWOFFICE_APP = "lawoffice_app";

	// 领导专栏缓存
	public static final String STORE_LEADER_WEB = "leader_web";
	public static final String STORE_LEADER_APP = "leader_app";

	// 领导专栏缓存
	public static final String STORE_TAXMAP_WEB = "taxmap_web";
	public static final String STORE_TAXMAP_APP = "taxmap_app";

	// 移动办税导航缓存
	public static final String STORE_TAXNAVIGATION_WEB = "taxnavigation_web";
	public static final String STORE_TAXNAVIGATION_APP = "taxnavigation_app";

	// 移动办税导航缓存
	public static final String STORE_STATION_WEB = "station_web";
	public static final String STORE_STATION_APP = "station_app";

	// 移动办税导航缓存
	public static final String STORE_UNIT_WEB = "unit_web";
	public static final String STORE_UNIT_APP = "unit_app";

	// 职责缓存
	public static final String STORE_APPOINT_WEB = "appoint_web";
	public static final String STORE_APPOINT_APP = "appoint_app";

	public static final String MARK_UNDERLINE = "_";

	/**
	 * 缓存集合
	 */
	public static List<CacheVo> CACHEVOLIST = new ArrayList<CacheVo>() {
		private static final long serialVersionUID = -797596420745774284L;
		{
			add(new CacheVo(STORE_SYSCLASSIFY_WEB, "类别信息WEB缓存")); //
			add(new CacheVo(STORE_SYSCLASSIFY_APP, "类别信息APP缓存")); //
			add(new CacheVo(STORE_ARTICLE_WEB, "税闻专题WEB缓存")); //
			add(new CacheVo(STORE_ARTICLE_APP, "税闻专题APP缓存")); //
			add(new CacheVo(STORE_BANNER_WEB, "Banner管理WEB缓存")); //
			add(new CacheVo(STORE_BANNER_APP, "Banner管理APP缓存")); //
			add(new CacheVo(STORE_LAW_WEB, "法律法规WEB缓存")); //
			add(new CacheVo(STORE_LAW_APP, "法律法规APP缓存")); //
			add(new CacheVo(STORE_EXPLAIN_WEB, "政策解读WEB缓存")); //
			add(new CacheVo(STORE_EXPLAIN_APP, "政策解读APP缓存")); //
			add(new CacheVo(STORE_TAXREGISTRATION_WEB, "办税指南WEB缓存")); //
			add(new CacheVo(STORE_TAXREGISTRATION_APP, "办税指南APP缓存")); //
			add(new CacheVo(STORE_SYSUSER_WEB, "管理员用户WEB缓存")); //
			add(new CacheVo(STORE_SYSUSER_ROLE_WEB, "管理员角色WEB缓存")); //
			add(new CacheVo(STORE_ROLE_WEB, "角色WEB缓存")); //
			add(new CacheVo(STORE_ROLE_RIGHT_WEB, "角色权限WEB缓存")); //
			add(new CacheVo(STORE_RIGHT_WEB, "权限WEB缓存")); //
			add(new CacheVo(STORE_LAWOFFICE_APP, "律师事务所APP缓存")); //
			add(new CacheVo(STORE_LEADER_WEB, "领导专栏WEB缓存")); //
			add(new CacheVo(STORE_LEADER_APP, "领导专栏APP缓存")); //
			add(new CacheVo(STORE_TAXMAP_WEB, "办税地图WEB缓存")); //
			add(new CacheVo(STORE_TAXMAP_APP, "办税地图APP缓存")); //
			add(new CacheVo(STORE_TAXNAVIGATION_WEB, "移动办税导航WEB缓存")); //
			add(new CacheVo(STORE_TAXNAVIGATION_APP, "移动办税导航APP缓存")); //
			add(new CacheVo(STORE_STATION_WEB, "税局信息WEB缓存")); //
			add(new CacheVo(STORE_STATION_APP, "税局信息APP缓存")); //
			add(new CacheVo(STORE_UNIT_WEB, "内设机构WEB缓存")); //
			add(new CacheVo(STORE_UNIT_APP, "内设机构APP缓存")); //
			add(new CacheVo(STORE_APPOINT_WEB, "人事任免WEB缓存")); //
			add(new CacheVo(STORE_APPOINT_APP, "人事任免APP缓存"));
		}
	};

	public enum CacheNameEnum {
		STORE_SYSCLASSIFY_WEB(CustomKeyConstant.STORE_SYSCLASSIFY_WEB, ""), //
		STORE_SYSCLASSIFY_APP(CustomKeyConstant.STORE_SYSCLASSIFY_APP, ""), //
		STORE_ARTICLE_WEB(CustomKeyConstant.STORE_ARTICLE_WEB, "article_web"), //
		STORE_ARTICLE_APP(CustomKeyConstant.STORE_ARTICLE_APP, "article_app"), //
		STORE_BANNER_WEB(CustomKeyConstant.STORE_BANNER_WEB, "banner_web"), //
		STORE_BANNER_APP(CustomKeyConstant.STORE_BANNER_APP, "banner_app"), //
		STORE_LAW_WEB(CustomKeyConstant.STORE_LAW_WEB, "law_web"), //
		STORE_LAW_APP(CustomKeyConstant.STORE_LAW_APP, "law_app"), //
		STORE_EXPLAIN_WEB(CustomKeyConstant.STORE_EXPLAIN_WEB, "explain_web"), //
		STORE_EXPLAIN_APP(CustomKeyConstant.STORE_EXPLAIN_APP, "explain_app"), //
		STORE_TAXREGISTRATION_WEB(CustomKeyConstant.STORE_TAXREGISTRATION_WEB,
				"taxregistration_web"), //
		STORE_TAXREGISTRATION_APP(CustomKeyConstant.STORE_TAXREGISTRATION_APP,
				"taxregistration_app"), //
		STORE_SYSUSER_WEB(CustomKeyConstant.STORE_SYSUSER_WEB, "sysuser_web"), //
		STORE_SYSUSER_ROLE_WEB(CustomKeyConstant.STORE_SYSUSER_ROLE_WEB,
				"sysuser_role_web"), //
		STORE_ROLE_WEB(CustomKeyConstant.STORE_ROLE_WEB, "role_web"), //
		STORE_ROLE_RIGHT_WEB(CustomKeyConstant.STORE_ROLE_RIGHT_WEB,
				"role_right_web"), //
		STORE_RIGHT_WEB(CustomKeyConstant.STORE_RIGHT_WEB, "right_web"), //
		STORE_LAWOFFICE_APP(CustomKeyConstant.STORE_LAWOFFICE_APP,
				"lawoffice_app"), //
		STORE_LEADER_WEB(CustomKeyConstant.STORE_LEADER_WEB, "leader_web"), //
		STORE_LEADER_APP(CustomKeyConstant.STORE_LEADER_APP, "leader_app"), //
		STORE_TAXMAP_WEB(CustomKeyConstant.STORE_TAXMAP_WEB, "taxmap_web"), //
		STORE_TAXMAP_APP(CustomKeyConstant.STORE_TAXMAP_APP, "taxmap_app"), //
		STORE_TAXNAVIGATION_WEB(CustomKeyConstant.STORE_TAXNAVIGATION_WEB,
				"taxnavigation_web"), //
		STORE_TAXNAVIGATION_APP(CustomKeyConstant.STORE_TAXNAVIGATION_APP,
				"taxnavigation_app"), //
		STORE_STATION_WEB(CustomKeyConstant.STORE_STATION_WEB, "station_web"), //
		STORE_STATION_APP(CustomKeyConstant.STORE_STATION_APP, "station_app"), //
		STORE_UNIT_WEB(CustomKeyConstant.STORE_UNIT_WEB, "unit_web"), //
		STORE_UNIT_APP(CustomKeyConstant.STORE_UNIT_APP, "unit_app"), //
		STORE_APPOINT_WEB(CustomKeyConstant.STORE_APPOINT_WEB, "人事任免WEB缓存"), //
		STORE_APPOINT_APP(CustomKeyConstant.STORE_APPOINT_APP, "人事任免APP缓存");

		private final String name;
		private final String alias;

		public String getName() {
			return name;
		}

		public String getAlias() {
			return alias;
		}

		private CacheNameEnum(String name, String alias) {
			this.name = name;
			this.alias = alias;
		}

	}
}

package com.meteor.common;

import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.ext.plugin.quartz.QuartzPlugin;
import com.meteor.controller.WebAction;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.ext.plugin.monogodb.MongodbPlugin;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.dialect.PostgreSqlDialect;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.render.ViewType;

/**
 * API引导式配置
 */
public class MainConfig extends JFinalConfig {
	/**
	 * 配置常量
	 */
	public void configConstant(Constants me) {
		PropKit.use("config.txt");
		me.setDevMode(PropKit.getBoolean("devMode", false));
		me.setViewType(ViewType.JSP);// 设置视图类型为Jsp，否则默认为FreeMarker
		me.setError404View("/WEB-INF/error.jsp");
	}
	
	/**
	 * 配置路由
	 */
	public void configRoute(Routes me) {
		me.add("/web", WebAction.class, "/WEB-INF/web");
	}
	
	/**
	 * 配置插件
	 */
	public void configPlugin(Plugins me) {

	}
	
	/**
	 * 配置全局拦截器
	 */
	public void configInterceptor(Interceptors me) {
		
	}
	
	/**
	 * 配置处理器
	 */
	public void configHandler(Handlers me) {

	}
	
	/**
	 * 建议使用 JFinal 手册推荐的方式启动项目
	 * 运行此 main 方法可以启动项目，此main方法可以放置在任意的Class类定义中，不一定要放于此
	 */
	public static void main(String[] args) {
		JFinal.start("WebRoot", 82, "/", 5);
	}
}

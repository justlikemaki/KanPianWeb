/**
 * 
 */
package com.meteor.controller;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.kit.PropKit;
import com.meteor.interceptor.accesslog;
import com.meteor.kit.InterfaceKit;
import com.meteor.kit.PageKit;
import com.meteor.kit.http.MultitHttpClient;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author justlikemaki
 *
 */

@Before(accesslog.class)
public class WebAction extends Controller {
	private final Logger logger = LoggerFactory.getLogger(WebAction.class);

	@Clear
	public void getBt(){
		try {
			String sv = getPara("searchval");
			String id = getPara("mgid");
			Map param=new HashMap();
			param.put("searchval",sv);
			param.put("mgid",id);
			String url= InterfaceKit.getGetBtUrl();
			String res= MultitHttpClient.getInParams(url, param);
			renderText(res);
		}catch (Exception e){
			logger.error("getBt: " + e.toString());
			renderText("");
		}
	}

	public void index(){
		newspage();
	}

	public void newspage(){
		HttpServletRequest request=getRequest();
		String p=getPara();
		editForm();
		int num=1;
		if(StringUtils.isNotBlank(p)) {
			num = getParaToInt();
		}
		render(PageKit.topage(request, num, "newspage", "list", "") + ".jsp");
	}

	public void censored(){
		HttpServletRequest request=getRequest();
		String p=getPara();
		editForm();
		int num=1;
		if(StringUtils.isNotBlank(p)) {
			num = getParaToInt();
		}
		render(PageKit.topage(request, num, "censored", "list", "tabtype")+ ".jsp");
	}

	public void uncensored(){
		HttpServletRequest request=getRequest();
		String p=getPara();
		editForm();
		int num=1;
		if(StringUtils.isNotBlank(p)) {
			num = getParaToInt();
		}
		render(PageKit.topage(request, num, "uncensored", "list", "tabtype")+ ".jsp");
	}

	public void westporn(){
		HttpServletRequest request=getRequest();
		String p=getPara();
		editForm();
		int num=1;
		if(StringUtils.isNotBlank(p)) {
			num = getParaToInt();
		}
		render(PageKit.topage(request, num, "westporn", "list", "tabtype")+ ".jsp");
	}

	public void classical(){
		HttpServletRequest request=getRequest();
		String p=getPara();
		editForm();
		int num=1;
		if(StringUtils.isNotBlank(p)) {
			num = getParaToInt();
		}
		render(PageKit.topage(request, num, "classical", "list", "tabtype")+ ".jsp");
	}

	public void search(){
		HttpServletRequest request=getRequest();
		String p=getPara();
		editForm();
		String tagstr="";
		String searchzd = "";
		String sp = getPara("sp");
		String time = getPara("time");

		if(StringUtils.isNotBlank(sp)||StringUtils.isNotBlank(time)) {
			if (StringUtils.isBlank(sp)) {
				tagstr = time;
				searchzd = "times";
				if (StringUtils.isNotBlank(tagstr)) {
					request.setAttribute("searchname", "time");
					request.setAttribute("searchvalue", tagstr);
				}
			} else {
				tagstr = sp;
				searchzd = "tags";
				if (StringUtils.isNotBlank(tagstr)) {
					request.setAttribute("searchname", "sp");
					request.setAttribute("searchvalue", tagstr.toUpperCase());
				}
			}
		}else{
			tagstr = "";
			searchzd = "tags";
			request.setAttribute("searchname", "sp");
			request.setAttribute("searchvalue", "");
		}

		int num=1;
		if(StringUtils.isNotBlank(p)) {
			num = getParaToInt();
		}
		render(PageKit.topage(request,num, tagstr.toUpperCase(), "list", searchzd)+ ".jsp");
	}

	private void editForm() {
		HttpServletRequest request=getRequest();
		HttpSession sct=getSession();
		//如果页面右侧参数为空，默认跳转到首页
		String res= PageKit.getparameters(sct);
		if(res==null){
			request.setAttribute("pagetype", "list");
			request.setAttribute("tab", "newspage");
		}
	}
}

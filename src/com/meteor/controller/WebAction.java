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

	@Clear
	public void pageGetBt(){
		try {
			String sv = getPara("searchval");
			String idtype = getPara("idtype");
			String likeflag = getPara("islike");
			Map param=new HashMap();
			param.put("searchval",sv);
			param.put("idtype",idtype);
			param.put("islike",likeflag);
			String url= InterfaceKit.getGetPageBtUrl();
			String res= MultitHttpClient.getInParams(url, param);
			renderText(res);
		}catch (Exception e){
			logger.error("pageGetBt: " + e.toString());
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
		render(PageKit.topage(request, num, "newspage", "list", null) + ".jsp");
	}

	public void censored(){
		HttpServletRequest request=getRequest();
		String p=getPara();
		editForm();
		int num=1;
		if(StringUtils.isNotBlank(p)) {
			num = getParaToInt();
		}
		Map scmap=new HashMap();
		scmap.put("tabtype","censored");
		render(PageKit.topage(request, num, "censored", "list", scmap)+ ".jsp");
	}

	public void uncensored(){
		HttpServletRequest request=getRequest();
		String p=getPara();
		editForm();
		int num=1;
		if(StringUtils.isNotBlank(p)) {
			num = getParaToInt();
		}
		Map scmap=new HashMap();
		scmap.put("tabtype","uncensored");
		render(PageKit.topage(request, num, "uncensored", "list", scmap)+ ".jsp");
	}

	public void westporn(){
		HttpServletRequest request=getRequest();
		String p=getPara();
		editForm();
		int num=1;
		if(StringUtils.isNotBlank(p)) {
			num = getParaToInt();
		}
		Map scmap=new HashMap();
		scmap.put("tabtype","westporn");
		render(PageKit.topage(request, num, "westporn", "list", scmap)+ ".jsp");
	}

	public void classical(){
		HttpServletRequest request=getRequest();
		String p=getPara();
		editForm();
		int num=1;
		if(StringUtils.isNotBlank(p)) {
			num = getParaToInt();
		}
		Map scmap=new HashMap();
		scmap.put("tabtype","classical");
		render(PageKit.topage(request, num, "classical", "list", scmap)+ ".jsp");
	}

	public void search(){
		HttpServletRequest request=getRequest();
		String p=getPara();
		editForm();
		String tagstr="";
		String searchzd = "";
		String sp = getPara("sp");
		String time = getPara("time");

		Map scmap=new HashMap();
		String type = getPara("type");
		if(StringUtils.isNotBlank(type)) {
			String zhetype=PageKit.getTabType(type);
			if(StringUtils.isNotBlank(zhetype)){
				type=zhetype;
			}
			request.setAttribute("searchtype", type);
			scmap.put("tabtype", type);
		}

		if(StringUtils.isNotBlank(sp)||StringUtils.isNotBlank(time)) {
			if (StringUtils.isBlank(sp)) {
				tagstr = time;
				searchzd = "times";
				scmap.put(searchzd,tagstr);
				if (StringUtils.isNotBlank(tagstr)) {
					request.setAttribute("searchname", "time");
					request.setAttribute("searchvalue", tagstr);
				}
			} else {
				tagstr = sp;
				searchzd = "tags";
				scmap.put(searchzd,tagstr);
				if (StringUtils.isNotBlank(tagstr)) {
					request.setAttribute("searchname", "sp");
					request.setAttribute("searchvalue", tagstr.toUpperCase());
				}
			}
		}else{
			tagstr = "";
			searchzd = "tags";
			scmap.put(searchzd,tagstr);
			request.setAttribute("searchname", "sp");
			request.setAttribute("searchvalue", "");
		}

		int num=1;
		if(StringUtils.isNotBlank(p)) {
			num = getParaToInt();
		}
		render(PageKit.topage(request,num, tagstr.toUpperCase(), "list", scmap)+ ".jsp");
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

		if(ispc()) {
			int streammode = PropKit.getInt("streammode");
			request.setAttribute("streammode", streammode);
			request.setAttribute("ispc", "1");
		}else{
			request.setAttribute("ispc", "0");
		}

	}

	private void setpc(){
		HttpServletRequest request=getRequest();
		boolean flag=ispc();
		if(flag) {
			request.setAttribute("ispc", "1");
		}else{
			request.setAttribute("ispc", "0");
		}
	}

	private boolean ispc(){
		String userAgentInfo = getRequest().getHeader("User-Agent");
		String[] Agents = new String[]{"Android", "iPhone","SymbianOS", "Windows Phone","iPad", "iPod"};
		boolean flag = true;
		for (int v = 0; v < Agents.length; v++) {
			if (userAgentInfo.contains(Agents[v])) {
				flag = false;
				break;
			}
		}
		return flag;
	}

	public void getbtlist(){
		setpc();
		HttpServletRequest request=getRequest();
		render(PageKit.topage(request, 0, "getbtlist", "getbtlist", null)+ ".jsp");
	}
}

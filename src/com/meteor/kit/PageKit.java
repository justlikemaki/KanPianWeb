package com.meteor.kit;

import com.meteor.kit.http.MultitHttpClient;
import com.meteor.model.po.javsrc;
import com.meteor.model.vo.DateVo;
import com.meteor.model.vo.SearchQueryP;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageKit {

	private final static Logger logger = LoggerFactory.getLogger(PageKit.class);

	public static String topage(HttpServletRequest request,int nowpage,String pagename,String pagetype,String searchzd ){
			try {
				SearchQueryP p = new SearchQueryP();
				p.setSbtype("web");
				p.setNowpage(nowpage);
				Map mp = new HashMap();
				//如果搜索条件不为空，加入搜索条件
				if (StringUtils.isNotBlank(searchzd)) {
					mp.put(searchzd, pagename);
				}
				p.setParameters(mp);
				Map param = new HashMap();
				param.put("sp",JsonKit.bean2JSON(p));

				String url=InterfaceKit.getGetLeftUrl();
				String json=MultitHttpClient.getInParams(url,param);
				Map res =JsonKit.json2Bean(json, HashMap.class);
				if(res.get("status").toString().equals("-1")){
					logger.error("topage: " + res.get("errmsg").toString());
					return "error";
				}
				List<javsrc> srcs = (List<javsrc>) res.get("list");
				double dcount=Double.valueOf(res.get("pagecount").toString());
				long count= Math.round(dcount);
				srcs=BeanKit.copyTo(srcs,javsrc.class);
				double dpagecount =Double.valueOf(res.get("count").toString());
				long pagecount = Math.round(dpagecount);
				request.setAttribute("srcs", srcs);
				request.setAttribute("pagecount", pagecount);//总共有多少条数据
				request.setAttribute("countsize", count);//总共显示多少条数据
				request.setAttribute("pagenum", nowpage);//当前页面
				if(searchzd.equals("tabtype")||pagename.equals("newspage")) {
					request.setAttribute("actionUrl", "web/"+pagename);//要跳转的页面名称
				}else{
					request.setAttribute("actionUrl", "web/"+"search");//要跳转的页面名称
				}
			} catch (Exception e) {
				logger.error("topage: " + e.toString());
				return "error";
			}
		request.setAttribute("pagetype", pagetype);//页面类型
		request.setAttribute("tabtitle", pagename.toUpperCase());//页面标题
		request.setAttribute("tab", pagename);
		return "default";
	}

	public static String getparameters(HttpSession sct) {
		try {
			if(sct.getAttribute("datelist")==null) {
				return getparameters( sct, 1);
			}else{
				if(sct.getAttribute("hotlist") ==null) {
					return getparameters( sct, 0);
				}
			}
		} catch (Exception e) {
			logger.error("getparameters: " + e.toString());
			return null;
		}
		return "ok";
	}

	private static String getparameters(HttpSession sct,int withdate) throws Exception {
		List<DateVo> datelist=null;
		List<Map.Entry> hotlist=null;
		Map param = new HashMap();
		param.put("withdate",withdate+"");
		String url=InterfaceKit.getGetRightUrl();
		String json=MultitHttpClient.getInParams(url,param);
		Map res =JsonKit.json2Bean(json, HashMap.class);
		if(res.get("status").toString().equals("-1")){
			logger.error("getparameters: " + res.get("errmsg").toString());
			return null;
		}else {
			datelist= (List<DateVo>) res.get("datelist");
			if(datelist!=null&&datelist.size()>0) {
				sct.setAttribute("datelist", datelist);
			}
			hotlist= (List<Map.Entry>) res.get("hotlist");
			if(hotlist!=null&&hotlist.size()>0) {
				sct.setAttribute("hotlist", hotlist);
			}
			return "ok";
		}
	}

}

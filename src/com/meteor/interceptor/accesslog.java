package com.meteor.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.jfinal.kit.PropKit;
import com.meteor.kit.IpKit;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by Meteor on 2015/9/13.
 *
 * @category (这里用一句话描述这个类的作用)
 */
public class accesslog implements Interceptor {
    private final Logger logger = LoggerFactory.getLogger(accesslog.class);

    @Override
    public void intercept(Invocation inv) {
        Controller cr=inv.getController();
        HttpServletRequest request=cr.getRequest();
        String method=inv.getMethodName();
        String p=cr.getPara();
        if(StringUtils.isBlank(p)){
            p="1";
        }
        //logger.error("内网ip地址为：" + IpKit.getIpAddress(request) + "的用户访问了" + method);
        logger.error("ip地址为：" + IpKit.getIpWeb(request) + "的用户访问了" + method+"/"+p);
        inv.invoke();
    }
}

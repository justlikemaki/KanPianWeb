package com.meteor.kit;

import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;

/**
 * Created by Meteor on 2015/9/15.
 *
 * @category (这里用一句话描述这个类的作用)
 */
public class InterfaceKit {
    private static String serverIp=PropKit.get("serverhost");
    private static String getRightUrl=serverIp+"getRight";
    private static String getLeftUrl=serverIp+"getLeft";
    private static String getBtUrl=serverIp+"getBt";

    public static String getServerIp() {
        return serverIp;
    }

    public static void setServerIp(String serverIp) {
        InterfaceKit.serverIp = serverIp;
    }

    public static String getGetRightUrl() {
        return getRightUrl;
    }

    public static void setGetRightUrl(String getRightUrl) {
        InterfaceKit.getRightUrl = getRightUrl;
    }

    public static String getGetLeftUrl() {
        return getLeftUrl;
    }

    public static void setGetLeftUrl(String getLeftUrl) {
        InterfaceKit.getLeftUrl = getLeftUrl;
    }

    public static String getGetBtUrl() {
        return getBtUrl;
    }

    public static void setGetBtUrl(String getBtUrl) {
        InterfaceKit.getBtUrl = getBtUrl;
    }
}

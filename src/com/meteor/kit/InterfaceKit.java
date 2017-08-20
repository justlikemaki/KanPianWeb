package com.meteor.kit;

import com.jfinal.kit.PropKit;

/**
 * Created by Meteor on 2015/9/15.
 *
 * @category (这里用一句话描述这个类的作用)
 */
public class InterfaceKit {
	private static String getRightUrl = "getRight";
	private static String getLeftUrl = "getLeft";
	private static String getBtUrl = "getBt";
	private static String getPageBtUrl = "pageGetBt";

	public static String getGetRightUrl() {
		return PropKit.get("serverhostip") + getRightUrl;
	}

	public static String getGetLeftUrl() {
		return PropKit.get("serverhostip") + getLeftUrl;
	}

	public static String getGetBtUrl() {
		return PropKit.get("serverhostip") + getBtUrl;
	}

	public static String getGetPageBtUrl() {
		return PropKit.get("serverhostip") + getPageBtUrl;
	}
}

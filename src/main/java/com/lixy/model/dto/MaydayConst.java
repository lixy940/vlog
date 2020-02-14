package com.lixy.model.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lixy.model.domain.Menu;

/**
 * 公共常量
 * 
 * @author : 宋浩志
 * @createDate : 2018年9月20日
 */
public class MaydayConst {
	/**
	 * 安全密码(UUID生成)，作为盐值用于用户密码的加密
	 */
	public static final String ZYD_SECURITY_KEY = "929123f8f17944e8b0a531045453e1f1";
	/**
	 * user_session
	 */
	public static final String USER_SESSION_KEY = "user_session";
	/**
	 * 最大页码
	 */
	public static final int MAX_PAGE = 100;
	/**
	 * 所有设置选项
	 */
	public static Map<String, String> OPTIONS = new HashMap<String, String>();
	/**
	 * 所有菜单
	 */
	public static List<Menu> MENUS = new ArrayList<Menu>();
	/**
	 * 主题
	 */
	public static String THEME_NAME;
	
	/**
	 * 同一IP十分钟以内重复访问同一篇文章只算一次
	 */
	public static final Integer IP_REPEAT_TIME=600;
	
	/**
     * 点击次数超过多少更新到数据库
     */
    public static final int CLICK_EXCEED = 10; 
}

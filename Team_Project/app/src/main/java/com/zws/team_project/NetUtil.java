package com.zws.team_project;

/**
 * Created by ninni on 2017/11/6.
 */

public class NetUtil {
    /**服务器IP地址 **/
    public final static String PATH_BASE = "http://123.206.135.58:8080/web-bian";

    /** 用户登录 **/
    public final static String PATH_USER_LOGIN = "/user/login_app";
    /**注册用户 **/
    public final static String PATH_USER_NEW ="/user/new_user";
    /**寻找用户 **/
    public final static String PATH_USER_FIND ="/user/find_user";
    /**修改用户信息 **/
    public final static String PATH_USER_EDIT ="/user/edit_user";

    /**获取日记 **/
    public final static String PATH_DIARY_GETALL ="/diary/getDiary";
    /**写新日记 **/
    public final static String PATH_DIARY_NEW ="/diary/newDiary";
    /**通过标题查找日记 **/
    public final static String PATH_DIARY_TITLE ="/diary/findTitle";
    /**通过时间查找日记 **/
    public final static String PATH_DIARY_DATE ="/diary/findDate";
    /**删除日记 **/
    public final static String PATH_DIARY_DELETE ="/diary/delDiary";
    /**编辑日记 **/
    public final static String PATH_DIARY_EDIT ="/diary/editDiary";

    /**获取日拍 **/
    public final static String PATH_PHOTO_GETALL ="/photo/getPhoto";
    /**传新日拍 **/
    public final static String PATH_PHOTO_NEW ="/photo/newPhoto";

    /**获取备忘 **/
    public final static String PATH_REMINDER_GETALL ="/reminder/getReminder";
    /**写新备忘 **/
    public final static String PATH_REMINDER_NEW ="/reminder/newReminder";
    /**编辑备忘 **/
    public final static String PATH_REMINDER_EDIT ="/reminder/editReminder";
    /**删除备忘 **/
    public final static String PATH_REMINDER_DELETE ="/reminder/delReminder";

    /**获取计划 **/
    public final static String PATH_PLAN_GETALL ="/plan/getPlan";
    /**写新计划 **/
    public final static String PATH_PLAN_NEW ="/plan/newPlan";
    /**删除计划 **/
    public final static String PATH_PLAN_DELETE ="/plan/delPlan";

    /**新建足迹 **/
    public final static String PATH_FOOT_NEW ="/footmark/newFootmark";
    /**获取足迹 **/
    public final static String PATH_FOOT_GET ="/footmark/getFootmark";
    /**删除足迹 **/
    public final static String PATH_FOOT_DELETE ="/footmark/delFootmark";

}
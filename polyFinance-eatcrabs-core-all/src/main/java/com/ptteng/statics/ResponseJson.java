package com.ptteng.statics;

/**
 * 一些发送给前端的全局code信息
 */
public class ResponseJson {
    //服务器拒绝处理，不进行跳转（已登陆没有权限（后台），非法数据）
    public static final String NOT_PERM_MESSAGE = "{\"code\":\"-1\",\"message\":\"服务器拒绝处理\"}";
    //前台需先调用登出接口,跳转至登陆页面登陆（未登陆没有权限，session过期，账号被冻结）
    public static final String NOT_LOGIN_MESSAGE = "{\"code\":\"-2\",\"message\":\"登录信息失效\"}";
    //服务器内部错误（通常开发完成后不会出现)
    public static final String ERROR_MESSAGE = "{\"code\":\"-3\",\"message\":\"服务器内部错误\"}";
    //重复性的数据（可能由输入或者过快的操作导致)
    public static final String BAD_REQUEST_MESSAGE = "{\"code\":\"-5\",\"message\":\"重复性的数据\"}";

}

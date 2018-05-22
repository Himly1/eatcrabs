package com.ptteng.service;

/**
 * 第三方API接口service接口
 */
public interface CommonService {

    /**
     * 检验用户姓名和身份证号是否有效
     * @param name 用户姓名
     * @param idNumber 用户身份证号
     * @return 如果匹配就返回true,其他所有情况返回false
     */
    boolean validateUserIdentity(String name,String idNumber);


    /**
     * @param account  用户账户（登录手机号）
     * @param bankCard 银行卡号
     * @param mobile   银行预留手机号
     * @return 正数:成功返回银行id -1：信息错误 -2：用户没有经过实名制认证 -3:平台不支持的银行（即后台系统不存在该银行）
     */
    Long validateUserBankCard(String account,String bankCard,String mobile);

    /**
     * 发送手机验证码的接口
     * @param mobile 需要发送验证码的手机号
     * @return 刚刚发送出去的手机验证码
     */
    String getMoblieCode(String mobile);
}

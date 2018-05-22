package com.ptteng.dubbo;

import com.alibaba.dubbo.config.annotation.Service;
import com.ptteng.domain.business.Bank;
import com.ptteng.domain.business.Identity;
import com.ptteng.domain.business.User;
import com.ptteng.exception.SessionException;
import com.ptteng.http.HttpAPIManager;
import com.ptteng.repository.BankDAO;
import com.ptteng.repository.IdentityDAO;
import com.ptteng.repository.UserDAO;
import com.ptteng.service.CommonService;
import com.ptteng.utils.RandomUtils;
import com.ptteng.utils.SMSUtils;
import com.ptteng.utlis.fuiou.BankCardUtils;
import com.ptteng.utlis.fuiou.IdentityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 第三方API接口service实现类
 */
@Component
@Service(version = "1.0.0", interfaceName = "com.ptteng.service.CommonService")
public class CommonServiceImpl implements CommonService {
    private static final Logger logger = LoggerFactory.getLogger(CommonServiceImpl.class);
    @Autowired
    private HttpAPIManager httpAPIManager;
    @Autowired
    private IdentityDAO identityDAO;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private BankDAO bankDAO;

    /**
     * 检验用户姓名和身份证号是否有效
     *
     * @param name     用户姓名
     * @param idNumber 用户身份证号
     * @return 如果匹配就返回true, 其他所有情况返回false
     */
    @Override
    public boolean validateUserIdentity(String name, String idNumber) {
        String xmlStr = null;
        try {
            xmlStr = httpAPIManager.doGet(IdentityUtils.getIdentityURL(name, idNumber));
        } catch (Exception e) {
            logger.warn("请求富友实名认证接口失败，用户名：{}，身份证号：{}", name, idNumber);
        }
        if (xmlStr == null || xmlStr.equals("")) {
            return false;
        }
        Map<String, String> result = IdentityUtils.getIdentityCode(xmlStr);
        String code = result.get("code");
        String message = result.get("message");
        logger.warn("用户：{}，身份证号：{}，认证状态码：{}，认证信息：{}", name, idNumber, code, message);
        return code != null && code.equals("0000");
    }

    /**
     * @param account  用户账户（登录手机号）
     * @param bankCard 银行卡号
     * @param mobile   银行预留手机号
     * @return 正数:成功返回银行id -1：信息错误 -2：用户没有经过实名制认证 -3:平台不支持的银行（即后台系统不存在该银行）
     */
    @Override
    public Long validateUserBankCard(String account, String bankCard, String mobile) {
        User user = userDAO.findByAccount(account);
        if (user == null) {
            throw new SessionException("账户信息已过期");
        }
        Long userId = user.getId();
        Identity identity = identityDAO.findByUserId(user.getId());
        if (identity == null) {
            return -2L;
        }
        String name = identity.getName();
        String idNumber = identity.getIdCard();
        String xmlStr = null;
        try {
            xmlStr = httpAPIManager.doGet(BankCardUtils.getIdentityURL(name, idNumber, bankCard, mobile));
        } catch (Exception e) {
            logger.warn("请求富友银行卡认证接口失败，用户id：{}，用户名：{}，银行卡号：{}", userId, name, bankCard);
        }
        if (xmlStr == null || xmlStr.equals("")) {
            return -1L;
        }
        Map<String, String> result = BankCardUtils.getBankCardCode(xmlStr);
        String code = result.get("code");
        String message = result.get("message");
        String bankName = result.get("bankName");
        String bankNumber = result.get("bankNumber");
        if (code == null || !code.equals("0000")) {
            logger.warn("验证银行卡失败，用户id：{}，用户名：{}，银行卡号：{}，状态码：{}，详细信息：{}", userId, name, bankCard, code, message);
            return -1L;
        }
        Bank bank = bankDAO.findByNumber(bankNumber);
        if (bank == null) {
            logger.warn("后台不支持的银行类型，用户id：{}，用户名：{}，银行卡号：{}，银行名：{}，银行机构号：{}", userId, name, bankCard, bankName, bankNumber);
            return -3L;
        }
        logger.warn("验证银行卡成功，用户id：{}，用户名：{}，银行卡号：{}，银行名：{}，银行机构号：{}", userId, name, bankCard, bankName, bankNumber);
        return bank.getId();
    }

    /**
     * 发送手机验证码的接口
     * @param mobile 需要发送验证码的手机号
     * @return 刚刚发送出去的手机验证码 发送失败就返回null
     */
    @Override
    public String getMoblieCode(String mobile) {
        String code = RandomUtils.getRandom(6);
        //发送验证码,如果返回状态码不是OK(详情见阿里云文档)，就视为失败
        String status = SMSUtils.sendSms(code, mobile).getCode();
        if (!status.equals("OK")) {
           return null;
        }
        return code;
    }

}

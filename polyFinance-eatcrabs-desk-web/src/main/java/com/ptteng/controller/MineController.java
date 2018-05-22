package com.ptteng.controller;

import com.ptteng.bo.DepositorInfoBO;
import com.ptteng.bo.UserInfoBO;
import com.ptteng.domain.business.User;
import com.ptteng.dubbo.DeskConsumer;
import com.ptteng.utlis.validator.ValidatorUtil;
import com.ptteng.vo.backstage.NewKeyVO;
import com.ptteng.vo.desk.IdentityVO;
import com.ptteng.vo.desk.MobileVcodeVO;
import com.ptteng.vo.desk.PwdVcodeVO;
import com.ptteng.vo.desk.TpwdVcodeVO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.Serializable;

/**
 * 我的
 */
@Controller
public class MineController implements Serializable {
    @Autowired
    private DeskConsumer consumer;
    private static Logger logger = LoggerFactory.getLogger(MineController.class);

    /**
     * 我的
     * @param model
     * @return
     */
    @RequestMapping(value = "/b/mine", method = RequestMethod.GET)
    public String mine(Model model){
        //获取用户的手机号
        String account = (String)SecurityUtils.getSubject().getPrincipal();
        User user = consumer.getCustomerService().getUserLoginToken(account);
        //获取用身份认证信息
        UserInfoBO userInfo = consumer.getCustomerService().getSingleUserInfo(user.getId());
        //获取账户信息
        DepositorInfoBO depositorInfo = consumer.getCustomerService().getSingleDepositorInfo(user.getId());
        //昨日收益
        Long ydayEarn = consumer.getCustomerService().ydayEarn(account);
        //未读消息
        

        model.addAttribute("userName", userInfo.getIdentity().getName());
        model.addAttribute("total", depositorInfo.getTotal());
        model.addAttribute("ydayearn", ydayEarn);
        model.addAttribute("income", depositorInfo.getIncome());
        model.addAttribute("news");
        return "mine";
    }

    /**
     * 我的理财
     * @param model
     * @return
     */
    @RequestMapping(value = "/b/finance", method = RequestMethod.GET)
    public String finance(Model model){
        return "success";
    }

    /**
     * 我的理财-获取验证码
     * @return
     */
    @RequestMapping(value = "/b/finance/vcode/new", method = RequestMethod.GET)
    public String getFinanceVcode(){
        return "success";
    }

    /**
     * 我的理财-续投确认
     * @param investId
     * @param confirmVO
     * @param model
     * @return
     */
    @RequestMapping(value = "/b/finance/{investId}/confirm", method = RequestMethod.POST)
    public String investConfirm(@PathVariable Long investId, @RequestBody TpwdVcodeVO confirmVO, Model model){
        String errorJson = ValidatorUtil.getErrorJSON(confirmVO);
        if(null != errorJson){
            logger.warn("续投接口接收非法参数： {}", confirmVO.toString());
            model.addAttribute("code", -1);
            model.addAttribute("message", errorJson);
            return "exception";
        }
        logger.info("续投接口接收参数： {}， id： {}", confirmVO.toString(), investId);

        return "success";
    }

    /**
     * 交易记录
     * @param model
     * @return
     */
    @RequestMapping(value = "/b/records", method = RequestMethod.POST)
    public String recodes(Model model){
        return "success";
    }

    /**
     * 银行卡管理
     * @param model
     * @return
     */
    @RequestMapping(value = "/b/bankcard", method = RequestMethod.GET)
    public String bankcard(Model model){
        return "success";
    }

    /**
     * 解绑银行卡-获取验证码
     * @return
     */
    @RequestMapping(value = "/b/bandcard/del/vcode/new", method = RequestMethod.GET)
    public String getBandcardVcode(){
        return "success";
    }

    /**
     * 解绑银行卡
     * @param delVO
     * @param model
     * @return
     */
    @RequestMapping(value = "/b/bankcard/del", method = RequestMethod.POST)
    public String bankcardDel(@RequestBody TpwdVcodeVO delVO, Model model){
        String errorJson = ValidatorUtil.getErrorJSON(delVO);
        if(null != errorJson){
            logger.warn("解绑银行卡接口接收非法参数： {}", delVO.toString());
            model.addAttribute("code", -1);
            model.addAttribute("message", errorJson);
            return "exception";
        }
        logger.info("解绑银行卡接口接收参数： {}", delVO.toString());

        return "success";
    }

    /**
     * 添加银行卡
     * @param model
     * @return
     */
    @RequestMapping(value = "/b/bankcard/set", method = RequestMethod.GET)
    public String bankcardSet(Model model){
        return "success";
    }

    /**
     * 实名认证
     * @param identityVO
     * @param model
     * @return
     */
    @RequestMapping(value = "/b/name/test", method = RequestMethod.POST)
    public String nameTest(@RequestBody IdentityVO identityVO, Model model){
        String errorJson = ValidatorUtil.getErrorJSON(identityVO);
        if(null != errorJson){
            logger.warn("实名认制接口接收非法参数： {}", identityVO.toString());
            model.addAttribute("code", -1);
            model.addAttribute("message", errorJson);
            return "exception";
        }
        logger.info("实名认证接口接收参数： {}", identityVO.toString());

        return "success";
    }

    /**
     * 设置交易密码-获取验证码
     * @return
     */
    @RequestMapping(value = "/b/tpwd/vcode/new", method = RequestMethod.GET)
    public String getTpwdVcode(){
        return "success";
    }

    /**
     * 设置交易密码
     * @param tpwdVO
     * @return
     */
    @RequestMapping(value = "/b/tpwd/set", method = RequestMethod.POST)
    public String tpwdSet(@RequestBody TpwdVcodeVO tpwdVO, Model model){
        String errorJson = ValidatorUtil.getErrorJSON(tpwdVO);
        if(null != errorJson){
            logger.warn("设置交易密码接口接收非法参数： {}", tpwdVO.toString());
            model.addAttribute("code", -1);
            model.addAttribute("message", errorJson);
            return "exception";
        }
        logger.info("设置交易密码接口接收参数： {}", tpwdVO.toString());
        return "success";
    }

    /**
     * 添加银行卡2
     * @param number
     * @return
     */
    @RequestMapping(value = "/b/bankcard/number", method = RequestMethod.POST)
    public String bankcardNumber(String number){
        return "success";
    }

    /**
     * 添加银行卡2-获取验证码
     * @param mobile
     * @return
     */
    @RequestMapping(value = "/b/bandcard/add/vcode/new", method = RequestMethod.GET)
    public String getBandcardAddVcode(String mobile){
        return "success";
    }

    /**
     * 添加银行卡2-添加
     * @param mobileVcodeVO
     * @param model
     * @return
     */
    @RequestMapping(value = "/b/bankcard/add", method = RequestMethod.POST)
    public String addBankcard(@RequestBody MobileVcodeVO mobileVcodeVO, Model model){
        String errorJson = ValidatorUtil.getErrorJSON(mobileVcodeVO);
        if(null != errorJson){
            logger.warn("添加银行卡2-添加接口接收非法参数： {}", mobileVcodeVO.toString());
            model.addAttribute("code", -1);
            model.addAttribute("message", errorJson);
            return "exception";
        }
        logger.info("添加银行卡2-添加接口接收参数： {}", mobileVcodeVO.toString());

        return "success";
    }

    /**
     * 我的消息
     * @param model
     * @return
     */
    @RequestMapping(value = "/b/events", method = RequestMethod.GET)
    public String events(Model model){
        return "success";
    }

    /**
     * 我的消息-删除消息
     * @param eventId
     * @return
     */
    @RequestMapping(value = "/b/events/{eventId}/del", method = RequestMethod.GET)
    public String delEvent(@PathVariable Long eventId){
        logger.info("我的消息-删除消息接口接收参数 id：{}", eventId);

        return "success";
    }

    /**
     * 账户安全
     * @param model
     * @return
     */
    @RequestMapping(value = "/b/security", method = RequestMethod.POST)
    public String security(Model model){
        return "success";
    }

    /**
     * 登出
     * @return
     */
    @RequestMapping(value = "/b/logout", method = RequestMethod.GET)
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        //避免生成不必要的日志
        if (!subject.isAuthenticated()) {
            return "success";
        }
        String name = (String) subject.getPrincipal();
        logger.warn("用户{}退出登录", name);
        subject.logout();
        //注意所有的jsp都必须加上<%@ page session="false" %>，否则大大降低性能
        return "success";
    }

    /**
     * 修改登录密码
     * @param newKeyVO
     * @param model
     * @return
     */
    @RequestMapping(value = "/b/lpwd/set", method = RequestMethod.POST)
    public String lpwdSet(@RequestBody NewKeyVO newKeyVO, Model model){
        String errorJson = ValidatorUtil.getErrorJSON(newKeyVO);
        if(null != errorJson){
            logger.warn("修改登录密码接口接收非法参数： {}", newKeyVO.toString());
            model.addAttribute("code", -1);
            model.addAttribute("message", errorJson);
            return "exception";
        }
        logger.info("修改登录密码接口接收参数： {}", newKeyVO.toString());

        return "success";
    }

    /**
     * 修改交易密码-输入旧交易密码
     * @param password
     * @return
     */
    @RequestMapping(value = "/b/tpwd/old", method = RequestMethod.POST)
    public String oldTpwd(@RequestBody String password){
        return "success";
    }

    /**
     * 修改交易密码-输入新交易密码
     * @param password
     * @return
     */
    @RequestMapping(value = "/b/tpwd/new", method = RequestMethod.POST)
    public String newTpwd(@RequestBody String password){
        return "success";
    }

    /**
     * 忘记交易密码-获取验证码
     * @return
     */
    @RequestMapping(value = "/b/findtpwd/vcode/new", method = RequestMethod.GET)
    public String getFindtpwdVcode(){
        return "success";
    }

    /**
     * 忘记交易密码-找回交易密码
     * @param findtpwdVO
     * @param model
     * @return
     */
    @RequestMapping(value = "/b/findtpwd/test", method = RequestMethod.POST)
    public String findtpwdTest(@RequestBody PwdVcodeVO findtpwdVO, Model model){
        String errorJson = ValidatorUtil.getErrorJSON(findtpwdVO);
        if(null != errorJson){
            logger.warn("忘记交易密码-找回交易密码接口接收非法参数： {}", findtpwdVO.toString());
            model.addAttribute("code", -1);
            model.addAttribute("message", errorJson);
            return "exception";
        }
        logger.info("忘记交易密码-找回交易密码接口接收参数： {}", findtpwdVO.toString());

        return "success";
    }

    /**
     * 交易密码设置
     * @param model
     * @return
     */
    @RequestMapping(value = "/b/tpwd", method = RequestMethod.GET)
    public String tpwd(Model model){
        return "success";
    }

    /**
     * 手势密码-关闭手势密码
     * @return
     */
    @RequestMapping(value = "/b/gpwd/used", method = RequestMethod.GET)
    public String usedGpwd(){
        return "success";
    }

    /**
     * 手势密码-输入登录密码
     * @param pawword
     * @return
     */
    @RequestMapping(value = "/b/gpwd/using", method = RequestMethod.POST)
    public String usingGpwd(@RequestBody String pawword){
        return "success";
    }

    /**
     * 手势密码-设置手势密码
     * @param password
     * @return
     */
    @RequestMapping(value = "/b/gpwd/add", method = RequestMethod.POST)
    public String addGpwd(@RequestBody String password){
        return "success";
    }

    /**
     * 意见反馈
     * @param content
     * @return
     */
    @RequestMapping(value = "/b/feedback", method = RequestMethod.POST)
    public String feedback(@RequestBody String content){
        return "success";
    }
}


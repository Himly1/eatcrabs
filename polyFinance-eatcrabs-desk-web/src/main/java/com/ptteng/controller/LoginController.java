package com.ptteng.controller;

import com.ptteng.dubbo.DeskConsumer;
import com.ptteng.utlis.IPUtil;
import com.ptteng.utlis.Md5Util;
import com.ptteng.utlis.validator.ValidatorUtil;
import com.ptteng.vo.desk.MobilePwdVO;
import com.ptteng.vo.desk.MobileVcodeVO;
import com.ptteng.vo.desk.SignupVO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * 登陆
 */
@Controller
public class LoginController {
    private static Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private DeskConsumer consumer;
    /**
     * 登陆
     *
     * @param loginVO
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/b/u/login", method = RequestMethod.POST)
    public String userLogin(@RequestBody MobilePwdVO loginVO, Model model, HttpServletRequest request) {
        String errorJson = ValidatorUtil.getErrorJSON(loginVO);
        if(null != errorJson){
            logger.warn("登陆接口接收非法参数： {}", loginVO.toString());
            model.addAttribute("code", -1);
            model.addAttribute("message", errorJson);
            return "exception";
        }
        logger.info("登陆接口接收参数： {}", loginVO.toString());

        Subject subject = SecurityUtils.getSubject();
        //登入前首先登出
        subject.logout();
        logger.info("登录接口接收参数：{},ip:{}", loginVO, IPUtil.getIpAddr(request));
        String account = loginVO.getMobile();
        //参数通过后生成shiro令牌，传参
        UsernamePasswordToken token = new UsernamePasswordToken(account, Md5Util.getMd5WithSalt(loginVO.getPassword()));
        System.out.println(Md5Util.getMd5WithSalt(loginVO.getPassword()));
        try {
            subject.login(token);
        } catch (UnknownAccountException e1) {
            logger.warn("不存在的用户信息，账号名：{}", account);
            model.addAttribute("code", -100);
            model.addAttribute("message", "用户不存在");
            return "exception";
        } catch (AuthenticationException e2) {
            logger.warn("错误的用户密码，账号名：{}", account);
            model.addAttribute("code", -101);
            model.addAttribute("message", "密码错误");
            return "exception";
        }
        logger.warn("用户登录成功，用户名：{}，ip：{}", account, IPUtil.getIpAddr(request));
        return "success";
    }

    /**
     * 注册-获取验证码
     *
     * @param mobile
     * @param model
     * @return
     */
    @RequestMapping(value = "/b/u/signup/vcode/new", method = RequestMethod.GET)
    public String getSignupVcode(String mobile, Model model) {

        return "success";
    }

    /**
     * 注册
     *
     * @param signupVO
     * @param model
     * @return
     */
    @RequestMapping(value = "/b/u/signup", method = RequestMethod.POST)
    public String signup(@RequestBody SignupVO signupVO, Model model) {
        String errorJson = ValidatorUtil.getErrorJSON(signupVO);
        if(null != errorJson){
            logger.warn("注册接口接收非法参数： {}", signupVO.toString());
            model.addAttribute("code", -1);
            model.addAttribute("message", errorJson);
            return "exception";
        }
        logger.info("注册接口接收参数： {}", signupVO.toString());

        return "success";
    }

    /**
     * 找回密码-获取验证码
     *
     * @param mobile
     * @param model
     * @return
     */
    @RequestMapping(value = "/b/u/findpwd/vcode/new", method = RequestMethod.GET)
    public String getFindpwdVcode(String mobile, Model model) {
        return "success";
    }

    /**
     * 找回密码-验证验证码
     *
     * @param verifyVO
     * @param model
     * @return
     */
    @RequestMapping(value = "/b/u/findpwd/vcode/test", method = RequestMethod.POST)
    public String testFindpwdVcode(@RequestBody MobileVcodeVO verifyVO, Model model) {
        String errorJson = ValidatorUtil.getErrorJSON(verifyVO);
        if(null != errorJson){
            logger.warn("找回密码-验证验证码接口接收非法参数： {}", verifyVO.toString());
            model.addAttribute("code", -1);
            model.addAttribute("message", errorJson);
        }
        logger.warn("找回密码-验证验证码接口接收参数： {}", verifyVO.toString());

        return "success";
    }

    /**
     * 找回密码-更新密码
     *
     * @param resetVO
     * @param model
     * @return
     */
    @RequestMapping(value = "/b/u/findpwd/edit", method = RequestMethod.POST)
    public String editFindpwd(@RequestBody MobilePwdVO resetVO, Model model) {
        String errorJson = ValidatorUtil.getErrorJSON(resetVO);
        if(null != errorJson){
            logger.warn("找回密码-更新密码接口接收非法参数： {}", resetVO.toString());
            model.addAttribute("code", -1);
            model.addAttribute("message", errorJson);
        }
        logger.warn("找回密码-更新密码接口接收参数： {}", resetVO.toString());

        return "success";
    }

    /**
     * 手势密码登陆
     * @param password
     * @param model
     * @return
     */
    @RequestMapping(value = "/b/u/login/gesture", method = RequestMethod.POST)
    public String gestureLogin(String password, Model model) {
        return "success";
    }
}

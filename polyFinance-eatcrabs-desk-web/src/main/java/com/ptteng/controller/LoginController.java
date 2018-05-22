package com.ptteng.controller;

import com.ptteng.dubbo.DeskConsumer;
import com.ptteng.shiro.token.DeskToken;
import com.ptteng.utlis.IPUtil;
import com.ptteng.utlis.Md5Util;
import com.ptteng.utlis.validator.ValidatorUtil;
import com.ptteng.vo.desk.GestureVO;
import com.ptteng.vo.desk.MobilePwdVO;
import com.ptteng.vo.desk.MobileVcodeVO;
import com.ptteng.vo.desk.SignupVO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
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
     */
    @RequestMapping(value = "/b/u/login", method = RequestMethod.POST)
    public String userLogin(@RequestBody MobilePwdVO loginVO, Model model, HttpServletRequest request) {
        String errorJson = ValidatorUtil.getErrorJSON(loginVO);
        if (errorJson != null) {
            logger.info("用户登录接口接收的非法参数：{},ip:{}", loginVO, IPUtil.getIpAddr(request));
            model.addAttribute("code", -1);
            model.addAttribute("message", errorJson);
            return "exception";
        }
        Subject subject = SecurityUtils.getSubject();
        //登入前首先登出
        subject.logout();
        logger.info("用户登录接口接收参数：{},ip:{}", loginVO, IPUtil.getIpAddr(request));
        String account = loginVO.getMobile();
        //参数通过后生成shiro令牌，传参
        DeskToken token = new DeskToken(account, Md5Util.getMd5WithSalt(loginVO.getPassword()), DeskToken.LoginType.PASSWORD, true);
        try {
            subject.login(token);
        } catch (DisabledAccountException e1) {
            logger.warn("用户账号被冻结，账号名：{}", account);
            model.addAttribute("code", -102);
            model.addAttribute("message", "用户账号被冻结");
            return "exception";
        } catch (UnknownAccountException e2) {
            logger.warn("不存在的用户信息，账号名：{}", account);
            model.addAttribute("code", -100);
            model.addAttribute("message", "用户不存在");
            return "exception";
        } catch (AuthenticationException e3) {
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
     */
    @RequestMapping(value = "/b/u/signup/vcode/new", method = RequestMethod.GET)
    public String getSignupVcode(String mobile, Model model) {

        return "success";
    }

    /**
     * 注册
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
     */
    @RequestMapping(value = "/b/u/findpwd/vcode/new", method = RequestMethod.GET)
    public String getFindpwdVcode(String mobile, Model model) {
        return "success";
    }

    /**
     * 找回密码-验证验证码
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
     */
    @RequestMapping(value = "/b/u/login/gesture", method = RequestMethod.POST)
    public String gestureLogin(@RequestBody GestureVO vo, Model model, HttpServletRequest request) {
        String errorJson = ValidatorUtil.getErrorJSON(vo);
        if (errorJson != null) {
            logger.info("手势密码登录接口接收的非法参数：{},ip:{}", vo, IPUtil.getIpAddr(request));
            model.addAttribute("code", -1);
            model.addAttribute("message", errorJson);
            return "exception";
        }
        String gesture = vo.getGesture();
        Subject subject = SecurityUtils.getSubject();
        logger.info("手势密码登录接口接收参数：{},ip:{}", gesture, IPUtil.getIpAddr(request));
        String account = (String) subject.getPrincipal();
        if (account == null) {
            model.addAttribute("code", -2);
            model.addAttribute("message", "手势密码过期，请通过密码重新登录");
            return "exception";
        }
        //参数通过后生成shiro令牌，传参
        DeskToken token = new DeskToken(account, Md5Util.getMd5WithSalt(gesture), DeskToken.LoginType.GESTURE, true);
        try {
            subject.login(token);
        } catch (DisabledAccountException e1) {
            logger.warn("用户账号被冻结，账号名：{}", account);
            model.addAttribute("code", -102);
            model.addAttribute("message", "用户账号被冻结");
            return "exception";
        } catch (AuthenticationException e3) {
            logger.warn("错误的用户密码，账号名：{}", account);
            model.addAttribute("code", -101);
            model.addAttribute("message", "密码错误");
            return "exception";
        }
        logger.warn("用户通过手势密码登录成功，用户名：{}，ip：{}", account, IPUtil.getIpAddr(request));
        return "success";
    }

}

package com.ptteng.controller;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.internal.org.apache.commons.codec.digest.DigestUtils;
import com.ptteng.bo.PageHandleBO;
import com.ptteng.bo.StaffInfoBO;
import com.ptteng.domain.manager.Module;
import com.ptteng.domain.manager.Role;
import com.ptteng.domain.manager.Staff;
import com.ptteng.dubbo.BackstageConsumer;
import com.ptteng.shiro.token.BackstageToken;
import com.ptteng.utlis.IPUtil;
import com.ptteng.utlis.Md5Util;
import com.ptteng.utlis.validator.ValidatorUtil;
import com.ptteng.vo.backstage.*;
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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 后台管理
 */
@Controller
public class ManagerController {
    private static final Logger logger = LoggerFactory.getLogger(ManagerController.class);
    @Autowired
    private BackstageConsumer consumer;

    /**
     * 普通员工登录接口
     */
    @RequestMapping(value = "/a/u/login", method = RequestMethod.POST)
    public String staffLogin(@RequestBody StaffLogin loginVO, Model model, HttpServletRequest request) {
        String errorJson = ValidatorUtil.getErrorJSON(loginVO);
        if (errorJson != null) {
            logger.info("员工登录接口接收的非法参数：{},ip:{}", loginVO, IPUtil.getIpAddr(request));
            model.addAttribute("code", -1);
            model.addAttribute("message", errorJson);
            return "exception";
        }
        Subject subject = SecurityUtils.getSubject();
        //登入前首先登出
        subject.logout();
        logger.info("员工登录接口接收参数：{},ip:{}", loginVO, IPUtil.getIpAddr(request));
        String account = loginVO.getStaffName();
        //参数通过后生成shiro令牌，传参
        UsernamePasswordToken token = new BackstageToken(account, Md5Util.getMd5WithSalt(loginVO.getPassword())
                , BackstageToken.LoginType.STAFF);
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
        logger.warn("员工用户登录成功，用户名：{}，ip：{}", account, IPUtil.getIpAddr(request));
        model.addAttribute("account", account);
        model.addAttribute("token", consumer.getManagerService().getStaffPerms(account));
        return "loginToken";
    }

    /**
     * 登出接口（包括超管）
     */
    @RequestMapping(value = "/a/u/logout", method = RequestMethod.GET)
    public String staffLogin() {
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
     * 超管登录
     */
    @RequestMapping(value = "/a/u/root", method = RequestMethod.POST)
    public String rootLogin(HttpServletRequest request, @RequestBody RootLogin loginVO, Model model) {
        String errorJson = ValidatorUtil.getErrorJSON(loginVO);
        if (errorJson != null) {
            logger.warn("超管登录接口接收的非法参数：{},ip:{}", loginVO, IPUtil.getIpAddr(request));
            model.addAttribute("message", errorJson);
            model.addAttribute("code", -1);
            return "exception";
        }
        Subject subject = SecurityUtils.getSubject();
        //登入前首先登出
        subject.logout();
        logger.warn("超管登录接口接收参数：{},ip:{}", loginVO, IPUtil.getIpAddr(request));
        String account = loginVO.getRootName();
        //参数通过后生成shiro令牌，传参
        UsernamePasswordToken token = new BackstageToken(account, DigestUtils.sha512Hex((loginVO.getPassword()))
                , BackstageToken.LoginType.ROOT);
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            logger.warn("超管用户登录失败，用户名：{}，ip：{}", account, IPUtil.getIpAddr(request));
            model.addAttribute("code", -101);
            model.addAttribute("message", "账号密码错误");
            return "exception";
        }
        logger.warn("超管用户登录成功，用户名：{}，ip：{}", account, IPUtil.getIpAddr(request));
        model.addAttribute("account", account);
        model.addAttribute("token", consumer.getManagerService().getRootPerms());
        return "loginToken";
    }

    /**
     * 模块详情
     */
    @RequestMapping(value = "/a/module/list", method = RequestMethod.GET)
    public String getModuleList(ModulesQuery query, Model model) {
        PageHandleBO<Module> pageHandleBO = consumer.getManagerService().findModulesByQuery(query);
        model.addAttribute("modules", pageHandleBO.getObjects());
        model.addAttribute("count", pageHandleBO.getCount());
        return "moduleList";
    }

    /**
     * 查看单个模块
     */
    @ResponseBody
    @RequestMapping(value = "/a/module/{id}", method = RequestMethod.GET)
    public String getSingleModule(@PathVariable Long id) {
        Module module = consumer.getManagerService().getSingleModule(id);
        Map<String, Object> result = new HashMap<>();
        result.put("fmoduleId", module.getFmoduleId());
        result.put("name", module.getName());
        return JSON.toJSONString(result);
    }

    /**
     * 编辑模块
     */
    @RequestMapping(value = "/a/module/edit/{id}", method = RequestMethod.POST)
    public String editModule(@PathVariable Long id, @RequestBody Module module, Model model) {
        String errorJson = ValidatorUtil.getErrorJSON(module);
        if (errorJson != null) {
            logger.info("编辑模块接口接收的非法参数：{},id:{}", module, id);
            model.addAttribute("code", -1);
            model.addAttribute("message", errorJson);
            return "exception";
        }
        logger.info("编辑模块接口接收参数：{},id:{}", module, id);
        Subject subject = SecurityUtils.getSubject();
        String account = (String) subject.getPrincipal();
        String name = subject.hasRole("root") ? "root" : account;
        if (consumer.getManagerService().editSingleModule(id, module, name)) {
            logger.warn("编辑模块成功，id:{}", id);
            return "success";
        }
        logger.warn("编辑模块失败，id:{}", id);
        model.addAttribute("code", -1);
        model.addAttribute("message", "该模块信息不存在");
        return "exception";
    }

    /**
     * 查看角色列表
     */
    @RequestMapping(value = "/a/role/list", method = RequestMethod.GET)
    public String getRoleList(RolesQuery query, Model model) {
        PageHandleBO<Role> pageHandleBO = consumer.getManagerService().findRolesByQuery(query);
        model.addAttribute("roles", pageHandleBO.getObjects());
        model.addAttribute("count", pageHandleBO.getCount());
        return "roleList";
    }

    /**
     * 查看单个角色
     */
    @ResponseBody
    @RequestMapping(value = "/a/role/{id}", method = RequestMethod.GET)
    public String getSingleRole(@PathVariable Long id) {
        return JSON.toJSONString(consumer.getManagerService().getSingleRole(id));
    }

    /**
     * 角色删除
     */
    @RequestMapping(value = "/a/role/delete/{id}", method = RequestMethod.POST)
    public String deleteRole(@PathVariable Long id) {
        Subject subject = SecurityUtils.getSubject();
        String account = (String) subject.getPrincipal();
        String name = subject.hasRole("root") ? "root" : account;
        consumer.getManagerService().deleteSingleRole(id);
        logger.warn("成功删除角色，id：{}，操作人:{}", id, name);
        return "success";
    }

    /**
     * 模块列表（三列复选框）
     */
    @RequestMapping(value = "/a/role/module", method = RequestMethod.GET)
    public String moduleAll(Model model) {
        model.addAttribute("modules", consumer.getManagerService().getAllModules());
        return "moduleAll";
    }

    /**
     * 新增角色
     */
    @RequestMapping(value = "/a/role/new", method = RequestMethod.POST)
    public String addRole(@RequestBody RoleVO roleVO, Model model) {
        String errorJson = ValidatorUtil.getErrorJSON(roleVO);
        if (errorJson != null) {
            logger.info("新增角色接口接收的非法参数：{}", roleVO);
            model.addAttribute("code", -1);
            model.addAttribute("message", errorJson);
            return "exception";
        }
        logger.info("新增角色接口接收参数：{}", roleVO);
        Subject subject = SecurityUtils.getSubject();
        String account = (String) subject.getPrincipal();
        String name = subject.hasRole("root") ? "root" : account;
        if (consumer.getManagerService().addSingleRole(roleVO, name)) {
            logger.warn("新增角色成功，角色名：{}", roleVO.getRoleName());
            return "success";
        }
        logger.warn("新增角色失败，角色名：{}", roleVO.getRoleName());
        model.addAttribute("code", -5);
        model.addAttribute("message", "角色已经存在");
        return "exception";
    }

    /**
     * 编辑角色
     */
    @RequestMapping(value = "/a/role/edit/{id}", method = RequestMethod.POST)
    public String editRole(@PathVariable Long id, @RequestBody RoleVO roleVO, Model model) {
        String errorJson = ValidatorUtil.getErrorJSON(roleVO);
        if (errorJson != null) {
            logger.info("编辑角色接口接收的非法参数：{}，id：{}", roleVO, id);
            model.addAttribute("code", -1);
            model.addAttribute("message", errorJson);
            return "exception";
        }
        logger.info("编辑角色接口接收参数：{}，id：{}", roleVO, id);
        Subject subject = SecurityUtils.getSubject();
        String account = (String) subject.getPrincipal();
        String name = subject.hasRole("root") ? "root" : account;
        if (consumer.getManagerService().editSingleRole(id, roleVO, name)) {
            return "success";
        }
        model.addAttribute("code", -5);
        model.addAttribute("message", "角色不存在或者角色名重复");
        return "exception";
    }

    /**
     * 账号（后台）管理-账号管理列表
     */
    @RequestMapping(value = "/a/staff/list", method = RequestMethod.GET)
    public String getStaffList(StaffsQuery query, Model model) {
        PageHandleBO<StaffInfoBO> pageHandleBO = consumer.getManagerService().findStaffsByQuery(query);
        model.addAttribute("staffInfos", pageHandleBO.getObjects());
        model.addAttribute("count", pageHandleBO.getCount());
        return "staffList";
    }

    /**
     * 查看单个职工列表
     */
    @ResponseBody
    @RequestMapping(value = "/a/staff/{id}", method = RequestMethod.GET)
    public String getSingleStaff(@PathVariable Long id) {
        Staff staff = consumer.getManagerService().getSingleStaff(id);
        Map<String, Object> result = new HashMap<>();
        result.put("account", staff.getAccount());
        result.put("mobile", staff.getMobile());
        result.put("roleId", staff.getRoleId());
        return JSON.toJSONString(result);
    }

    /**
     * 删除员工账号
     */
    @RequestMapping(value = "/a/staff/delete/{id}", method = RequestMethod.POST)
    public String deleteStaff(@PathVariable Long id) {
        Subject subject = SecurityUtils.getSubject();
        String account = (String) subject.getPrincipal();
        String name = subject.hasRole("root") ? "root" : account;
        consumer.getManagerService().deleteSingleStaff(id);
        logger.warn("成功删除员工，id：{},操作人：{}", id, name);
        return "success";
    }

    /**
     * 角色列表（下拉单选框）
     */
    @RequestMapping(value = "/a/staff/role", method = RequestMethod.GET)
    public String roleAll(Model model) {
        model.addAttribute("roles", consumer.getManagerService().getAllRoles());
        return "roleAll";
    }

    /**
     * 新增员工账号
     */
    @RequestMapping(value = "/a/staff/new", method = RequestMethod.POST)
    public String staffNew(@RequestBody Staff staff, Model model) {
        String errorJson = ValidatorUtil.getErrorJSON(staff);
        if (errorJson != null) {
            logger.info("新增员工接口接收的非法参数：{}", staff);
            model.addAttribute("code", -1);
            model.addAttribute("message", errorJson);
            return "exception";
        }
        logger.info("新增员工接口接收参数：{}", staff);
        Subject subject = SecurityUtils.getSubject();
        String account = (String) subject.getPrincipal();
        String name = subject.hasRole("root") ? "root" : account;
        Long id = consumer.getManagerService().addSingleStaff(staff, name).getId();
        logger.info("新增员工成功，员工id：{}", id);
        return "success";
    }

    /**
     * 编辑员工账号
     */
    @RequestMapping(value = "/a/staff/edit/{id}", method = RequestMethod.POST)
    public String editStaff(@PathVariable Long id, @RequestBody Staff staff, Model model) {
        String errorJson = ValidatorUtil.getErrorJSON(staff);
        if (errorJson != null) {
            logger.info("编辑员工接口接收的非法参数：{}，id：{}", staff, id);
            model.addAttribute("code", -1);
            model.addAttribute("message", errorJson);
            return "exception";
        }
        logger.info("编辑员工接口接收参数：{}，id：{}", staff, id);
        Subject subject = SecurityUtils.getSubject();
        String account = (String) subject.getPrincipal();
        String name = subject.hasRole("root") ? "root" : account;
        if (consumer.getManagerService().editSingleStaff(id, staff, name)) {
            logger.warn("编辑员工成功，id：{}", id);
            return "success";
        }
        logger.warn("编辑员工失败，id：{}", id);
        model.addAttribute("code", -1);
        model.addAttribute("message", "该职工信息不存在");
        return "exception";
    }

    /**
     * 修改密码
     */
    @RequestMapping(value = "/a/common/password", method = RequestMethod.POST)
    public String updatePassword(@RequestBody NewKeyVO newKeyVO, Model model) {
        String errorJson = ValidatorUtil.getErrorJSON(newKeyVO);
        if (errorJson != null) {
            logger.warn("修改密码接口接收的非法参数：{}", newKeyVO);
            model.addAttribute("code", -1);
            model.addAttribute("message", errorJson);
            return "exception";
        }
        logger.warn("修改密码接口接收参数：{}", newKeyVO);
        Subject subject = SecurityUtils.getSubject();
        String name = (String) subject.getPrincipal();
        boolean root = subject.hasRole("root");
        //需要区分是超管修改还是普通用户修改
        boolean success;
        if (root) {
            success = consumer.getManagerService().updateRootKey(name, newKeyVO);
        } else {
            success = consumer.getManagerService().updateStaffKey(name, newKeyVO);
        }
        //根据不同结果返回不同状态码
        if (success) {
            logger.warn("修改密码成功，登录名：{}，是否为超管用户：{}", name, root);
            return "success";
        } else {
            logger.warn("修改密码失败，登录名：{}，是否为超管用户：{}", name, root);
            model.addAttribute("code", -1);
            model.addAttribute("message", "修改密码失败");
            return "exception";
        }
    }
}

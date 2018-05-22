package com.ptteng.service;

import com.ptteng.bo.PageHandleBO;
import com.ptteng.bo.StaffInfoBO;
import com.ptteng.bo.BackstagePermsBO;
import com.ptteng.domain.business.User;
import com.ptteng.domain.manager.Module;
import com.ptteng.domain.manager.Role;
import com.ptteng.domain.manager.Root;
import com.ptteng.domain.manager.Staff;
import com.ptteng.vo.backstage.*;

import java.util.List;

public interface ManagerService {

    /**
     * 查询模块信息
     */
    PageHandleBO<Module> findModulesByQuery(ModulesQuery query);

    /**
     * 查询角色列表
     */
    PageHandleBO<Role> findRolesByQuery(RolesQuery query);

    /**
     * 查询职工列表
     */
    PageHandleBO<StaffInfoBO> findStaffsByQuery(StaffsQuery query);

    /**
     * 获取所有模块
     */
    List<Module> getAllModules();

    /**
     * 获取单个模块
     */
    Module getSingleModule(Long id);

    /**
     * 编辑单个模块
     */
    boolean editSingleModule(Long id, Module module,String name);

    /**
     * 获取所有角色
     */
    List<Role> getAllRoles();

    /**
     * 查询单个角色
     */
    RoleVO getSingleRole(Long id);

    /**
     * 新增单个角色
     */
    boolean addSingleRole(RoleVO roleVO, String createBy);

    /**
     * 编辑单个角色
     */
    boolean editSingleRole(Long id ,RoleVO roleVO,String name);

    /**
     * 删除单个角色
     */
    void deleteSingleRole(Long id);

    /**
     * 新增单个职工账号
     */
    Staff addSingleStaff(Staff staff, String createBy);

    /**
     * 查询单个职工账号
     */
    Staff getSingleStaff(Long id);

    /**
     * 编辑单个职工账号
     */
    boolean editSingleStaff(Long id, Staff staff,String name);

    /**
     * 删除单个职工账号
     */
    void deleteSingleStaff(Long id);

    /**
     * 修改单个职工密码
     */
    boolean updateStaffKey(String account, NewKeyVO vo);

    /**
     * 获取员工登录密码
     */
    Staff getStaffLoginToken(String account);

    /**
     * 获取员工权限信息
     */
    BackstagePermsBO getStaffPerms(String account);

    /**
     * 获取超管登录密码
     */
    Root getRootLoginToken(String account);

    /**
     * 获取超管权限
     */
    BackstagePermsBO getRootPerms();

    /**
     * 超管用户修改自己的密码
     */
    boolean updateRootKey(String account, NewKeyVO vo);
}

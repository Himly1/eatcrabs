package com.ptteng.dubbo;

import com.alibaba.dubbo.config.annotation.Service;
import com.auth0.jwt.internal.org.apache.commons.codec.digest.DigestUtils;
import com.ptteng.bo.PageHandleBO;
import com.ptteng.bo.StaffInfoBO;
import com.ptteng.bo.BackstagePermsBO;
import com.ptteng.domain.business.User;
import com.ptteng.domain.manager.*;
import com.ptteng.repository.*;
import com.ptteng.utlis.Md5Util;
import com.ptteng.utlis.jpa.CopyUtil;
import com.ptteng.utlis.jpa.MySpecification;
import com.ptteng.utlis.jpa.PageableUtil;
import com.ptteng.service.ManagerService;
import com.ptteng.vo.backstage.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * RBAC业务实现类
 */
@Component
@Service(version = "1.0.0", interfaceName = "com.ptteng.service.ManagerService")
public class ManagerServiceImpl implements ManagerService {
    @Autowired
    private ModuleDAO moduleDAO;
    @Autowired
    private RoleDAO roleDAO;
    @Autowired
    private StaffDAO staffDAO;
    @Autowired
    private RoleModuleDAO roleModuleDAO;
    @Autowired
    private RootDAO rootDAO;
    @Autowired
    private UserDAO userDAO;

    /**
     * 查询模块信息
     */
    @Override
    public PageHandleBO<Module> findModulesByQuery(ModulesQuery query) {
        PageHandleBO<Module> pageHandleBO = new PageHandleBO<>();
        Page<Module> modulePage = moduleDAO.findAll(new MySpecification<>(query), PageableUtil.getPageRequest(query));
        pageHandleBO.setCount(modulePage.getTotalElements());
        pageHandleBO.setObjects(modulePage.getContent());
        return pageHandleBO;
    }

    /**
     * 查询角色列表
     */
    @Override
    public PageHandleBO<Role> findRolesByQuery(RolesQuery query) {
        PageHandleBO<Role> pageHandleBO = new PageHandleBO<>();
        Page<Role> rolePage = roleDAO.findAll(new MySpecification<>(query), PageableUtil.getPageRequest(query));
        pageHandleBO.setCount(rolePage.getTotalElements());
        pageHandleBO.setObjects(rolePage.getContent());
        return pageHandleBO;
    }

    /**
     * 查询职工列表
     */
    @Override
    public PageHandleBO<StaffInfoBO> findStaffsByQuery(StaffsQuery query) {
        PageHandleBO<StaffInfoBO> pageHandleBO = new PageHandleBO<>();
        Page<Staff> staffPage = staffDAO.findAll(new MySpecification<>(query), PageableUtil.getPageRequest(query));
        List<StaffInfoBO> list = new LinkedList<>();
        Role role;
        for (Staff staff : staffPage.getContent()) {
            StaffInfoBO staffInfoBO = new StaffInfoBO();
            staffInfoBO.setStaff(staff);
            role = roleDAO.findOne(staff.getRoleId());
            staffInfoBO.setRoleName(role == null ? "暂无角色" : role.getName());
            list.add(staffInfoBO);
        }
        pageHandleBO.setCount(staffPage.getTotalElements());
        pageHandleBO.setObjects(list);
        return pageHandleBO;
    }

    /**
     * 获取所有模块
     */
    @Override
    public List<Module> getAllModules() {
        return moduleDAO.findAll();
    }

    /**
     * 获取单个模块
     */
    @Override
    public Module getSingleModule(Long id) {
        Module module = moduleDAO.findOne(id);
        return module == null ? new Module() : module;
    }

    /**
     * 编辑单个模块
     */
    @Override
    public boolean editSingleModule(Long id, Module vo, String name) {
        Module po = moduleDAO.findOne(id);
        if (po == null) {
            return false;
        }
        CopyUtil.copyValue(vo, po);
        po.setId(id);
        po.setUpdateAt(System.currentTimeMillis());
        po.setUpdateBy(name);
        moduleDAO.save(po);
        return true;
    }

    /**
     * 获取所有角色
     */
    @Override
    public List<Role> getAllRoles() {
        return roleDAO.findAll();
    }

    /**
     * 查询单个角色
     */
    @Override
    public RoleVO getSingleRole(Long id) {
        Role role = roleDAO.findOne(id);
        RoleVO roleVO = new RoleVO();
        if (role == null) {
            return roleVO;
        }
        List<RoleModule> list = roleModuleDAO.findByRoleId(id);
        Set<Long> ids = new HashSet<>();
        for (RoleModule roleModule : list) {
            ids.add(roleModule.getModuleId());
        }
        roleVO.setRoleName(role.getName());
        roleVO.setModuleIds(ids);
        return roleVO;
    }


    /**
     * 新增单个角色
     */
    @Override
    @Transactional
    public boolean addSingleRole(RoleVO roleVO, String name) {
        if (roleDAO.findByName(roleVO.getRoleName()) != null) {
            return false;
        }
        Role role = new Role();
        role.setName(roleVO.getRoleName());
        long current = System.currentTimeMillis();
        role.setCreateBy(name);
        role.setUpdateBy(name);
        role.setCreateAt(current);
        role.setUpdateAt(current);
        role = roleDAO.save(role);
        RoleModule roleModule = new RoleModule();
        for (Long moduleId : roleVO.getModuleIds()) {
            roleModule.setRoleId(role.getId());
            roleModule.setModuleId(moduleId);
            roleModule.setCreateAt(current);
            roleModule.setUpdateAt(current);
            roleModuleDAO.save(roleModule);
        }
        return true;
    }


    /**
     * 编辑单个角色
     */
    @Override
    @Transactional
    public boolean editSingleRole(Long id, RoleVO roleVO, String name) {
        if (roleDAO.findByName(roleVO.getRoleName()) != null) {
            return false;
        }
        Role role = roleDAO.findOne(id);
        if (role == null) {
            return false;
        }
        long current = System.currentTimeMillis();
        role.setUpdateAt(current);
        role.setName(roleVO.getRoleName());
        role.setUpdateBy(name);
        roleDAO.save(role);
        List<RoleModule> roleModules = roleModuleDAO.findByRoleId(role.getId());
        roleModuleDAO.delete(roleModules);
        RoleModule roleModule = new RoleModule();
        for (Long moduleId : roleVO.getModuleIds()) {
            roleModule.setCreateAt(current);
            roleModule.setUpdateAt(current);
            roleModule.setRoleId(role.getId());
            roleModule.setModuleId(moduleId);
            roleModuleDAO.save(roleModule);
        }
        return false;
    }


    /**
     * 删除单个角色
     */
    @Override
    public void deleteSingleRole(Long id) {
        roleDAO.delete(id);
    }


    /**
     * 新增单个职工账号
     */
    @Override
    public Staff addSingleStaff(Staff staff, String createBy) {
        staff.setCreateAt(System.currentTimeMillis());
        staff.setUpdateAt(System.currentTimeMillis());
        staff.setCreateBy(createBy);
        staff.setUpdateBy(createBy);
        return staffDAO.save(staff);
    }

    /**
     * 查询单个职工账号
     */
    @Override
    public Staff getSingleStaff(Long id) {
        Staff staff = staffDAO.findOne(id);
        return staff == null ? new Staff() : staff;
    }


    /**
     * 编辑单个职工账号
     */
    @Override
    public boolean editSingleStaff(Long id, Staff vo, String name) {
        Staff po = staffDAO.findOne(id);
        if (po == null) {
            return false;
        }
        CopyUtil.copyValue(vo, po);
        po.setId(id);
        po.setUpdateAt(System.currentTimeMillis());
        po.setUpdateBy(name);
        staffDAO.save(po);
        return true;
    }


    /**
     * 删除单个职工账号
     */
    @Override
    public void deleteSingleStaff(Long id) {
        staffDAO.delete(id);
    }

    /**
     * 修改单个职工密码
     */
    @Override
    public boolean updateStaffKey(String account, NewKeyVO vo) {
        Staff staff = staffDAO.findByAccount(account);
        if (!Md5Util.getMd5WithSalt(vo.getOld()).equals(staff.getKey())) {
            return false;
        }
        staff.setKey(Md5Util.getMd5WithSalt(vo.getNewKey()));
        staffDAO.save(staff);
        return true;
    }

    /**
     * 获取员工登录密码
     */
    @Override
    public Staff getStaffLoginToken(String account) {
        return staffDAO.findByAccount(account);
    }

    /**
     * 获取员工权限信息
     */
    @Override
    public BackstagePermsBO getStaffPerms(String account) {
        Staff staff = staffDAO.findByAccount(account);
        if (staff == null) {
            return null;
        }
        //判断该角色是否被删除
        Role role = roleDAO.findOne(staff.getRoleId());
        if (role == null) {
            return null;
        }
        //查找关系表
        List<RoleModule> list = roleModuleDAO.findByRoleId(staff.getRoleId());
        //判断该角色是否对应有权限
        if (list == null || list.size() == 0) {
            return null;
        }
        //添加权限
        List<Module> perms = new LinkedList<>();
        Module module;
        for (RoleModule roleModule : list) {
            module = moduleDAO.findOne(roleModule.getModuleId());
            if (module == null) {
                continue;
            }
            perms.add(module);
        }
        BackstagePermsBO staffPermsBO = new BackstagePermsBO();
        staffPermsBO.setRoleName(role.getName());
        staffPermsBO.setPerms(perms);
        return staffPermsBO;
    }

    /**
     * 获取超管登录密码
     */
    @Override
    public Root getRootLoginToken(String account) {
        return rootDAO.findByAccount(account);
    }

    /**
     * 获取超管权限
     */
    @Override
    public BackstagePermsBO getRootPerms() {
        BackstagePermsBO staffPermsBO = new BackstagePermsBO();
        staffPermsBO.setRoleName("root");
        staffPermsBO.setPerms(moduleDAO.findAll());
        return staffPermsBO;
    }

    /**
     * 超管用户修改自己的密码
     */
    @Override
    public boolean updateRootKey(String account, NewKeyVO vo) {
        String oldKey = DigestUtils.sha512Hex(vo.getOld());
        Root root = rootDAO.findByAccount(account);
        if (!oldKey.equals(root.getKey())) {
            return false;
        }
        String newKey = DigestUtils.sha512Hex(vo.getNewKey());
        root.setKey(newKey);
        root.setUpdateAt(System.currentTimeMillis());
        rootDAO.save(root);
        return true;
    }
}

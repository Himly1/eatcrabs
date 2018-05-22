package com.ptteng;

import com.ptteng.domain.manager.Role;
import com.ptteng.utlis.jpa.CopyUtil;
import com.ptteng.utlis.jpa.MySpecification;
import com.ptteng.utlis.jpa.PageableUtil;
import com.ptteng.repository.*;
import com.ptteng.utlis.validator.ValidatorUtil;
import com.ptteng.vo.backstage.CustomersQuery;
import com.ptteng.vo.backstage.ModulesQuery;
import com.ptteng.vo.backstage.RolesQuery;
import com.ptteng.vo.backstage.StaffsQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ManagerApplication.class)
public class MyTest2 {
    @Autowired
    ModuleDAO moduleDAO;
    @Autowired
    RoleModuleDAO roleModuleDAO;
    @Autowired
    RoleDAO roleDAO;
    @Autowired
    RootDAO rootDAO;
    @Autowired
    StaffDAO staffDAO;
    @Autowired
    UserDAO userDAO;

    @Test
    public void test1(){
        ModulesQuery modulesQuery = new ModulesQuery();
        modulesQuery.setModuleName("一号");
        System.out.println(moduleDAO.findAll(new MySpecification<>(modulesQuery), PageableUtil.getPageRequest(modulesQuery)).getContent());
    }

    @Test
    public void test2(){
        CustomersQuery customersQuery = new CustomersQuery();
        customersQuery.setFreeze(-1);
        System.out.println(userDAO.findAll(new MySpecification<>(customersQuery),PageableUtil.getPageRequest(customersQuery)).getContent());
    }

    @Test
    public void test3(){
        RolesQuery rolesQuery = new RolesQuery();
        rolesQuery.setPage(1);
        System.out.println(roleDAO.findAll(new MySpecification<>(rolesQuery),PageableUtil.getPageRequest(rolesQuery)).getContent());
    }

    @Test
    public void test4(){
        StaffsQuery staffsQuery = new StaffsQuery();
        staffsQuery.setStaffName("12345");
        System.out.println(staffDAO.findAll(new MySpecification<>(staffsQuery),PageableUtil.getPageRequest(staffsQuery)).getContent());
    }

}

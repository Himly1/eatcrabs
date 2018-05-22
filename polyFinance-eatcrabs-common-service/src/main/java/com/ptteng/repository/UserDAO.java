package com.ptteng.repository;

import com.ptteng.domain.business.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserDAO extends JpaRepository<User,Long>, JpaSpecificationExecutor<User> {
    /**
     *
     * @param account 登录手机号
     * @return 数据库里面s_user映射的实体类User
     */
    User findByAccount(String account);
}

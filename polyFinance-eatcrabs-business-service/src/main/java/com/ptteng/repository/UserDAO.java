package com.ptteng.repository;

import com.ptteng.domain.business.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserDAO extends JpaRepository<User,Long>, JpaSpecificationExecutor<User> {
    User findByAccount(String account);
}

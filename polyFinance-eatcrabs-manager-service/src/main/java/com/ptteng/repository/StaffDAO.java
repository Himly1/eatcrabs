package com.ptteng.repository;

import com.ptteng.domain.manager.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StaffDAO extends JpaRepository<Staff, Long>, JpaSpecificationExecutor<Staff> {
    Staff findByAccount(String account);
}

package com.ptteng.repository;

import com.ptteng.domain.manager.RoleModule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface RoleModuleDAO extends JpaRepository<RoleModule, Long>, JpaSpecificationExecutor<RoleModule> {
    List<RoleModule> findByRoleId(Long roleId);
}

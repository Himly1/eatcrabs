package com.ptteng.repository;

import com.ptteng.domain.manager.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ModuleDAO extends JpaRepository<Module, Long>, JpaSpecificationExecutor<Module> {
}

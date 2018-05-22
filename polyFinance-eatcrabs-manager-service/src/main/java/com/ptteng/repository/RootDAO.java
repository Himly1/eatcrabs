package com.ptteng.repository;

import com.ptteng.domain.manager.Root;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RootDAO extends JpaRepository<Root, Long>, JpaSpecificationExecutor<Root> {
    Root findByAccount(String account);
}

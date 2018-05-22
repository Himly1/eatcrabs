package com.ptteng.repository;

import com.ptteng.domain.business.Depositor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DepositorDAO extends JpaRepository<Depositor,Long>, JpaSpecificationExecutor<Depositor> {
    Depositor findByUserId(Long userId);
}

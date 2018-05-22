package com.ptteng.repository;

import com.ptteng.domain.business.TempOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TempOrderDAO extends JpaRepository<TempOrder,Long> , JpaSpecificationExecutor<TempOrder> {
    TempOrder findByUserId(Long userId);
    void deleteByUserId(Long userId);
}

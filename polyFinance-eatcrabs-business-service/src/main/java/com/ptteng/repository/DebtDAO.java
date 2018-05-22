package com.ptteng.repository;

import com.ptteng.domain.business.Debt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface DebtDAO extends JpaRepository<Debt,Long>, JpaSpecificationExecutor<Debt> {
}

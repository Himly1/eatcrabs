package com.ptteng.repository;

import com.ptteng.domain.business.InvestDebt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface InvestDebtDAO extends JpaRepository<InvestDebt,Long>, JpaSpecificationExecutor<InvestDebt> {
}

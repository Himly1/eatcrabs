package com.ptteng.repository;

import com.ptteng.domain.business.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BankDAO extends JpaRepository<Bank,Long>, JpaSpecificationExecutor<Bank> {
}

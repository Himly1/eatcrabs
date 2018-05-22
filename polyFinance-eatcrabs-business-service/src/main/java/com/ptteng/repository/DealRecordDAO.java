package com.ptteng.repository;

import com.ptteng.domain.business.DealRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface DealRecordDAO extends JpaRepository<DealRecord,Long>, JpaSpecificationExecutor<DealRecord> {
}

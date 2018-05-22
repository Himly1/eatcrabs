package com.ptteng.repository;

import com.ptteng.domain.business.Opinion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface OpinionDAO extends JpaRepository<Opinion,Long> , JpaSpecificationExecutor<Opinion> {
}

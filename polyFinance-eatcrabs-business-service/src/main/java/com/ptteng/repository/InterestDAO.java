package com.ptteng.repository;

import com.ptteng.domain.business.Interest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface InterestDAO extends JpaRepository<Interest,Long>, JpaSpecificationExecutor<Interest> {
    List<Interest> findInterestsByUserId(Long id);
}

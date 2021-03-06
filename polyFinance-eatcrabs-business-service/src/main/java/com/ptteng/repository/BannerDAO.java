package com.ptteng.repository;

import com.ptteng.domain.business.Banner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface BannerDAO extends JpaRepository<Banner,Long>, JpaSpecificationExecutor<Banner> {
}

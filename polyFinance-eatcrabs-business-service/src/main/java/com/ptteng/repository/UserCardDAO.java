package com.ptteng.repository;

import com.ptteng.domain.business.UserCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserCardDAO extends JpaRepository<UserCard,Long>, JpaSpecificationExecutor<UserCard> {
}

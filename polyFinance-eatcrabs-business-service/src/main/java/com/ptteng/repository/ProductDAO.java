package com.ptteng.repository;

import com.ptteng.bo.ProductSelectBO;
import com.ptteng.domain.business.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductDAO extends JpaRepository<Product,Long>, JpaSpecificationExecutor<Product> {

}

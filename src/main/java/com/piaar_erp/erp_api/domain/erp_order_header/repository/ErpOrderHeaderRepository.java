package com.piaar_erp.erp_api.domain.erp_order_header.repository;

import com.piaar_erp.erp_api.domain.erp_order_header.entity.ErpOrderHeaderEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ErpOrderHeaderRepository extends JpaRepository<ErpOrderHeaderEntity, Integer> {

}

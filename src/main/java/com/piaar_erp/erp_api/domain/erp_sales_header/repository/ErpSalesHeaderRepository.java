package com.piaar_erp.erp_api.domain.erp_sales_header.repository;

import com.piaar_erp.erp_api.domain.erp_sales_header.entity.ErpSalesHeaderEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ErpSalesHeaderRepository extends JpaRepository<ErpSalesHeaderEntity, Integer> {

}

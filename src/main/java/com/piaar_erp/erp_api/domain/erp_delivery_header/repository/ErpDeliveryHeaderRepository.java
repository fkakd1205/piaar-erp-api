package com.piaar_erp.erp_api.domain.erp_delivery_header.repository;

import com.piaar_erp.erp_api.domain.erp_delivery_header.entity.ErpDeliveryHeaderEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ErpDeliveryHeaderRepository extends JpaRepository<ErpDeliveryHeaderEntity, Integer> {

}

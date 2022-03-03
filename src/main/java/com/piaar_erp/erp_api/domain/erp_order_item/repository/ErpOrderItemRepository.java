package com.piaar_erp.erp_api.domain.erp_order_item.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.piaar_erp.erp_api.domain.erp_order_item.entity.ErpOrderItemEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ErpOrderItemRepository extends JpaRepository<ErpOrderItemEntity, Integer>, ErpOrderItemRepositoryCustom {
    Optional<ErpOrderItemEntity> findById(UUID id);
}

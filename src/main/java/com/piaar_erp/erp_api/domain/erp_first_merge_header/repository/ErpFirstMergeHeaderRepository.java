package com.piaar_erp.erp_api.domain.erp_first_merge_header.repository;

import com.piaar_erp.erp_api.domain.erp_first_merge_header.entity.ErpFirstMergeHeaderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ErpFirstMergeHeaderRepository extends JpaRepository<ErpFirstMergeHeaderEntity, Integer> {
    Optional<ErpFirstMergeHeaderEntity> findById(UUID headerId);
}

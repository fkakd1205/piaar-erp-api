package com.piaar_erp.erp_api.domain.erp_second_merge_header.repository;

import com.piaar_erp.erp_api.domain.erp_second_merge_header.entity.ErpSecondMergeHeaderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ErpSecondMergeHeaderRepository extends JpaRepository<ErpSecondMergeHeaderEntity, Integer> {
    Optional<ErpSecondMergeHeaderEntity> findById(UUID headerId);
}

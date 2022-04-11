package com.piaar_erp.erp_api.domain.release_stock.repository;

import com.piaar_erp.erp_api.domain.release_stock.entity.ReleaseStockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReleaseStockRepository extends JpaRepository<ReleaseStockEntity, Integer>{
}

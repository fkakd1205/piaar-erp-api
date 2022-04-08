package com.piaar_erp.erp_api.domain.release_stock.repository;

import com.piaar_erp.erp_api.domain.release_stock.entity.ReleaseStockEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReleaseStockCustomJdbc {
    void jdbcBulkInsert(List<ReleaseStockEntity> entities);
}

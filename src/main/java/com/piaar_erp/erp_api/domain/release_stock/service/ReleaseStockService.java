package com.piaar_erp.erp_api.domain.release_stock.service;

import com.piaar_erp.erp_api.domain.release_stock.repository.ReleaseStockCustomJdbc;
import com.piaar_erp.erp_api.domain.release_stock.repository.ReleaseStockJdbcImpl;
import com.piaar_erp.erp_api.domain.release_stock.repository.ReleaseStockRepository;
import com.piaar_erp.erp_api.domain.release_stock.entity.ReleaseStockEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReleaseStockService {
    private final ReleaseStockRepository releaseStockRepository;
    private final ReleaseStockCustomJdbc releaseStockCustomJdbc;

    public void saveAndModifyList(List<ReleaseStockEntity> entities) {
        releaseStockRepository.saveAll(entities);
    }

    public void bulkInsert(List<ReleaseStockEntity> entities){
        releaseStockCustomJdbc.jdbcBulkInsert(entities);
    }

    public void deleteByErpOrderItemIds(List<UUID> erpOrderItemIds){
        releaseStockRepository.deleteByErpOrderItemIds(erpOrderItemIds);
    }
}

package com.piaar_erp.erp_api.domain.erp_order_item.repository;

import com.piaar_erp.erp_api.domain.erp_order_item.entity.ErpOrderItemEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ErpOrderItemCustomJdbc {
    void jdbcBulkInsert(List<ErpOrderItemEntity> entities);
}

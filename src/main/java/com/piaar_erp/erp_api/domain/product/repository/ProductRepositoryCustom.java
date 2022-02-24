package com.piaar_erp.erp_api.domain.product.repository;

import com.piaar_erp.erp_api.domain.product.entity.ProductEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ProductRepositoryCustom {
    List<ProductEntity> qSelectAll();
    // List<ProductEntity> qSelectById(Map<String, Object> params);
}

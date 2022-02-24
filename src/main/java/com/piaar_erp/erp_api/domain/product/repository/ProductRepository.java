package com.piaar_erp.erp_api.domain.product.repository;

import com.piaar_erp.erp_api.domain.product.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer>, ProductRepositoryCustom {
}

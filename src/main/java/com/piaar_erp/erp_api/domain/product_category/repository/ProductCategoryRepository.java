package com.piaar_erp.erp_api.domain.product_category.repository;

import com.piaar_erp.erp_api.domain.product_category.entity.ProductCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategoryEntity, Integer> {

}

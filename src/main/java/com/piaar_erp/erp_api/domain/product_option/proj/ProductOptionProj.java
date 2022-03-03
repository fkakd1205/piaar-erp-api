package com.piaar_erp.erp_api.domain.product_option.proj;

import com.piaar_erp.erp_api.domain.product.entity.ProductEntity;
import com.piaar_erp.erp_api.domain.product_category.entity.ProductCategoryEntity;
import com.piaar_erp.erp_api.domain.product_option.entity.ProductOptionEntity;

import lombok.Getter;

/**
 * 자기 자신과 Many To One 관계에 놓여있는 값들과 JOIN
 */
@Getter
public class ProductOptionProj {
    ProductOptionEntity productOption;
    ProductEntity product;
    ProductCategoryEntity productCategory;
}

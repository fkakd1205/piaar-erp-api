package com.piaar_erp.erp_api.domain.erp_order_item.proj;

import com.piaar_erp.erp_api.domain.erp_order_item.entity.ErpOrderItemEntity;
import com.piaar_erp.erp_api.domain.product.entity.ProductEntity;
import com.piaar_erp.erp_api.domain.product_category.entity.ProductCategoryEntity;
import com.piaar_erp.erp_api.domain.product_option.entity.ProductOptionEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ErpOrderItemProj {
    ErpOrderItemEntity erpOrderItem;
    ProductEntity product;
    ProductOptionEntity productOption;
    ProductCategoryEntity productCategory;
}

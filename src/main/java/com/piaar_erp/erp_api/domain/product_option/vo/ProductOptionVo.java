package com.piaar_erp.erp_api.domain.product_option.vo;

import com.piaar_erp.erp_api.domain.product.dto.ProductDto;
import com.piaar_erp.erp_api.domain.product_category.dto.ProductCategoryDto;
import com.piaar_erp.erp_api.domain.product_option.dto.ProductOptionDto;
import com.piaar_erp.erp_api.domain.product_option.proj.ProductOptionProj;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ProductOptionVo {
    ProductDto product;
    ProductCategoryDto category;
    ProductOptionDto option;

    public static ProductOptionVo toVo(ProductOptionProj proj){
        ProductOptionVo dto = ProductOptionVo.builder()
            .product(ProductDto.toDto(proj.getProduct()))
            .category(ProductCategoryDto.toDto(proj.getProductCategory()))
            .option(ProductOptionDto.toDto(proj.getProductOption()))
            .build();

        return dto;
    }
}

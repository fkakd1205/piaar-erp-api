package com.piaar_erp.erp_api.domain.product.service;

import com.piaar_erp.erp_api.domain.product.entity.ProductEntity;
import com.piaar_erp.erp_api.domain.product.vo.ProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductBusinessService {
    private final ProductService productService;

    @Autowired
    public ProductBusinessService(
            ProductService productService
    ) {
        this.productService = productService;
    }

    public List<ProductVo> searchAll() {
        List<ProductEntity> productEntities = productService.searchAll();
        List<ProductVo> productVos = productEntities.stream().map(r->{
            return ProductVo.toVo(r);
        }).collect(Collectors.toList());

        return productVos;
    }
}

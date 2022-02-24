package com.piaar_erp.erp_api.domain.product.service;

import com.piaar_erp.erp_api.domain.product.entity.ProductEntity;
import com.piaar_erp.erp_api.domain.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(
            ProductRepository productRepository
    ) {
        this.productRepository = productRepository;
    }

    public List<ProductEntity> searchAll() {
        return productRepository.qSelectAll();
    }

    // public ProductEntity searchOne(Map<String, Object> params){
    //     ProductEntity productEntity = productRepository.qSelectById(params).stream().findFirst().orElseThrow(()-> new NullPointerException("erer"));
    //     return productEntity;
    // }
}

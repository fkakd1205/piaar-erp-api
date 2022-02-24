package com.piaar_erp.erp_api.domain.product.controller;

import com.piaar_erp.erp_api.domain.message.dto.Message;
import com.piaar_erp.erp_api.domain.product.service.ProductBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/product")
public class ProductApi {
    private final ProductBusinessService productBusinessService;

    @Autowired
    public ProductApi(
            ProductBusinessService productBusinessService
    ) {
        this.productBusinessService = productBusinessService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> searchAll() {
        Message message = new Message();

        message.setStatus(HttpStatus.OK);
        message.setMessage("success");
        message.setData(productBusinessService.searchAll());

        return new ResponseEntity<>(message, message.getStatus());
    }
}

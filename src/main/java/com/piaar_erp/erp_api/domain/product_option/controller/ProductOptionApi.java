package com.piaar_erp.erp_api.domain.product_option.controller;

import com.piaar_erp.erp_api.domain.message.dto.Message;
import com.piaar_erp.erp_api.domain.product_option.service.ProductOptionBusinessService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/product-option")
public class ProductOptionApi {
    private ProductOptionBusinessService productOptionBusinessService;

    @Autowired
    public ProductOptionApi(
        ProductOptionBusinessService productOptionBusinessService
    ) {
        this.productOptionBusinessService = productOptionBusinessService;
    }

    /**
     * Search list api for productOption.
     * <p>
     * <b>GET : API URL => /api/v1/product-option/list</b>
     *
     * @return ResponseEntity(message, HttpStatus)
     * @see productOptionBusinessService#searchList
     */
    @GetMapping("/list")
    public ResponseEntity<?> searchList(){
        Message message = new Message();

        message.setData(productOptionBusinessService.searchList());
        message.setStatus(HttpStatus.OK);
        message.setMessage("success");
        
        return new ResponseEntity<>(message, message.getStatus());
    }

    /**
     * Search list api for productOption.
     * <p>
     * <b>GET : API URL => /api/v1/product-option/list-m2oj</b>
     * <p>
     * ProductOption 데이터를 조회한다.
     * 해당 Product와 연관관계에 놓여있는 Many To One JOIN(m2oj) 상태를 조회한다.
     *
     * @return ResponseEntity(message, HttpStatus)
     * @see Message
     * @see HttpStatus
     * @see UserService#isUserLogin
     * @see productOptionBusinessService#searchListM2OJ
     */
    @GetMapping("/list-m2oj")
    public ResponseEntity<?> searchListM2OJ() {
        Message message = new Message();

        message.setData(productOptionBusinessService.searchListM2OJ());
        message.setStatus(HttpStatus.OK);
        message.setMessage("success");

        return new ResponseEntity<>(message, message.getStatus());
    }
}

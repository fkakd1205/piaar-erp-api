package com.piaar_erp.erp_api.domain.erp_order_header.controller;

import com.piaar_erp.erp_api.domain.erp_order_header.dto.ErpOrderHeaderDto;
import com.piaar_erp.erp_api.domain.erp_order_header.service.ErpOrderHeaderBusinessService;
import com.piaar_erp.erp_api.domain.message.dto.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/erp-order-header")
public class ErpOrderHeaderApi {
    private ErpOrderHeaderBusinessService erpOrderHeaderBusinessService;

    @Autowired
    public ErpOrderHeaderApi(ErpOrderHeaderBusinessService erpOrderHeaderBusinessService) {
        this.erpOrderHeaderBusinessService = erpOrderHeaderBusinessService;
    }

    /**
     * Create one api for erp order header.
     * <p>
     * <b>POST : API URL => /api/v1/erp-order-header</b>
     * 
     * @param headerDto : ErpOrderHeaderDto
     * @return ResponseEntity(message, HttpStatus)
     * @see ErpOrderHeaderBusinessService#saveOne
     */
    @PostMapping("")
    public ResponseEntity<?> saveOne(@RequestBody ErpOrderHeaderDto headerDto) {
        Message message = new Message();

        erpOrderHeaderBusinessService.saveOne(headerDto);
        message.setStatus(HttpStatus.OK);
        message.setMessage("success");

        return new ResponseEntity<>(message, message.getStatus());
    }

    /**
     * Search one api for erp order header.
     * <p>
     * <b>GET : API URL => /api/v1/erp-order-header/list</b>
     *
     * @return ResponseEntity(message, HttpStatus)
     * @see ErpOrderHeaderBusinessService#searchOne
     */
    @GetMapping("/list")
    public ResponseEntity<?> searchOne() {
        Message message = new Message();

        message.setData(erpOrderHeaderBusinessService.searchOne());
        message.setStatus(HttpStatus.OK);
        message.setMessage("success");

        return new ResponseEntity<>(message, message.getStatus());
    }

    /**
     * Create one api for product.
     * <p>
     * <b>PUT : API URL => /api/v1/erp-order-header</b>
     * 
     * @param headerDto : ErpOrderHeaderDto
     * @return ResponseEntity(message, HttpStatus)
     * @see ErpOrderHeaderBusinessService#changeOne
     */
    @PutMapping("")
    public ResponseEntity<?> changeOne(@RequestBody ErpOrderHeaderDto headerDto) {
        Message message = new Message();

        erpOrderHeaderBusinessService.changeOne(headerDto);
        message.setStatus(HttpStatus.OK);
        message.setMessage("success");

        return new ResponseEntity<>(message, message.getStatus());
    }
}

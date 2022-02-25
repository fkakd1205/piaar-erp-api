package com.piaar_erp.erp_api.domain.erp_delivery_header.controller;

import com.piaar_erp.erp_api.domain.erp_delivery_header.dto.ErpDeliveryHeaderDto;
import com.piaar_erp.erp_api.domain.erp_delivery_header.service.ErpDeliveryHeaderBusinessService;
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
@RequestMapping("/api/v1/erp-delivery-header")
public class ErpDeliveryHeaderApi {
    private ErpDeliveryHeaderBusinessService erpDeliveryHeaderBusinessService;

    @Autowired
    public ErpDeliveryHeaderApi(ErpDeliveryHeaderBusinessService erpDeliveryHeaderBusinessService) {
        this.erpDeliveryHeaderBusinessService = erpDeliveryHeaderBusinessService;
    }

    /**
     * Create one api for erp delivery header.
     * <p>
     * <b>POST : API URL => /api/v1/erp-delivery-header</b>
     * 
     * @param headerDto : ErpDeliveryHeaderDto
     * @return ResponseEntity(message, HttpStatus)
     * @see ErpDeliveryHeaderBusinessService#saveOne
     */
    @PostMapping("")
    public ResponseEntity<?> saveOne(@RequestBody ErpDeliveryHeaderDto headerDto) {
        Message message = new Message();

        erpDeliveryHeaderBusinessService.saveOne(headerDto);
        message.setStatus(HttpStatus.OK);
        message.setMessage("success");

        return new ResponseEntity<>(message, message.getStatus());
    }

    /**
     * Search one api for erp delivery header.
     * <p>
     * <b>GET : API URL => /api/v1/erp-delivery-header</b>
     *
     * @return ResponseEntity(message, HttpStatus)
     * @see ErpDeliveryHeaderBusinessService#searchOne
     */
    @GetMapping("")
    public ResponseEntity<?> searchOne() {
        Message message = new Message();

        message.setData(erpDeliveryHeaderBusinessService.searchOne());
        message.setStatus(HttpStatus.OK);
        message.setMessage("success");

        return new ResponseEntity<>(message, message.getStatus());
    }

    /**
     * Create one api for product.
     * <p>
     * <b>PUT : API URL => /api/v1/erp-delivery-header</b>
     * 
     * @param headerDto : ErpDeliveryHeaderDto
     * @return ResponseEntity(message, HttpStatus)
     * @see ErpDeliveryHeaderBusinessService#updateOne
     */
    @PutMapping("")
    public ResponseEntity<?> updateOne(@RequestBody ErpDeliveryHeaderDto headerDto) {
        Message message = new Message();

        erpDeliveryHeaderBusinessService.updateOne(headerDto);
        message.setStatus(HttpStatus.OK);
        message.setMessage("success");

        return new ResponseEntity<>(message, message.getStatus());
    }
}

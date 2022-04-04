package com.piaar_erp.erp_api.domain.erp_sales_header.controller;

import com.piaar_erp.erp_api.domain.erp_sales_header.dto.ErpSalesHeaderDto;
import com.piaar_erp.erp_api.domain.erp_sales_header.service.ErpSalesHeaderBusinessService;
import com.piaar_erp.erp_api.domain.message.dto.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ws/v1/erp-sales-headers")
public class ErpSalesHeaderSocket {
    private final ErpSalesHeaderBusinessService erpSalesHeaderBusinessService;
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public ErpSalesHeaderSocket(
            ErpSalesHeaderBusinessService erpSalesHeaderBusinessService,
            SimpMessagingTemplate messagingTemplate
    ) {
        this.erpSalesHeaderBusinessService = erpSalesHeaderBusinessService;
        this.messagingTemplate = messagingTemplate;
    }

    /**
     * Create one api for erp sales header.
     * <p>
     * <b>POST : API URL => /ws/v1/erp-sales-headers</b>
     *
     * @param headerDto : ErpSalesHeaderDto
     * @return ResponseEntity(message, HttpStatus)
     * @see ErpSalesHeaderBusinessService#saveOne
     */
    @PostMapping("")
    public void saveOne(@RequestBody ErpSalesHeaderDto headerDto) {
        Message message = new Message();

        erpSalesHeaderBusinessService.saveOne(headerDto);
        message.setStatus(HttpStatus.OK);
        message.setMessage("success");

        messagingTemplate.convertAndSend("/topic/erp.erp-sales-header", message);
    }

    /**
     * Create one api for product.
     * <p>
     * <b>PUT : API URL => /ws/v1/erp-sales-headers</b>
     *
     * @param headerDto : ErpSalesHeaderDto
     * @return ResponseEntity(message, HttpStatus)
     * @see ErpSalesHeaderBusinessService#updateOne
     */
    @PutMapping("")
    public void updateOne(@RequestBody ErpSalesHeaderDto headerDto) {
        Message message = new Message();

        erpSalesHeaderBusinessService.updateOne(headerDto);
        message.setStatus(HttpStatus.OK);
        message.setMessage("success");

        messagingTemplate.convertAndSend("/topic/erp.erp-sales-header", message);
    }
}

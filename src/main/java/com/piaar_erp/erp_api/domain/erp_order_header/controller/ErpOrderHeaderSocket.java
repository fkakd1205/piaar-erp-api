package com.piaar_erp.erp_api.domain.erp_order_header.controller;

import com.piaar_erp.erp_api.domain.erp_order_header.dto.ErpOrderHeaderDto;
import com.piaar_erp.erp_api.domain.erp_order_header.service.ErpOrderHeaderBusinessService;
import com.piaar_erp.erp_api.domain.message.dto.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ws/v1/erp-order-headers")
public class ErpOrderHeaderSocket {
    //    TODO : 소켓통신 보완해야됨.
    private ErpOrderHeaderBusinessService erpOrderHeaderBusinessService;
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public ErpOrderHeaderSocket(
            ErpOrderHeaderBusinessService erpOrderHeaderBusinessService,
            SimpMessagingTemplate messagingTemplate
    ) {
        this.erpOrderHeaderBusinessService = erpOrderHeaderBusinessService;
        this.messagingTemplate = messagingTemplate;
    }

    @PostMapping("")
    public void saveOne(@RequestBody ErpOrderHeaderDto headerDto) {
        Message message = new Message();

        erpOrderHeaderBusinessService.saveOne(headerDto);
        message.setStatus(HttpStatus.OK);
        message.setMessage("success");

        messagingTemplate.convertAndSend("/topic/erp.erp-order-header",message);
    }

    @PutMapping("")
    public void updateOne(@RequestBody ErpOrderHeaderDto headerDto) {
        Message message = new Message();

        erpOrderHeaderBusinessService.updateOne(headerDto);
        message.setStatus(HttpStatus.OK);
        message.setMessage("success");

        messagingTemplate.convertAndSend("/topic/erp.erp-order-header",message);
    }
}

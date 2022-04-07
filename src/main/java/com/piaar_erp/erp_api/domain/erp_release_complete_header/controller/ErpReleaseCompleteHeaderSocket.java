package com.piaar_erp.erp_api.domain.erp_release_complete_header.controller;

import com.piaar_erp.erp_api.domain.erp_release_complete_header.dto.ErpReleaseCompleteHeaderDto;
import com.piaar_erp.erp_api.domain.erp_release_complete_header.service.ErpReleaseCompleteHeaderBusinessService;
import com.piaar_erp.erp_api.domain.message.dto.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ws/v1/erp-release-complete-headers")
public class ErpReleaseCompleteHeaderSocket {
    private final ErpReleaseCompleteHeaderBusinessService erpReleaseCompleteHeaderBusinessService;
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public ErpReleaseCompleteHeaderSocket(
            ErpReleaseCompleteHeaderBusinessService erpReleaseCompleteHeaderBusinessService,
            SimpMessagingTemplate messagingTemplate
    ) {
        this.erpReleaseCompleteHeaderBusinessService = erpReleaseCompleteHeaderBusinessService;
        this.messagingTemplate = messagingTemplate;
    }

    /**
     * Create one api for erp release complete header.
     * <p>
     * <b>POST : API URL => /ws/v1/erp-release-complete-headers</b>
     *
     * @param headerDto : ErpReleaseCompleteHeaderDto
     * @return ResponseEntity(message, HttpStatus)
     * @see ErpReleaseCompleteHeaderBusinessService#saveOne
     */
    @PostMapping("")
    public void saveOne(@RequestBody ErpReleaseCompleteHeaderDto headerDto) {
        Message message = new Message();

        erpReleaseCompleteHeaderBusinessService.saveOne(headerDto);
        message.setStatus(HttpStatus.OK);
        message.setMessage("success");

//        return new ResponseEntity<>(message, message.getStatus());
        messagingTemplate.convertAndSend("/topic/erp.erp-release-complete-header", message);
    }

    /**
     * Create one api for product.
     * <p>
     * <b>PUT : API URL => /ws/v1/erp-release-complete-headers</b>
     *
     * @param headerDto : ErpReleaseCompleteHeaderDto
     * @return ResponseEntity(message, HttpStatus)
     * @see ErpReleaseCompleteHeaderBusinessService#updateOne
     */
    @PutMapping("")
    public void updateOne(@RequestBody ErpReleaseCompleteHeaderDto headerDto) {
        Message message = new Message();

        erpReleaseCompleteHeaderBusinessService.updateOne(headerDto);
        message.setStatus(HttpStatus.OK);
        message.setMessage("success");

//        return new ResponseEntity<>(message, message.getStatus());
        messagingTemplate.convertAndSend("/topic/erp.erp-release-complete-header", message);
    }
}

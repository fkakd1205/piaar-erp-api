package com.piaar_erp.erp_api.domain.erp_release_ready_header.controller;

import com.piaar_erp.erp_api.domain.erp_release_ready_header.dto.ErpReleaseReadyHeaderDto;
import com.piaar_erp.erp_api.domain.erp_release_ready_header.service.ErpReleaseReadyHeaderBusinessService;
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
@RequestMapping("/api/v1/erp-release-ready-headers")
public class ErpReleaseReadyHeaderApi {
    private ErpReleaseReadyHeaderBusinessService erpReleaseReadyHeaderBusinessService;

    @Autowired
    public ErpReleaseReadyHeaderApi(ErpReleaseReadyHeaderBusinessService erpReleaseReadyHeaderBusinessService) {
        this.erpReleaseReadyHeaderBusinessService = erpReleaseReadyHeaderBusinessService;
    }

    /**
     * Create one api for erp release ready header.
     * <p>
     * <b>POST : API URL => /api/v1/erp-release-ready-headers</b>
     * 
     * @param headerDto : ErpReleaseReadyHeaderDto
     * @return ResponseEntity(message, HttpStatus)
     * @see ErpReleaseReadyHeaderBusinessService#saveOne
     */
    @PostMapping("")
    public ResponseEntity<?> saveOne(@RequestBody ErpReleaseReadyHeaderDto headerDto) {
        Message message = new Message();

        erpReleaseReadyHeaderBusinessService.saveOne(headerDto);
        message.setStatus(HttpStatus.OK);
        message.setMessage("success");

        return new ResponseEntity<>(message, message.getStatus());
    }

    /**
     * Search one api for erp release ready header.
     * <p>
     * <b>GET : API URL => /api/v1/erp-release-ready-headers</b>
     *
     * @return ResponseEntity(message, HttpStatus)
     * @see ErpReleaseReadyHeaderBusinessService#searchOne
     */
    @GetMapping("")
    public ResponseEntity<?> searchOne() {
        Message message = new Message();

        message.setData(erpReleaseReadyHeaderBusinessService.searchOne());
        message.setStatus(HttpStatus.OK);
        message.setMessage("success");

        return new ResponseEntity<>(message, message.getStatus());
    }

    /**
     * Create one api for product.
     * <p>
     * <b>PUT : API URL => /api/v1/erp-release-ready-headers</b>
     * 
     * @param headerDto : ErpReleaseReadyHeaderDto
     * @return ResponseEntity(message, HttpStatus)
     * @see ErpReleaseReadyHeaderBusinessService#updateOne
     */
    @PutMapping("")
    public ResponseEntity<?> updateOne(@RequestBody ErpReleaseReadyHeaderDto headerDto) {
        Message message = new Message();

        erpReleaseReadyHeaderBusinessService.updateOne(headerDto);
        message.setStatus(HttpStatus.OK);
        message.setMessage("success");

        return new ResponseEntity<>(message, message.getStatus());
    }
}

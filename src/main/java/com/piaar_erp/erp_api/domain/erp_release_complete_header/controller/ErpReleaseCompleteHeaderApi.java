package com.piaar_erp.erp_api.domain.erp_release_complete_header.controller;

import com.piaar_erp.erp_api.domain.erp_release_complete_header.dto.ErpReleaseCompleteHeaderDto;
import com.piaar_erp.erp_api.domain.erp_release_complete_header.service.ErpReleaseCompleteHeaderBusinessService;
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
@RequestMapping("/api/v1/erp-release-complete-headers")
public class ErpReleaseCompleteHeaderApi {
    private ErpReleaseCompleteHeaderBusinessService erpReleaseCompleteHeaderBusinessService;

    @Autowired
    public ErpReleaseCompleteHeaderApi(ErpReleaseCompleteHeaderBusinessService erpReleaseCompleteHeaderBusinessService) {
        this.erpReleaseCompleteHeaderBusinessService = erpReleaseCompleteHeaderBusinessService;
    }

    /**
     * Create one api for erp release complete header.
     * <p>
     * <b>POST : API URL => /api/v1/erp-release-complete-headers</b>
     * 
     * @param headerDto : ErpReleaseCompleteHeaderDto
     * @return ResponseEntity(message, HttpStatus)
     * @see ErpReleaseCompleteHeaderBusinessService#saveOne
     */
    @PostMapping("")
    public ResponseEntity<?> saveOne(@RequestBody ErpReleaseCompleteHeaderDto headerDto) {
        Message message = new Message();

        erpReleaseCompleteHeaderBusinessService.saveOne(headerDto);
        message.setStatus(HttpStatus.OK);
        message.setMessage("success");

        return new ResponseEntity<>(message, message.getStatus());
    }

    /**
     * Search one api for erp release complete header.
     * <p>
     * <b>GET : API URL => /api/v1/erp-release-complete-headers</b>
     *
     * @return ResponseEntity(message, HttpStatus)
     * @see ErpReleaseCompleteHeaderBusinessService#searchOne
     */
    @GetMapping("")
    public ResponseEntity<?> searchOne() {
        Message message = new Message();

        message.setData(erpReleaseCompleteHeaderBusinessService.searchOne());
        message.setStatus(HttpStatus.OK);
        message.setMessage("success");

        return new ResponseEntity<>(message, message.getStatus());
    }

    /**
     * Create one api for product.
     * <p>
     * <b>PUT : API URL => /api/v1/erp-release-complete-headers</b>
     * 
     * @param headerDto : ErpReleaseCompleteHeaderDto
     * @return ResponseEntity(message, HttpStatus)
     * @see ErpReleaseCompleteHeaderBusinessService#updateOne
     */
    @PutMapping("")
    public ResponseEntity<?> updateOne(@RequestBody ErpReleaseCompleteHeaderDto headerDto) {
        Message message = new Message();

        erpReleaseCompleteHeaderBusinessService.updateOne(headerDto);
        message.setStatus(HttpStatus.OK);
        message.setMessage("success");

        return new ResponseEntity<>(message, message.getStatus());
    }
}

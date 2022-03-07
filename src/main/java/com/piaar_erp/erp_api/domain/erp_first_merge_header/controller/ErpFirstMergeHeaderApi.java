package com.piaar_erp.erp_api.domain.erp_first_merge_header.controller;

import com.piaar_erp.erp_api.domain.erp_first_merge_header.dto.ErpFirstMergeHeaderDto;
import com.piaar_erp.erp_api.domain.erp_first_merge_header.service.ErpFirstMergeHeaderBusinessService;
import com.piaar_erp.erp_api.domain.message.dto.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/erp-first-merge-headers")
public class ErpFirstMergeHeaderApi {
    private ErpFirstMergeHeaderBusinessService erpFirstMergeHeaderBusinessService;

    @Autowired
    public ErpFirstMergeHeaderApi(ErpFirstMergeHeaderBusinessService erpFirstMergeHeaderBusinessService) {
        this.erpFirstMergeHeaderBusinessService = erpFirstMergeHeaderBusinessService;
    }

    /**
     * Create one api for erp first merge header.
     * <p>
     * <b>POST : API URL => /api/v1/erp-first-merge-headers</b>
     *
     * @param headerDto : ErpFirstMergeHeaderDto
     * @return ResponseEntity(message, HttpStatus)
     * @see ErpFirstMergeHeaderBusinessService#saveOne
     */
    @PostMapping("")
    public ResponseEntity<?> saveOne(@RequestBody ErpFirstMergeHeaderDto headerDto) {
        Message message = new Message();

        erpFirstMergeHeaderBusinessService.saveOne(headerDto);
        message.setStatus(HttpStatus.OK);
        message.setMessage("success");

        return new ResponseEntity<>(message, message.getStatus());
    }

    /**
     * Search list api for erp first merge header.
     * <p>
     * <b>GET : API URL => /api/v1/erp-first-merge-headers</b>
     *
     * @return ResponseEntity(message, HttpStatus)
     * @see ErpFirstMergeHeaderBusinessService#searchAll
     */
    @GetMapping("")
    public ResponseEntity<?> searchAll() {
        Message message = new Message();

        message.setData(erpFirstMergeHeaderBusinessService.searchAll());
        message.setStatus(HttpStatus.OK);
        message.setMessage("success");

        return new ResponseEntity<>(message, message.getStatus());
    }

    /**
     * Update one api for erp first merge header.
     * <p>
     * <b>PUT : API URL => /api/v1/erp-first-merge-headers</b>
     *
     * @param headerDto : ErpFirstMergeHeaderDto
     * @return ResponseEntity(message, HttpStatus)
     * @see ErpFirstMergeHeaderBusinessService#updateOne
     */
    @PutMapping("")
    public ResponseEntity<?> updateOne(@RequestBody ErpFirstMergeHeaderDto headerDto) {
        Message message = new Message();

        erpFirstMergeHeaderBusinessService.updateOne(headerDto);
        message.setStatus(HttpStatus.OK);
        message.setMessage("success");

        return new ResponseEntity<>(message, message.getStatus());
    }
}

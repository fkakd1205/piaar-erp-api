package com.piaar_erp.erp_api.domain.erp_second_merge_header.controller;

import com.piaar_erp.erp_api.domain.erp_second_merge_header.dto.ErpSecondMergeHeaderDto;
import com.piaar_erp.erp_api.domain.erp_second_merge_header.service.ErpSecondMergeHeaderBusinessService;
import com.piaar_erp.erp_api.domain.message.dto.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/erp-second-merge-headers")
public class ErpSecondMergeHeaderApi {
    private ErpSecondMergeHeaderBusinessService erpSecondMergeHeaderBusinessService;

    @Autowired
    public ErpSecondMergeHeaderApi(ErpSecondMergeHeaderBusinessService erpSecondMergeHeaderBusinessService) {
        this.erpSecondMergeHeaderBusinessService = erpSecondMergeHeaderBusinessService;
    }

    /**
     * Create one api for erp second merge header.
     * <p>
     * <b>POST : API URL => /api/v1/erp-second-merge-headers</b>
     *
     * @param headerDto : ErpSecondMergeHeaderDto
     * @return ResponseEntity(message, HttpStatus)
     * @see ErpSecondMergeHeaderBusinessService#saveOne
     */
    @PostMapping("")
    public ResponseEntity<?> saveOne(@RequestBody ErpSecondMergeHeaderDto headerDto) {
        Message message = new Message();

        erpSecondMergeHeaderBusinessService.saveOne(headerDto);
        message.setStatus(HttpStatus.OK);
        message.setMessage("success");

        return new ResponseEntity<>(message, message.getStatus());
    }

    /**
     * Search list api for erp second merge header.
     * <p>
     * <b>GET : API URL => /api/v1/erp-second-merge-headers</b>
     *
     * @return ResponseEntity(message, HttpStatus)
     * @see ErpSecondMergeHeaderBusinessService#searchAll
     */
    @GetMapping("")
    public ResponseEntity<?> searchAll() {
        Message message = new Message();

        message.setData(erpSecondMergeHeaderBusinessService.searchAll());
        message.setStatus(HttpStatus.OK);
        message.setMessage("success");

        return new ResponseEntity<>(message, message.getStatus());
    }

    /**
     * Update one api for erp second merge header.
     * <p>
     * <b>PUT : API URL => /api/v1/erp-second-merge-headers</b>
     *
     * @param headerDto : ErpSecondMergeHeaderDto
     * @return ResponseEntity(message, HttpStatus)
     * @see ErpSecondMergeHeaderBusinessService#updateOne
     */
    @PutMapping("")
    public ResponseEntity<?> updateOne(@RequestBody ErpSecondMergeHeaderDto headerDto) {
        Message message = new Message();

        erpSecondMergeHeaderBusinessService.updateOne(headerDto);
        message.setStatus(HttpStatus.OK);
        message.setMessage("success");

        return new ResponseEntity<>(message, message.getStatus());
    }

    /**
     * Update one api for erp second merge header.
     * <p>
     * <b>PUT : API URL => /api/v1/erp-second-merge-headers/{id}</b>
     *
     * @param id : UUID
     * @return ResponseEntity(message, HttpStatus)
     * @see ErpSecondMergeHeaderBusinessService#deleteOne
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOne(@PathVariable(value = "id") UUID id) {
        Message message = new Message();

        erpSecondMergeHeaderBusinessService.deleteOne(id);
        message.setStatus(HttpStatus.OK);
        message.setMessage("success");

        return new ResponseEntity<>(message, message.getStatus());
    }
}

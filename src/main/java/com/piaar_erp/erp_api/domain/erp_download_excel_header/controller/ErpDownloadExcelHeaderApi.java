package com.piaar_erp.erp_api.domain.erp_download_excel_header.controller;

import com.piaar_erp.erp_api.domain.erp_download_excel_header.dto.ErpDownloadExcelHeaderDto;
import com.piaar_erp.erp_api.domain.erp_download_excel_header.service.ErpDownloadExcelHeaderBusinessService;
import com.piaar_erp.erp_api.domain.erp_order_item.dto.ErpDownloadOrderItemDto;
import com.piaar_erp.erp_api.domain.message.dto.Message;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/erp-download-excel-headers")
public class ErpDownloadExcelHeaderApi {
    private ErpDownloadExcelHeaderBusinessService erpDownloadExcelHeaderBusinessService;

    @Autowired
    public ErpDownloadExcelHeaderApi(ErpDownloadExcelHeaderBusinessService erpDownloadExcelHeaderBusinessService) {
        this.erpDownloadExcelHeaderBusinessService = erpDownloadExcelHeaderBusinessService;
    }

    /**
     * Create one api for erp download excel header.
     * <p>
     * <b>POST : API URL => /api/v1/erp-download-excel-headers</b>
     *
     * @param headerDto : ErpDownloadExcelHeaderDto
     * @return ResponseEntity(message, HttpStatus)
     * @see ErpDownloadExcelHeaderBusinessService#saveOne
     */
    @PostMapping("")
    public ResponseEntity<?> saveOne(@RequestBody ErpDownloadExcelHeaderDto headerDto) {
        Message message = new Message();

        erpDownloadExcelHeaderBusinessService.saveOne(headerDto);
        message.setStatus(HttpStatus.OK);
        message.setMessage("success");

        return new ResponseEntity<>(message, message.getStatus());
    }

    /**
     * Search list api for erp download excel header.
     * <p>
     * <b>GET : API URL => /api/v1/erp-download-excel-headers</b>
     *
     * @return ResponseEntity(message, HttpStatus)
     * @see ErpDownloadExcelHeaderBusinessService#searchAll
     */
    @GetMapping("")
    public ResponseEntity<?> searchAll() {
        Message message = new Message();

        message.setData(erpDownloadExcelHeaderBusinessService.searchAll());
        message.setStatus(HttpStatus.OK);
        message.setMessage("success");

        return new ResponseEntity<>(message, message.getStatus());
    }

    /**
     * Update one api for erp download excel header.
     * <p>
     * <b>PUT : API URL => /api/v1/erp-download-excel-headers</b>
     *
     * @param headerDto : ErpDownloadExcelHeaderDto
     * @return ResponseEntity(message, HttpStatus)
     * @see ErpDownloadExcelHeaderBusinessService#updateOne
     */
    @PutMapping("")
    public ResponseEntity<?> updateOne(@RequestBody ErpDownloadExcelHeaderDto headerDto) {
        Message message = new Message();

        erpDownloadExcelHeaderBusinessService.updateOne(headerDto);
        message.setStatus(HttpStatus.OK);
        message.setMessage("success");

        return new ResponseEntity<>(message, message.getStatus());
    }

    /**
     * Update one api for erp download excel header.
     * <p>
     * <b>PUT : API URL => /api/v1/erp-download-excel-headers/{id}</b>
     *
     * @param id : UUID
     * @return ResponseEntity(message, HttpStatus)
     * @see ErpDownloadExcelHeaderBusinessService#deleteOne
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOne(@PathVariable(value = "id") UUID id) {
        Message message = new Message();

        erpDownloadExcelHeaderBusinessService.deleteOne(id);
        message.setStatus(HttpStatus.OK);
        message.setMessage("success");

        return new ResponseEntity<>(message, message.getStatus());
    }

//    TODO : 엑셀 다운로드 로직 구현.
    @PostMapping("/{id}/download-order-items/action-download")
    public ResponseEntity<?> downloadForDownloadOrderItems(@PathVariable(value = "id") UUID id, @RequestBody List<ErpDownloadOrderItemDto> erpDownloadOrderItemDtos) {
        Message message = new Message();

        System.out.println(id);
        System.out.println(erpDownloadOrderItemDtos);
        message.setStatus(HttpStatus.OK);
        message.setMessage("success");

        return new ResponseEntity<>(message, message.getStatus());
    }
}

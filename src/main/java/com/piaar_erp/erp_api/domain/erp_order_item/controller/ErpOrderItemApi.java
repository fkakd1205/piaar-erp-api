package com.piaar_erp.erp_api.domain.erp_order_item.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import com.piaar_erp.erp_api.domain.erp_order_item.dto.ErpOrderItemDto;
import com.piaar_erp.erp_api.domain.erp_order_item.service.ErpOrderItemBusinessService;
import com.piaar_erp.erp_api.domain.message.dto.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Validated
@RestController
@RequestMapping("/api/v1/erp-order-items")
public class ErpOrderItemApi {
    private ErpOrderItemBusinessService erpOrderItemBusinessService;

    @Autowired
    public ErpOrderItemApi(ErpOrderItemBusinessService erpOrderItemBusinessService) {
        this.erpOrderItemBusinessService = erpOrderItemBusinessService;
    }

    /**
     * Upload excel data for order excel.
     * <p>
     * <b>POST : API URL => /api/v1/erp-order-items/excel/upload</b>
     * 
     * @param file : MultipartFile
     * @return ResponseEntity(message, HttpStatus)
     * @see ErpOrderItemBusinessService#isExcelFile
     * @see ErpOrderItemBusinessService#uploadErpOrderExcel
     */
    @PostMapping("/excel/upload")
    public ResponseEntity<?> uploadErpOrderExcel(@RequestParam("file") MultipartFile file) {
        Message message = new Message();

        // file extension check.
        erpOrderItemBusinessService.isExcelFile(file);

        message.setData(erpOrderItemBusinessService.uploadErpOrderExcel(file));
        message.setStatus(HttpStatus.OK);
        message.setMessage("success");

        return new ResponseEntity<>(message, message.getStatus());
    }

    /**
     * Store excel data for order excel.
     * <p>
     * <b>POST : API URL => /api/v1/erp-order-items/batch</b>
     * 
     * @param itemDtos : List::ErpOrderItemDto::
     * @return ResponseEntity(message, HttpStatus)
     * @see ErpOrderItemBusinessService#createBatch
     */
    @PostMapping("/batch")
    public ResponseEntity<?> createBatch(@RequestBody @Valid List<ErpOrderItemDto> itemDtos) {
        Message message = new Message();

        erpOrderItemBusinessService.createBatch(itemDtos);
        message.setStatus(HttpStatus.OK);
        message.setMessage("success");

        return new ResponseEntity<>(message, message.getStatus());
    }

    /**
     * Search erp order item.
     * <p>
     * <b>GET : API URL => /api/v1/erp-order-items/products/product-categories</b>
     * 
     * @param params : Map::String, Object::
     * @return ResponseEntity(message, HttpStatus)
     * @see ErpOrderItemBusinessService#searchBatch
     */
    @GetMapping("")
    public ResponseEntity<?> searchBatch(@RequestParam Map<String, Object> params) {
        Message message = new Message();

        message.setData(erpOrderItemBusinessService.searchBatch(params));
        message.setStatus(HttpStatus.OK);
        message.setMessage("success");

        return new ResponseEntity<>(message, message.getStatus());
    }

    /**
     * Change salesYn of erp order item.
     * <p>
     * <b>PATCH : API URL => /api/v1/erp-order-items/batch/sales-yn</b>
     * 
     * @param itemDtos : List::ErpOrderItemDto::
     * @return ResponseEntity(message, HttpStatus)
     * @see ErpOrderItemBusinessService#changeBatchForSalesYn
     */
    @PatchMapping("/batch/sales-yn")
    public ResponseEntity<?> changeBatchForSalesYn(@RequestBody List<ErpOrderItemDto> itemDtos) {
        Message message = new Message();

        erpOrderItemBusinessService.changeBatchForSalesYn(itemDtos);
        message.setStatus(HttpStatus.OK);
        message.setMessage("success");

        return new ResponseEntity<>(message, message.getStatus());
    }

    /**
     * Change releaseYn of erp order item.
     * <p>
     * <b>PATCH : API URL => /api/v1/erp-order-items/batch/release-yn</b>
     * 
     * @param itemDtos : List::ErpOrderItemDto::
     * @return ResponseEntity(message, HttpStatus)
     * @see ErpOrderItemBusinessService#changeBatchForReleaseYn
     */
    @PatchMapping("/batch/release-yn")
    public ResponseEntity<?> changeBatchForReleaseYn(@RequestBody List<ErpOrderItemDto> itemDtos) {
        Message message = new Message();
 
        erpOrderItemBusinessService.changeBatchForReleaseYn(itemDtos);
        message.setStatus(HttpStatus.OK);
        message.setMessage("success");

        return new ResponseEntity<>(message, message.getStatus());
    }

    /**
     * Get combined delivery item of erp order item.
     * <p>
     * <b>POST : API URL => /api/v1/erp-order-items/action-combined</b>
     * 
     * @param itemDtos : List::ErpOrderItemDto::
     * @return ResponseEntity(message, HttpStatus)
     * @see ErpOrderItemBusinessService#getCombinedDelivery
     */
//    @PostMapping("/action-combined")
//    public ResponseEntity<?> getCombinedDelivery(@RequestBody List<ErpOrderItemDto> itemDtos) {
//        Message message = new Message();
//
//        message.setData(erpOrderItemBusinessService.getCombinedDelivery(itemDtos));
//        message.setStatus(HttpStatus.OK);
//        message.setMessage("success");
//
//        return new ResponseEntity<>(message, message.getStatus());
//    }

    /**
     * Get merged combined delivery item of erp order item.
     * <p>
     * <b>POST : API URL => /api/v1/erp-order-items/action-combined/action-merge</b>
     * 
     * @param itemDtos : List::ErpOrderItemDto::
     * @return ResponseEntity(message, HttpStatus)
     * @see ErpOrderItemBusinessService#getMergeCombinedDelivery
     */
//    @PostMapping("/action-combined/action-merge")
//    public ResponseEntity<?> getMergeCombinedDelivery(@RequestBody List<ErpOrderItemDto> itemDtos) {
//        Message message = new Message();
//
//        message.setData(erpOrderItemBusinessService.getMergeCombinedDelivery(itemDtos));
//        message.setStatus(HttpStatus.OK);
//        message.setMessage("success");
//
//        return new ResponseEntity<>(message, message.getStatus());
//    }

    /**
     * Delete erp order item.
     * <p>
     * <b>POST : API URL => /api/v1/erp-order-items/batch-delete</b>
     * 
     * @param itemDtos : List::ErpOrderItemDto::
     * @return ResponseEntity(message, HttpStatus)
     * @see ErpOrderItemBusinessService#deleteBatch
     */
    @PostMapping("/batch-delete")
    public ResponseEntity<?> deleteBatch(@RequestBody List<ErpOrderItemDto> itemDtos) {
        Message message = new Message();

        erpOrderItemBusinessService.deleteBatch(itemDtos);
        message.setStatus(HttpStatus.OK);
        message.setMessage("success");

        return new ResponseEntity<>(message, message.getStatus());
    }

    /**
     * Change option code and release option code of erp order item.
     * <p>
     * <b>Pathch : API URL => /api/v1/erp-order-items/batch/option-code/all</b>
     * 
     * @param itemDtos : List::ErpOrderItemDto::
     * @return ResponseEntity(message, HttpStatus)
     * @see ErpOrderItemBusinessService#changeBatchForAllOptionCode
     */
    @PatchMapping("/batch/option-code/all")
    public ResponseEntity<?> changeBatchForAllOptionCode(@RequestBody List<ErpOrderItemDto> itemDtos) {
        Message message = new Message();

        erpOrderItemBusinessService.changeBatchForAllOptionCode(itemDtos);
        message.setStatus(HttpStatus.OK);
        message.setMessage("success");

        return new ResponseEntity<>(message, message.getStatus());
    }

    /**
     * Change release option code of erp order item.
     * <p>
     * <b>Patch : API URL => /api/v1/erp-order-items/batch/option-code/release</b>
     * 
     * @param itemDtos : List::ErpOrderItemDto::
     * @return ResponseEntity(message, HttpStatus)
     * @see ErpOrderItemBusinessService#changeBatchForReleaseOptionCode
     */
    @PatchMapping("/batch/option-code/release")
    public ResponseEntity<?> changeBatchForReleaseOptionCode(@RequestBody List<ErpOrderItemDto> itemDtos) {
        Message message = new Message();

        erpOrderItemBusinessService.changeBatchForReleaseOptionCode(itemDtos);
        message.setStatus(HttpStatus.OK);
        message.setMessage("success");

        return new ResponseEntity<>(message, message.getStatus());
    }

    @PostMapping("/erp-first-merge-headers/{firstMergeHeaderId}/action-merge")
    public ResponseEntity<?> getFirstMergeItem(@PathVariable(value = "firstMergeHeaderId") UUID firstMergeHeaderId, @RequestBody List<ErpOrderItemDto> itemDtos) {
        Message message = new Message();

        message.setData(erpOrderItemBusinessService.getFirstMergeItem(firstMergeHeaderId, itemDtos));
        message.setStatus(HttpStatus.OK);
        message.setMessage("success");

        return new ResponseEntity<>(message, message.getStatus());
    }
}

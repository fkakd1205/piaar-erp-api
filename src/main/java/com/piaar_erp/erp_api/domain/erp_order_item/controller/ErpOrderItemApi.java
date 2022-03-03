package com.piaar_erp.erp_api.domain.erp_order_item.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.piaar_erp.erp_api.domain.erp_order_item.dto.ErpOrderItemDto;
import com.piaar_erp.erp_api.domain.erp_order_item.service.ErpOrderItemBusinessService;
import com.piaar_erp.erp_api.domain.message.dto.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Validated
@RestController
@RequestMapping("/api/v1/erp-order-item")
public class ErpOrderItemApi {
    private ErpOrderItemBusinessService erpOrderItemBusinessService;

    @Autowired
    public ErpOrderItemApi(ErpOrderItemBusinessService erpOrderItemBusinessService) {
        this.erpOrderItemBusinessService = erpOrderItemBusinessService;
    }

    /**
     * Upload excel data for order excel.
     * <p>
     * <b>POST : API URL => /api/v1/erp-order-item/excel/upload</b>
     * 
     * @param file : MultipartFile
     * @return ResponseEntity(message, HttpStatus)
     * @throws NullPointerException
     * @throws IllegalStateException
     * @throws IllegalArgumentException
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
     * <b>POST : API URL => /api/v1/erp-order-item/list</b>
     * 
     * @param itemDtos : List::ErpOrderItemDto::
     * @return ResponseEntity(message, HttpStatus)
     * @see ErpOrderItemBusinessService#saveList
     */
    @PostMapping("/list")
    public ResponseEntity<?> saveList(@RequestBody @Valid List<ErpOrderItemDto> itemDtos) {
        Message message = new Message();

        erpOrderItemBusinessService.saveList(itemDtos);
        message.setStatus(HttpStatus.OK);
        message.setMessage("success");

        return new ResponseEntity<>(message, message.getStatus());
    }

    /**
     * Search erp order item.
     * <p>
     * <b>GET : API URL => /api/v1/erp-order-item/list</b>
     * 
     * @param params : Map::String, Object::
     * @return ResponseEntity(message, HttpStatus)
     * @see ErpOrderItemBusinessService#searchList
     */
    @GetMapping("/list")
    public ResponseEntity<?> searchList(@RequestParam Map<String, Object> params) {
        Message message = new Message();

        message.setData(erpOrderItemBusinessService.searchList(params));
        message.setStatus(HttpStatus.OK);
        message.setMessage("success");

        return new ResponseEntity<>(message, message.getStatus());
    }

    /**
     * Update erp order item to sales item.
     * <p>
     * <b>PATCH : API URL => /api/v1/erp-order-item/sales</b>
     * 
     * @param itemDtos : List::ErpOrderItemDto::
     * @return ResponseEntity(message, HttpStatus)
     * @see ErpOrderItemBusinessService#updateListToSales
     */
    @PatchMapping("/sales")
    public ResponseEntity<?> updateListToSales(@RequestBody List<ErpOrderItemDto> itemDtos) {
        Message message = new Message();

        erpOrderItemBusinessService.updateListToSales(itemDtos);
        message.setStatus(HttpStatus.OK);
        message.setMessage("success");

        return new ResponseEntity<>(message, message.getStatus());
    }

    /**
     * Update erp order item to sales item.
     * <p>
     * <b>PATCH : API URL => /api/v1/erp-order-item/sales/cancel</b>
     * 
     * @param itemDtos : List::ErpOrderItemDto::
     * @return ResponseEntity(message, HttpStatus)
     * @see ErpOrderItemBusinessService#updateListToSalesCancel
     */
    @PatchMapping("/sales/cancel")
    public ResponseEntity<?> updateListToSalesCancel(@RequestBody List<ErpOrderItemDto> itemDtos) {
        Message message = new Message();

        erpOrderItemBusinessService.updateListToSalesCancel(itemDtos);
        message.setStatus(HttpStatus.OK);
        message.setMessage("success");

        return new ResponseEntity<>(message, message.getStatus());
    }

    /**
     * Update erp order item to release item.
     * <p>
     * <b>PATCH : API URL => /api/v1/erp-order-item/release</b>
     * 
     * @param itemDtos : List::ErpOrderItemDto::
     * @return ResponseEntity(message, HttpStatus)
     * @see ErpOrderItemBusinessService#updateListToRelease
     */
    @PatchMapping("/release")
    public ResponseEntity<?> updateListToRelease(@RequestBody List<ErpOrderItemDto> itemDtos) {
        Message message = new Message();
 
        erpOrderItemBusinessService.updateListToRelease(itemDtos);
        message.setStatus(HttpStatus.OK);
        message.setMessage("success");

        return new ResponseEntity<>(message, message.getStatus());
    }

    /**
     * Update erp order item to release item.
     * <p>
     * <b>PATCH : API URL => /api/v1/erp-order-item/release/cancel</b>
     * 
     * @param itemDtos : List::ErpOrderItemDto::
     * @return ResponseEntity(message, HttpStatus)
     * @see ErpOrderItemBusinessService#updateListToReleaseCancel
     */
    @PatchMapping("/release/cancel")
    public ResponseEntity<?> updateListToReleaseCancel(@RequestBody List<ErpOrderItemDto> itemDtos) {
        Message message = new Message();
 
        erpOrderItemBusinessService.updateListToReleaseCancel(itemDtos);
        message.setStatus(HttpStatus.OK);
        message.setMessage("success");

        return new ResponseEntity<>(message, message.getStatus());
    }

    /**
     * Get combined delivery item of erp order item.
     * <p>
     * <b>POST : API URL => /api/v1/erp-order-item/combined</b>
     * 
     * @param itemDtos : List::ErpOrderItemDto::
     * @return ResponseEntity(message, HttpStatus)
     * @see ErpOrderItemBusinessService#getCombinedDelivery
     */
    @PostMapping("/combined")
    public ResponseEntity<?> getCombinedDelivery(@RequestBody List<ErpOrderItemDto> itemDtos) {
        Message message = new Message();

        message.setData(erpOrderItemBusinessService.getCombinedDelivery(itemDtos));
        message.setStatus(HttpStatus.OK);
        message.setMessage("success");

        return new ResponseEntity<>(message, message.getStatus());
    }

    /**
     * Get merged combined delivery item of erp order item.
     * <p>
     * <b>POST : API URL => /api/v1/erp-order-item/combined/merge</b>
     * 
     * @param itemDtos : List::ErpOrderItemDto::
     * @return ResponseEntity(message, HttpStatus)
     * @see ErpOrderItemBusinessService#getMergeCombinedDelivery
     */
    @PostMapping("/combined/merge")
    public ResponseEntity<?> getMergeCombinedDelivery(@RequestBody List<ErpOrderItemDto> itemDtos) {
        Message message = new Message();

        message.setData(erpOrderItemBusinessService.getMergeCombinedDelivery(itemDtos));
        message.setStatus(HttpStatus.OK);
        message.setMessage("success");

        return new ResponseEntity<>(message, message.getStatus());
    }

    /**
     * Delete erp order item.
     * <p>
     * <b>POST : API URL => /api/v1/erp-order-item/list/delete</b>
     * 
     * @param itemDtos : List::ErpOrderItemDto::
     * @return ResponseEntity(message, HttpStatus)
     * @see ErpOrderItemBusinessService#deleteList
     */
    @PostMapping("/list/delete")
    public ResponseEntity<?> deleteList(@RequestBody List<ErpOrderItemDto> itemDtos) {
        Message message = new Message();

        erpOrderItemBusinessService.deleteList(itemDtos);
        message.setStatus(HttpStatus.OK);
        message.setMessage("success");

        return new ResponseEntity<>(message, message.getStatus());
    }
    

}

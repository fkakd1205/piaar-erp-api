package com.piaar_erp.erp_api.domain.erp_order_item.controller;

import java.util.List;

import com.piaar_erp.erp_api.domain.erp_order_item.dto.ErpOrderItemDto;
import com.piaar_erp.erp_api.domain.erp_order_item.service.ErpOrderItemBusinessService;
import com.piaar_erp.erp_api.domain.message.dto.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
     * @see erpOrderItemBusinessService#saveItemList
     */
    @PostMapping("/list")
    public ResponseEntity<?> saveList(@RequestBody List<ErpOrderItemDto> itemDtos) {
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
     * @return ResponseEntity(message, HttpStatus)
     * @see ErpOrderItemBusinessService#getErpOrderItemByUserId
     */
    @GetMapping("/list")
    public ResponseEntity<?> searchList() {
        Message message = new Message();

        message.setData(erpOrderItemBusinessService.searchList());
        message.setStatus(HttpStatus.OK);
        message.setMessage("success");

        return new ResponseEntity<>(message, message.getStatus());
    }

    /**
     * Update erp order item to sales item.
     * <p>
     * <b>PUT : API URL => /api/v1/erp-order-item/sales</b>
     * 
     * @param itemDtos : List::ErpOrderItemDto::
     * @return ResponseEntity(message, HttpStatus)
     * @see ErpOrderItemBusinessService#updateListToSales
     */
    @PutMapping("/sales")
    public ResponseEntity<?> updateListToSales(@RequestBody List<ErpOrderItemDto> itemDtos) {
        Message message = new Message();

        erpOrderItemBusinessService.updateListToSales(itemDtos);
        message.setStatus(HttpStatus.OK);
        message.setMessage("success");

        return new ResponseEntity<>(message, message.getStatus());
    }

    /**
     * Update erp order item to release item.
     * <p>
     * <b>PUT : API URL => /api/v1/erp-order-item/release</b>
     * 
     * @param itemDtos : List::ErpOrderItemDto::
     * @return ResponseEntity(message, HttpStatus)
     * @see ErpOrderItemBusinessService#updateListToRelease
     */
    @PutMapping("/release")
    public ResponseEntity<?> updateListToRelease(@RequestBody List<ErpOrderItemDto> itemDtos) {
        Message message = new Message();
 
        erpOrderItemBusinessService.updateListToRelease(itemDtos);
        message.setStatus(HttpStatus.OK);
        message.setMessage("success");

        return new ResponseEntity<>(message, message.getStatus());
    }

}

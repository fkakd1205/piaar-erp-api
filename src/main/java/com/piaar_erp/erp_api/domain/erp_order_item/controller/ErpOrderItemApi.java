package com.piaar_erp.erp_api.domain.erp_order_item.controller;

import java.util.List;

import com.piaar_erp.erp_api.domain.erp_order_item.dto.ErpOrderItemDto;
import com.piaar_erp.erp_api.domain.erp_order_item.service.ErpOrderItemBusinessService;
import com.piaar_erp.erp_api.domain.exception.ExcelFileUploadException;
import com.piaar_erp.erp_api.domain.message.dto.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
     * Upload excel data for delivery ready.
     * <p>
     * <b>POST : API URL => /api/v1/erp-order-item/upload</b>
     * 
     * @param file : MultipartFile
     * @return ResponseEntity(message, HttpStatus)
     * @throws NullPointerException
     * @throws IllegalStateException
     * @throws IllegalArgumentException
     * @see ErpOrderItemBusinessService#isExcelFile
     * @see ErpOrderItemBusinessService#uploadErpOrderItem
     */
    @PostMapping("/upload")
    public ResponseEntity<?> uploadErpOrderExcel(@RequestParam("file") MultipartFile file) {
        Message message = new Message();

        // file extension check.
        erpOrderItemBusinessService.isExcelFile(file);
        try {
            message.setData(erpOrderItemBusinessService.uploadErpOrderExcel(file));
            message.setStatus(HttpStatus.OK);
            message.setMessage("success");
        } catch (NullPointerException e) {
            throw new ExcelFileUploadException("엑셀 파일 데이터에 올바르지 않은 값이 존재합니다.");
        } catch (IllegalStateException e) {
            throw new ExcelFileUploadException("피아르 엑셀 양식과 데이터 타입이 다른 값이 존재합니다.\n올바른 엑셀 파일을 업로드해주세요");
        } catch (IllegalArgumentException e) {
            throw new ExcelFileUploadException("피아르 양식의 엑셀 파일이 아닙니다.\n올바른 엑셀 파일을 업로드해주세요");
        }

        return new ResponseEntity<>(message, message.getStatus());
    }

    /**
     * Store excel data for delivery ready.
     * <p>
     * <b>POST : API URL => /api/v1/erp-order-item/store</b>
     * 
     * @param itemDtos : List::ErpOrderItemDto::
     * @return ResponseEntity(message, HttpStatus)
     * @see erpOrderItemBusinessService#saveItemList
     */
    @PostMapping("/store")
    public ResponseEntity<?> storeDeliveryReadyExcelFile(@RequestBody List<ErpOrderItemDto> itemDtos) {
        Message message = new Message();

        erpOrderItemBusinessService.saveItemList(itemDtos);
        message.setStatus(HttpStatus.OK);
        message.setMessage("success");

        return new ResponseEntity<>(message, message.getStatus());
    }
}

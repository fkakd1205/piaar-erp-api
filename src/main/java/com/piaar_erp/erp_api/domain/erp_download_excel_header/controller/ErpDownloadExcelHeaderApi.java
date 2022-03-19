package com.piaar_erp.erp_api.domain.erp_download_excel_header.controller;

import com.piaar_erp.erp_api.domain.erp_download_excel_header.dto.ErpDownloadExcelHeaderDto;
import com.piaar_erp.erp_api.domain.erp_download_excel_header.service.ErpDownloadExcelHeaderBusinessService;
import com.piaar_erp.erp_api.domain.erp_order_item.dto.ErpDownloadOrderItemDto;
import com.piaar_erp.erp_api.domain.erp_order_item.vo.ErpOrderItemVo;
import com.piaar_erp.erp_api.domain.message.dto.Message;
import com.piaar_erp.erp_api.utils.CustomFieldUtils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

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

    /**
     * Download merge excel file by erp download excel header.
     * <p>
     * <b>POST : API URL => /api/v1/erp-download-excel-headers/{id}/download-order-items/action-download</b>
     * 
     * @param response : HttpServletResponse
     * @param id : UUID
     * @param erpDownloadOrderItemDtos : List::ErpDownloadOrderItemDto:;
     * @see ErpDownloadExcelHeaderBusinessService#searchErpDownloadExcelHeader
     * @see ErpDownloadExcelHeaderBusinessService#downloadByErpDownloadExcelHeader
     */
    @PostMapping("/{id}/download-order-items/action-download")
    public void downloadForDownloadOrderItems(HttpServletResponse response, @PathVariable(value = "id") UUID id, @RequestBody List<ErpDownloadOrderItemDto> erpDownloadOrderItemDtos) {
        ErpDownloadExcelHeaderDto headerDto = erpDownloadExcelHeaderBusinessService.searchErpDownloadExcelHeader(id);
        List<String> details = headerDto.getHeaderDetail().getDetails().stream().map(r -> r.getCustomCellName()).collect(Collectors.toList());
        List<ErpOrderItemVo> vos = erpDownloadExcelHeaderBusinessService.downloadByErpDownloadExcelHeader(id, erpDownloadOrderItemDtos);

         // 엑셀 생성
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Sheet1");
        Row row = null;
        Cell cell = null;
        int rowNum = 0;

        row = sheet.createRow(rowNum++);
        for(int i = 0; i < details.size(); i++) {
            cell = row.createCell(i);
            cell.setCellValue(details.get(i));
        }

        // matchedColumnName 데이터만 헤더로 설정
        for(int i = 0; i < vos.size(); i++) {
            row = sheet.createRow(rowNum++);
            for(int j = 0; j < headerDto.getHeaderDetail().getDetails().size(); j++) {
                String cellValue = "";
                if(headerDto.getHeaderDetail().getDetails().get(j).getMatchedColumnName().equals("freightCode")) {
                    cellValue = erpDownloadOrderItemDtos.get(i).getCombinedFreightCode();
                }else {
                    cellValue = CustomFieldUtils.getFieldValue(vos.get(i), headerDto.getHeaderDetail().getDetails().get(j).getMatchedColumnName());
                }
                cell = row.createCell(j);
                cell.setCellValue(cellValue);
            }
        }

        for(int i = 0; i < headerDto.getHeaderDetail().getDetails().size(); i++) {
            sheet.autoSizeColumn(i);
        }
 
        response.setContentType("ms-vnd/excel");
        response.setHeader("Content-Disposition", "attachment;filename=example.xlsx");

        try{
            workbook.write(response.getOutputStream());
            workbook.close();
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }
}

package com.piaar_erp.erp_api.domain.erp_order_item.service;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.piaar_erp.erp_api.domain.erp_order_item.dto.ErpOrderItemDto;
import com.piaar_erp.erp_api.domain.erp_order_item.entity.ErpOrderItemEntity;
import com.piaar_erp.erp_api.domain.erp_order_item.proj.ErpOrderItemProj;
import com.piaar_erp.erp_api.domain.erp_order_item.vo.ErpOrderItemVo;
import com.piaar_erp.erp_api.domain.exception.CustomExcelFileUploadException;
import com.piaar_erp.erp_api.domain.product_option.dto.ProductOptionDto;
import com.piaar_erp.erp_api.domain.product_option.service.ProductOptionService;
import com.piaar_erp.erp_api.utils.CustomDateUtils;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ErpOrderItemBusinessService {
    private ErpOrderItemService erpOrderItemService;
    private ProductOptionService productOptionService;

    @Autowired
    public ErpOrderItemBusinessService(
        ErpOrderItemService erpOrderItemService,
        ProductOptionService productOptionService
    ) {
        this.erpOrderItemService = erpOrderItemService;
        this.productOptionService = productOptionService;
    }

    // Excel file extension.
    private final List<String> EXTENSIONS_EXCEL = Arrays.asList("xlsx", "xls");

    private final Integer PIAAR_ERP_ORDER_ITEM_SIZE = 40;
    private final Integer PIAAR_ERP_ORDER_MEMO_START_INDEX = 20;

    private final List<String> PIAAR_ERP_ORDER_HEADER_NAME_LIST = Arrays.asList(
            "피아르 고유번호",
            "주문번호1",
            "주문번호2",
            "주문번호3",
            "상품명",
            "옵션명",
            "수량",
            "수취인명",
            "전화번호1",
            "전화번호2",
            "주소",
            "우편번호",
            "배송방식",
            "배송메세지",
            "상품고유번호1",
            "상품고유번호2",
            "옵션고유번호1",
            "옵션고유번호2",
            "피아르 상품코드",
            "피아르 옵션코드",
            "관리메모1",
            "관리메모2",
            "관리메모3",
            "관리메모4",
            "관리메모5",
            "관리메모6",
            "관리메모7",
            "관리메모8",
            "관리메모9",
            "관리메모10",
            "관리메모11",
            "관리메모12",
            "관리메모13",
            "관리메모14",
            "관리메모15",
            "관리메모16",
            "관리메모17",
            "관리메모18",
            "관리메모19",
            "관리메모20");

    /**
     * <b>Extension Check</b>
     * <p>
     * 
     * @param file : MultipartFile
     * @throws CustomExcelFileUploadException
     */
    public void isExcelFile(MultipartFile file) {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename().toLowerCase());

        if (EXTENSIONS_EXCEL.contains(extension)) {
            return;
        }
        throw new CustomExcelFileUploadException("This is not an excel file.");
    }

    /**
     * <b>Upload Excel File</b>
     * <p>
     * 피아르 엑셀 파일을 업로드한다.
     * 
     * @param file : MultipartFile
     * @return List::ErpOrderItemVo::
     * @throws CustomExcelFileUploadException
     * @see ErpOrderItemBusinessService#getErpOrderItemForm
     */
    public List<ErpOrderItemVo> uploadErpOrderExcel(MultipartFile file) {
        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(file.getInputStream());
        } catch (IOException e) {
            throw new CustomExcelFileUploadException("피아르 양식의 엑셀 파일이 아닙니다.\n올바른 엑셀 파일을 업로드해주세요.");
        }

        Sheet sheet = workbook.getSheetAt(0);

        List<ErpOrderItemVo> vos = new ArrayList<>();
        try{
            vos = this.getErpOrderItemForm(sheet);
        } catch (NullPointerException e) {
            throw new CustomExcelFileUploadException("엑셀 파일 데이터에 올바르지 않은 값이 존재합니다.");
        } catch (IllegalStateException e) {
            throw new CustomExcelFileUploadException("피아르 엑셀 양식과 데이터 타입이 다른 값이 존재합니다.\n올바른 엑셀 파일을 업로드해주세요.");
        } catch (IllegalArgumentException e) {
            throw new CustomExcelFileUploadException("피아르 양식의 엑셀 파일이 아닙니다.\n올바른 엑셀 파일을 업로드해주세요.");
        }

        return vos;
    }

    private List<ErpOrderItemVo> getErpOrderItemForm(Sheet worksheet) {
        List<ErpOrderItemVo> itemVos = new ArrayList<>();

        Row firstRow = worksheet.getRow(0);
        // 피아르 엑셀 양식 검사
        for (int i = 0; i < PIAAR_ERP_ORDER_ITEM_SIZE; i++) {
            Cell cell = firstRow.getCell(i);
            String headerName = cell != null ? cell.getStringCellValue() : null;
            // 지정된 양식이 아니라면
            if (!PIAAR_ERP_ORDER_HEADER_NAME_LIST.get(i).equals(headerName)) {
                throw new CustomExcelFileUploadException("피아르 양식의 엑셀 파일이 아닙니다.\n올바른 엑셀 파일을 업로드해주세요.");
            }
        }

        for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
            Row row = worksheet.getRow(i);
            if (row == null)
                break;

            Object cellValue = new Object();
            List<String> customManagementMemo = new ArrayList<>();

            // type check and data setting of managementMemo1~20.
            for (int j = PIAAR_ERP_ORDER_MEMO_START_INDEX; j < PIAAR_ERP_ORDER_ITEM_SIZE; j++) {
                Cell cell = row.getCell(j);

                if (cell == null || cell.getCellType().equals(CellType.BLANK)) {
                    cellValue = "";
                } else if (cell.getCellType().equals(CellType.NUMERIC)) {
                    if (DateUtil.isCellDateFormatted(cell)) {
                        Instant instant = Instant.ofEpochMilli(cell.getDateCellValue().getTime());
                        LocalDateTime date = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
                        // yyyy-MM-dd'T'HH:mm:ss -> yyyy-MM-dd HH:mm:ss로 변경
                        String newDate = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                        cellValue = newDate;
                    } else {
                        cellValue = cell.getNumericCellValue();
                    }
                } else {
                    cellValue = cell.getStringCellValue();
                }
                customManagementMemo.add(cellValue.toString());
            }

            ErpOrderItemVo excelVo = ErpOrderItemVo.builder()
                    .uniqueCode(UUID.randomUUID())
                    .orderNumber1(row.getCell(1) != null ? row.getCell(1).getStringCellValue() : "")
                    .orderNumber2(row.getCell(2) != null ? row.getCell(2).getStringCellValue() : "")
                    .orderNumber3(row.getCell(3) != null ? row.getCell(3).getStringCellValue() : "")
                    .prodName(row.getCell(4) != null ? row.getCell(4).getStringCellValue() : "")
                    .optionName(row.getCell(5) != null ? row.getCell(5).getStringCellValue() : "")
                    .unit(row.getCell(6) != null ? (int) row.getCell(6).getNumericCellValue() : 0)
                    .receiver(row.getCell(7) != null ? row.getCell(7).getStringCellValue() : "")
                    .receiverContact1(row.getCell(8) != null ? row.getCell(8).getStringCellValue() : "")
                    .receiverContact2(row.getCell(9) != null ? row.getCell(9).getStringCellValue() : "")
                    .destination(row.getCell(10) != null ? row.getCell(10).getStringCellValue() : "")
                    .zipCode(row.getCell(11) != null ? row.getCell(11).getStringCellValue() : "")
                    .transportType(row.getCell(12) != null ? row.getCell(12).getStringCellValue() : "")
                    .deliveryMessage(row.getCell(13) != null ? row.getCell(13).getStringCellValue() : "")
                    .prodUniqueNumber1(row.getCell(14) != null ? row.getCell(14).getStringCellValue() : "")
                    .prodUniqueNumber2(row.getCell(15) != null ? row.getCell(15).getStringCellValue() : "")
                    .optionUniqueNumber1(row.getCell(16) != null ? row.getCell(16).getStringCellValue() : "")
                    .optionUniqueNumber2(row.getCell(17) != null ? row.getCell(17).getStringCellValue() : "")
                    .prodCode(row.getCell(18) != null ? row.getCell(18).getStringCellValue() : "")
                    .optionCode(row.getCell(19) != null ? row.getCell(19).getStringCellValue() : "")
                    .managementMemo1(customManagementMemo.get(0))
                    .managementMemo2(customManagementMemo.get(1))
                    .managementMemo3(customManagementMemo.get(2))
                    .managementMemo4(customManagementMemo.get(3))
                    .managementMemo5(customManagementMemo.get(4))
                    .managementMemo6(customManagementMemo.get(5))
                    .managementMemo7(customManagementMemo.get(6))
                    .managementMemo8(customManagementMemo.get(7))
                    .managementMemo9(customManagementMemo.get(8))
                    .managementMemo10(customManagementMemo.get(9))
                    .managementMemo11(customManagementMemo.get(10))
                    .managementMemo12(customManagementMemo.get(11))
                    .managementMemo13(customManagementMemo.get(12))
                    .managementMemo14(customManagementMemo.get(13))
                    .managementMemo15(customManagementMemo.get(14))
                    .managementMemo16(customManagementMemo.get(15))
                    .managementMemo17(customManagementMemo.get(16))
                    .managementMemo18(customManagementMemo.get(17))
                    .managementMemo19(customManagementMemo.get(18))
                    .managementMemo20(customManagementMemo.get(19))
                    .build();
                    
            itemVos.add(excelVo);
        }
        return itemVos;
    }

    public void saveList(List<ErpOrderItemDto> orderItemDtos) {
        UUID USER_ID = UUID.randomUUID();

        List<ErpOrderItemEntity> orderItemEntities = orderItemDtos.stream()
                .map(r -> {
                    r.setId(UUID.randomUUID())
                            .setUniqueCode(UUID.randomUUID())
                            .setSalesYn("n")
                            .setReleaseYn("n")
                            .setStockReflectYn("n")
                            .setCreatedAt(CustomDateUtils.getCurrentDateTime())
                            .setCreatedBy(USER_ID);

                    return ErpOrderItemEntity.toEntity(r);
                }).collect(Collectors.toList());

        erpOrderItemService.saveListAndModify(orderItemEntities);
    }

    /**
     * <b>DB Select Related Method</b>
     * <p>
     * 유저가 업로드한 엑셀을 전체 가져온다.
     * 피아르 관리코드에 대응하는 데이터들을 반환 Dto에 추가한다.
     *
     * @return List::ErpOrderItemVo::
     * @see ErpOrderItemService#findAllMappingDataByPiaarOptionCode
     * @see ErpOrderItemVo#toVo
     */
    public List<ErpOrderItemVo> searchList() {
        // 등록된 모든 엑셀 데이터를 조회한다
        List<ErpOrderItemProj> itemViewProjs = erpOrderItemService.findAllMappingDataByPiaarOptionCode();
        List<ErpOrderItemVo> itemVos = itemViewProjs.stream().map(r -> ErpOrderItemVo.toVo(r)).collect(Collectors.toList());

        // 옵션재고수량 추가
        List<ErpOrderItemVo> ErpOrderItemVos = this.getOptionStockUnit(itemVos);
        return ErpOrderItemVos;
    }

    /**
     * <b>DB Select Related Method</b>
     * <p>
     * 유저가 업로드한 엑셀 중 판매 처리된 데이터를 가져온다.
     * 피아르 관리코드에 대응하는 데이터들을 반환 Dto에 추가한다.
     *
     * @return List::ErpOrderItemVo::
     * @see ErpOrderItemService#findSalesListMappingDataByPiaarOptionCode
     * @see ErpOrderItemVo#toVo
     */
    public List<ErpOrderItemVo> searchSalesList() {
        List<ErpOrderItemProj> itemViewProjs = erpOrderItemService.findSalesListMappingDataByPiaarOptionCode();
        List<ErpOrderItemVo> itemVos = itemViewProjs.stream().map(r -> ErpOrderItemVo.toVo(r)).collect(Collectors.toList());

        // 옵션재고수량 추가
        List<ErpOrderItemVo> ErpOrderItemVos = this.getOptionStockUnit(itemVos);
        return ErpOrderItemVos;
    }

    /**
     * <b>DB Select Related Method</b>
     * <p>
     * 유저가 업로드한 엑셀 중 출고 처리된 데이터를 가져온다.
     * 피아르 관리코드에 대응하는 데이터들을 반환 Dto에 추가한다.
     *
     * @return List::ErpOrderItemVo::
     * @see ErpOrderItemService#findReleaseListMappingDataByPiaarOptionCode
     * @see ErpOrderItemVo#toVo
     */
    public List<ErpOrderItemVo> searchReleaseList() {
        List<ErpOrderItemProj> itemViewProjs = erpOrderItemService.findReleaseListMappingDataByPiaarOptionCode();
        List<ErpOrderItemVo> itemVos = itemViewProjs.stream().map(r -> ErpOrderItemVo.toVo(r)).collect(Collectors.toList());

        // 옵션재고수량 추가
        List<ErpOrderItemVo> ErpOrderItemVos = this.getOptionStockUnit(itemVos);
        return ErpOrderItemVos;
    }

    /**
     * <b>DB Select Related Method</b>
     * <p>
     * 조회된 피아르 엑셀 데이터에서 옵션코드 값과 대응하는 옵션데이터를 조회한다.
     * 옵션데이터의 재고수량을 피아르 엑셀 데이터에 추가한다.
     * 
     * @param itemVos : List::ErpOrderItemVo::
     * @return List::ErpOrderItemVo::
     * @see ProductOptionService#searchListByProductListOptionCode
     */
    public List<ErpOrderItemVo> getOptionStockUnit(List<ErpOrderItemVo> itemVos) {
        List<String> optionCodes = itemVos.stream().map(r -> r.getOptionCode()).collect(Collectors.toList());
        List<ProductOptionDto> optionGetDtos = productOptionService.searchListByProductListOptionCode(optionCodes);

        // 옵션 재고수량을 StockSumUnit(총 입고 수량 - 총 출고 수량)으로 변경
        List<ErpOrderItemVo> erpOrderItemVos = itemVos.stream().map(itemVo -> {
            // 옵션 코드와 동일한 상품의 재고수량을 변경한다
            optionGetDtos.stream().forEach(option -> {
                if (itemVo.getOptionCode().equals(option.getCode())) {
                    itemVo.setOptionStockUnit(option.getStockSumUnit());
                }
            });
            return itemVo;
        }).collect(Collectors.toList());

        return erpOrderItemVos;
    }

    /**
     * <b>DB Update Related Method</b>
     * <p>
     * 엑셀 데이터의 salesYn(판매 여부)을 y(판매 O)로 업데이트한다.
     * 
     * @param itemDtos : List::ErpOrderItemDto::
     * @see ErpOrderItemService#findAllByIdList
     * @see CustomDateUtils#getCurrentDateTime
     * @see ErpOrderItemService#saveListAndModify
     */
    public void updateListToSales(List<ErpOrderItemDto> itemDtos) {
        List<UUID> idList = itemDtos.stream().map(dto -> dto.getId()).collect(Collectors.toList());
        List<ErpOrderItemEntity> entities = erpOrderItemService.findAllByIdList(idList);

        entities.forEach(entity -> {
            entity.setSalesYn("y").setSalesAt(CustomDateUtils.getCurrentDateTime());
        });

        erpOrderItemService.saveListAndModify(entities);
    }

    /**
     * <b>DB Update Related Method</b>
     * <p>
     * 엑셀 데이터의 salesYn(판매 여부)을 n(판매 X)로 업데이트한다.
     * 
     * @param itemDtos : List::ErpOrderItemDto::
     * @see ErpOrderItemService#findAllByIdList
     * @see ErpOrderItemService#saveListAndModify
     */
    public void updateListToSalesCancel(List<ErpOrderItemDto> itemDtos) {
        List<UUID> idList = itemDtos.stream().map(dto -> dto.getId()).collect(Collectors.toList());
        List<ErpOrderItemEntity> entities = erpOrderItemService.findAllByIdList(idList);

        entities.forEach(entity -> {
            entity.setSalesYn("n").setSalesAt(null);
        });

        erpOrderItemService.saveListAndModify(entities);
    }

    /**
     * <b>DB Update Related Method</b>
     * <p>
     * 엑셀 데이터의 releaseYn(출고 여부)을 y(출고 O)로 업데이트한다.
     * 
     * @param itemDtos : List::ErpOrderItemDto::
     * @see ErpOrderItemService#findAllByIdList
     * @see CustomDateUtils#getCurrentDateTime
     * @see ErpOrderItemService#saveListAndModify
     */
    public void updateListToRelease(List<ErpOrderItemDto> itemDtos) {
        List<UUID> idList = itemDtos.stream().map(dto -> dto.getId()).collect(Collectors.toList());
        List<ErpOrderItemEntity> entities = erpOrderItemService.findAllByIdList(idList);

        entities.forEach(entity -> {
            entity.setReleaseYn("y").setReleaseAt(CustomDateUtils.getCurrentDateTime());
        });

        erpOrderItemService.saveListAndModify(entities);
    }

    /**
     * <b>DB Update Related Method</b>
     * <p>
     * 엑셀 데이터의 releaseYn(출고 여부)을 n(출고 X)로 업데이트한다.
     * 
     * @param itemDtos : List::ErpOrderItemDto::
     * @see ErpOrderItemService#findAllByIdList
     * @see ErpOrderItemService#saveListAndModify
     */
    public void updateListToReleaseCancel(List<ErpOrderItemDto> itemDtos) {
        List<UUID> idList = itemDtos.stream().map(dto -> dto.getId()).collect(Collectors.toList());
        List<ErpOrderItemEntity> entities = erpOrderItemService.findAllByIdList(idList);

        entities.forEach(entity -> {
            entity.setReleaseYn("n").setReleaseAt(null);
        });

        erpOrderItemService.saveListAndModify(entities);
    }
}

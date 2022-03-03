package com.piaar_erp.erp_api.domain.erp_order_item.service;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import com.piaar_erp.erp_api.domain.erp_order_header.dto.ErpOrderHeaderDto;
import com.piaar_erp.erp_api.domain.erp_order_header.entity.ErpOrderHeaderEntity;
import com.piaar_erp.erp_api.domain.erp_order_header.service.ErpOrderHeaderService;
import com.piaar_erp.erp_api.domain.erp_order_item.dto.ErpOrderItemDto;
import com.piaar_erp.erp_api.domain.erp_order_item.entity.ErpOrderItemEntity;
import com.piaar_erp.erp_api.domain.erp_order_item.proj.ErpOrderItemProj;
import com.piaar_erp.erp_api.domain.erp_order_item.repository.ErpOrderItemRepository;
import com.piaar_erp.erp_api.domain.erp_order_item.vo.CombinedDeliveryErpOrderItemVo;
import com.piaar_erp.erp_api.domain.erp_order_item.vo.ErpOrderItemVo;
import com.piaar_erp.erp_api.domain.exception.CustomExcelFileUploadException;
import com.piaar_erp.erp_api.domain.product_option.dto.ProductOptionDto;
import com.piaar_erp.erp_api.domain.product_option.entity.ProductOptionEntity;
import com.piaar_erp.erp_api.domain.product_option.service.ProductOptionService;
import com.piaar_erp.erp_api.utils.CustomDateUtils;
import com.piaar_erp.erp_api.utils.CustomFieldUtils;

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
    private ErpOrderHeaderService erpOrderHeaderService;

    @Autowired
    public ErpOrderItemBusinessService(
        ErpOrderItemService erpOrderItemService,
        ProductOptionService productOptionService,
        ErpOrderHeaderService erpOrderHeaderService
    ) {
        this.erpOrderItemService = erpOrderItemService;
        this.productOptionService = productOptionService;
        this.erpOrderHeaderService = erpOrderHeaderService;
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
                    .uniqueCode(UUID.randomUUID().toString())
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
                            .setUniqueCode(UUID.randomUUID().toString())
                            .setSalesYn("n")
                            .setReleaseOptionCode(r.getOptionCode())
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
     * @param params : Map::String, Object::
     * @return List::ErpOrderItemVo::
     * @see ErpOrderItemService#findAllM2OJ
     * @see ErpOrderItemBusinessService#getOptionStockUnit
     */
    public List<ErpOrderItemVo> searchList(Map<String, Object> params) {
        // 등록된 모든 엑셀 데이터를 조회한다
        List<ErpOrderItemProj> itemProjs = erpOrderItemService.findAllM2OJ(params);
        // 옵션재고수량 추가
        List<ErpOrderItemVo> ErpOrderItemVos = this.getOptionStockUnit(itemProjs);
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
     * @see ProductOptionService#searchStockUnit
     * @see ErpOrderItemVo#toVo
     */
    public List<ErpOrderItemVo> getOptionStockUnit(List<ErpOrderItemProj> itemProjs) {
        // 옵션이 존재하는 데이터들의 
        List<ProductOptionEntity> optionEntities = itemProjs.stream().filter(r -> r.getProductOption() != null ? true : false).collect(Collectors.toList())
            .stream().map(r -> r.getProductOption()).collect(Collectors.toList());

        List<ProductOptionDto> optionDtos = productOptionService.searchStockUnit(optionEntities);
        List<ErpOrderItemVo> itemVos = itemProjs.stream().map(r -> ErpOrderItemVo.toVo(r)).collect(Collectors.toList());

        // 옵션 재고수량을 StockSumUnit(총 입고 수량 - 총 출고 수량)으로 변경
        List<ErpOrderItemVo> erpOrderItemVos = itemVos.stream().map(itemVo -> {
            // 옵션 코드와 동일한 상품의 재고수량을 변경한다
            optionDtos.stream().forEach(option -> {
                if (itemVo.getOptionCode().equals(option.getCode())) {
                    itemVo.setOptionStockUnit(option.getStockSumUnit().toString());
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
    public void changeListToSales(List<ErpOrderItemDto> itemDtos) {
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
    public void changeListToSalesCancel(List<ErpOrderItemDto> itemDtos) {
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
    public void changeListToRelease(List<ErpOrderItemDto> itemDtos) {
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
    public void changeListToReleaseCancel(List<ErpOrderItemDto> itemDtos) {
        List<UUID> idList = itemDtos.stream().map(dto -> dto.getId()).collect(Collectors.toList());
        List<ErpOrderItemEntity> entities = erpOrderItemService.findAllByIdList(idList);

        entities.forEach(entity -> {
            entity.setReleaseYn("n").setReleaseAt(null);
        });

        erpOrderItemService.saveListAndModify(entities);
    }

    /**
     * <b>Data Processing Related Method</b>
     * <p>
     * 엑셀 데이터의 수취인 정보가 동일한 데이터들을 합배송 처리한다
     * 
     * @param dtos : List::ErpOrderItemDto::
     * @return  List::CombinedDeliveryErpOrderItemVo::
     */
    public List<CombinedDeliveryErpOrderItemVo> getCombinedDelivery(List<ErpOrderItemDto> dtos) {
        List<CombinedDeliveryErpOrderItemVo> combinedDeliveryItems = new ArrayList<>();
        Set<String> deliverySet = new HashSet<>();  // 수취인+전화번호+주소 를 담는 Set
        
        // 수취인 > 전화번호 > 주소 > 상품명 > 옵션명 으로 정렬
        dtos.sort(Comparator.comparing(ErpOrderItemDto::getReceiver)
                .thenComparing(ErpOrderItemDto::getReceiverContact1)
                .thenComparing(ErpOrderItemDto::getDestination)
                .thenComparing(ErpOrderItemDto::getProdName)
                .thenComparing(ErpOrderItemDto::getOptionName));

        for (int i = 0; i < dtos.size(); i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(dtos.get(i).getReceiver());
            sb.append(dtos.get(i).getReceiverContact1());
            sb.append(dtos.get(i).getDestination());

            String resultStr = sb.toString();
            List<ErpOrderItemDto> newCombinedList = new ArrayList<>();
            CombinedDeliveryErpOrderItemVo itemVo = new CombinedDeliveryErpOrderItemVo();

            // 새로운 데이터라면
            if (deliverySet.add(resultStr)) {
                newCombinedList.add(dtos.get(i));
                itemVo = CombinedDeliveryErpOrderItemVo.builder().combinedDeliveryItems(newCombinedList).build();
                combinedDeliveryItems.add(itemVo);
            } else { // 중복된다면
                // 이전 데이터에 현재 데이터를 추가한다
                newCombinedList = combinedDeliveryItems.get(combinedDeliveryItems.size() - 1).getCombinedDeliveryItems();
                newCombinedList.add(dtos.get(i));

                itemVo = CombinedDeliveryErpOrderItemVo.builder().combinedDeliveryItems(newCombinedList).build();

                // 이전 결합배송 리스트를 수정한다
                combinedDeliveryItems.set(combinedDeliveryItems.size() - 1, itemVo);
            }
        }
        return combinedDeliveryItems;
    }

    /**
     * <b>Data Processing Related Method</b>
     * <p>
     * 엑셀 데이터의 수취인 정보가 동일한 데이터들을 합배송 처리한다
     * 
     * @param dtos : List::ErpOrderItemDto::
     * @return  List::CombinedDeliveryErpOrderItemVo::
     */
    public List<CombinedDeliveryErpOrderItemVo> getMergeCombinedDelivery(List<ErpOrderItemDto> dtos) {
        // 수취인 정보가 동일한 합배송 데이터 추출
        List<CombinedDeliveryErpOrderItemVo> combinedDeliveryItems = this.getCombinedDelivery(dtos);

        ErpOrderHeaderEntity headerEntity = erpOrderHeaderService.findAll().stream().findFirst().orElse(null);
        ErpOrderHeaderDto headerDto = ErpOrderHeaderDto.toDto(headerEntity);
        List<String> matchedColumnName = headerDto.getHeaderDetail().getDetails().stream().filter(r -> r.getMergeYn().equals("y")).collect(Collectors.toList())
            .stream().map(r -> r.getMatchedColumnName()).collect(Collectors.toList());

        Set<String> deliverySet = new HashSet<>();
        
        for(int i = 0; i < combinedDeliveryItems.size(); i++) {
            for(int j = 0; j < combinedDeliveryItems.get(i).getCombinedDeliveryItems().size(); j++) {
                ErpOrderItemDto currentDto = combinedDeliveryItems.get(i).getCombinedDeliveryItems().get(j);

                StringBuilder sb = new StringBuilder();
                sb.append(currentDto.getReceiver());
                sb.append(currentDto.getReceiverContact1());
                sb.append(currentDto.getDestination());
                sb.append(currentDto.getProdName());
                sb.append(currentDto.getOptionName());

                String resultStr = sb.toString();

                if(!deliverySet.add(resultStr) && (j > 0)) {
                    ErpOrderItemDto prevDto = combinedDeliveryItems.get(i).getCombinedDeliveryItems().get(j-1);

                    // 중복데이터(상품 + 옵션) 수량 더하기
                    CustomFieldUtils.setFieldValue(prevDto, "unit", prevDto.getUnit() + currentDto.getUnit());

                    matchedColumnName.forEach(columnName -> {
                        String prevFieldValue = CustomFieldUtils.getFieldValue(prevDto, columnName) == null ? "" : CustomFieldUtils.getFieldValue(prevDto, columnName);
                        String currentFieldValue = CustomFieldUtils.getFieldValue(currentDto, columnName) == null ? "" : CustomFieldUtils.getFieldValue(currentDto, columnName);
                    
                        if(!columnName.equals("unit")) {
                            CustomFieldUtils.setFieldValue(prevDto, columnName, prevFieldValue + "|&&|" + currentFieldValue);
                        }
                    });

                    // 수량이 합쳐지고 남은 중복 데이터 제거
                    combinedDeliveryItems.get(i).getCombinedDeliveryItems().remove(j);
                }
            }
        }

        return combinedDeliveryItems;
    }

    /**
     * <b>Data Delete Related Method</b>
     * <p>
     * 피아르 엑셀 데이터를 삭제한다.
     * 
     * @param itemDtos : List::ErpOrderItemDto::
     * @see ErpOrderItemEntity#toEntity
     * @see ErpOrderItemService#delete
     */
    public void deleteList(List<ErpOrderItemDto> itemDtos) {
        itemDtos.stream().forEach(dto -> {
            ErpOrderItemEntity.toEntity(dto);
            erpOrderItemService.delete(dto.getId());
        });
    }

    public void changeAllOptionCode(List<ErpOrderItemDto> itemDtos) {
        itemDtos.stream().forEach(dto -> {
            ErpOrderItemEntity entity = erpOrderItemService.searchOne(dto.getId());
            entity.setOptionCode(dto.getOptionCode()).setReleaseOptionCode(dto.getOptionCode());

            erpOrderItemService.saveAndModify(entity);
        });
    }

    public void changeReleaseOptionCode(List<ErpOrderItemDto> itemDtos) {
        itemDtos.stream().forEach(dto -> {
            ErpOrderItemEntity entity = erpOrderItemService.searchOne(dto.getId());
            entity.setReleaseOptionCode(dto.getReleaseOptionCode());

            erpOrderItemService.saveAndModify(entity);
        });
    }
}

package com.piaar_erp.erp_api.domain.erp_order_item.service;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import com.piaar_erp.erp_api.domain.erp_first_merge_header.dto.ErpFirstMergeHeaderDto;
import com.piaar_erp.erp_api.domain.erp_first_merge_header.entity.ErpFirstMergeHeaderEntity;
import com.piaar_erp.erp_api.domain.erp_first_merge_header.service.ErpFirstMergeHeaderService;
import com.piaar_erp.erp_api.domain.erp_order_item.dto.ErpOrderItemDto;
import com.piaar_erp.erp_api.domain.erp_order_item.entity.ErpOrderItemEntity;
import com.piaar_erp.erp_api.domain.erp_order_item.proj.ErpOrderItemProj;
import com.piaar_erp.erp_api.domain.erp_order_item.vo.ErpOrderItemVo;
import com.piaar_erp.erp_api.domain.erp_second_merge_header.dto.DetailDto;
import com.piaar_erp.erp_api.domain.erp_second_merge_header.dto.ErpSecondMergeHeaderDto;
import com.piaar_erp.erp_api.domain.erp_second_merge_header.entity.ErpSecondMergeHeaderEntity;
import com.piaar_erp.erp_api.domain.erp_second_merge_header.service.ErpSecondMergeHeaderService;
import com.piaar_erp.erp_api.domain.exception.CustomExcelFileUploadException;
import com.piaar_erp.erp_api.domain.product_option.dto.ProductOptionDto;
import com.piaar_erp.erp_api.domain.product_option.entity.ProductOptionEntity;
import com.piaar_erp.erp_api.domain.product_option.service.ProductOptionService;
import com.piaar_erp.erp_api.utils.CustomDateUtils;
import com.piaar_erp.erp_api.utils.CustomFieldUtils;
import com.piaar_erp.erp_api.utils.CustomUniqueKeyUtils;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ErpOrderItemBusinessService {
    private final ErpOrderItemService erpOrderItemService;
    private final ProductOptionService productOptionService;
    private final ErpFirstMergeHeaderService erpFirstMergeHeaderService;
    private final ErpSecondMergeHeaderService erpSecondMergeHeaderService;

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
                    .uniqueCode(CustomUniqueKeyUtils.generateKey())
                    .freightCode(CustomUniqueKeyUtils.generateFreightCode())
                    .orderNumber1(row.getCell(1) != null ? row.getCell(1).getStringCellValue() : "")
                    .orderNumber2(row.getCell(2) != null ? row.getCell(2).getStringCellValue() : "")
                    .orderNumber3(row.getCell(3) != null ? row.getCell(3).getStringCellValue() : "")
                    .prodName(row.getCell(4) != null ? row.getCell(4).getStringCellValue() : "")
                    .optionName(row.getCell(5) != null ? row.getCell(5).getStringCellValue() : "")
                    .unit(row.getCell(6) != null ? Integer.toString((int)row.getCell(6).getNumericCellValue()) : "")
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

    public void createBatch(List<ErpOrderItemDto> orderItemDtos) {
        UUID USER_ID = UUID.randomUUID();

        List<ErpOrderItemEntity> orderItemEntities = orderItemDtos.stream()
                .map(r -> {
                    r.setId(UUID.randomUUID())
                            .setUniqueCode(r.getUniqueCode())
                            .setFreightCode(r.getFreightCode())
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
     * @see ErpOrderItemBusinessService#setOptionStockUnit
     */
    public List<ErpOrderItemVo> searchBatch(Map<String, Object> params) {
        // 등록된 모든 엑셀 데이터를 조회한다
        List<ErpOrderItemProj> itemProjs = erpOrderItemService.findAllM2OJ(params);       // 페이징 처리 x
        // 옵션재고수량 추가
        List<ErpOrderItemVo> ErpOrderItemVos = this.setOptionStockUnit(itemProjs);
        return ErpOrderItemVos;
    }

    /**
     * <b>DB Select Related Method</b>
     * <p>
     * 유저가 업로드한 엑셀을 전체 가져온다.
     * 피아르 관리코드에 대응하는 데이터들을 반환 Dto에 추가한다.
     *
     * @param params : Map::String, Object::
     * @param pageable : Pageable
     * @return List::ErpOrderItemVo::
     * @see ErpOrderItemService#findAllM2OJ
     * @see ErpOrderItemBusinessService#setOptionStockUnit
     */
    public Page<ErpOrderItemVo> searchBatchByPaging(Map<String, Object> params, Pageable pageable) {
        Page<ErpOrderItemProj> itemPages = erpOrderItemService.findAllM2OJByPage(params, pageable);
        // 등록된 모든 엑셀 데이터를 조회한다
        List<ErpOrderItemProj> itemProjs = itemPages.getContent();    // 페이징 처리 o
        // 옵션재고수량 추가
        List<ErpOrderItemVo> ErpOrderItemVos = this.setOptionStockUnit(itemProjs);

        return new PageImpl(ErpOrderItemVos, pageable, itemPages.getTotalElements());
    }

    /**
     * <b>DB Select Related Method</b>
     * <p>
     * 조회된 피아르 엑셀 데이터에서 옵션코드 값과 대응하는 옵션데이터를 조회한다.
     * 옵션데이터의 재고수량을 피아르 엑셀 데이터에 추가한다.
     * 
     * @param itemProjs : List::ErpOrderItemVo::
     * @return List::ErpOrderItemVo::
     * @see ProductOptionService#searchStockUnit
     * @see ErpOrderItemVo#toVo
     */
    public List<ErpOrderItemVo> setOptionStockUnit(List<ErpOrderItemProj> itemProjs) {
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
     * 엑셀 데이터의 salesYn(판매 여부)을 업데이트한다.
     * 
     * @param itemDtos : List::ErpOrderItemDto::
     * @see ErpOrderItemService#findAllByIdList
     * @see CustomDateUtils#getCurrentDateTime
     * @see ErpOrderItemService#saveListAndModify
     */
    public void changeBatchForSalesYn(List<ErpOrderItemDto> itemDtos) {
        List<UUID> idList = itemDtos.stream().map(dto -> dto.getId()).collect(Collectors.toList());
        List<ErpOrderItemEntity> entities = erpOrderItemService.findAllByIdList(idList);

        entities.forEach(entity -> {
            itemDtos.forEach(dto -> {
                if(entity.getId().equals(dto.getId())){
                    entity.setSalesYn(dto.getSalesYn()).setSalesAt(dto.getSalesAt());
                }
            });
        });

        erpOrderItemService.saveListAndModify(entities);
    }

    /**
     * <b>DB Update Related Method</b>
     * <p>
     * 엑셀 데이터의 releaseYn(출고 여부)을 업데이트한다.
     * 
     * @param itemDtos : List::ErpOrderItemDto::
     * @see ErpOrderItemService#findAllByIdList
     * @see CustomDateUtils#getCurrentDateTime
     * @see ErpOrderItemService#saveListAndModify
     */
    public void changeBatchForReleaseYn(List<ErpOrderItemDto> itemDtos) {
        List<UUID> idList = itemDtos.stream().map(dto -> dto.getId()).collect(Collectors.toList());
        List<ErpOrderItemEntity> entities = erpOrderItemService.findAllByIdList(idList);

        entities.forEach(entity -> {
            itemDtos.forEach(dto -> {
                if(entity.getId().equals(dto.getId())){
                    entity.setReleaseYn(dto.getReleaseYn()).setReleaseAt(dto.getReleaseAt());
                }
            });
        });

        erpOrderItemService.saveListAndModify(entities);
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
    public void deleteBatch(List<ErpOrderItemDto> itemDtos) {
        itemDtos.stream().forEach(dto -> {
            ErpOrderItemEntity.toEntity(dto);
            erpOrderItemService.delete(dto.getId());
        });
    }

    /**
     * <b>Data Update Related Method</b>
     * <p>
     * 변경 주문 옵션코드를 참고해 주문 옵션코드와 출고 옵션코드를 변경한다.
     * 
     * @param itemDtos : List::ErpOrderItemDto::
     * @see ErpOrderItemService#findAllByIdList
     * @see ErpOrderItemService#saveListAndModify
     */
    public void changeBatchForAllOptionCode(List<ErpOrderItemDto> itemDtos) {
        List<UUID> idList = itemDtos.stream().map(r -> r.getId()).collect(Collectors.toList());
        List<ErpOrderItemEntity> entities = erpOrderItemService.findAllByIdList(idList);

        entities.stream().forEach(entity -> {
            itemDtos.stream().forEach(dto -> {
                if(entity.getId().equals(dto.getId())) {
                    entity.setOptionCode(dto.getOptionCode()).setReleaseOptionCode(dto.getOptionCode());
                }
            });
        });
        erpOrderItemService.saveListAndModify(entities);
    }

    /**
     * <b>Data Update Related Method</b>
     * <p>
     * 출고 옵션코드를 변경한다.
     * 
     * @param itemDtos : List::ErpOrderItemDto::
     * @see ErpOrderItemService#findAllByIdList
     * @see ErpOrderItemService#saveListAndModify
     */
    public void changeBatchForReleaseOptionCode(List<ErpOrderItemDto> itemDtos) {
        List<UUID> idList = itemDtos.stream().map(r -> r.getId()).collect(Collectors.toList());
        List<ErpOrderItemEntity> entities = erpOrderItemService.findAllByIdList(idList);

        entities.stream().forEach(entity -> {
            itemDtos.stream().forEach(dto -> {
                if(entity.getId().equals(dto.getId())) {
                    entity.setReleaseOptionCode(dto.getReleaseOptionCode());
                }
            });
        });
        erpOrderItemService.saveListAndModify(entities);
    }

    /**
    * <b>Data Processing Related Method</b>
    * <p>
    * 수령인 > 수령인 전화번호 > 주소 > 상품명 > 옵션명 순으로 정렬해서
    * 동일 수령인정보 + 같은 상품과 옵션이라면 수량을 더한다
    * 병합 데이터의 나열 여부와 고정값 여부를 체크해서 데이터를 변환한다
    * 
    * @param firstMergeHeaderId : UUID
    * @param dtos : List::ErpOrderItemDto::
    * @return  List::ErpOrderItemVo::
    * @see ErpOrderItemBusinessService#searchErpFirstMergeHeader
    * @see CustomFieldUtils#getFieldValue
    * @see CustomFieldUtils#setFieldValue
    */
    public List<ErpOrderItemVo> getFirstMergeItem(UUID firstMergeHeaderId, List<ErpOrderItemDto> dtos) {
        List<ErpOrderItemVo> itemVos = dtos.stream().map(r -> ErpOrderItemVo.toVo(r)).collect(Collectors.toList());

        // 선택된 병합 헤더데이터 조회
        ErpFirstMergeHeaderDto headerDto = this.searchErpFirstMergeHeader(firstMergeHeaderId);

        // 나열 컬럼명 추출
        List<String> matchedColumnName = headerDto.getHeaderDetail().getDetails().stream().filter(r -> r.getMergeYn().equals("y")).collect(Collectors.toList())
            .stream().map(r -> r.getMatchedColumnName()).collect(Collectors.toList());

        // fixedValue가 존재하는 컬럼의 컬럼명과 fixedValue값 추출
        Map<String, String> fixedValueMap = headerDto.getHeaderDetail().getDetails().stream().filter(r -> !r.getFixedValue().isBlank()).collect(Collectors.toList())
            .stream().collect(Collectors.toMap(
                    key -> key.getMatchedColumnName(),
                    value -> value.getFixedValue()
            ));

        itemVos.sort(Comparator.comparing(ErpOrderItemVo::getReceiver)
            .thenComparing(ErpOrderItemVo::getReceiverContact1)
            .thenComparing(ErpOrderItemVo::getDestination)
            .thenComparing(ErpOrderItemVo::getProdName)
            .thenComparing(ErpOrderItemVo::getOptionName));

            
        // 반환할 병합 데이터
        List<ErpOrderItemVo> mergeItemVos = new ArrayList<>();
            
        Set<String> deliverySet = new HashSet<>();
        for (int i = 0; i < itemVos.size(); i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(itemVos.get(i).getReceiver());
            sb.append(itemVos.get(i).getReceiverContact1());
            sb.append(itemVos.get(i).getDestination());
            sb.append(itemVos.get(i).getProdName());
            sb.append(itemVos.get(i).getOptionName());

            String resultStr = sb.toString();
            
            mergeItemVos.add(itemVos.get(i));
            int currentMergeItemIndex = mergeItemVos.size()-1;

            // 중복데이터(상품 + 옵션)
            if(!deliverySet.add(resultStr)) {
                ErpOrderItemVo currentVo = mergeItemVos.get(currentMergeItemIndex);
                ErpOrderItemVo prevVo = mergeItemVos.get(currentMergeItemIndex-1);
                
                // 수량 더하기
                int sumUnit = Integer.parseInt(prevVo.getUnit()) + Integer.parseInt(currentVo.getUnit());
                CustomFieldUtils.setFieldValue(prevVo, "unit", String.valueOf(sumUnit));

                // 구분자로 나열 데이터 처리 - 수량은 제외하고
                matchedColumnName.forEach(columnName -> {
                    if (!columnName.equals("unit")) {
                        String prevFieldValue = CustomFieldUtils.getFieldValue(prevVo, columnName) == null ? "" : CustomFieldUtils.getFieldValue(prevVo, columnName);
                        String currentFieldValue = CustomFieldUtils.getFieldValue(currentVo, columnName) == null ? "" : CustomFieldUtils.getFieldValue(currentVo, columnName);
                        CustomFieldUtils.setFieldValue(prevVo, columnName, prevFieldValue + "|&&|" + currentFieldValue);
                    }
                });

                // 중복데이터 제거
                mergeItemVos.remove(currentMergeItemIndex);
            }

            // fixedValue가 지정된 column들은 fixedValue값으로 데이터를 덮어씌운다
            fixedValueMap.entrySet().stream().forEach(map -> {
                CustomFieldUtils.setFieldValue(mergeItemVos.get(mergeItemVos.size()-1), map.getKey(), map.getValue());
            });
        }
        return mergeItemVos;
    }

    /**
     * <b>Data Select Related Method</b>
     * <p>
     * firstMergeHeaderId에 대응하는 1차 병합헤더 데이터를 조회한다.
     * 
     * @param firstMergeHeaderId : UUID
     * @return ErpFirstMergeHeaderDto
     * @see ErpFirstMergeHeaderService#searchOne
     * @see ErpFirstMergeHeaerDto#toDto
     */
    public ErpFirstMergeHeaderDto searchErpFirstMergeHeader(UUID firstMergeHeaderId) {
        ErpFirstMergeHeaderEntity firstMergeHeaderEntity = erpFirstMergeHeaderService.searchOne(firstMergeHeaderId);
        return ErpFirstMergeHeaderDto.toDto(firstMergeHeaderEntity);
    }

    /**
    * <b>Data Processing Related Method</b>
    * <p>
    * 병합 여부와 splitter로 구분해 나타낼 컬럼들을 확인해 데이터를 나열한다
    * 동일 수령인정보라면 구분자(|&&|)로 표시해 병합한다
    * 고정값 여부를 체크해서 데이터를 고정값으로 채워넣는다
    * 
    * @param firstMergeHeaderId : UUID
    * @param dtos : List::ErpOrderItemDto::
    * @return  List::ErpOrderItemVo::
    * @see ErpOrderItemBusinessService#searchErpSecondMergeHeader
    * @see CustomFieldUtils#getFieldValue
    * @see CustomFieldUtils#setFieldValue
    */
    public List<ErpOrderItemVo> getSecondMergeItem(UUID secondMergeHeaderId, List<ErpOrderItemDto> dtos) {
        List<ErpOrderItemVo> itemVos = dtos.stream().map(r -> ErpOrderItemVo.toVo(r)).collect(Collectors.toList());

        // 선택된 병합 헤더데이터 조회
        ErpSecondMergeHeaderDto headerDto = this.searchErpSecondMergeHeader(secondMergeHeaderId);

        Map<String, String> splitterMap = headerDto.getHeaderDetail().getDetails().stream().filter(r -> r.getMergeYn().equals("y")).collect(Collectors.toList())
            .stream().collect(Collectors.toMap(
                r -> r.getMatchedColumnName(),
                r -> r.getSplitter()
            ));

        // fixedValue가 존재하는 컬럼의 컬럼명과 fixedValue값 추출
        Map<String, String> fixedValueMap = headerDto.getHeaderDetail().getDetails().stream().filter(r -> !r.getFixedValue().isBlank()).collect(Collectors.toList())
                .stream().collect(Collectors.toMap(
                        r -> r.getMatchedColumnName(),
                        r -> r.getFixedValue()));

        itemVos.sort(Comparator.comparing(ErpOrderItemVo::getReceiver)
                .thenComparing(ErpOrderItemVo::getReceiverContact1)
                .thenComparing(ErpOrderItemVo::getDestination)
                .thenComparing(ErpOrderItemVo::getProdName)
                .thenComparing(ErpOrderItemVo::getOptionName));

        for (int i = 0; i < itemVos.size() && i < dtos.size(); i++) {
            ErpOrderItemVo currentVo = itemVos.get(i);
            ErpOrderItemDto originDto = dtos.get(i);
            
            // 1. splitter로 나타낼 데이터 컬럼을 모두 추출해서 현재 데이터에 그 컬럼의 데이터 값을 구분자를 붙여 추가한다.
            // 2. 수령인이 동일하면 |&&|구분자로 병합해서 나열. 중복처리된 열 제거
            // 3. fixedValue가 존재하는 애들은 fixedValue값으로 채우기
            
            // 1. splitter로 나타낼 데이터 컬럼을 추출
            splitterMap.entrySet().stream().forEach(mergeMap -> {
                // viewDetails 
                DetailDto matchedDetail = headerDto.getHeaderDetail().getDetails().stream().filter(r -> r.getMatchedColumnName().equals(mergeMap.getKey())).collect(Collectors.toList()).get(0);
                String appendFieldValue = "";

                for(int j = 0; j < matchedDetail.getViewDetails().size(); j++) {
                    appendFieldValue += CustomFieldUtils.getFieldValue(originDto, matchedDetail.getViewDetails().get(j).getMatchedColumnName()).toString();
                    if(j < matchedDetail.getViewDetails().size()-1) {
                        appendFieldValue += mergeMap.getValue().toString();
                    }
                }
                CustomFieldUtils.setFieldValue(currentVo, mergeMap.getKey(), appendFieldValue);
            });
        }


        // 2. 수령인 동일하면 |&&|구분자로 병합해서 나열.
        List<ErpOrderItemVo> mergeItemVos = new ArrayList<>();
            
        Set<String> deliverySet = new HashSet<>();
        for (int i = 0; i < itemVos.size(); i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(itemVos.get(i).getReceiver());
            sb.append(itemVos.get(i).getReceiverContact1());
            sb.append(itemVos.get(i).getDestination());

            String resultStr = sb.toString();
            
            mergeItemVos.add(itemVos.get(i));
            int currentMergeItemIndex = mergeItemVos.size()-1;

            // 중복데이터(상품 + 옵션)
            if(!deliverySet.add(resultStr)) {
                ErpOrderItemVo currentVo = mergeItemVos.get(currentMergeItemIndex);
                ErpOrderItemVo prevVo = mergeItemVos.get(currentMergeItemIndex-1);

                splitterMap.entrySet().stream().forEach(mergeMap -> {
                    String prevFieldValue = CustomFieldUtils.getFieldValue(prevVo, mergeMap.getKey()) == null ? "" : CustomFieldUtils.getFieldValue(prevVo, mergeMap.getKey());
                    String currentFieldValue = CustomFieldUtils.getFieldValue(currentVo, mergeMap.getKey()) == null ? "" : CustomFieldUtils.getFieldValue(currentVo, mergeMap.getKey());
                    CustomFieldUtils.setFieldValue(prevVo, mergeMap.getKey(), prevFieldValue + "|&&|" + currentFieldValue);
                });

                // 중복데이터 제거
                mergeItemVos.remove(currentMergeItemIndex);
            }

            // 3. fixedValue가 지정된 column들은 fixedValue값으로 데이터를 덮어씌운다
            fixedValueMap.entrySet().stream().forEach(map -> {
                CustomFieldUtils.setFieldValue(mergeItemVos.get(mergeItemVos.size()-1), map.getKey(), map.getValue());
            });
        }

        return mergeItemVos;
    }

    public ErpSecondMergeHeaderDto searchErpSecondMergeHeader(UUID secondMergeHeaderId) {
        ErpSecondMergeHeaderEntity secondMergeHeaderEntity = erpSecondMergeHeaderService.searchOne(secondMergeHeaderId);
        return ErpSecondMergeHeaderDto.toDto(secondMergeHeaderEntity);
    }
}

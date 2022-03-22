package com.piaar_erp.erp_api.domain.erp_download_excel_header.service;

import com.piaar_erp.erp_api.domain.erp_download_excel_header.dto.DetailDto;
import com.piaar_erp.erp_api.domain.erp_download_excel_header.dto.ErpDownloadExcelHeaderDto;
import com.piaar_erp.erp_api.domain.erp_download_excel_header.entity.ErpDownloadExcelHeaderEntity;
import com.piaar_erp.erp_api.domain.erp_order_item.dto.ErpDownloadOrderItemDto;
import com.piaar_erp.erp_api.domain.erp_order_item.dto.ErpOrderItemDto;
import com.piaar_erp.erp_api.domain.erp_order_item.vo.ErpOrderItemVo;
import com.piaar_erp.erp_api.domain.erp_second_merge_header.dto.ErpSecondMergeHeaderDto;
import com.piaar_erp.erp_api.domain.erp_second_merge_header.entity.ErpSecondMergeHeaderEntity;
import com.piaar_erp.erp_api.domain.erp_second_merge_header.service.ErpSecondMergeHeaderService;
import com.piaar_erp.erp_api.utils.CustomDateUtils;
import com.piaar_erp.erp_api.utils.CustomFieldUtils;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ErpDownloadExcelHeaderBusinessService {
    private final ErpDownloadExcelHeaderService erpDownloadExcelHeaderService;

    /**
     * <b>DB Insert Related Method</b>
     * <p>
     * erp download excel header를 등록한다.
     *
     * @param headerDto : ErpDownloadExcelHeaderDto
     * @see ErpDownloadExcelHeaderEntity#toEntity
     * @see ErpDownloadExcelHeaderService#saveAndModify
     */
    public void saveOne(ErpDownloadExcelHeaderDto headerDto) {
        UUID USER_ID = UUID.randomUUID();
        ErpDownloadExcelHeaderEntity headerEntity = ErpDownloadExcelHeaderEntity.toEntity(headerDto);
        headerEntity
            .setId(UUID.randomUUID())
            .setCreatedAt(CustomDateUtils.getCurrentDateTime())
            .setCreatedBy(USER_ID);

        erpDownloadExcelHeaderService.saveAndModify(headerEntity);
    }

    /**
     * <b>DB Select Related Method</b>
     * <p>
     * 저장된 erp download excel header를 조회한다.
     *
     * @return List::ErpDownloadExcelHeaderDto::
     * @see ErpDownloadExcelHeaderService#searchAll
     * @see ErpDownloadExcelHeaderDto#toDto
     */
    public List<ErpDownloadExcelHeaderDto> searchAll() {
        List<ErpDownloadExcelHeaderEntity> entities = erpDownloadExcelHeaderService.searchAll();
        List<ErpDownloadExcelHeaderDto> dtos = entities.stream().map(r -> ErpDownloadExcelHeaderDto.toDto(r)).collect(Collectors.toList());
        return dtos;
    }

    /**
     * <b>DB Update Related Method</b>
     * <p>
     * 저장된 erp donwload excel header를 변경한다.
     *
     * @param headerDto : ErpDownloadExcelHeaderDto
     * @see ErpDownloadExcelHeaderService#searchOne
     * @see CustomDateUtils#getCurrentDateTime
     * @see ErpDownloadExcelHeaderService#saveAndModify
     */
    public void updateOne(ErpDownloadExcelHeaderDto headerDto) {
        ErpDownloadExcelHeaderEntity entity = erpDownloadExcelHeaderService.searchOne(headerDto.getId());

        entity.getHeaderDetail().setDetails(headerDto.getHeaderDetail().getDetails());
        entity.setTitle(headerDto.getTitle()).setUpdatedAt(CustomDateUtils.getCurrentDateTime());

        erpDownloadExcelHeaderService.saveAndModify(entity);
    }

    /**
     * <b>DB Delete Related Method</b>
     * <p>
     * id 값에 대응하는 엑셀 데이터를 삭제한다.
     * 
     * @param id : UUID
     * @ErpDownloadExcelHeaderService#delete
     */
    public void deleteOne(UUID id) {
        erpDownloadExcelHeaderService.deleteOne(id);
    }

    /**
     * <b>DB Select Related Method</b>
     * <p>
     * 병합 여부와 splitter로 구분해 나타낼 컬럼들을 확인해 데이터를 나열한다
     * 동일 수령인정보라면 구분자(|&&|)로 표시해 병합한다
     * 고정값 여부를 체크해서 데이터를 고정값으로 채워넣는다
     * 
     * @param id : UUID
     * @param erpDownloadOrderItemDtos : List::ErpDownloadOrderItemDto::
     * @return List::ErpOrderItemVo::
     * @see ErpDownloadExcelHeaderBusinessService#searchErpDownloadExcelHeader
     * @see ErpOrderItemVo#toVo
     * @see CustomFieldUtils#getFieldValue
     * @see CustomFieldUtils#setFieldValue
     */
    public List<ErpOrderItemVo> downloadByErpDownloadExcelHeader(UUID id, List<ErpDownloadOrderItemDto> erpDownloadOrderItemDtos) {
        // 선택된 병합 헤더데이터 조회
        ErpDownloadExcelHeaderDto headerDto = this.searchErpDownloadExcelHeader(id);

        Map<String, String> splitterMap = headerDto.getHeaderDetail().getDetails().stream()
                .filter(r -> r.getMergeYn().equals("y")).collect(Collectors.toList())
                .stream().collect(Collectors.toMap(
                        r -> r.getMatchedColumnName(),
                        r -> r.getSplitter()));

        // fixedValue가 존재하는 컬럼의 컬럼명과 fixedValue값 추출
        Map<String, String> fixedValueMap = headerDto.getHeaderDetail().getDetails().stream()
                .filter(r -> !r.getFixedValue().isBlank()).collect(Collectors.toList())
                .stream().collect(Collectors.toMap(
                        r -> r.getMatchedColumnName(),
                        r -> r.getFixedValue()));

        // 2. 수령인 동일하면 |&&|구분자로 병합해서 나열.
        List<ErpOrderItemVo> mergeItemVos = new ArrayList<>();

        for (int k = 0; k < erpDownloadOrderItemDtos.size(); k++) {
            // downloadDto의 k번쨰 collection
            List<ErpOrderItemDto> dtos = erpDownloadOrderItemDtos.get(k).getCollections();
            List<ErpOrderItemVo> itemVos = dtos.stream().map(r -> ErpOrderItemVo.toVo(r)).collect(Collectors.toList());

            itemVos.sort(Comparator.comparing(ErpOrderItemVo::getReceiver)
                    .thenComparing(ErpOrderItemVo::getReceiverContact1)
                    .thenComparing(ErpOrderItemVo::getDestination)
                    .thenComparing(ErpOrderItemVo::getProdName)
                    .thenComparing(ErpOrderItemVo::getOptionName));

            for (int i = 0; i < itemVos.size() && i < dtos.size(); i++) {
                ErpOrderItemVo currentVo = itemVos.get(i);
                ErpOrderItemDto originDto = dtos.get(i);

                // 1. splitter로 나타낼 데이터 컬럼을 추출
                splitterMap.entrySet().stream().forEach(mergeMap -> {
                    // viewDetails
                    DetailDto matchedDetail = headerDto.getHeaderDetail().getDetails().stream()
                            .filter(r -> r.getMatchedColumnName().equals(mergeMap.getKey()))
                            .collect(Collectors.toList()).get(0);
                    String appendFieldValue = "";

                    for (int j = 0; j < matchedDetail.getViewDetails().size(); j++) {
                        appendFieldValue += CustomFieldUtils
                                .getFieldValue(originDto, matchedDetail.getViewDetails().get(j).getMatchedColumnName())
                                .toString();
                        if (j < matchedDetail.getViewDetails().size() - 1) {
                            appendFieldValue += mergeMap.getValue().toString();
                        }
                    }
                    CustomFieldUtils.setFieldValue(currentVo, mergeMap.getKey(), appendFieldValue);
                });
            }

            Set<String> deliverySet = new HashSet<>();
            for (int i = 0; i < itemVos.size(); i++) {
                StringBuilder sb = new StringBuilder();
                sb.append(itemVos.get(i).getReceiver());
                sb.append(itemVos.get(i).getReceiverContact1());
                sb.append(itemVos.get(i).getDestination());

                String resultStr = sb.toString();

                mergeItemVos.add(itemVos.get(i));
                int currentMergeItemIndex = mergeItemVos.size() - 1;

                // 중복데이터(상품 + 옵션)
                if (!deliverySet.add(resultStr)) {
                    ErpOrderItemVo currentVo = mergeItemVos.get(currentMergeItemIndex);
                    ErpOrderItemVo prevVo = mergeItemVos.get(currentMergeItemIndex - 1);

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
                    CustomFieldUtils.setFieldValue(mergeItemVos.get(mergeItemVos.size() - 1), map.getKey(), map.getValue());
                });
            }
        }
        return mergeItemVos;
    }

    /**
     * <b>DB Select Related Method</b>
     * <p>
     * secondMergeHeaderId에 대응하는 erp download excel header를 조회한다.
     * 
     * @param secondMergeHeaderId : UUID
     * @return ErpDownloadExcelHeaderDto
     * @see ErpDownloadExcelHeaderService#searchOne
     * @see ErpDownloadExcelHeaderDto#toDto
     */
    public ErpDownloadExcelHeaderDto searchErpDownloadExcelHeader(UUID secondMergeHeaderId) {
        ErpDownloadExcelHeaderEntity downloadHeaderEntity = erpDownloadExcelHeaderService.searchOne(secondMergeHeaderId);
        return ErpDownloadExcelHeaderDto.toDto(downloadHeaderEntity);
    }
}

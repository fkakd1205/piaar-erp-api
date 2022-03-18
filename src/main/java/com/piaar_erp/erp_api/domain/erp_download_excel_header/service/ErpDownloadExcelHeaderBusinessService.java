package com.piaar_erp.erp_api.domain.erp_download_excel_header.service;

import com.piaar_erp.erp_api.domain.erp_download_excel_header.dto.ErpDownloadExcelHeaderDto;
import com.piaar_erp.erp_api.domain.erp_download_excel_header.entity.ErpDownloadExcelHeaderEntity;
import com.piaar_erp.erp_api.utils.CustomDateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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
        headerEntity.setId(UUID.randomUUID()).setCreatedAt(CustomDateUtils.getCurrentDateTime()).setCreatedBy(USER_ID);

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

    public void deleteOne(UUID id) {
        erpDownloadExcelHeaderService.deleteOne(id);
    }
}

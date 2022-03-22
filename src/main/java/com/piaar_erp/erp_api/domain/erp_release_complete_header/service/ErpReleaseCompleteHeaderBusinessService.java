package com.piaar_erp.erp_api.domain.erp_release_complete_header.service;

import java.util.UUID;

import com.piaar_erp.erp_api.domain.erp_release_complete_header.dto.ErpReleaseCompleteHeaderDto;
import com.piaar_erp.erp_api.domain.erp_release_complete_header.entity.ErpReleaseCompleteHeaderEntity;
import com.piaar_erp.erp_api.domain.exception.CustomNotFoundDataException;
import com.piaar_erp.erp_api.utils.CustomDateUtils;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ErpReleaseCompleteHeaderBusinessService {
    private final ErpReleaseCompleteHeaderService erpReleaseCompleteHeaderService;

    /**
     * <b>DB Insert Related Method</b>
     * <p>
     * erp release complete header를 등록한다.
     * 
     * @param headerDto : ErpReleaseCompleteHeaderDto
     * @see ErpReleaseCompleteHeaderEntity#toEntity
     */
    public void saveOne(ErpReleaseCompleteHeaderDto headerDto) {
        UUID ID = UUID.randomUUID();
        UUID USER_ID = UUID.randomUUID();
        headerDto
                .setId(ID)
                .setCreatedAt(CustomDateUtils.getCurrentDateTime())
                .setCreatedBy(USER_ID);
                // .setUpdatedAt(CustomDateUtils.getCurrentDateTime());
                
        ErpReleaseCompleteHeaderEntity headerEntity = ErpReleaseCompleteHeaderEntity.toEntity(headerDto);
        erpReleaseCompleteHeaderService.saveAndModify(headerEntity);
    }

    /**
     * <b>DB Select Related Method</b>
     * <p>
     * 저장된 erp release complete header를 조회한다.
     *
     * @return ErpReleaseCompleteHeaderDto
     * @see ErpReleaseCompleteHeaderService#findAll
     * @see ErpReleaseCompleteHeaderDto#toDto
     */
    public ErpReleaseCompleteHeaderDto searchOne() {
        ErpReleaseCompleteHeaderEntity headerEntity = erpReleaseCompleteHeaderService.findAll().stream().findFirst().orElse(null);
        
        return ErpReleaseCompleteHeaderDto.toDto(headerEntity);
    }

    /**
     * <b>DB Update Related Method</b>
     * <p>
     * 저장된 erp release complete header를 변경한다.
     * 
     * @param headerDto : ErpReleaseCompleteHeaderDto
     * @see ErpReleaseCompleteHeaderBusinessService#searchOne
     * @see CustomDateUtils#getCurrentDateTime
     * @see ErpReleaseCompleteHeaderEntity#toEntity
     */
    public void updateOne(ErpReleaseCompleteHeaderDto headerDto) {
        ErpReleaseCompleteHeaderDto dto = this.searchOne();
        
        if(dto == null) {
            throw new CustomNotFoundDataException("수정하려는 데이터를 찾을 수 없습니다.");
        }

        dto.getHeaderDetail().setDetails(headerDto.getHeaderDetail().getDetails());
        dto.setUpdatedAt(CustomDateUtils.getCurrentDateTime());

        erpReleaseCompleteHeaderService.saveAndModify(ErpReleaseCompleteHeaderEntity.toEntity(dto));
    }
}

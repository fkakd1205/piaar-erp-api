package com.piaar_erp.erp_api.domain.erp_release_ready_header.service;

import java.util.UUID;

import com.piaar_erp.erp_api.domain.erp_release_ready_header.dto.ErpReleaseReadyHeaderDto;
import com.piaar_erp.erp_api.domain.erp_release_ready_header.entity.ErpReleaseReadyHeaderEntity;
import com.piaar_erp.erp_api.domain.exception.CustomNotFoundDataException;
import com.piaar_erp.erp_api.utils.CustomDateUtils;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ErpReleaseReadyHeaderBusinessService {
    private final ErpReleaseReadyHeaderService erpReleaseReadyHeaderService;

    /**
     * <b>DB Insert Related Method</b>
     * <p>
     * erp release ready header를 등록한다.
     * 
     * @param headerDto : ErpReleaseReadyHeaderDto
     * @see ErpReleaseReadyHeaderEntity#toEntity
     */
    public void saveOne(ErpReleaseReadyHeaderDto headerDto) {
        UUID ID = UUID.randomUUID();
        UUID USER_ID = UUID.randomUUID();
        headerDto
            .setId(ID)
            .setCreatedAt(CustomDateUtils.getCurrentDateTime())
            .setCreatedBy(USER_ID);

        ErpReleaseReadyHeaderEntity headerEntity = ErpReleaseReadyHeaderEntity.toEntity(headerDto);
        erpReleaseReadyHeaderService.saveAndModify(headerEntity);
    }

    /**
     * <b>DB Select Related Method</b>
     * <p>
     * 저장된 erp release ready header를 조회한다.
     *
     * @return ErpReleaseReadyHeaderDto
     * @see ErpReleaseReadyHeaderService#findAll
     * @see ErpReleaseReadyHeaderDto#toDto
     */
    public ErpReleaseReadyHeaderDto searchOne() {
        ErpReleaseReadyHeaderEntity headerEntity = erpReleaseReadyHeaderService.findAll().stream().findFirst().orElse(null);
        
        return ErpReleaseReadyHeaderDto.toDto(headerEntity);
    }

    /**
     * <b>DB Update Related Method</b>
     * <p>
     * 저장된 erp release ready header를 변경한다.
     * 
     * @param headerDto : ErpReleaseReadyHeaderDto
     * @see ErpReleaseReadyHeaderBusinessService#searchOne
     * @see CustomDateUtils#getCurrentDateTime
     * @see ErpReleaseReadyHeaderEntity#toEntity
     */
    public void updateOne(ErpReleaseReadyHeaderDto headerDto) {
        ErpReleaseReadyHeaderDto dto = this.searchOne();
        
        if(dto == null) {
            throw new CustomNotFoundDataException("수정하려는 데이터를 찾을 수 없습니다.");
        }

        dto.getHeaderDetail().setDetails(headerDto.getHeaderDetail().getDetails());
        dto.setUpdatedAt(CustomDateUtils.getCurrentDateTime());

        erpReleaseReadyHeaderService.saveAndModify(ErpReleaseReadyHeaderEntity.toEntity(dto));
    }
}

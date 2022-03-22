package com.piaar_erp.erp_api.domain.erp_order_header.service;

import java.util.UUID;

import com.piaar_erp.erp_api.domain.erp_order_header.dto.ErpOrderHeaderDto;
import com.piaar_erp.erp_api.domain.erp_order_header.entity.ErpOrderHeaderEntity;
import com.piaar_erp.erp_api.domain.exception.CustomNotFoundDataException;
import com.piaar_erp.erp_api.utils.CustomDateUtils;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ErpOrderHeaderBusinessService {
    private final ErpOrderHeaderService erpOrderHeaderService;

    /**
     * <b>DB Insert Related Method</b>
     * <p>
     * erp order header를 등록한다.
     *
     * @param headerDto : ErpOrderHeaderDto
     * @see ErpOrderHeaderEntity#toEntity
     * @see ErpOrderHeaderService#saveAndModify
     */
    public void saveOne(ErpOrderHeaderDto headerDto) {
        UUID ID = UUID.randomUUID();
        UUID USER_ID = UUID.randomUUID();
        headerDto
                .setId(ID)
                .setCreatedAt(CustomDateUtils.getCurrentDateTime())
                .setCreatedBy(USER_ID);
                // .setUpdatedAt(CustomDateUtils.getCurrentDateTime());
        ErpOrderHeaderEntity headerEntity = ErpOrderHeaderEntity.toEntity(headerDto);

        erpOrderHeaderService.saveAndModify(headerEntity);
    }

    /**
     * <b>DB Select Related Method</b>
     * <p>
     * 저장된 erp order header를 조회한다.
     *
     * @return ErpOrderHeaderDto
     * @see ErpOrderHeaderService#findAll
     * @see ErpOrderHeaderDto#toDto
     */
    public ErpOrderHeaderDto searchOne() {
        ErpOrderHeaderEntity headerEntity = erpOrderHeaderService.findAll().stream().findFirst().orElse(null);

        return ErpOrderHeaderDto.toDto(headerEntity);
    }

    /**
     * <b>DB Update Related Method</b>
     * <p>
     * 저장된 erp order header를 변경한다.
     *
     * @param headerDto : ErpOrderHeaderDto
     * @see ErpOrderHeaderBusinessService#searchOne
     * @see CustomDateUtils#getCurrentDateTime
     * @see ErpOrderHeaderEntity#toEntity
     */
    public void updateOne(ErpOrderHeaderDto headerDto) {
        ErpOrderHeaderDto dto = this.searchOne();

        if (dto == null) {
            throw new CustomNotFoundDataException("수정하려는 데이터를 찾을 수 없습니다.");
        }

        dto.getHeaderDetail().setDetails(headerDto.getHeaderDetail().getDetails());
        dto.setUpdatedAt(CustomDateUtils.getCurrentDateTime());

        erpOrderHeaderService.saveAndModify(ErpOrderHeaderEntity.toEntity(dto));
    }
}

package com.piaar_erp.erp_api.domain.erp_delivery_header.service;

import java.util.UUID;

import com.piaar_erp.erp_api.domain.erp_delivery_header.dto.ErpDeliveryHeaderDto;
import com.piaar_erp.erp_api.domain.erp_delivery_header.entity.ErpDeliveryHeaderEntity;
import com.piaar_erp.erp_api.domain.exception.CustomNotFoundDataException;
import com.piaar_erp.erp_api.utils.CustomDateUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ErpDeliveryHeaderBusinessService {
    private ErpDeliveryHeaderService erpDeliveryHeaderService;

    @Autowired
    public ErpDeliveryHeaderBusinessService(
        ErpDeliveryHeaderService erpDeliveryHeaderService
    ) {
        this.erpDeliveryHeaderService = erpDeliveryHeaderService;
    }

    /**
     * <b>DB Insert Related Method</b>
     * <p>
     * erp delivery header를 등록한다.
     * 
     * @param headerDto : ErpDeliveryHeaderDto
     * @see ErpDeliveryHeaderEntity#toEntity
     */
    public void saveOne(ErpDeliveryHeaderDto headerDto) {
        UUID USER_ID = UUID.randomUUID();
        ErpDeliveryHeaderEntity headerEntity = ErpDeliveryHeaderEntity.toEntity(headerDto);
        headerEntity.setCreatedAt(CustomDateUtils.getCurrentDateTime()).setCreatedBy(USER_ID);

        erpDeliveryHeaderService.saveAndModify(headerEntity);
    }

    /**
     * <b>DB Select Related Method</b>
     * <p>
     * 저장된 erp delivery header를 조회한다.
     *
     * @return ErpDeliveryHeaderDto
     * @see ErpDeliveryHeaderService#findAll
     * @see ErpDeliveryHeaderDto#toDto
     */
    public ErpDeliveryHeaderDto searchOne() {
        ErpDeliveryHeaderEntity headerEntity = erpDeliveryHeaderService.findAll().stream().findFirst().orElse(null);
        
        return ErpDeliveryHeaderDto.toDto(headerEntity);
    }

    /**
     * <b>DB Update Related Method</b>
     * <p>
     * 저장된 erp delivery header를 변경한다.
     * 
     * @param headerDto : ErpDeliveryHeaderDto
     * @see ErpDeliveryHeaderBusinessService#searchOne
     * @see CustomDateUtils#getCurrentDateTime
     * @see ErpDeliveryHeaderEntity#toEntity
     */
    public void updateOne(ErpDeliveryHeaderDto headerDto) {
        ErpDeliveryHeaderDto dto = this.searchOne();
        
        if(dto == null) {
            throw new CustomNotFoundDataException("수정하려는 데이터를 찾을 수 없습니다.");
        }

        dto.getHeaderDetail().setDetails(headerDto.getHeaderDetail().getDetails());
        dto.setUpdatedAt(CustomDateUtils.getCurrentDateTime());

        erpDeliveryHeaderService.saveAndModify(ErpDeliveryHeaderEntity.toEntity(dto));
    }
}

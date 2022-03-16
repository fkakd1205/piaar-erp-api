package com.piaar_erp.erp_api.domain.erp_sales_header.service;

import java.util.UUID;

import com.piaar_erp.erp_api.domain.erp_sales_header.dto.ErpSalesHeaderDto;
import com.piaar_erp.erp_api.domain.erp_sales_header.entity.ErpSalesHeaderEntity;
import com.piaar_erp.erp_api.domain.exception.CustomNotFoundDataException;
import com.piaar_erp.erp_api.utils.CustomDateUtils;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ErpSalesHeaderBusinessService {
    private final ErpSalesHeaderService erpSalesHeaderService;

    /**
     * <b>DB Insert Related Method</b>
     * <p>
     * erp sales header를 등록한다.
     * 
     * @param headerDto : ErpSalesHeaderDto
     * @see ErpSalesHeaderEntity#toEntity
     */
    public void saveOne(ErpSalesHeaderDto headerDto) {
        UUID USER_ID = UUID.randomUUID();
        ErpSalesHeaderEntity headerEntity = ErpSalesHeaderEntity.toEntity(headerDto);
        headerEntity.setCreatedAt(CustomDateUtils.getCurrentDateTime()).setCreatedBy(USER_ID);

        erpSalesHeaderService.saveAndModify(headerEntity);
    }

    /**
     * <b>DB Select Related Method</b>
     * <p>
     * 저장된 erp sales header를 조회한다.
     *
     * @return ErpSalesHeaderDto
     * @see ErpSalesHeaderService#findAll
     * @see ErpSalesHeaderDto#toDto
     */
    public ErpSalesHeaderDto searchOne() {
        ErpSalesHeaderEntity headerEntity = erpSalesHeaderService.findAll().stream().findFirst().orElse(null);
        
        return ErpSalesHeaderDto.toDto(headerEntity);
    }

    /**
     * <b>DB Update Related Method</b>
     * <p>
     * 저장된 erp sales header를 변경한다.
     * 
     * @param headerDto : ErpSalesHeaderDto
     * @see ErpSalesHeaderBusinessService#searchOne
     * @see CustomDateUtils#getCurrentDateTime
     * @see ErpSalesHeaderEntity#toEntity
     */
    public void updateOne(ErpSalesHeaderDto headerDto) {
        ErpSalesHeaderDto dto = this.searchOne();
        
        if(dto == null) {
            throw new CustomNotFoundDataException("수정하려는 데이터를 찾을 수 없습니다.");
        }

        dto.getHeaderDetail().setDetails(headerDto.getHeaderDetail().getDetails());
        dto.setUpdatedAt(CustomDateUtils.getCurrentDateTime());

        erpSalesHeaderService.saveAndModify(ErpSalesHeaderEntity.toEntity(dto));
    }
}

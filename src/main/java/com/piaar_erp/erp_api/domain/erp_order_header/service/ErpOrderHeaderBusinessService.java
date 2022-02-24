package com.piaar_erp.erp_api.domain.erp_order_header.service;

import java.util.List;
import java.util.UUID;

import com.piaar_erp.erp_api.domain.erp_order_header.dto.ErpOrderHeaderDto;
import com.piaar_erp.erp_api.domain.erp_order_header.entity.ErpOrderHeaderEntity;
import com.piaar_erp.erp_api.utils.CustomDateUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ErpOrderHeaderBusinessService {
    private ErpOrderHeaderService erpOrderHeaderService;

    @Autowired
    public ErpOrderHeaderBusinessService(
        ErpOrderHeaderService erpOrderHeaderService
    ) {
        this.erpOrderHeaderService = erpOrderHeaderService;
    }

    /**
     * <b>DB Insert Related Method</b>
     * <p>
     * erp order header를 등록한다.
     * 
     * @param headerDto : ErpOrderHeaderDto
     * @see ErpOrderHeaderEntity#toEntity
     */
    public void saveOne(ErpOrderHeaderDto headerDto) {
        UUID USER_ID = UUID.randomUUID();
        headerDto.setCreatedAt(CustomDateUtils.getCurrentDateTime()).setCreatedBy(USER_ID).setUpdatedAt(CustomDateUtils.getCurrentDateTime());

        erpOrderHeaderService.saveAndModify(ErpOrderHeaderEntity.toEntity(headerDto));
    }

    /**
     * <b>DB Select Related Method</b>
     * <p>
     * 저장된 erp order header를 조회한다.
     *
     * @return ErpOrderHeaderDto
     * @see ErpOrderHeaderService#findAll
     */
    public ErpOrderHeaderDto searchAll() {
        List<ErpOrderHeaderEntity> headerEntity = erpOrderHeaderService.findAll();

        if(headerEntity.size() == 0) {
            return null;
        }
        
        return ErpOrderHeaderDto.toDto(headerEntity.get(0));
    }
}

package com.piaar_erp.erp_api.domain.erp_delivery_header.service;

import java.util.List;

import com.piaar_erp.erp_api.domain.erp_delivery_header.entity.ErpDeliveryHeaderEntity;
import com.piaar_erp.erp_api.domain.erp_delivery_header.repository.ErpDeliveryHeaderRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ErpDeliveryHeaderService {
    private final ErpDeliveryHeaderRepository erpDeliveryHeaderRepository;

    /**
     * <b>DB Insert Or Update Related Method</b>
     * <p>
     * 피아르 엑셀 헤더를 저장 or 수정한다.
     * 
     * @param entity : ErpDeliveryHeaderEntity
     * @see ErpDeliveryHeaderRepository#save
     */
    public void saveAndModify(ErpDeliveryHeaderEntity entity) {
        erpDeliveryHeaderRepository.save(entity);
    }

     /**
     * <b>DB Select Related Method</b>
     * <p>
     * erp delivery header을 조회한다.
     *
     * @return List::ErpDeliveryHeaderEntity::
     * @see ErpDeliveryHeaderRepository#findAll
     */
    public List<ErpDeliveryHeaderEntity> findAll() {
        return erpDeliveryHeaderRepository.findAll();
    }
}

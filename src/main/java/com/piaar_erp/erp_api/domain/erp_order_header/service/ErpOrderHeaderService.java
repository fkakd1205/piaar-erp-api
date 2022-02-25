package com.piaar_erp.erp_api.domain.erp_order_header.service;

import java.util.List;

import com.piaar_erp.erp_api.domain.erp_order_header.entity.ErpOrderHeaderEntity;
import com.piaar_erp.erp_api.domain.erp_order_header.repository.ErpOrderHeaderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ErpOrderHeaderService {
    private ErpOrderHeaderRepository erpOrderHeaderRepository;

    @Autowired
    public ErpOrderHeaderService(
        ErpOrderHeaderRepository erpOrderHeaderRepository
    ) {
        this.erpOrderHeaderRepository = erpOrderHeaderRepository;   
    }

    /**
     * <b>DB Insert Or Update Related Method</b>
     * <p>
     * 피아르 엑셀 헤더를 저장 or 수정한다.
     * 
     * @param entity : ErpOrderHeaderEntity
     * @see ErpOrderHeaderRepository#save
     */
    public void saveAndModify(ErpOrderHeaderEntity entity) {
        erpOrderHeaderRepository.save(entity);
    }

     /**
     * <b>DB Select Related Method</b>
     * <p>
     * erp order header을 조회한다.
     *
     * @return List::ErpOrderHeaderEntity::
     * @see ErpOrderHeaderRepository#findAll
     */
    public List<ErpOrderHeaderEntity> findAll() {
        return erpOrderHeaderRepository.findAll();
    }
}

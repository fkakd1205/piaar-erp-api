package com.piaar_erp.erp_api.domain.erp_sales_header.service;

import java.util.List;

import com.piaar_erp.erp_api.domain.erp_sales_header.entity.ErpSalesHeaderEntity;
import com.piaar_erp.erp_api.domain.erp_sales_header.repository.ErpSalesHeaderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ErpSalesHeaderService {
    private ErpSalesHeaderRepository erpSalesHeaderRepository;

    @Autowired
    public ErpSalesHeaderService(
        ErpSalesHeaderRepository erpSalesHeaderRepository
    ) {
        this.erpSalesHeaderRepository = erpSalesHeaderRepository;   
    }

    /**
     * <b>DB Insert Or Update Related Method</b>
     * <p>
     * 피아르 엑셀 판매 헤더를 저장 or 수정한다.
     * 
     * @param entity : ErpSalesHeaderEntity
     * @see ErpSalesHeaderRepository#save
     */
    public void saveAndModify(ErpSalesHeaderEntity entity) {
        erpSalesHeaderRepository.save(entity);
    }

     /**
     * <b>DB Select Related Method</b>
     * <p>
     * erp sales header을 조회한다.
     *
     * @return List::ErpSalesHeaderEntity::
     * @see ErpSalesHeaderRepository#findAll
     */
    public List<ErpSalesHeaderEntity> findAll() {
        return erpSalesHeaderRepository.findAll();
    }
}

package com.piaar_erp.erp_api.domain.erp_release_complete_header.service;

import java.util.List;

import com.piaar_erp.erp_api.domain.erp_release_complete_header.entity.ErpReleaseCompleteHeaderEntity;
import com.piaar_erp.erp_api.domain.erp_release_complete_header.repository.ErpReleaseCompleteHeaderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ErpReleaseCompleteHeaderService {
    private ErpReleaseCompleteHeaderRepository erpReleaseCompleteHeaderRepository;

    @Autowired
    public ErpReleaseCompleteHeaderService(
        ErpReleaseCompleteHeaderRepository erpReleaseCompleteHeaderRepository
    ) {
        this.erpReleaseCompleteHeaderRepository = erpReleaseCompleteHeaderRepository;   
    }

    /**
     * <b>DB Insert Or Update Related Method</b>
     * <p>
     * 피아르 엑셀 헤더를 저장 or 수정한다.
     * 
     * @param entity : ErpReleaseCompleteHeaderEntity
     * @see ErpReleaseCompleteHeaderRepository#save
     */
    public void saveAndModify(ErpReleaseCompleteHeaderEntity entity) {
        erpReleaseCompleteHeaderRepository.save(entity);
    }

     /**
     * <b>DB Select Related Method</b>
     * <p>
     * erp release complete header을 조회한다.
     *
     * @return List::ErpReleaseCompleteHeaderEntity::
     * @see ErpReleaseCompleteHeaderRepository#findAll
     */
    public List<ErpReleaseCompleteHeaderEntity> findAll() {
        return erpReleaseCompleteHeaderRepository.findAll();
    }
}

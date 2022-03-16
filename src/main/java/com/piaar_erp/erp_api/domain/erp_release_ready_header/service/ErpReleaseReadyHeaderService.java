package com.piaar_erp.erp_api.domain.erp_release_ready_header.service;

import java.util.List;

import com.piaar_erp.erp_api.domain.erp_release_ready_header.entity.ErpReleaseReadyHeaderEntity;
import com.piaar_erp.erp_api.domain.erp_release_ready_header.repository.ErpReleaseReadyHeaderRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ErpReleaseReadyHeaderService {
    private final ErpReleaseReadyHeaderRepository erpReleaseReadyHeaderRepository;

    /**
     * <b>DB Insert Or Update Related Method</b>
     * <p>
     * 피아르 엑셀 헤더를 저장 or 수정한다.
     * 
     * @param entity : ErpReleaseReadyHeaderEntity
     * @see ErpReleaseReadyHeaderRepository#save
     */
    public void saveAndModify(ErpReleaseReadyHeaderEntity entity) {
        erpReleaseReadyHeaderRepository.save(entity);
    }

     /**
     * <b>DB Select Related Method</b>
     * <p>
     * erp release ready header을 조회한다.
     *
     * @return List::ErpReleaseReadyHeaderEntity::
     * @see ErpReleaseReadyHeaderRepository#findAll
     */
    public List<ErpReleaseReadyHeaderEntity> findAll() {
        return erpReleaseReadyHeaderRepository.findAll();
    }
}

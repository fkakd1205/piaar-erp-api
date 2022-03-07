package com.piaar_erp.erp_api.domain.erp_first_merge_header.service;

import com.piaar_erp.erp_api.domain.erp_first_merge_header.entity.ErpFirstMergeHeaderEntity;
import com.piaar_erp.erp_api.domain.erp_first_merge_header.repository.ErpFirstMergeHeaderRepository;
import com.piaar_erp.erp_api.domain.exception.CustomNotFoundDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ErpFirstMergeHeaderService {
    private ErpFirstMergeHeaderRepository erpFirstMergeHeaderRepository;

    @Autowired
    public ErpFirstMergeHeaderService(ErpFirstMergeHeaderRepository erpFirstMergeHeaderRepository) {
        this.erpFirstMergeHeaderRepository = erpFirstMergeHeaderRepository;
    }

    /**
     * <b>DB Insert Or Update Related Method</b>
     * <p>
     * erp first merge header를 저장 or 수정한다.
     *
     * @param entity : ErpFirstMergeHeaderEntity
     * @see ErpFirstMergeHeaderRepository#save
     */
    public void saveAndModify(ErpFirstMergeHeaderEntity entity) {
        erpFirstMergeHeaderRepository.save(entity);
    }

    /**
     * <b>DB Select Related Method</b>
     * <p>
     * erp first merge header을 조회한다.
     *
     * @return List::ErpFirstMergeHeaderEntity::
     * @see ErpFirstMergeHeaderRepository#findAll
     */
    public List<ErpFirstMergeHeaderEntity> searchAll() {
        return erpFirstMergeHeaderRepository.findAll();
    }

    /**
     * <b>DB Select Related Method</b>
     * <p>
     * id에 대응하는 erp first merge header을 조회한다.
     *
     * @return ErpFirstMergeHeaderEntity
     * @see ErpFirstMergeHeaderRepository#findById
     */
    public ErpFirstMergeHeaderEntity searchOne(UUID id) {
        Optional<ErpFirstMergeHeaderEntity> entityOpt = erpFirstMergeHeaderRepository.findById(id);

        if(entityOpt.isPresent()) {
            return entityOpt.get();
        }else {
            throw new CustomNotFoundDataException("수정하려는 데이터를 찾을 수 없습니다.");
        }
    }
}

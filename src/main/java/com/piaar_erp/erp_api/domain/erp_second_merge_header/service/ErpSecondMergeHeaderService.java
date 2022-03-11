package com.piaar_erp.erp_api.domain.erp_second_merge_header.service;

import com.piaar_erp.erp_api.domain.erp_second_merge_header.entity.ErpSecondMergeHeaderEntity;
import com.piaar_erp.erp_api.domain.erp_second_merge_header.repository.ErpSecondMergeHeaderRepository;
import com.piaar_erp.erp_api.domain.exception.CustomNotFoundDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ErpSecondMergeHeaderService {
    private final ErpSecondMergeHeaderRepository erpSecondMergeHeaderRepository;

    /**
     * <b>DB Insert Or Update Related Method</b>
     * <p>
     * erp second merge header를 저장 or 수정한다.
     *
     * @param entity : ErpSecondMergeHeaderEntity
     * @see ErpSecondMergeHeaderRepository#save
     */
    public void saveAndModify(ErpSecondMergeHeaderEntity entity) {
        erpSecondMergeHeaderRepository.save(entity);
    }

    /**
     * <b>DB Select Related Method</b>
     * <p>
     * erp second merge header을 조회한다.
     *
     * @return List::ErpSecondMergeHeaderEntity::
     * @see ErpSecondMergeHeaderRepository#findAll
     */
    public List<ErpSecondMergeHeaderEntity> searchAll() {
        return erpSecondMergeHeaderRepository.findAll();
    }

    /**
     * <b>DB Select Related Method</b>
     * <p>
     * id에 대응하는 erp Second merge header을 조회한다.
     *
     * @param id : UUID
     * @return ErpSecondMergeHeaderEntity
     * @see ErpSecondMergeHeaderRepository#findById
     */
    public ErpSecondMergeHeaderEntity searchOne(UUID id) {
        Optional<ErpSecondMergeHeaderEntity> entityOpt = erpSecondMergeHeaderRepository.findById(id);

        if(entityOpt.isPresent()) {
            return entityOpt.get();
        }else {
            throw new CustomNotFoundDataException("수정하려는 데이터를 찾을 수 없습니다.");
        }
    }

    /**
     * <b>DB Delete Related Method</b>
     * <p>
     * id에 대응하는 erp second merge header을 삭제한다.
     *
     * @param id : UUID
     * @see ErpSecondMergeHeaderRepository#findById
     * @see ErpSecondMergeHeaderRepository#delete
     */
    public void deleteOne(UUID id) {
        erpSecondMergeHeaderRepository.findById(id).ifPresent(header -> erpSecondMergeHeaderRepository.delete(header));
    }
}

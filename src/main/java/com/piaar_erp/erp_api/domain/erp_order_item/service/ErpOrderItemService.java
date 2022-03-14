package com.piaar_erp.erp_api.domain.erp_order_item.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import com.piaar_erp.erp_api.domain.erp_order_item.entity.ErpOrderItemEntity;
import com.piaar_erp.erp_api.domain.erp_order_item.proj.ErpOrderItemProj;
import com.piaar_erp.erp_api.domain.erp_order_item.repository.ErpOrderItemRepository;
import com.piaar_erp.erp_api.domain.exception.CustomNotFoundDataException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ErpOrderItemService {
    private ErpOrderItemRepository erpOrderItemRepository;

    @Autowired
    public ErpOrderItemService(
        ErpOrderItemRepository erpOrderItemRepository
    ) {
        this.erpOrderItemRepository = erpOrderItemRepository;
    }

    /**
     * <b>DB Insert Or Update Related Method</b>
     * <p>
     * 피아르 엑셀 데이터를 저장 or 수정한다.
     *
     * @param itemEntity : ErpOrderItemEntity
     * @see ErpOrderItemRepository#save
     */
    public void saveAndModify(ErpOrderItemEntity itemEntity) {
        erpOrderItemRepository.save(itemEntity);
    }

    /**
     * <b>DB Insert Or Update Related Method</b>
     * <p>
     * 피아르 엑셀 데이터를 저장 or 수정한다.
     *
     * @param itemEntities : List::ErpOrderItemEntity::
     * @see ErpOrderItemRepository#saveAll
     */
    public void saveListAndModify(List<ErpOrderItemEntity> itemEntities) {
        erpOrderItemRepository.saveAll(itemEntities);
    }

    /**
     * <b>DB Select Related Method</b>
     * <p>
     * 피아르 엑셀 데이터를 모두 조회한다.
     *
     * @return List::ErpOrderItemProj::
     * @see ErpOrderItemRepository#qfindAllM2OJ
     * 
     */
    public List<ErpOrderItemProj> findAllM2OJ(Map<String, Object> params) {
        return erpOrderItemRepository.qfindAllM2OJ(params);
    }

    /**
     * <b>DB Select Related Method</b>
     * <p>
     * 페이지 처리 후 피아르 엑셀 데이터를 모두 조회한다.
     *
     * @return List::ErpOrderItemProj::
     * @see ErpOrderItemRepository#qfindAllM2OJ
     * 
     */
    public Page<ErpOrderItemProj> findAllM2OJByPage(Map<String, Object> params, Pageable pageable) {
        return erpOrderItemRepository.qfindAllM2OJByPage(params, pageable);
    }

    /**
     * <b>DB Select Related Method</b>
     * <p>
     * id 값들과 대응하는 엑셀 데이터를 모두 조회한다.
     * 
     * @param idList : List::UUID::
     * @return List::ErpOrderItemEntity::
     * @see ErpOrderItemRepository#qfindAllByIdList
     */
    public List<ErpOrderItemEntity> findAllByIdList(List<UUID> idList) {
        return erpOrderItemRepository.qfindAllByIdList(idList);
    }

    /**
     * <b>DB Delete Related Method</b>
     * <p>
     * id 값에 대응하는 엑셀 데이터를 모두 삭제한다.
     * 
     * @param id : UUID
     * @ErpOrderItemRepository#findById
     * @ErpOrderItemRepository#delete
     */
    public void delete(UUID id) {
        erpOrderItemRepository.findById(id).ifPresent(item -> {
            erpOrderItemRepository.delete(item);
        });
    }

    public ErpOrderItemEntity searchOne(UUID id) {
        Optional<ErpOrderItemEntity> entityOpt = erpOrderItemRepository.findById(id);

        if(entityOpt.isPresent()){
            return entityOpt.get();
        }else{
            throw new CustomNotFoundDataException("존재하지 않는 데이터입니다.");
        }
    }
}

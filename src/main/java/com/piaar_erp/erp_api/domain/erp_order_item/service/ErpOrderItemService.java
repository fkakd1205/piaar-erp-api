package com.piaar_erp.erp_api.domain.erp_order_item.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.piaar_erp.erp_api.domain.erp_order_item.entity.ErpOrderItemEntity;
import com.piaar_erp.erp_api.domain.erp_order_item.proj.ErpOrderItemProj;
import com.piaar_erp.erp_api.domain.erp_order_item.repository.ErpOrderItemRepository;

import org.springframework.beans.factory.annotation.Autowired;
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
     * id 값들과 대응하는 엑셀 데이터를 모두 조회한다.
     * 
     * @param idList : List::UUID::
     * @return List::ErpOrderItemEntity::
     * @see ErpOrderItemRepository#qfindAllByIdList
     */
    public List<ErpOrderItemEntity> findAllByIdList(List<UUID> idList) {
        return erpOrderItemRepository.qfindAllByIdList(idList);
    }
}

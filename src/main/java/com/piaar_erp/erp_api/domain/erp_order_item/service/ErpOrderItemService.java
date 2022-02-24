package com.piaar_erp.erp_api.domain.erp_order_item.service;

import java.util.List;

import com.piaar_erp.erp_api.domain.erp_order_item.entity.ErpOrderItemEntity;
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
     * <b>DB Insert Related Method</b>
     * <p>
     * 배송준비 엑셀 파일의 하나의 데이터를 저장한다.
     *
     * @param itemEntities : List::ErpOrderItemEntity::
     * @return List::ErpOrderItemEntity::
     * @see ErpOrderItemRepository#saveAll
     */
    public List<ErpOrderItemEntity> saveItemList(List<ErpOrderItemEntity> itemEntities) {
        return erpOrderItemRepository.saveAll(itemEntities);
    }
}

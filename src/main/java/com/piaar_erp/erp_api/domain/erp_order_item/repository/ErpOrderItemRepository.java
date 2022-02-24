package com.piaar_erp.erp_api.domain.erp_order_item.repository;

import java.util.List;

import com.piaar_erp.erp_api.domain.erp_order_item.entity.ErpOrderItemEntity;
import com.piaar_erp.erp_api.domain.erp_order_item.proj.ErpOrderItemProj;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ErpOrderItemRepository extends JpaRepository<ErpOrderItemEntity, Integer>, ErpOrderItemRepositoryCustom {
   
    @Query("SELECT item AS erpOrderItem, p.defaultName AS prodDefaultName, p.managementName AS prodManagementName, po.defaultName AS optionDefaultName, po.managementName AS optionManagementName, pc.name AS categoryName FROM ErpOrderItemEntity item\n"
        + "LEFT JOIN ProductOptionEntity po ON item.optionCode = po.code\n"
        + "LEFT JOIN ProductEntity p ON po.productCid = p.cid\n"
        + "LEFT JOIN ProductCategoryEntity pc ON p.productCategoryCid = pc.cid"
    )
    List<ErpOrderItemProj> findAllMappingDataByPiaarOptionCode();
}

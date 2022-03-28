package com.piaar_erp.erp_api.domain.erp_order_item.repository;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.piaar_erp.erp_api.domain.erp_order_item.entity.ErpOrderItemEntity;
import com.piaar_erp.erp_api.domain.erp_order_item.proj.ErpOrderItemProj;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface ErpOrderItemRepositoryCustom {
    List<ErpOrderItemEntity> qfindAllByIdList(List<UUID> idList);
    List<ErpOrderItemProj> qfindAllM2OJ(Map<String, Object> params);
    Page<ErpOrderItemProj> qfindAllM2OJByPage(Map<String, Object> params, Pageable pageable);
    Page<ErpOrderItemProj> qfindReleaseItemAllM2OJByPage(Map<String, Object> params, Pageable pageable);
}

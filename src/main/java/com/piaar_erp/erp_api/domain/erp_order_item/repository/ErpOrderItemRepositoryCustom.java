package com.piaar_erp.erp_api.domain.erp_order_item.repository;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.piaar_erp.erp_api.domain.erp_order_item.entity.ErpOrderItemEntity;
import com.piaar_erp.erp_api.domain.erp_order_item.proj.ErpOrderItemProj;

import org.springframework.stereotype.Repository;

@Repository
public interface ErpOrderItemRepositoryCustom {
    List<ErpOrderItemEntity> qfindAllByIdList(List<UUID> idList);
    List<ErpOrderItemProj> qfindAllMappingDataByPiaarOptionCode(Map<String, Object> params);
}

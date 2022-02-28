package com.piaar_erp.erp_api.domain.erp_order_item.proj;

import com.piaar_erp.erp_api.domain.erp_order_item.entity.ErpOrderItemEntity;

import lombok.Getter;

@Getter
public class ErpOrderItemProj {
    ErpOrderItemEntity erpOrderItem;
    String prodDefaultName;
    String prodManagementName;
    String optionDefaultName;
    String optionManagementName;
    String categoryName;
}

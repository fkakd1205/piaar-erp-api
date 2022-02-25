package com.piaar_erp.erp_api.domain.erp_order_item.proj;

import com.piaar_erp.erp_api.domain.erp_order_item.entity.ErpOrderItemEntity;

public interface ErpOrderItemProj {
    ErpOrderItemEntity getErpOrderItem();
    String getProdDefaultName();
    String getProdManagementName();
    String getOptionDefaultName();
    String getOptionManagementName();
    String getCategoryName();
}

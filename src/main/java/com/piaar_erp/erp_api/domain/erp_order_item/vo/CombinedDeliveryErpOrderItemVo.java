package com.piaar_erp.erp_api.domain.erp_order_item.vo;

import java.util.List;

import com.piaar_erp.erp_api.domain.erp_order_item.dto.ErpOrderItemDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Builder
@Data
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class CombinedDeliveryErpOrderItemVo {
    List<ErpOrderItemDto> combinedDeliveryItems;
}

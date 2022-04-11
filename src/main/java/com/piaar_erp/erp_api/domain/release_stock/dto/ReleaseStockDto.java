package com.piaar_erp.erp_api.domain.release_stock.dto;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReleaseStockDto {
    private Integer cid;
    private UUID id;
    private Integer releaseUnit;
    private String memo;
    private LocalDateTime createdAt;
    private UUID createdBy;
    private Integer productOptionCid;
    private UUID productOptionId;
    private UUID erpOrderItemId;
}

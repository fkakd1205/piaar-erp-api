package com.piaar_erp.erp_api.domain.erp_order_header.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.piaar_erp.erp_api.domain.erp_order_header.entity.ErpOrderHeaderEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Builder
@Getter
@ToString
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class ErpOrderHeaderDto {
    private Integer cid;
    private UUID id;
    private ErpOrderHeaderDetailDto headerDetail;

    @Setter
    private LocalDateTime createdAt;

    @Setter
    private UUID createdBy;

    @Setter
    private LocalDateTime updatedAt;

    public static ErpOrderHeaderDto toDto(ErpOrderHeaderEntity entity) {
        if(entity == null) return null;

        ErpOrderHeaderDto dto = ErpOrderHeaderDto.builder()
            .cid(entity.getCid())
            .id(entity.getId())
            .headerDetail(entity.getHeaderDetail())
            .createdAt(entity.getCreatedAt())
            .createdBy(entity.getCreatedBy())
            .updatedAt(entity.getUpdatedAt())
            .build();

        return dto;
    }
}

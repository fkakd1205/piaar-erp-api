package com.piaar_erp.erp_api.domain.erp_first_merge_header.dto;

import com.piaar_erp.erp_api.domain.erp_first_merge_header.entity.ErpFirstMergeHeaderEntity;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
@ToString
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class ErpFirstMergeHeaderDto {
    private Integer cid;
    private UUID id;
    private String title;
    private ErpFirstMergeHeaderDetailDto headerDetail;

    @Setter
    private LocalDateTime createdAt;

    @Setter
    private UUID createdBy;

    @Setter
    private LocalDateTime updatedAt;

    public static ErpFirstMergeHeaderDto toDto(ErpFirstMergeHeaderEntity entity) {
        if(entity == null) return null;

        ErpFirstMergeHeaderDto dto = ErpFirstMergeHeaderDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .headerDetail(entity.getHeaderDetail())
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .updatedAt(entity.getUpdatedAt())
                .build();

        return dto;
    }
}

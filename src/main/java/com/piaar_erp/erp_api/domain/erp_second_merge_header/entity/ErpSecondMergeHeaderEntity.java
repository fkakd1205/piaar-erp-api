package com.piaar_erp.erp_api.domain.erp_second_merge_header.entity;

import com.piaar_erp.erp_api.domain.erp_second_merge_header.dto.ErpSecondMergeHeaderDetailDto;
import com.piaar_erp.erp_api.domain.erp_second_merge_header.dto.ErpSecondMergeHeaderDto;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Builder
@Getter
@Table(name = "erp_second_merge_header")
@ToString
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class ErpSecondMergeHeaderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cid")
    private Integer cid;

    @Setter
    @Type(type = "uuid-char")
    @Column(name = "id")
    private UUID id;

    @Setter
    @Column(name = "title")
    private String title;

    @Type(type = "json")
    @Column(name = "header_detail", columnDefinition = "json")
    private ErpSecondMergeHeaderDetailDto headerDetail;

    @Setter
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Setter
    @Type(type = "uuid-char")
    @Column(name = "created_by")
    private UUID createdBy;

    @Setter
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public static ErpSecondMergeHeaderEntity toEntity(ErpSecondMergeHeaderDto dto) {
        if(dto == null) return null;

        ErpSecondMergeHeaderEntity entity = ErpSecondMergeHeaderEntity.builder()
                .cid(dto.getCid())
                .id(dto.getId())
                .title(dto.getTitle())
                .headerDetail(dto.getHeaderDetail())
                .createdAt(dto.getCreatedAt())
                .createdBy(dto.getCreatedBy())
                .updatedAt(dto.getUpdatedAt())
                .build();

        return entity;
    }
}

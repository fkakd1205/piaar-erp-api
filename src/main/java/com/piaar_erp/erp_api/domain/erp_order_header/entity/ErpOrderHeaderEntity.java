package com.piaar_erp.erp_api.domain.erp_order_header.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.piaar_erp.erp_api.domain.erp_order_header.dto.ErpOrderHeaderDetailDto;
import com.piaar_erp.erp_api.domain.erp_order_header.dto.ErpOrderHeaderDto;
import com.vladmihalcea.hibernate.type.json.JsonStringType;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder
@Table(name = "erp_order_header")
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@TypeDef(name = "json", typeClass = JsonStringType.class)
public class ErpOrderHeaderEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cid")
    private Integer cid;

    @Type(type = "uuid-char")
    @Column(name = "id")
    private UUID id;

    @Type(type = "json")
    @Column(name = "header_detail", columnDefinition = "json")
    private ErpOrderHeaderDetailDto headerDetail;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Type(type = "uuid-char")
    @Column(name = "created_by")
    private UUID createdBy;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public static ErpOrderHeaderEntity toEntity(ErpOrderHeaderDto dto) {
        if(dto == null) return null;

        ErpOrderHeaderEntity entity = ErpOrderHeaderEntity.builder()
            .cid(dto.getCid())
            .id(dto.getId())
            .headerDetail(dto.getHeaderDetail())
            .createdAt(dto.getCreatedAt())
            .createdBy(dto.getCreatedBy())
            .updatedAt(dto.getUpdatedAt())
            .build();

        return entity;
    }
}

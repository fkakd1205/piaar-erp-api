package com.piaar_erp.erp_api.domain.erp_sales_header.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.piaar_erp.erp_api.domain.erp_sales_header.dto.ErpSalesHeaderDetailDto;
import com.piaar_erp.erp_api.domain.erp_sales_header.dto.ErpSalesHeaderDto;
import com.vladmihalcea.hibernate.type.json.JsonStringType;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Entity
@Builder
@Table(name = "erp_sales_header")
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TypeDef(name = "json", typeClass = JsonStringType.class)
public class ErpSalesHeaderEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cid")
    private Integer cid;

    @Type(type = "uuid-char")
    @Column(name = "id")
    private UUID id;

    @Type(type = "json")
    @Column(name = "header_detail", columnDefinition = "json")
    private ErpSalesHeaderDetailDto headerDetail;

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

    public static ErpSalesHeaderEntity toEntity(ErpSalesHeaderDto dto) {
        if(dto == null) return null;

        ErpSalesHeaderEntity entity = ErpSalesHeaderEntity.builder()
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

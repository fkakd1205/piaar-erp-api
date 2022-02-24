package com.piaar_erp.erp_api.domain.product.entity;

import com.piaar_erp.erp_api.domain.product.dto.ProductDto;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cid")
    private Integer cid;

    @Type(type = "uuid-char")
    @Column(name = "id")
    private UUID id;

    @Column(name = "code")
    private String code;

    @Column(name = "manufacturing_code")
    private String manufacturingCode;

    @Column(name = "naver_product_code")
    private String naverProductCode;

    @Column(name = "default_name")
    private String defaultName;

    @Column(name = "management_name")
    private String managementName;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "image_file_name")
    private String imageFileName;

    @Column(name = "purchase_url")
    private String purchaseUrl;

    @Column(name = "memo")
    private String memo;

    @Column(name = "hs_code")
    private String hsCode;

    @Column(name = "style")
    private String style;

    @Column(name = "tariff_rate")
    private String tariffRate;

    @Column(name = "default_width")
    private Integer defaultWidth;

    @Column(name = "default_length")
    private Integer defaultLength;

    @Column(name = "default_height")
    private Integer defaultHeight;

    @Column(name = "default_quantity")
    private Integer defaultQuantity;

    @Column(name = "default_weight")
    private Integer defaultWeight;

    @Column(name = "created_at")
    private Date createdAt;

    @Type(type = "uuid-char")
    @Column(name = "created_by")
    private UUID createdBy;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Type(type = "uuid-char")
    @Column(name = "updated_by")
    private UUID updatedBy;

    @Column(name = "stock_management")
    private Boolean stockManagement;

    @Column(name = "product_category_cid")
    private Integer productCategoryCid;

    public static ProductEntity toEntity(ProductDto productDto) {
        ProductEntity entity = ProductEntity.builder()
            .id(UUID.randomUUID())
            .code(productDto.getCode())
            .manufacturingCode(productDto.getManufacturingCode())
            .naverProductCode(productDto.getNaverProductCode())
            .defaultName(productDto.getDefaultName())
            .managementName(productDto.getManagementName())
            .imageUrl(productDto.getImageUrl())
            .imageFileName(productDto.getImageFileName())
            .purchaseUrl(productDto.getPurchaseUrl())
            .memo(productDto.getMemo())
            .hsCode(productDto.getHsCode())
            .tariffRate(productDto.getTariffRate())
            .style(productDto.getStyle())
            .tariffRate(productDto.getTariffRate())
            .defaultWidth(productDto.getDefaultWidth())
            .defaultLength(productDto.getDefaultLength())
            .defaultHeight(productDto.getDefaultHeight())
            .defaultQuantity(productDto.getDefaultQuantity())
            .defaultWeight(productDto.getDefaultWeight())
            .createdAt(productDto.getCreatedAt())
            .createdBy(productDto.getCreatedBy())
            .updatedAt(productDto.getUpdatedAt())
            .updatedBy(productDto.getUpdatedBy())
            .stockManagement(productDto.getStockManagement())
            .productCategoryCid(productDto.getProductCategoryCid())
            .build();

        return entity;
    }
}

package com.piaar_erp.erp_api.domain.product.vo;

import com.piaar_erp.erp_api.domain.product.entity.ProductEntity;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductVo {
    private Integer cid;
    private UUID id;
    private String code;
    private String manufacturingCode;
    private String naverProductCode;
    private String defaultName;
    private String managementName;
    private String imageUrl;
    private String imageFileName;
    private String purchaseUrl;
    private String memo;
    private String hsCode;
    private String style;
    private String tariffRate;
    private Integer defaultWidth;
    private Integer defaultLength;
    private Integer defaultHeight;
    private Integer defaultQuantity;
    private Integer defaultWeight;
    private Date createdAt;
    private UUID createdBy;
    private Date updatedAt;
    private UUID updatedBy;
    private Boolean stockManagement;
    private Integer productCategoryCid;

    /**
     * <b>Convert Method</b>
     * <p>
     * ProductEntity => ProductGetDto
     * 
     * @param productEntity : ProductEntity
     * @return ProductGetDto
     */
    public static ProductVo toVo(ProductEntity entity) {
        ProductVo productVo = ProductVo.builder()
            .cid(entity.getCid())
            .id(entity.getId())
            .code(entity.getCode())
            .manufacturingCode(entity.getManufacturingCode())
            .naverProductCode(entity.getNaverProductCode())
            .defaultName(entity.getDefaultName())
            .managementName(entity.getManagementName())
            .imageUrl(entity.getImageUrl())
            .purchaseUrl(entity.getPurchaseUrl())
            .imageFileName(entity.getImageFileName())
            .memo(entity.getMemo())
            .hsCode(entity.getHsCode())
            .tariffRate(entity.getTariffRate())
            .style(entity.getStyle())
            .tariffRate(entity.getTariffRate())
            .defaultWidth(entity.getDefaultWidth())
            .defaultLength(entity.getDefaultLength())
            .defaultHeight(entity.getDefaultHeight())
            .defaultQuantity(entity.getDefaultQuantity())
            .defaultWeight(entity.getDefaultWeight())
            .createdAt(entity.getCreatedAt())
            .createdBy(entity.getCreatedBy())
            .updatedAt(entity.getUpdatedAt())
            .updatedBy(entity.getUpdatedBy())
            .stockManagement(entity.getStockManagement())
            .productCategoryCid(entity.getProductCategoryCid())
            .build();

        return productVo;
    }
}

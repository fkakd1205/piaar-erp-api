package com.piaar_erp.erp_api.domain.release_stock.entity;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "release_stock")
public class ReleaseStockEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cid")
    private Integer cid;

    @Column(name = "id")
    @Type(type = "uuid-char")
    private UUID id;

    @Column(name = "release_unit")
    private Integer releaseUnit;

    @Column(name = "memo")
    private String memo;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @Column(name = "created_by")
    @Type(type ="uuid-char")
    private UUID createdBy;

    @Column(name = "product_option_cid")
    private Integer productOptionCid;

    @Column(name = "product_option_id")
    @Type(type ="uuid-char")
    private UUID productOptionId;

    @Column(name = "erp_order_item_id")
    @Type(type ="uuid-char")
    private UUID erpOrderItemId;
}

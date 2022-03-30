package com.piaar_erp.erp_api.domain.erp_order_item.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.piaar_erp.erp_api.domain.erp_order_item.dto.ErpOrderItemDto;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.Type;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Entity
@Builder
@Getter
@Table(name = "erp_order_item")
@ToString
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
public class ErpOrderItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cid")
    private Integer cid;

    @Type(type = "uuid-char")
    @Column(name = "id")
    private UUID id;

    @Column(name = "unique_code")
    private String uniqueCode; // 피아르 고유코드

    @Setter
    @Column(name = "prod_name")
    private String prodName; // 상품명 / 필수값

    @Setter
    @Column(name = "option_name")
    private String optionName; // 옵션정보 / 필수값

    @Setter
    @Column(name = "unit")
    private Integer unit; // 수량 / 필수값

    @Setter
    @Column(name = "receiver")
    private String receiver; // 수취인명 / 필수값

    @Setter
    @Column(name = "receiver_contact1")
    private String receiverContact1; // 전화번호1 / 필수값

    @Setter
    @Column(name = "receiver_contact2")
    private String receiverContact2; // 전화번호2

    @Setter
    @Column(name = "destination")
    private String destination; // 주소 / 필수값

    @Setter
    @Column(name = "sales_channel")
    private String salesChannel; // 판매채널

    @Setter
    @Column(name = "order_number1")
    private String orderNumber1; // 판매채널 주문번호1

    @Setter
    @Column(name = "order_number2")
    private String orderNumber2; // 판매채널 주문번호2

    @Setter
    @Column(name = "channel_prod_code")
    private String channelProdCode; // 판매채널 상품코드

    @Setter
    @Column(name = "channel_option_code")
    private String channelOptionCode; // 판매채널 옵션코드

    @Setter
    @Column(name = "zip_code")
    private String zipCode; // 우편번호

    @Setter
    @Column(name = "courier")
    private String courier; // 택배사

    @Setter
    @Column(name = "transport_type")
    private String transportType; // 배송방식

    @Setter
    @Column(name = "delivery_message")
    private String deliveryMessage; // 배송메세지

    @Setter
    @Column(name = "waybill_number")
    private String waybillNumber; // 운송장번호

    @Setter
    @Column(name = "price")
    private Integer price; // 판매금액

    @Setter
    @Column(name = "delivery_charge")
    private Integer deliveryCharge; // 배송비

    @Setter
    @Column(name = "barcode")
    private String barCode; // 바코드

    @Setter
    @Column(name = "prod_code")
    private String prodCode; // 피아르 상품코드

    @Setter
    @Column(name = "option_code")
    private String optionCode; // 피아르 옵션코드

    @Setter
    @Column(name = "release_option_code")
    private String releaseOptionCode; // 출고 옵션코드

    @Setter
    @Column(name = "management_memo1")
    private String managementMemo1; // 관리메모1

    @Setter
    @Column(name = "management_memo2")
    private String managementMemo2; // 관리메모2

    @Setter
    @Column(name = "management_memo3")
    private String managementMemo3; // 관리메모3

    @Setter
    @Column(name = "management_memo4")
    private String managementMemo4; // 관리메모4

    @Setter
    @Column(name = "management_memo5")
    private String managementMemo5; // 관리메모5

    @Setter
    @Column(name = "management_memo6")
    private String managementMemo6; // 관리메모6

    @Setter
    @Column(name = "management_memo7")
    private String managementMemo7; // 관리메모7

    @Setter
    @Column(name = "management_memo8")
    private String managementMemo8; // 관리메모8

    @Setter
    @Column(name = "management_memo9")
    private String managementMemo9; // 관리메모9

    @Setter
    @Column(name = "management_memo10")
    private String managementMemo10; // 관리메모10

    @Column(name = "freight_code")
    private String freightCode; // 운송코드

    @Setter
    @Column(name = "sales_yn", columnDefinition = "n")
    private String salesYn;

    @Setter
    @Column(name = "sales_at")
    private LocalDateTime salesAt;

    @Setter
    @Column(name = "release_yn", columnDefinition = "n")
    private String releaseYn;

    @Setter
    @Column(name = "release_at")
    private LocalDateTime releaseAt;

    @Setter
    @Column(name = "stock_reflect_yn", columnDefinition = "n")
    private String stockReflectYn;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Type(type = "uuid-char")
    @Column(name = "created_by")
    private UUID createdBy;

    public static ErpOrderItemEntity toEntity(ErpOrderItemDto dto) {
        ErpOrderItemEntity entity = ErpOrderItemEntity.builder()
                .cid(dto.getCid())
                .id(dto.getId())
                .uniqueCode(dto.getUniqueCode())
                .prodName(dto.getProdName())
                .optionName(dto.getOptionName())
                .unit(dto.getUnit())
                .receiver(dto.getReceiver())
                .receiverContact1(dto.getReceiverContact1())
                .receiverContact2(dto.getReceiverContact2())
                .destination(dto.getDestination())
                .salesChannel(dto.getSalesChannel())
                .orderNumber1(dto.getOrderNumber1())
                .orderNumber2(dto.getOrderNumber2())
                .channelProdCode(dto.getChannelProdCode())
                .channelOptionCode(dto.getChannelOptionCode())
                .zipCode(dto.getZipCode())
                .courier(dto.getCourier())
                .transportType(dto.getTransportType())
                .deliveryMessage(dto.getDeliveryMessage())
                .waybillNumber(dto.getWaybillNumber())
                .price(dto.getPrice())
                .deliveryCharge(dto.getDeliveryCharge())
                .barCode(dto.getBarCode())
                .prodCode(dto.getProdCode())
                .optionCode(dto.getOptionCode())
                .releaseOptionCode(dto.getReleaseOptionCode())
                .managementMemo1(dto.getManagementMemo1())
                .managementMemo2(dto.getManagementMemo2())
                .managementMemo3(dto.getManagementMemo3())
                .managementMemo4(dto.getManagementMemo4())
                .managementMemo5(dto.getManagementMemo5())
                .managementMemo6(dto.getManagementMemo6())
                .managementMemo7(dto.getManagementMemo7())
                .managementMemo8(dto.getManagementMemo8())
                .managementMemo9(dto.getManagementMemo9())
                .managementMemo10(dto.getManagementMemo10())
                .freightCode(dto.getFreightCode())
                .salesYn(dto.getSalesYn())
                .salesAt(dto.getSalesAt())
                .releaseYn(dto.getReleaseYn())
                .releaseAt(dto.getReleaseAt())
                .stockReflectYn(dto.getStockReflectYn())
                .createdAt(dto.getCreatedAt())
                .createdBy(dto.getCreatedBy())
                .build();

        return entity;
    }
}

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

    @Column(name = "order_number1")
    private String orderNumber1; // 주문번호1

    @Column(name = "order_number2")
    private String orderNumber2; // 주문번호2

    @Column(name = "order_number3")
    private String orderNumber3; // 주문번호3

    @Column(name = "prod_name")
    private String prodName; // 상품명 / 필수값

    @Column(name = "option_name")
    private String optionName; // 옵션명 / 필수값

    @Column(name = "unit")
    private Integer unit; // 수량 / 필수값

    @Column(name = "receiver")
    private String receiver; // 수취인명 / 필수값

    @Column(name = "receiver_contact1")
    private String receiverContact1; // 전화번호1 / 필수값

    @Column(name = "receiver_contact2")
    private String receiverContact2; // 전화번호2

    @Column(name = "destination")
    private String destination; // 주소 / 필수값

    @Column(name = "zip_code")
    private String zipCode; // 우편번호

    @Column(name = "transport_type")
    private String transportType; // 배송방식

    @Column(name = "delivery_message")
    private String deliveryMessage; // 배송메세지

    @Column(name = "prod_unique_number1")
    private String prodUniqueNumber1; // 상품고유번호1

    @Column(name = "prod_unique_number2")
    private String prodUniqueNumber2; // 상품고유번호2

    @Column(name = "option_unique_number1")
    private String optionUniqueNumber1; // 옵션고유번호1

    @Column(name = "option_unique_number2")
    private String optionUniqueNumber2; // 옵션고유번호2

    @Column(name = "prod_code")
    private String prodCode; // 피아르 상품코드

    @Setter
    @Column(name = "option_code")
    private String optionCode; // 피아르 옵션코드

    @Column(name = "management_memo1")
    private String managementMemo1; // 관리메모1

    @Column(name = "management_memo2")
    private String managementMemo2; // 관리메모2

    @Column(name = "management_memo3")
    private String managementMemo3; // 관리메모3

    @Column(name = "management_memo4")
    private String managementMemo4; // 관리메모4

    @Column(name = "management_memo5")
    private String managementMemo5; // 관리메모5

    @Column(name = "management_memo6")
    private String managementMemo6; // 관리메모6

    @Column(name = "management_memo7")
    private String managementMemo7; // 관리메모7

    @Column(name = "management_memo8")
    private String managementMemo8; // 관리메모8

    @Column(name = "management_memo9")
    private String managementMemo9; // 관리메모9

    @Column(name = "management_memo10")
    private String managementMemo10; // 관리메모10

    @Column(name = "management_memo11")
    private String managementMemo11; // 관리메모11

    @Column(name = "management_memo12")
    private String managementMemo12; // 관리메모12

    @Column(name = "management_memo13")
    private String managementMemo13; // 관리메모13

    @Column(name = "management_memo14")
    private String managementMemo14; // 관리메모14

    @Column(name = "management_memo15")
    private String managementMemo15; // 관리메모15

    @Column(name = "management_memo16")
    private String managementMemo16; // 관리메모16

    @Column(name = "management_memo17")
    private String managementMemo17; // 관리메모17

    @Column(name = "management_memo18")
    private String managementMemo18; // 관리메모18

    @Column(name = "management_memo19")
    private String managementMemo19; // 관리메모19

    @Column(name = "management_memo20")
    private String managementMemo20; // 관리메모20

    @Setter
    @Column(name = "sales_yn", columnDefinition = "n")
    private String salesYn;

    @Setter
    @Column(name = "sales_at")
    private LocalDateTime salesAt;

    @Setter
    @Column(name = "release_option_code")
    private String releaseOptionCode;

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
        if(dto == null) return null;

        ErpOrderItemEntity entity = ErpOrderItemEntity.builder()
                .cid(dto.getCid())
                .id(dto.getId())
                .uniqueCode(dto.getUniqueCode())
                .orderNumber1(dto.getOrderNumber1())
                .orderNumber2(dto.getOrderNumber2())
                .orderNumber3(dto.getOrderNumber3())
                .prodName(dto.getProdName())
                .optionName(dto.getOptionName())
                .unit(dto.getUnit())
                .receiver(dto.getReceiver())
                .receiverContact1(dto.getReceiverContact1())
                .receiverContact2(dto.getReceiverContact2())
                .destination(dto.getDestination())
                .zipCode(dto.getZipCode())
                .transportType(dto.getTransportType())
                .deliveryMessage(dto.getDeliveryMessage())
                .prodUniqueNumber1(dto.getProdUniqueNumber1())
                .prodUniqueNumber2(dto.getProdUniqueNumber2())
                .optionUniqueNumber1(dto.getOptionUniqueNumber1())
                .optionUniqueNumber2(dto.getOptionUniqueNumber2())
                .prodCode(dto.getProdCode())
                .optionCode(dto.getOptionCode())
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
                .managementMemo11(dto.getManagementMemo11())
                .managementMemo12(dto.getManagementMemo12())
                .managementMemo13(dto.getManagementMemo13())
                .managementMemo14(dto.getManagementMemo14())
                .managementMemo15(dto.getManagementMemo15())
                .managementMemo16(dto.getManagementMemo16())
                .managementMemo17(dto.getManagementMemo17())
                .managementMemo18(dto.getManagementMemo18())
                .managementMemo19(dto.getManagementMemo19())
                .managementMemo20(dto.getManagementMemo20())
                .salesYn(dto.getSalesYn())
                .salesAt(dto.getSalesAt())
                .releaseOptionCode(dto.getReleaseOptionCode())
                .releaseYn(dto.getReleaseYn())
                .releaseAt(dto.getReleaseAt())
                .createdAt(dto.getCreatedAt())
                .createdBy(dto.getCreatedBy())
                .stockReflectYn(dto.getStockReflectYn())
                .build();

        return entity;
    }
}

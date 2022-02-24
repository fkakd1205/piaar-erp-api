package com.piaar_erp.erp_api.domain.erp_order_item.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.piaar_erp.erp_api.domain.erp_order_item.entity.ErpOrderItemEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Builder
@Getter
@Setter
@ToString
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class ErpOrderItemDto {
    private Integer cid;
    private UUID id;
    private UUID uniqueCode;      // 피아르 고유코드
    private String orderNumber1;        // 주문번호1
    private String orderNumber2;        // 주문번호2
    private String orderNumber3;        // 주문번호3
    private String prodName;        // 상품명 / 필수값
    private String optionName;      // 옵션명 / 필수값
    private Integer unit;       // 수량 / 필수값
    private String receiver;        // 수취인명 / 필수값
    private String receiverContact1;        // 전화번호1 / 필수값
    private String receiverContact2;        // 전화번호2
    private String destination;     // 주소 / 필수값
    private String zipCode;     // 우편번호
    private String transportType;       // 배송방식
    private String deliveryMessage;     // 배송메세지
    private String prodUniqueNumber1;       // 상품고유번호1
    private String prodUniqueNumber2;       // 상품고유번호2
    private String optionUniqueNumber1;     // 옵션고유번호1
    private String optionUniqueNumber2;     // 옵션고유번호2
    private String prodCode;        // 피아르 상품코드
    private String optionCode;      // 피아르 옵션코드
    private String managementMemo1;     // 관리메모1
    private String managementMemo2;     // 관리메모2
    private String managementMemo3;     // 관리메모3
    private String managementMemo4;     // 관리메모4
    private String managementMemo5;     // 관리메모5
    private String managementMemo6;     // 관리메모6
    private String managementMemo7;     // 관리메모7
    private String managementMemo8;     // 관리메모8
    private String managementMemo9;     // 관리메모9
    private String managementMemo10;     // 관리메모10
    private String managementMemo11;     // 관리메모11
    private String managementMemo12;     // 관리메모12
    private String managementMemo13;     // 관리메모13
    private String managementMemo14;     // 관리메모14
    private String managementMemo15;     // 관리메모15
    private String managementMemo16;     // 관리메모16
    private String managementMemo17;     // 관리메모17
    private String managementMemo18;     // 관리메모18
    private String managementMemo19;     // 관리메모19
    private String managementMemo20;     // 관리메모20
    private String soldYn;
    private LocalDateTime soldAt;
    private String releasedYn;
    private LocalDateTime releasedAt;
    private String stockReflectedYn;
    private LocalDateTime createdAt;
    private UUID createdBy;

    private String prodDefaultName;
    private String prodManagementName;
    private String optionDefaultName;
    private String optionManagementName;
    private String categoryName;
    private Integer optionStockUnit;

    public static ErpOrderItemDto toDto(ErpOrderItemEntity entity) {
        ErpOrderItemDto dto = ErpOrderItemDto.builder()
                .cid(entity.getCid())
                .id(entity.getId())
                .uniqueCode(entity.getUniqueCode())
                .orderNumber1(entity.getOrderNumber1())
                .orderNumber2(entity.getOrderNumber2())
                .orderNumber3(entity.getOrderNumber3())
                .prodName(entity.getProdName())
                .optionName(entity.getOptionName())
                .unit(entity.getUnit())
                .receiver(entity.getReceiver())
                .receiverContact1(entity.getReceiverContact1())
                .receiverContact2(entity.getReceiverContact2())
                .destination(entity.getDestination())
                .zipCode(entity.getZipCode())
                .transportType(entity.getTransportType())
                .deliveryMessage(entity.getDeliveryMessage())
                .prodUniqueNumber1(entity.getProdUniqueNumber1())
                .prodUniqueNumber2(entity.getProdUniqueNumber2())
                .optionUniqueNumber1(entity.getOptionUniqueNumber1())
                .optionUniqueNumber2(entity.getOptionUniqueNumber2())
                .prodCode(entity.getProdCode())
                .optionCode(entity.getOptionCode())
                .managementMemo1(entity.getManagementMemo1())
                .managementMemo2(entity.getManagementMemo2())
                .managementMemo3(entity.getManagementMemo3())
                .managementMemo4(entity.getManagementMemo4())
                .managementMemo5(entity.getManagementMemo5())
                .managementMemo6(entity.getManagementMemo6())
                .managementMemo7(entity.getManagementMemo7())
                .managementMemo8(entity.getManagementMemo8())
                .managementMemo9(entity.getManagementMemo9())
                .managementMemo10(entity.getManagementMemo10())
                .managementMemo11(entity.getManagementMemo11())
                .managementMemo12(entity.getManagementMemo12())
                .managementMemo13(entity.getManagementMemo13())
                .managementMemo14(entity.getManagementMemo14())
                .managementMemo15(entity.getManagementMemo15())
                .managementMemo16(entity.getManagementMemo16())
                .managementMemo17(entity.getManagementMemo17())
                .managementMemo18(entity.getManagementMemo18())
                .managementMemo19(entity.getManagementMemo19())
                .managementMemo20(entity.getManagementMemo20())
                .soldYn(entity.getSoldYn())
                .soldAt(entity.getSoldAt())
                .releasedYn(entity.getReleasedYn())
                .releasedAt(entity.getReleasedAt())
                .stockReflectedYn(entity.getStockReflectedYn())
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .build();

        return dto;
    }
}

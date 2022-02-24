package com.piaar_erp.erp_api.domain.erp_order_item.vo;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Builder
@Data
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class ErpOrderItemVo {
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
    
    private String categoryName;
    private String prodDefaultName;
    private String prodManagementName;
    private String optionDefaultName;
    private String optionManagementName;
    private Integer optionStockUnit;

    private String soldYn;
    private LocalDateTime soldAt;
    private String releasedYn;
    private LocalDateTime releasedAt;
    private String stockReflectedYn;
    private LocalDateTime createdAt;
    private UUID createdBy;
    private Integer deliveryReadyFileCid;

    // public static ErpOrderItemVo toVo(ErpOrderItemProj proj) {
    //     ErpOrderItemVo itemVo = ErpOrderItemVo.builder()
    //             .id(proj.getDeliveryReadyItem().getId())
    //             .uniqueCode(proj.getDeliveryReadyItem().getUniqueCode())
    //             .orderNumber1(proj.getDeliveryReadyItem().getOrderNumber1())
    //             .orderNumber2(proj.getDeliveryReadyItem().getOrderNumber2())
    //             .orderNumber3(proj.getDeliveryReadyItem().getOrderNumber3())
    //             .prodName(proj.getDeliveryReadyItem().getProdName())
    //             .optionName(proj.getDeliveryReadyItem().getOptionName())
    //             .unit(proj.getDeliveryReadyItem().getUnit())
    //             .receiver(proj.getDeliveryReadyItem().getReceiver())
    //             .receiverContact1(proj.getDeliveryReadyItem().getReceiverContact1())
    //             .receiverContact2(proj.getDeliveryReadyItem().getReceiverContact2())
    //             .destination(proj.getDeliveryReadyItem().getDestination())
    //             .zipCode(proj.getDeliveryReadyItem().getZipCode())
    //             .transportType(proj.getDeliveryReadyItem().getTransportType())
    //             .deliveryMessage(proj.getDeliveryReadyItem().getDeliveryMessage())
    //             .prodUniqueNumber1(proj.getDeliveryReadyItem().getProdUniqueNumber1())
    //             .prodUniqueNumber2(proj.getDeliveryReadyItem().getProdUniqueNumber2())
    //             .optionUniqueNumber1(proj.getDeliveryReadyItem().getOptionUniqueNumber1())
    //             .optionUniqueNumber2(proj.getDeliveryReadyItem().getOptionUniqueNumber2())
    //             .prodCode(proj.getDeliveryReadyItem().getProdCode())
    //             .optionCode(proj.getDeliveryReadyItem().getOptionCode())
    //             .managementMemo1(proj.getDeliveryReadyItem().getManagementMemo1())
    //             .managementMemo2(proj.getDeliveryReadyItem().getManagementMemo2())
    //             .managementMemo3(proj.getDeliveryReadyItem().getManagementMemo3())
    //             .managementMemo4(proj.getDeliveryReadyItem().getManagementMemo4())
    //             .managementMemo5(proj.getDeliveryReadyItem().getManagementMemo5())
    //             .managementMemo6(proj.getDeliveryReadyItem().getManagementMemo6())
    //             .managementMemo7(proj.getDeliveryReadyItem().getManagementMemo7())
    //             .managementMemo8(proj.getDeliveryReadyItem().getManagementMemo8())
    //             .managementMemo9(proj.getDeliveryReadyItem().getManagementMemo9())
    //             .managementMemo10(proj.getDeliveryReadyItem().getManagementMemo10())
    //             .managementMemo11(proj.getDeliveryReadyItem().getManagementMemo11())
    //             .managementMemo12(proj.getDeliveryReadyItem().getManagementMemo12())
    //             .managementMemo13(proj.getDeliveryReadyItem().getManagementMemo13())
    //             .managementMemo14(proj.getDeliveryReadyItem().getManagementMemo14())
    //             .managementMemo15(proj.getDeliveryReadyItem().getManagementMemo15())
    //             .managementMemo16(proj.getDeliveryReadyItem().getManagementMemo16())
    //             .managementMemo17(proj.getDeliveryReadyItem().getManagementMemo17())
    //             .managementMemo18(proj.getDeliveryReadyItem().getManagementMemo18())
    //             .managementMemo19(proj.getDeliveryReadyItem().getManagementMemo19())
    //             .managementMemo20(proj.getDeliveryReadyItem().getManagementMemo20())
    //             .soldYn(proj.getDeliveryReadyItem().getSoldYn())
    //             .soldAt(proj.getDeliveryReadyItem().getSoldAt())
    //             .releasedYn(proj.getDeliveryReadyItem().getReleasedYn())
    //             .releasedAt(proj.getDeliveryReadyItem().getReleasedAt())
    //             .stockReflectedYn(proj.getDeliveryReadyItem().getStockReflectedYn())
    //             .createdAt(proj.getDeliveryReadyItem().getCreatedAt())
    //             .createdBy(proj.getDeliveryReadyItem().getCreatedBy())
    //             .categoryName(proj.getCategoryName())
    //             .prodDefaultName(proj.getProdDefaultName())
    //             .prodManagementName(proj.getProdManagementName())
    //             .optionDefaultName(proj.getOptionDefaultName())
    //             .optionManagementName(proj.getOptionManagementName())
    //             .build();

    //     return itemVo;
    // }
}

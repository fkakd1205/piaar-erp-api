package com.piaar_erp.erp_api.domain.erp_order_item.vo;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.piaar_erp.erp_api.domain.erp_order_item.dto.ErpOrderItemDto;
import com.piaar_erp.erp_api.domain.erp_order_item.proj.ErpOrderItemProj;

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
    private String uniqueCode;      // 피아르 고유코드
    private String orderNumber1;        // 주문번호1
    private String orderNumber2;        // 주문번호2
    private String orderNumber3;        // 주문번호3
    private String prodName;        // 상품명 / 필수값
    private String optionName;      // 옵션명 / 필수값
    private String unit;       // 수량 / 필수값
    // private Integer unit;       // 수량 / 필수값
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
    private String optionStockUnit;

    private String salesYn;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime salesAt;
    private String releaseOptionCode;
    private String releaseYn;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime releaseAt;
    private String stockReflectYn;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime createdAt;
    private UUID createdBy;

    public static ErpOrderItemVo toVo(ErpOrderItemProj proj) {
        if(proj == null) return null;

        ErpOrderItemVo itemVo = ErpOrderItemVo.builder()
                .id(proj.getErpOrderItem().getId())
                .uniqueCode(proj.getErpOrderItem().getUniqueCode())
                .orderNumber1(proj.getErpOrderItem().getOrderNumber1())
                .orderNumber2(proj.getErpOrderItem().getOrderNumber2())
                .orderNumber3(proj.getErpOrderItem().getOrderNumber3())
                .prodName(proj.getErpOrderItem().getProdName())
                .optionName(proj.getErpOrderItem().getOptionName())
                .unit(proj.getErpOrderItem().getUnit().toString())
                .receiver(proj.getErpOrderItem().getReceiver())
                .receiverContact1(proj.getErpOrderItem().getReceiverContact1())
                .receiverContact2(proj.getErpOrderItem().getReceiverContact2())
                .destination(proj.getErpOrderItem().getDestination())
                .zipCode(proj.getErpOrderItem().getZipCode())
                .transportType(proj.getErpOrderItem().getTransportType())
                .deliveryMessage(proj.getErpOrderItem().getDeliveryMessage())
                .prodUniqueNumber1(proj.getErpOrderItem().getProdUniqueNumber1())
                .prodUniqueNumber2(proj.getErpOrderItem().getProdUniqueNumber2())
                .optionUniqueNumber1(proj.getErpOrderItem().getOptionUniqueNumber1())
                .optionUniqueNumber2(proj.getErpOrderItem().getOptionUniqueNumber2())
                .prodCode(proj.getErpOrderItem().getProdCode())
                .optionCode(proj.getErpOrderItem().getOptionCode())
                .managementMemo1(proj.getErpOrderItem().getManagementMemo1())
                .managementMemo2(proj.getErpOrderItem().getManagementMemo2())
                .managementMemo3(proj.getErpOrderItem().getManagementMemo3())
                .managementMemo4(proj.getErpOrderItem().getManagementMemo4())
                .managementMemo5(proj.getErpOrderItem().getManagementMemo5())
                .managementMemo6(proj.getErpOrderItem().getManagementMemo6())
                .managementMemo7(proj.getErpOrderItem().getManagementMemo7())
                .managementMemo8(proj.getErpOrderItem().getManagementMemo8())
                .managementMemo9(proj.getErpOrderItem().getManagementMemo9())
                .managementMemo10(proj.getErpOrderItem().getManagementMemo10())
                .managementMemo11(proj.getErpOrderItem().getManagementMemo11())
                .managementMemo12(proj.getErpOrderItem().getManagementMemo12())
                .managementMemo13(proj.getErpOrderItem().getManagementMemo13())
                .managementMemo14(proj.getErpOrderItem().getManagementMemo14())
                .managementMemo15(proj.getErpOrderItem().getManagementMemo15())
                .managementMemo16(proj.getErpOrderItem().getManagementMemo16())
                .managementMemo17(proj.getErpOrderItem().getManagementMemo17())
                .managementMemo18(proj.getErpOrderItem().getManagementMemo18())
                .managementMemo19(proj.getErpOrderItem().getManagementMemo19())
                .managementMemo20(proj.getErpOrderItem().getManagementMemo20())
                .releaseOptionCode(proj.getErpOrderItem().getReleaseOptionCode())
                .salesYn(proj.getErpOrderItem().getSalesYn())
                .salesAt(proj.getErpOrderItem().getSalesAt())
                .releaseYn(proj.getErpOrderItem().getReleaseYn())
                .releaseAt(proj.getErpOrderItem().getReleaseAt())
                .stockReflectYn(proj.getErpOrderItem().getStockReflectYn())
                .createdAt(proj.getErpOrderItem().getCreatedAt())
                .createdBy(proj.getErpOrderItem().getCreatedBy())
                .categoryName(proj.getProductCategory() != null ? proj.getProductCategory().getName() : "")
                .prodDefaultName(proj.getProduct() != null ? proj.getProduct().getDefaultName() : "")
                .prodManagementName(proj.getProduct() != null ? proj.getProduct().getManagementName() : "")
                .optionDefaultName(proj.getProductOption() != null ? proj.getProductOption().getDefaultName() : "")
                .optionManagementName(proj.getProductOption() != null ? proj.getProductOption().getManagementName() :"")
                .build();

        return itemVo;
    }

    public static ErpOrderItemVo toVo(ErpOrderItemDto dto) {
        if(dto == null) return null;

        ErpOrderItemVo itemVo = ErpOrderItemVo.builder()
                .id(dto.getId())
                .uniqueCode(dto.getUniqueCode())
                .orderNumber1(dto.getOrderNumber1())
                .orderNumber2(dto.getOrderNumber2())
                .orderNumber3(dto.getOrderNumber3())
                .prodName(dto.getProdName())
                .optionName(dto.getOptionName())
                .unit(dto.getUnit().toString())
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
                .releaseOptionCode(dto.getReleaseOptionCode())
                .salesYn(dto.getSalesYn())
                .salesAt(dto.getSalesAt())
                .releaseYn(dto.getReleaseYn())
                .releaseAt(dto.getReleaseAt())
                .stockReflectYn(dto.getStockReflectYn())
                .createdAt(dto.getCreatedAt())
                .createdBy(dto.getCreatedBy())
                .categoryName(dto.getCategoryName())
                .prodDefaultName(dto.getProdDefaultName())
                .prodManagementName(dto.getProdManagementName())
                .optionDefaultName(dto.getOptionDefaultName())
                .optionManagementName(dto.getOptionManagementName())
                .build();

        return itemVo;
    }
}

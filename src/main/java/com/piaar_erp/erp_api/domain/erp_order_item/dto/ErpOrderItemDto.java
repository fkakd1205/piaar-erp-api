package com.piaar_erp.erp_api.domain.erp_order_item.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

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
    
    @Size(max = 36)
    private String uniqueCode;      // 피아르 고유코드

    @Size(max = 36)
    private String orderNumber1;        // 주문번호1
    
    @Size(max = 36)
    private String orderNumber2;        // 주문번호2

    @Size(max = 36)
    private String orderNumber3;        // 주문번호3

    @Size(max = 300)
    private String prodName;        // 상품명 / 필수값
    
    @Size(max = 300)
    private String optionName;      // 옵션명 / 필수값

    @PositiveOrZero
    private Integer unit;       // 수량 / 필수값
    
    @Size(max = 20)
    private String receiver;        // 수취인명 / 필수값

    @Size(max = 20)
    private String receiverContact1;        // 전화번호1 / 필수값

    @Size(max = 20)
    private String receiverContact2;        // 전화번호2

    @Size(max = 200)
    private String destination;     // 주소 / 필수값

    @Size(max = 10)
    private String zipCode;     // 우편번호

    @Size(max = 45)
    private String transportType;       // 배송방식

    @Size(max = 200)
    private String deliveryMessage;     // 배송메세지

    @Size(max = 36)
    private String prodUniqueNumber1;       // 상품고유번호1

    @Size(max = 36)
    private String prodUniqueNumber2;       // 상품고유번호2

    @Size(max = 36)
    private String optionUniqueNumber1;     // 옵션고유번호1

    @Size(max = 36)
    private String optionUniqueNumber2;     // 옵션고유번호2

    @Size(max = 20)
    private String prodCode;        // 피아르 상품코드

    @Size(max = 20)
    private String optionCode;      // 피아르 옵션코드

    @Size(max = 200)
    private String managementMemo1;     // 관리메모1

    @Size(max = 200)
    private String managementMemo2;     // 관리메모2

    @Size(max = 200)
    private String managementMemo3;     // 관리메모3

    @Size(max = 200)
    private String managementMemo4;     // 관리메모4

    @Size(max = 200)
    private String managementMemo5;     // 관리메모5
    
    @Size(max = 200)
    private String managementMemo6;     // 관리메모6
    
    @Size(max = 200)
    private String managementMemo7;     // 관리메모7
    
    @Size(max = 200)
    private String managementMemo8;     // 관리메모8
    
    @Size(max = 200)
    private String managementMemo9;     // 관리메모9
    
    @Size(max = 200)
    private String managementMemo10;     // 관리메모10
    
    @Size(max = 200)
    private String managementMemo11;     // 관리메모11
    
    @Size(max = 200)
    private String managementMemo12;     // 관리메모12
    
    @Size(max = 200)
    private String managementMemo13;     // 관리메모13
    
    @Size(max = 200)
    private String managementMemo14;     // 관리메모14
    
    @Size(max = 200)
    private String managementMemo15;     // 관리메모15
    
    @Size(max = 200)
    private String managementMemo16;     // 관리메모16
    
    @Size(max = 200)
    private String managementMemo17;     // 관리메모17
    
    @Size(max = 200)
    private String managementMemo18;     // 관리메모18
    
    @Size(max = 200)
    private String managementMemo19;     // 관리메모19
    
    @Size(max = 200)
    private String managementMemo20;     // 관리메모20
    
    @Size(max = 1)
    private String salesYn;
    
    private LocalDateTime salesAt;

    @Size(max = 20)
    private String releaseOptionCode;
    
    @Size(max = 1)
    private String releaseYn;
    
    private LocalDateTime releaseAt;
    
    @Size(max = 1)
    private String stockReflectYn;
    
    private LocalDateTime createdAt;
    private UUID createdBy;

    private String prodDefaultName;
    private String prodManagementName;
    private String optionDefaultName;
    private String optionManagementName;
    private String categoryName;
    private Integer optionStockUnit;

    public static ErpOrderItemDto toDto(ErpOrderItemEntity entity) {
        if(entity == null) return null;

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
                .salesYn(entity.getSalesYn())
                .salesAt(entity.getSalesAt())
                .releaseOptionCode(entity.getReleaseOptionCode())
                .releaseYn(entity.getReleaseYn())
                .releaseAt(entity.getReleaseAt())
                .stockReflectYn(entity.getStockReflectYn())
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .build();

        return dto;
    }
}

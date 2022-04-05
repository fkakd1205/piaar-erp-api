package com.piaar_erp.erp_api.domain.erp_order_item.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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

//    @NotNull
//    @NotBlank
//    @Size(max = 36)
    private String uniqueCode; // 피아르 고유코드

    @NotNull
    @NotBlank
    @Size(max = 300)
    private String prodName; // 상품명 / 필수값

    @NotNull
    @NotBlank
    @Size(max = 300)
    private String optionName; // 옵션정보 / 필수값

    @NotNull
    @PositiveOrZero
    private Integer unit; // 수량 / 필수값

    @NotNull
    @NotBlank
    @Size(max = 20)
    private String receiver; // 수취인명 / 필수값

    @NotBlank
    @Size(max = 20)
    private String receiverContact1; // 전화번호1 / 필수값

    @Size(max = 20)
    private String receiverContact2; // 전화번호2

    @NotNull
    @NotBlank
    @Size(max = 200)
    private String destination; // 주소 / 필수값

    @Size(max = 40)
    private String salesChannel; // 판매채널

    @Size(max = 36)
    private String orderNumber1; // 판매채널 주문번호1

    @Size(max = 36)
    private String orderNumber2; // 판매채널 주문번호2

    @Size(max = 36)
    private String channelProdCode; // 판매채널 상품코드

    @Size(max = 36)
    private String channelOptionCode; // 판매채널 옵션코드

    @Size(max = 10)
    private String zipCode; // 우편번호

    @Size(max = 40)
    private String courier; // 택배사

    @Size(max = 45)
    private String transportType; // 배송방식

    @Size(max = 200)
    private String deliveryMessage; // 배송메세지

    @Size(max = 36)
    private String waybillNumber;   // 운송장번호

    @PositiveOrZero
    private Integer price;  // 판매금액

    @PositiveOrZero
    private Integer deliveryCharge;  // 배송비

    @Size(max = 100)
    private String barCode; // 바코드

    @Size(max = 20)
    private String prodCode; // 피아르 상품코드

    @Size(max = 20)
    private String optionCode; // 피아르 옵션코드

    @Size(max = 20)
    private String releaseOptionCode;   // 출고 옵션코드

    @Size(max = 200)
    private String managementMemo1; // 관리메모1
    
    @Size(max = 200)
    private String managementMemo2; // 관리메모2
    
    @Size(max = 200)
    private String managementMemo3; // 관리메모3
    
    @Size(max = 200)
    private String managementMemo4; // 관리메모4
    
    @Size(max = 200)
    private String managementMemo5; // 관리메모5
    
    @Size(max = 200)
    private String managementMemo6; // 관리메모6
    
    @Size(max = 200)
    private String managementMemo7; // 관리메모7
    
    @Size(max = 200)
    private String managementMemo8; // 관리메모8
    
    @Size(max = 200)
    private String managementMemo9; // 관리메모9
    
    @Size(max = 200)
    private String managementMemo10; // 관리메모10
    
    @Size(max = 200)
    private String freightCode; // 운송코드

    @Setter
    private String salesYn;  // 판매등록일

    @Setter
    private LocalDateTime salesAt;

    @Setter
    private String releaseYn;

    private LocalDateTime releaseAt;   // 출고등록일
    private String stockReflectYn;
    private LocalDateTime createdAt;  // 주문등록일
    private UUID createdBy;

    private String categoryName;    // 피아르 카테고리명
    private String prodDefaultName; // 피아르 상품명
    private String prodManagementName;  // 피아르 상품관리명
    private String optionDefaultName;   // 피아르 옵션명
    private String optionManagementName;    // 피아르 옵션관리명
    private Integer optionStockUnit;    // 재고수량

    private Integer salesPrice; // 판매금액
    private Integer receiverDeliveryCharge;  // 소비자 부담 운임비
    private Integer purchaseCost;    // 매입금액
    private Integer purchaseDeliveryCharge; // 판매자 부담 운임비
    private Integer sellerDeliveryCharge;   // 판매자 부담 운임비
    private Integer extraCost;  // 기타비용
    private Integer commission; // 판매채널 수수료

    public static ErpOrderItemDto toDto(ErpOrderItemEntity entity) {
        ErpOrderItemDto dto = ErpOrderItemDto.builder()
                .cid(entity.getCid())
                .id(entity.getId())
                .uniqueCode(entity.getUniqueCode())
                .prodName(entity.getProdName())
                .optionName(entity.getOptionName())
                .unit(entity.getUnit())
                .receiver(entity.getReceiver())
                .receiverContact1(entity.getReceiverContact1())
                .receiverContact2(entity.getReceiverContact2())
                .destination(entity.getDestination())
                .salesChannel(entity.getSalesChannel())
                .orderNumber1(entity.getOrderNumber1())
                .orderNumber2(entity.getOrderNumber2())
                .channelProdCode(entity.getChannelProdCode())
                .channelOptionCode(entity.getChannelOptionCode())
                .zipCode(entity.getZipCode())
                .courier(entity.getCourier())
                .transportType(entity.getTransportType())
                .deliveryMessage(entity.getDeliveryMessage())
                .waybillNumber(entity.getWaybillNumber())
                .price(entity.getPrice())
                .deliveryCharge(entity.getDeliveryCharge())
                .barCode(entity.getBarCode())
                .prodCode(entity.getProdCode())
                .optionCode(entity.getOptionCode())
                .releaseOptionCode(entity.getReleaseOptionCode())
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
                .freightCode(entity.getFreightCode())
                .salesYn(entity.getSalesYn())
                .salesAt(entity.getSalesAt())
                .releaseYn(entity.getReleaseYn())
                .releaseAt(entity.getReleaseAt())
                .stockReflectYn(entity.getStockReflectYn())
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .build();

        return dto;
    }
}

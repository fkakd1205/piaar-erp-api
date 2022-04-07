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
    private String uniqueCode; // 피아르 고유코드
    private String prodName; // 상품명 / 필수값
    private String optionName; // 옵션정보 / 필수값
    private String unit; // 수량 / 필수값
    private String receiver; // 수취인명 / 필수값
    private String receiverContact1; // 전화번호1 / 필수값
    private String receiverContact2; // 전화번호2
    private String destination; // 주소 / 필수값
    private String salesChannel; // 판매채널
    private String orderNumber1; // 판매채널 주문번호1
    private String orderNumber2; // 판매채널 주문번호2
    private String channelProdCode; // 판매채널 상품코드
    private String channelOptionCode; // 판매채널 옵션코드
    private String zipCode; // 우편번호
    private String courier; // 택배사
    private String transportType; // 배송방식
    private String deliveryMessage; // 배송메세지
    private String waybillNumber;   // 운송장번호
    private String price;  // 판매금액
    private String deliveryCharge;  // 배송비
    private String barcode; // 바코드
    private String prodCode; // 피아르 상품코드
    private String optionCode; // 피아르 옵션코드
    private String releaseOptionCode;   // 출고 옵션코드
    private String managementMemo1; // 관리메모1
    private String managementMemo2; // 관리메모2
    private String managementMemo3; // 관리메모3
    private String managementMemo4; // 관리메모4
    private String managementMemo5; // 관리메모5
    private String managementMemo6; // 관리메모6
    private String managementMemo7; // 관리메모7
    private String managementMemo8; // 관리메모8
    private String managementMemo9; // 관리메모9
    private String managementMemo10; // 관리메모10
    private String freightCode; // 운송코드

    private String salesYn;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime salesAt;

    private String releaseYn;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime releaseAt;

    private String stockReflectYn;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime createdAt;

    private UUID createdBy;

    private String categoryName;
    private String prodDefaultName;
    private String prodManagementName;
    private String optionDefaultName;
    private String optionManagementName;
    private String optionStockUnit;

    public static ErpOrderItemVo toVo(ErpOrderItemProj proj) {
        if (proj == null)
            return null;

        ErpOrderItemVo itemVo = ErpOrderItemVo.builder()
                .id(proj.getErpOrderItem().getId())
                .uniqueCode(proj.getErpOrderItem().getUniqueCode())
                .prodName(proj.getErpOrderItem().getProdName())
                .optionName(proj.getErpOrderItem().getOptionName())
                .unit(proj.getErpOrderItem().getUnit().toString())
                .receiver(proj.getErpOrderItem().getReceiver())
                .receiverContact1(proj.getErpOrderItem().getReceiverContact1())
                .receiverContact2(proj.getErpOrderItem().getReceiverContact2())
                .destination(proj.getErpOrderItem().getDestination())
                .salesChannel(proj.getErpOrderItem().getSalesChannel())
                .orderNumber1(proj.getErpOrderItem().getOrderNumber1())
                .orderNumber2(proj.getErpOrderItem().getOrderNumber2())
                .channelProdCode(proj.getErpOrderItem().getChannelProdCode())
                .channelOptionCode(proj.getErpOrderItem().getChannelOptionCode())
                .zipCode(proj.getErpOrderItem().getZipCode())
                .courier(proj.getErpOrderItem().getCourier())
                .transportType(proj.getErpOrderItem().getTransportType())
                .deliveryMessage(proj.getErpOrderItem().getDeliveryMessage())
                .waybillNumber(proj.getErpOrderItem().getWaybillNumber())
                .price(proj.getErpOrderItem().getPrice() != null ? proj.getErpOrderItem().getPrice().toString() : null)
                .deliveryCharge(proj.getErpOrderItem().getDeliveryCharge() != null ? proj.getErpOrderItem().getDeliveryCharge().toString() : null)
                .barcode(proj.getErpOrderItem().getBarcode())
                .prodCode(proj.getErpOrderItem().getProdCode())
                .optionCode(proj.getErpOrderItem().getOptionCode())
                .releaseOptionCode(proj.getErpOrderItem().getReleaseOptionCode())
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
                .freightCode(proj.getErpOrderItem().getFreightCode())
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
                .optionManagementName(proj.getProductOption() != null ? proj.getProductOption().getManagementName() : "")
                .build();

        return itemVo;
    }

    public static ErpOrderItemVo toVo(ErpOrderItemDto dto) {
        if(dto == null) return null;

        ErpOrderItemVo itemVo = ErpOrderItemVo.builder()
                .id(dto.getId())
                .uniqueCode(dto.getUniqueCode() != null ? dto.getUniqueCode().toString() : null)
                .prodName(dto.getProdName())
                .optionName(dto.getOptionName())
                .unit(dto.getUnit() != null ? dto.getUnit().toString() : null)
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
                .price(dto.getPrice() != null ? dto.getPrice().toString() : null)
                .deliveryCharge(dto.getDeliveryCharge() != null ? dto.getDeliveryCharge().toString() : null)
                .barcode(dto.getBarcode())
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

        return itemVo;
    }
}

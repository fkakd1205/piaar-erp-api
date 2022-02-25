package com.piaar_erp.erp_api.domain.product_option.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReceiveReleaseSumOnlyDto {
    private Integer optionCid;      // ProductOption cid
    private Integer receivedSum;        // option cid에 대응하는 입고데이터의 수량합
    private Integer releasedSum;        // option cid에 대응하는 출고데이터의 수량합
}

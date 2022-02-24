package com.piaar_erp.erp_api.domain.erp_order_header.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class DetailDto {
    private Integer cellNumber;
    private String cellValue;
    private String matchedColumnName;
    private String mergeYn;
}

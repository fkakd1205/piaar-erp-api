package com.piaar_erp.erp_api.domain.erp_first_merge_header.dto;

import lombok.Getter;

@Getter
public class DetailDto {
    private Integer cellNumber;
    private String originCellName;
    private String customCellName;
    private String matchedColumnName;
    private String mergeYn;
    private String fixedValue;
}

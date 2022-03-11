package com.piaar_erp.erp_api.domain.erp_second_merge_header.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErpSecondMergeHeaderDetailDto {
    @Type(type = "jsonb")
    private List<DetailDto> details;
}

package com.piaar_erp.erp_api.domain.erp_first_merge_header.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import org.hibernate.annotations.Type;

import java.util.List;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErpFirstMergeHeaderDetailDto {
    @Type(type = "jsonb")
    private List<DetailDto> details;
}

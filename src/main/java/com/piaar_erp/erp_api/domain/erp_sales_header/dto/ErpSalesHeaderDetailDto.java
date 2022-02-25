package com.piaar_erp.erp_api.domain.erp_sales_header.dto;

import java.util.List;

import org.hibernate.annotations.Type;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErpSalesHeaderDetailDto {
    @Type(type = "jsonb")
    private List<DetailDto> details;
}

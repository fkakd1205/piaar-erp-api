package com.piaar_erp.erp_api.domain.excel_form.waybill;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WaybillExcelFormDto {
    private String receiver;
    private String freightCode;
    private String waybillNumber;
    private String transportType;
    private String courier;
}

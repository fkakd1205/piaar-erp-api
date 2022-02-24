package com.piaar_erp.erp_api.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CustomDateUtils {
    public static LocalDateTime getCurrentDateTime(){
        return LocalDateTime.now();
    }

    public static String getCurrentKRDate2yyyyMMddHHmmss_SSS(){
        LocalDateTime currentDateTime = LocalDateTime.now();

        String result = currentDateTime.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss_SSS"));
        return result;
    }
}

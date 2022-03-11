package com.piaar_erp.erp_api.utils;

import org.apache.commons.lang3.RandomStringUtils;

public class CustomUniqueKeyUtils {
//    total : 18 characters => splitCode(1) + currentMillisecondsString(8) + nanoTime(8) + randomString(1)
    public static String generateKey() {
        StringBuilder sb = new StringBuilder();
        String splitCode = "p";
        String currentMillisStr = Long.toString(getCurrentMillis(), 36).substring(0,8);
        String nanoTimeStr = Long.toString(getNanoTime(), 36).substring(0,8);
        String randomNumericStr = getRandomNumeric(1);

        sb.append(splitCode);
        sb.append(randomNumericStr);
        sb.append(currentMillisStr);
        sb.append(nanoTimeStr);

        return sb.toString();
    }

    public static Long getCurrentMillis() {
        Long l = System.currentTimeMillis();
        return l;
    }

    public static Long getNanoTime() {
        Long l = System.nanoTime();
        return l;
    }

    public static String getRandomNumeric(int length) {
        return RandomStringUtils.randomNumeric(length);
    }
}

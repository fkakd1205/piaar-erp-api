package com.piaar_erp.erp_api.utils;

import java.util.Random;

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

    // total : 4 random characters => upper case, lower case, Number 1~9
    public static String generateFreightCode() {
        int[] NUMBER_BOUND = {49, 57};     // asci 49~57 => number 1~9
        int[] UPPER_CASE_BOUND = {65, 90};      // asci 65~90 => upper case A~Z
        int[] LOWER_CASE_BOUND = {97, 122};     // asci 97~122 => lower case a~z

        int targetLength = 4;
        Random random = new Random();
        String generatedCode = random.ints(NUMBER_BOUND[0], LOWER_CASE_BOUND[1]+1)
                            .filter(i -> (i <= NUMBER_BOUND[1] || i >= UPPER_CASE_BOUND[0]) && (i <= UPPER_CASE_BOUND[1] || i >= LOWER_CASE_BOUND[0]))
                            .limit(targetLength)
                            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                            .toString();

        return generatedCode;
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

package com.lixiong.straight.common.utils;

public class StringUtil {

    public static boolean isEmpty(String str) {
        if (null == str || str.trim().equals("") || str.trim().equalsIgnoreCase("null")) {
            return true;
        }
        return false;
    }
}

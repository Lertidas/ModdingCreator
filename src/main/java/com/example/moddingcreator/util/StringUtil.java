package com.example.moddingcreator.util;

public class StringUtil {
    public static String convertToClassString(String s) {
        String secondPart = s.substring(1);
        secondPart = secondPart.toLowerCase();
        return Character.toUpperCase(s.charAt(0)) + secondPart;
    }
}

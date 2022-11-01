package com.example.moddingcreator.util;

public class StringUtil {
    public static String convertToClassString(String string) {
        String secondPart = string.substring(1);
        secondPart = secondPart.toLowerCase();
        return Character.toUpperCase(string.charAt(0)) + secondPart;
    }

    public static String getCamelRepresentation(String string) {
        String[] splitValues = string.split(" ");
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : splitValues) {
            stringBuilder.append(Character.toUpperCase(s.charAt(0))).append(s.substring(1));
        }
        return stringBuilder.toString();
    }

    public static String getUnderscoreRepresentation() {

    }
}

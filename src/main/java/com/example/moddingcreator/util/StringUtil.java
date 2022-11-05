package com.example.moddingcreator.util;

public class StringUtil {
    public static String convertToClassString(String string) {
        String secondPart = string.substring(1);
        secondPart = secondPart.toLowerCase();
        return Character.toUpperCase(string.charAt(0)) + secondPart;
    }

    public static String getClassCamelRepresentation(String string) {
        String[] splitValues = string.split(" ");
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : splitValues) {
            if (!"".equals(s)) {
                stringBuilder.append(Character.toUpperCase(s.charAt(0))).append(s.substring(1).toLowerCase());
            }
        }
        return stringBuilder.toString();
    }

    public static String getVariableCamelRepresentation(String string) {
        String[] splitValues = string.split(" ");
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : splitValues) {
            if (!"".equals(s)) {
                if (stringBuilder.isEmpty()) {
                    stringBuilder.append(s.toLowerCase());
                }
                else {
                    stringBuilder.append(Character.toUpperCase(s.charAt(0))).append(s.substring(1).toLowerCase());
                }
            }
        }
        return stringBuilder.toString();
    }

    public static String getUnderscoreRepresentation(String string) {
        String[] splitValues = string.split(" ");
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < splitValues.length; i++) {
            if (!"".equals(splitValues[i])) {
                stringBuilder.append(splitValues[i].toLowerCase());
                if (i != splitValues.length - 1) {
                    stringBuilder.append("_");
                }
            }
        }
        return stringBuilder.toString();
    }
}

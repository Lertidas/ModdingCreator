package com.example.moddingcreator.data;

public class InstanceData {
    public static final String modOutputPath = "output/createdmods/";
    public static String modName = "";
    public static String modid = "";
    public static String modJavaPath = "";
    public static String modResourcePath = "";

    public static void setupModInstanceData(String name, String id) {
        modName = name;
        modid = id;
        modJavaPath = "output/createdmods/" + modName + "/src/main/java/";
        modResourcePath = "output/createdmods/" + modName + "/src/main/resources/";
    }
}

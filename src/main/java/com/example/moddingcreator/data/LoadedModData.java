package com.example.moddingcreator.data;

public class LoadedModData {
    public static String modName = "";
    public static String modid = "";
    public static String modPath = "";
    public static String modJavaPath = "";
    public static String modResourcePath = "";

    public static void setupModInstanceData(String name, String id) {
        modName = name;
        modid = id;
        modPath = "output/createdmods/" + modName;
        modJavaPath = "output/createdmods/" + modName + "/src/main/java/";
        modResourcePath = "output/createdmods/" + modName + "/src/main/resources/";
    }

    public static void resetModInstanceData() {
        modName = "";
        modid = "";
        modPath = "";
        modJavaPath = "";
        modResourcePath = "";
    }
}

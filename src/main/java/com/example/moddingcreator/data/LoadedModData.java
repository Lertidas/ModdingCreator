package com.example.moddingcreator.data;

public class LoadedModData {
    public static String modName = "";
    public static String modid = "";
    public static String modPath = "";
    public static String modJavaPath = "";
    public static String modResourcePath = "";
    public static String modClassesPath = "";
    public static String modBlockClassPath = "";
    public static String modItemClassPath = "";

    public static void setupModInstanceData(String name, String id) {
        modName = name;
        modid = id;
        modPath = "output/createdmods/" + modName;
        modJavaPath = "output/createdmods/" + modName + "/src/main/java/";
        modResourcePath = "output/createdmods/" + modName + "/src/main/resources/";
        modClassesPath = modJavaPath + "com/example/" + modid + "/";
        modBlockClassPath = modClassesPath + "blocks/";
        modItemClassPath = modClassesPath + "items/";
    }

    public static void resetModInstanceData() {
        modName = "";
        modid = "";
        modPath = "";
        modJavaPath = "";
        modResourcePath = "";
        modClassesPath = "";
        modBlockClassPath = "";
        modItemClassPath = "";
    }
}

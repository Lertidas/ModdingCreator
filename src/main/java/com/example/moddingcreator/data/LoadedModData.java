package com.example.moddingcreator.data;

public class LoadedModData {
    public static String modName = "";
    public static String modid = "";
    public static String modPath = "";
    public static String modJavaPath = "";
    public static String modResourcePath = "";
    public static String modDataPath = "";
    public static String modClassesPath = "";
    public static String modBlockClassPath = "";
    public static String modItemClassPath = "";
    public static String modBlockItemClassPath = "";
    public static String modidFilePath = "";
    public static String resourcesModidPath = "";
    public static String dataModidPath = "";

    // Import Paths
    public static String importClassPath = "";
    public static String importBlockClassPath = "";
    public static String importBlockItemClassPath = "";
    public static String importItemClassPath = "";

    public static void setupModInstanceData(String name, String id) {
        modName = name;
        modid = id;
        modPath = "output/createdmods/" + modName;
        modJavaPath = "output/createdmods/" + modName + "/src/main/java/";
        modResourcePath = "output/createdmods/" + modName + "/src/main/resources/";
        modDataPath = "output/createdmods/" + modName + "/src/main/data/";
        modClassesPath = modJavaPath + "com/example/" + modid + "/";
        modBlockClassPath = modClassesPath + "block/";
        modItemClassPath = modClassesPath + "item/";
        modBlockItemClassPath = modClassesPath + "blockItem/";
        modidFilePath = modClassesPath + modid + ".java";
        resourcesModidPath = modResourcePath + "assets/" + modid + "/";
        dataModidPath = modResourcePath + "data/" + modid + "/";

        importClassPath = "com.example." + modid + ".";
        importBlockClassPath = importClassPath + "block.";
        importBlockItemClassPath = importClassPath + "blockItem.";
        importItemClassPath = importClassPath + "item.";
    }

    public static void resetModInstanceData() {
        modName = "";
        modid = "";
        modPath = "";
        modJavaPath = "";
        modResourcePath = "";
        modDataPath = "";
        modClassesPath = "";
        modBlockClassPath = "";
        modItemClassPath = "";
        modBlockItemClassPath = "";
        resourcesModidPath = "";
        dataModidPath = "";

        importClassPath = "";
        importBlockClassPath = "";
        importBlockItemClassPath = "";
        importItemClassPath = "";
    }
}

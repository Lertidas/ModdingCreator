package com.example.moddingcreator.creators;

import com.example.moddingcreator.data.FileData;
import com.example.moddingcreator.data.ImageData;
import com.example.moddingcreator.data.LoadedModData;
import com.example.moddingcreator.data.StringLocatorData;
import com.example.moddingcreator.enums.AccessLevelModifier;
import com.example.moddingcreator.enums.EntityType;
import com.example.moddingcreator.models.*;
import com.example.moddingcreator.util.FileUtil;
import com.example.moddingcreator.util.XmlUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ItemCreator {
    public static void createItem(String itemName, String className, String variableName, String jsonName) throws IOException {

        // Create Item File for given parameters
        createItemClassFile(className);

        // Register item to ITEMS in modid.java
        registerItem(className, variableName, jsonName);

        // Setup item model JSON
        ItemModelModel itemModelModel = new ItemModelModel("item/generated", jsonName, EntityType.ITEM);
        FileUtil.writeLinesToFile(LoadedModData.resourcesModidPath + "models/item/" + jsonName + ".json", itemModelModel.getRepresentation());

        // Add texture file
        // TODO: For right now add in a temporary image for item (appleItem)
        Path src = new File(ImageData.appleItemImagePath).toPath();
        Path dest = new File(LoadedModData.resourcesModidPath + "textures/item/" + jsonName + ".png").toPath();
        Files.copy(src, dest, StandardCopyOption.REPLACE_EXISTING);

        // Add to lang file
        addItemToLang(jsonName, itemName);

        // Save item to JSON
        XmlUtil.saveItem(itemName, className, variableName, jsonName);

        System.out.println("Successfully created '" + itemName + "'");
    }

    public static void createItemClassFile(String itemName) throws IOException {
        String packageReference = "com.example." + LoadedModData.modid + ".item";

        List<String> imports = new ArrayList<>();
        imports.add("net.minecraft.world.item.Item");
        imports.add("net.minecraft.world.item.CreativeModeTab");

        List<String> extensions = new ArrayList<>();
        extensions.add("Item");

        List<MethodModel> methodModels = new ArrayList<>(
                List.of(
                        new MethodModel(AccessLevelModifier.PUBLIC, false, false, "", itemName,
                                new ArrayList<>(), new ArrayList<>(), new ArrayList<>(
                                List.of(
                                        // TODO: This is a temporary fix, (adding creative mode tab)
                                        "super(new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS))"
                                )
                        ))
                )
        );

        FileCreator.createFile(itemName, ".java", LoadedModData.modItemClassPath + itemName + ".java",
                packageReference, imports, extensions, methodModels);
    }

    public static void registerItem(String className, String variableName, String jsonName) {
        String registerCode = "ITEMS.register(\"" + jsonName + "\", () -> new " + className + "())";
        VariableModel variableModel = new VariableModel(AccessLevelModifier.PUBLIC, true, true,
                "RegistryObject<Item>", variableName, registerCode);

        HashMap<String, String> targetMap = new HashMap<>();
        // Import
        ImportModel importModel = new ImportModel(LoadedModData.importItemClassPath, className);
        targetMap.put(StringLocatorData.modidImportsLocation, importModel.getImportRepresentation() + ";");
        // Registry
        targetMap.put(StringLocatorData.modidItemsLocatorComment, FileData.javaIndent + variableModel.getDeclarationRepresentation() + ";");

        FileUtil.writeLinesAfterTargets(LoadedModData.modidFilePath, targetMap);
    }

    public static void addItemToLang(String jsonName, String itemName) {

        boolean notFirstItemInJSON = FileUtil.hasAtLeastLines(LoadedModData.resourcesModidPath + "lang/en_us.json", 3);

        HashMap<String, String> targetMap = new HashMap<>();
        LangModel langModel = new LangModel(EntityType.ITEM, jsonName, itemName);
        targetMap.put("{", FileData.javaIndent + langModel.getLangRepresentation() + (notFirstItemInJSON ? "," : ""));

        FileUtil.writeLinesAfterTargets(LoadedModData.resourcesModidPath + "lang/en_us.json", targetMap);
    }
}

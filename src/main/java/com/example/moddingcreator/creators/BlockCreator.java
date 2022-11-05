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

public class BlockCreator {
    public static void createBlock(String blockName, String className, String variableName, String jsonName) throws IOException {

        String blockItemClassName = className + "Item";
        String blockItemVariableName = variableName + "Item";

        // Create Block File for given parameters
        createBlockClassFile(className);

        // Create BlockItem File for given parameters
        createBlockItemClassFile(blockItemClassName);

        // Register Block to BLOCKS in modid.java
        registerBlock(className, variableName, jsonName);

        // Register BlockItem to ITEMS in modid.java
        registerBlockItem(blockItemClassName, blockItemVariableName, jsonName, variableName);

        // Setup blockstate JSON
        BlockstateModel blockstateModel = new BlockstateModel(jsonName);
        FileUtil.writeLinesToFile(LoadedModData.resourcesModidPath + "blockstates/" + jsonName + ".json", blockstateModel.getRepresentation());

        // Setup block model JSON
        BlockModelModel blockModelModel = new BlockModelModel("block/cube_all", jsonName);
        FileUtil.writeLinesToFile(LoadedModData.resourcesModidPath + "models/block/" + jsonName + ".json", blockModelModel.getRepresentation());

        // Setup item model JSON
        ItemModelModel itemModelModel = new ItemModelModel(null, jsonName, EntityType.BLOCK);
        FileUtil.writeLinesToFile(LoadedModData.resourcesModidPath + "models/item/" + jsonName + ".json", itemModelModel.getRepresentation());

        // Add texture file
        // TODO: For right now add in a temporary image for block (64_grass)
        Path src = new File(ImageData.size64ImagePath).toPath();
        Path dest = new File(LoadedModData.resourcesModidPath + "textures/block/" + jsonName + ".png").toPath();
        Files.copy(src, dest, StandardCopyOption.REPLACE_EXISTING);

        // Add to lang file
        addBlockToLang(jsonName, blockName);

        // Create loot table
        createLootTable(jsonName);

        // Save block to JSON
        XmlUtil.saveBlock(blockName, className, variableName, jsonName);

        System.out.println("Successfully created '" + blockName + "'");
    }

    public static void createBlockClassFile(String blockName) throws IOException {
        String packageReference = "com.example." + LoadedModData.modid + ".block";

        List<String> imports = new ArrayList<>();
        imports.add("net.minecraft.world.level.block.Block");
        imports.add("net.minecraft.world.level.block.state.BlockBehaviour");
        imports.add("net.minecraft.world.level.material.Material");

        List<String> extensions = new ArrayList<>();
        extensions.add("Block");

        List<MethodModel> methodModels = new ArrayList<>(
                List.of(
                        new MethodModel(AccessLevelModifier.PUBLIC, false, false, "", blockName,
                                new ArrayList<>(), new ArrayList<>(), new ArrayList<>(
                                List.of(
                                        // TODO: This is a temporary fix, (adding material property as template)
                                        "super(BlockBehaviour.Properties.of(Material.STONE))"
                                )
                        ))
                )
        );

        FileCreator.createFile(blockName, ".java", LoadedModData.modBlockClassPath + blockName + ".java",
                packageReference, imports, extensions, methodModels);
    }

    public static void createBlockItemClassFile(String blockItemName) throws IOException {
        String packageReference = "com.example." + LoadedModData.modid + ".blockItem";

        List<String> imports = new ArrayList<>();
        imports.add("net.minecraft.world.item.BlockItem");
        imports.add("net.minecraft.world.level.block.Block");
        imports.add("net.minecraft.world.item.CreativeModeTab");

        List<String> extensions = new ArrayList<>();
        extensions.add("BlockItem");

        List<MethodModel> methodModels = new ArrayList<>(
                List.of(
                        new MethodModel(AccessLevelModifier.PUBLIC, false, false, "", blockItemName,
                                new ArrayList<>(
                                        List.of(
                                                "Block"
                                        )
                                ), new ArrayList<>(
                                        List.of(
                                                "block"
                                        )
                        ), new ArrayList<>(
                                List.of(
                                        // TODO: This is a temporary fix, (adding material property as template)
                                        "super(block, new Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS))"
                                )
                        ))
                )
        );

        FileCreator.createFile(blockItemName, ".java", LoadedModData.modBlockItemClassPath + blockItemName + ".java",
                packageReference, imports, extensions, methodModels);
    }

    public static void registerBlock(String className, String variableName, String jsonName) {
        String registerCode = "BLOCKS.register(\"" + jsonName + "\", () -> new " + className + "())";
        VariableModel variableModel = new VariableModel(AccessLevelModifier.PUBLIC, true, true,
                "RegistryObject<Block>", variableName, registerCode);

        HashMap<String, String> targetMap = new HashMap<>();
        // Import
        ImportModel importModel = new ImportModel(LoadedModData.importBlockClassPath, className);
        targetMap.put(StringLocatorData.modidImportsLocation, importModel.getImportRepresentation() + ";");
        // Registry
        targetMap.put(StringLocatorData.modidBlocksLocatorComment, FileData.javaIndent + variableModel.getDeclarationRepresentation() + ";");

        FileUtil.writeLinesAfterTargets(LoadedModData.modidFilePath, targetMap);
    }

    public static void registerBlockItem(String className, String variableName, String jsonName, String blockVariableName) {
        String registerCode = "ITEMS.register(\"" + jsonName + "\", () -> new " + className + "(" + blockVariableName + ".get()))";
        VariableModel variableModel = new VariableModel(AccessLevelModifier.PUBLIC, true, true,
                "RegistryObject<Item>", variableName, registerCode);

        HashMap<String, String> targetMap = new HashMap<>();
        // Import
        ImportModel importModel = new ImportModel(LoadedModData.importBlockItemClassPath, className);
        targetMap.put(StringLocatorData.modidImportsLocation, importModel.getImportRepresentation() + ";");
        // Registry
        targetMap.put(StringLocatorData.modidBlockItemsLocatorComment, FileData.javaIndent + variableModel.getDeclarationRepresentation() + ";");

        FileUtil.writeLinesAfterTargets(LoadedModData.modidFilePath, targetMap);
    }

    public static void addBlockToLang(String jsonName, String blockName) {

        boolean notFirstItemInJSON = FileUtil.hasAtLeastLines(LoadedModData.resourcesModidPath + "lang/en_us.json", 3);

        HashMap<String, String> targetMap = new HashMap<>();
        LangModel langModel = new LangModel(EntityType.BLOCK, jsonName, blockName);
        targetMap.put("{", FileData.javaIndent + langModel.getLangRepresentation() + (notFirstItemInJSON ? "," : ""));

        FileUtil.writeLinesAfterTargets(LoadedModData.resourcesModidPath + "lang/en_us.json", targetMap);
    }

    public static void createLootTable(String jsonName) throws IOException {
        LootTableModel lootTableModel = new LootTableModel(EntityType.BLOCK, new ArrayList<>(
                List.of(
                        new LootTablePool(1, new ArrayList<>(
                                List.of(
                                        new LootTableEntry(EntityType.ITEM, jsonName)
                                )
                        ))
                )
        ));
        FileUtil.writeLinesToFile(LoadedModData.dataModidPath + "loot_tables/blocks/" + jsonName + ".json", lootTableModel.getRepresentation());
    }
}

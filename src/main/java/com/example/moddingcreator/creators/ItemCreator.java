package com.example.moddingcreator.creators;

import com.example.moddingcreator.data.LoadedModData;
import com.example.moddingcreator.enums.AccessLevelModifier;
import com.example.moddingcreator.models.MethodModel;
import com.example.moddingcreator.models.VariableModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ItemCreator {
    public static void createItem(String itemName) throws IOException {
        // Get valid Item names for files
        String camelItemName = "";
        String underscoreItemName = "";

        // Create Item File for given parameters
        createItemClassFile(itemName);

        // Register item to ITEMS in modid.java
        registerItem(itemName);

        // Setup item model JSON

        // Add texture file

        // Add to lang file
    }
    public static void registerItem(String itemName) {
        String registerCode = "ITEMS.register()";
        VariableModel variableModel = new VariableModel(AccessLevelModifier.PUBLIC, true, true, );
    }

    public static void createItemClassFile(String itemName) throws IOException {
        String packageReference = "com.example." + LoadedModData.modid + ".item";

        List<String> imports = new ArrayList<>();
        imports.add("net.minecraft.world.item.Item");

        List<String> extensions = new ArrayList<>();
        extensions.add("Item");

        List<MethodModel> methodModels = new ArrayList<>(
                List.of(
                        new MethodModel(AccessLevelModifier.PUBLIC, false, false, "", itemName,
                                new ArrayList<>(), new ArrayList<>(), new ArrayList<>(
                                List.of(
                                        "super(new Properties())"
                                )
                        ))
                )
        );

        FileCreator.createFile(itemName, ".java", LoadedModData.modItemClassPath + itemName + ".java",
                packageReference, imports, extensions, methodModels);
    }
}

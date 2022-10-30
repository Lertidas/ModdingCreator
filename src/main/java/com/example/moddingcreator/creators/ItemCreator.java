package com.example.moddingcreator.creators;

import com.example.moddingcreator.data.LoadedModData;
import com.example.moddingcreator.enums.AccessLevelModifier;
import com.example.moddingcreator.models.MethodModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemCreator {
    public static void createItem(String itemName) throws IOException {
        // Create Item File for given parameters
        createItemClassFile(itemName);

        // Register item to ITEMS in modid.java

        // Setup item model JSON

        // Add texture file

        // Add to lang file
    }

    public static void createItemClassFile(String itemName) throws IOException {
        String packageReference = "com.example." + LoadedModData.modid + ".item";

        List<String> imports = new ArrayList<>();
        imports.add("net.minecraft.world.item.Item");

        List<String> extensions = new ArrayList<>();
        extensions.add("Item");

        List<MethodModel> methodModels = new ArrayList<>(
                Arrays.asList(
                        new MethodModel(AccessLevelModifier.PUBLIC, false, false, "", itemName,
                                new ArrayList<>(), new ArrayList<>(), new ArrayList<>(
                                        Arrays.asList(
                                                "super(new Properties())"
                                        )
                        ))
                )
        );

        FileCreator.createFile(itemName, ".java", LoadedModData.modItemClassPath + itemName + ".java",
                packageReference, imports, extensions, methodModels);
    }
}

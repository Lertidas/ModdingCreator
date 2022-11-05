package com.example.moddingcreator.models;

import com.example.moddingcreator.data.FileData;
import com.example.moddingcreator.data.LoadedModData;
import com.example.moddingcreator.enums.EntityType;

import java.util.ArrayList;
import java.util.List;

public class LootTableEntry {
    public EntityType type;
    public String name;

    public LootTableEntry(EntityType type, String name) {
        this.type = type;
        this.name = name;
    }

    public List<String> getRepresentation(int currentIndentation) {
        List<String> representation = new ArrayList<>();
        String indentation = String.valueOf(FileData.jsonIndent).repeat(currentIndentation);

        representation.add(indentation + "\"type\": \"minecraft:" + type.toString().toLowerCase() + "\",");
        representation.add(indentation + "\"name\": \"" + LoadedModData.modid + ":" + name + "\"");

        return representation;
    }
}

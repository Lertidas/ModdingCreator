package com.example.moddingcreator.models;

import com.example.moddingcreator.data.FileData;
import com.example.moddingcreator.data.LoadedModData;
import com.example.moddingcreator.enums.EntityType;

import java.util.ArrayList;
import java.util.List;

public class ItemModelModel {
    public String parent;
    public String itemName;
    public EntityType type;

    public ItemModelModel(String parent, String itemName, EntityType type) {
        this.parent = parent;
        this.itemName = itemName;
        this.type = type;
    }

    public List<String> getRepresentation() {
        List<String> representation = new ArrayList<>();

        representation.add("{");
        if (type == EntityType.ITEM) {
            representation.add(FileData.jsonIndent + "\"parent\": \"" + parent + "\",");
            representation.add(FileData.jsonIndent + "\"textures\": {");
            representation.add(FileData.jsonIndent + FileData.jsonIndent + "\"layer0\": \"" + LoadedModData.modid + ":item/" + itemName + "\"");
            representation.add(FileData.jsonIndent + "}");
        }
        else if (type == EntityType.BLOCK) {
            representation.add(FileData.jsonIndent + "\"parent\": \"" + LoadedModData.modid + ":block/" + itemName + "\"");
        }
        representation.add("}");

        return representation;
    }
}

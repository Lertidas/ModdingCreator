package com.example.moddingcreator.models;

import com.example.moddingcreator.data.FileData;
import com.example.moddingcreator.enums.EntityType;

import java.util.ArrayList;
import java.util.List;

public class LootTableModel {
    public EntityType type;
    public List<LootTablePool> pools;

    public LootTableModel(EntityType type, List<LootTablePool> pools) {
        this.type = type;
        this.pools = pools;
    }

    public List<String> getRepresentation() {
        List<String> representation = new ArrayList<>();

        representation.add("{");
        representation.add(FileData.jsonIndent + "\"type\": \"minecraft:" + type.toString().toLowerCase() + "\",");
        representation.add(FileData.jsonIndent + "\"pools\": [");
        int poolCount = pools.size();
        for (int i = 0; i < poolCount; i++) {
            representation.add(FileData.jsonIndent + FileData.jsonIndent + "{");
            representation.addAll(pools.get(i).getRepresentation(3));
            representation.add(FileData.jsonIndent + FileData.jsonIndent + "}" + ((i != poolCount - 1) ? "," : ""));
        }
        representation.add(FileData.jsonIndent + "]");
        representation.add("}");

        return representation;
    }
}

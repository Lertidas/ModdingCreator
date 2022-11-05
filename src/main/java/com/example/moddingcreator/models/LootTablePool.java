package com.example.moddingcreator.models;

import com.example.moddingcreator.data.FileData;

import java.util.ArrayList;
import java.util.List;

public class LootTablePool {
    public int rolls;
    public List<LootTableEntry> entries;

    public LootTablePool(int rolls, List<LootTableEntry> entries) {
        this.rolls = rolls;
        this.entries = entries;
    }

    public List<String> getRepresentation(int currentIndentation) {
        List<String> representation = new ArrayList<>();
        String indentation = String.valueOf(FileData.jsonIndent).repeat(currentIndentation);

        representation.add(indentation + "\"rolls\": " + rolls + ",");
        representation.add(indentation + "\"entries\": [");

        int entriesCount = entries.size();
        for (int i = 0; i < entriesCount; i++) {
            representation.add(indentation + FileData.jsonIndent + "{");
            representation.addAll(entries.get(i).getRepresentation(currentIndentation + 2));
            representation.add(indentation + FileData.jsonIndent + "}" + ((i != entriesCount - 1) ? "," : ""));
        }

        representation.add(indentation + "]");

        return representation;
    }
}

package com.example.moddingcreator.models;

import com.example.moddingcreator.data.FileData;
import com.example.moddingcreator.data.LoadedModData;

import java.util.ArrayList;
import java.util.List;

public class BlockstateModel {
    public String blockName;

    public BlockstateModel(String blockName) {
        this.blockName = blockName;
    }

    public List<String> getRepresentation() {
        List<String> representation = new ArrayList<>();

        representation.add("{");
        representation.add(FileData.jsonIndent + "\"variants\": {");
        representation.add(FileData.jsonIndent + FileData.jsonIndent + "\"\": { \"model\": \"" + LoadedModData.modid + ":block/" + blockName + "\" }");
        representation.add(FileData.jsonIndent + "}");
        representation.add("}");

        return representation;
    }
}

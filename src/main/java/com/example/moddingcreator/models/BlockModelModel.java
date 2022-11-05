package com.example.moddingcreator.models;

import com.example.moddingcreator.data.FileData;
import com.example.moddingcreator.data.LoadedModData;

import java.util.ArrayList;
import java.util.List;

public class BlockModelModel {
    public String parent;
    public String blockName;

    public BlockModelModel(String parent, String blockName) {
        this.parent = parent;
        this.blockName = blockName;
    }

    public List<String> getRepresentation() {
        List<String> representation = new ArrayList<>();

        representation.add("{");
        representation.add(FileData.jsonIndent + "\"parent\": \"" + parent + "\",");
        representation.add(FileData.jsonIndent + "\"textures\": {");
        representation.add(FileData.jsonIndent + FileData.jsonIndent + "\"all\": \"" + LoadedModData.modid + ":block/" + blockName + "\"");
        representation.add(FileData.jsonIndent + "}");
        representation.add("}");

        return representation;
    }
}

package com.example.moddingcreator.models;

import com.example.moddingcreator.data.LoadedModData;
import com.example.moddingcreator.enums.EntityType;

public class LangModel {
    public EntityType type;
    public String jsonName;
    public String itemName;

    public LangModel(EntityType type, String jsonName, String itemName) {
        this.type = type;
        this.jsonName = jsonName;
        this.itemName = itemName;
    }

    public String getLangRepresentation() {
        return "\"" + type.toString().toLowerCase() + "." + LoadedModData.modid + "." + jsonName + "\": \"" + itemName + "\"";
    }
}

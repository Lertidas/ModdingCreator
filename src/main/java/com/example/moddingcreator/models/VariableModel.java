package com.example.moddingcreator.models;

import com.example.moddingcreator.enums.AccessLevelModifier;

public class VariableModel {
    public AccessLevelModifier accessLevelModifier;
    public boolean isStatic;
    public boolean isFinal;
    public String type;
    public String name;
    public String valueToSet;

    public VariableModel(AccessLevelModifier accessLevelModifier, boolean isStatic, boolean isFinal, String type, String name, String valueToSet) {
        this.accessLevelModifier = accessLevelModifier;
        this.isStatic = isStatic;
        this.isFinal = isFinal;
        this.type = type;
        this.name = name;
        this.valueToSet = valueToSet;
    }
}

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

    public String getDeclarationRepresentation() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.accessLevelModifier.toString().toLowerCase()).append(" ");
        stringBuilder.append(this.isStatic ? "static " : "");
        stringBuilder.append(this.isFinal ? "final " : "");
        stringBuilder.append(this.type).append(" ");
        stringBuilder.append(this.name);
        if (!"".equals(this.valueToSet)) {
            stringBuilder.append(" = ").append(this.valueToSet);
        }
        return stringBuilder.toString();
    }
}

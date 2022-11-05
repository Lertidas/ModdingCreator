package com.example.moddingcreator.models;

import com.example.moddingcreator.enums.AccessLevelModifier;

import java.util.List;

public class MethodModel {
    public AccessLevelModifier accessLevelModifier;
    public boolean isStatic;
    public boolean isFinal;
    public String returnType;
    public String methodName;
    public List<String> parameterTypes;
    public List<String> parameterNames;
    public List<String> body;

    public MethodModel(AccessLevelModifier accessLevelModifier, boolean isStatic, boolean isFinal, String returnType,
                       String methodName, List<String> parameterTypes, List<String> parameterNames, List<String> body) {
        this.accessLevelModifier = accessLevelModifier;
        this.isStatic = isStatic;
        this.isFinal = isFinal;
        this.returnType = returnType;
        this.methodName = methodName;
        this.parameterTypes = parameterTypes;
        this.parameterNames = parameterNames;
        this.body = body;
    }

    public String getSignatureRepresentation() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(accessLevelModifier.toString().toLowerCase()).append(" ");
        stringBuilder.append(isStatic ? "static " : "");
        stringBuilder.append((isFinal ? "final " : ""));
        stringBuilder.append(returnType).append(!"".equals(returnType) ? " " : "");
        stringBuilder.append(methodName).append("(");
        int parametersSize = parameterTypes.size();
        for (int i = 0; i < parametersSize; i++) {
            stringBuilder.append(parameterTypes.get(i)).append(" ").append(parameterNames.get(i));
            if (i != parametersSize - 1) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}

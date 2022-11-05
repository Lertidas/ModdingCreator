package com.example.moddingcreator.models;

import com.example.moddingcreator.data.LoadedModData;

public class ImportModel {
    public String importPath;
    public String className;

    public ImportModel(String importPath, String className) {
        this.importPath = importPath;
        this.className = className;
    }
    // Temporary solution to hardcode import path
    public String getImportRepresentation() {
        return "import " + importPath + className;
    }
}

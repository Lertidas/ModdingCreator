package com.example.moddingcreator.creators;

import com.example.moddingcreator.data.FileData;
import com.example.moddingcreator.models.MethodModel;
import com.example.moddingcreator.util.FileUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileCreator {

    public static void createFile(String fileName, String fileExtension, String filePath, String packageReference, List<String> imports,
                                  List<String> extensions, List<MethodModel> models) throws IOException {
        List<String> linesToWrite = new ArrayList<>();
        int indents = 0;

        // Add in package Reference
        linesToWrite.add(getLine(packageReference, ";\n", indents));

        // Add in imports
        int importsLength = imports.size();
        for (int i = 0; i < importsLength; i++) {
            linesToWrite.add(getLine(imports.get(i), ";" + (i == importsLength - 1 ? "\n" : ""), indents));
        }

        // Add in class name and extension
        StringBuilder extendsStringBuilder = new StringBuilder();
        int extensionsLength = extensions.size();
        if (extensionsLength > 0) {
            extendsStringBuilder.append(" extends ");
        }
        for (int i = 0; i < extensionsLength; i++) {
            extendsStringBuilder.append(extensions.get(i));
            extendsStringBuilder.append((i == extensionsLength - 1) ? ", " : "");
        }
        linesToWrite.add(getLine("public class " + fileName + extendsStringBuilder, " {", indents));
        indents += 1;

        // Add in methods
        for (MethodModel methodModel : models) {
            linesToWrite.add(getLine(methodModel.getSignature(), " {", indents));
            indents += 1;
            for (String bodyLine : methodModel.body) {
                linesToWrite.add(getLine(bodyLine, ";", indents));
            }
            indents -= 1;
            linesToWrite.add(getLine("}", "", indents));
        }

        indents -= 1;
        linesToWrite.add(getLine("}", "", indents));

        FileUtil.writeLinesToFile(filePath, linesToWrite);
    }

    public static String getLine(String content, String closing, int indents) {
        return String.valueOf(FileData.indent).repeat(indents) + content + closing;
    }
}

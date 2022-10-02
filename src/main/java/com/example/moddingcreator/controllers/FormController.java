package com.example.moddingcreator.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileWriter;

public class FormController {
    @FXML
    TextField textField;

    @FXML
    private void onFileGenerateClick() throws Exception {
        createFile();
    }
    private void createFile() throws Exception {
        String fileName = textField.getText();
        String filePath = "output/" + fileName + ".java";
        File file = new File(filePath);
        boolean result = file.createNewFile();
        if (result) {
            System.out.println(fileName + " succesfully created");
            // Write to File
            writeToFile(filePath, fileName);
        }
        else {
            System.out.println(fileName + " already exists");
        }
    }
    private void writeToFile(String filePath, String fileName) throws Exception {
        String code = "public class " + fileName + " {" + "\n\t\n}";
        FileWriter output = new FileWriter(filePath);
        output.write(code);
        output.close();
    }
}

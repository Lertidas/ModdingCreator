package com.example.moddingcreator.controllers.mod;

import com.example.moddingcreator.creators.BlockCreator;
import com.example.moddingcreator.data.SceneData;
import com.example.moddingcreator.services.Validator;
import com.example.moddingcreator.util.SceneUtil;
import com.example.moddingcreator.util.StringUtil;
import com.example.moddingcreator.util.XmlUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class CreateBlockController {

    @FXML
    public Label blockNameLabel;

    @FXML
    public TextField blockNameTextField;

    @FXML
    public Button createButton;

    @FXML
    protected void onCreateClicked(ActionEvent event) throws IOException {

        String blockName = blockNameTextField.getText();

        // Get all names used for item
        String className = StringUtil.getClassCamelRepresentation(blockName);
        String variableName = StringUtil.getVariableCamelRepresentation(blockName);
        String jsonName = StringUtil.getUnderscoreRepresentation(blockName);

        // Validate name
        // ClassName
        if (!Validator.isValidItemClassName(className)) {
            className = "_" + className;
            if (!Validator.isValidItemClassName(className)) {
                // TODO: switch to error that displays in the UI
                System.out.println("Invalid Block Name!!!");
                return;
            }
        }
        // VariableName
        if (!Validator.isValidItemClassName(variableName)) {
            variableName = "_" + variableName;
            if (!Validator.isValidItemClassName(variableName)) {
                // TODO: switch to error that displays in the UI
                System.out.println("Invalid Block Name!!!");
                return;
            }
        }
        // JSONName
        if (!Validator.isValidItemClassName(jsonName)) {
            jsonName = "_" + jsonName;
            if (!Validator.isValidItemClassName(jsonName)) {
                // TODO: switch to error that displays in the UI
                System.out.println("Invalid Block Name!!!");
                return;
            }
        }

        // Check if block name is already used by item
        if (XmlUtil.containsItem(blockName, className, variableName, jsonName)) {
            // TODO: switch to error that displays in the UI
            System.out.println("Block Name is already used!!!");
            return;
        }

        // Check if block name is already used by block
        if (XmlUtil.containsBlock(blockName, className, variableName, jsonName)) {
            // TODO: switch to error that displays in the UI
            System.out.println("Block Name is already used!!!");
            return;
        }

        BlockCreator.createBlock(blockName, className, variableName, jsonName);
        SceneUtil.switchScene(event, SceneData.modMenu);
    }

    @FXML
    protected void onBackClicked(ActionEvent event) throws IOException {
        SceneUtil.switchScene(event, SceneData.modMenu);
    }
}

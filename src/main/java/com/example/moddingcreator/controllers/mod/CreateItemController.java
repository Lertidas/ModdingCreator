package com.example.moddingcreator.controllers.mod;

import com.example.moddingcreator.creators.ItemCreator;
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

public class CreateItemController {

    @FXML
    public Label itemNameLabel;

    @FXML
    public TextField itemNameTextField;

    @FXML
    public Button createButton;

    @FXML
    public Button backButton;

    @FXML
    protected void onCreateClicked(ActionEvent event) throws IOException {

        String itemName = itemNameTextField.getText();

        // Get all names used for item
        String className = StringUtil.getClassCamelRepresentation(itemName);
        String variableName = StringUtil.getVariableCamelRepresentation(itemName);
        String jsonName = StringUtil.getUnderscoreRepresentation(itemName);

        // Validate name
        // ClassName
        if (!Validator.isValidItemClassName(className)) {
            className = "_" + className;
            if (!Validator.isValidItemClassName(className)) {
                // TODO: switch to error that displays in the UI
                System.out.println("Invalid Item Name!!!");
                return;
            }
        }
        // VariableName
        if (!Validator.isValidItemClassName(variableName)) {
            variableName = "_" + variableName;
            if (!Validator.isValidItemClassName(variableName)) {
                // TODO: switch to error that displays in the UI
                System.out.println("Invalid Item Name!!!");
                return;
            }
        }
        // JSONName
        if (!Validator.isValidItemClassName(jsonName)) {
            jsonName = "_" + jsonName;
            if (!Validator.isValidItemClassName(jsonName)) {
                // TODO: switch to error that displays in the UI
                System.out.println("Invalid Item Name!!!");
                return;
            }
        }

        // Check if item name is already used by item
        if (XmlUtil.containsItem(itemName, className, variableName, jsonName)) {
            // TODO: switch to error that displays in the UI
            System.out.println("Item Name is already used!!!");
            return;
        }

        // Check if item name is already used by block
        if (XmlUtil.containsBlock(itemName, className, variableName, jsonName)) {
            // TODO: switch to error that displays in the UI
            System.out.println("Item Name is already used!!!");
            return;
        }

        // Create item
        ItemCreator.createItem(itemName, className, variableName, jsonName);
        SceneUtil.switchScene(event, SceneData.modMenu);
    }

    @FXML
    protected void onBackClicked(ActionEvent event) throws IOException {
        SceneUtil.switchScene(event, SceneData.modMenu);
    }
}

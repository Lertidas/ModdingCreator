package com.example.moddingcreator.controllers.mod;

import com.example.moddingcreator.creators.BlockCreator;
import com.example.moddingcreator.data.ImageData;
import com.example.moddingcreator.data.SceneData;
import com.example.moddingcreator.services.Validator;
import com.example.moddingcreator.util.ImageUtil;
import com.example.moddingcreator.util.SceneUtil;
import com.example.moddingcreator.util.StringUtil;
import com.example.moddingcreator.util.XmlUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateBlockController implements Initializable {

    @FXML
    public Label blockNameLabel;

    @FXML
    public TextField blockNameTextField;

    @FXML
    public Button createButton;

    @FXML
    public Label blockModelLabel;

    @FXML
    public Button blockModelButton;

    @FXML
    public Button backButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            blockModelButton.setGraphic(ImageUtil.loadAndGetImageView(ImageData.grassBlockImagePath));
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

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
    protected void onBlockModelClicked(ActionEvent event) throws IOException {
        SceneUtil.switchSceneBlockModel(event, SceneData.createBlockModel);
    }

    @FXML
    protected void onBackClicked(ActionEvent event) throws IOException {
        SceneUtil.switchScene(event, SceneData.modMenu);
    }
}

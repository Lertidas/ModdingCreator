package com.example.moddingcreator.controllers.mod;

import com.example.moddingcreator.creators.ItemCreator;
import com.example.moddingcreator.data.SceneData;
import com.example.moddingcreator.util.SceneUtil;
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
    protected void onCreateClicked(ActionEvent event) throws IOException {
        ItemCreator.createItem(itemNameTextField.getText());
        SceneUtil.switchScene(event, SceneData.modMenu);
    }

    @FXML
    protected void onBackClicked(ActionEvent event) throws IOException {
        SceneUtil.switchScene(event, SceneData.modMenu);
    }
}

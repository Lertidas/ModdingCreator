package com.example.moddingcreator.controllers.menu;

import com.example.moddingcreator.data.InstanceData;
import com.example.moddingcreator.data.LoadedModData;
import com.example.moddingcreator.data.SceneData;
import com.example.moddingcreator.services.Validator;
import com.example.moddingcreator.util.FileUtil;
import com.example.moddingcreator.util.SceneUtil;
import com.example.moddingcreator.util.XmlUtil;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoadModController implements Initializable {
    @FXML
    private Label loadLabel;

    @FXML
    private ComboBox<String> savedMods;

    @FXML
    private Button loadModButton;

    @FXML
    private Button deleteModButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateSavedMods();
    }
    @FXML
    protected void onLoadModClick(ActionEvent event) throws IOException {
        String modName = savedMods.getValue();
        if (Validator.validateModLoad(modName)) {
            // Setup LoadedModData to match mod
            LoadedModData.setupModInstanceData(modName, XmlUtil.getModid(modName));
            System.out.println("Successfully loaded in mod");
            // Load in mod
            SceneUtil.switchScene(event, SceneData.modMenu);
        }
        else {
            System.out.println("Unable to load in mod");
        }
    }
    @FXML
    protected void onDeleteModClick(ActionEvent event) throws IOException {
        String modName = savedMods.getValue();
        if (Validator.validateModLoad(modName)) {
            // Remove files from file directory
            FileUtil.deleteDirectory(InstanceData.modOutputPath + modName);
            // Remove save from XML
            XmlUtil.removeSave(savedMods.getValue());
            // Update savedMods
            updateSavedMods();
            // Setup LoadedModData to match mod
            System.out.println("Successfully deleted mod");
        }
        else {
            System.out.println("Unable to delete mod");
        }
    }

    @FXML
    protected void onBackClicked(ActionEvent event) throws IOException {
        SceneUtil.switchScene(event, SceneData.mainMenu);
    }

    private void updateSavedMods() {
        savedMods.setItems(FXCollections.observableArrayList(
                XmlUtil.getSaveNames()
        ));
        if (savedMods.getItems().size() > 0) {
            savedMods.setValue(savedMods.getItems().get(0));
        }
    }
}

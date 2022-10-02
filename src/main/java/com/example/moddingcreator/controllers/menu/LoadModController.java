package com.example.moddingcreator.controllers.menu;

import com.example.moddingcreator.data.InstanceData;
import com.example.moddingcreator.services.GradleCommandRunner;
import com.example.moddingcreator.services.Validator;
import com.example.moddingcreator.util.FileUtil;
import com.example.moddingcreator.util.SceneUtil;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        savedMods.setItems(FXCollections.observableArrayList(
                FileUtil.getItemsInDirectory(InstanceData.modOutputPath)
        ));
        if (savedMods.getItems().size() > 0) {
            savedMods.setValue(savedMods.getItems().get(0));
        }
    }
    @FXML
    protected void onLoadModClick(ActionEvent event) throws IOException, InterruptedException {
        String modName = savedMods.getValue();
        if (Validator.validateModLoad(modName)) {
            // Build project and runClient
            GradleCommandRunner.buildAndRunClient(modName);
            // Load in mod
            SceneUtil.switchScene(event, "/com/example/moddingcreator/view/mod/mod-menu.fxml", "Mod Menu", 500, 500);
            System.out.println("Successfully loaded in mod");
        }
        else {
            System.out.println("Unable to load in mod");
        }
    }
}

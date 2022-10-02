package com.example.moddingcreator.controllers.menu;

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
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NewModController implements Initializable {
    @FXML
    private Label modNameLabel;

    @FXML
    private TextField modNameTextField;

    @FXML
    private Label modVersionLabel;

    @FXML
    private ComboBox<String> forgeVersions;

    @FXML
    private Button createModButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        forgeVersions.setItems(FXCollections.observableArrayList(
                FileUtil.getItemsInDirectory("src/main/resources/com/example/moddingcreator/forgeversions")
        ));
        forgeVersions.setValue(forgeVersions.getItems().get(0));
    }

    @FXML
    protected void onCreateModClicked(ActionEvent event) throws IOException {
        // Try to create Mod
        if (Validator.validateModSave("output/createdmods", modNameTextField.getText())) {
            // Clone forge mod repo to output/createdmods
            FileUtil.cloneRepository(
                    "src/main/resources/com/example/moddingcreator/forgeversions/" + forgeVersions.getValue(),
                    "output/createdmods",
                    modNameTextField.getText()
            );
            // Successfully created mod
            System.out.println("Successfully created mod!");
            // Load in mod
            SceneUtil.switchScene(event, "/com/example/moddingcreator/view/mod/mod-menu.fxml", "Mod Menu", 500, 500);
        }
        else {
            // Return error creating mod
            System.out.println("Cannot create mod, name exists or is invalid");
        }
    }
}

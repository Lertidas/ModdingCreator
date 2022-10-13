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
import javafx.scene.control.TextField;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NewModController implements Initializable {
    @FXML
    private Label modNameLabel;

    @FXML
    private TextField modNameTextField;

    @FXML
    private Label modidLabel;

    @FXML
    private TextField modidTextField;

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
        if (forgeVersions.getItems().size() > 0) {
            forgeVersions.setValue(forgeVersions.getItems().get(0));
        }
    }

    @FXML
    protected void onCreateModClicked(ActionEvent event) throws IOException, InterruptedException {
        String modName = modNameTextField.getText();
        String modid = modidTextField.getText();
        // Try to create Mod
        if (Validator.validateModSave(InstanceData.modOutputPath, modName, modid)) {
            // Clone forge mod repo to output/createdmods
            FileUtil.cloneRepository(
                    "src/main/resources/com/example/moddingcreator/forgeversions/" + forgeVersions.getValue(),
                    InstanceData.modOutputPath,
                    modName
            );
            // Setup mod
            GradleCommandRunner.setup(modName);
            // Adjust files to match mod
            InstanceData.modName = modName;
            InstanceData.modid = modid;
            writeModid(modid);
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

    private void writeModid(String modid) {
        String examplePath = InstanceData.modOutputPath +
                "src/main/java/com/example/";
        // Change directory name
        new File(examplePath + "examplemod").renameTo(new File(examplePath + modid));
    }
}

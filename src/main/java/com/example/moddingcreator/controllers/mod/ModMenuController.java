package com.example.moddingcreator.controllers.mod;

import com.example.moddingcreator.data.FontData;
import com.example.moddingcreator.data.LoadedModData;
import com.example.moddingcreator.data.SceneData;
import com.example.moddingcreator.services.GradleCommandRunner;
import com.example.moddingcreator.util.SceneUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModMenuController implements Initializable {
    @FXML
    public Label modNameLabel;

    @FXML
    public Label modIdLabel;

    @FXML
    public Button mainMenuButton;

    @FXML
    public Button launchModButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        modNameLabel.setText(LoadedModData.modName);
        modNameLabel.setFont(FontData.menuTitleFont);

        modIdLabel.setText(LoadedModData.modid);
        modIdLabel.setFont(FontData.menuSubtitleFont);
    }

    @FXML
    protected void mainMenuClicked(ActionEvent event) throws IOException {
        LoadedModData.resetModInstanceData();
        SceneUtil.switchScene(event, SceneData.mainMenu);
    }

    @FXML
    protected void launchModClicked() throws IOException, InterruptedException {
        // Build project and runClient
        GradleCommandRunner.buildAndRunClient(LoadedModData.modName);
    }
}

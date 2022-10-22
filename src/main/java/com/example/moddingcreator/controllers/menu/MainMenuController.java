package com.example.moddingcreator.controllers.menu;

import com.example.moddingcreator.data.SceneData;
import com.example.moddingcreator.util.SceneUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class MainMenuController {
    @FXML
    protected void onNewModClicked(ActionEvent event) throws IOException {
        SceneUtil.switchScene(event, SceneData.newMod);
    }

    @FXML
    protected void onLoadModClicked(ActionEvent event) throws IOException {
        SceneUtil.switchScene(event, SceneData.loadMod);
    }
}

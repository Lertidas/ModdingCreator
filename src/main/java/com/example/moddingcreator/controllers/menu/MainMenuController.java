package com.example.moddingcreator.controllers.menu;

import com.example.moddingcreator.util.SceneUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class MainMenuController {
    @FXML
    protected void onNewModClicked(ActionEvent event) throws IOException {
        SceneUtil.switchScene(event, "/com/example/moddingcreator/view/menu/new-mod.fxml", "New mod", 500, 500);
    }

    @FXML
    protected void onLoadModClicked(ActionEvent event) throws IOException {
        SceneUtil.switchScene(event, "/com/example/moddingcreator/view/menu/load-mod.fxml", "Load mod", 500, 500);
    }
}

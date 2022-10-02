package com.example.moddingcreator;

import com.example.moddingcreator.util.SceneUtil;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class ModdingCreator extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        SceneUtil.switchScene(stage, "/com/example/moddingcreator/view/menu/main-menu.fxml", "Minecraft Mod Form", 320, 240);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
package com.example.moddingcreator;

import com.example.moddingcreator.data.SceneData;
import com.example.moddingcreator.util.SceneUtil;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class ModdingCreator extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        SceneUtil.switchScene(stage, SceneData.mainMenu);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
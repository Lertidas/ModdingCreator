package com.example.moddingcreator.util;

import com.example.moddingcreator.ModdingCreator;
import com.example.moddingcreator.models.SceneModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneUtil {

    public static void switchScene(ActionEvent event, SceneModel sceneModel) throws IOException {
        switchScene(getStage(event), sceneModel);
    }

    public static void switchScene(Stage stage, SceneModel sceneModel) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ModdingCreator.class.getResource(sceneModel.path));
        Scene scene = new Scene(fxmlLoader.load(), sceneModel.width, sceneModel.height);
        stage.setTitle(sceneModel.title);
        stage.setScene(scene);
    }

    public static Stage getStage(ActionEvent event) {
        return (Stage) ((Node) event.getSource()).getScene().getWindow();
    }
}

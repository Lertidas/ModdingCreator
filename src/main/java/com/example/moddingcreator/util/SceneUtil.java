package com.example.moddingcreator.util;

import com.example.moddingcreator.ModdingCreator;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneUtil {
    public static void switchScene(ActionEvent event, String path, String title, int width, int height) throws IOException {
        switchScene((Stage) ((Node) event.getSource()).getScene().getWindow(), path, title, width, height);
    }
    public static void switchScene(Stage stage, String path, String title, int width, int height) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ModdingCreator.class.getResource(path));
        Scene scene = new Scene(fxmlLoader.load(), width, height);
        stage.setTitle(title);
        stage.setScene(scene);
    }
}

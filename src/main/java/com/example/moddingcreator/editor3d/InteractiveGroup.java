package com.example.moddingcreator.editor3d;

import com.example.moddingcreator.data.SceneData;
import com.example.moddingcreator.util.SceneUtil;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Rectangle;

import java.io.IOException;

public class InteractiveGroup extends Group {
    public InteractiveGroup() {

        // Size - 16
        int size = 16;
        int tileSize = 10;

        // 3D Model Nodes
        // Face 0
        int startX = 100;
        int startY = 100;
        int z = 0;
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                getChildren().add(createRectangle(x, y, z, startX, startY, size, tileSize));
            }
        }

//        FOR TESTING PURPOSES
//        Box box = new Box(100.0, 100.0, 100.0);
//        box.setTranslateX(400);
//        box.setTranslateY(300);
//        box.setTranslateZ(0);
//        box.setMaterial(new PhongMaterial(Color.RED));
//        getChildren().add(box);

        // UI Nodes
        Button button = new Button();
        button.setText("Save");
        button.setLayoutX(400);
        button.setLayoutY(700);
        button.setOnAction(onSaveClicked);
        getChildren().add(button);
    }

    public Rectangle createRectangle(int x, int y, int z, int startX, int startY, int size, int tileSize) {
        Rectangle rectangle = new Rectangle(tileSize, tileSize);
        rectangle.setStroke(Color.BLACK);
        rectangle.setStrokeWidth(1);
        rectangle.setFill(Color.WHITE);
        rectangle.setTranslateX(startX + (tileSize * x));
        rectangle.setTranslateY(startY + (tileSize * y));
        rectangle.setTranslateZ(z);
        rectangle.setTranslateZ(size * tileSize);

        rectangle.setOnMouseEntered(event -> {
            rectangle.setStroke(Color.RED);
            rectangle.toFront();
        });
        rectangle.setOnMouseExited(event -> rectangle.setStroke(Color.BLACK));
        rectangle.setOnMouseClicked(event -> rectangle.setFill(Color.GREEN));

        return rectangle;
    }

    public EventHandler<ActionEvent> onSaveClicked = (event -> {
        try {
            SceneUtil.switchScene(event, SceneData.createBlock);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    });
}

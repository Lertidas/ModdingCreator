package com.example.moddingcreator.editor3d;

import javafx.event.EventHandler;
import javafx.geometry.Point3D;
import javafx.scene.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

public class InteractiveScene extends Scene {


    public final double rotationSpeed = 10.0; // degrees
    Rotate xRotation;
    Rotate yRotation;
    Rotate zRotation;

    Point3D xAxis;
    Point3D yAxis;

    public InteractiveScene(Parent root, double width, double height) {
        super(root, width, height);

        // Set background
        setFill(new Color(0.5, 0.5, 0.5, 1));
        fillProperty();

        cameraSetup();

        addEventFilter(KeyEvent.KEY_PRESSED, arrowKeyHandler);
    }

    private void cameraSetup() {
        // Camera
        Camera camera = new PerspectiveCamera(true);
        camera.setNearClip(0.01);
        camera.setFarClip(10000);

        Translate pivot = new Translate();
        pivot.setX(180); // These are set to be centered
        pivot.setY(180); // These are set to be centered
        pivot.setZ(80); // Centered point

        xAxis = new Point3D(1, 0,0);
        yAxis = new Point3D(0, 1, 0);

        camera.getTransforms().addAll(
                pivot,
                xRotation = new Rotate(0, xAxis),
                yRotation = new Rotate(0, yAxis),
                zRotation = new Rotate(0, Rotate.Z_AXIS),
                new Translate(0, 0, -500)
        );

        setCamera(camera);
    }

    public EventHandler<KeyEvent> arrowKeyHandler = (event -> {
        if (event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.UP || event.getCode() == KeyCode.DOWN) {
            performRotation(event.getCode());
        }
    });

    public void performRotation(KeyCode keyCode) {
        // Get model and rotate it
        switch (keyCode) {
            case LEFT -> yRotation.setAngle(yRotation.getAngle() - rotationSpeed);
            case RIGHT -> yRotation.setAngle(yRotation.getAngle() + rotationSpeed);
            case UP -> xRotation.setAngle(xRotation.getAngle() - rotationSpeed);
            case DOWN -> xRotation.setAngle(xRotation.getAngle() + rotationSpeed);
        }
    }
}

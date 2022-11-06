package com.example.moddingcreator.models;

import javafx.scene.paint.Color;

public class SceneModel {

    public String path;

    public String title;

    public int width;

    public int height;

    public Color color;

    public SceneModel(String path, String title, int width, int height, Color color) {
        this.path = path;
        this.title = title;
        this.width = width;
        this.height = height;
        this.color = color;
    }
}

package com.example.moddingcreator.util;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ImageUtil {

    public static Image loadAndGetImage(String filePath) throws FileNotFoundException {
        InputStream filePathInputStream = new FileInputStream(filePath);
        return new Image(filePathInputStream);
    }

    public static ImageView loadAndGetImageView(String filePath) throws FileNotFoundException {
        return new ImageView(loadAndGetImage(filePath));
    }
}

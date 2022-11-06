package com.example.moddingcreator.data;

import com.example.moddingcreator.models.SceneModel;
import javafx.scene.paint.Color;

public class SceneData {
    public static SceneModel mainMenu = new SceneModel(InstanceData.viewFilePath + "menu/main-menu.fxml", "Minecraft Mod Form", 320, 240, Color.WHITE);
    public static SceneModel newMod = new SceneModel(InstanceData.viewFilePath + "menu/new-mod.fxml", "New mod", 500, 500, Color.WHITE);
    public static SceneModel loadMod = new SceneModel(InstanceData.viewFilePath + "menu/load-mod.fxml", "Load mod", 500, 500, Color.WHITE);
    public static SceneModel modMenu = new SceneModel(InstanceData.viewFilePath + "mod/mod-menu.fxml", "Mod Menu", 800, 600, Color.WHITE);
    public static SceneModel createBlock = new SceneModel(InstanceData.viewFilePath + "mod/create-block.fxml", "Create Block", 800, 600, Color.WHITE);
    public static SceneModel createItem = new SceneModel(InstanceData.viewFilePath + "mod/create-item.fxml", "Create Item", 800, 600, Color.WHITE);
    public static SceneModel createBlockModel = new SceneModel(InstanceData.viewFilePath + "model/create-block-model.fxml", "Create Block Model", 1000, 800, Color.LIGHTGRAY);
}

package com.example.moddingcreator.data;

import com.example.moddingcreator.models.SceneModel;

public class SceneData {
    public static SceneModel mainMenu = new SceneModel(InstanceData.viewFilePath + "menu/main-menu.fxml", "Minecraft Mod Form", 320, 240);
    public static SceneModel newMod = new SceneModel(InstanceData.viewFilePath + "menu/new-mod.fxml", "New mod", 500, 500);
    public static SceneModel loadMod = new SceneModel(InstanceData.viewFilePath + "menu/load-mod.fxml", "Load mod", 500, 500);
    public static SceneModel modMenu = new SceneModel(InstanceData.viewFilePath + "mod/mod-menu.fxml", "Mod Menu", 800, 600);
    public static SceneModel createBlock = new SceneModel(InstanceData.viewFilePath + "mod/create-block.fxml", "Create Block", 800, 600);
    public static SceneModel createItem = new SceneModel(InstanceData.viewFilePath + "mod/create-item.fxml", "Create Item", 800, 600);
}

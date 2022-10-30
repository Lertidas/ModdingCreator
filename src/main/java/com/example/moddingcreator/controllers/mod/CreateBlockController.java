package com.example.moddingcreator.controllers.mod;

import com.example.moddingcreator.data.SceneData;
import com.example.moddingcreator.util.SceneUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class CreateBlockController {

    @FXML
    protected void onBackClicked(ActionEvent event) throws IOException {
        SceneUtil.switchScene(event, SceneData.modMenu);
    }
}

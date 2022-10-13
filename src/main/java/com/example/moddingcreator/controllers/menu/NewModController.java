package com.example.moddingcreator.controllers.menu;

import com.example.moddingcreator.data.InstanceData;
import com.example.moddingcreator.services.GradleCommandRunner;
import com.example.moddingcreator.services.Validator;
import com.example.moddingcreator.util.FileUtil;
import com.example.moddingcreator.util.SceneUtil;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NewModController implements Initializable {
    @FXML
    private Label modNameLabel;

    @FXML
    private TextField modNameTextField;

    @FXML
    private Label modidLabel;

    @FXML
    private TextField modidTextField;

    @FXML
    private Label modVersionLabel;

    @FXML
    private Label authorLabel;

    @FXML
    private TextField authorTextField;

    @FXML
    private Label descriptionLabel;

    @FXML
    TextArea descriptionTextArea;

    @FXML
    private ComboBox<String> forgeVersions;

    @FXML
    private Button createModButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        forgeVersions.setItems(FXCollections.observableArrayList(
                FileUtil.getItemsInDirectory("src/main/resources/com/example/moddingcreator/forgeversions")
        ));
        if (forgeVersions.getItems().size() > 0) {
            forgeVersions.setValue(forgeVersions.getItems().get(0));
        }
    }

    @FXML
    protected void onCreateModClicked(ActionEvent event) throws IOException, InterruptedException {
        String modName = modNameTextField.getText();
        String modid = modidTextField.getText().toLowerCase();
        String author = authorTextField.getText();
        String description = descriptionTextArea.getText();
        // Try to create Mod
        if (Validator.validateModSave(InstanceData.modOutputPath, modName, modid)) {
            // Clone forge mod repo to output/createdmods
            FileUtil.cloneRepository(
                    "src/main/resources/com/example/moddingcreator/forgeversions/" + forgeVersions.getValue(),
                    InstanceData.modOutputPath,
                    modName
            );
            // Setup mod
            GradleCommandRunner.setup(modName);
            // Setup InstanceData to match mod
            InstanceData.setupModInstanceData(modName, modid);
            // Adjust files to match mod
            updateModpack(modName, modid, author, description);
            // Successfully created mod
            System.out.println("Successfully created mod!");
            // Load in mod
            SceneUtil.switchScene(event, "/com/example/moddingcreator/view/mod/mod-menu.fxml", "Mod Menu", 500, 500);
        }
        else {
            // Return error creating mod
            System.out.println("Cannot create mod, name exists or is invalid");
        }
    }

    /**
     * Update all files in cloned forge repo to match the created modName and modid
     * @param modName
     * @param modid
     * @param author
     * @param description
     */
    private void updateModpack(String modName, String modid, String author, String description) {
        renameExampleDirectoryAndFile(modName, modid);
        replaceExampleModReferences(modName, modid, author, description);
    }

    /**
     * Rename the ExampleMod.java file and examplemod directory to the corresponding modName
     * @param modName
     */
    private void renameExampleDirectoryAndFile(String modName, String modid) {
        String examplePath = InstanceData.modJavaPath + "com/example/";

        // Change directory name
        boolean renameFlag = FileUtil.renameFile(examplePath + "examplemod", examplePath + modid);
        if (renameFlag) {
            // Change file name
            renameFlag = FileUtil.renameFile(examplePath + modid + "/ExampleMod.java",
                    examplePath + modid + "/" + modid + ".java");
            if (!renameFlag) {
                System.out.println("Unable to rename file");
            }
        }
        else {
            System.out.println("Unable to rename directory");
        }
    }

    /**
     * Replace all ExampleMod references in the Main modName file
     * @param modName
     * @param modid
     * @param author
     * @param description
     */
    private void replaceExampleModReferences(String modName, String modid, String author, String description) {
        // Paths
        String modidFilePath = InstanceData.modJavaPath + "com/example/" + modid + "/" + modid + ".java";
        String packMcmetaPath = InstanceData.modResourcePath + "pack.mcmeta";
        String modsTomlPath = InstanceData.modResourcePath + "META-INF/mods.toml";

        // Main modid.java file
        FileUtil.replaceAllOccurrences(modidFilePath, "ExampleMod", modid, true);

        // pack.mcmeta file
        FileUtil.replaceAllOccurrences(packMcmetaPath, "examplemod", modid);

        // mods.toml

        // Add modid
        FileUtil.replaceAllOccurrences(modsTomlPath, "examplemod", modid);
        FileUtil.replaceAllOccurrences(modsTomlPath, "Example Mod", modName);

        // Update credits field
        String[] currentCredits = new String[1];
        currentCredits[0] = "credits=\"Thanks for this example mod goes to Java\" #optional";
        String creditsString = "credits=\"Thanks for this " + modName + " goes to " + author + "\" #optional";
        FileUtil.replaceMultiLineOccurrence(modsTomlPath, currentCredits, creditsString);

        // Update author field
        String[] currentAuthors = new String[1];
        currentAuthors[0] = "authors=\"Love, Cheese and small house plants\" #optional";
        String authorString = "authors=\"" + author + "\" #optional";
        FileUtil.replaceMultiLineOccurrence(modsTomlPath, currentAuthors, authorString);


        // Update description field
        if (!"".equals(description)) {
            String[] currentDescription = new String[5];
            currentDescription[0] = "This is a long form description of the mod. You can write whatever you want here";
            currentDescription[1] = "";
            currentDescription[2] = "Have some lorem ipsum.";
            currentDescription[3] = "";
            currentDescription[4] = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed mollis lacinia magna. " +
                    "Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Sed sagittis " +
                    "luctus odio eu tempus. Interdum et malesuada fames ac ante ipsum primis in faucibus. Pellentesque " +
                    "volutpat ligula eget lacus auctor sagittis. In hac habitasse platea dictumst. Nunc gravida elit vitae " +
                    "sem vehicula efficitur. Donec mattis ipsum et arcu lobortis, eleifend sagittis sem rutrum. Cras " +
                    "pharetra quam eget posuere fermentum. Sed id tincidunt justo. Lorem ipsum dolor sit amet, consectetur " +
                    "adipiscing elit.";
            FileUtil.replaceMultiLineOccurrence(modsTomlPath, currentDescription, description);
        }
    }
}

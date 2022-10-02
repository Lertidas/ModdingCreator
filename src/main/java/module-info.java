module com.example.moddingcreator {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires org.apache.commons.io;

    opens com.example.moddingcreator to javafx.fxml;
    exports com.example.moddingcreator;
    exports com.example.moddingcreator.controllers.menu;
    opens com.example.moddingcreator.controllers.menu to javafx.fxml;
    exports com.example.moddingcreator.controllers.mod;
    opens com.example.moddingcreator.controllers.mod to javafx.fxml;
}
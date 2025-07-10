module com.fitnesstracker.fitnesstrackerr {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;
    requires java.desktop;

    opens com.fitnesstracker.app to javafx.fxml, javafx.graphics;
    exports com.fitnesstracker.app;
    exports com.fitnesstracker.utilidades;
    opens com.fitnesstracker.utilidades to javafx.fxml, javafx.graphics;

}

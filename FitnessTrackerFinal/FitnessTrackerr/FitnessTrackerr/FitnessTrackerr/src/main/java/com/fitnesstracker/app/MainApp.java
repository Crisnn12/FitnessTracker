package com.fitnesstracker.app;

import com.fitnesstracker.db.SetupBaseDatos;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/vistas/PantallaPrincipal.fxml"));
        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(getClass().getResource("/vistas/estilos.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        this.primaryStage = primaryStage;
        SetupBaseDatos.initDatabase();
        mostrarRegistroUsuario();
    }

    public void mostrarRegistroUsuario() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/RegistroUsuario.fxml"));
            Scene scene = new Scene(loader.load());

            com.fitnesstracker.app.RegistroUsuarioController controller = loader.getController();
            controller.setMainApp(this);

            primaryStage.setScene(scene);
            primaryStage.setTitle("Fitness Tracker - Registro");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarCrearRutina() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/CrearRutina.fxml"));
            Scene scene = new Scene(loader.load());

            com.fitnesstracker.app.CrearRutinaController controller = loader.getController();
            controller.setMainApp(this);

            primaryStage.setScene(scene);
            primaryStage.setTitle("Fitness Tracker - Crear Rutina");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarPanelUsuario() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/PanelUsuario.fxml"));
            Scene scene = new Scene(loader.load());

            com.fitnesstracker.app.PanelUsuarioController controller = loader.getController();
            controller.setMainApp(this);

            primaryStage.setScene(scene);
            primaryStage.setTitle("Fitness Tracker - Panel de Usuario");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

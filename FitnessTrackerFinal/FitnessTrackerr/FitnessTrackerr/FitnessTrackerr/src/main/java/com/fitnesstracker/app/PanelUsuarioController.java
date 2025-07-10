package com.fitnesstracker.app;

import com.fitnesstracker.app.MainApp;
import com.fitnesstracker.db.RutinaDAO;
import com.fitnesstracker.db.UsuarioDAO;
import com.fitnesstracker.model.EjercicioRutina;
import com.fitnesstracker.model.Rutina;
import com.fitnesstracker.model.Usuario;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class PanelUsuarioController {

    private MainApp mainApp;

    @FXML private Label lblNombre;
    @FXML private Label lblPeso;
    @FXML private Label lblPesoObjetivo;
    @FXML private Label lblImc;
    @FXML private TableView<Rutina> tablaRutinas;
    @FXML private TableColumn<Rutina, String> colFecha;
    @FXML private TableColumn<Rutina, String> colEjercicios;
    @FXML private Label lblMensaje;

    private int usuarioIdActivo = 1;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    public void initialize() {
        cargarDatosUsuario();
        configurarTablaRutinas();
        cargarRutinas();
    }

    private void cargarDatosUsuario() {
        Usuario usuario = UsuarioDAO.obtenerUsuarioPorId(usuarioIdActivo);
        if (usuario != null) {
            lblNombre.setText(usuario.getNombre());
            lblPeso.setText(String.format("%.2f", usuario.getPeso()));
            lblPesoObjetivo.setText(String.format("%.2f", usuario.getPesoObjetivo()));
            lblImc.setText(String.format("%.2f", usuario.getImc()));
        } else {
            lblMensaje.setText("No se encontró información del usuario.");
        }
    }

    private void configurarTablaRutinas() {
        colFecha.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getFecha().toString()));

        colEjercicios.setCellValueFactory(data -> {
            List<EjercicioRutina> ejercicios = data.getValue().getEjercicios();
            String nombres = ejercicios.stream()
                    .map(e -> e.getNombre() + " (" + e.getSeries() + "x" + e.getRepeticiones() + ")")
                    .collect(Collectors.joining(", "));
            return new SimpleStringProperty(nombres);
        });
    }

    private void cargarRutinas() {
        List<Rutina> rutinas = RutinaDAO.obtenerRutinasPorUsuario(usuarioIdActivo);
        if (rutinas.isEmpty()) {
            lblMensaje.setText("No hay rutinas guardadas.");
        } else {
            lblMensaje.setText("");
            tablaRutinas.getItems().setAll(rutinas);
        }
    }

    @FXML
    private void volverPantallaPrincipal(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/PantallaPrincipal.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 800, 600));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

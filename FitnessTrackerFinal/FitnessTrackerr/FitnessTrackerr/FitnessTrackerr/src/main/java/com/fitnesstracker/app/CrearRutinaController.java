package com.fitnesstracker.app;

import com.fitnesstracker.app.MainApp;
import com.fitnesstracker.db.RutinaDAO;
import com.fitnesstracker.model.EjercicioRutina;
import com.fitnesstracker.model.Rutina;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.time.LocalDate;

public class CrearRutinaController {

    private MainApp mainApp;

    @FXML private DatePicker datePickerFecha;
    @FXML private TextField txtEjercicio;
    @FXML private ComboBox<String> comboGrupoMuscular;
    @FXML private TextField txtSeries;
    @FXML private TextField txtRepeticiones;
    @FXML private TableView<EjercicioRutina> tablaEjercicios;
    @FXML private TableColumn<EjercicioRutina, String> colEjercicio;
    @FXML private TableColumn<EjercicioRutina, String> colGrupo;
    @FXML private TableColumn<EjercicioRutina, Integer> colSeries;
    @FXML private TableColumn<EjercicioRutina, Integer> colRepeticiones;
    @FXML private Label lblMensaje;

    private ObservableList<EjercicioRutina> listaEjercicios = FXCollections.observableArrayList();

    private int usuarioIdActivo = 1;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    public void initialize() {
        datePickerFecha.setValue(LocalDate.now());

        comboGrupoMuscular.getItems().addAll("Piernas", "Espalda", "Brazos", "Pecho", "Hombros", "Abdomen");
        comboGrupoMuscular.getSelectionModel().selectFirst();

        colEjercicio.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNombre()));
        colGrupo.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getGrupoMuscular()));
        colSeries.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getSeries()));
        colRepeticiones.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getRepeticiones()));

        tablaEjercicios.setItems(listaEjercicios);

        datePickerFecha.setValue(LocalDate.now());
    }

    @FXML
    private void agregarEjercicio() {
        String nombre = txtEjercicio.getText().trim();
        String grupo = comboGrupoMuscular.getSelectionModel().getSelectedItem();
        String seriesStr = txtSeries.getText().trim();
        String repeticionesStr = txtRepeticiones.getText().trim();

        if (nombre.isEmpty() || seriesStr.isEmpty() || repeticionesStr.isEmpty()) {
            lblMensaje.setText("Complete todos los campos para agregar ejercicio.");
            return;
        }

        try {
            int series = Integer.parseInt(seriesStr);
            int repeticiones = Integer.parseInt(repeticionesStr);

            if (series <= 0 || repeticiones <= 0) {
                lblMensaje.setText("Series y repeticiones deben ser positivos.");
                return;
            }

            EjercicioRutina ejercicio = new EjercicioRutina(-1, -1, nombre, repeticiones, series, grupo);
            listaEjercicios.add(ejercicio);

            txtEjercicio.clear();
            txtSeries.clear();
            txtRepeticiones.clear();
            lblMensaje.setText("");

        } catch (NumberFormatException e) {
            lblMensaje.setText("Series y repeticiones deben ser números válidos.");
        }
    }

    @FXML
    private void guardarRutina() {
        if (listaEjercicios.isEmpty()) {
            lblMensaje.setText("Agregue al menos un ejercicio para guardar la rutina.");
            return;
        }

        LocalDate fecha = datePickerFecha.getValue();
        if (fecha == null) {
            lblMensaje.setText("Seleccione una fecha válida.");
            return;
        }

        Rutina rutina = new Rutina(usuarioIdActivo, fecha);
        rutina.getEjercicios().addAll(listaEjercicios);

        int idGuardado = RutinaDAO.guardarRutina(rutina);

        if (idGuardado != -1) {
            lblMensaje.setStyle("-fx-text-fill: green;");
            lblMensaje.setText("Rutina guardada correctamente.");
            listaEjercicios.clear();

            mainApp.mostrarPanelUsuario();
        } else {
            lblMensaje.setStyle("-fx-text-fill: red;");
            lblMensaje.setText("Error al guardar rutina.");
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

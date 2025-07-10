package com.fitnesstracker.app;

import com.fitnesstracker.db.UsuarioDAO;
import com.fitnesstracker.model.Usuario;
import com.fitnesstracker.utilidades.CalculoIMC;
import com.fitnesstracker.utilidades.SesionUsuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;

public class RegistroUsuarioController {

    private MainApp mainApp;

    @FXML private TextField txtNombre;
    @FXML private TextField txtPeso;
    @FXML private TextField txtAltura;
    @FXML private TextField txtPesoObjetivo;
    @FXML private Label lblMensaje;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void registrarUsuario(ActionEvent event) {
        String nombre = txtNombre.getText().trim();
        String pesoStr = txtPeso.getText().trim();
        String alturaStr = txtAltura.getText().trim();
        String pesoObjetivoStr = txtPesoObjetivo.getText().trim();

        if (nombre.isEmpty() || pesoStr.isEmpty() || alturaStr.isEmpty() || pesoObjetivoStr.isEmpty()) {
            lblMensaje.setText("Por favor, complete todos los campos.");
            return;
        }

        try {
            double peso = Double.parseDouble(pesoStr);
            double altura = Double.parseDouble(alturaStr);
            double pesoObjetivo = Double.parseDouble(pesoObjetivoStr);

            double imc = CalculoIMC.calcularIMC(peso, altura);

            Usuario usuario = new Usuario(nombre, peso, altura, pesoObjetivo, imc);
            boolean guardado = UsuarioDAO.guardarUsuario(usuario);

            if (guardado) {
                SesionUsuario.setUsuario(usuario);
                lblMensaje.setStyle("-fx-text-fill: green;");
                lblMensaje.setText("Usuario registrado con éxito. IMC: " + String.format("%.2f", imc));
                SesionUsuario.setUsuario(usuario); // o usuarioRegistrado
                cambiarAEscenaPrincipal(event);

            } else {
                lblMensaje.setStyle("-fx-text-fill: red;");
                lblMensaje.setText("Error al registrar usuario.");
            }

        } catch (NumberFormatException e) {
            lblMensaje.setText("Peso, altura y peso objetivo deben ser números válidos.");
        }
    }

    @FXML
    private void cambiarAEscenaPrincipal(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/PantallaPrincipal.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 800, 600));
            stage.setTitle("Fitness Tracker - Rutina");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

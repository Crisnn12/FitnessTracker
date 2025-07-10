package com.fitnesstracker.app;

import com.fitnesstracker.model.Usuario;
import com.fitnesstracker.utilidades.SesionUsuario;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class CalculadoraIMCController {

    @FXML private Label lblPeso;
    @FXML private Label lblAltura;
    @FXML private Label lblResultado;

    @FXML
    public void initialize() {
        Usuario usuario = SesionUsuario.getUsuario();
        if (usuario != null) {
            lblPeso.setText(String.format("%.1f kg", usuario.getPeso()));
            lblAltura.setText(String.format("%.1f cm", usuario.getAltura()));
        }
    }

    @FXML
    public void calcularIMC() {
        Usuario usuario = SesionUsuario.getUsuario();
        if (usuario == null) {
            lblResultado.setText("Error: no hay usuario activo.");
            return;
        }

        double peso = usuario.getPeso();
        double alturaCm = usuario.getAltura();
        double alturaM = alturaCm / 100.0;

        double imc = peso / (alturaM * alturaM);
        String clasificacion = clasificarIMC(imc);

        lblResultado.setText(String.format(
                "Tu IMC es %.1f (%s).", imc, clasificacion
        ));
    }

    private String clasificarIMC(double imc) {
        if (imc < 18.5) return "Bajo peso";
        if (imc < 24.9) return "Peso normal";
        if (imc < 29.9) return "Sobrepeso";
        return "Obesidad";
    }

}

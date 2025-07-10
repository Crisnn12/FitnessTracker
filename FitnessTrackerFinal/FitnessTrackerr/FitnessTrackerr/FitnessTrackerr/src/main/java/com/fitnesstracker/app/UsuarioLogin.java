package com.fitnesstracker.app;

public class UsuarioLogin {
    private String nombreUsuario;
    private String contraseña;

    public UsuarioLogin(String nombreUsuario, String contraseña) {
        this.nombreUsuario = nombreUsuario;
        this.contraseña = contraseña;
    }

    public String getNombreUsuario() {

        return nombreUsuario;
    }
    public String getContraseña() {

        return contraseña;
    }
}


package com.fitnesstracker.utilidades;

import com.fitnesstracker.model.Usuario;

public class SesionUsuario {
    private static Usuario usuarioActual;

    public static void setUsuario(Usuario u) {
        usuarioActual = u;
    }

    public static Usuario getUsuario() {
        return usuarioActual;
    }
}

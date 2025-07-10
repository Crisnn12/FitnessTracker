package com.fitnesstracker.db;

import java.sql.Connection;
import java.sql.Statement;

public class SetupBaseDatos {

    public static void initDatabase() {
        String sqlUsuario = """
            CREATE TABLE IF NOT EXISTS usuario (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre TEXT NOT NULL,
                peso REAL,
                altura REAL,
                peso_objetivo REAL,
                imc REAL
            );
        """;

        String sqlRutina = """
            CREATE TABLE IF NOT EXISTS rutina (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                usuario_id INTEGER,
                fecha TEXT,
                FOREIGN KEY(usuario_id) REFERENCES usuario(id)
            );
        """;

        String sqlEjercicioRutina = """
            CREATE TABLE IF NOT EXISTS ejercicio_rutina (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                rutina_id INTEGER,
                nombre TEXT,
                repeticiones INTEGER,
                series INTEGER,
                grupo_muscular TEXT,
                FOREIGN KEY(rutina_id) REFERENCES rutina(id)
            );
        """;

        try (Connection conn = DBconeccion.getConnection(); Statement stmt = conn.createStatement()) {
            stmt.execute(sqlUsuario);
            stmt.execute(sqlRutina);
            stmt.execute(sqlEjercicioRutina);
            System.out.println("Tablas creadas correctamente.");
        } catch (Exception e) {
            System.out.println("Error al crear tablas: " + e.getMessage());
        }
    }
}

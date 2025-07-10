package com.fitnesstracker.db;

import com.fitnesstracker.model.EjercicioRutina;
import com.fitnesstracker.model.Rutina;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RutinaDAO {

    public static int guardarRutina(Rutina rutina) {
        String sqlRutina = "INSERT INTO rutina(usuario_id, fecha) VALUES (?, ?)";

        try (Connection conn = DBconeccion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sqlRutina, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, rutina.getUsuarioId());
            pstmt.setString(2, rutina.getFecha().toString());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Crear rutina falló, no se insertó ninguna fila.");
            }

            int rutinaId;
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    rutinaId = generatedKeys.getInt(1);
                    rutina.setId(rutinaId);
                } else {
                    throw new SQLException("Crear rutina falló, no se obtuvo ID.");
                }
            }

            for (EjercicioRutina ejercicio : rutina.getEjercicios()) {
                guardarEjercicioRutina(rutinaId, ejercicio);
            }

            return rutinaId;

        } catch (SQLException e) {
            System.err.println("Error guardando rutina: " + e.getMessage());
            return -1;
        }
    }

    private static void guardarEjercicioRutina(int rutinaId, EjercicioRutina ejercicio) throws SQLException {
        String sqlEjercicio = """
            INSERT INTO ejercicio_rutina (rutina_id, nombre, repeticiones, series, grupo_muscular)
            VALUES (?, ?, ?, ?, ?)
            """;

        try (PreparedStatement pstmt = DBconeccion.getConnection().prepareStatement(sqlEjercicio)) {
            pstmt.setInt(1, rutinaId);
            pstmt.setString(2, ejercicio.getNombre());
            pstmt.setInt(3, ejercicio.getRepeticiones());
            pstmt.setInt(4, ejercicio.getSeries());
            pstmt.setString(5, ejercicio.getGrupoMuscular());
            pstmt.executeUpdate();
        }
    }

    public static List<Rutina> obtenerRutinasPorUsuario(int usuarioId) {
        List<Rutina> rutinas = new ArrayList<>();
        String sqlRutinas = "SELECT * FROM rutina WHERE usuario_id = ? ORDER BY fecha DESC";

        try (Connection conn = DBconeccion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sqlRutinas)) {

            pstmt.setInt(1, usuarioId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int rutinaId = rs.getInt("id");
                LocalDate fecha = LocalDate.parse(rs.getString("fecha"));

                Rutina rutina = new Rutina(rutinaId, usuarioId, fecha);
                rutina.getEjercicios().addAll(obtenerEjerciciosPorRutina(rutinaId));
                rutinas.add(rutina);
            }

        } catch (SQLException e) {
            System.err.println("Error obteniendo rutinas: " + e.getMessage());
        }

        return rutinas;
    }

    private static List<EjercicioRutina> obtenerEjerciciosPorRutina(int rutinaId) {
        List<EjercicioRutina> ejercicios = new ArrayList<>();
        String sqlEjercicios = "SELECT * FROM ejercicio_rutina WHERE rutina_id = ?";

        try (PreparedStatement pstmt = DBconeccion.getConnection().prepareStatement(sqlEjercicios)) {
            pstmt.setInt(1, rutinaId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                ejercicios.add(new EjercicioRutina(
                        rs.getInt("id"),
                        rutinaId,
                        rs.getString("nombre"),
                        rs.getInt("repeticiones"),
                        rs.getInt("series"),
                        rs.getString("grupo_muscular")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error obteniendo ejercicios de rutina: " + e.getMessage());
        }

        return ejercicios;
    }
}

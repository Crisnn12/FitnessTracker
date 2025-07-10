package com.fitnesstracker.db;

import com.fitnesstracker.model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    public static boolean guardarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuario(nombre, peso, altura, peso_objetivo, imc) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBconeccion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, usuario.getNombre());
            pstmt.setDouble(2, usuario.getPeso());
            pstmt.setDouble(3, usuario.getAltura());
            pstmt.setDouble(4, usuario.getPesoObjetivo());
            pstmt.setDouble(5, usuario.getImc());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 0) {
                return false;
            }

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    usuario.setId(generatedKeys.getInt(1));
                }
            }

            return true;

        } catch (SQLException e) {
            System.err.println("Error guardando usuario: " + e.getMessage());
            return false;
        }
    }

    public static Usuario obtenerUsuarioPorId(int id) {
        String sql = "SELECT * FROM usuario WHERE id = ?";
        try (Connection conn = DBconeccion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Usuario(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getDouble("peso"),
                        rs.getDouble("altura"),
                        rs.getDouble("peso_objetivo"),
                        rs.getDouble("imc")
                );
            }

        } catch (SQLException e) {
            System.err.println("Error obteniendo usuario: " + e.getMessage());
        }
        return null;
    }

    public static List<Usuario> obtenerTodosUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuario";

        try (Connection conn = DBconeccion.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                usuarios.add(new Usuario(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getDouble("peso"),
                        rs.getDouble("altura"),
                        rs.getDouble("peso_objetivo"),
                        rs.getDouble("imc")
                ));
            }

        } catch (SQLException e) {
            System.err.println("Error obteniendo usuarios: " + e.getMessage());
        }
        return usuarios;
    }
}

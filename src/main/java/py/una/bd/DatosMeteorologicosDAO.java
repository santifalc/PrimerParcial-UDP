/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package py.una.bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import py.una.entidad.DatosMeteorologicos;

/**
 *
 * @author Santi
 */
public class DatosMeteorologicosDAO {

    public List<DatosMeteorologicos> seleccionar() {
        String query = "SELECT id_estacion, ciudad, porcentaje_humedad, temperatura, velocidad_viento, fecha, hora FROM datos_meteorologicos ";

        List<DatosMeteorologicos> lista = new ArrayList<>();

        Connection conn = null;
        try {
            conn = Bd.connect();
            ResultSet rs = conn.createStatement().executeQuery(query);

            while (rs.next()) {
                DatosMeteorologicos datosMeteorologicos = new DatosMeteorologicos();
                datosMeteorologicos.setId_estacion(rs.getLong(1));
                datosMeteorologicos.setCiudad(rs.getString(2));
                datosMeteorologicos.setPorcentaje_humedad(rs.getFloat(3));
                datosMeteorologicos.setTemperatura(rs.getInt(4));
                datosMeteorologicos.setVelocidad_viento(rs.getFloat(5));
                datosMeteorologicos.setFecha(rs.getString(6));
                datosMeteorologicos.setHora(rs.getString(7));

                lista.add(datosMeteorologicos);
            }

        } catch (SQLException ex) {
            System.out.println("Error en la seleccion: " + ex.getMessage());
        } finally {
            try {
                conn.close();
            } catch (Exception ef) {
                System.out.println("No se pudo cerrar la conexion a BD: " + ef.getMessage());
            }
        }
        return lista;

    }

    public long insertar(DatosMeteorologicos datosMetereologicos) throws SQLException {

        String SQL = "INSERT INTO datos_meteorologicos(id_estacion, ciudad, porcentaje_humedad, temperatura, velocidad_viento, fecha, hora) "
                + "VALUES(?,?,?,?,?,?,?)";

        long id = 0;
        Connection conn = null;

        try {
            conn = Bd.connect();
            PreparedStatement pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);

            pstmt.setLong(1, datosMetereologicos.getId_estacion());
            pstmt.setString(2, datosMetereologicos.getCiudad());
            pstmt.setFloat(3, datosMetereologicos.getPorcentaje_humedad());
            pstmt.setInt(4, datosMetereologicos.getTemperatura());
            pstmt.setFloat(5, datosMetereologicos.getVelocidad_viento());
            pstmt.setString(6, datosMetereologicos.getFecha());
            pstmt.setString(7, datosMetereologicos.getHora());

            int affectedRows = pstmt.executeUpdate();
            // check the affected rows 
            if (affectedRows > 0) {
                // get the ID back
                try ( ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        id = rs.getLong(1);
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error en la insercion: " + ex.getMessage());
        } finally {
            try {
                conn.close();
            } catch (Exception ef) {
                System.out.println("No se pudo cerrar la conexion a BD: " + ef.getMessage());
            }
        }

        return id;

    }

    public long actualizar(DatosMeteorologicos datosMetereologicos) throws SQLException {

        String SQL = "UPDATE datos_meteorologicos SET ciudad = ?, porcentaje_humedad = ? , temperatura = ? , velocidad_viento = ? , fecha = ? , hora = ? WHERE id_estacion = ? ";

        long id = 0;
        Connection conn = null;

        try {
            conn = Bd.connect();
            PreparedStatement pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            pstmt.setLong(1, datosMetereologicos.getId_estacion());
            pstmt.setString(2, datosMetereologicos.getCiudad());
            pstmt.setFloat(3, datosMetereologicos.getPorcentaje_humedad());
            pstmt.setInt(4, datosMetereologicos.getTemperatura());
            pstmt.setFloat(5, datosMetereologicos.getVelocidad_viento());
            pstmt.setString(6, datosMetereologicos.getFecha());
            pstmt.setString(7, datosMetereologicos.getHora());

            int affectedRows = pstmt.executeUpdate();
            // check the affected rows 
            if (affectedRows > 0) {
                // get the ID back
                try ( ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        id = rs.getLong(1);
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error en la actualizacion: " + ex.getMessage());
        } finally {
            try {
                conn.close();
            } catch (Exception ef) {
                System.out.println("No se pudo cerrar la conexion a BD: " + ef.getMessage());
            }
        }
        return id;
    }

    public long borrar(String ciudad) throws SQLException {

        String SQL = "DELETE FROM datos_meteorologicos WHERE id_estacion = ? ";

        long id = 0;
        Connection conn = null;

        try {
            conn = Bd.connect();
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, ciudad);

            int affectedRows = pstmt.executeUpdate();
            // check the affected rows 
            if (affectedRows > 0) {
                // get the ID back
                try ( ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        id = rs.getLong(1);
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error en la eliminación: " + ex.getMessage());
        } finally {
            try {
                conn.close();
            } catch (Exception ef) {
                System.out.println("No se pudo cerrar la conexion a BD: " + ef.getMessage());
            }
        }
        return id;
    }

    public List<DatosMeteorologicos> seleccionarPorCiudad(String ciudad) {
        String SQL = "SELECT ciudad, temperatura FROM datos_meteorologicos WHERE ciudad = ? ";

        List<DatosMeteorologicos> lista = new ArrayList<DatosMeteorologicos>();

        Connection conn = null;
        try {
            conn = Bd.connect();
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, ciudad);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                DatosMeteorologicos datosMeteorologicos = new DatosMeteorologicos();
                datosMeteorologicos.setCiudad(rs.getString(1));
                datosMeteorologicos.setTemperatura(rs.getInt(2));

                lista.add(datosMeteorologicos);
            }

        } catch (SQLException ex) {
            System.out.println("Error en la seleccion: " + ex.getMessage());
        } finally {
            try {
                conn.close();
            } catch (Exception ef) {
                System.out.println("No se pudo cerrar la conexion a BD: " + ef.getMessage());
            }
        }
        return lista;

    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package py.una.bd;

import java.sql.SQLException;
import java.util.List;
import py.una.entidad.DatosMeteorologicos;

/**
 *
 * @author Santi
 */
public class TestDatosMeteorologicosDAO {

    public static void main(String args[]) throws SQLException {
        DatosMeteorologicosDAO datosMeteorologicosDAO = new DatosMeteorologicosDAO();

        DatosMeteorologicos datosMeteorologicos = new DatosMeteorologicos();
        datosMeteorologicos.setId_estacion(1L);
        datosMeteorologicos.setCiudad("San Lorenzo");
        datosMeteorologicos.setPorcentaje_humedad(12.4f);
        datosMeteorologicos.setTemperatura(27);
        datosMeteorologicos.setVelocidad_viento(5.0f);
        datosMeteorologicos.setFecha("17/09/2022");
        datosMeteorologicos.setHora("19:30");

        datosMeteorologicosDAO.insertar(datosMeteorologicos);

        List<DatosMeteorologicos> lista = datosMeteorologicosDAO.seleccionar();
//        List<DatosMeteorologicos> lista = datosMeteorologicosDAO.seleccionarPorCiudad("San Lorenzo");

        //Prueba para listar todos los datos de la bd
        for (DatosMeteorologicos datos : lista) {
            System.out.println(datos.getId_estacion() + " " + datos.getCiudad());
        }
        //Prueba para buscar por ciudad
//        for (DatosMeteorologicos datos : lista) {
//            System.out.println(datos.getCiudad() + " " + datos.getTemperatura());
//        }
    }
}

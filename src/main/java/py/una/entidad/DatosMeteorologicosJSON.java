/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package py.una.entidad;

import java.sql.Date;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Santi
 */
public class DatosMeteorologicosJSON {

    public static void main(String[] args) throws Exception {
        DatosMeteorologicosJSON representacion = new DatosMeteorologicosJSON();

        System.out.println("Ejemplo de uso 1: pasar de objeto a string");
        DatosMeteorologicos datosMeteorologicos = new DatosMeteorologicos();
        datosMeteorologicos.setId_estacion(1L);
        datosMeteorologicos.setCiudad("San Lorenzo");
        datosMeteorologicos.setPorcentaje_humedad(12.4f);
        datosMeteorologicos.setTemperatura(27);
        datosMeteorologicos.setVelocidad_viento(5.0f);
        datosMeteorologicos.setFecha("17/09/2022");
        datosMeteorologicos.setHora("19:30");

        String r1 = representacion.objetoString(datosMeteorologicos);
        System.out.println(r1);

        System.out.println("\n*************************************************************************");
        System.out.println("\nEjemplo de uso 2: pasar de string a objeto");
        String un_string = "{\"id_estacion\":1,\"ciudad\":\"San Lorenzo\",\"porcentaje_humedad\":\"12.4\",\"temperatura\":\"27\",\"velocidad_viento\":\"5.0\",\"fecha\":\"2022-09-17 00:00:00.000\",\"hora\":\"2022-09-17 00:00:00.000\"}";

        DatosMeteorologicos r2 = representacion.stringObjeto(un_string);
        System.out.println(r2.getId_estacion() + " " + r2.getCiudad() + " " + r2.getPorcentaje_humedad() + " " + r2.getTemperatura() + " " + r2.getVelocidad_viento() + " " + r2.getFecha() + " " + r2.getHora());
    }

    public static String objetoString(DatosMeteorologicos datosMeteorologicos) {

        JSONObject obj = new JSONObject();
        obj.put("id_estacion", datosMeteorologicos.getId_estacion());
        obj.put("ciudad", datosMeteorologicos.getCiudad());
        obj.put("porcentaje_humedad", datosMeteorologicos.getPorcentaje_humedad());
        obj.put("temperatura", datosMeteorologicos.getTemperatura());
        obj.put("velocidad_viento", datosMeteorologicos.getVelocidad_viento());
        obj.put("fecha", datosMeteorologicos.getFecha());
        obj.put("hora", datosMeteorologicos.getHora());

        return obj.toJSONString();
    }

    public static DatosMeteorologicos stringObjeto(String str) throws Exception {
        DatosMeteorologicos datosMeteorologicos = new DatosMeteorologicos();
        JSONParser parser = new JSONParser();

        Object obj = parser.parse(str.trim());
        JSONObject jsonObject = (JSONObject) obj;

        Long id_estacion = (Long) jsonObject.get("id_estacion");
        datosMeteorologicos.setId_estacion(id_estacion);
        datosMeteorologicos.setCiudad((String) jsonObject.get("ciudad"));
        if (jsonObject.get("porcentaje_humedad") != null) {
            datosMeteorologicos.setPorcentaje_humedad(Float.parseFloat(jsonObject.get("porcentaje_humedad").toString()));
        }
        if (jsonObject.get("temperatura") != null) {
            datosMeteorologicos.setTemperatura(Integer.parseInt(jsonObject.get("temperatura").toString()));
        }
        if (jsonObject.get("velocidad_viento") != null) {
            datosMeteorologicos.setVelocidad_viento(Float.parseFloat(jsonObject.get("velocidad_viento").toString()));
        }
        datosMeteorologicos.setFecha((String) jsonObject.get("fecha"));
        datosMeteorologicos.setHora((String) jsonObject.get("hora"));

        return datosMeteorologicos;
    }
}

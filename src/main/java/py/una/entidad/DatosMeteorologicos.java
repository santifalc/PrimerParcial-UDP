/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package py.una.entidad;

import java.sql.Date;

/**
 *
 * @author Santi
 */
public class DatosMeteorologicos {
    
    private Long id_estacion;
    private String ciudad;
    private Float porcentaje_humedad;
    private Integer temperatura;
    private Float velocidad_viento;
    private String fecha;
    private String hora;

    public DatosMeteorologicos() {
    }

    public Long getId_estacion() {
        return id_estacion;
    }

    public void setId_estacion(Long id_estacion) {
        this.id_estacion = id_estacion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Float getPorcentaje_humedad() {
        return porcentaje_humedad;
    }

    public void setPorcentaje_humedad(Float porcentaje_humedad) {
        this.porcentaje_humedad = porcentaje_humedad;
    }

    public Integer getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Integer temperatura) {
        this.temperatura = temperatura;
    }

    public Float getVelocidad_viento() {
        return velocidad_viento;
    }

    public void setVelocidad_viento(Float velocidad_viento) {
        this.velocidad_viento = velocidad_viento;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package py.una.udp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import py.una.entidad.DatosMeteorologicos;
import py.una.entidad.DatosMeteorologicosJSON;

/**
 *
 * @author Santi
 */
public class UDPClient {

    public static void main(String a[]) throws Exception {
        // Datos necesario
        String direccionServidor = "127.0.0.1";

        if (a.length > 0) {
            direccionServidor = a[0];
        }

        int puertoServidor = 8000;
        BufferedReader inFromUser
                = new BufferedReader(new InputStreamReader(System.in));

        DatagramSocket clientSocket = new DatagramSocket();

        InetAddress IPAddress = InetAddress.getByName(direccionServidor);
        System.out.println("Intentando conectar a = " + IPAddress + ":" + puertoServidor + " via UDP...");
        System.out.println("Ingrese la operacion deseada");
        System.out.println("1 - Cargar datos Meteorológicos");
//        System.out.println("2 - Consultar temperatura por ciudad: ");
        String opcion = inFromUser.readLine();
        if (opcion.equals("1")) {
            try {
                byte[] sendData = new byte[1024];
                byte[] receiveData = new byte[1024];

                System.out.print("Ingrese el id de la estación: ");
                String strIdEstacion = inFromUser.readLine();
                Long idEstacion = null;
                try {
                    idEstacion = Long.parseLong(strIdEstacion);
                } catch (Exception e) {

                }

                System.out.print("Ingrese la ciudad: ");
                String ciudad = inFromUser.readLine();
                System.out.print("Ingrese porcentaje de humedad: ");
                String strPorcentaje = inFromUser.readLine();
                Float porcentaje = null;
                try {
                    porcentaje = Float.parseFloat(strPorcentaje);
                } catch (Exception e) {

                }
                System.out.print("Ingrese la temperatura: ");
                String strTemp = inFromUser.readLine();
                Integer temp = null;
                try {
                    temp = Integer.parseInt(strTemp);
                } catch (Exception e) {

                }
                System.out.print("Ingrese la velocidad del viento: ");
                String strVel = inFromUser.readLine();
                Float vel = null;
                try {
                    vel = Float.parseFloat(strVel);
                } catch (Exception e) {

                }
                System.out.print("Ingrese la fecha: ");
                String fecha = inFromUser.readLine();
                System.out.print("Ingrese la hora: ");
                String hora = inFromUser.readLine();

                DatosMeteorologicos datosMeteorologicos = new DatosMeteorologicos();
                datosMeteorologicos.setId_estacion(idEstacion);
                datosMeteorologicos.setCiudad(ciudad);
                datosMeteorologicos.setPorcentaje_humedad(porcentaje);
                datosMeteorologicos.setTemperatura(temp);
                datosMeteorologicos.setVelocidad_viento(vel);
                datosMeteorologicos.setFecha(fecha);
                datosMeteorologicos.setHora(hora);

                String datoPaquete = DatosMeteorologicosJSON.objetoString(datosMeteorologicos);
                sendData = datoPaquete.getBytes();

                System.out.println("Enviar " + datoPaquete + " al servidor. (" + sendData.length + " bytes)");
                DatagramPacket sendPacket
                        = new DatagramPacket(sendData, sendData.length, IPAddress, puertoServidor);

                clientSocket.send(sendPacket);

                DatagramPacket receivePacket
                        = new DatagramPacket(receiveData, receiveData.length);

                System.out.println("Esperamos si viene la respuesta.");
                //Vamos a hacer una llamada BLOQUEANTE entonces establecemos un timeout maximo de espera
                clientSocket.setSoTimeout(10000);

                try {
                    clientSocket.receive(receivePacket);

                    String respuesta = new String(receivePacket.getData());
                    DatosMeteorologicos datosResp = DatosMeteorologicosJSON.stringObjeto(respuesta.trim());

                    InetAddress returnIPAddress = receivePacket.getAddress();
                    int port = receivePacket.getPort();

                    System.out.println("Respuesta desde =  " + returnIPAddress + ":" + port);

                    System.out.println("Ciudad: " + datosResp.getCiudad());
                    System.out.println("Temperatura: " + datosResp.getTemperatura());
                } catch (SocketTimeoutException ste) {

                    System.out.println("TimeOut: El paquete udp se asume perdido.");
                }
                clientSocket.close();

            } catch (UnknownHostException ex) {
                System.err.println(ex);
            } catch (IOException ex) {
                System.err.println(ex);
            }
        }
    }
}

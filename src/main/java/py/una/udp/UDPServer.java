/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package py.una.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import py.una.bd.DatosMeteorologicosDAO;
import py.una.entidad.DatosMeteorologicos;
import py.una.entidad.DatosMeteorologicosJSON;

/**
 *
 * @author Santi
 */
public class UDPServer {

    public static void main(String[] a) throws SocketException {

        int puertoServidor = 8000;
        DatosMeteorologicosDAO datosMeteorologicosDAO = new DatosMeteorologicosDAO();

        try {
            //1) Creamos el socket Servidor de Datagramas (UDP)
            DatagramSocket serverSocket = new DatagramSocket(puertoServidor);
            System.out.println("Servidor Sistemas Distribuidos - UDP ");

            //2) buffer de datos a enviar y recibir
            byte[] receiveData = new byte[1024];
            byte[] sendData = new byte[1024];

            //3) Servidor siempre esperando
            while (true) {

                receiveData = new byte[1024];

                DatagramPacket receivePacket
                        = new DatagramPacket(receiveData, receiveData.length);

                System.out.println("Esperando a algun cliente... ");

                // 4) Receive LLAMADA BLOQUEANTE
                serverSocket.receive(receivePacket);

                System.out.println("________________________________________________");
                System.out.println("Aceptamos un paquete");

                // Datos recibidos e Identificamos quien nos envio
                String datoRecibido = new String(receivePacket.getData());
                datoRecibido = datoRecibido.trim();
                System.out.println("Dato Recibido: " + datoRecibido);
                DatosMeteorologicos dato = DatosMeteorologicosJSON.stringObjeto(datoRecibido);

                InetAddress IPAddress = receivePacket.getAddress();

                int port = receivePacket.getPort();

                System.out.println("De : " + IPAddress + ":" + port);
                System.out.println("Dato Recibido : " + dato.getId_estacion() + ", " + dato.getCiudad() + ", " + dato.getPorcentaje_humedad()
                        + ", " + dato.getTemperatura() + ", " + dato.getVelocidad_viento() + ", " + dato.getFecha() + ", " + dato.getHora());
                try {
                    datosMeteorologicosDAO.insertar(dato);
                    System.out.println("Dato insertado exitosamente en la Base de datos");
                } catch (Exception e) {
                    System.out.println("Dato NO insertado en la Base de datos, razón: " + e.getLocalizedMessage());
                }

                // Enviamos la respuesta inmediatamente a ese mismo cliente
                // Es no bloqueante
                sendData = DatosMeteorologicosJSON.objetoString(dato).getBytes();
                DatagramPacket sendPacket
                        = new DatagramPacket(sendData, sendData.length, IPAddress, port);

                serverSocket.send(sendPacket);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }
}

package controller;
import model.Usuario;

import java.io.*;
import java.net.*;

public class RecibirObjetoSerializado {

    public static void main(String[] args) {
        try {

            // Crear un socket para escuchar en el puerto especificado
            ServerSocket serverSocket = new ServerSocket(3002);

            // Aceptar una conexión entrante
            Socket socket = serverSocket.accept();

            // Obtener los flujos de entrada y salida del socket
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            // Leer el objeto serializado
            Usuario usuario = (Usuario) ois.readObject();

            // Imprimir el objeto
            System.out.println(usuario);

            // Cerrar el socket
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
package controller;
import model.Usuario;

import java.io.*;
import java.net.*;

public class EnviarObjetoSerializado {
    public static void main(String[] args) {
        try {
            // Crear un objeto a enviar
            Usuario usuario = new Usuario("robert","robert@gmail.com");

            // Crear un socket para conectarse a la máquina destinataria
            Socket socket = new Socket("10.200.243.221", 3002);

            // Obtener los flujos de salida y entrada del socket
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

            // Enviar el objeto serializado
            oos.writeObject(usuario);

            // Cerrar el socket
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

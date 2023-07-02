package red;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public static void main(String[] args) throws IOException {
        ServerSocket servidor = new ServerSocket(8000);
        System.out.println("Servidor iniciado");

        while (true) {
            Socket socket = servidor.accept();

            System.out.println("Conexión aceptada desde " + socket.getInetAddress().getHostAddress() + ":" + socket.getPort());

            socket.close();
            System.out.println("Conexión cerrada");
        }
    }
}

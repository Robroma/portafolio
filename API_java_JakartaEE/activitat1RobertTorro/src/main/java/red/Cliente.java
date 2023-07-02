package red;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {

    public static void main(String[] args) throws UnknownHostException, IOException {
        String host = "localhost";
        int puerto = 8000;

        Socket socket = new Socket(host, puerto);
        System.out.println("Conectado a " + host + ":" + puerto);

        socket.close();
        System.out.println("Conexión cerrada");
    }
}

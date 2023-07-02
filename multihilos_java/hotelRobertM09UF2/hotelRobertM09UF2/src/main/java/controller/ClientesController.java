package controller;

import Read.ReadFile;
import models.Cliente;
import models.Habitacion;
import utils.EstadoHabitacion;
import utils.TipoHabitacion;
import view.PanelHotel;

import java.io.*;

public class ClientesController implements Runnable{
    private PanelHotel panelHotel;

    public ClientesController(PanelHotel panelHotel) {
        this.panelHotel = panelHotel;
    }

    @Override
    public void run() {
        try {
            FileInputStream archivo = ReadFile.getArxivo("files/clientes.txt");
            InputStreamReader isr = new InputStreamReader(archivo);
            BufferedReader br = new BufferedReader(isr);

            String linea;
            while ((linea = br.readLine()) != null) {
                String str[] = linea.split(" ");
                String numThreds[] = str[2].split(",");
                for (String s: numThreds ) {
                    Thread threadClient = new Thread(new Cliente(Integer.parseInt(str[0]),str[1],Integer.parseInt(s),TipoHabitacion.valueOf(str[3].toLowerCase()),Integer.parseInt(str[4]),panelHotel));
                    threadClient.start();
                }

                Thread.sleep(2000);
            }
            br.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

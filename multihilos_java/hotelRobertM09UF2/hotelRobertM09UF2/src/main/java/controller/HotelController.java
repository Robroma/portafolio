package controller;

import Read.ReadFile;
import models.Habitacion;
import utils.EstadoHabitacion;
import utils.TipoHabitacion;
import view.PanelHotel;

import java.io.*;

public class HotelController implements Runnable {
    private PanelHotel panelHotel;

    public HotelController(PanelHotel panelHotel) {
        this.panelHotel = panelHotel;
    }

    @Override
    public void run() {
        try {
            FileInputStream archivo = ReadFile.getArxivo("files/habitacions.txt");
            InputStreamReader isr = new InputStreamReader(archivo);
            BufferedReader br = new BufferedReader(isr);

            String linea;
            while ((linea = br.readLine()) != null) {
                String str[] = linea.split(" ");
                Habitacion habitacion = new Habitacion(Integer.parseInt(str[0]), EstadoHabitacion.valueOf(str[1].toUpperCase()), TipoHabitacion.valueOf(str[2].toLowerCase()), Integer.parseInt(str[3]), Double.parseDouble(str[4]));
                panelHotel.getHotel().getHabitaciones().add(habitacion);
                panelHotel.actualizar();
                Thread.sleep(500);
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

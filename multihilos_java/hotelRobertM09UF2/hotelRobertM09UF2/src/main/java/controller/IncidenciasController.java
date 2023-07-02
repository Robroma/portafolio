package controller;

import Read.ReadFile;
import models.Habitacion;
import utils.EstadoHabitacion;
import view.PanelHotel;

import java.io.*;

public class IncidenciasController implements Runnable {
    private PanelHotel panelHotel;

    public IncidenciasController(PanelHotel panelHotel) {
        this.panelHotel = panelHotel;
    }

    @Override
    public void run() {
        try {
            FileInputStream archivo = ReadFile.getArxivo("files/incidencias.txt");
            InputStreamReader isr = new InputStreamReader(archivo);
            BufferedReader br = new BufferedReader(isr);

            String linea;
            while ((linea = br.readLine()) != null) {
                String str[] = linea.split(" ");

                Thread.sleep(Integer.parseInt(str[1]));

                if (str[0].equals("P")) {
                    for (Habitacion h : panelHotel.getHotel().getHabitaciones()) {
                        if (h.getNumero() / 100 == Integer.parseInt(str[2]) / 100) h.setDisponible(EstadoHabitacion.AVERIADA);
                    }
                } else {
                    for (Habitacion h : panelHotel.getHotel().getHabitaciones()) {
                        if (h.getNumero() == Integer.parseInt(str[2])) h.setDisponible(EstadoHabitacion.AVERIADA);
                    }
                }

                Thread.sleep(500);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}

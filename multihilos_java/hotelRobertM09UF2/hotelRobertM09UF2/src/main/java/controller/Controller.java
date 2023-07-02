package controller;

import models.Hotel;
import view.PanelHotel;

public class Controller {
    private Hotel hotel;
    private PanelHotel panelHotel;
    private DiasController diasController;
    private HotelController hotelController;
    private ClientesController clientesController;
    private IncidenciasController incidenciasController;
    public void init () throws Exception {
        hotel = new Hotel("Hotel 1");
        panelHotel = new PanelHotel(hotel);

        diasController = new DiasController(panelHotel);
        Thread threadDiasController = new Thread(diasController);
        hotelController = new HotelController(panelHotel);
        Thread threadHotelController = new Thread(hotelController);
        threadHotelController.start();
        threadDiasController.start();

        Thread.sleep(2000);

        clientesController = new ClientesController(panelHotel);
        Thread threadClientesController = new Thread(clientesController);
        threadClientesController.start();

        Thread.sleep(2000);

        incidenciasController = new IncidenciasController(panelHotel);
        Thread threadIncidenciasController = new Thread(incidenciasController);
        threadIncidenciasController.start();




    }
}

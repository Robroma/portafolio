package controller;

import view.PanelHotel;

public class DiasController implements Runnable{
    private PanelHotel panelHotel;

    public DiasController(PanelHotel panelHotel) {
        this.panelHotel = panelHotel;
    }

    @Override
    public void run() {
        while (panelHotel.getHotel().isOpen()){
            try {
                Thread.sleep(2000);
                panelHotel.getHotel().setDia(1+panelHotel.getHotel().getDia());
                panelHotel.actualizar();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
}

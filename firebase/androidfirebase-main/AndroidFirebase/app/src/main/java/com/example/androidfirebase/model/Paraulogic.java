package com.example.androidfirebase.model;

import java.util.ArrayList;

public class Paraulogic {
    private ArrayList<String> paraulesCorrectes;
    private int idParaulogic;

    public Paraulogic() { paraulesCorrectes = new ArrayList<>();

    }

    public ArrayList<String> getParaulesCorrectes() {
        return paraulesCorrectes;
    }

    public void setParaulesCorrectes(ArrayList<String> paraulesCorrectes) {
        this.paraulesCorrectes = paraulesCorrectes;
    }

    public int getIdParaulogic() {
        return idParaulogic;
    }

    public void setIdParaulogic(int idParaulogic) {
        this.idParaulogic = idParaulogic;
    }
}

package model;

import java.io.Serializable;

public class Usuario implements Serializable {
    private String nom;
    private String email;
    private static final long serialVersionUID = 42L;


    public Usuario(String nom, String email) {
        this.nom = nom;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nom + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

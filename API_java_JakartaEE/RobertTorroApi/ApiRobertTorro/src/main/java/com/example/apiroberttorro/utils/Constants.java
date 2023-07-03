package com.example.apiroberttorro.utils;

public class Constants {
    public static final String GET_BARRIO_BY_NAME = "SELECT b.name AS barrio, c.id AS casa, i.id AS inquilino\n" +
            "FROM barrio b\n" +
            "LEFT JOIN casa c ON b.name = c.barrio\n" +
            "LEFT JOIN inquilino i ON c.id = i.casa\n" +
            "WHERE b.name = ?;\n";


    public static final String USER_CONNECTION = "root";
    public static final String PASS_CONNECTION = "root";
    public static final String CONENCTION2 = "jdbc:mysql://localhost:3306/m09uf3ac1?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    public static final String DELETE_INQUILINOS_BY_CASA = "DELETE FROM inquilino WHERE casa = ?";
    public static final String DELETE_CASA_BY_BARRIO = "DELETE FROM casa WHERE barrio = ?";
    public static final String GET_CASA_POR_ID = "SELECT * FROM inquilino WHERE casa = ?";
    public static final String GET_CASAS = "SELECT casa.id AS casa_id, barrio.name AS barrio_name, inquilino.id AS inquilino_id\n" +
            "FROM casa\n" +
            "LEFT JOIN inquilino ON casa.id = inquilino.casa\n" +
            "INNER JOIN barrio ON casa.barrio = barrio.name\n" +
            "ORDER BY casa.id;";
    public static final String GET_ALL_CASAS = "SELECT * FROM casa";
    public static final String INSERT_CASA = "INSERT INTO casa (id, barrio) VALUES (?, ?)";
    public static final String INSERT_INQUILINO = "INSERT INTO inquilino (id, casa) VALUES (?, ?)";
    public static final String DELETE_CASA_ID = "DELETE FROM casa WHERE id = ?";
    public static final String GET_INQUILINO_POR_ID = "SELECT * FROM inquilino WHERE id = ?";
    public static final String GET_ALL_INQUILINOS = "SELECT * FROM inquilino";
    public static final String PUT_NUEVO_INQUILINO = "INSERT INTO inquilino\n" +
            "(id, casa)\n" +
            "VALUES(?, ?);";
    public static final String DELETE_INQUILINO = "DELETE FROM inquilino WHERE id = ?";
}

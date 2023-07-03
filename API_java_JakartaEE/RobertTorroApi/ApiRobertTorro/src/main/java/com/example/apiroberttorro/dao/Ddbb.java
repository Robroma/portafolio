package com.example.apiroberttorro.dao;

import com.example.apiroberttorro.models.Barrio;
import com.example.apiroberttorro.models.Casa;
import com.example.apiroberttorro.models.Inquilino;
import com.example.apiroberttorro.utils.Constants;

import java.sql.*;
import java.util.ArrayList;

public class Ddbb {
    private Connection conexion;

    public void connectar() {
        String url = Constants.CONENCTION2;
        String user = Constants.USER_CONNECTION;
        String pass = Constants.PASS_CONNECTION;
        try {
            conexion = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void desconectar() throws SQLException {
        if (conexion != null) {
            conexion.close();
        }

    }

    public Barrio getBarrioByName(String nombreBarrio) throws SQLException {
        connectar();
        Barrio barrio = null;

        try (PreparedStatement statement = conexion.prepareStatement(Constants.GET_BARRIO_BY_NAME)) {

            statement.setString(1, nombreBarrio);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                if (barrio == null) {
                    String nombre = resultSet.getString("barrio");
                    barrio = new Barrio(nombre);
                }

                int casaId = resultSet.getInt("casa");
                int inquilinoId = resultSet.getInt("inquilino");
                Inquilino inquilino = new Inquilino(inquilinoId, casaId);

                Casa casa = null;
                for (Casa c : barrio.getCasas()) {
                    if (c.getId() == casaId) {
                        casa = c;
                        break;
                    }
                }
                if (casa == null) {
                    casa = new Casa(casaId);
                    barrio.agregarCasa(casa);
                }

                casa.getInquilinos().add(inquilino);
            }
        }


        desconectar();

        return barrio;
    }

    public void resetBarrioByName(String nombreBarrio) throws SQLException {
        connectar();
        Barrio barrio = null;

        try (PreparedStatement statement = conexion.prepareStatement(Constants.GET_BARRIO_BY_NAME)) {
            statement.setString(1, nombreBarrio);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                if (barrio == null) {
                    String nombre = resultSet.getString("barrio");
                    barrio = new Barrio(nombre);
                }

                int casaId = resultSet.getInt("casa");
                int inquilinoId = resultSet.getInt("inquilino");
                Inquilino inquilino = new Inquilino(inquilinoId, casaId);

                Casa casa = null;
                for (Casa c : barrio.getCasas()) {
                    if (c.getId() == casaId) {
                        casa = c;
                        break;
                    }
                }
                if (casa == null) {
                    casa = new Casa(casaId);
                    barrio.agregarCasa(casa);
                }

                casa.getInquilinos().add(inquilino);
            }
        }

        for (Casa casa : barrio.getCasas()) {
            try (PreparedStatement statement = conexion.prepareStatement(Constants.DELETE_INQUILINOS_BY_CASA)) {
                statement.setInt(1, casa.getId());
                statement.executeUpdate();
            }

            try (PreparedStatement statement = conexion.prepareStatement(Constants.DELETE_CASA_BY_BARRIO)) {
                statement.setString(1, barrio.getName());
                statement.executeUpdate();
            }
        }

        desconectar();
    }


    public Casa getCasaPorId(int id) throws SQLException {
        Casa casa = new Casa(id);
        connectar();

        try (PreparedStatement statement = conexion.prepareStatement(Constants.GET_CASA_POR_ID)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {

                Inquilino inquilino = new Inquilino(resultSet.getInt("id"), id);
                System.out.println("asd1" + inquilino.toString());
                casa.getInquilinos().add(inquilino);
            }
        }

        System.out.println("asd2" + casa.toString());
        desconectar();
        return casa;
    }

    public ArrayList<Casa> getCasas() throws SQLException {
        ArrayList<Casa> casas = new ArrayList<>();
        connectar();

        try (PreparedStatement statement = conexion.prepareStatement(Constants.GET_ALL_CASAS)) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {

                int casaId = resultSet.getInt("id");
                Casa casa = new Casa(casaId);

                PreparedStatement statementInquilinos = conexion.prepareStatement(Constants.GET_CASA_POR_ID);
                statementInquilinos.setInt(1, casaId);
                ResultSet resultSetInquilinos = statementInquilinos.executeQuery();
                while (resultSetInquilinos.next()) {
                    Inquilino inquilino = new Inquilino(resultSetInquilinos.getInt("id"), casaId);
                    casa.getInquilinos().add(inquilino);
                }
                casas.add(casa);
            }
        }

        desconectar();
        return casas;
    }

    public void insertCasa(Casa casa) throws SQLException {
        connectar();

        try (PreparedStatement statementCasa = conexion.prepareStatement(Constants.INSERT_CASA)) {
            statementCasa.setInt(1, casa.getId());
            statementCasa.setString(2, casa.getBarrio());
            statementCasa.executeUpdate();

            if (casa.getInquilinos() != null) {
                for (Inquilino inquilino : casa.getInquilinos()) {
                    try (PreparedStatement statementInquilino = conexion.prepareStatement(Constants.INSERT_INQUILINO)) {
                        statementInquilino.setInt(1, inquilino.getId());
                        statementInquilino.setInt(2, casa.getId());
                        statementInquilino.executeUpdate();
                    }
                }
            }
        }

        desconectar();
    }

    public void deleteCasa(int id) throws SQLException {
        connectar();

        try (PreparedStatement statementInquilinos = conexion.prepareStatement(Constants.DELETE_INQUILINOS_BY_CASA);
             PreparedStatement statementCasa = conexion.prepareStatement(Constants.DELETE_CASA_ID)) {

            statementInquilinos.setInt(1, id);
            statementInquilinos.execute();

            statementCasa.setInt(1, id);
            statementCasa.execute();

        }
        desconectar();
    }

    public Inquilino getInquilinoPorId(int id) throws SQLException {
        Inquilino inquilino = null;
        connectar();

        try (PreparedStatement statement = conexion.prepareStatement(Constants.GET_INQUILINO_POR_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                inquilino = new Inquilino(resultSet.getInt("id"), resultSet.getInt("casa"));
            }
        }

        desconectar();
        return inquilino;
    }

    public ArrayList<Inquilino> getInquilinos() throws SQLException {
        ArrayList<Inquilino> inquilinos = new ArrayList<>();
        connectar();

        try (PreparedStatement statement = conexion.prepareStatement(Constants.GET_ALL_INQUILINOS)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Inquilino inquilino = new Inquilino(resultSet.getInt("id"), resultSet.getInt("casa"));
                inquilinos.add(inquilino);
            }
        }

        desconectar();
        return inquilinos;
    }

    public void putNuevoInquilino(Inquilino inquilino) throws SQLException {
        connectar();

        try (PreparedStatement statement = conexion.prepareStatement(Constants.PUT_NUEVO_INQUILINO)) {
            statement.setInt(1, inquilino.getId());
            statement.setInt(2, inquilino.getCasa());
            statement.executeUpdate();
        }

        desconectar();
    }

    public void deleteInquilino(int id) throws SQLException {
        connectar();

        try (PreparedStatement statement = conexion.prepareStatement(Constants.DELETE_INQUILINO)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }

        desconectar();
    }
}

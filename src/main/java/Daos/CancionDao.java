package Daos;

import Beans.Cancion;

import java.sql.*;
import java.util.ArrayList;

public class CancionDao {

    public ArrayList<Cancion> listarRecomendados(){
        ArrayList<Cancion> lista = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String sql = "select c.idcancion, c.nombre_cancion, c.banda from cancion c\n" +
                "inner join reproduccion r on (c.idcancion =  r.cancion_idcancion)\n" +
                "group by cancion_idcancion\n" +
                "having count(*) > 2\n" +
                "order by count(*) desc;";
        String url = "jdbc:mysql://localhost:3306/lab6sw1?serverTimezone=America/Lima";
        try (Connection connection = DriverManager.getConnection(url, "root", "root");
             Statement stmt = connection.createStatement();
             ResultSet resultSet = stmt.executeQuery(sql)) {

            while(resultSet.next()){
                Cancion cancion = new Cancion();
                cancion.setIdCancion(resultSet.getInt(1));
                cancion.setNombre_cancion(resultSet.getString(2));
                cancion.setIdbanda(resultSet.getString(3));

                lista.add(cancion);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }

    public ArrayList<Cancion> listaCanciones(){
            ArrayList<Cancion> lista = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String sql = "select * from cancion";
        String url = "jdbc:mysql://localhost:3306/lab6sw1?serverTimezone=America/Lima";
        try (Connection connection = DriverManager.getConnection(url, "root", "root");
             Statement stmt = connection.createStatement();
             ResultSet resultSet = stmt.executeQuery(sql)) {

            while(resultSet.next()){
                Cancion cancion = new Cancion();
                cancion.setIdCancion(resultSet.getInt(1));
                cancion.setNombre_cancion(resultSet.getString(2));
                cancion.setIdbanda(resultSet.getString(3));

                lista.add(cancion);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }

    public ArrayList<Cancion> listarCancionesBanda(String id){

        ArrayList<Cancion> lista = new ArrayList<>();


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String sql = "select * from cancion where banda = ?";
        String url = "jdbc:mysql://localhost:3306/lab6sw1?serverTimezone=America/Lima";

        try (Connection connection = DriverManager.getConnection(url, "root", "root");
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1,id);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    Cancion cancion = new Cancion();
                    cancion.setNombre_cancion(rs.getString(2));
                    cancion.setIdbanda(rs.getString(3));
                    lista.add(cancion);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }

   /* public Cancion listarCancionesBanda(String id){

        Cancion cancion = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String sql = "select * from cancion where banda = ?";
        String url = "jdbc:mysql://localhost:3306/lab6sw1?serverTimezone=America/Lima";

        try (Connection connection = DriverManager.getConnection(url, "root", "root");
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1,id);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    cancion = new Cancion();
                    cancion.setIdCancion(rs.getInt(1));
                    cancion.setNombre_cancion(rs.getString(2));
                    cancion.setIdbanda(rs.getString(3));

                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return cancion;
    }*/

    public void actualizar(Cancion cancion) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String url = "jdbc:mysql://localhost:3306/lab6sw1?serverTimezone=America/Lima";
        String sql = "update cancion set favorito = 1 where idCancion = ?";
        try (Connection connection = DriverManager.getConnection(url, "root", "root");
             PreparedStatement pstmt = connection.prepareStatement(sql)) {


            pstmt.setInt(1, cancion.getFavorito());
            pstmt.setInt(2,cancion.getIdCancion());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void eliminar(Cancion cancion) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String url = "jdbc:mysql://localhost:3306/lab6sw1?serverTimezone=America/Lima";
        String sql = "update cancion set favorito = 0 where idCancion = ?";
        try (Connection connection = DriverManager.getConnection(url, "root", "root");
             PreparedStatement pstmt = connection.prepareStatement(sql)) {


            pstmt.setInt(2, cancion.getIdCancion());


            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Cancion> listaFavoritos(){
        ArrayList<Cancion> lista = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String sql = "select * from cancion where favorito = 1;";
        String url = "jdbc:mysql://localhost:3306/lab6sw1?serverTimezone=America/Lima";
        try (Connection connection = DriverManager.getConnection(url, "root", "root");
             Statement stmt = connection.createStatement();
             ResultSet resultSet = stmt.executeQuery(sql)) {jdbc:mysql://localhost:3306/lab6sw1?serverTimezone=America/Lima

            while(resultSet.next()){
                Cancion cancion = new Cancion();
                cancion.setIdCancion(resultSet.getInt(1));
                cancion.setNombre_cancion(resultSet.getString(2));
                cancion.setIdbanda(resultSet.getString(3));
                cancion.setFavorito(resultSet.getInt(4));
                lista.add(cancion);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }


    public Cancion listar(String id) {
        Cancion cancion = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String sql = "select * from cancion where idcancion = ?";
        String url = "jdbc:mysql://localhost:3306/lab6sw1?serverTimezone=America/Lima";
        try (Connection connection = DriverManager.getConnection(url, "root", "root");
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, id);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    cancion = new Cancion();
                    cancion.setIdCancion(rs.getInt(1));
                    cancion.setNombre_cancion(rs.getString(2));
                    cancion.setIdbanda(rs.getString(3));
                    cancion.setFavorito(rs.getInt(4));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return cancion;
    }

}


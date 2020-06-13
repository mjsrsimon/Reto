/**
 * @author: Mª Jose Simón.
 * @version: 1.0
 *
 *
 */

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static Connection conexion = null;

    public static void main(String[] args) {

        //crear listados para almacenar los datos que traemos de nuestra BBDD

        List<Socio> socios = new ArrayList<>();

        /*CONEXION A LA BASE DE DATOS*/
        conectarBD();

        /*CONSULTA SIMPLE PARA CARGAR LOS SOCIOS EN NUESTRO LISTADO*/
        JFrame frame = new JFrame("Comienza...");
        cargarSocios(socios);
        Login l = new Login(socios, conexion);
        frame.setContentPane(l.getpLogin(socios));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        /*CONSULTA SIMPLE PARA CARGAR LAS RECETAS EN NUESTRO LISTADO*/
/*
        JFrame frame = new JFrame("vPrincipal");
        cargarRecetas(recetas);
        vPrincipal p = new vPrincipal(recetas, ingredientes, recetaIngredientes, conexion);

        frame.setContentPane(p.getPanelPpal());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        // desconectarBD();
        // -- no la utilizamos ya que desconectamos de la base de datos cuando hacermos conexiones para ejecutar procedimientos

 */

    }


    public static void conectarBD() {

        try {
            // Cadena de conexión
            String servidor = Configuracion.leer("DB_HOST");
            String puerto = Configuracion.leer("DB_PORT");
            String bd = Configuracion.leer("DB_DATABASE");
            String login = Configuracion.leer("DB_USERNAME");
            String password = Configuracion.leer("DB_PASSWORD");
            String url = "jdbc:oracle:thin:@" + servidor + ":" + puerto + ":" + bd;

            // Establecimiento de conexión
            conexion = DriverManager.getConnection(url, login, password);

            System.out.println("Conexion establecida");

        } catch (
                SQLException e) {
            System.out.println("no se ha podido conectar.");
        }

    }

    public static void cargarSocios(List<Socio> socios) {
        try {

            Statement stmt = conexion.createStatement();

            ResultSet rSocio = stmt.executeQuery("select * from socio");
            while (rSocio.next()) {

                socios.add(new Socio(rSocio.getInt(1),
                        rSocio.getString(2),
                        rSocio.getString(3),
                        rSocio.getString(4),
                        rSocio.getString(5),
                        rSocio.getString(6),
                        rSocio.getString(7),
                        rSocio.getString(8),
                        rSocio.getString(9),
                        Perfil.valueOf(rSocio.getString(10)),
                        rSocio.getInt(11)));
            }

            stmt.close();

        } catch (SQLException e) {
            System.out.println("No se ha podido cargar los socios.");
        }


    }


    public static void desconectarBD() {

        try {
            conexion.close();
            System.out.println("Cerrada la conexion");
        } catch (SQLException e) {
            System.out.println("No se ha podido cerrar la conexion");
        }

    }
}

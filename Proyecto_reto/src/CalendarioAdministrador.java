import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CalendarioAdministrador {
    List<Cuota> cuotas = new ArrayList<>();
    List<CuotaPagada> cuotasPagadas = new ArrayList<>();
    private JPanel pCalendarioAdmin;
    private JButton bMiPerfil;
    private JButton bCuota;
    private JButton bActividad;
    private JButton bSocios;

    public CalendarioAdministrador(List<Socio> socios, Socio socio, String representante, Connection conexion) {
        bMiPerfil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Mis datos son...");
                DatosSocio d = new DatosSocio(socio, representante, conexion, frame);
                frame.setContentPane(d.getJPDatosSocio(socio, representante, conexion));
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);

            }
        });
        bCuota.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                cargarCuotas(cuotas, cuotasPagadas, conexion, socios);
                JFrame frame = new JFrame("CuotasNuevas");
                CuotasNuevas cn = new CuotasNuevas(cuotas, cuotasPagadas, conexion, socios);
                frame.setContentPane(cn.getpCuotasNuevas(cuotas, cuotasPagadas, conexion, socios));
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);

            }
        });
        bActividad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public JPanel getpCalendarioAdmin(List<Socio> socios, Socio socio, String representante, Connection conexion) {
        return pCalendarioAdmin;
    }


    public void cargarCuotas(List<Cuota> cuotas, List<CuotaPagada> cuotasPagadas, Connection conexion, List<Socio> socios) {
        try {

            Statement stmt = conexion.createStatement();

            // almaceno en mi lista las cuotas.
            ResultSet rCuotas = stmt.executeQuery("select * from cuota");

            while (rCuotas.next()) {
                cuotas.add(new Cuota(rCuotas.getInt(1),
                        rCuotas.getDouble(2),
                        rCuotas.getDouble(3),
                        rCuotas.getString(4)));

            }

            //almaceno en mi lista las cuotas pagadas.
            ResultSet rCuotasPagadas = stmt.executeQuery("select * from cuotapagada");

            while (rCuotasPagadas.next()) {
                Socio soc = null;
                for (Socio s : socios) {
                    if (s.getId_Socio() == rCuotasPagadas.getInt(1)) {
                        soc = s;
                        break;
                    }
                }

                Cuota cu = null;
                for (Cuota c : cuotas) {
                    if (c.getId_cuota() == rCuotasPagadas.getInt(2)) {
                        cu = c;
                        break;
                    }
                }

                Boolean pagada;
                if(rCuotasPagadas.getString(4).equals("Si")){
                    pagada=true;
                }else {
                    pagada=false;
                }
                cuotasPagadas.add(new CuotaPagada(soc, cu, rCuotasPagadas.getString(3), pagada));
            }

            stmt.close();

        } catch (SQLException e) {
            System.out.println("No se ha podido cargar los socios.");
        }

    }


}

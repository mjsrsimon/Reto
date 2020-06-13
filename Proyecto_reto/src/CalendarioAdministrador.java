import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.List;

public class CalendarioAdministrador {
    private JPanel pCalendarioAdmin;
    private JButton bMiPerfil;
    private JButton bCuota;
    private JButton bActividad;
    private JButton bSocios;

    public CalendarioAdministrador(List<Socio>socios, Socio socio, String representante, Connection conexion) {
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

            }
        });
        bActividad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public JPanel getpCalendarioAdmin(List<Socio>socios, Socio socio, String representante, Connection conexion) {
        return pCalendarioAdmin;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}

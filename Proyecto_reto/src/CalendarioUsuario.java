import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.List;

public class CalendarioUsuario {
    private JPanel pCalendarioUser;
    private JButton bSocioUser;
    private JList LSocios;
    private JScrollPane SociosLista;
    private JButton bOtrosSocios;


    public CalendarioUsuario(Socio s, String representante, List<Socio> socios, Connection conexion) {
        bSocioUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Mis datos son...");
                DatosSocio d = new DatosSocio(s, representante, conexion, frame);
                frame.setContentPane(d.getJPDatosSocio(s, representante, conexion));
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);

            }
        });

        Modelo<Socio> modelo = new Modelo<>(socios);
        LSocios.setModel(modelo);


    }

    public JPanel getpCalendarioUser(Socio socio, String representante, List<Socio> socios, Connection conexion) {
        Modelo<Socio> modelo = new Modelo<>(socios);
        LSocios.setModel(modelo);
        return pCalendarioUser;
    }
}

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CalendarioAdministrador {
    private JPanel pCalendarioAdmin;
    private JButton bMiPerfil;
    private JLabel LSocios;
    private JComboBox cbSocios;
    private JButton bCuota;
    private JButton bActividad;

    public CalendarioAdministrador(List<Socio>socios, Socio socio) {
        bMiPerfil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


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

    public JPanel getpCalendarioAdmin(List<Socio>socios, Socio socio) {
        return pCalendarioAdmin;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}

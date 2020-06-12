
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalendarioUsuario {
    private JPanel pCalendarioUser;
    private JButton bSocioUser;


    public CalendarioUsuario(Socio s, String representante) {
        bSocioUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Mis datos son...");
                DatosSocio d = new DatosSocio(s, representante);
                frame.setContentPane(d.getJPDatosSocio( s, representante));
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);

            }
        });
    }

    public JPanel getpCalendarioUser(Socio socio, String representante) {
        return pCalendarioUser;
    }
}

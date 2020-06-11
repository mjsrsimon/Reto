
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalendarioUsuario {
    private JPanel pCalendarioUser;
    private JButton bSocioUser;


    public CalendarioUsuario(Socio s) {
        bSocioUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public JPanel getpCalendarioUser(Socio socio) {
        return pCalendarioUser;
    }
}

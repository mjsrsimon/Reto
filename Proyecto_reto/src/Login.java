import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Login {
    private JPanel pLogin;
    private JTextField txtUser;
    private JButton bBorrar;
    private JButton bEntrar;


    public Login(List<Socio> socios) {
        bEntrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                for (Socio s: socios){
                    if(txtUser.getText().equals(s.getDNI())){
                        JFrame frame = new JFrame("Calendario Usuario...");
                        CalendarioUsuario c = new CalendarioUsuario(s);
                        frame.setContentPane(c.getpCalendarioUser(s));
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.pack();
                        frame.setVisible(true);
                    }
                }


            }
        });
        bBorrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtUser.setText("");
            }
        });
    }

    public JPanel getpLogin(List<Socio> socios) {
        return pLogin;
    }
}

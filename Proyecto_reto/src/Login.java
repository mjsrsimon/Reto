import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

/**
 *
 */
public class Login {
    private JPanel pLogin;
    private JTextField txtUser;
    private JButton bBorrar;
    private JButton bEntrar;


    public Login(List<Socio> socios, Connection conexion) {
        bEntrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                for (Socio s : socios) {
                    if (txtUser.getText().equals(s.getDNI())) {
                        if (s.getPerfil().equals(Perfil.valueOf("ADMINISTRADOR"))) {
                            String repre = Representante(s, conexion);
                            JFrame frame = new JFrame("Calendario administrador...");
                            CalendarioAdministrador ca = new CalendarioAdministrador(socios, s, repre, conexion);
                            frame.setContentPane(ca.getpCalendarioAdmin(socios, s,repre, conexion));
                            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            frame.pack();
                            frame.setVisible(true);
                            //hago un break para que no continue el foreach ya que ha encontrado al socio.
                            break;
                        } else if (s.getPerfil().equals(Perfil.valueOf("USUARIO"))) {
                            String repre = Representante(s, conexion);
                            JFrame frame = new JFrame("Calendario Usuario...");
                            CalendarioUsuario c = new CalendarioUsuario(s, repre, socios, conexion);
                            frame.setContentPane(c.getpCalendarioUser(s, repre, socios, conexion));
                            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            frame.pack();
                            frame.setVisible(true);
                            //hago un break para que no continue el foreach ya que ha encontrado al socio.
                            break;
                        }

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

    public String Representante(Socio s, Connection conexion) {
        String RepresentanteDatos = "";

        try {

            if(s.getRepresentante()>0) {
                int repr = s.getRepresentante();

                // Creamos el statement
                String sql = "{ call consultas.consultaRepresentante(?, ?, ?) }";
                CallableStatement cs = conexion.prepareCall(sql);

                // Cargamos los parametros de entrada IN que el codigo del representante.
                cs.setInt(1, repr);

                // Cargamos los parametros de entrada OUT que son el nombre y el apellido.

                cs.registerOutParameter(2, Types.VARCHAR);
                cs.registerOutParameter(3, Types.VARCHAR);

                // Ejecutamos la llamada
                cs.execute();

                String nombreRepresentante = cs.getString(2);

                String apellidosRepresentante = cs.getString(3);

                RepresentanteDatos = nombreRepresentante.concat(" " + apellidosRepresentante);
            }

        } catch (SQLException e) {
            System.out.println("Error no se ha ejecutado el procedimiento.");
        }
        return RepresentanteDatos;

    }


}

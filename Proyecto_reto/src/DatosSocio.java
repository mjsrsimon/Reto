import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class DatosSocio {
    private JLabel txtID;
    private JLabel txtNombre;
    private JLabel txtApellidos;
    private JLabel txtFNac;
    private JLabel txtDNI;
    private JLabel txtTelf;
    private JLabel txtFAlta;
    private JLabel txtFBaja;
    private JLabel txtRepresentante;
    private JLabel txtEmail;
    private JLabel txtCuotas;
    private JLabel txtPerfil;
    private JTextField textIDSocio;
    private JTextField textNombre;
    private JTextField textApellido;
    private JTextField textFNacim;
    private JTextField textDNI;
    private JTextField textTelf;
    private JTextField textEmail;
    private JTextField textFAltaC;
    private JTextField textFBajaC;
    private JTextField textRepresentante;
    private JTextField textImpagadas;
    private JTextField textPerfil;
    private JPanel JPDatosSocio;
    private JButton bGuardar;


    public DatosSocio(Socio socio, String representante, Connection conexion, Frame frame) {

        textApellido.setText(socio.getApellidos());
        textDNI.setText(socio.getDNI());
        textEmail.setText(socio.getEmail());
        textFAltaC.setText(socio.getFechaAltaClub());
        textFNacim.setText(socio.getFechaNacimiento());
        textPerfil.setText(socio.getPerfil().toString());
        textNombre.setText(socio.getNombre());
        textTelf.setText(socio.getTelf());
        textIDSocio.setText(String.valueOf(socio.getId_Socio()));

        if (representante.equals("")) {
            textRepresentante.setText("");
        } else {
            textRepresentante.setText(representante);
        }


        bGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (String.valueOf(socio.getPerfil()).equals("USUARIO")) {

                    /* Tenemos que guardar los datos en el socio, en nuestro listado java.*/

                    socio.setTelf(textTelf.getText());
                    socio.setEmail(textEmail.getText());

/* Tenemos que guardar los datos en la bbdd. Tenemos que crear una conexion o utilizar la que tenemos abierta
para guardar los datos.*/

                    guardarDatos(conexion, socio);


                    /*Falta ver como puedo hacer para que cuando pulse guardar cierre la ventana. He conseguido cerrar la ventana pasando el frame */
                    frame.setVisible(false);

                } else if (String.valueOf(socio.getPerfil()).equals("ADMINISTRADOR")) {

                    /* Tenemos que guardar los datos en el socio, en nuestro listado java.*/

                    socio.setTelf(textTelf.getText());
                    socio.setEmail(textEmail.getText());
                    socio.setNombre(textNombre.getText());
                    socio.setApellidos(textApellido.getText());
                    socio.setDNI(textDNI.getText());
                    socio.setFechaNacimiento(textFNacim.getText());
                    socio.setFechaAltaClub(textFAltaC.getText());
                    socio.setFechaBajaClub(textFBajaC.getText());


/* Tenemos que guardar los datos en la bbdd. Tenemos que crear una conexion o utilizar la que tenemos abierta
para guardar los datos.*/

                    guardarDatos(conexion, socio);


                    /*Falta ver como puedo hacer para que cuando pulse guardar cierre la ventana. He conseguido cerrar la ventana pasando el frame */
                    frame.setVisible(false);

                }


            }
        });
    }


    public JPanel getJPDatosSocio(Socio socio, String Representante, Connection conexion) {
        return JPDatosSocio;
    }


    public void guardarDatos(Connection conexion, Socio socio) {

        if (String.valueOf(socio.getPerfil()).equals("USUARIO")) {
            try {
                // Creamos el statement para añadir los ingredientes.
                String sqlActualizaUser = "{ call actualizaciones.actualizaUsuario(?,?,?) }";

                //crear la conexion
                CallableStatement csUser = conexion.prepareCall(sqlActualizaUser);

                //pasamos datos
                csUser.setInt(1, socio.getId_Socio());
                csUser.setString(2, socio.getEmail());
                csUser.setString(3, socio.getTelf());

                //ejecutamos
                csUser.execute();
                csUser.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "No se ha podido actualizar", "Error.", JOptionPane.ERROR_MESSAGE);
            }


        } else if (String.valueOf(socio.getPerfil()).equals("ADMINISTRADOR")) {

            try {
                // Creamos el statement para añadir los ingredientes.
                String sqlActualizaAdmin = "{ call actualizaciones.actualizaAdministrador(?,?,?,?,?,?,?,?,?) }";

                //crear la conexion
                CallableStatement csAdmin = conexion.prepareCall(sqlActualizaAdmin);

                //pasamos datos
                csAdmin.setInt(1, socio.getId_Socio());
                csAdmin.setString(2, socio.getEmail());
                csAdmin.setString(3, socio.getTelf());
                csAdmin.setString(4, socio.getNombre());
                csAdmin.setString(5, socio.getApellidos());
                csAdmin.setString(6, socio.getFechaNacimiento().);
                csAdmin.setString(7, socio.getDNI());
                csAdmin.setString(8, socio.getFechaAltaClub());
                csAdmin.setString(9, socio.getFechaBajaClub());

                //ejecutamos
                csAdmin.execute();
                csAdmin.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "No se ha podido actualizar el administrador", "Error.", JOptionPane.ERROR_MESSAGE);
            }


        }
    }
}



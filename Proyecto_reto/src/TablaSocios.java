import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;


public class TablaSocios {

    private JList LSocios;
    private JScrollPane SociosLista;
    private JPanel panelSocios;
    private JButton bVolver;
    private JScrollPane tSociosDatos;
    private JPanel panelDatos;
    private JPanel JPDatosSocio;
    private JLabel txtNombre;
    private JLabel txtApellidos;
    private JLabel txtFNac;
    private JLabel txtDNI;
    private JLabel txtTelf;
    private JLabel txtFAlta;
    private JLabel txtFBaja;
    private JLabel txtRepresentante;
    private JLabel txtEmail;
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
    private JTextField textPerfil;
    private JLabel txtID;
    private JButton bGuardar;
    private JLabel txtCuotas;
    private JScrollPane tCuotas;
    private JPanel pCuotas;
    private JTable tabla;
    private DatosSocio datosSocio;

    public TablaSocios(Frame frame, List<Socio> socios, Connection conexion, List<CuotaPagada> cuotasPagadas) {


        Modelo<Socio> modelo = new Modelo<>(socios);
        LSocios.setModel(modelo);
        // Cuando se pulsa en un socio, mostrar datos de socio seleccionado

        //Mostrar datos en los campos.

        LSocios.addListSelectionListener(e -> {

            Socio seleccionado = (Socio) LSocios.getSelectedValue();

            if (seleccionado != null) {

                textIDSocio.setText(String.valueOf(seleccionado.getId_Socio()));
                textNombre.setText(seleccionado.getNombre());
                textApellido.setText(seleccionado.getApellidos());
                textTelf.setText(seleccionado.getTelf());
                textEmail.setText(seleccionado.getEmail());
                textDNI.setText(seleccionado.getDNI());
                textFNacim.setText(seleccionado.getFechaNacimiento());
                textFAltaC.setText(seleccionado.getFechaAltaClub());
                textFBajaC.setText(seleccionado.getFechaBajaClub());
                textRepresentante.setText(String.valueOf(seleccionado.getRepresentante()));
                textPerfil.setText(String.valueOf(seleccionado.getPerfil()));

                /*Revisar las cuotas impagadas, guardar datos que llame al procedimiento para guardar datos*/

                tabla = new JTable();
                tabla.setModel(new TablaCuotasImpagadas());
                tCuotas.setViewportView(tabla);
                tabla.setModel(new TablaCuotasImpagadas(seleccionado.getId_Socio(), cuotasPagadas));



            }



            bGuardar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {


                    if(!(textFAltaC.getText().equals(seleccionado.getFechaAltaClub()))){
                        seleccionado.setFechaAltaClub(textFAltaC.getText());
                    }
                    if(!(textFBajaC.getText().equals(seleccionado.getFechaBajaClub()))){
                        seleccionado.setFechaBajaClub(textFBajaC.getText());
                    }
                    if(!(Perfil.valueOf(textPerfil.getText()).equals(seleccionado.getPerfil().toString()))){
                        seleccionado.setPerfil(Perfil.valueOf(textPerfil.getText()));
                    }

                    if(!(textApellido.getText().equals(seleccionado.getApellidos()))){
                        seleccionado.setApellidos(textApellido.getText());
                    }

                    if(!(textNombre.getText().equals(seleccionado.getNombre()))){
                        seleccionado.setNombre(textNombre.getText());
                    }

                    if(!(textEmail.getText().equals(seleccionado.getEmail()))){
                        seleccionado.setEmail(textEmail.getText());
                    }

                    if(!(textTelf.getText().equals(seleccionado.getTelf()))){
                        seleccionado.setTelf(textTelf.getText());
                    }

                    if(!(textDNI.getText().equals(seleccionado.getDNI()))){
                        seleccionado.setDNI(textDNI.getText());
                    }

                    if(!(textFNacim.getText().equals(seleccionado.getFechaNacimiento()))){
                        seleccionado.setFechaNacimiento(textFNacim.getText());
                    }
                    try {
                        // Creamos el statement para actualizar datos del administrador.
                        String sqlActualizaAdminSocio = "{ call actualizaciones.actualizaAdministradorSocio(?,?,?,?,?,?,?,?,?) }";

                        //crear la conexion
                        CallableStatement csAdmin = conexion.prepareCall(sqlActualizaAdminSocio);

                        //Covertir string en fecha para java y este en sql.

                        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

                        java.util.Date fNacimiento = null;
                        fNacimiento = formato.parse(seleccionado.getFechaNacimiento());
                        java.sql.Date fNac = new java.sql.Date(fNacimiento.getTime());


                        java.util.Date fAltaClub = null;
                        fAltaClub = formato.parse(seleccionado.getFechaAltaClub());
                        java.sql.Date fAlta = new java.sql.Date(fAltaClub.getTime());


                        java.util.Date fBajaClub = null;
                        fBajaClub = formato.parse(seleccionado.getFechaNacimiento());
                        java.sql.Date fBaja = new java.sql.Date(fBajaClub.getTime());


                        //pasamos datos
                        csAdmin.setInt(1, seleccionado.getId_Socio());
                        csAdmin.setString(2, seleccionado.getEmail());
                        csAdmin.setString(3, seleccionado.getTelf());
                        csAdmin.setString(4, seleccionado.getNombre());
                        csAdmin.setString(5, seleccionado.getApellidos());
                        csAdmin.setDate(6, fNac);
                        csAdmin.setString(7, seleccionado.getDNI());
                        csAdmin.setDate(8, fAlta);
                        csAdmin.setDate(9, fBaja);

                        //ejecutamos
                        csAdmin.execute();
                        csAdmin.close();

                        System.out.println("se ha actualizado correctamente. ");
                    } catch (SQLException | ParseException ex) {
                        JOptionPane.showMessageDialog(null, "No se ha podido actualizar el administrador", "Error.", JOptionPane.ERROR_MESSAGE);
                    }

                }
            });


        });


        bVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
            }
        });


    }

    public JPanel getPanelSocios(Frame frame, List<Socio> socios, Connection conexion, List<CuotaPagada> cuotasPagadas) {
        Modelo<Socio> modelo = new Modelo<>(socios);
        LSocios.setModel(modelo);

        return panelSocios;
    }


}

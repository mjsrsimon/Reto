import javax.swing.*;

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


    public DatosSocio(Socio socio, String representante) {

        textApellido.setText(socio.getApellidos());
        textDNI.setText(socio.getDNI());
        textEmail.setText(socio.getEmail());
        textFAltaC.setText(socio.getFechaAltaClub());
        textFNacim.setText(socio.getFechaNacimiento());
        textPerfil.setText(socio.getPerfil().toString());
        textNombre.setText(socio.getNombre());
        textTelf.setText(socio.getTelf());
        textIDSocio.setText(String.valueOf(socio.getId_Socio()));

        if(socio.getFechaBajaClub() == null){
            textFBajaC.setText("Sigue de alta");
        }else{
            textFBajaC.setText(socio.getFechaBajaClub());
        }

        if(representante.equals("")){
            textRepresentante.setText("Es mayor de edad.");
        }else {
            textRepresentante.setText(representante);
        }


    }

    public JPanel getJPDatosSocio(Socio socio, String Representante) {
        return JPDatosSocio;
    }
}

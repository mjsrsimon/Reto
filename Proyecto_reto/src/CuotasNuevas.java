import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class CuotasNuevas {
    private JTextField textPAdulto;
    private JTextField textPMenor;
    private JTextField textAnyo;
    private JPanel pCuotasNuevas;
    private JButton bGuardar;
    private JButton bVolver;


    public CuotasNuevas(List<Cuota> cuotas, List<CuotaPagada> cuotasPagadas, Connection conexion, List<Socio> socios, Frame frame) {
        bGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    // Creamos el statement
                    String sqlNewCuo = "{ call inserciones.nuevaCuota(?, ?, ?, ?) }";
                    CallableStatement cs = conexion.prepareCall(sqlNewCuo);

//Tengo que convertir el string en java.util.Date y este en java.sql.Date.

                    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

                    java.util.Date fechaCuota = null;
                    try {
                        fechaCuota = formato.parse(textAnyo.getText());
                    } catch (ParseException ex) {
                        JOptionPane.showMessageDialog(null, "Dato incorrecto en fecha. \n Introduca dd/mm/yyyy", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                    java.sql.Date fCuota = new java.sql.Date(fechaCuota.getTime());


                    // Cargamos los parametros de entrada IN que son: precioAdulto, precioMenor y año.
                    cs.setDouble(2, Double.parseDouble(textPAdulto.getText()));
                    cs.setDouble(3, Double.parseDouble(textPMenor.getText()));
                    cs.setDate(4, fCuota);

                    // Cargamos los parametros de entrada OUT que es el codigo de Cuota.

                    cs.registerOutParameter(1, Types.INTEGER);
                    // Ejecutamos la llamada para insertar una nueva cuota.
                    cs.execute();

                    int codigoCuota = cs.getInt(1);
                    // añadimos la nueva cuota a nuestra lista.

                    Cuota nuevaCuota = new Cuota(codigoCuota,
                            Double.parseDouble(textPAdulto.getText()),
                            Double.parseDouble(textPMenor.getText()),
                            textAnyo.getText());
                    cuotas.add(nuevaCuota);


                    cs.close();
//Automaticamente debe añadir la cuota nueva a todos los socios.

                    String sqlCuotaSocio = "{ call inserciones.addNewCuotaSocio(?, ?, ?, ?) }";
                    CallableStatement nCS = conexion.prepareCall(sqlCuotaSocio);


                    for (Socio s : socios) {

                        if (s.getFechaBajaClub() == null) {
                            cuotasPagadas.add(new CuotaPagada(s, nuevaCuota, textAnyo.getText(), false));

                            int codSocio = s.getId_Socio();

                            nCS.setInt(1, codSocio);
                            nCS.setInt(2, codigoCuota);
                            nCS.setDate(3, fCuota);
                            nCS.setString(4, "No");
                            nCS.execute();
                        }

                    }

                    nCS.close();

                    frame.setVisible(false);

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Datos erroneos", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        bVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
            }
        });
    }

    public JPanel getpCuotasNuevas(List<Cuota> cuotas, List<CuotaPagada> cuotasPagadas, Connection conexion, List<Socio> socios) {
        return pCuotasNuevas;
    }


}

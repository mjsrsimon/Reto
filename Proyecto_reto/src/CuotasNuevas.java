import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.List;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class CuotasNuevas {
    private JTextField textPAdulto;
    private JTextField textPMenor;
    private JTextField textAnyo;
    private JPanel pCuotasNuevas;
    private JButton bGuardar;


    public CuotasNuevas(List<Cuota> cuotas, List<CuotaPagada> cuotasPagadas, Connection conexion, List<Socio> socios) {
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
                    fechaCuota = formato.parse(textAnyo.getText());
                    java.sql.Date fCuota = new java.sql.Date(fechaCuota.getTime());


                    // Cargamos los parametros de entrada IN que son: precioAdulto, precioMenor y año.
                    cs.setDouble(2, Double.parseDouble(textPAdulto.getText()));
                    cs.setDouble(3, Double.parseDouble(textPMenor.getText()));
                    cs.setDate(4,fCuota);

                    // Cargamos los parametros de entrada OUT que es el codigo de Cuota.

                    cs.registerOutParameter(1, Types.INTEGER);

//Me da error en la fecha, en BBDD campo definido como DATE.




                    // Ejecutamos la llamada
                    cs.execute();

                    int codigoCuota = cs.getInt(1);
                    // añadimos la nueva cuota a nuestra lista.
                    cuotas.add(new Cuota
                            (codigoCuota,
                                    Double.parseDouble(textPAdulto.getText()),
                                    Double.parseDouble(textPMenor.getText()),
                                    textAnyo.getText()));


//Automaticamente debe añadir la cuota nueva a todos los socios.

                   /* for (Socio s: socios){
                        cuo

                    }*/


                } catch (SQLException | ParseException ex) {
                    ex.printStackTrace();
                }

            }
        });
    }

    public JPanel getpCuotasNuevas(List<Cuota> cuotas, List<CuotaPagada> cuotasPagadas, Connection conexion, List<Socio> socios) {
        return pCuotasNuevas;
    }


}

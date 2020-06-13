import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

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

                    // Cargamos los parametros de entrada IN que son: precioAdulto, precioMenor y año.
                    cs.setDouble(2, Double.parseDouble(textPAdulto.getText()));
                    cs.setDouble(3, Double.parseDouble(textPMenor.getText()));
                    cs.setString(4, textAnyo.getText());

                    // Cargamos los parametros de entrada OUT que es el codigo de Cuota.

                   cs.registerOutParameter(1, Types.INTEGER);


                    // Ejecutamos la llamada
                    cs.execute();

                    // añadimos la nueva cuota a nuestra lista.
                    cuotas.add(new Cuota
                            (cs.getInt(1),
                            Double.parseDouble(textPAdulto.getText()),
                            Double.parseDouble(textPMenor.getText()),
                            textAnyo.getText()));


                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }
        });
    }

    public JPanel getpCuotasNuevas(List<Cuota> cuotas, List<CuotaPagada> cuotasPagadas, Connection conexion, List<Socio> socios) {
        return pCuotasNuevas;
    }


}

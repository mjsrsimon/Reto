import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class TDatosSocios extends AbstractTableModel {

    private String[] columnas = {"ID", "NOMBRE", "APELLIDOS", "FECHA NACIM", "DNI", "TELEFONO", "EMAIL", "FECHA ALTA CLUB", "FECHA BAJA CLUB", "PERFIL", "REPRESNTANTE"};

    private int idSocio;

    private List<Socio> SociosT;


    public TDatosSocios() {
        this.idSocio = -1;
        SociosT = new ArrayList<>();
    }

    public TDatosSocios(int idSocio, List<Socio> socios) {
        this.idSocio = idSocio;
        SociosT = new ArrayList<>();

        for (Socio s : socios) {
            if (s.getId_Socio() == idSocio) {
//necesito recorrer la tabla pedidos donde aparezca el cliente seleccionado y guardar en un arraylist para mostrar en la tabla
                SociosT.add(s);
            }

        }


    }


    @Override
    public int getRowCount() {

        return SociosT.size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        /*esto hay que modificar para que devuelva la relacion de los pedidos.*/
        Socio so = SociosT.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return so.getId_Socio(); // aqui es el id que se ha asignado al socio.
            case 1:
                return so.getNombre(); // nombre del socio
            case 2:
                return so.getApellidos(); // apellidos del socio.
            case 3:
                return so.getFechaNacimiento(); // fecha de nacimiento
            case 4:
                return so.getDNI();
            case 5:
                return so.getTelf();
            case 6:
                return so.getEmail();
            case 7:
                return so.getFechaAltaClub();
            case 8:
                return so.getFechaBajaClub();
            case 9:
                return so.getPerfil();
            case 10:
                return so.getRepresentante();


        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        return columnas[column];
    }


}

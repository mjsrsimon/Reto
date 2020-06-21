import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;


public class TablaCuotasImpagadas extends AbstractTableModel {

    private String[] columnas = {"FECHA", "PAGADO"};

    private int idsocio;
    private List<CuotaPagada> cuotaImpagadas;


    public TablaCuotasImpagadas() {
        this.idsocio = -1;
        cuotaImpagadas = new ArrayList<>();
    }

    public TablaCuotasImpagadas(int socio, List<CuotaPagada> cuotasPagadas) {
        this.idsocio = socio;
        cuotaImpagadas = new ArrayList<>();

        for (CuotaPagada p : cuotasPagadas) {
            if (p.getSocio().getId_Socio() == idsocio) {
                cuotaImpagadas.add(p);
            }
        }


    }

    @Override
    public int getRowCount() {
        return cuotaImpagadas.size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        CuotaPagada c = cuotaImpagadas.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return c.getFecha();
            case 1:
                if (c.getPagado()) {
                    return "SI";
                } else {
                    return "NO";
                }

        }

        return null;
    }

    @Override
    public String getColumnName(int column) {
        return columnas[column];
    }

}




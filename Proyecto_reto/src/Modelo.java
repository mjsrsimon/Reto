
import javax.swing.*;
import java.util.List;

public class Modelo<T> extends AbstractListModel<T> {

    List<T> lista;

    public Modelo(List<T> lista) {
        this.lista = lista;
    }

    @Override
    public int getSize() {
        return lista.size();
    }

    @Override
    public T getElementAt(int index) {
        return lista.get(index);
    }

}

public class Cuota {
    private int id_cuota;
    private double precioAdulto;
    private double precioMenor;
    private String anyo;

    public Cuota() {
    }

    public Cuota(int id_cuota, double precioAdulto, double precioMenor, String anyo) {
        this.id_cuota = id_cuota;
        this.precioAdulto = precioAdulto;
        this.precioMenor = precioMenor;
        this.anyo = anyo;
    }

    public void setId_cuota(int id_cuota) {
        this.id_cuota = id_cuota;
    }

    public void setPrecioAdulto(double precioAdulto) {
        this.precioAdulto = precioAdulto;
    }

    public void setPrecioMenor(double precioMenor) {
        this.precioMenor = precioMenor;
    }

    public void setAnyo(String anyo) {
        this.anyo = anyo;
    }

    public int getId_cuota() {
        return id_cuota;
    }

    public double getPrecioAdulto() {
        return precioAdulto;
    }

    public double getPrecioMenor() {
        return precioMenor;
    }

    public String getAnyo() {
        return anyo;
    }
}

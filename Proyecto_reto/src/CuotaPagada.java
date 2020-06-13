public class CuotaPagada {


    private Socio socio;
    private Cuota cuota;

    private String fecha;
    private Boolean pagado;


    public CuotaPagada(Socio socio, Cuota cuota, String fecha, Boolean pagado) {
        this.socio = socio;
        this.cuota = cuota;
        this.fecha = fecha;
        this.pagado = pagado;

    }

    public void setSocio(Socio socio) {
        this.socio = socio;
    }

    public void setCuota(Cuota cuota) {
        this.cuota = cuota;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setPagado(Boolean pagado) {
        this.pagado = pagado;
    }

    public Socio getSocio() {
        return socio;
    }

    public Cuota getCuota() {
        return cuota;
    }

    public String getFecha() {
        return fecha;
    }

    public Boolean getPagado() {
        return pagado;
    }
}

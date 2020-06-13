public class Socio {

    private int id_Socio;
    private String nombre;
    private String apellidos;
    private String fechaNacimiento;
    private String DNI;
    private String telf;
    private String email;
    private String fechaAltaClub;
    private String fechaBajaClub;
    private Perfil perfil;
    private int  representante;

    public Socio(int id_Socio, String nombre, String apellidos, String fechaNacimiento, String DNI, String telf, String email,
                 String fechaAltaClub, String fechaBajaClub, Perfil perfil, int representante) {
        this.id_Socio = id_Socio;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.DNI = DNI;
        this.telf = telf;
        this.email = email;
        this.fechaAltaClub = fechaAltaClub;
        this.fechaBajaClub = fechaBajaClub;
        this.perfil = perfil;
        this.representante = representante;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getDNI() {
        return DNI;
    }

    public String getTelf() {
        return telf;
    }

    public String getEmail() {
        return email;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public int getId_Socio() {
        return id_Socio;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getFechaAltaClub() {
        return fechaAltaClub;
    }

    public String getFechaBajaClub() {
        return fechaBajaClub;
    }

    public int getRepresentante() {
        return representante;
    }

    public void setId_Socio(int id_Socio) {
        this.id_Socio = id_Socio;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public void setTelf(String telf) {
        this.telf = telf;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFechaAltaClub(String fechaAltaClub) {
        this.fechaAltaClub = fechaAltaClub;
    }

    public void setFechaBajaClub(String fechaBajaClub) {
        this.fechaBajaClub = fechaBajaClub;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public void setRepresentante(int representante) {
        this.representante = representante;
    }

    @Override
    public String toString() {
        return nombre + " " + apellidos;

    }


}

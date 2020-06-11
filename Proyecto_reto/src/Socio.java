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
}

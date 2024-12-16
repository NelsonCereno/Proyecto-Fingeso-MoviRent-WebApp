package Grupo_5.MoviRent_WebApp.ModuloUsuario.Entidades;

import jakarta.persistence.*;
import lombok.*;

@Entity //notacion para java spring: Esto es una entidad
@Table  //notacion: se puede definir el nombre de la tabla  ej://(name = "usuario")

@AllArgsConstructor //Instancias de tablas
@NoArgsConstructor  //

//@Getter//con esto me ahorro los getters y seters
//@Setter
@Data

public class EntidadUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Generacion de IDs secuenciales
    private Long id;

//    @Column(name = "super nombre")
    private String nombre;

    private String apellido;

    private String fechaNacimiento;

    private String celular;

    @Column(unique = true, nullable = false)
    private String correo;

    private String contrasena;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {

        return apellido;
    }

    public void setApellido(String apellido) {

        this.apellido = apellido;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}

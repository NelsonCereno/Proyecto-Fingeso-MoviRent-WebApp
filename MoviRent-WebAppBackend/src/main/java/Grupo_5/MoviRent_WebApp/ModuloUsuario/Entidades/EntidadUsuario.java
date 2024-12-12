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

    private String correo;

    private String contrasena;
}

package Grupo_5.MoviRent_WebApp.Entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table  //(name = "usuario")
@AllArgsConstructor //definiciones de gets y sets
@NoArgsConstructor

public class EntidadUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
}

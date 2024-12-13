package Grupo_5.MoviRent_WebApp.ModuloVehiculo.Entidades;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Data
@Entity
@Table

@AllArgsConstructor
@NoArgsConstructor


public class EntidadVehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String patente;
    private String codigoAcriss;
    private String marca;
    private String modelo;
    private String color;
    private String anio;
    private String precio;
    private String fechaRevision;
    private String kilometraje;
    private Boolean disponibilidad;
    private String tipo;
    private String estado;
    private String descripcion;
    private String imagen;





}

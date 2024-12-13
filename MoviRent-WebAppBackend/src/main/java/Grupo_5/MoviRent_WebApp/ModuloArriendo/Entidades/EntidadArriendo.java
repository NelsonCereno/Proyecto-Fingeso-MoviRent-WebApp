package Grupo_5.MoviRent_WebApp.ModuloArriendo.Entidades;

import jakarta.persistence.*;
import lombok.*;

@Entity //notacion para java spring: Esto es una entidad
@Table  //notacion: se puede definir el nombre de la tabla  ej://(name = "usuario")

@AllArgsConstructor //Instancias de tablas
@NoArgsConstructor  //

//@Getter//con esto me ahorro los getters y seters
//@Setter
@Data

public class EntidadArriendo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Generacion de IDs secuenciales
    private Long id;

    private String fechaInicio;
    private String fechaTermino;
    private Long idSucursalLlegada;
    private Long idSucursalPartida;
    private Long idVehiculo;
    private int montoPagar;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaTermino() {
        return fechaTermino;
    }

    public void setFechaTermino(String fechaTermino) {
        this.fechaTermino = fechaTermino;
    }

    public Long getIdSucursalLlegada() {
        return idSucursalLlegada;
    }

    public void setIdSucursalLlegada(Long idSucursalLlegada) {
        this.idSucursalLlegada = idSucursalLlegada;
    }

    public Long getIdSucursalPartida() {
        return idSucursalPartida;
    }

    public void setIdSucursalPartida(Long idSucursalPartida) {
        this.idSucursalPartida = idSucursalPartida;
    }

    public Long getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(Long idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public int getMontoPagar() {
        return montoPagar;
    }

    public void setMontoPagar(int montoPagar) {
        this.montoPagar = montoPagar;
    }
}

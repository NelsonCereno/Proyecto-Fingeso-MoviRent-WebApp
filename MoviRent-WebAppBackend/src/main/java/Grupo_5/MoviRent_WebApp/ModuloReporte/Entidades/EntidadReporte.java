package Grupo_5.MoviRent_WebApp.ModuloReporte.Entidades;

import java.util.Date;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "reportes")
public class EntidadReporte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReporte;

    @Temporal(TemporalType.DATE)
    private Date fechaReporte;

    private String tipoReporte;
    private String contenido;
    private Long IdUsuario;
    private Long vehiculoId; // Nuevo campo para ID del Vehículo

    // Getters y Setters

    public Long getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(Long idReporte) {
        this.idReporte = idReporte;
    }

    public Date getFechaReporte() {
        return fechaReporte;
    }

    public void setFechaReporte(Date fechaReporte) {
        this.fechaReporte = fechaReporte;
    }

    public String getTipoReporte() {
        return tipoReporte;
    }

    public void setTipoReporte(String tipoReporte) {
        this.tipoReporte = tipoReporte;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Long getUsuarioId() {
        return IdUsuario;
    }

    public void setUsuarioId(Long usuarioId) {
        this.IdUsuario = usuarioId;
    }

    public Long getVehiculoId() {
        return vehiculoId;
    }

    public void setVehiculoId(Long vehiculoId) {
        this.vehiculoId = vehiculoId;
    }
}

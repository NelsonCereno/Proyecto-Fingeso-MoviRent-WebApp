package Grupo_5.MoviRent_WebApp.ModuloVehiculo.Entidades;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table

@AllArgsConstructor
@NoArgsConstructor


public class EntidadVehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVehiculo;
    private String patente;
    private Integer nroPasajeros;
    private Integer capacidadMaletero;
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
    private String imagen; //Aca anexar una URL con el modelo del auto.


    public Long getIdVehiculo() {
        return idVehiculo;
    }

    public void setId(Long id) {
        this.idVehiculo = id;
    }

    public Integer getNroPasajeros() {
        return nroPasajeros;
    }

    public void setNroPasajeros(Integer nroPasajeros) {
        this.nroPasajeros = nroPasajeros;
    }

    public Integer getCapacidadMaletero() {
        return capacidadMaletero;
    }

    public void setCapacidadMaletero(Integer capacidadMaletero) {
        this.capacidadMaletero = capacidadMaletero;
    }

    public void setIdVehiculo(Long idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public String getCodigoAcriss() {
        return codigoAcriss;
    }

    public void setCodigoAcriss(String codigoAcriss) {
        this.codigoAcriss = codigoAcriss;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getFechaRevision() {
        return fechaRevision;
    }

    public void setFechaRevision(String fechaRevision) {
        this.fechaRevision = fechaRevision;
    }

    public String getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(String kilometraje) {
        this.kilometraje = kilometraje;
    }

    public Boolean getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(Boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

}

package Grupo_5.MoviRent_WebApp.ModuloVehiculo.Servicios;

import Grupo_5.MoviRent_WebApp.ModuloVehiculo.Entidades.EntidadVehiculo;
import Grupo_5.MoviRent_WebApp.ModuloVehiculo.Repositorios.RepositorioVehiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class ServicioVehiculo {
    private final RepositorioVehiculo repositorioVehiculo;

    @Autowired
    public ServicioVehiculo(RepositorioVehiculo repositorioVehiculo) {
        this.repositorioVehiculo = repositorioVehiculo;
    }
    //Agregar un vehículo
    public void agregarVehiculo(EntidadVehiculo vehiculo) {
        if (!esPatenteValida(vehiculo.getPatente())) {
            throw new IllegalArgumentException("La patente no cumple con el formato requerido: AA*AA*00");
        }
        if (!vehiculo.getAnio().matches("^\\d{4}$")) {
            throw new IllegalArgumentException("El año debe ser un numero de 4 dígitos.");
        }
        try {
            Double kilometraje = Double.parseDouble(vehiculo.getKilometraje());
            if (kilometraje < 0) {
                throw new IllegalArgumentException("El kilometraje debe ser un numero positivo.");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El kilometraje debe ser un numero valido.");
        }

        try {
            Double precio = Double.parseDouble(vehiculo.getPrecio());
            if (precio < 0) {
                throw new IllegalArgumentException("El precio debe ser un numero positivo.");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El precio debe ser un numero valido.");
        }
        repositorioVehiculo.save(vehiculo);
    }

    //Eliminar un vehiculo
    public void eliminarVehiculo(Long id) {
        repositorioVehiculo.deleteById(id);
    }
    //Buscar por ID
    public EntidadVehiculo buscarVehiculoPorId(Long id) {
        return repositorioVehiculo.findById(id).orElse(null);
    }
    //Buscar por patente
    public EntidadVehiculo buscarVehiculoPorPatente(String patente) {
        return repositorioVehiculo.findByPatente(patente);
    }
    //Obtener todos los vehiculos
    public List<EntidadVehiculo> obtenerVehiculos() {
        List<EntidadVehiculo> vehiculos = repositorioVehiculo.findAll();

        // Asegura valores predeterminados para evitar nulos
        for (EntidadVehiculo vehiculo : vehiculos) {
            if (vehiculo.getPatente() == null) vehiculo.setPatente("Sin patente");
            if (vehiculo.getModelo() == null) vehiculo.setModelo("Sin modelo");
            if (vehiculo.getMarca() == null) vehiculo.setMarca("Sin marca");
            if (vehiculo.getNroPasajeros() < 0 || vehiculo.getNroPasajeros() == null) vehiculo.setNroPasajeros(1); // Capacidad predeterminada
            if (vehiculo.getAnio() == null) vehiculo.setAnio("Desconocido");
            if (vehiculo.getPrecio() == null) vehiculo.setPrecio("No disponible");
            if (vehiculo.getTipo() == null) vehiculo.setTipo("No especificado");
            if (vehiculo.getEstado() == null) vehiculo.setEstado("Desconocido");
            if (vehiculo.getDescripcion() == null) vehiculo.setDescripcion("Sin descripción");
        }
        return vehiculos;
    }

    // Buscar por disponibilidad
    public List<EntidadVehiculo> buscarPorDisponibilidad(Boolean disponibilidad) {
        return repositorioVehiculo.findByDisponibilidad(disponibilidad);
    }
    //Buscar por capacidad de pasajeros
    public List<EntidadVehiculo> buscarPorNroPasajeros(Integer nroPasajeros) {
        return repositorioVehiculo.findByNroPasajeros(nroPasajeros);
    }

    public boolean esPatenteValida(String patente) {
        String regex = "^[A-Z]{2}\\*[A-Z]{2}\\*[0-9]{2}$";
        return Pattern.matches(regex, patente);
    }

}

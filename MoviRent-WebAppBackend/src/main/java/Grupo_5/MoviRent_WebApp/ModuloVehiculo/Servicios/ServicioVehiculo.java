package Grupo_5.MoviRent_WebApp.ModuloVehiculo.Servicios;

import Grupo_5.MoviRent_WebApp.ModuloVehiculo.Entidades.EntidadVehiculo;
import Grupo_5.MoviRent_WebApp.ModuloVehiculo.Repositorios.RepositorioVehiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioVehiculo {
    private final RepositorioVehiculo repositorioVehiculo;

    @Autowired
    public ServicioVehiculo(RepositorioVehiculo repositorioVehiculo) {
        this.repositorioVehiculo = repositorioVehiculo;
    }
    //Agregar un vehículo
    public void agregarVehiculo(EntidadVehiculo vehiculo) {
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
        return repositorioVehiculo.findAll();
    }
    // Buscar por disponibilidad
    public List<EntidadVehiculo> buscarPorDisponibilidad(Boolean disponibilidad) {
        return repositorioVehiculo.findByDisponibilidad(disponibilidad);
    }
}

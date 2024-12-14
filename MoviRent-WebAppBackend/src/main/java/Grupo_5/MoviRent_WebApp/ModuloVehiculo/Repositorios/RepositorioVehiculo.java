package Grupo_5.MoviRent_WebApp.ModuloVehiculo.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import Grupo_5.MoviRent_WebApp.ModuloVehiculo.Entidades.EntidadVehiculo;

import java.util.List;

@Repository
public interface RepositorioVehiculo extends JpaRepository<EntidadVehiculo, Long> {
    // Buscar por patente
    EntidadVehiculo findByPatente(String patente);

    // Buscar por disponibilidad
    List<EntidadVehiculo> findByDisponibilidad(Boolean disponibilidad);

}


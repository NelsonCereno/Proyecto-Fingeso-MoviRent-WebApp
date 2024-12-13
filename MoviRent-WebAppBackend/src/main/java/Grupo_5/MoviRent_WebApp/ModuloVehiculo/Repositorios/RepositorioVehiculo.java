package Grupo_5.MoviRent_WebApp.ModuloVehiculo.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import Grupo_5.MoviRent_WebApp.ModuloVehiculo.Entidades.EntidadVehiculo;

@Repository
public interface RepositorioVehiculo extends JpaRepository<EntidadVehiculo, Long> {
    //Gracias a JPA las consultas de hacen automaticas
}

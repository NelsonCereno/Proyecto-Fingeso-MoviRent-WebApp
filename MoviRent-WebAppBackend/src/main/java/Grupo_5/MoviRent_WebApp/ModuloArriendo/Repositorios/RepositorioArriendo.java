
package Grupo_5.MoviRent_WebApp.ModuloArriendo.Repositorios;

import Grupo_5.MoviRent_WebApp.ModuloArriendo.Entidades.EntidadArriendo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositorioArriendo extends JpaRepository<EntidadArriendo, Long> {
    //Gracias a JPA las consultas de hacen automaticas
    List<EntidadArriendo> findByUsuarioId(Long idUsuario);
}

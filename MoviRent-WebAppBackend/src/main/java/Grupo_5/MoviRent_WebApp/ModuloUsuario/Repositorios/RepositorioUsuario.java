
package Grupo_5.MoviRent_WebApp.ModuloUsuario.Repositorios;

import Grupo_5.MoviRent_WebApp.ModuloUsuario.Entidades.EntidadUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioUsuario extends JpaRepository<EntidadUsuario, Long> {
    //Gracias a JPA las consultas de hacen automaticas
}

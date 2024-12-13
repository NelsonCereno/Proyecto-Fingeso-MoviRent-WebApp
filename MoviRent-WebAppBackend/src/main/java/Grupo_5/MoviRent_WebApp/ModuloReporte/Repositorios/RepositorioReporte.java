
// REPOSITORIO
package Grupo_5.MoviRent_WebApp.ModuloReporte.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioReporte extends JpaRepository<Grupo_5.MoviRent_WebApp.ModuloReporte.Entidades.EntidadReporte, Long> {
}

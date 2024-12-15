package Grupo_5.MoviRent_WebApp.ModuloUsuario.Repositorios;

import Grupo_5.MoviRent_WebApp.ModuloUsuario.Entidades.EntidadUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositorioUsuario extends JpaRepository<EntidadUsuario, Long> {
    boolean existsByCorreo(String correo);
    //Gracias a JPA las consultas de hacen automaticas
}

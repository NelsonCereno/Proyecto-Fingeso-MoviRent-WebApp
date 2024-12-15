package Grupo_5.MoviRent_WebApp.ModuloUsuario.Servicios;


import Grupo_5.MoviRent_WebApp.ModuloUsuario.Entidades.EntidadUsuario;
import Grupo_5.MoviRent_WebApp.ModuloUsuario.Repositorios.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioUsuario {


    private final RepositorioUsuario repositorioUsuario;

    @Autowired
    public ServicioUsuario(RepositorioUsuario repositorioUsuario) {

        this.repositorioUsuario = repositorioUsuario;
    }

    // CRUD

    //create
    public EntidadUsuario crearUsuario(EntidadUsuario usuario){
        return repositorioUsuario.save(usuario);
    }

    //read
    public EntidadUsuario getUsuarioById(Long id){
        return
                repositorioUsuario.findById(id).orElse(null);
    }

    //obtener todos los usuarios
    public List<EntidadUsuario> getAllUsuarios() {
        return repositorioUsuario.findAll();
    }

    //Update

    public EntidadUsuario updateUsuario(EntidadUsuario usuario){
        return repositorioUsuario.save(usuario);

    }

    //Delete
    public void deleteUsuario(Long id){

        repositorioUsuario.deleteById(id);
    }


}
